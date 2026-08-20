package wf.practice4_bintang.general.domain.model;

/**
 * PC購入申請のヘッダー情報を保存するモデルクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class PcHeaderModel {

	// ID
	private int id = 0;
	// システム案件ID
	private String system_matter_id = "";
	// ユーザデータID
	private String user_data_id = "";
	// 案件ステータス
	private String status = "";
	// 登録日時
	private String created_at = "";
	// 更新日時
	private String updated_at = "";
	// メール送信ステータス
	private String mail_status = "";

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
	 * ステータスを取得する。
	 *
	 * @return ステータス
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * ステータスを設定する。
	 *
	 * @param status ステータス
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * メールステータスを取得する。
	 *
	 * @return メールステータス
	 */
	public String getMail_status() {
		return mail_status;
	}

	/**
	 * メールステータスを設定する。
	 *
	 * @param mail_status メールステータス
	 */
	public void setMail_status(String mail_status) {
		this.mail_status = mail_status;
	}

}
