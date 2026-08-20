package wf.practice3_bintang.general.domain.model;

/**
 * 経費申請の添付ファイル情報を保存するモデルクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class ExpenseAttachmentModel {

	// ID
	private int id = 0;
	// システム案件ID
	private String system_matter_id = "";
	// ユーザデータID
	private String user_data_id = "";
	// ファイル名
	private String file_name = "";
	// ファイルパス
	private String file_path = "";
	// 物理ファイル名
	private String file_real_name = "";
	// ファイルタイプ
	private String file_type = "";
	// 登録日時
	private String created_at = "";
	// 更新日時
	private String updated_at = "";

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
	 * ファイル名を取得する。
	 *
	 * @return ファイル名
	 */
	public String getFile_name() {
		return file_name;
	}

	/**
	 * ファイル名を設定する。
	 *
	 * @param file_name ファイル名
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	/**
	 * ファイルパスを取得する。
	 *
	 * @return ファイルパス
	 */
	public String getFile_path() {
		return file_path;
	}

	/**
	 * ファイルパスを設定する。
	 *
	 * @param file_path ファイルパス
	 */
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	/**
	 * 物理ファイル名を取得する。
	 *
	 * @return 物理ファイル名
	 */
	public String getFile_real_name() {
		return file_real_name;
	}

	/**
	 * 物理ファイル名を設定する。
	 *
	 * @param file_real_name 物理ファイル名
	 */
	public void setFile_real_name(String file_real_name) {
		this.file_real_name = file_real_name;
	}

	/**
	 * ファイルタイプを取得する。
	 *
	 * @return ファイルタイプ
	 */
	public String getFile_type() {
		return file_type;
	}

	/**
	 * ファイルタイプを設定する。
	 *
	 * @param file_type ファイルタイプ
	 */
	public void setFile_type(String file_type) {
		this.file_type = file_type;
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

}
