package wf.practice1_bintang.general.constant;

/**
 * 備品申請のデータベースカラム名を定義する定数クラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public final class EquipmentDbConstants {

    // インスタンス化を防ぐためのプライベートコンストラクタ。
    private EquipmentDbConstants() {
    }

    // DBカラム名：備品名
    public static final String COLUMN_EQUIPMENT_NAME = "equipment_name";

    // DBカラム名：金額
    public static final String COLUMN_PRICE = "price";

    // DBカラム名：申請理由
    public static final String COLUMN_REASON = "reason";
}
