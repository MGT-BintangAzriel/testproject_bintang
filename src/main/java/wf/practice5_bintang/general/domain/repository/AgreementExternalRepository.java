package wf.practice5_bintang.general.domain.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wf.practice5_bintang.general.constant.AgreementDbConstants;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoModel;
import wf.practice5_bintang.general.domain.model.AgreementPaymentDetailModel;

public class AgreementExternalRepository {

	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String mysqlUrl = "jdbc:mysql://localhost:3306/external_procurement_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		String mysqlUser = "root";
		String mysqlPass = "Zuleha210902";
		return DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPass);
	}

	public List<AgreementHeaderInfoModel> findPendingHeaders(Connection conn) throws SQLException {
		List<AgreementHeaderInfoModel> list = new ArrayList<>();
		String sql = "SELECT * FROM ext_agreement_header_info WHERE sync_status = 'PENDING'";

		try (PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(mapHeaderInfo(rs));
			}
		}
		return list;
	}

	public List<AgreementPaymentDetailModel> findPaymentDetails(int extHeaderId, Connection conn) throws SQLException {
		List<AgreementPaymentDetailModel> list = new ArrayList<>();
		String sql = "SELECT * FROM ext_agreement_payment_details WHERE ext_header_id = ? ORDER BY row_no ASC, id ASC";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, extHeaderId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					AgreementPaymentDetailModel model = new AgreementPaymentDetailModel();
					model.setBrand(rs.getString("brand"));
					model.setType(rs.getString("type"));
					model.setPayment_amount(rs.getString("payment_amount"));
					model.setPayment_date(formatDate(rs.getString("payment_date")));
					model.setCategory(rs.getString("category"));
					model.setRecurring(rs.getString("recurring"));
					model.setPaid_by(rs.getString("paid_by"));
					list.add(model);
				}
			}
		}
		return list;
	}

	public List<AgreementAttachmentModel> findAttachments(int extHeaderId, Connection conn) throws SQLException {
		List<AgreementAttachmentModel> list = new ArrayList<>();
		String sql = "SELECT * FROM ext_agreement_attach_file WHERE ext_header_id = ? ORDER BY id ASC";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, extHeaderId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					AgreementAttachmentModel model = new AgreementAttachmentModel();
					model.setFile_name(rs.getString("file_name"));
					model.setFile_path(rs.getString("file_path"));
					model.setFile_type(rs.getString("file_type") != null ? rs.getString("file_type") : "agreement");
					list.add(model);
				}
			}
		}
		return list;
	}

	public void updateSyncStatus(int id, String status, String systemMatterId, Connection conn) {
		String sql = "UPDATE ext_agreement_header_info SET sync_status = ?, system_matter_id = ?, updated_at = NOW() WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, status);
			ps.setString(2, systemMatterId);
			ps.setInt(3, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private AgreementHeaderInfoModel mapHeaderInfo(ResultSet rs) throws SQLException {
		AgreementHeaderInfoModel model = new AgreementHeaderInfoModel();
		model.setId(rs.getInt("id"));
		model.setCreated_at(rs.getString("created_at"));
		model.setUpdated_at(rs.getString("updated_at"));

		model.setApplication_number(rs.getString(AgreementDbConstants.COLUMN_APPLICATION_NUMBER));
		model.setApplication_date(formatDate(rs.getString(AgreementDbConstants.COLUMN_APPLICATION_DATE)));
		model.setApplicant_number(rs.getString(AgreementDbConstants.COLUMN_APPLICANT_NUMBER));
		model.setApplicant_department(rs.getString(AgreementDbConstants.COLUMN_APPLICANT_DEPARTMENT));
		model.setApplicant_name(rs.getString(AgreementDbConstants.COLUMN_APPLICANT_NAME));
		model.setApplicant_post(rs.getString(AgreementDbConstants.COLUMN_APPLICANT_POST));

		model.setCounter_party(rs.getString(AgreementDbConstants.COLUMN_COUNTER_PARTY));
		model.setCurrency(rs.getString(AgreementDbConstants.COLUMN_CURRENCY));
		model.setTotal_amount(rs.getString(AgreementDbConstants.COLUMN_TOTAL_AMOUNT));
		model.setAgreement_status(rs.getString(AgreementDbConstants.COLUMN_AGREEMENT_STATUS));
		model.setTotal_duration(rs.getString(AgreementDbConstants.COLUMN_TOTAL_DURATION));
		model.setAuto_extension(rs.getString(AgreementDbConstants.COLUMN_AUTO_EXTENSION));
		model.setPo_required(rs.getString(AgreementDbConstants.COLUMN_PO_REQUIRED));
		model.setAgreement_title(rs.getString(AgreementDbConstants.COLUMN_AGREEMENT_TITLE));
		model.setEffective_from(formatDate(rs.getString(AgreementDbConstants.COLUMN_EFFECTIVE_FROM)));
		model.setEffective_to(formatDate(rs.getString(AgreementDbConstants.COLUMN_EFFECTIVE_TO)));
		model.setCompany_relation(rs.getString(AgreementDbConstants.COLUMN_COMPANY_RELATION));
		model.setEstimated_delivery_from(formatDate(rs.getString(AgreementDbConstants.COLUMN_ESTIMATED_DELIVERY_FROM)));
		model.setEstimated_delivery_to(formatDate(rs.getString(AgreementDbConstants.COLUMN_ESTIMATED_DELIVERY_TO)));
		model.setAgreement_summary(rs.getString(AgreementDbConstants.COLUMN_AGREEMENT_SUMMARY));

		model.setPurchase_category(rs.getString(AgreementDbConstants.COLUMN_PURCHASE_CATEGORY));
		model.setStart_using_date(formatDate(rs.getString(AgreementDbConstants.COLUMN_START_USING_DATE)));
		model.setDeprec_month(rs.getString(AgreementDbConstants.COLUMN_DEPREC_MONTH));
		model.setMultidata(rs.getString(AgreementDbConstants.COLUMN_MULTIDATA));
		model.setBudget_pl_impact(rs.getString(AgreementDbConstants.COLUMN_BUDGET_PL_IMPACT));
		model.setBudget_pl_month(rs.getString(AgreementDbConstants.COLUMN_BUDGET_PL_MONTH));
		model.setPl_impact(rs.getString(AgreementDbConstants.COLUMN_PL_IMPACT));
		model.setPl_month(rs.getString(AgreementDbConstants.COLUMN_PL_MONTH));
		model.setAsset_number(rs.getString(AgreementDbConstants.COLUMN_ASSET_NUMBER));
		model.setBook_value(rs.getString(AgreementDbConstants.COLUMN_BOOK_VALUE));
		model.setTotal_payment_amount(rs.getString(AgreementDbConstants.COLUMN_TOTAL_PAYMENT_AMOUNT));
		model.setAgreement_classification(rs.getString(AgreementDbConstants.COLUMN_AGREEMENT_CLASSIFICATION));
		model.setPd_sub_condition(rs.getString(AgreementDbConstants.COLUMN_PD_SUB_CONDITION));
		model.setEc_approval(rs.getString(AgreementDbConstants.COLUMN_EC_APPROVAL));
		model.setEc_sub_condition(rs.getString(AgreementDbConstants.COLUMN_EC_SUB_CONDITION));

		return model;
	}

	private String formatDate(String dateStr) {
		if (dateStr == null || dateStr.trim().isEmpty()) return "";
		dateStr = dateStr.trim();
		if (dateStr.length() >= 10) dateStr = dateStr.substring(0, 10);
		return dateStr.replace("-", "/");
	}
}
