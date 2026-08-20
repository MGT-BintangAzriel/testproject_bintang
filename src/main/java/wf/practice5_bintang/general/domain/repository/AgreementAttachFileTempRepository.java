package wf.practice5_bintang.general.domain.repository;

import java.util.ArrayList;
import java.util.Collection;

import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.database.SearchCondition;

import wf.common.constant.WorkflowCommonConstants;
import wf.practice5_bintang.general.constant.AgreementDbConstants;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AgreementAttachFileTempRepository {

	public void insertAttachmentTemp(AgreementAttachmentModel model) throws Exception {
		SQLManager sqlManager = new SQLManager();
		ColumnValues columnValues = buildColumnValues(model, WorkflowCommonConstants.CONDITION_CREATE);
		sqlManager.insert(AgreementDbConstants.TABLE_ATTACH_TEMP, columnValues);
	}

	public Collection<AgreementAttachmentModel> selectAttachmentTemp(String filterValue, String condition) throws Exception {
		SQLManager sqlMngr = new SQLManager();

		String sql = "SELECT * FROM " + AgreementDbConstants.TABLE_ATTACH_TEMP + " WHERE " + condition + " = ?";

		Collection<Object> parameters = new ArrayList<Object>();

		if (condition.equals("id")) {
			parameters.add(Integer.parseInt(filterValue));
		} else {
			parameters.add(filterValue);
		}
		Collection<AgreementAttachmentModel> result = sqlMngr.select(AgreementAttachmentModel.class, sql, parameters);
		return result;
	}

	public void updateAttachmentTemp(AgreementAttachmentModel model, String filterValue, String condition) throws Exception {
		SQLManager sqlManager = new SQLManager();
		SearchCondition search = new SearchCondition();
		search.addCondition(condition, filterValue);

		ColumnValues columnValues = buildColumnValues(model, WorkflowCommonConstants.CONDITION_UPDATE);
		sqlManager.update(AgreementDbConstants.TABLE_ATTACH_TEMP, columnValues, search);
	}

	public void deleteAttachmentTemp(String filterValue, String condition) throws Exception {
		SQLManager sqlManager = new SQLManager();

		SearchCondition searchCondition = new SearchCondition();
		searchCondition.addCondition(condition, filterValue);
		sqlManager.delete(AgreementDbConstants.TABLE_ATTACH_TEMP, searchCondition);
	}

	private ColumnValues buildColumnValues(AgreementAttachmentModel model, String condition) throws Exception {
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

		result.add(AgreementDbConstants.COLUMN_FILE_NAME, model.getFile_name());
		result.add(AgreementDbConstants.COLUMN_FILE_PATH, model.getFile_path());
		result.add(AgreementDbConstants.COLUMN_FILE_REAL_NAME, model.getFile_real_name());
		result.add(AgreementDbConstants.COLUMN_FILE_TYPE, model.getFile_type());
		result.add(AgreementDbConstants.COLUMN_FILE_SIZE, model.getFile_size());
		result.add(AgreementDbConstants.COLUMN_FILE_EXTENSION, model.getFile_extension());

		return result;
	}
}
