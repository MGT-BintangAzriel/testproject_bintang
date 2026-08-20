package wf.practice1_bintang.general.domain.model;

/**
 * 備品申請の一時保存情報を保存するモデルクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class EquipmentHeaderInfoTempModel {

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

	// 備品名
	private String equipment_name = "";
	// 金額
	private int price = 0;
	// 理由
	private String reason = "";

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
	 * ユーザーデータIDを取得する。
	 *
	 * @return ユーザーデータID
	 */
	public String getUser_data_id() {
		return user_data_id;
	}

	/**
	 * ユーザーデータIDを設定する。
	 *
	 * @param user_data_id ユーザーデータID
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
	 * 備品名を取得する。
	 *
	 * @return 備品名
	 */
	public String getEquipment_name() {
		return equipment_name;
	}

	/**
	 * 備品名を設定する。
	 *
	 * @param equipment_name 備品名
	 */
	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}

	/**
	 * 金額を取得する。
	 *
	 * @return 金額
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 金額を設定する。
	 *
	 * @param price 金額
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * 理由を取得する。
	 *
	 * @return 理由
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * 理由を設定する。
	 *
	 * @param reason 理由
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

}
