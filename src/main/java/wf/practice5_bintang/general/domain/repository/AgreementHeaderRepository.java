package wf.practice5_bintang.general.domain.repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.database.SearchCondition;
import jp.co.intra_mart.foundation.exception.BizApiException;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import wf.common.constant.MailStatus;
import wf.common.constant.WorkflowCommonConstants;
import wf.common.constant.WorkflowStatus;
import wf.practice5_bintang.general.constant.AgreementDbConstants;
import wf.practice5_bintang.general.domain.model.AgreementHeaderModel;

public class AgreementHeaderRepository {

	private String selectDataAll = "SELECT * FROM " + AgreementDbConstants.TABLE_HEADER;
	private String selectDataByMatterId = "SELECT * FROM " + AgreementDbConstants.TABLE_HEADER + " WHERE system_matter_id = ?";
	private String selectDataMail = "SELECT * FROM " + AgreementDbConstants.TABLE_HEADER 
		+ " WHERE status = '" + WorkflowStatus.COMPLETED.getCode() + "'"
		+ " AND mail_status IN ('" + MailStatus.UNSENT.getCode() + "', '" + MailStatus.FAILED.getCode() + "')";


	public void insertHeader(AgreementHeaderModel model) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();

			ColumnValues columnVal = this.buildColumnValues(model, WorkflowCommonConstants.CONDITION_CREATE);

			sqlManager.insert(AgreementDbConstants.TABLE_HEADER, columnVal);

		} catch (AccessSecurityException | IllegalArgumentException | NamingException var4) {
			var4.printStackTrace();
			throw new Exception("DB Error in InsertDataHeader");
		}

	}

	private ColumnValues buildColumnValues(AgreementHeaderModel model, String condition) {
		ColumnValues result = new ColumnValues();

		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);

		if (condition.equals(WorkflowCommonConstants.CONDITION_CREATE)) {
			result.add(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, model.getSystem_matter_id());
			result.add(WorkflowCommonConstants.COLUMN_USER_DATA_ID, model.getUser_data_id());
			result.add(WorkflowCommonConstants.COLUMN_STATUS, model.getStatus());
			result.add(WorkflowCommonConstants.COLUMN_MAIL_STATUS, model.getMail_status());
			result.add(WorkflowCommonConstants.COLUMN_CREATED_AT, timestamp);
			result.add(WorkflowCommonConstants.COLUMN_UPDATED_AT, timestamp);

		} else if (condition.equals(WorkflowCommonConstants.CONDITION_UPDATE)) {
			result.add(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, model.getSystem_matter_id());
			result.add(WorkflowCommonConstants.COLUMN_USER_DATA_ID, model.getUser_data_id());
			result.add(WorkflowCommonConstants.COLUMN_STATUS, model.getStatus());
			result.add(WorkflowCommonConstants.COLUMN_MAIL_STATUS, model.getMail_status());
			result.add(WorkflowCommonConstants.COLUMN_UPDATED_AT, timestamp);
		}

		return result;
	}

	public Collection<AgreementHeaderModel> selectHeader(String selectValue, String selectWhere) throws Exception {
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

			Collection<AgreementHeaderModel> result = sqlManager.select(AgreementHeaderModel.class, selectQuery, parameters);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in selectHeader", e);
		}
	}

	public void updateHeader(AgreementHeaderModel model) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();

			SearchCondition searchCondition = new SearchCondition();
			searchCondition.addCondition(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, model.getSystem_matter_id());

			ColumnValues columnVal = this.buildColumnValues(model, WorkflowCommonConstants.CONDITION_UPDATE);

			sqlManager.update(AgreementDbConstants.TABLE_HEADER, columnVal, searchCondition);

		} catch (AccessSecurityException | IllegalArgumentException | NamingException | BizApiException | SQLException var6) {
			var6.printStackTrace();
			throw new Exception("DB Error in UpdateHeader");

		}

	}

	public AgreementHeaderModel selectAgreementHeaderMaxId() throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();
			String sql = "SELECT " + "max(id) as id " + "FROM " + AgreementDbConstants.TABLE_HEADER;

			Collection<Object> parameters = new ArrayList<Object>();

			List<AgreementHeaderModel> sqlResults = new ArrayList<AgreementHeaderModel>();
			sqlResults = (List<AgreementHeaderModel>) sqlManager.select(AgreementHeaderModel.class, sql, parameters);
			return sqlResults.get(0) != null ? sqlResults.get(0) : new AgreementHeaderModel();

		} catch (SQLException | AccessSecurityException | IllegalArgumentException | InstantiationException | IllegalAccessException | InvocationTargetException | NamingException e) {
			e.printStackTrace();
			throw new Exception("DB error in selectPersonalInfoChangeHeader_MaxId()", e);
		}
	}

	public Collection<AgreementHeaderModel> selectPendingMailHeader() throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();
			Collection<Object> parameters = new ArrayList<Object>();

			Collection<AgreementHeaderModel> result = sqlManager.select(AgreementHeaderModel.class, selectDataMail, parameters);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in selectPendingMailHeader", e);
		}
	}

}
