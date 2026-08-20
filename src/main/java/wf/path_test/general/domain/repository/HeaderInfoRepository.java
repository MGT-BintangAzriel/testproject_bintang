package wf.path_test.general.domain.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.naming.NamingException;
import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import wf.path_test.general.domain.model.*;

public class HeaderInfoRepository {

	private String tableName = "wf_header_info";

	public void insertDataHeader(HeaderInfoModel varDataHeaderData) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();

			new ColumnValues();
			ColumnValues columnVal = this.setDataValue(varDataHeaderData, "create");

			sqlManager.insert(tableName, columnVal);

		} catch (AccessSecurityException | IllegalArgumentException | NamingException var4) {
			var4.printStackTrace();
			throw new Exception("DB Error in InsertDataHeaderInfo");
		}

	}

	private ColumnValues setDataValue(HeaderInfoModel varDataHeaderData, String Condition) {
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
}
