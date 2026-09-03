package wf.practice5_bintang.general.domain.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.intra_mart.foundation.context.Contexts;
import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.database.SearchCondition;
import jp.co.intra_mart.foundation.service.client.information.Identifier;
import jp.co.intra_mart.foundation.user_context.model.UserContext;
import jp.co.intra_mart.foundation.workflow.application.model.ApplyResultModel;
import jp.co.intra_mart.foundation.workflow.application.model.param.ApplyParam;
import jp.co.intra_mart.foundation.workflow.application.process.ApplyManager;
import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;

import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.file.SessionScopeStorage;
import wf.practice5_bintang.general.constant.AgreementFormConstants;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoModel;
import wf.practice5_bintang.general.domain.model.AgreementPaymentDetailModel;
import wf.practice5_bintang.general.domain.repository.AgreementExternalRepository;

public class AgreementAutoApplyService {
	public String executeTestApply() throws Exception {
		UserContext userContext = Contexts.get(UserContext.class);
		Identifier identifier = new Identifier();

		String applicantName = userContext.getUserProfile().getUserName();
		String applicantNumber = userContext.getUserProfile().getUserCd();
		String userDataId = identifier.get();
		String today = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		Map<String, Object> userParameter = new HashMap<String,Object>();
		userParameter.put(AgreementFormConstants.FIELD_APPLICATION_NUMBER, "Testing Application Number");
		userParameter.put(AgreementFormConstants.FIELD_APPLICATION_DATE, today);
		userParameter.put(AgreementFormConstants.FIELD_APPLICANT_NUMBER, applicantNumber);
		userParameter.put(AgreementFormConstants.FIELD_APPLICANT_DEPARTMENT, "Testing Department");
		userParameter.put(AgreementFormConstants.FIELD_APPLICANT_NAME, applicantName);
		userParameter.put(AgreementFormConstants.FIELD_APPLICANT_POST, "Testing Post");
		
		userParameter.put(AgreementFormConstants.FIELD_COUNTER_PARTY, "Testing Counter Party");
		userParameter.put(AgreementFormConstants.FIELD_CURRENCY, "JPY");
		userParameter.put(AgreementFormConstants.FIELD_TOTAL_AMOUNT, "22222");
		userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_STATUS, "one_time");
		userParameter.put(AgreementFormConstants.FIELD_AUTO_EXTENSION, "no");
		userParameter.put(AgreementFormConstants.FIELD_PO_REQUIRED, "no");
		userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_TITLE, "Testing Title");
		userParameter.put(AgreementFormConstants.FIELD_EFFECTIVE_FROM, today);
		userParameter.put(AgreementFormConstants.FIELD_EFFECTIVE_TO, today);
		userParameter.put(AgreementFormConstants.FIELD_ESTIMATED_DELIVERY_FROM, today);
		userParameter.put(AgreementFormConstants.FIELD_ESTIMATED_DELIVERY_TO, today);
		userParameter.put(AgreementFormConstants.FIELD_COMPANY_RELATION, "non_related_parties");
		userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_SUMMARY, "Testing Summary");
		
		userParameter.put(AgreementFormConstants.FIELD_PURCHASE_CATEGORY, "tangible_asset");
		userParameter.put(AgreementFormConstants.FIELD_START_USING_DATE, today);
		userParameter.put(AgreementFormConstants.FIELD_DEPREC_MONTH, "Testing Deprec");

		userParameter.put(AgreementFormConstants.FIELD_MULTIDATA, "[pl,asset,estimated]");

		userParameter.put(AgreementFormConstants.FIELD_BUDGET_PL_IMPACT, " Testing Budget PL Impact");
		userParameter.put(AgreementFormConstants.FIELD_BUDGET_PL_MONTH, "Testing Budget PL Month");
		userParameter.put(AgreementFormConstants.FIELD_PL_IMPACT, "Testing PL Impact");
		userParameter.put(AgreementFormConstants.FIELD_PL_MONTH, "Testing PL Month");

		userParameter.put(AgreementFormConstants.FIELD_ASSET_NUMBER, "Testing Asset Number");
		userParameter.put(AgreementFormConstants.FIELD_BOOK_VALUE, "Testing Book Value");

		for (int i = 1; i <= 3; i++) {
			userParameter.put("f_brand_" + i, "Testing Brand " + i);
			userParameter.put("f_type_" + i, "Testing Type " + i);
			userParameter.put("f_payment_amount_" + i, "123");
			userParameter.put("f_payment_date_" + i, today);
			userParameter.put("f_category_" + i, String.valueOf(i));
			userParameter.put("f_recurring_" + i, "yes");
			userParameter.put("f_paid_by_" + i, Arrays.asList("cash", "card"));
		}

		userParameter.put(AgreementFormConstants.FIELD_TOTAL_PAYMENT_AMOUNT, String.valueOf(123*3));

		userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_CLASSIFICATION, "pd");
		userParameter.put(AgreementFormConstants.FIELD_PD_SUB_CONDITION, "pd_specific_party");
		userParameter.put(AgreementFormConstants.FIELD_EC_APPROVAL, "yes");
		userParameter.put(AgreementFormConstants.FIELD_EC_SUB_CONDITION, "ec_period_equal_more_than_12_months");

		File realDiskFile = new File("C:/imarttest/storage/public/storage/tenant/mock_files/test_document.txt");

		if(realDiskFile.exists() && realDiskFile.isFile()){
			byte[] fileBytes = Files.readAllBytes(realDiskFile.toPath());

			String originalFileName = realDiskFile.getName();
			String fileRealName = identifier.get();
    		long fileSize = realDiskFile.length();

			SessionScopeStorage tempDir = new SessionScopeStorage(AgreementFormConstants.STORAGE_DIR_FILE_ATTACHMENT);
			tempDir.makeDirectories();
			SessionScopeStorage tempFile = new SessionScopeStorage(AgreementFormConstants.STORAGE_DIR_FILE_ATTACHMENT + "/" + fileRealName);
    		tempFile.save(fileBytes);

    		userParameter.put("f_upload_file_id", "1");
    		userParameter.put("f_upload_file_name", originalFileName);
    		userParameter.put("f_upload_file_real_name", fileRealName);
    		userParameter.put("f_upload_file_type", "agreement");
    		userParameter.put("f_upload_file_size", String.valueOf(fileSize));
    		userParameter.put("f_upload_file_extension", "text/plain");
		} else {
			System.out.println("File not found: " + realDiskFile.getAbsolutePath());
		}

		ApplyParam applyParam = new ApplyParam();
		applyParam.setFlowId("8i3yw26w0xzkrem");
		applyParam.setApplyBaseDate(today);
		applyParam.setApplyExecuteUserCode("autoapplyservice");
		applyParam.setApplyAuthUserCode("autoapplyservice");
		applyParam.setUserDataId(userDataId);
		String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		applyParam.setMatterName("Auto Batch Application Test - " + timestamp);

		ApplyManager applyManager = new ApplyManager();
		applyManager.apply(applyParam, userParameter);

		return "TEST APPLIED! (UserDataId: " + userDataId + ")";
	}

	public String syncPending() {
		try {
			SQLManager sqlManager = new SQLManager();
			String sql = "SELECT * FROM ext_agreement_header_info WHERE sync_status = 'PENDING'";
			Collection<AgreementHeaderInfoModel> pendingList = sqlManager.select(AgreementHeaderInfoModel.class, sql, new ArrayList<>());

			for (AgreementHeaderInfoModel pending : pendingList) {
				try {
					Map<String, Object> userParameter = buildUserParameter(pending);
					ApplyParam applyParam = buildApplyParam(pending);

					if (userParameter == null || applyParam == null) {
						System.out.println("Skipping record ID " + pending.getId() + " due to build error.");
						continue;
					}

					ApplyManager applyManager = new ApplyManager();
					ApplyResultModel result = applyManager.apply(applyParam, userParameter);

					updateSyncStatus(pending.getId(), "PROCESSED", result.getSystemMatterId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return "TEST APPLIED!";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		} 
	}

	private Map<String, Object> buildUserParameter(AgreementHeaderInfoModel pending) {
		try {
			Map<String, Object> userParameter = new HashMap<>();

			userParameter.put(AgreementFormConstants.FIELD_APPLICATION_NUMBER, pending.getApplication_number());
			userParameter.put(AgreementFormConstants.FIELD_APPLICATION_DATE, pending.getApplication_date());
			userParameter.put(AgreementFormConstants.FIELD_APPLICANT_NUMBER, pending.getApplicant_number());
			userParameter.put(AgreementFormConstants.FIELD_APPLICANT_DEPARTMENT, pending.getApplicant_department());
			userParameter.put(AgreementFormConstants.FIELD_APPLICANT_NAME, pending.getApplicant_name());
			userParameter.put(AgreementFormConstants.FIELD_APPLICANT_POST, pending.getApplicant_post());
			
			userParameter.put(AgreementFormConstants.FIELD_COUNTER_PARTY, pending.getCounter_party());
			userParameter.put(AgreementFormConstants.FIELD_CURRENCY, pending.getCurrency());
			userParameter.put(AgreementFormConstants.FIELD_TOTAL_AMOUNT, pending.getTotal_amount());
			userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_STATUS, pending.getAgreement_status());
			userParameter.put(AgreementFormConstants.FIELD_AUTO_EXTENSION, pending.getAuto_extension());
			userParameter.put(AgreementFormConstants.FIELD_PO_REQUIRED, pending.getPo_required());
			userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_TITLE, pending.getAgreement_title());
			userParameter.put(AgreementFormConstants.FIELD_EFFECTIVE_FROM, pending.getEffective_from());
			userParameter.put(AgreementFormConstants.FIELD_EFFECTIVE_TO, pending.getEffective_to());
			userParameter.put(AgreementFormConstants.FIELD_COMPANY_RELATION, pending.getCompany_relation());
			userParameter.put(AgreementFormConstants.FIELD_ESTIMATED_DELIVERY_FROM, pending.getEstimated_delivery_from());
			userParameter.put(AgreementFormConstants.FIELD_ESTIMATED_DELIVERY_TO, pending.getEstimated_delivery_to());
			userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_SUMMARY, pending.getAgreement_summary());
			
			userParameter.put(AgreementFormConstants.FIELD_PURCHASE_CATEGORY, pending.getPurchase_category());
			userParameter.put(AgreementFormConstants.FIELD_START_USING_DATE, pending.getStart_using_date());
			userParameter.put(AgreementFormConstants.FIELD_DEPREC_MONTH, pending.getDeprec_month());

			userParameter.put(AgreementFormConstants.FIELD_MULTIDATA, pending.getMultidata());

			userParameter.put(AgreementFormConstants.FIELD_BUDGET_PL_IMPACT, pending.getBudget_pl_impact());
			userParameter.put(AgreementFormConstants.FIELD_BUDGET_PL_MONTH, pending.getBudget_pl_month());
			userParameter.put(AgreementFormConstants.FIELD_PL_IMPACT, pending.getPl_impact());
			userParameter.put(AgreementFormConstants.FIELD_PL_MONTH, pending.getPl_month());

			userParameter.put(AgreementFormConstants.FIELD_ASSET_NUMBER, pending.getAsset_number());
			userParameter.put(AgreementFormConstants.FIELD_BOOK_VALUE, pending.getBook_value());

			userParameter.put(AgreementFormConstants.FIELD_TOTAL_PAYMENT_AMOUNT, pending.getTotal_payment_amount());

			userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_CLASSIFICATION, pending.getAgreement_classification());
			userParameter.put(AgreementFormConstants.FIELD_PD_SUB_CONDITION, pending.getPd_sub_condition());
			userParameter.put(AgreementFormConstants.FIELD_EC_APPROVAL, pending.getEc_approval());
			userParameter.put(AgreementFormConstants.FIELD_EC_SUB_CONDITION, pending.getEc_sub_condition());

			int externalId = pending.getId();
			userParameter = buildPaymentDetail(externalId, userParameter);
			userParameter = buildAttachment(externalId, userParameter);

			return userParameter;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Map<String, Object> buildPaymentDetail(int externalId, Map<String, Object> userParameter) {
		try {
			SQLManager sqlManager = new SQLManager();
			String sql = "SELECT * FROM ext_agreement_payment_details WHERE ext_header_id = ? ORDER BY row_no ASC, id ASC";
			Collection<Object> parameters = new ArrayList<Object>();
			parameters.add(externalId);

			ArrayList<AgreementPaymentDetailModel> paymentDetailList = (ArrayList<AgreementPaymentDetailModel>) sqlManager.select(AgreementPaymentDetailModel.class, sql, parameters);
			
			for (int i = 1; i <= paymentDetailList.size(); i++) {
				userParameter.put("f_brand_" + i, paymentDetailList.get(i-1).getBrand());
				userParameter.put("f_type_" + i, paymentDetailList.get(i-1).getType());
				userParameter.put("f_payment_amount_" + i, paymentDetailList.get(i-1).getPayment_amount());
				userParameter.put("f_payment_date_" + i, paymentDetailList.get(i-1).getPayment_date());
				userParameter.put("f_category_" + i, paymentDetailList.get(i-1).getCategory());
				userParameter.put("f_recurring_" + i, paymentDetailList.get(i-1).getRecurring());
				userParameter.put("f_paid_by_" + i, paymentDetailList.get(i-1).getPaid_by());
			}

			return userParameter;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	private Map<String, Object> buildAttachment(int externalId, Map<String, Object> userParameter) {
		try {
			Identifier identifier = new Identifier();
			SQLManager sqlManager = new SQLManager();
			String sql = "SELECT * FROM ext_agreement_attach_file WHERE ext_header_id = ?";
			Collection<Object> parameters = new ArrayList<Object>();
			parameters.add(externalId);

			ArrayList<AgreementAttachmentModel> attachmentList = (ArrayList<AgreementAttachmentModel>) sqlManager.select(AgreementAttachmentModel.class, sql, parameters);

			if (attachmentList == null || attachmentList.isEmpty()) {
				return userParameter;
			}

			List<String> fileIds = new ArrayList<>();
			List<String> fileNames = new ArrayList<>();
			List<String> fileRealNames = new ArrayList<>();
			List<String> fileTypes = new ArrayList<>();
			List<String> fileSizes = new ArrayList<>();
			List<String> fileExtensions = new ArrayList<>();

			PublicStorage tempDir = new PublicStorage(AgreementFormConstants.STORAGE_DIR_FILE_ATTACHMENT);
			tempDir.makeDirectories();

			for (int i = 0; i < attachmentList.size(); i++) {
				String filePath = attachmentList.get(i).getFile_path();
				File realDiskFile = new File(filePath);

				if (realDiskFile.exists() && realDiskFile.isFile()) {
					byte[] fileBytes = Files.readAllBytes(realDiskFile.toPath());

					String originalFileName = realDiskFile.getName();
					String fileRealName = identifier.get();
					long fileSize = realDiskFile.length();
					String fileExtension = attachmentList.get(i).getFile_extension() != null ? attachmentList.get(i).getFile_extension() : "text/plain";
					String fileType = attachmentList.get(i).getFile_type() != null ? attachmentList.get(i).getFile_type() : "agreement";

					PublicStorage tempFile = new PublicStorage(AgreementFormConstants.STORAGE_DIR_FILE_ATTACHMENT + "/" + fileRealName);
					tempFile.save(fileBytes);

					fileIds.add(String.valueOf(i + 1));
					fileNames.add(originalFileName);
					fileRealNames.add(fileRealName);
					fileTypes.add(fileType);
					fileSizes.add(String.valueOf(fileSize));
					fileExtensions.add(fileExtension);

				} else {
					System.out.println("File not found: " + filePath);
				}
			}

			if (!fileIds.isEmpty()) {
				userParameter.put("f_upload_file_id", fileIds);
				userParameter.put("f_upload_file_name", fileNames);
				userParameter.put("f_upload_file_real_name", fileRealNames);
				userParameter.put("f_upload_file_type", fileTypes);
				userParameter.put("f_upload_file_size", fileSizes);
				userParameter.put("f_upload_file_extension", fileExtensions);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userParameter;
	}

	private void updateSyncStatus(int id, String status, String systemMatterId) {
		try {
			SQLManager sqlManager = new SQLManager();
			
			ColumnValues columnValues = new ColumnValues();
			columnValues.add("sync_status", status);
			columnValues.add("updated_at", new java.sql.Timestamp(System.currentTimeMillis()));
			if (systemMatterId != null) {
				columnValues.add("system_matter_id", systemMatterId);
			}

			SearchCondition searchCondition = new SearchCondition();
			searchCondition.addCondition("id", id);

			sqlManager.update("ext_agreement_header_info", columnValues, searchCondition);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ApplyParam buildApplyParam(AgreementHeaderInfoModel pending) {
		try {
			ApplyParam applyParam = new ApplyParam();
			Identifier identifier = new Identifier();
			String userDataId = identifier.get();
			String today = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

			applyParam.setFlowId("8i3yw26w0xzkrem");
			applyParam.setApplyBaseDate(today);
			applyParam.setApplyExecuteUserCode("autoapplyservice");
			applyParam.setApplyAuthUserCode("autoapplyservice");
			applyParam.setUserDataId(userDataId);
			String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
			applyParam.setMatterName("Auto Batch Application Test - " + timestamp);

			return applyParam;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String syncPendingFromMySql() {
		AgreementExternalRepository extRepo = new AgreementExternalRepository();

		try (Connection conn = extRepo.getConnection()) {
			List<AgreementHeaderInfoModel> pendingList = extRepo.findPendingHeaders(conn);

			for (AgreementHeaderInfoModel pending : pendingList) {
				try {
					Map<String, Object> userParameter = buildUserParameterFromMySql(pending, extRepo, conn);
					ApplyParam applyParam = buildApplyParam(pending);

					if (userParameter == null || applyParam == null) {
						System.out.println("Skipping record ID " + pending.getId() + " due to build error.");
						continue;
					}

					ApplyManager applyManager = new ApplyManager();
					ApplyResultModel result = applyManager.apply(applyParam, userParameter);

					extRepo.updateSyncStatus(pending.getId(), "PROCESSED", result.getSystemMatterId(), conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return "TEST APPLIED!";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	private Map<String, Object> buildUserParameterFromMySql(AgreementHeaderInfoModel pending, AgreementExternalRepository extRepo, Connection conn) {
		try {
			Map<String, Object> userParameter = new HashMap<>();

			userParameter.put(AgreementFormConstants.FIELD_APPLICATION_NUMBER, pending.getApplication_number());
			userParameter.put(AgreementFormConstants.FIELD_APPLICATION_DATE, pending.getApplication_date());
			userParameter.put(AgreementFormConstants.FIELD_APPLICANT_NUMBER, pending.getApplicant_number());
			userParameter.put(AgreementFormConstants.FIELD_APPLICANT_DEPARTMENT, pending.getApplicant_department());
			userParameter.put(AgreementFormConstants.FIELD_APPLICANT_NAME, pending.getApplicant_name());
			userParameter.put(AgreementFormConstants.FIELD_APPLICANT_POST, pending.getApplicant_post());

			userParameter.put(AgreementFormConstants.FIELD_COUNTER_PARTY, pending.getCounter_party());
			userParameter.put(AgreementFormConstants.FIELD_CURRENCY, pending.getCurrency());
			userParameter.put(AgreementFormConstants.FIELD_TOTAL_AMOUNT, pending.getTotal_amount());
			userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_STATUS, pending.getAgreement_status());
			userParameter.put(AgreementFormConstants.FIELD_AUTO_EXTENSION, pending.getAuto_extension());
			userParameter.put(AgreementFormConstants.FIELD_PO_REQUIRED, pending.getPo_required());
			userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_TITLE, pending.getAgreement_title());
			userParameter.put(AgreementFormConstants.FIELD_EFFECTIVE_FROM, pending.getEffective_from());
			userParameter.put(AgreementFormConstants.FIELD_EFFECTIVE_TO, pending.getEffective_to());
			userParameter.put(AgreementFormConstants.FIELD_COMPANY_RELATION, pending.getCompany_relation());
			userParameter.put(AgreementFormConstants.FIELD_ESTIMATED_DELIVERY_FROM, pending.getEstimated_delivery_from());
			userParameter.put(AgreementFormConstants.FIELD_ESTIMATED_DELIVERY_TO, pending.getEstimated_delivery_to());
			userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_SUMMARY, pending.getAgreement_summary());

			userParameter.put(AgreementFormConstants.FIELD_PURCHASE_CATEGORY, pending.getPurchase_category());
			userParameter.put(AgreementFormConstants.FIELD_START_USING_DATE, pending.getStart_using_date());
			userParameter.put(AgreementFormConstants.FIELD_DEPREC_MONTH, pending.getDeprec_month());

			userParameter.put(AgreementFormConstants.FIELD_MULTIDATA, pending.getMultidata());

			userParameter.put(AgreementFormConstants.FIELD_BUDGET_PL_IMPACT, pending.getBudget_pl_impact());
			userParameter.put(AgreementFormConstants.FIELD_BUDGET_PL_MONTH, pending.getBudget_pl_month());
			userParameter.put(AgreementFormConstants.FIELD_PL_IMPACT, pending.getPl_impact());
			userParameter.put(AgreementFormConstants.FIELD_PL_MONTH, pending.getPl_month());

			userParameter.put(AgreementFormConstants.FIELD_ASSET_NUMBER, pending.getAsset_number());
			userParameter.put(AgreementFormConstants.FIELD_BOOK_VALUE, pending.getBook_value());

			userParameter.put(AgreementFormConstants.FIELD_TOTAL_PAYMENT_AMOUNT, pending.getTotal_payment_amount());

			userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_CLASSIFICATION, pending.getAgreement_classification());
			userParameter.put(AgreementFormConstants.FIELD_PD_SUB_CONDITION, pending.getPd_sub_condition());
			userParameter.put(AgreementFormConstants.FIELD_EC_APPROVAL, pending.getEc_approval());
			userParameter.put(AgreementFormConstants.FIELD_EC_SUB_CONDITION, pending.getEc_sub_condition());

			int externalId = pending.getId();
			userParameter = buildPaymentDetailFromMySql(externalId, userParameter, extRepo, conn);
			userParameter = buildAttachmentFromMySql(externalId, userParameter, extRepo, conn);

			return userParameter;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Map<String, Object> buildPaymentDetailFromMySql(int externalId, Map<String, Object> userParameter, AgreementExternalRepository extRepo, Connection conn) {
		try {
			List<AgreementPaymentDetailModel> paymentDetailList = extRepo.findPaymentDetails(externalId, conn);

			for (int i = 1; i <= paymentDetailList.size(); i++) {
				AgreementPaymentDetailModel detail = paymentDetailList.get(i - 1);
				userParameter.put("f_brand_" + i, detail.getBrand());
				userParameter.put("f_type_" + i, detail.getType());
				userParameter.put("f_payment_amount_" + i, detail.getPayment_amount());
				userParameter.put("f_payment_date_" + i, detail.getPayment_date());
				userParameter.put("f_category_" + i, detail.getCategory());
				userParameter.put("f_recurring_" + i, detail.getRecurring());
				userParameter.put("f_paid_by_" + i, detail.getPaid_by());
			}
			return userParameter;
		} catch (Exception e) {
			e.printStackTrace();
			return userParameter;
		}
	}

	private Map<String, Object> buildAttachmentFromMySql(int externalId, Map<String, Object> userParameter, AgreementExternalRepository extRepo, Connection conn) {
		try {
			List<AgreementAttachmentModel> attachmentList = extRepo.findAttachments(externalId, conn);
			if (attachmentList == null || attachmentList.isEmpty()) {
				return userParameter;
			}

			Identifier identifier = new Identifier();
			List<String> fileIds = new ArrayList<>();
			List<String> fileNames = new ArrayList<>();
			List<String> fileRealNames = new ArrayList<>();
			List<String> fileTypes = new ArrayList<>();
			List<String> fileSizes = new ArrayList<>();
			List<String> fileExtensions = new ArrayList<>();

			PublicStorage tempDir = new PublicStorage(AgreementFormConstants.STORAGE_DIR_FILE_ATTACHMENT);
			tempDir.makeDirectories();

			for (int i = 0; i < attachmentList.size(); i++) {
				AgreementAttachmentModel attach = attachmentList.get(i);
				String filePath = attach.getFile_path();
				File realDiskFile = new File(filePath);

				if (realDiskFile.exists() && realDiskFile.isFile()) {
					byte[] fileBytes = Files.readAllBytes(realDiskFile.toPath());

					String fileRealName = identifier.get();
					String originalFileName = realDiskFile.getName();
					long fileSize = realDiskFile.length();
					String fileExtension = "text/plain";
					String fileType = "agreement";

					PublicStorage tempFile = new PublicStorage(AgreementFormConstants.STORAGE_DIR_FILE_ATTACHMENT + "/" + fileRealName);
					tempFile.save(fileBytes);

					fileIds.add(String.valueOf(i + 1));
					fileNames.add(originalFileName);
					fileRealNames.add(fileRealName);
					fileTypes.add(fileType);
					fileSizes.add(String.valueOf(fileSize));
					fileExtensions.add(fileExtension);
				} else {
					System.out.println("File not found: " + filePath);
				}
			}

			if (!fileIds.isEmpty()) {
				userParameter.put("f_upload_file_id", fileIds);
				userParameter.put("f_upload_file_name", fileNames);
				userParameter.put("f_upload_file_real_name", fileRealNames);
				userParameter.put("f_upload_file_type", fileTypes);
				userParameter.put("f_upload_file_size", fileSizes);
				userParameter.put("f_upload_file_extension", fileExtensions);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return userParameter;
		}
		return userParameter;
	}
}

