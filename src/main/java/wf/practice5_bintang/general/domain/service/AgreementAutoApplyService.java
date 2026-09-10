package wf.practice5_bintang.general.domain.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.intra_mart.foundation.service.client.information.Identifier;
import jp.co.intra_mart.foundation.workflow.application.model.ApplyResultModel;
import jp.co.intra_mart.foundation.workflow.application.model.param.ApplyParam;
import jp.co.intra_mart.foundation.workflow.application.process.ApplyManager;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;

import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import wf.practice5_bintang.general.constant.AgreementFormConstants;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoModel;
import wf.practice5_bintang.general.domain.model.AgreementPaymentDetailModel;
import wf.practice5_bintang.general.domain.repository.AgreementExternalRepository;
import wf.practice5_bintang.general.domain.util.DbValueUtils;

public class AgreementAutoApplyService {

	public String syncPending() {
		return syncPending(new AgreementExternalRepository());
	}

	public String syncPending(String extDbName) {
		return syncPending(new AgreementExternalRepository(extDbName));
	}

	public String syncPending(AgreementExternalRepository repository) {
		try {
			Collection<AgreementHeaderInfoModel> pendingList = repository.findPendingHeaders();

			for (AgreementHeaderInfoModel pending : pendingList) {
				try {
					Map<String, Object> userParameter = buildUserParameter(pending, repository);
					ApplyParam applyParam = buildApplyParam(pending);

					if (userParameter == null || applyParam == null) {
						System.out.println("Skipping record ID " + pending.getId() + " due to build error.");
						continue;
					}

					ApplyManager applyManager = new ApplyManager();
					ApplyResultModel result = applyManager.apply(applyParam, userParameter);

					repository.updateSyncStatus(pending.getId(), "PROCESSED", result.getSystemMatterId());
				} catch (Exception e) {
					repository.updateSyncStatus(pending.getId(), "FAILED", null);
					e.printStackTrace();
				}
			}

			return "TEST APPLIED!";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	private Map<String, Object> buildUserParameter(AgreementHeaderInfoModel pending, AgreementExternalRepository repository) {
		try {
			int externalId = pending.getId();
			List<AgreementPaymentDetailModel> paymentDetailList = new ArrayList<>(repository.findPaymentDetails(externalId));
			List<AgreementAttachmentModel> attachmentList = new ArrayList<>(repository.findAttachments(externalId));

			return populateUserParameter(pending, paymentDetailList, attachmentList);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Map<String, Object> populateUserParameter(AgreementHeaderInfoModel pending, List<AgreementPaymentDetailModel> paymentDetails, List<AgreementAttachmentModel> attachments) {
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

			userParameter.put(AgreementFormConstants.FIELD_AGREEMENT_CLASSIFICATION, pending.getAgreement_classification());
			userParameter.put(AgreementFormConstants.FIELD_PD_SUB_CONDITION, pending.getPd_sub_condition());
			userParameter.put(AgreementFormConstants.FIELD_EC_APPROVAL, pending.getEc_approval());
			userParameter.put(AgreementFormConstants.FIELD_EC_SUB_CONDITION, pending.getEc_sub_condition());

			if (paymentDetails != null && !paymentDetails.isEmpty()) {
				BigDecimal calculatedPaymentAmount = BigDecimal.ZERO;

				for (int i = 1; i <= paymentDetails.size(); i++) {
					AgreementPaymentDetailModel detail = paymentDetails.get(i - 1);
					BigDecimal paymentAmount = DbValueUtils.parseBigDecimal(detail.getPayment_amount());
					if (paymentAmount != null) {
						calculatedPaymentAmount = calculatedPaymentAmount.add(paymentAmount);
					}

					userParameter.put("f_brand_" + i, detail.getBrand());
					userParameter.put("f_type_" + i, detail.getType());
					userParameter.put("f_payment_amount_" + i, detail.getPayment_amount());
					userParameter.put("f_payment_date_" + i, detail.getPayment_date());
					userParameter.put("f_category_" + i, detail.getCategory());
					userParameter.put("f_recurring_" + i, detail.getRecurring());
					userParameter.put("f_paid_by_" + i, detail.getPaid_by());
				}

				userParameter.put(AgreementFormConstants.FIELD_TOTAL_PAYMENT_AMOUNT, calculatedPaymentAmount.toPlainString());
			}

			populateAttachmentParameters(attachments, userParameter);

			return userParameter;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void populateAttachmentParameters(List<AgreementAttachmentModel> attachments, Map<String, Object> userParameter) {
		if (attachments == null || attachments.isEmpty()) {
			return;
		}

		try {
			Identifier identifier = new Identifier();
			List<String> fileIds = new ArrayList<>();
			List<String> fileNames = new ArrayList<>();
			List<String> fileRealNames = new ArrayList<>();
			List<String> fileTypes = new ArrayList<>();
			List<String> fileSizes = new ArrayList<>();
			List<String> fileExtensions = new ArrayList<>();

			PublicStorage tempDir = new PublicStorage(AgreementFormConstants.STORAGE_DIR_FILE_ATTACHMENT);
			tempDir.makeDirectories();

			for (int i = 0; i < attachments.size(); i++) {
				AgreementAttachmentModel attach = attachments.get(i);
				String filePath = attach.getFile_path();
				File realDiskFile = new File(filePath);

				if (realDiskFile.exists() && realDiskFile.isFile()) {
					byte[] fileBytes = Files.readAllBytes(realDiskFile.toPath());

					String originalFileName = realDiskFile.getName();
					String fileRealName = identifier.get();
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
		}
	}

	private ApplyParam buildApplyParam(AgreementHeaderInfoModel pending) {
		try {
			ApplyParam applyParam = new ApplyParam();
			Identifier identifier = new Identifier();
			String userDataId = identifier.get();
			String today = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

			applyParam.setFlowId("8i507dvedvb655o");
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
}
