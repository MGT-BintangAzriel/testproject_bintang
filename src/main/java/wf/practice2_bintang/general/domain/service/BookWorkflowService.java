package wf.practice2_bintang.general.domain.service;

import java.util.Collection;

import wf.practice2_bintang.general.app.BookForm;
import wf.practice2_bintang.general.domain.model.BookHeaderInfoModel;
import wf.practice2_bintang.general.domain.model.BookHeaderInfoTempModel;
import wf.practice2_bintang.general.domain.repository.BookHeaderInfoTempRepository;

/**
 * 書籍申請のワークフロー共通処理クラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class BookWorkflowService {

	/**
	 * 書籍申請一時保存情報を取得するメソッド。
	 * 取得したデータをフォームクラスオブジェクトにマッピングして返す。
	 *
	 * @param select_value 検索キーに対応する値
	 * @param select_where 検索キー名（例: "system_matter_id"）
	 * @return 一時保存情報が設定されたフォームクラスオブジェクト
	 * @throws Exception データ取得中に例外が発生した場合
	 */
	public BookForm getInfoTemp(String select_value, String select_where) throws Exception {

		BookHeaderInfoTempRepository BookInfoTempHeaderDB = new BookHeaderInfoTempRepository();

		// 一時保存テーブルからデータを検索する
		Collection<BookHeaderInfoTempModel> rows_headerInfo = BookInfoTempHeaderDB
				.selectDataInfoTempHeader(select_value, select_where);

		return setInfoTempForm(rows_headerInfo);
	}

	/**
	 * 一時保存テーブルから取得した情報をフォームに設定するメソッド。
	 *
	 * @param rows_headerInfo データベースから取得した一時保存情報モデルのコレクション
	 * @return 一時保存情報が設定されたフォームクラスオブジェクト
	 */
	private BookForm setInfoTempForm(Collection<BookHeaderInfoTempModel> rows_headerInfo) {
		// フォームオブジェクトをインスタンス化する
		BookForm result = new BookForm();

		// 一時保存モデルを取得する
		BookHeaderInfoTempModel InfoTempHeaderRows = rows_headerInfo.iterator().next();

		try {
			// 取得したモデルのデータをフォームに設定する
			result.setF_system_matter_id(InfoTempHeaderRows.getSystem_matter_id());
			result.setF_user_data_id(InfoTempHeaderRows.getUser_data_id());
			result.setF_book_name(InfoTempHeaderRows.getBook_name());
			result.setF_price(String.valueOf(InfoTempHeaderRows.getPrice()));
			result.setF_reason(InfoTempHeaderRows.getReason());
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
	public BookHeaderInfoModel Move_DataTemp_to_InfoHeader(
			Collection<BookHeaderInfoTempModel> rows_temp_header) {

		// 一時保存モデルを取得する
		BookHeaderInfoTempModel tempHeaderRows = rows_temp_header.iterator().next();

		// 本保存用モデルをインスタンス化する
		BookHeaderInfoModel result = new BookHeaderInfoModel();

		// 一時保存データを本保存用モデルに設定する
		result.setSystem_matter_id(tempHeaderRows.getSystem_matter_id());
		result.setUser_data_id(tempHeaderRows.getUser_data_id());
		result.setBook_name(tempHeaderRows.getBook_name());
		result.setPrice(tempHeaderRows.getPrice());
		result.setReason(tempHeaderRows.getReason());

		return result;
	}

}
