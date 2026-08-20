package wf.practice3_bintang.general.domain.repository;

import java.util.ArrayList;
import java.util.Collection;

import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.database.SearchCondition;

import wf.common.constant.WorkflowCommonConstants;
import wf.practice3_bintang.general.constant.ExpenseDbConstants;
import wf.practice3_bintang.general.domain.model.ExpenseAttachmentModel;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 経費申請の添付ファイル情報をDBに登録・取得・更新・削除・移送するリポジトリクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class ExpenseAttachFileRepository {

	// 処理対象の本保存テーブル名
	private String tableName = "wf_expense_attach_file";

	// 処理対象の一時保存テーブル名
	private String tableTempName = "wf_expense_attach_file_temp";

	// テーブルのカラム定義
	private String columnTable = "id,"
			+ "system_matter_id,"
			+ "user_data_id,"
			+ "file_name,"
			+ "file_path,"
			+ "file_real_name,"
			+ "file_type,"
			+ "created_at,"
			+ "updated_at";

	/**
	 * 添付ファイル一時保存情報をDBに登録するメソッド。
	 *
	 * @param tempData 登録対象の添付ファイル情報モデル
	 * @throws Exception 登録処理中に例外が発生した場合
	 */
	public void createTempInfoFile(ExpenseAttachmentModel tempData) throws Exception {
		SQLManager sqlManager = new SQLManager();

		// 登録用データをカラム値に設定する
		ColumnValues columnValues = setTempInfoFile(tempData, WorkflowCommonConstants.CONDITION_CREATE);

		// DBへの登録処理を実行する
		sqlManager.insert(tableTempName, columnValues);
	}

	/**
	 * DBから添付ファイル一時保存情報を取得するメソッド。
	 *
	 * @param filterValue 検索キーに対応する値
	 * @param condition   検索キー名（例: "system_matter_id"）
	 * @return 取得した添付ファイル情報モデルのコレクション
	 * @throws Exception 取得処理中に例外が発生した場合
	 */
	public Collection<ExpenseAttachmentModel> SelectTempInfo(String filterValue, String condition) throws Exception {
		SQLManager sqlMngr = new SQLManager();

		String sql = "SELECT * FROM " + tableTempName + " WHERE "
				+ condition + " = ?";

		Collection<Object> parameters = new ArrayList<Object>();

		// パラメータを設定する
		if (condition.equals("id")) {
			parameters.add(Integer.parseInt(filterValue));
		} else {
			parameters.add(filterValue);
		}
		// DBから添付ファイル情報を取得する
		Collection<ExpenseAttachmentModel> result = sqlMngr.select(ExpenseAttachmentModel.class, sql, parameters);
		return result;
	}

	/**
	 * DBの添付ファイル一時保存情報を更新するメソッド。
	 *
	 * @param tempData    更新対象の添付ファイル情報モデル
	 * @param filterValue 更新条件キーに対応する値
	 * @param condition   更新条件のキー名
	 * @throws Exception 更新処理中に例外が発生した場合
	 */
	public void updateTempFile(ExpenseAttachmentModel tempData, String filterValue, String condition) throws Exception {
		SQLManager sqlManager = new SQLManager();

		// 更新条件を設定する
		SearchCondition search = new SearchCondition();
		search.addCondition(condition, filterValue);

		// 更新用データをカラム値に設定する
		ColumnValues columnValues = setTempInfoFile(tempData, WorkflowCommonConstants.CONDITION_UPDATE);

		// DBへの更新処理を実行する
		sqlManager.update(tableTempName, columnValues, search);
	}

	/**
	 * DBの添付ファイル一時保存情報を削除するメソッド。
	 *
	 * @param filterValue 削除条件キーに対応する値
	 * @param condition   削除条件のキー名
	 * @throws Exception 削除処理中に例外が発生した場合
	 */
	public void deleteTempInfoFile(String filterValue, String condition) throws Exception {
		SQLManager sqlManager = new SQLManager();

		// 削除条件を設定する
		SearchCondition searchCondition = new SearchCondition();
		searchCondition.addCondition(condition, filterValue);

		// DBから添付ファイルを削除する
		sqlManager.delete(tableTempName, searchCondition);
	}

	/**
	 * 一時保存テーブルから本保存テーブルにデータを移送するメソッド。
	 *
	 * @param systemMatterId データを移送する対象のシステム案件ID
	 * @throws Exception 移送処理中に例外が発生した場合
	 */
	public void MoveInfoFile(String systemMatterId) throws Exception {
		SQLManager sqlManager = new SQLManager();

		String sql = "INSERT INTO " + tableName + " (" + columnTable + ") SELECT "
				+ "id, system_matter_id, user_data_id, file_name, file_path, "
				+ "file_real_name, file_type, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP "
				+ "FROM " + tableTempName + " WHERE system_matter_id = ?";

		// パラメータを設定する
		Collection<Object> parameters = new ArrayList<Object>();
		parameters.add(systemMatterId);

		// 一時保存から本保存への移行クエリを実行する
		sqlManager.insert(sql, parameters);
	}

	/**
	 * データをカラム値に設定するメソッド。
	 * 実行条件（新規登録または更新）に応じてColumnValuesを構築する。
	 *
	 * @param tempData  添付ファイル情報モデル
	 * @param Condition 実行条件（"create" または "update"）
	 * @return カラム値が設定されたColumnValuesオブジェクト
	 */
	private ColumnValues setTempInfoFile(ExpenseAttachmentModel tempData, String Condition) throws Exception {
		ColumnValues result = new ColumnValues();

		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);

		// 新規登録データのマッピング
		if (Condition.equals(WorkflowCommonConstants.CONDITION_CREATE)) {
			result.add(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, tempData.getSystem_matter_id());
			result.add(WorkflowCommonConstants.COLUMN_USER_DATA_ID, tempData.getUser_data_id());
			result.add(ExpenseDbConstants.COLUMN_FILE_NAME, tempData.getFile_name());
			result.add(ExpenseDbConstants.COLUMN_FILE_PATH, tempData.getFile_path());
			result.add(ExpenseDbConstants.COLUMN_FILE_REAL_NAME, tempData.getFile_real_name());
			result.add(ExpenseDbConstants.COLUMN_FILE_TYPE, tempData.getFile_type());
			result.add(WorkflowCommonConstants.COLUMN_CREATED_AT, timestamp);
			result.add(WorkflowCommonConstants.COLUMN_UPDATED_AT, timestamp);

			// 更新データのマッピング
		} else if (Condition.equals(WorkflowCommonConstants.CONDITION_UPDATE)) {
			result.add(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, tempData.getSystem_matter_id());
			result.add(WorkflowCommonConstants.COLUMN_USER_DATA_ID, tempData.getUser_data_id());
			result.add(ExpenseDbConstants.COLUMN_FILE_NAME, tempData.getFile_name());
			result.add(ExpenseDbConstants.COLUMN_FILE_PATH, tempData.getFile_path());
			result.add(ExpenseDbConstants.COLUMN_FILE_REAL_NAME, tempData.getFile_real_name());
			result.add(ExpenseDbConstants.COLUMN_FILE_TYPE, tempData.getFile_type());
			result.add(WorkflowCommonConstants.COLUMN_UPDATED_AT, timestamp);
		}

		return result;
	}

}
