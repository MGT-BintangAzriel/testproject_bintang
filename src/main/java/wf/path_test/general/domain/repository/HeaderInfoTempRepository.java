package wf.path_test.general.domain.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;
import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.database.SearchCondition;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import java.sql.SQLException;
import jp.co.intra_mart.foundation.exception.BizApiException;
import wf.path_test.general.domain.model.*;

public class HeaderInfoTempRepository {

	private String tableName = "wf_header_info_temp";
	private String selectDataAll = "SELECT * FROM " + tableName;
	private String selectDataBy_MatterId = "SELECT * FROM " + tableName + " WHERE system_matter_id = ?";

	public void insertDataHeader(HeaderInfoTempModel varDataHeaderData) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();

			new ColumnValues();
			ColumnValues columnVal = this.setDataValue(varDataHeaderData, "create");

			sqlManager.insert(tableName, columnVal);

		} catch (AccessSecurityException | IllegalArgumentException | NamingException var4) {
			var4.printStackTrace();
			throw new Exception("DB Error in InsertDataHeaderInfoTemp");
		}

	}

	public Collection<HeaderInfoTempModel> selectDataInfoTempHeader(String select_value, String select_where)
			throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();
			Collection<Object> parameters = new ArrayList<>();

			String select_query = "";

			if (select_where.equals("system_matter_id")) {
				select_query = this.selectDataBy_MatterId;
				parameters.add(select_value);

			} else {
				select_query = this.selectDataAll;
			}

			Collection<HeaderInfoTempModel> result = sqlManager.select(HeaderInfoTempModel.class, select_query,
					parameters);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in selectDataInfoTempHeader", e);
		}
	}

	private ColumnValues setDataValue(HeaderInfoTempModel varDataHeaderData, String Condition) {
		ColumnValues result = new ColumnValues();

		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);

		if (Condition.equals("create")) {
			result.add("system_matter_id", varDataHeaderData.getSystem_matter_id());
			result.add("user_data_id", varDataHeaderData.getUser_data_id());
			result.add("name", varDataHeaderData.getName());
			result.add("age", varDataHeaderData.getAge());
			result.add("note", varDataHeaderData.getNote());
			result.add("created_at", timestamp);
			result.add("updated_at", timestamp);

		} else if (Condition.equals("update")) {
			result.add("system_matter_id", varDataHeaderData.getSystem_matter_id());
			result.add("user_data_id", varDataHeaderData.getUser_data_id());
			result.add("name", varDataHeaderData.getName());
			result.add("age", varDataHeaderData.getAge());
			result.add("note", varDataHeaderData.getNote());
			result.add("updated_at", timestamp);
		}

		return result;
	}

	public void updateDataInfoTempHeader(HeaderInfoTempModel varDataHeaderData) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();

			new ColumnValues();
			SearchCondition searchCondition = new SearchCondition();
			searchCondition.addCondition("system_matter_id", varDataHeaderData.getSystem_matter_id());

			ColumnValues columnVal = this.setDataValue(varDataHeaderData, "update");
			sqlManager.update(tableName, columnVal, searchCondition);

		} catch (AccessSecurityException | IllegalArgumentException | NamingException | BizApiException
				| SQLException var6) {
			var6.printStackTrace();
			throw new Exception("DB Error in UpdateDataHeaderInfoTemp");

		}

	}
}
