package wf.practice5_bintang.general.domain.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.co.intra_mart.foundation.context.Contexts;
import jp.co.intra_mart.foundation.service.client.information.Identifier;
import jp.co.intra_mart.foundation.user_context.model.UserContext;
import jp.co.intra_mart.foundation.workflow.application.model.param.ApplyParam;
import jp.co.intra_mart.foundation.workflow.application.process.ApplyManager;
import java.io.File;
import java.nio.file.Files;
import jp.co.intra_mart.foundation.service.client.file.SessionScopeStorage;
import wf.practice5_bintang.general.constant.AgreementFormConstants;

public class AgreementAutoApplyTestService {
	public String executeTestApply() throws Exception {
		UserContext userContext = Contexts.get(UserContext.class);

		String applicantName = userContext.getUserProfile().getUserName();
		String applicantNumber = userContext.getUserProfile().getUserCd();
		
		Identifier identifier = new Identifier();
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

		// userParameter.put(AgreementFormConstants.FIELD_PSD_AREA, "psd");
		// userParameter.put(AgreementFormConstants.FIELD_PSD_PROCESS, "psd");
		// userParameter.put(AgreementFormConstants.FIELD_DIC_REASON, "Testing Dic Reason");
		// userParameter.put(AgreementFormConstants.FIELD_DD_PROCESS, "yes");
		// userParameter.put(AgreementFormConstants.FIELD_ANTI_BRIBERY, "yes");
		// userParameter.put(AgreementFormConstants.FIELD_AUDIT_RIGHTS, "yes");
		// userParameter.put(AgreementFormConstants.FIELD_LEGAL_AGREEMENT_NUMBER, "Testing Legal Agreement Number");
		// userParameter.put(AgreementFormConstants.FIELD_LEGAL_AGREEMENT_DATE, today);

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
		applyParam.setApplyExecuteUserCode("Autoapply Service");
		applyParam.setApplyAuthUserCode("Autoapply Service");
		applyParam.setUserDataId(userDataId);

		String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		applyParam.setMatterName("Auto Batch Application Test - " + timestamp);

		ApplyManager applyManager = new ApplyManager();
		applyManager.apply(applyParam, userParameter);

		return "TEST APPLIED! (UserDataId: " + userDataId + ")";
	}
}
