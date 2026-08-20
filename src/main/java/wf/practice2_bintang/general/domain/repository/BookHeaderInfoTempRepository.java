package wf.practice2_bintang.general.domain.repository;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;

import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.database.SearchCondition;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import jp.co.intra_mart.foundation.exception.BizApiException;

import wf.common.constant.WorkflowCommonConstants;
import wf.practice2_bintang.general.constant.BookDbConstants;
import wf.practice2_bintang.general.domain.model.BookHeaderInfoTempModel;

/**
 * 書籍申請一時保存情報をDBに登録・取得・更新するリポジトリクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class BookHeaderInfoTempRepository {

	// 処理対象テーブル名
	private String tableName = "wf_book_header_info_temp";

	// テーブル全件取得クエリ
	private String selectDataAll = "SELECT * FROM " + tableName;

	// システム案件IDによる取得クエリ
	private String selectDataBy_MatterId = "SELECT * FROM " + tableName + " WHERE system_matter_id = ?";

	/**
	 * 書籍申請一時保存情報をDBに登録するメソッド。
	 *
	 * @param varDataHeaderData 登録対象の一時保存情報モデル
	 * @throws Exception 登録処理中に例外が発生した場合
	 */
	public void insertDataHeader(BookHeaderInfoTempModel varDataHeaderData) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();

			new ColumnValues();

			// 登録用データをカラム値に設定する
			ColumnValues columnVal = this.setDataValue(varDataHeaderData, WorkflowCommonConstants.CONDITION_CREATE);

			// DBへの登録処理を実行する
			sqlManager.insert(tableName, columnVal);

		} catch (AccessSecurityException | IllegalArgumentException | NamingException var4) {
			var4.printStackTrace();
			throw new Exception("DB Error in InsertDataHeaderInfoTemp");
		}

	}

	/**
	 * データをカラム値に設定するメソッド。
	 * 実行条件（新規登録または更新）に応じてColumnValuesを構築する。
	 *
	 * @param varDataHeaderData 一時保存情報モデル
	 * @param Condition         実行条件（"create" または "update"）
	 * @return カラム値が設定されたColumnValuesオブジェクト
	 */
	private ColumnValues setDataValue(BookHeaderInfoTempModel varDataHeaderData, String Condition) {
		ColumnValues result = new ColumnValues();

		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);

		// 新規登録データのマッピング
		if (Condition.equals(WorkflowCommonConstants.CONDITION_CREATE)) {
			result.add(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, varDataHeaderData.getSystem_matter_id());
			result.add(WorkflowCommonConstants.COLUMN_USER_DATA_ID, varDataHeaderData.getUser_data_id());
			result.add(BookDbConstants.COLUMN_BOOK_NAME, varDataHeaderData.getBook_name());
			result.add(BookDbConstants.COLUMN_PRICE, varDataHeaderData.getPrice());
			result.add(BookDbConstants.COLUMN_REASON, varDataHeaderData.getReason());
			result.add(WorkflowCommonConstants.COLUMN_CREATED_AT, timestamp);
			result.add(WorkflowCommonConstants.COLUMN_UPDATED_AT, timestamp);

			// 更新データのマッピング
		} else if (Condition.equals(WorkflowCommonConstants.CONDITION_UPDATE)) {
			result.add(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, varDataHeaderData.getSystem_matter_id());
			result.add(WorkflowCommonConstants.COLUMN_USER_DATA_ID, varDataHeaderData.getUser_data_id());
			result.add(BookDbConstants.COLUMN_BOOK_NAME, varDataHeaderData.getBook_name());
			result.add(BookDbConstants.COLUMN_PRICE, varDataHeaderData.getPrice());
			result.add(BookDbConstants.COLUMN_REASON, varDataHeaderData.getReason());
			result.add(WorkflowCommonConstants.COLUMN_UPDATED_AT, timestamp);
		}

		return result;
	}

	/**
	 * DBから書籍申請一時保存情報を取得するメソッド。
	 *
	 * @param select_value 検索キーに対応する値
	 * @param select_where 検索キー名（例: "system_matter_id"）
	 * @return 取得した一時保存情報モデルのコレクション
	 * @throws Exception 取得処理中に例外が発生した場合
	 */
	public Collection<BookHeaderInfoTempModel> selectDataInfoTempHeader(String select_value, String select_where)
			throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();
			Collection<Object> parameters = new ArrayList<>();

			String select_query = "";

			// システム案件IDで取得する場合のクエリ設定
			if (select_where.equals(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID)) {
				select_query = this.selectDataBy_MatterId;
				parameters.add(select_value);

				// 全件取得する場合のクエリ設定
			} else {
				select_query = this.selectDataAll;
			}

			// DBから書籍申請一時保存情報を取得する
			Collection<BookHeaderInfoTempModel> result = sqlManager.select(BookHeaderInfoTempModel.class,
					select_query,
					parameters);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in selectDataInfoTempHeader", e);
		}
	}

	/**
	 * DBの書籍申請一時保存情報を更新するメソッド。
	 *
	 * @param varDataHeaderData 更新対象の一時保存情報モデル
	 * @throws Exception 更新処理中に例外が発生した場合
	 */
	public void updateDataHeaderInfoTemp(BookHeaderInfoTempModel varDataHeaderData) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();

			new ColumnValues();

			// 更新条件を設定する
			SearchCondition searchCondition = new SearchCondition();
			searchCondition.addCondition(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID,
					varDataHeaderData.getSystem_matter_id());

			// 更新用データをカラム値に設定する
			ColumnValues columnVal = this.setDataValue(varDataHeaderData, WorkflowCommonConstants.CONDITION_UPDATE);

			// DBへの更新処理を実行する
			sqlManager.update(tableName, columnVal, searchCondition);

		} catch (AccessSecurityException | IllegalArgumentException | NamingException | BizApiException
				| SQLException var6) {
			var6.printStackTrace();
			throw new Exception("DB Error in UpdateHeader");

		}

	}
}
