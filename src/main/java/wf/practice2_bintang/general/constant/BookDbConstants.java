package wf.practice2_bintang.general.constant;

/**
 * 書籍申請のデータベースカラム名を定義する定数クラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public final class BookDbConstants {

    // インスタンス化を防ぐためのプライベートコンストラクタ。
    private BookDbConstants() {
    }

    // DBカラム名：書籍名
    public static final String COLUMN_BOOK_NAME = "book_name";

    // DBカラム名：金額
    public static final String COLUMN_PRICE = "price";

    // DBカラム名：申請理由
    public static final String COLUMN_REASON = "reason";
}
