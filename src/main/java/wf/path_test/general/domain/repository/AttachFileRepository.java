package wf.path_test.general.domain.repository;

import java.util.ArrayList;
import java.util.Collection;

import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.database.SearchCondition;
import wf.path_test.general.domain.model.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AttachFileRepository {
	

    private String tableName = "wf_attach_file";
	private String tableTempName = "wf_attach_file_temp";
	private String columnTable = "id,"
			+ "system_matter_id,"
			+ "user_data_id,"
			+ "file_name,"
			+ "file_path,"
			+ "file_real_name,"
			+ "file_type,"
			+ "created_at,"
	        + "updated_at";
	
	
	//Insert
	public void createTempInfoFile(AttachmentModel tempData) throws Exception {
		SQLManager sqlManager = new SQLManager();
		ColumnValues columnValues = setTempInfoFile(tempData, "create");
		sqlManager.insert(tableTempName, columnValues);
	}
	//Select
	public Collection<AttachmentModel> SelectTempInfo (String filterValue, String condition) throws Exception {
		SQLManager sqlMngr = new SQLManager();
		String sql = "SELECT * FROM " + tableTempName + " WHERE "
				+ condition + " = ?";
		Collection<Object> parameters = new ArrayList<Object>();
		if (condition.equals("id")) {
			parameters.add(Integer.parseInt(filterValue));
		}else {
			parameters.add(filterValue);
		}
		Collection<AttachmentModel> result = sqlMngr.select(AttachmentModel.class, sql, parameters);
		return result;
	}
	//Update
	public void updateTempFile (AttachmentModel tempData, String filterValue, String condition) throws Exception {
		SQLManager sqlManager = new SQLManager();
		ColumnValues columnValues = new ColumnValues();
		SearchCondition search = new SearchCondition();
		search.addCondition(condition, filterValue);
		columnValues = setTempInfoFile(tempData, "update");
		sqlManager.update(tableTempName, columnValues, search);
	}
	//Delete
	public void deleteTempInfoFile (String filterValue, String condition) throws Exception {
		SQLManager sqlManager = new SQLManager();
		SearchCondition searchCondition = new SearchCondition();
		searchCondition.addCondition(condition, filterValue);
		sqlManager.delete(tableTempName, searchCondition);
	}
	
	//Move data from Attach Temp Table to Attach Table
	public void MoveInfoFile (String systemMatterId) throws Exception {
		SQLManager sqlManager = new SQLManager();
		String sql = "INSERT INTO " + tableName + " (" + columnTable + ") SELECT "
	            + "id, system_matter_id, user_data_id, file_name, file_path, "
	            + "file_real_name, file_type, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP "
	            + "FROM " + tableTempName + " WHERE system_matter_id = ?";
		Collection<Object> parameters = new ArrayList<Object>();
		parameters.add(systemMatterId);
		sqlManager.insert(sql, parameters);
	}
	
	
	private ColumnValues setTempInfoFile(AttachmentModel tempData, String Condition) throws Exception{
		ColumnValues result = new ColumnValues();
		
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		
		if (Condition.equals("create")) {
			result.add("system_matter_id", tempData.getSystem_matter_id());
			result.add("user_data_id", tempData.getUser_data_id());
			result.add("file_name", tempData.getFile_name());
			result.add("file_path", tempData.getFile_path());
			result.add("file_real_name", tempData.getFile_real_name());
			result.add("file_type", tempData.getFile_type());
			result.add("created_at",timestamp);
			result.add("updated_at",timestamp);
		}else if (Condition.equals("update")) {
			result.add("system_matter_id", tempData.getSystem_matter_id());
			result.add("user_data_id", tempData.getUser_data_id());
			result.add("file_name", tempData.getFile_name());
			result.add("file_path", tempData.getFile_path());
			result.add("file_real_name", tempData.getFile_real_name());
			result.add("file_type", tempData.getFile_type());
			result.add("updated_at",timestamp);
		}
		
		
		return result;
	}


}
