package wf.path_test.general.domain.repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;
import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.database.SearchCondition;
import jp.co.intra_mart.foundation.exception.BizApiException;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import wf.path_test.general.domain.model.*;

public class HeaderRepository {

	private String tableName = "wf_header";
	private String selectDataAll = "SELECT * FROM wf_header";
	private String selectDataBy_MatterId = "SELECT * FROM wf_header WHERE system_matter_id = ?";
	private String selectDataMail = "SELECT * FROM wf_header WHERE status = '2' and mail_status = '1'";

	public void insertDataHeader(HeaderModel varDataHeaderData) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();

			new ColumnValues();
			ColumnValues columnVal = this.setDataHeaderValue(varDataHeaderData, "create");

			sqlManager.insert(tableName, columnVal);

		} catch (AccessSecurityException | IllegalArgumentException | NamingException var4) {
			var4.printStackTrace();
			throw new Exception("DB Error in InsertDataHeader");
		}

	}

	public void updateDataHeader(HeaderModel varDataHeaderData) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();

			new ColumnValues();
			SearchCondition searchCondition = new SearchCondition();
			searchCondition.addCondition("system_matter_id", varDataHeaderData.getSystem_matter_id());

			ColumnValues columnVal = this.setDataHeaderValue(varDataHeaderData, "update");
			sqlManager.update(tableName, columnVal, searchCondition);

		} catch (AccessSecurityException | IllegalArgumentException | NamingException | BizApiException
				| SQLException var6) {
			var6.printStackTrace();
			throw new Exception("DB Error in UpdateHeader");

		}

	}

	private ColumnValues setDataHeaderValue(HeaderModel varDataHeaderData, String Condition) {
		ColumnValues result = new ColumnValues();

		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);

		if (Condition.equals("create")) {
			result.add("system_matter_id", varDataHeaderData.getSystem_matter_id());
			result.add("user_data_id", varDataHeaderData.getUser_data_id());
			result.add("status", varDataHeaderData.getStatus());
			result.add("mail_status", varDataHeaderData.getMail_status());
			result.add("created_at", timestamp);
			result.add("updated_at", timestamp);

		} else if (Condition.equals("update")) {
			result.add("system_matter_id", varDataHeaderData.getSystem_matter_id());
			result.add("user_data_id", varDataHeaderData.getUser_data_id());
			result.add("status", varDataHeaderData.getStatus());
			result.add("mail_status", varDataHeaderData.getMail_status());
			result.add("updated_at", timestamp);
		}

		return result;
	}

	public Collection<HeaderModel> selectDataHeader(String select_value, String select_where) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();
			Collection<Object> parameters = new ArrayList<>();

			String select_query = "";

			if (select_where.equals("system_matter_id")) {
				select_query = this.selectDataBy_MatterId;
				parameters.add(select_value);

			} else if (select_where.equals("mail")) {
				select_query = this.selectDataMail;

			} else {
				select_query = this.selectDataAll;
			}

			Collection<HeaderModel> result = sqlManager.select(HeaderModel.class, select_query, parameters);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in selectDataHeader", e);
		}
	}

}
