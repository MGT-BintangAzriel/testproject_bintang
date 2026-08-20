package wf.path_test.general.domain.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import wf.path_test.general.domain.model.*;
import javax.naming.NamingException;
import jp.co.intra_mart.foundation.database.SearchCondition;
import jp.co.intra_mart.foundation.exception.BizApiException;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;

public class DetailTableTempRepository {

    private String tableName = "wf_detail_tableinfo_temp";
    private String selectDataAll = "SELECT * FROM wf_detail_tableinfo_temp";
    private String selectDataBy_MatterId = "SELECT * FROM wf_detail_tableinfo_temp where system_matter_id = ?";

    public Collection<DetailTableModel> selectDataTable(String select_value, String select_where) throws Exception {
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

            Collection<DetailTableModel> result = sqlManager.select(DetailTableModel.class, select_query,
                    parameters);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error in selectDataTable", e);
        }
    }

    public void insertDataHeader(DetailTableModel varDataDetail) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();

            new ColumnValues();
            ColumnValues columnVal = this.setDataValue(varDataDetail, "create");

            sqlManager.insert(tableName, columnVal);

        } catch (AccessSecurityException | IllegalArgumentException | NamingException | BizApiException
                | SQLException var4) {
            var4.printStackTrace();
            throw new Exception("DB Error in InsertDataHeader");
        }
    }

    public void updateDataHeader(DetailTableModel varDataDetail) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();

            new ColumnValues();
            SearchCondition searchCondition = new SearchCondition();
            searchCondition.addCondition("system_matter_id", varDataDetail.getSystem_matter_id());

            ColumnValues columnVal = this.setDataValue(varDataDetail, "update");
            sqlManager.update(tableName, columnVal, searchCondition);

        } catch (AccessSecurityException | IllegalArgumentException | NamingException | BizApiException
                | SQLException var4) {
            var4.printStackTrace();
            throw new Exception("DB Error in UpdateDataHeader");
        }
    }

    private ColumnValues setDataValue(DetailTableModel varDataDetail, String Condition) {
        ColumnValues result = new ColumnValues();
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        if (Condition.equals("create")) {

            try {

                String sendDate = varDataDetail.getSend_date();
                Date datetime;

                if (sendDate.contains("-")) {
                    // Format: yyyy-MM-dd
                    datetime = new SimpleDateFormat("yyyy-MM-dd").parse(sendDate);
                } else if (sendDate.contains("/")) {
                    // Format: yyyy/MM/dd
                    datetime = new SimpleDateFormat("yyyy/MM/dd").parse(sendDate);
                } else {
                    throw new IllegalArgumentException("Unknown date format: " + sendDate);
                }

                result.add("system_matter_id", varDataDetail.getSystem_matter_id());
                result.add("user_data_id", varDataDetail.getUser_data_id());

                result.add("seq_number", varDataDetail.getSeq_number());
                result.add("item_name", varDataDetail.getItem_name());
                result.add("quantity", varDataDetail.getQuantity());
                result.add("vendor", varDataDetail.getVendor());
                result.add("send_date", datetime);

                result.add("created_at", timestamp);
                result.add("updated_at", timestamp);

            } catch (Exception e) {
                System.out.println("Error parsing date table data");
            }

        } else if (Condition.equals("update")) {

            try {
                Date datetime = new SimpleDateFormat("yyyy/MM/dd").parse(varDataDetail.getSend_date());

                result.add("system_matter_id", varDataDetail.getSystem_matter_id());
                result.add("user_data_id", varDataDetail.getUser_data_id());

                result.add("seq_number", varDataDetail.getSeq_number());
                result.add("item_name", varDataDetail.getItem_name());
                result.add("quantity", varDataDetail.getQuantity());
                result.add("vendor", varDataDetail.getVendor());
                result.add("send_date", datetime);

                result.add("updated_at", timestamp);

            } catch (Exception e) {
                System.out.println("Error parsing date table data");
            }
        }

        return result;
    }

    public void deleteDataDetailTemp(String filterValue, String condition) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            SearchCondition searchCondition = new SearchCondition();
            searchCondition.addCondition(condition, filterValue);
            sqlManager.delete(tableName, searchCondition);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("DB Error in DeleteDataDetailTemp");
        }
    }

}
