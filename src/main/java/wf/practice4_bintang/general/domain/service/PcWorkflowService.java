package wf.practice4_bintang.general.domain.service;

import java.util.Collection;

import wf.practice4_bintang.general.app.PcForm;
import wf.practice4_bintang.general.domain.model.PcHeaderInfoModel;
import wf.practice4_bintang.general.domain.model.PcHeaderInfoTempModel;
import wf.practice4_bintang.general.domain.repository.PcHeaderInfoTempRepository;

/**
 * PC購入申請のワークフロー共通処理クラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class PcWorkflowService {

	/**
	 * PC購入申請一時保存情報を取得するメソッド。
	 * 取得したデータをフォームクラスオブジェクトにマッピングして返す。
	 *
	 * @param select_value 検索キーに対応する値
	 * @param select_where 検索キー名（例: "system_matter_id"）
	 * @return 一時保存情報が設定されたフォームクラスオブジェクト
	 * @throws Exception データ取得中に例外が発生した場合
	 */
	public PcForm getInfoTemp(String select_value, String select_where) throws Exception {

		PcHeaderInfoTempRepository PcInfoTempHeaderDB = new PcHeaderInfoTempRepository();

		// 一時保存テーブルからデータを検索する
		Collection<PcHeaderInfoTempModel> rows_headerInfo = PcInfoTempHeaderDB
				.selectDataInfoTempHeader(select_value, select_where);

		return setInfoTempForm(rows_headerInfo);
	}

	/**
	 * 一時保存テーブルから取得した情報をフォームに設定するメソッド。
	 *
	 * @param rows_headerInfo データベースから取得した一時保存情報モデルのコレクション
	 * @return 一時保存情報が設定されたフォームクラスオブジェクト
	 */
	private PcForm setInfoTempForm(Collection<PcHeaderInfoTempModel> rows_headerInfo) {
		// フォームオブジェクトをインスタンス化する
		PcForm result = new PcForm();

		// 一時保存モデルを取得する
		PcHeaderInfoTempModel InfoTempHeaderRows = rows_headerInfo.iterator().next();

		try {
			// 取得したモデルのデータをフォームに設定する
			result.setF_system_matter_id(InfoTempHeaderRows.getSystem_matter_id());
			result.setF_user_data_id(InfoTempHeaderRows.getUser_data_id());
			result.setF_applicant(InfoTempHeaderRows.getApplicant());
			result.setF_apply_date(InfoTempHeaderRows.getApply_date());
			result.setF_department(InfoTempHeaderRows.getDepartment());
			result.setF_pc_use_type(InfoTempHeaderRows.getPc_use_type());
			result.setF_pc_use_other(InfoTempHeaderRows.getPc_use_other());
			result.setF_manufacturer(InfoTempHeaderRows.getManufacturer());
			result.setF_model_number(InfoTempHeaderRows.getModel_number());
			result.setF_quantity(String.valueOf(InfoTempHeaderRows.getQuantity()));
			result.setF_unit_price(String.valueOf(InfoTempHeaderRows.getUnit_price()));
			result.setF_remarks(InfoTempHeaderRows.getRemarks());
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
	public PcHeaderInfoModel Move_DataTemp_to_InfoHeader(
			Collection<PcHeaderInfoTempModel> rows_temp_header) {

		// 一時保存モデルを取得する
		PcHeaderInfoTempModel tempHeaderRows = rows_temp_header.iterator().next();

		// 本保存用モデルをインスタンス化する
		PcHeaderInfoModel result = new PcHeaderInfoModel();

		// 一時保存データを本保存用モデルに設定する
		result.setSystem_matter_id(tempHeaderRows.getSystem_matter_id());
		result.setUser_data_id(tempHeaderRows.getUser_data_id());
		result.setApplicant(tempHeaderRows.getApplicant());
		result.setApply_date(tempHeaderRows.getApply_date());
		result.setDepartment(tempHeaderRows.getDepartment());
		result.setPc_use_type(tempHeaderRows.getPc_use_type());
		result.setPc_use_other(tempHeaderRows.getPc_use_other());
		result.setManufacturer(tempHeaderRows.getManufacturer());
		result.setModel_number(tempHeaderRows.getModel_number());
		result.setQuantity(tempHeaderRows.getQuantity());
		result.setUnit_price(tempHeaderRows.getUnit_price());
		result.setRemarks(tempHeaderRows.getRemarks());

		return result;
	}

}
