package wf.practice5_bintang.general.domain.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;

import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;

import wf.common.constant.WorkflowCommonConstants;
import wf.practice5_bintang.general.constant.AgreementDbConstants;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoModel;
import wf.practice5_bintang.general.domain.util.DbValueUtils;

public class AgreementHeaderInfoRepository {
	
	private String selectDataAll = "SELECT * FROM " + AgreementDbConstants.TABLE_HEADER_INFO;
	private String selectDataByMatterId = "SELECT * FROM " + AgreementDbConstants.TABLE_HEADER_INFO + " WHERE system_matter_id = ?";

	public void insertHeaderInfo(AgreementHeaderInfoModel model) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();
			ColumnValues columnVal = this.buildColumnValues(model, WorkflowCommonConstants.CONDITION_CREATE);
			sqlManager.insert(AgreementDbConstants.TABLE_HEADER_INFO, columnVal);

		} catch (AccessSecurityException | IllegalArgumentException | NamingException var4) {
			var4.printStackTrace();
			throw new Exception("DB Error in InsertDataHeaderInfoTemp");
		}

	}
	
	public Collection<AgreementHeaderInfoModel> selectHeaderInfo(String selectValue, String selectWhere) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();
			Collection<Object> parameters = new ArrayList<>();

			String selectQuery = "";

			if (selectWhere.equals(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID)) {
				selectQuery = this.selectDataByMatterId;
				parameters.add(selectValue);

			} else {
				selectQuery = this.selectDataAll;
			}

			Collection<AgreementHeaderInfoModel> result = sqlManager.select(AgreementHeaderInfoModel.class, selectQuery, parameters);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in selectHeaderInfo", e);
		}
	}

	private ColumnValues buildColumnValues(AgreementHeaderInfoModel model, String condition) {
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

		result.add(AgreementDbConstants.COLUMN_APPLICATION_NUMBER, model.getApplication_number());
		result.add(AgreementDbConstants.COLUMN_APPLICATION_DATE, DbValueUtils.parseSqlDate(model.getApplication_date()));
		result.add(AgreementDbConstants.COLUMN_APPLICANT_NUMBER, model.getApplicant_number());
		result.add(AgreementDbConstants.COLUMN_APPLICANT_DEPARTMENT, model.getApplicant_department());
		result.add(AgreementDbConstants.COLUMN_APPLICANT_NAME, model.getApplicant_name());
		result.add(AgreementDbConstants.COLUMN_APPLICANT_POST, model.getApplicant_post());
		result.add(AgreementDbConstants.COLUMN_MULTIDATA, model.getMultidata());
		result.add(AgreementDbConstants.COLUMN_COUNTER_PARTY, model.getCounter_party());
		result.add(AgreementDbConstants.COLUMN_CURRENCY, model.getCurrency());
		result.add(AgreementDbConstants.COLUMN_TOTAL_AMOUNT, DbValueUtils.parseBigDecimal(model.getTotal_amount()));
		result.add(AgreementDbConstants.COLUMN_AGREEMENT_STATUS, model.getAgreement_status());
		result.add(AgreementDbConstants.COLUMN_TOTAL_DURATION, model.getTotal_duration());
		result.add(AgreementDbConstants.COLUMN_AUTO_EXTENSION, model.getAuto_extension());
		result.add(AgreementDbConstants.COLUMN_PO_REQUIRED, model.getPo_required());
		result.add(AgreementDbConstants.COLUMN_AGREEMENT_TITLE, model.getAgreement_title());
		result.add(AgreementDbConstants.COLUMN_EFFECTIVE_FROM, DbValueUtils.parseSqlDate(model.getEffective_from()));
		result.add(AgreementDbConstants.COLUMN_EFFECTIVE_TO, DbValueUtils.parseSqlDate(model.getEffective_to()));
		result.add(AgreementDbConstants.COLUMN_COMPANY_RELATION, model.getCompany_relation());
		result.add(AgreementDbConstants.COLUMN_ESTIMATED_DELIVERY_FROM, DbValueUtils.parseSqlDate(model.getEstimated_delivery_from()));
		result.add(AgreementDbConstants.COLUMN_ESTIMATED_DELIVERY_TO, DbValueUtils.parseSqlDate(model.getEstimated_delivery_to()));
		result.add(AgreementDbConstants.COLUMN_AGREEMENT_SUMMARY, model.getAgreement_summary());
		result.add(AgreementDbConstants.COLUMN_PURCHASE_CATEGORY, model.getPurchase_category());
		result.add(AgreementDbConstants.COLUMN_START_USING_DATE, DbValueUtils.parseSqlDate(model.getStart_using_date()));
		result.add(AgreementDbConstants.COLUMN_DEPREC_MONTH, model.getDeprec_month());
		result.add(AgreementDbConstants.COLUMN_BUDGET_PL_IMPACT, model.getBudget_pl_impact());
		result.add(AgreementDbConstants.COLUMN_BUDGET_PL_MONTH, model.getBudget_pl_month());
		result.add(AgreementDbConstants.COLUMN_PL_IMPACT, model.getPl_impact());
		result.add(AgreementDbConstants.COLUMN_PL_MONTH, model.getPl_month());
		result.add(AgreementDbConstants.COLUMN_ASSET_NUMBER, model.getAsset_number());
		result.add(AgreementDbConstants.COLUMN_BOOK_VALUE, model.getBook_value());
		result.add(AgreementDbConstants.COLUMN_TOTAL_PAYMENT_AMOUNT, DbValueUtils.parseBigDecimal(model.getTotal_payment_amount()));
		result.add(AgreementDbConstants.COLUMN_AGREEMENT_CLASSIFICATION, model.getAgreement_classification());
		result.add(AgreementDbConstants.COLUMN_PD_SUB_CONDITION, model.getPd_sub_condition());
		result.add(AgreementDbConstants.COLUMN_EC_APPROVAL, model.getEc_approval());
		result.add(AgreementDbConstants.COLUMN_EC_SUB_CONDITION, model.getEc_sub_condition());
		result.add(AgreementDbConstants.COLUMN_PSD_AREA, model.getPsd_area());
		result.add(AgreementDbConstants.COLUMN_PSD_PROCESS, model.getPsd_process());
		result.add(AgreementDbConstants.COLUMN_DIC_REASON, model.getDic_reason());
		result.add(AgreementDbConstants.COLUMN_DD_PROCESS, model.getDd_process());
		result.add(AgreementDbConstants.COLUMN_ANTI_BRIBERY, model.getAnti_bribery());
		result.add(AgreementDbConstants.COLUMN_AUDIT_RIGHTS, model.getAudit_rights());
		result.add(AgreementDbConstants.COLUMN_LEGAL_AGREEMENT_NUMBER, model.getLegal_agreement_number());
		result.add(AgreementDbConstants.COLUMN_LEGAL_AGREEMENT_DATE, DbValueUtils.parseSqlDate(model.getLegal_agreement_date()));

		return result;
	}
}
