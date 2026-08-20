package wf.practice3_bintang.general.app;

import java.util.Collection;

import wf.practice3_bintang.general.domain.model.ExpenseAttachmentModel;

/**
 * 経費申請画面のフォームデータを保持するクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class ExpenseForm extends ExpenseWorkflowForm {

	// 画面入力フォームフィールド名を定数化する
	public static final String FIELD_EXPENSE_CONTENT = "f_expense_content";
	public static final String FIELD_PRICE = "f_price";
	public static final String FIELD_REASON = "f_reason";

	// ID
	private String f_id;
	// システム案件ID
	private String f_system_matter_id;
	// ユーザデータID
	private String f_user_data_id;

	// 領収書
	private Collection<ExpenseAttachmentModel> d_list_attachment;
	// 申請内容
	private String f_expense_content;
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
	 * 申請内容を取得する。
	 *
	 * @return 申請内容
	 */
	public String getF_expense_content() {
		return f_expense_content;
	}

	/**
	 * 申請内容を設定する。
	 *
	 * @param f_expense_content 申請内容
	 */
	public void setF_expense_content(String f_expense_content) {
		this.f_expense_content = f_expense_content;
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

	/**
	 * 領収書を取得する。
	 *
	 * @return 領収書
	 */
	public Collection<ExpenseAttachmentModel> getD_list_attachment() {
		return d_list_attachment;
	}

	/**
	 * 領収書を設定する。
	 *
	 * @param d_list_attachment 領収書
	 */
	public void setD_list_attachment(Collection<ExpenseAttachmentModel> d_list_attachment) {
		this.d_list_attachment = d_list_attachment;
	}

}
