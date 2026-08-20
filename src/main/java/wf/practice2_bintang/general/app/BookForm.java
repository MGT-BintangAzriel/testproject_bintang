package wf.practice2_bintang.general.app;

/**
 * 書籍申請画面のフォームデータを保持するクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class BookForm extends BookWorkflowForm {

	// 画面入力フォームフィールド名を定数化する
	public static final String FIELD_BOOK_NAME = "f_book_name";
	public static final String FIELD_PRICE = "f_price";
	public static final String FIELD_REASON = "f_reason";

	// ID
	private String f_id;
	// システム案件ID
	private String f_system_matter_id;
	// ユーザデータID
	private String f_user_data_id;

	// 書籍名
	private String f_book_name;
	// 金額
	private String f_price;
	// 理由
	private String f_reason;

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
	 * ユーザーデータIDを取得する。
	 *
	 * @return ユーザーデータID
	 */
	public String getF_user_data_id() {
		return f_user_data_id;
	}

	/**
	 * ユーザーデータIDを設定する。
	 *
	 * @param f_user_data_id ユーザーデータID
	 */
	public void setF_user_data_id(String f_user_data_id) {
		this.f_user_data_id = f_user_data_id;
	}

	/**
	 * 書籍名を取得する。
	 *
	 * @return 書籍名
	 */
	public String getF_book_name() {
		return f_book_name;
	}

	/**
	 * 書籍名を設定する。
	 *
	 * @param f_book_name 書籍名
	 */
	public void setF_book_name(String f_book_name) {
		this.f_book_name = f_book_name;
	}

	/**
	 * 金額を取得する。
	 *
	 * @return 金額
	 */
	public String getF_price() {
		return f_price;
	}

	/**
	 * 金額を設定する。
	 *
	 * @param f_price 金額
	 */
	public void setF_price(String f_price) {
		this.f_price = f_price;
	}

	/**
	 * 理由を取得する。
	 *
	 * @return 理由
	 */
	public String getF_reason() {
		return f_reason;
	}

	/**
	 * 理由を設定する。
	 *
	 * @param f_reason 理由
	 */
	public void setF_reason(String f_reason) {
		this.f_reason = f_reason;
	}

}
