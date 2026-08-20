package wf.practice4_bintang.general.domain.model;

/**
 * PC購入申請情報を保存するモデルクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class PcHeaderInfoModel {

	// ID
	private int id = 0;
	// システム案件ID
	private String system_matter_id = "";
	// ユーザデータID
	private String user_data_id = "";
	// 登録日時
	private String created_at = "";
	// 更新日時
	private String updated_at = "";
	// 申請日
	private String apply_date = "";
	// 申請者
	private String applicant = "";
	// 部署
	private String department = "";
	// 用途
	private String pc_use_type = "";
	// その他用途
	private String pc_use_other = "";
	// メーカー
	private String manufacturer = "";
	// 型番
	private String model_number = "";
	// 数量
	private int quantity = 0;
	// 単価
	private int unit_price = 0;
	// 備考
	private String remarks = "";

	/**
	 * IDを取得する。
	 *
	 * @return ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * IDを設定する。
	 *
	 * @param id ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * システム案件IDを取得する。
	 *
	 * @return システム案件ID
	 */
	public String getSystem_matter_id() {
		return system_matter_id;
	}

	/**
	 * システム案件IDを設定する。
	 *
	 * @param system_matter_id システム案件ID
	 */
	public void setSystem_matter_id(String system_matter_id) {
		this.system_matter_id = system_matter_id;
	}

	/**
	 * ユーザデータIDを取得する。
	 *
	 * @return ユーザデータID
	 */
	public String getUser_data_id() {
		return user_data_id;
	}

	/**
	 * ユーザデータIDを設定する。
	 *
	 * @param user_data_id ユーザデータID
	 */
	public void setUser_data_id(String user_data_id) {
		this.user_data_id = user_data_id;
	}

	/**
	 * 登録日時を取得する。
	 *
	 * @return 登録日時
	 */
	public String getCreated_at() {
		return created_at;
	}

	/**
	 * 登録日時を設定する。
	 *
	 * @param created_at 登録日時
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	/**
	 * 更新日時を取得する。
	 *
	 * @return 更新日時
	 */
	public String getUpdated_at() {
		return updated_at;
	}

	/**
	 * 更新日時を設定する。
	 *
	 * @param updated_at 更新日時
	 */
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	/**
	 * 申請日を取得する。
	 *
	 * @return 申請日
	 */
	public String getApply_date() {
		return apply_date;
	}

	/**
	 * 申請日を設定する。
	 *
	 * @param apply_date 申請日
	 */
	public void setApply_date(String apply_date) {
		this.apply_date = apply_date;
	}

	/**
	 * 申請者を取得する。
	 *
	 * @return 申請者
	 */
	public String getApplicant() {
		return applicant;
	}

	/**
	 * 申請者を設定する。
	 *
	 * @param applicant 申請者
	 */
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	/**
	 * 部署を取得する。
	 *
	 * @return 部署
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * 部署を設定する。
	 *
	 * @param department 部署
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * 用途を取得する。
	 *
	 * @return 用途
	 */
	public String getPc_use_type() {
		return pc_use_type;
	}

	/**
	 * 用途を設定する。
	 *
	 * @param pc_use_type 用途
	 */
	public void setPc_use_type(String pc_use_type) {
		this.pc_use_type = pc_use_type;
	}

	/**
	 * その他用途を取得する。
	 *
	 * @return その他用途
	 */
	public String getPc_use_other() {
		return pc_use_other;
	}

	/**
	 * その他用途を設定する。
	 *
	 * @param pc_use_other その他用途
	 */
	public void setPc_use_other(String pc_use_other) {
		this.pc_use_other = pc_use_other;
	}

	/**
	 * メーカーを取得する。
	 *
	 * @return メーカー
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * メーカーを設定する。
	 *
	 * @param manufacturer メーカー
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * 型番を取得する。
	 *
	 * @return 型番
	 */
	public String getModel_number() {
		return model_number;
	}

	/**
	 * 型番を設定する。
	 *
	 * @param model_number 型番
	 */
	public void setModel_number(String model_number) {
		this.model_number = model_number;
	}

	/**
	 * 数量を取得する。
	 *
	 * @return 数量
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 数量を設定する。
	 *
	 * @param quantity 数量
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 単価を取得する。
	 *
	 * @return 単価
	 */
	public int getUnit_price() {
		return unit_price;
	}

	/**
	 * 単価を設定する。
	 *
	 * @param unit_price 単価
	 */
	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}

	/**
	 * 備考を取得する。
	 *
	 * @return 備考
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 備考を設定する。
	 *
	 * @param remarks 備考
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
