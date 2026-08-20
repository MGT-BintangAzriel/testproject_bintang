package wf.practice1_bintang.general.domain.service;

import java.util.Collection;

import wf.practice1_bintang.general.app.EquipmentForm;
import wf.practice1_bintang.general.domain.model.EquipmentHeaderInfoModel;
import wf.practice1_bintang.general.domain.model.EquipmentHeaderInfoTempModel;
import wf.practice1_bintang.general.domain.repository.EquipmentHeaderInfoTempRepository;

/**
 * 備品申請のワークフロー共通処理クラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class EquipmentWorkflowService {

	/**
	 * 備品申請一時保存情報を取得するメソッド。
	 * 取得したデータをフォームクラスオブジェクトにマッピングして返す。
	 *
	 * @param select_value 検索キーに対応する値
	 * @param select_where 検索キー名（例: "system_matter_id"）
	 * @return 一時保存情報が設定されたフォームクラスオブジェクト
	 * @throws Exception データ取得中に例外が発生した場合
	 */
	public EquipmentForm getInfoTemp(String select_value, String select_where) throws Exception {

		EquipmentHeaderInfoTempRepository EquipmentInfoTempHeaderDB = new EquipmentHeaderInfoTempRepository();

		// 一時保存テーブルからデータを検索する
		Collection<EquipmentHeaderInfoTempModel> rows_headerInfo = EquipmentInfoTempHeaderDB
				.selectDataInfoTempHeader(select_value, select_where);

		return setInfoTempForm(rows_headerInfo);
	}

	/**
	 * 一時保存テーブルから取得した情報をフォームに設定するメソッド。
	 *
	 * @param rows_headerInfo データベースから取得した一時保存情報モデルのコレクション
	 * @return 一時保存情報が設定されたフォームクラスオブジェクト
	 */
	private EquipmentForm setInfoTempForm(Collection<EquipmentHeaderInfoTempModel> rows_headerInfo) {
		// フォームオブジェクトをインスタンス化する
		EquipmentForm result = new EquipmentForm();

		// 一時保存モデルを取得する
		EquipmentHeaderInfoTempModel InfoTempHeaderRows = rows_headerInfo.iterator().next();

		try {
			// 取得したモデルのデータをフォームに設定する
			result.setF_system_matter_id(InfoTempHeaderRows.getSystem_matter_id());
			result.setF_user_data_id(InfoTempHeaderRows.getUser_data_id());
			result.setF_equipment_name(InfoTempHeaderRows.getEquipment_name());
			result.setF_price(String.valueOf(InfoTempHeaderRows.getPrice()));
			result.setF_reason(InfoTempHeaderRows.getReason());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 一時保存データを本保存モデルに設定するメソッド。
	 *
	 * @param rows_temp_header 一時保存情報モデルのコレクション
	 * @return マッピングされた本保存情報モデル
	 */
	public EquipmentHeaderInfoModel Move_DataTemp_to_InfoHeader(
			Collection<EquipmentHeaderInfoTempModel> rows_temp_header) {

		// 一時保存モデルを取得する
		EquipmentHeaderInfoTempModel tempHeaderRows = rows_temp_header.iterator().next();

		// 本保存用モデルをインスタンス化する
		EquipmentHeaderInfoModel result = new EquipmentHeaderInfoModel();

		// 一時保存データを本保存用モデルに設定する
		result.setSystem_matter_id(tempHeaderRows.getSystem_matter_id());
		result.setUser_data_id(tempHeaderRows.getUser_data_id());
		result.setEquipment_name(tempHeaderRows.getEquipment_name());
		result.setPrice(tempHeaderRows.getPrice());
		result.setReason(tempHeaderRows.getReason());

		return result;
	}

}
