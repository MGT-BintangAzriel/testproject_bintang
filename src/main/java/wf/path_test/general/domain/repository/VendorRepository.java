package wf.path_test.general.domain.repository;

import java.util.ArrayList;
import java.util.Collection;

import jp.co.intra_mart.foundation.database.SQLManager;
import wf.path_test.general.domain.model.VendorModel;

public class VendorRepository {

	@SuppressWarnings("unused")
	private String tableName = "wf_vendor";
	private String selectDataAll = "SELECT * FROM wf_vendor";
	private String selectDataById = "SELECT * FROM wf_vendor where id = ?";

	public Collection<VendorModel> selectDataVendor(String select_value, String select_where) throws Exception {
		try {
			SQLManager sqlManager = new SQLManager();
			Collection<Object> parameters = new ArrayList<>();

			String select_query = "";

			if (select_where.equals("id")) {
				select_query = this.selectDataById;
				parameters.add(select_value);
			} else {
				select_query = this.selectDataAll;
			}

			Collection<VendorModel> result = sqlManager.select(VendorModel.class, select_query, parameters);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in selectDataVendor", e);
		}
	}

}
