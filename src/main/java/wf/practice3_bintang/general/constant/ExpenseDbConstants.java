package wf.practice3_bintang.general.constant;

/**
 * 経費申請・添付ファイルのデータベースカラム名を定義する定数クラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public final class ExpenseDbConstants {

    // インスタンス化を防ぐためのプライベートコンストラクタ。
    private ExpenseDbConstants() {
    }

    // 経費申請のDBカラム名：経費内容
    public static final String COLUMN_EXPENSE_CONTENT = "expense_content";

    // 経費申請のDBカラム名：金額
    public static final String COLUMN_PRICE = "price";

    // 経費申請のDBカラム名：申請理由
    public static final String COLUMN_REASON = "reason";

    // 添付ファイルのDBカラム名：ファイル名
    public static final String COLUMN_FILE_NAME = "file_name";

    // 添付ファイルのDBカラム名：ファイルパス
    public static final String COLUMN_FILE_PATH = "file_path";

    // 添付ファイルのDBカラム名：物理ファイル名
    public static final String COLUMN_FILE_REAL_NAME = "file_real_name";

    // 添付ファイルのDBカラム名：ファイルタイプ
    public static final String COLUMN_FILE_TYPE = "file_type";
}
