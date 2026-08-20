package wf.common.constant;

/**
 * ワークフローのステータスコードを定義する列挙型。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public enum WorkflowStatus {

    // 申請中
    APPLIED("1"),

    // 完了
    COMPLETED("2"),

    // 否認
    DENIED("99");

    private final String code;

    /**
     * コンストラクタ。
     *
     * @param code ステータスコード
     */
    WorkflowStatus(final String code) {
        this.code = code;
    }

    /**
     * ステータスコードを取得する。
     *
     * @return ステータスコード
     */
    public String getCode() {
        return this.code;
    }
}
