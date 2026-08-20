package wf.common.constant;

/**
 * 案件終了ステータスを定義する列挙型。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public enum MatterEndStatus {

    // 案件完了
    MATTER_COMPLETE("mattercomplete"),

    // 否認
    DENY("deny");

    private final String status;

    /**
     * コンストラクタ。
     *
     * @param status 案件終了ステータス
     */
    MatterEndStatus(final String status) {
        this.status = status;
    }

    /**
     * 案件終了ステータスを取得する。
     *
     * @return 案件終了ステータス
     */
    public String getStatus() {
        return this.status;
    }
}
