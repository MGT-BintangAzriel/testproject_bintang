package wf.practice1_bintang.general.domain.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.naming.NamingException;

import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;

import wf.common.constant.WorkflowCommonConstants;
import wf.practice1_bintang.general.constant.EquipmentDbConstants;
import wf.practice1_bintang.general.domain.model.EquipmentHeaderInfoModel;

/**
 * 備品申請情報をDBに登録・取得するリポジトリクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class EquipmentHeaderInfoRepository {

	// 処理対象テーブル名
	private String tableName = "wf_equipment_header_info";

	/**
	 * 備品申請情報をDBに登録するメソッド。
	 *
	 * @param varDataHeaderData 登録対象の申請情報モデル
	 * @throws Exception 登録処理中に例外が発生した場合
	 */
	public void insertDataHeader(EquipmentHeaderInfoModel varDataHeaderData) throws Exception {
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
	 * @param varDataHeaderData 申請情報モデル
	 * @param Condition         実行条件（"create" または "update"）
	 * @return カラム値が設定されたColumnValuesオブジェクト
	 */
	private ColumnValues setDataValue(EquipmentHeaderInfoModel varDataHeaderData, String Condition) {
		ColumnValues result = new ColumnValues();

		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);

		// 新規登録データのマッピング
		if (Condition.equals(WorkflowCommonConstants.CONDITION_CREATE)) {
			result.add(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, varDataHeaderData.getSystem_matter_id());
			result.add(WorkflowCommonConstants.COLUMN_USER_DATA_ID, varDataHeaderData.getUser_data_id());
			result.add(EquipmentDbConstants.COLUMN_EQUIPMENT_NAME, varDataHeaderData.getEquipment_name());
			result.add(EquipmentDbConstants.COLUMN_PRICE, varDataHeaderData.getPrice());
			result.add(EquipmentDbConstants.COLUMN_REASON, varDataHeaderData.getReason());
			result.add(WorkflowCommonConstants.COLUMN_CREATED_AT, timestamp);
			result.add(WorkflowCommonConstants.COLUMN_UPDATED_AT, timestamp);

			// 更新データのマッピング
		} else if (Condition.equals(WorkflowCommonConstants.CONDITION_UPDATE)) {
			result.add(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, varDataHeaderData.getSystem_matter_id());
			result.add(WorkflowCommonConstants.COLUMN_USER_DATA_ID, varDataHeaderData.getUser_data_id());
			result.add(EquipmentDbConstants.COLUMN_EQUIPMENT_NAME, varDataHeaderData.getEquipment_name());
			result.add(EquipmentDbConstants.COLUMN_PRICE, varDataHeaderData.getPrice());
			result.add(EquipmentDbConstants.COLUMN_REASON, varDataHeaderData.getReason());
			result.add(WorkflowCommonConstants.COLUMN_UPDATED_AT, timestamp);
		}

		return result;
	}

}
