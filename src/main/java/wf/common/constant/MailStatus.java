package wf.common.constant;

/**
 * メール送信ステータスを定義する列挙型。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public enum MailStatus {

    // 初期状態
    INITIAL("0"),

    // 送信待ち
    UNSENT("1"),

    // 送信成功
    SENT("2"),

    // 送信エラー
    FAILED("99");

    private final String code;

    /**
     * コンストラクタ。
     *
     * @param code ステータスコード
     */
    MailStatus(final String code) {
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
