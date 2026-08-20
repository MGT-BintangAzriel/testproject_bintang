package wf.practice2_bintang.general.domain.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.intra_mart.common.aid.jdk.java.util.LocaleUtil;
import jp.co.intra_mart.foundation.security.message.MessageManager;
import jp.co.intra_mart.foundation.workflow.application.general.UserActvMatterPropertyValue;
import jp.co.intra_mart.foundation.workflow.application.model.UserMatterPropertyModel;
import jp.co.intra_mart.foundation.workflow.exception.WorkflowException;
import jp.co.intra_mart.foundation.workflow.exception.WorkflowExternalException;
import jp.co.intra_mart.foundation.workflow.plugin.process.action.ActionProcessParameter;
import jp.co.intra_mart.foundation.workflow.util.WorkflowNumberingManager;

import wf.common.constant.WorkflowStatus;
import wf.practice2_bintang.general.app.BookForm;
import wf.practice2_bintang.general.BookActionProcessService;
import wf.practice2_bintang.general.domain.model.BookHeaderInfoTempModel;
import wf.practice2_bintang.general.domain.model.BookHeaderModel;
import wf.practice2_bintang.general.domain.repository.BookHeaderInfoTempRepository;
import wf.practice2_bintang.general.domain.repository.BookHeaderRepository;

/**
 * ワークフローのアクション処理（申請、再申請など）の実装クラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
@Service
@Transactional(propagation = Propagation.MANDATORY)
public class BookActionProcessServiceImpl implements BookActionProcessService {

	/**
	 * 申請時の登録処理を行うメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @return 発行されたユーザデータID
	 * @throws Exception 処理中に例外が発生した場合
	 */
	@Override
	public String apply(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {
		String number = null;
		try {

			// リポジトリをインスタンス化する
			BookHeaderRepository BookHeaderDB = new BookHeaderRepository();
			BookHeaderInfoTempRepository BookHeaderInfoTempDB = new BookHeaderInfoTempRepository();

			// ワークフローのパラメータ (parameter)と画面入力値（userParameter）からモデルを構築する
			BookHeaderModel entity_BookHeader = getEntity_Header(parameter, userParameter);
			BookHeaderInfoTempModel entity_BookHeaderInfoTemp = getEntity_HeaderInfoTemp(parameter,
					userParameter);

			// DBへの登録処理を実行する
			BookHeaderDB.insertDataHeader(entity_BookHeader);
			BookHeaderInfoTempDB.insertDataHeader(entity_BookHeaderInfoTemp);

			// 分岐判定用の金額プロパティを登録する
			int get_Price = entity_BookHeaderInfoTemp.getPrice();
			createMatterProperty(parameter.getUserDataId(), "price_branch", String.valueOf(get_Price));

			number = WorkflowNumberingManager.getNumber();

		} catch (final WorkflowException e) {
			throw new WorkflowExternalException(MessageManager.getInstance()
					.getMessage(LocaleUtil.toLocale(parameter.getLocaleId()), "SAMPLE.IMW.ERR.003"));

		}

		return number;
	}

	/**
	 * 案件プロパティ情報を登録するメソッド。
	 *
	 * @param userDataId          案件プロパティを登録する対象のユーザーデータID
	 * @param matterPropertyKey   案件プロパティのプロパティキー名
	 * @param matterPropertyValue 案件プロパティの値
	 * @throws WorkflowException 案件プロパティ登録処理中に例外が発生した場合
	 */
	private final void createMatterProperty(final String userDataId, final String matterPropertyKey,
			final String matterPropertyValue) throws WorkflowException {

		// プロパティ情報を設定する
		final UserMatterPropertyModel matterPropertyModel = new UserMatterPropertyModel();
		matterPropertyModel.setUserDataId(userDataId);
		matterPropertyModel.setMatterPropertyKey(matterPropertyKey);
		matterPropertyModel.setMatterPropertyValue(matterPropertyValue);

		// プロパティ情報を登録する
		UserActvMatterPropertyValue property;
		property = new UserActvMatterPropertyValue();
		final UserMatterPropertyModel[] matterProperty = new UserMatterPropertyModel[1];
		matterProperty[0] = matterPropertyModel;
		property.createMatterProperty(matterProperty);
	}

	/**
	 * 案件プロパティ情報を更新するメソッド。
	 *
	 * @param userDataId          案件プロパティを更新する対象のユーザーデータID
	 * @param matterPropertyKey   案件プロパティのプロパティキー名
	 * @param matterPropertyValue 案件プロパティの値
	 * @throws WorkflowException 案件プロパティ更新処理中に例外が発生した場合
	 */
	@SuppressWarnings("unused")
	private final void updateMatterProperty(final String userDataId, final String matterPropertyKey,
			final String matterPropertyValue) throws WorkflowException {

		// プロパティ情報を設定する
		final UserMatterPropertyModel matterPropertyModel = new UserMatterPropertyModel();
		matterPropertyModel.setUserDataId(userDataId);
		matterPropertyModel.setMatterPropertyKey(matterPropertyKey);
		matterPropertyModel.setMatterPropertyValue(matterPropertyValue);

		// プロパティ情報を更新する
		UserActvMatterPropertyValue property;
		property = new UserActvMatterPropertyValue();
		final UserMatterPropertyModel[] matterProperty = new UserMatterPropertyModel[1];
		matterProperty[0] = matterPropertyModel;
		property.updateMatterProperty(matterProperty);
	}

	/**
	 * 案件プロパティ情報を削除するメソッド。
	 *
	 * @param userDataId        案件プロパティを削除する対象のユーザーデータID
	 * @param matterPropertyKey 案件プロパティのプロパティキー名
	 * @throws WorkflowException 案件プロパティ削除処理中に例外が発生した場合
	 */
	@SuppressWarnings("unused")
	private final void deleteMatterProperty(final String userDataId, final String matterPropertyKey)
			throws WorkflowException {

		// プロパティ情報を設定する
		final UserMatterPropertyModel matterPropertyModel = new UserMatterPropertyModel();
		matterPropertyModel.setUserDataId(userDataId);
		matterPropertyModel.setMatterPropertyKey(matterPropertyKey);

		// プロパティ情報を削除する
		UserActvMatterPropertyValue property;
		property = new UserActvMatterPropertyValue();
		final UserMatterPropertyModel[] matterProperty = new UserMatterPropertyModel[1];
		matterProperty[0] = matterPropertyModel;
		property.deleteMatterProperty(matterProperty);
	}

	/**
	 * ワークフローのパラメータと画面入力値から一時保存モデルを構築するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @return 一時保存用モデル（BookHeaderInfoTempModel）
	 */
	private BookHeaderInfoTempModel getEntity_HeaderInfoTemp(ActionProcessParameter parameter,
			Map<String, Object> userParameter) {
		BookHeaderInfoTempModel entity = new BookHeaderInfoTempModel();

		// ワークフローのパラメータ (parameter)の値を取得する
		entity.setUser_data_id(parameter.getUserDataId());
		entity.setSystem_matter_id(parameter.getSystemMatterId());

		// 画面入力値（userParameter）の値を取得する
		entity.setBook_name(getEntity_TryCatch_UserParameter(userParameter, BookForm.FIELD_BOOK_NAME));
		String priceStr = getEntity_TryCatch_UserParameter(userParameter, BookForm.FIELD_PRICE);
		entity.setPrice(parseInteger(priceStr)); // 金額を数値に変換する
		entity.setReason(getEntity_TryCatch_UserParameter(userParameter, BookForm.FIELD_REASON));

		return entity;

	}

	/**
	 * ワークフローのパラメータと画面入力値からヘッダーモデルを構築するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @return ヘッダーモデル（BookHeaderModel）
	 */
	private BookHeaderModel getEntity_Header(ActionProcessParameter parameter, Map<String, Object> userParameter) {
		BookHeaderModel entity = new BookHeaderModel();

		entity.setUser_data_id(parameter.getUserDataId());
		entity.setSystem_matter_id(parameter.getSystemMatterId());
		entity.setStatus(WorkflowStatus.APPLIED.getCode());

		return entity;
	}

	/**
	 * 画面入力値から指定されたキーに対応する文字列を取得するメソッド。
	 * 例外発生時は空文字を返す。
	 *
	 * @param userParameter 画面入力値のマップ
	 * @param input_form    取得対象のキー名
	 * @return 取得した文字列（例外時は空文字）
	 */
	private String getEntity_TryCatch_UserParameter(final Map<String, Object> userParameter, String input_form) {
		try {
			return userParameter.get(input_form).toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 文字列を数値（int型）に変換するメソッド。
	 * 変換に失敗した場合や空文字の場合は0を返す。
	 *
	 * @param strVal 変換対象の文字列
	 * @return 変換後の数値（例外時は0）
	 */
	private int parseInteger(String strVal) {
		if (strVal == null || strVal.trim().isEmpty()) {
			return 0;
		}
		try {
			return Integer.parseInt(strVal.trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * 一時保存からの申請時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @return ユーザデータID
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public String applyFromTempSave(ActionProcessParameter parameter, Map<String, Object> userParameter)
			throws Exception {
		return null;
	}

	/**
	 * 未申請状態案件からの申請時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @return ユーザデータID
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public String applyFromUnapply(ActionProcessParameter parameter, Map<String, Object> userParameter)
			throws Exception {
		return null;
	}

	/**
	 * 承認時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void approve(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	/**
	 * 承認終了時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void approveEnd(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	/**
	 * 否認時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void deny(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	/**
	 * 取止め時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void discontinue(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	/**
	 * 案件操作時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void matterHandle(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	/**
	 * 引き戻し時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void pullBack(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	/**
	 * 再申請時の処理を行うメソッド。
	 * ヘッダーモデルおよび一時保存モデルを取得し、DBの更新処理を実行する。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @return 処理結果（通常はnull）
	 * @throws Exception 更新処理中に例外が発生した場合。
	 */
	@Override
	public String reapply(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {
		return null;
	}

	/**
	 * 保留時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void reserve(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	/**
	 * 保留解除時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void reserveCancel(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	/**
	 * 差し戻し時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void sendBack(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	/**
	 * 差し戻し後引き戻し時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void sendBackToPullBack(ActionProcessParameter parameter, Map<String, Object> userParameter)
			throws Exception {

	}

	/**
	 * 一時保存新規登録時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void tempSaveCreate(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	/**
	 * 一時保存削除時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void tempSaveDelete(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	/**
	 * 一時保存更新時の処理を実行するメソッド。
	 *
	 * @param parameter     ワークフローのパラメータ
	 * @param userParameter 画面入力値（ユーザパラメータ）
	 * @throws Exception 処理実行中に例外が発生した場合
	 */
	@Override
	public void tempSaveUpdate(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

}
