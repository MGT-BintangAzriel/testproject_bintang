package wf.practice5_bintang.general.domain.repository;

import java.util.ArrayList;
import java.util.Collection;

import jp.co.intra_mart.foundation.database.SQLManager;
import wf.practice5_bintang.general.constant.AgreementDbConstants;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoModel;
import wf.practice5_bintang.general.domain.model.AgreementPaymentDetailModel;

public class AgreementExternalRepository {

	private static final String SQL_SELECT_PENDING = "SELECT * FROM " + AgreementDbConstants.EXT_TABLE_HEADER_INFO + " WHERE sync_status = 'PENDING'";
	private static final String SQL_SELECT_PAYMENT_DETAIL = "SELECT * FROM " + AgreementDbConstants.EXT_TABLE_PAYMENT_DETAILS + " WHERE ext_header_id = ? ORDER BY row_no ASC, id ASC";
	private static final String SQL_SELECT_ATTACHMENT = "SELECT * FROM " + AgreementDbConstants.EXT_TABLE_ATTACH_FILE + " WHERE ext_header_id = ? ORDER BY id ASC";
	private static final String SQL_UPDATE_SYNC = "UPDATE " + AgreementDbConstants.EXT_TABLE_HEADER_INFO + " SET sync_status = ?, system_matter_id = ?, updated_at = NOW() WHERE id = ?";

	private SQLManager sqlManager;

	public AgreementExternalRepository() {
		this(null);
	}

	public AgreementExternalRepository(String extDbName) {
		try {
			if (extDbName != null && !extDbName.trim().isEmpty()) {
				this.sqlManager = new SQLManager(extDbName.trim(), false);
			} else {
				this.sqlManager = new SQLManager();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException("Failed to initialize SQLManager for database: " + extDbName, e);
		}
	}

	public Collection<AgreementHeaderInfoModel> findPendingHeaders() throws Exception {
		try {
			Collection<AgreementHeaderInfoModel> result = sqlManager.select(AgreementHeaderInfoModel.class, SQL_SELECT_PENDING, new ArrayList<>());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in findPendingHeaders", e);
		}
	}

	public Collection<AgreementPaymentDetailModel> findPaymentDetails(int extHeaderId) throws Exception {
		try {
			Collection<Object> parameters = new ArrayList<>();
			parameters.add(extHeaderId);

			Collection<AgreementPaymentDetailModel> result = sqlManager.select(AgreementPaymentDetailModel.class, SQL_SELECT_PAYMENT_DETAIL, parameters);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in findPaymentDetails", e);

		}
	}

	public Collection<AgreementAttachmentModel> findAttachments(int extHeaderId) throws Exception {
		try {
			Collection<Object> parameters = new ArrayList<>();
			parameters.add(extHeaderId);

			Collection<AgreementAttachmentModel> result = sqlManager.select(AgreementAttachmentModel.class, SQL_SELECT_ATTACHMENT, parameters);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in findAttachments", e);
		}
	}

	public void updateSyncStatus(int id, String status, String systemMatterId) throws Exception {
		try {
			Collection<Object> parameters = new ArrayList<>();
			parameters.add(status);
			parameters.add(systemMatterId);
			parameters.add(id);

			sqlManager.update(SQL_UPDATE_SYNC, parameters);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in updateSyncStatus", e);
		}
	}
}
