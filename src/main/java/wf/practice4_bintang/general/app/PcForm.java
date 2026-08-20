package wf.practice4_bintang.general.app;

/**
 * PC購入申請画面のフォームデータを保持するクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class PcForm extends PcWorkflowForm {

	// 画面入力フォームフィールド名を定数化する
	public static final String FIELD_APPLY_DATE = "f_apply_date";
	public static final String FIELD_APPLICANT = "f_applicant";
	public static final String FIELD_DEPARTMENT = "f_department";
	public static final String FIELD_PC_USE_TYPE = "f_pc_use_type";
	public static final String FIELD_PC_USE_OTHER = "f_pc_use_other";
	public static final String FIELD_MANUFACTURER = "f_manufacturer";
	public static final String FIELD_MODEL_NUMBER = "f_model_number";
	public static final String FIELD_QUANTITY = "f_quantity";
	public static final String FIELD_UNIT_PRICE = "f_unit_price";
	public static final String FIELD_REMARKS = "f_remarks";

	// ID
	private String f_id;
	// システム案件ID
	private String f_system_matter_id;
	// ユーザデータID
	private String f_user_data_id;

	// 申請日
	private String f_apply_date;
	// 申請者
	private String f_applicant;
	// 部署
	private String f_department;
	// 用途
	private String f_pc_use_type;
	// その他用途
	private String f_pc_use_other;
	// メーカー
	private String f_manufacturer;
	// 型番
	private String f_model_number;
	// 数量
	private String f_quantity;
	// 単価
	private String f_unit_price;
	// 備考
	private String f_remarks;

	/**
	 * IDを取得する。
	 *
	 * @return ID
	 */
	public String getF_id() {
		return f_id;
	}

	/**
	 * IDを設定する。
	 *
	 * @param f_id ID
	 */
	public void setF_id(String f_id) {
		this.f_id = f_id;
	}

	/**
	 * システム案件IDを取得する。
	 *
	 * @return システム案件ID
	 */
	public String getF_system_matter_id() {
		return f_system_matter_id;
	}

	/**
	 * システム案件IDを設定する。
	 *
	 * @param f_system_matter_id システム案件ID
	 */
	public void setF_system_matter_id(String f_system_matter_id) {
		this.f_system_matter_id = f_system_matter_id;
	}

	/**
	 * ユーザデータIDを取得する。
	 *
	 * @return ユーザデータID
	 */
	public String getF_user_data_id() {
		return f_user_data_id;
	}

	/**
	 * ユーザデータIDを設定する。
	 *
	 * @param f_user_data_id ユーザデータID
	 */
	public void setF_user_data_id(String f_user_data_id) {
		this.f_user_data_id = f_user_data_id;
	}

	/**
	 * 申請日を取得する。
	 *
	 * @return 申請日
	 */
	public String getF_apply_date() {
		return f_apply_date;
	}

	/**
	 * 申請日を設定する。
	 *
	 * @param f_apply_date 申請日
	 */
	public void setF_apply_date(String f_apply_date) {
		this.f_apply_date = f_apply_date;
	}

	/**
	 * 申請者を取得する。
	 *
	 * @return 申請者
	 */
	public String getF_applicant() {
		return f_applicant;
	}

	/**
	 * 申請者を設定する。
	 *
	 * @param f_applicant 申請者
	 */
	public void setF_applicant(String f_applicant) {
		this.f_applicant = f_applicant;
	}

	/**
	 * 部署を取得する。
	 *
	 * @return 部署
	 */
	public String getF_department() {
		return f_department;
	}

	/**
	 * 部署を設定する。
	 *
	 * @param f_department 部署
	 */
	public void setF_department(String f_department) {
		this.f_department = f_department;
	}

	/**
	 * 用途を取得する。
	 *
	 * @return 用途
	 */
	public String getF_pc_use_type() {
		return f_pc_use_type;
	}

	/**
	 * 用途を設定する。
	 *
	 * @param f_pc_use_type 用途
	 */
	public void setF_pc_use_type(String f_pc_use_type) {
		this.f_pc_use_type = f_pc_use_type;
	}

	/**
	 * その他用途を取得する。
	 *
	 * @return その他用途
	 */
	public String getF_pc_use_other() {
		return f_pc_use_other;
	}

	/**
	 * その他用途を設定する。
	 *
	 * @param f_pc_use_other その他用途
	 */
	public void setF_pc_use_other(String f_pc_use_other) {
		this.f_pc_use_other = f_pc_use_other;
	}

	/**
	 * メーカーを取得する。
	 *
	 * @return メーカー
	 */
	public String getF_manufacturer() {
		return f_manufacturer;
	}

	/**
	 * メーカーを設定する。
	 *
	 * @param f_manufacturer メーカー
	 */
	public void setF_manufacturer(String f_manufacturer) {
		this.f_manufacturer = f_manufacturer;
	}

	/**
	 * 型番を取得する。
	 *
	 * @return 型番
	 */
	public String getF_model_number() {
		return f_model_number;
	}

	/**
	 * 型番を設定する。
	 *
	 * @param f_model_number 型番
	 */
	public void setF_model_number(String f_model_number) {
		this.f_model_number = f_model_number;
	}

	/**
	 * 数量を取得する。
	 *
	 * @return 数量
	 */
	public String getF_quantity() {
		return f_quantity;
	}

	/**
	 * 数量を設定する。
	 *
	 * @param f_quantity 数量
	 */
	public void setF_quantity(String f_quantity) {
		this.f_quantity = f_quantity;
	}

	/**
	 * 単価を取得する。
	 *
	 * @return 単価
	 */
	public String getF_unit_price() {
		return f_unit_price;
	}

	/**
	 * 単価を設定する。
	 *
	 * @param f_unit_price 単価
	 */
	public void setF_unit_price(String f_unit_price) {
		this.f_unit_price = f_unit_price;
	}

	/**
	 * 備考を取得する。
	 *
	 * @return 備考
	 */
	public String getF_remarks() {
		return f_remarks;
	}

	/**
	 * 備考を設定する。
	 *
	 * @param f_remarks 備考
	 */
	public void setF_remarks(String f_remarks) {
		this.f_remarks = f_remarks;
	}

}
