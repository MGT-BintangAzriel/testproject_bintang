package wf.practice2_bintang.general.domain.model;

/**
 * 書籍申請の一時保存情報を保存するモデルクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class BookHeaderInfoTempModel {

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

	// 書籍名
	private String book_name = "";
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
	 * 書籍名を取得する。
	 *
	 * @return 書籍名
	 */
	public String getBook_name() {
		return book_name;
	}

	/**
	 * 書籍名を設定する。
	 *
	 * @param book_name 書籍名
	 */
	public void setBook_name(String book_name) {
		this.book_name = book_name;
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
