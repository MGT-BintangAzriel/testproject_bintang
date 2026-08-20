package wf.practice4_bintang.general.constant;

/**
 * PC購入申請のデータベースカラム名を定義する定数クラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public final class PcDbConstants {

	// インスタンス化を防ぐためのプライベートコンストラクタ。
	private PcDbConstants() {
	}

	// DBカラム名：申請日
	public static final String COLUMN_APPLY_DATE = "apply_date";

	// DBカラム名：申請者
	public static final String COLUMN_APPLICANT = "applicant";

	// DBカラム名：部署
	public static final String COLUMN_DEPARTMENT = "department";

	// DBカラム名：PC用途
	public static final String COLUMN_PC_USE_TYPE = "pc_use_type";

	// DBカラム名：その他用途
	public static final String COLUMN_PC_USE_OTHER = "pc_use_other";

	// DBカラム名：メーカー
	public static final String COLUMN_MANUFACTURER = "manufacturer";

	// DBカラム名：型番
	public static final String COLUMN_MODEL_NUMBER = "model_number";

	// DBカラム名：数量
	public static final String COLUMN_QUANTITY = "quantity";

	// DBカラム名：単価
	public static final String COLUMN_UNIT_PRICE = "unit_price";

	// DBカラム名：備考
	public static final String COLUMN_REMARKS = "remarks";
}
