package wf.practice3_bintang.general.domain.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.file.SessionScopeStorage;

import wf.practice3_bintang.general.app.ExpenseForm;
import wf.practice3_bintang.general.domain.model.ExpenseAttachmentModel;
import wf.practice3_bintang.general.domain.model.ExpenseHeaderInfoModel;
import wf.practice3_bintang.general.domain.model.ExpenseHeaderInfoTempModel;
import wf.practice3_bintang.general.domain.repository.ExpenseAttachFileRepository;
import wf.practice3_bintang.general.domain.repository.ExpenseHeaderInfoTempRepository;

/**
 * 経費申請のワークフロー共通処理クラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class ExpenseWorkflowService {

	/**
	 * 経費申請一時保存情報を取得するメソッド。
	 * 取得したデータをフォームクラスオブジェクトにマッピングして返す。
	 *
	 * @param select_value 検索キーに対応する値
	 * @param select_where 検索キー名（例: "system_matter_id"）
	 * @return 一時保存情報が設定されたフォームクラスオブジェクト
	 * @throws Exception データ取得中に例外が発生した場合
	 */
	public ExpenseForm getInfoTemp(String select_value, String select_where) throws Exception {

		ExpenseHeaderInfoTempRepository ExpenseInfoTempHeaderDB = new ExpenseHeaderInfoTempRepository();

		// 一時保存テーブルからデータを検索する
		Collection<ExpenseHeaderInfoTempModel> rows_headerInfo = ExpenseInfoTempHeaderDB
				.selectDataInfoTempHeader(select_value, select_where);

		return setInfoTempForm(rows_headerInfo);
	}

	/**
	 * 一時保存テーブルから取得した情報をフォームに設定するメソッド。
	 *
	 * @param rows_headerInfo データベースから取得した一時保存情報モデルのコレクション
	 * @return 一時保存情報が設定されたフォームクラスオブジェクト
	 */
	private ExpenseForm setInfoTempForm(Collection<ExpenseHeaderInfoTempModel> rows_headerInfo) {
		// フォームオブジェクトをインスタンス化する
		ExpenseForm result = new ExpenseForm();

		// 一時保存モデルを取得する
		ExpenseHeaderInfoTempModel InfoTempHeaderRows = rows_headerInfo.iterator().next();

		// 添付ファイルリポジトリのインスタンス化
		ExpenseAttachFileRepository FileInfoTemp = new ExpenseAttachFileRepository();
		List<ExpenseAttachmentModel> entityFileTemp = new ArrayList<ExpenseAttachmentModel>();

		// 取得したモデルのデータをフォームに設定する
		try {
			result.setF_system_matter_id(InfoTempHeaderRows.getSystem_matter_id());
			result.setF_user_data_id(InfoTempHeaderRows.getUser_data_id());
			result.setF_expense_content(InfoTempHeaderRows.getExpense_content());
			result.setF_price(String.valueOf(InfoTempHeaderRows.getPrice()));
			result.setF_reason(InfoTempHeaderRows.getReason());

			String matter_id = InfoTempHeaderRows.getSystem_matter_id();
			entityFileTemp = new ArrayList<ExpenseAttachmentModel>(
					FileInfoTemp.SelectTempInfo(matter_id, "system_matter_id"));

			result.setD_list_attachment(entityFileTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 一時保存データを本保存モデルに変換するメソッド。
	 *
	 * @param rows_temp_header 一時保存情報モデルのコレクション
	 * @return マッピングされた本保存情報モデル
	 */
	public ExpenseHeaderInfoModel Move_DataTemp_to_InfoHeader(
			Collection<ExpenseHeaderInfoTempModel> rows_temp_header) {

		// 一時保存モデルを取得する
		ExpenseHeaderInfoTempModel tempHeaderRows = rows_temp_header.iterator().next();

		// 本保存用モデルをインスタンス化する
		ExpenseHeaderInfoModel result = new ExpenseHeaderInfoModel();

		// 一時保存データを本保存用モデルに設定する
		result.setSystem_matter_id(tempHeaderRows.getSystem_matter_id());
		result.setUser_data_id(tempHeaderRows.getUser_data_id());
		result.setExpense_content(tempHeaderRows.getExpense_content());
		result.setPrice(tempHeaderRows.getPrice());
		result.setReason(tempHeaderRows.getReason());

		return result;
	}

	/**
	 * 添付ファイルをセッション領域から永続的な保存先フォルダに転送するメソッド。
	 *
	 * @param systemMatterId システム案件ID
	 * @param file_real_name 添付ファイルの物理ファイル名
	 * @return 転送処理に成功した場合はtrue
	 */
	public final Boolean AttachmentFileTransfer(String systemMatterId, String file_real_name) {
		PublicStorage createDir = new PublicStorage("practice3_bintang/" + systemMatterId + "/file_attachment");
		PublicStorage createFile = new PublicStorage(
				"practice3_bintang/" + systemMatterId + "/file_attachment/" + file_real_name);
		SessionScopeStorage getOriginalFile = new SessionScopeStorage("file_attachment/" + file_real_name);
		try {
			// 保存先ディレクトリを作成する
			createDir.makeDirectories();

			// ファイルが存在しない場合、セッション領域のデータを取得し、保存する
			if (!createFile.isFile()) {
				createFile.save(org.apache.commons.io.IOUtils.toByteArray(getOriginalFile.open()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
