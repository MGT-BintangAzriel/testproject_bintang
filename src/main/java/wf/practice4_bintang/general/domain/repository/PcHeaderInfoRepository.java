package wf.practice4_bintang.general.domain.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.naming.NamingException;

import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;

import wf.common.constant.WorkflowCommonConstants;
import wf.practice4_bintang.general.constant.PcDbConstants;
import wf.practice4_bintang.general.domain.model.PcHeaderInfoModel;

/**
 * PC購入申請情報をDBに登録・取得するリポジトリクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class PcHeaderInfoRepository {

	// 処理対象テーブル名
	private String tableName = "wf_pc_header_info";

	/**
	 * PC購入申請情報をDBに登録するメソッド。
	 *
	 * @param varDataHeaderData 登録対象の申請情報モデル
	 * @throws Exception 登録処理中に例外が発生した場合
	 */
	public void insertDataHeader(PcHeaderInfoModel varDataHeaderData) throws Exception {
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
	 * 日付文字列（yyyy/MM/dd または yyyy-MM-dd）を java.sql.Date に変換する。
	 *
	 * @param dateStr 日付文字列
	 * @return 変換後の Date オブジェクト（失敗時は null）
	 */
	private Date parseSqlDate(String dateStr) {
		if (dateStr == null || dateStr.trim().isEmpty()) {
			return null;
		}
		try {
			String cleanDate = dateStr.split(" ")[0];
			String normalized = cleanDate.replace('/', '-');
			return Date.valueOf(normalized);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * データをカラム値に設定するメソッド。
	 * 実行条件（新規登録または更新）に応じてColumnValuesを構築する。
	 *
	 * @param varDataHeaderData 申請情報モデル
	 * @param Condition         実行条件（"create" または "update"）
	 * @return カラム値が設定されたColumnValuesオブジェクト
	 */
	private ColumnValues setDataValue(PcHeaderInfoModel varDataHeaderData, String Condition) {
		ColumnValues result = new ColumnValues();

		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		Date applyDate = parseSqlDate(varDataHeaderData.getApply_date());

		// 新規登録データのマッピング
		if (Condition.equals(WorkflowCommonConstants.CONDITION_CREATE)) {
			result.add(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, varDataHeaderData.getSystem_matter_id());
			result.add(WorkflowCommonConstants.COLUMN_USER_DATA_ID, varDataHeaderData.getUser_data_id());
			result.add(PcDbConstants.COLUMN_APPLY_DATE, applyDate);
			result.add(PcDbConstants.COLUMN_APPLICANT, varDataHeaderData.getApplicant());
			result.add(PcDbConstants.COLUMN_DEPARTMENT, varDataHeaderData.getDepartment());
			result.add(PcDbConstants.COLUMN_PC_USE_TYPE, varDataHeaderData.getPc_use_type());
			result.add(PcDbConstants.COLUMN_PC_USE_OTHER, varDataHeaderData.getPc_use_other());
			result.add(PcDbConstants.COLUMN_MANUFACTURER, varDataHeaderData.getManufacturer());
			result.add(PcDbConstants.COLUMN_MODEL_NUMBER, varDataHeaderData.getModel_number());
			result.add(PcDbConstants.COLUMN_QUANTITY, varDataHeaderData.getQuantity());
			result.add(PcDbConstants.COLUMN_UNIT_PRICE, varDataHeaderData.getUnit_price());
			result.add(PcDbConstants.COLUMN_REMARKS, varDataHeaderData.getRemarks());
			result.add(WorkflowCommonConstants.COLUMN_CREATED_AT, timestamp);
			result.add(WorkflowCommonConstants.COLUMN_UPDATED_AT, timestamp);

			// 更新データのマッピング
		} else if (Condition.equals(WorkflowCommonConstants.CONDITION_UPDATE)) {
			result.add(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, varDataHeaderData.getSystem_matter_id());
			result.add(WorkflowCommonConstants.COLUMN_USER_DATA_ID, varDataHeaderData.getUser_data_id());
			result.add(PcDbConstants.COLUMN_APPLY_DATE, applyDate);
			result.add(PcDbConstants.COLUMN_APPLICANT, varDataHeaderData.getApplicant());
			result.add(PcDbConstants.COLUMN_DEPARTMENT, varDataHeaderData.getDepartment());
			result.add(PcDbConstants.COLUMN_PC_USE_TYPE, varDataHeaderData.getPc_use_type());
			result.add(PcDbConstants.COLUMN_PC_USE_OTHER, varDataHeaderData.getPc_use_other());
			result.add(PcDbConstants.COLUMN_MANUFACTURER, varDataHeaderData.getManufacturer());
			result.add(PcDbConstants.COLUMN_MODEL_NUMBER, varDataHeaderData.getModel_number());
			result.add(PcDbConstants.COLUMN_QUANTITY, varDataHeaderData.getQuantity());
			result.add(PcDbConstants.COLUMN_UNIT_PRICE, varDataHeaderData.getUnit_price());
			result.add(PcDbConstants.COLUMN_REMARKS, varDataHeaderData.getRemarks());
			result.add(WorkflowCommonConstants.COLUMN_UPDATED_AT, timestamp);
		}

		return result;
	}

}
