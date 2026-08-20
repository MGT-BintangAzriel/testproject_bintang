package wf.practice5_bintang.general.domain.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.database.SearchCondition;
import wf.common.constant.WorkflowCommonConstants;
import wf.practice5_bintang.general.constant.AgreementDbConstants;
import wf.practice5_bintang.general.domain.model.AgreementPaymentDetailModel;
import wf.practice5_bintang.general.domain.util.DbValueUtils;

public class AgreementPaymentDetailRepository {
	
	private String columnTable = "id,"
			+ "system_matter_id,"
			+ "user_data_id,"
			+ "row_no,"
			+ "brand,"
			+ "type,"
			+ "payment_amount,"
			+ "payment_date,"
			+ "category,"
			+ "recurring,"
			+ "paid_by,"
			+ "created_at,"
			+ "updated_at";

	public void insertTempPaymentDetail(AgreementPaymentDetailModel model) throws Exception {
		SQLManager sqlManager = new SQLManager();
		ColumnValues columnValues = buildColumnValues(model, WorkflowCommonConstants.CONDITION_CREATE);
		sqlManager.insert(AgreementDbConstants.TABLE_PAYMENT_DETAILS_TEMP, columnValues);
	}

	public Collection<AgreementPaymentDetailModel> selectTempPaymentDetail(String filterValue, String condition) throws Exception {
		SQLManager sqlMngr = new SQLManager();

		String sql = "SELECT * FROM " + AgreementDbConstants.TABLE_PAYMENT_DETAILS_TEMP + " WHERE "
				+ condition + " = ? ORDER BY row_no ASC";

		Collection<Object> parameters = new ArrayList<Object>();

		if (condition.equals("id")) {
			parameters.add(Integer.parseInt(filterValue));
		} else {
			parameters.add(filterValue);
		}
		Collection<AgreementPaymentDetailModel> result = sqlMngr.select(AgreementPaymentDetailModel.class, sql, parameters);
		return result;
	}

	public void updateTempPaymentDetail(AgreementPaymentDetailModel model, String filterValue, String condition) throws Exception {
		SQLManager sqlManager = new SQLManager();
		SearchCondition search = new SearchCondition();
		search.addCondition(condition, filterValue);

		ColumnValues columnValues = buildColumnValues(model, WorkflowCommonConstants.CONDITION_UPDATE);
		sqlManager.update(AgreementDbConstants.TABLE_PAYMENT_DETAILS_TEMP, columnValues, search);
	}

	public void deleteTempPaymentDetail(String filterValue, String condition) throws Exception {
		SQLManager sqlManager = new SQLManager();
		SearchCondition search = new SearchCondition();
		search.addCondition(condition, filterValue);

		sqlManager.delete(AgreementDbConstants.TABLE_PAYMENT_DETAILS_TEMP, search);
	}

	public void movePaymentDetailFromTempToMain(String systemMatterId) throws Exception {
		SQLManager sqlManager = new SQLManager();

		String sql = "INSERT INTO " + AgreementDbConstants.TABLE_PAYMENT_DETAILS + " (" + columnTable + ") SELECT "
				+ "id, system_matter_id, user_data_id, row_no, brand, type, payment_amount, payment_date, category, recurring, paid_by, "
				+ "CURRENT_TIMESTAMP, CURRENT_TIMESTAMP "
				+ "FROM " + AgreementDbConstants.TABLE_PAYMENT_DETAILS_TEMP + " WHERE system_matter_id = ?";

		Collection<Object> parameters = new ArrayList<Object>();
		parameters.add(systemMatterId);
		sqlManager.insert(sql, parameters);
	}

	private ColumnValues buildColumnValues(AgreementPaymentDetailModel model, String condition) throws Exception {
		ColumnValues result = new ColumnValues();

		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);

		if (condition.equals(WorkflowCommonConstants.CONDITION_CREATE)) {
			result.add(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, model.getSystem_matter_id());
			result.add(WorkflowCommonConstants.COLUMN_USER_DATA_ID, model.getUser_data_id());
			result.add(WorkflowCommonConstants.COLUMN_CREATED_AT, timestamp);
			result.add(WorkflowCommonConstants.COLUMN_UPDATED_AT, timestamp);
	
		} else if (condition.equals(WorkflowCommonConstants.CONDITION_UPDATE)) {
			result.add(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, model.getSystem_matter_id());
			result.add(WorkflowCommonConstants.COLUMN_USER_DATA_ID, model.getUser_data_id());
			result.add(WorkflowCommonConstants.COLUMN_UPDATED_AT, timestamp);
		}
		
		result.add(AgreementDbConstants.COLUMN_ROW_NO, DbValueUtils.parseInteger(model.getRow_no()));
		result.add(AgreementDbConstants.COLUMN_BRAND, model.getBrand());
		result.add(AgreementDbConstants.COLUMN_TYPE, model.getType());
		result.add(AgreementDbConstants.COLUMN_PAYMENT_AMOUNT, DbValueUtils.parseBigDecimal(model.getPayment_amount()));
		result.add(AgreementDbConstants.COLUMN_PAYMENT_DATE, DbValueUtils.parseSqlDate(model.getPayment_date()));
		result.add(AgreementDbConstants.COLUMN_CATEGORY, model.getCategory());
		result.add(AgreementDbConstants.COLUMN_RECURRING, model.getRecurring());
		result.add(AgreementDbConstants.COLUMN_PAID_BY, model.getPaid_by());
		
		return result;
	}
}
