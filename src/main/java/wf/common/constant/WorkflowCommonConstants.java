package wf.common.constant;

/**
 * ワークフロー共通で使用する定数を定義するクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public final class WorkflowCommonConstants {

    // インスタンス化を防ぐためのプライベートコンストラクタ
    private WorkflowCommonConstants() {
    }

    // DBカラム名：システム案件ID
    public static final String COLUMN_SYSTEM_MATTER_ID = "system_matter_id";

    // DBカラム名：ユーザデータID
    public static final String COLUMN_USER_DATA_ID = "user_data_id";

    // DBカラム名：ステータス
    public static final String COLUMN_STATUS = "status";

    // DBカラム名：メール送信ステータス
    public static final String COLUMN_MAIL_STATUS = "mail_status";

    // DBカラム名：登録日時
    public static final String COLUMN_CREATED_AT = "created_at";

    // DBカラム名：更新日時
    public static final String COLUMN_UPDATED_AT = "updated_at";

    // 実行条件：新規登録
    public static final String CONDITION_CREATE = "create";

    // 実行条件：更新
    public static final String CONDITION_UPDATE = "update";

    // HTMLタグ：テーブル開始・終了
    public static final String HTML_TAG_TABLE_START = "<table>";
    public static final String HTML_TAG_TABLE_END = "</table>";

    // HTMLタグ：行（tr）開始・終了
    public static final String HTML_TAG_TR_START = "<tr>";
    public static final String HTML_TAG_TR_END = "</tr>";

    // HTMLタグ：ヘッダーセル（th）開始・終了
    public static final String HTML_TAG_TH_START = "<th>";
    public static final String HTML_TAG_TH_END = "</th>";

    // HTMLタグ：データセル（td）開始・終了
    public static final String HTML_TAG_TD_START = "<td>";
    public static final String HTML_TAG_TD_END = "</td>";
}
