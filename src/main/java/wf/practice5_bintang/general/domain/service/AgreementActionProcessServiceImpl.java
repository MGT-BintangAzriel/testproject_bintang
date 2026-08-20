package wf.practice5_bintang.general.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.intra_mart.common.aid.jdk.java.util.LocaleUtil;
import jp.co.intra_mart.foundation.security.message.MessageManager;
import jp.co.intra_mart.foundation.workflow.application.general.UserActvMatterPropertyValue;
import jp.co.intra_mart.foundation.workflow.application.model.UserMatterPropertyModel;
import jp.co.intra_mart.foundation.workflow.exception.WorkflowException;
import jp.co.intra_mart.foundation.workflow.exception.WorkflowExternalException;
import jp.co.intra_mart.foundation.workflow.plugin.process.action.ActionProcessParameter;
import jp.co.intra_mart.foundation.workflow.util.WorkflowNumberingManager;

import wf.common.constant.WorkflowStatus;
import wf.practice5_bintang.general.AgreementActionProcessService;
import wf.practice5_bintang.general.constant.AgreementFormConstants;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoTempModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderModel;
import wf.practice5_bintang.general.domain.model.AgreementPaymentDetailModel;
import wf.practice5_bintang.general.domain.repository.AgreementAttachFileRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderInfoTempRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderRepository;
import wf.practice5_bintang.general.domain.repository.AgreementPaymentDetailRepository;
import wf.common.constant.MailStatus;
import wf.common.constant.WorkflowCommonConstants;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class AgreementActionProcessServiceImpl implements AgreementActionProcessService {

	@Override
	public String apply(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {
		String number = null;
		try {
			AgreementWorkflowService workflowService = new AgreementWorkflowService();
			
			AgreementHeaderRepository agreementHeaderDb = new AgreementHeaderRepository();
			AgreementHeaderInfoTempRepository agreementHeaderInfoTempDb = new AgreementHeaderInfoTempRepository();
			AgreementAttachFileRepository agreementAttachDb = new AgreementAttachFileRepository();
			AgreementPaymentDetailRepository agreementPaymentDetailDb = new AgreementPaymentDetailRepository();

			AgreementHeaderModel headerModel = extractHeaderModel(parameter, userParameter);
			agreementHeaderDb.insertHeader(headerModel);

			AgreementHeaderInfoTempModel headerInfoTempModel = extractHeaderInfoTempModel(parameter, userParameter);
			agreementHeaderInfoTempDb.insertHeaderInfoTemp(headerInfoTempModel);

			final List<AgreementAttachmentModel> attachmentList = extractAttachmentModels(parameter, userParameter);
			for (int i = 0; i < attachmentList.size(); i++) {
				agreementAttachDb.insertTempAttachment(attachmentList.get(i));
				workflowService.transferAttachmentFile(parameter.getSystemMatterId(),
						attachmentList.get(i).getFile_real_name());
			}

			final List<AgreementPaymentDetailModel> paymentDetailList = extractPaymentDetailModels(parameter,
					userParameter);
			for (int i=0; i<paymentDetailList.size();i++) {
				agreementPaymentDetailDb.insertTempPaymentDetail(paymentDetailList.get(i));
			}

			createMatterProperty(parameter.getUserDataId(), "practice5_bintang_po", headerInfoTempModel.getPo_required());
			createMatterProperty(parameter.getUserDataId(), "practice5_bintang_ac", headerInfoTempModel.getAgreement_classification());
			createMatterProperty(parameter.getUserDataId(), "practice5_bintang_ec", headerInfoTempModel.getEc_approval());

			number = WorkflowNumberingManager.getNumber();

		} catch (final WorkflowException e) {
			throw new WorkflowExternalException(MessageManager.getInstance()
					.getMessage(LocaleUtil.toLocale(parameter.getLocaleId()), "SAMPLE.IMW.ERR.003"));

		}
		return number;
	}

	private AgreementHeaderModel extractHeaderModel(ActionProcessParameter parameter,
			Map<String, Object> userParameter) {
		AgreementHeaderModel headerModel = new AgreementHeaderModel();

		headerModel.setUser_data_id(parameter.getUserDataId());
		headerModel.setSystem_matter_id(parameter.getSystemMatterId());
		headerModel.setStatus(WorkflowStatus.APPLIED.getCode());
		headerModel.setMail_status(MailStatus.INITIAL.getCode());

		return headerModel;
	}

	private AgreementHeaderInfoTempModel extractHeaderInfoTempModel(ActionProcessParameter parameter,
			Map<String, Object> userParameter) {
		AgreementHeaderInfoTempModel headerInfoTempModel = new AgreementHeaderInfoTempModel();

		headerInfoTempModel.setUser_data_id(parameter.getUserDataId());
		headerInfoTempModel.setSystem_matter_id(parameter.getSystemMatterId());

		AgreementHeaderRepository agreementHeaderDb = new AgreementHeaderRepository();
		AgreementHeaderModel agreementHeaderModels = new AgreementHeaderModel();

		try {
			agreementHeaderModels = agreementHeaderDb.selectHeader(parameter.getSystemMatterId(),
					WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID).iterator().next();
			headerInfoTempModel
					.setApplication_number("AD-" + String.format("%06d", agreementHeaderModels.getId()));
		} catch (final Exception e) {
			System.out.println(e);
		}

		headerInfoTempModel.setApplication_date(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_APPLICATION_DATE));
		headerInfoTempModel.setApplicant_number(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_APPLICANT_NUMBER));
		headerInfoTempModel.setApplicant_department(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_APPLICANT_DEPARTMENT));
		headerInfoTempModel.setApplicant_name(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_APPLICANT_NAME));
		headerInfoTempModel.setApplicant_post(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_APPLICANT_POST));
		headerInfoTempModel
				.setCounter_party(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_COUNTER_PARTY));
		headerInfoTempModel.setCurrency(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_CURRENCY));
		headerInfoTempModel
				.setTotal_amount(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_TOTAL_AMOUNT));
		headerInfoTempModel.setAgreement_status(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_AGREEMENT_STATUS));
		headerInfoTempModel
				.setTotal_duration(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_TOTAL_DURATION));
		headerInfoTempModel
				.setAuto_extension(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_AUTO_EXTENSION));
		headerInfoTempModel
				.setPo_required(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_PO_REQUIRED));
		headerInfoTempModel
				.setAgreement_title(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_AGREEMENT_TITLE));
		headerInfoTempModel
				.setEffective_from(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_EFFECTIVE_FROM));
		headerInfoTempModel
				.setEffective_to(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_EFFECTIVE_TO));
		headerInfoTempModel.setCompany_relation(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_COMPANY_RELATION));
		headerInfoTempModel.setEstimated_delivery_from(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_ESTIMATED_DELIVERY_FROM));
		headerInfoTempModel.setEstimated_delivery_to(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_ESTIMATED_DELIVERY_TO));
		headerInfoTempModel.setAgreement_summary(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_AGREEMENT_SUMMARY));
		headerInfoTempModel.setPurchase_category(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_PURCHASE_CATEGORY));
		headerInfoTempModel.setStart_using_date(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_START_USING_DATE));
		headerInfoTempModel
				.setDeprec_month(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_DEPREC_MONTH));
		
		String multiData = getUserParameterValue(userParameter, AgreementFormConstants.FIELD_MULTIDATA);
		headerInfoTempModel.setMultidata(multiData);

		if (multiData.contains("pl")) {
			headerInfoTempModel.setBudget_pl_impact(
					getUserParameterValue(userParameter, AgreementFormConstants.FIELD_BUDGET_PL_IMPACT));
			headerInfoTempModel
					.setBudget_pl_month(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_BUDGET_PL_MONTH));
			headerInfoTempModel.setPl_impact(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_PL_IMPACT));
			headerInfoTempModel.setPl_month(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_PL_MONTH));
		} else {
			headerInfoTempModel.setBudget_pl_impact("");
			headerInfoTempModel.setBudget_pl_month("");
			headerInfoTempModel.setPl_impact("");
			headerInfoTempModel.setPl_month("");
		}

		if (multiData.contains("asset")) {
			headerInfoTempModel
					.setAsset_number(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_ASSET_NUMBER));
			headerInfoTempModel
					.setBook_value(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_BOOK_VALUE));
		} else {
			headerInfoTempModel.setAsset_number("");
			headerInfoTempModel.setBook_value("");
		}

		if (multiData.contains("estimated")) {
			headerInfoTempModel.setTotal_payment_amount(
					getUserParameterValue(userParameter, AgreementFormConstants.FIELD_TOTAL_PAYMENT_AMOUNT));
		} else {
			headerInfoTempModel.setTotal_payment_amount("");
		}
		headerInfoTempModel.setAgreement_classification(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_AGREEMENT_CLASSIFICATION));
		headerInfoTempModel.setPd_sub_condition(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_PD_SUB_CONDITION));
		headerInfoTempModel
				.setEc_approval(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_EC_APPROVAL));
		headerInfoTempModel.setEc_sub_condition(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_EC_SUB_CONDITION));
		headerInfoTempModel.setPsd_area(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_PSD_AREA));
		headerInfoTempModel
				.setPsd_process(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_PSD_PROCESS));
		headerInfoTempModel
				.setDic_reason(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_DIC_REASON));
		headerInfoTempModel
				.setDd_process(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_DD_PROCESS));
		headerInfoTempModel
				.setAnti_bribery(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_ANTI_BRIBERY));
		headerInfoTempModel
				.setAudit_rights(getUserParameterValue(userParameter, AgreementFormConstants.FIELD_AUDIT_RIGHTS));
		headerInfoTempModel.setLegal_agreement_number(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_LEGAL_AGREEMENT_NUMBER));
		headerInfoTempModel.setLegal_agreement_date(
				getUserParameterValue(userParameter, AgreementFormConstants.FIELD_LEGAL_AGREEMENT_DATE));

		return headerInfoTempModel;
	}

	private List<AgreementAttachmentModel> extractAttachmentModels(ActionProcessParameter parameter,
			Map<String, Object> userParameter) {
		List<AgreementAttachmentModel> result = new ArrayList<AgreementAttachmentModel>();

		try {
			List<String> attachmentId = normalizeToList(userParameter.get("f_upload_file_id"));
			List<String> attachmentFileName = normalizeToList(userParameter.get("f_upload_file_name"));
			List<String> attachmentRealName = normalizeToList(userParameter.get("f_upload_file_real_name"));
			List<String> attachmentFileType = normalizeToList(userParameter.get("f_upload_file_type"));
			List<String> attachmentFileSize = normalizeToList(userParameter.get("f_upload_file_size"));
			List<String> attachmentFileExtension = normalizeToList(userParameter.get("f_upload_file_extension"));

			for (int i = 0; i < attachmentId.size(); i++) {
				AgreementAttachmentModel attachmentModel = new AgreementAttachmentModel();

				attachmentModel.setSystem_matter_id(parameter.getSystemMatterId());
				attachmentModel.setUser_data_id(parameter.getUserDataId());
				attachmentModel.setFile_name(getListElementSafely(attachmentFileName, i));
				attachmentModel.setFile_real_name(getListElementSafely(attachmentRealName, i));
				attachmentModel.setFile_type(getListElementSafely(attachmentFileType, i));
				attachmentModel.setFile_size(getListElementSafely(attachmentFileSize, i));
				attachmentModel.setFile_extension(getListElementSafely(attachmentFileExtension, i));

				String file_path = "practice5_bintang/" + parameter.getSystemMatterId() + "/file_attachment/" + attachmentModel.getFile_real_name();
				attachmentModel.setFile_path(file_path);

				if (isValidFileEntity(attachmentModel)) {
					result.add(attachmentModel);
				}

			}
		} catch (Exception e) {

			System.out.println("Error Get Entity File");

		}

		return result;
	}

	private List<AgreementPaymentDetailModel> extractPaymentDetailModels(ActionProcessParameter parameter,
			Map<String, Object> userParameter) {
		List<AgreementPaymentDetailModel> result = new ArrayList<AgreementPaymentDetailModel>();

		if (userParameter == null) {
			return result;
		}

		// Check if multi-data includes estimated schedule
		String multiData = getUserParameterValue(userParameter, AgreementFormConstants.FIELD_MULTIDATA);
		if (!multiData.contains("estimated")) {
			return result;
		}

		int rowNo = 1;
		while (userParameter.containsKey("f_brand_" + rowNo)
				|| userParameter.containsKey("f_payment_amount_" + rowNo)) {

			AgreementPaymentDetailModel paymentDetailModel = new AgreementPaymentDetailModel();
		
			paymentDetailModel.setSystem_matter_id(parameter.getSystemMatterId());
			paymentDetailModel.setUser_data_id(parameter.getUserDataId());
			paymentDetailModel.setRow_no(String.valueOf(rowNo));
			paymentDetailModel.setBrand(getUserParameterValue(userParameter, "f_brand_" + rowNo));
			paymentDetailModel.setType(getUserParameterValue(userParameter, "f_type_" + rowNo));
			paymentDetailModel.setPayment_amount(getUserParameterValue(userParameter, "f_payment_amount_" + rowNo));
			paymentDetailModel.setPayment_date(getUserParameterValue(userParameter, "f_payment_date_" + rowNo));
			paymentDetailModel.setCategory(getUserParameterValue(userParameter, "f_category_" + rowNo));
			paymentDetailModel.setRecurring(getUserParameterValue(userParameter, "f_recurring_" + rowNo));

			Object paidByObj = userParameter.get("f_paid_by_" + rowNo);
			if (paidByObj != null) {
				if (paidByObj instanceof List) {
					@SuppressWarnings("unchecked")
					List<String> paidByList = (List<String>) paidByObj;
					paymentDetailModel.setPaid_by(String.join(",", paidByList));
				} else {
					paymentDetailModel.setPaid_by(paidByObj.toString());
				}
			} else {
				paymentDetailModel.setPaid_by("");
			}

			result.add(paymentDetailModel);
			rowNo++;
		}

		return result;
	}

	@Override
	public String applyFromTempSave(ActionProcessParameter parameter, Map<String, Object> userParameter)
			throws Exception {
		return null;
	}

	@Override
	public String applyFromUnapply(ActionProcessParameter parameter, Map<String, Object> userParameter)
			throws Exception {
		return null;
	}

	@Override
	public void approve(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {
		try {
			AgreementHeaderInfoTempRepository headerInfoTempDb = new AgreementHeaderInfoTempRepository();

			Collection<AgreementHeaderInfoTempModel> headerInfoTempModel = headerInfoTempDb.selectHeaderInfoTemp(
					parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

			if (headerInfoTempModel != null && !headerInfoTempModel.isEmpty()) {
				AgreementHeaderInfoTempModel model = headerInfoTempModel.iterator().next();

				if (userParameter.get("f_psd_area") != null) model.setPsd_area(getUserParameterValue(userParameter, "f_psd_area"));
				if (userParameter.get("f_psd_process") != null) model.setPsd_process(getUserParameterValue(userParameter, "f_psd_process"));
				if (userParameter.get("f_dic_reason") != null) model.setDic_reason(getUserParameterValue(userParameter, "f_dic_reason"));
				if (userParameter.get("f_dd_process") != null) model.setDd_process(getUserParameterValue(userParameter, "f_dd_process"));
				if (userParameter.get("f_anti_bribery") != null) model.setAnti_bribery(getUserParameterValue(userParameter, "f_anti_bribery"));
				if (userParameter.get("f_audit_rights") != null) model.setAudit_rights(getUserParameterValue(userParameter, "f_audit_rights"));
				if (userParameter.get("f_legal_agreement_number") != null) model.setLegal_agreement_number(getUserParameterValue(userParameter, "f_legal_agreement_number"));
				if (userParameter.get("f_legal_agreement_date") != null) model.setLegal_agreement_date(getUserParameterValue(userParameter, "f_legal_agreement_date"));

				headerInfoTempDb.updateHeaderInfoTemp(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void approveEnd(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	@Override
	public void deny(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	@Override
	public void discontinue(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	@Override
	public void matterHandle(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	@Override
	public void pullBack(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	@Override
	public String reapply(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {
		try {
			AgreementWorkflowService service = new AgreementWorkflowService();
			
			AgreementHeaderInfoTempRepository agreementHeaderInfoTempDb = new AgreementHeaderInfoTempRepository();
			AgreementPaymentDetailRepository agreementPaymentDetailDb = new AgreementPaymentDetailRepository();
			AgreementAttachFileRepository agreementAttachDb = new AgreementAttachFileRepository();

			AgreementHeaderInfoTempModel headerInfoTempModel = extractHeaderInfoTempModel(parameter, userParameter);
			List<AgreementPaymentDetailModel> paymentDetailList = extractPaymentDetailModels(parameter, userParameter);
			List<AgreementAttachmentModel> attachmentList = extractAttachmentModels(parameter, userParameter);
			System.out.println(attachmentList.size());

			agreementHeaderInfoTempDb.deleteHeaderInfoTemp(parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);
			agreementPaymentDetailDb.deleteTempPaymentDetail(parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);	
			agreementAttachDb.deleteTempAttachment(parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

			agreementHeaderInfoTempDb.insertHeaderInfoTemp(headerInfoTempModel);
			
			if(headerInfoTempModel.getMultidata().contains("estimated")) {
				for(int i=0; i<paymentDetailList.size(); i++) {
				agreementPaymentDetailDb.insertTempPaymentDetail(paymentDetailList.get(i));
				}
			}

			for (int i = 0; i < attachmentList.size(); i++) {
				agreementAttachDb.insertTempAttachment(attachmentList.get(i));
				service.transferAttachmentFile(parameter.getSystemMatterId(), attachmentList.get(i).getFile_real_name());
			}

			updateMatterProperty(parameter.getUserDataId(), "practice5_bintang_po", headerInfoTempModel.getPo_required());
			updateMatterProperty(parameter.getUserDataId(), "practice5_bintang_ac", headerInfoTempModel.getAgreement_classification());
			updateMatterProperty(parameter.getUserDataId(), "practice5_bintang_ec", headerInfoTempModel.getEc_approval());


		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}
		return null;
	}

	@Override
	public void reserve(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	@Override
	public void reserveCancel(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	@Override
	public void sendBack(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	@Override
	public void sendBackToPullBack(ActionProcessParameter parameter, Map<String, Object> userParameter)
			throws Exception {

	}

	@Override
	public void tempSaveCreate(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	@Override
	public void tempSaveDelete(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	@Override
	public void tempSaveUpdate(ActionProcessParameter parameter, Map<String, Object> userParameter) throws Exception {

	}

	private final void createMatterProperty(final String userDataId, final String matterPropertyKey,
			final String matterPropertyValue) throws WorkflowException {

		final UserMatterPropertyModel matterPropertyModel = new UserMatterPropertyModel();
		matterPropertyModel.setUserDataId(userDataId);
		matterPropertyModel.setMatterPropertyKey(matterPropertyKey);
		matterPropertyModel.setMatterPropertyValue(matterPropertyValue);

		UserActvMatterPropertyValue property;
		property = new UserActvMatterPropertyValue();
		final UserMatterPropertyModel[] matterProperty = new UserMatterPropertyModel[1];
		matterProperty[0] = matterPropertyModel;
		property.createMatterProperty(matterProperty);
	}

	private final void updateMatterProperty(final String userDataId, final String matterPropertyKey,
			final String matterPropertyValue) throws WorkflowException {

		final UserMatterPropertyModel matterPropertyModel = new UserMatterPropertyModel();
		matterPropertyModel.setUserDataId(userDataId);
		matterPropertyModel.setMatterPropertyKey(matterPropertyKey);
		matterPropertyModel.setMatterPropertyValue(matterPropertyValue);

		UserActvMatterPropertyValue property;
		property = new UserActvMatterPropertyValue();
		final UserMatterPropertyModel[] matterProperty = new UserMatterPropertyModel[1];
		matterProperty[0] = matterPropertyModel;
		property.updateMatterProperty(matterProperty);
	}

	@SuppressWarnings("unused")
	private final void deleteMatterProperty(final String userDataId, final String matterPropertyKey)
			throws WorkflowException {

		final UserMatterPropertyModel matterPropertyModel = new UserMatterPropertyModel();
		matterPropertyModel.setUserDataId(userDataId);
		matterPropertyModel.setMatterPropertyKey(matterPropertyKey);

		UserActvMatterPropertyValue property;
		property = new UserActvMatterPropertyValue();
		final UserMatterPropertyModel[] matterProperty = new UserMatterPropertyModel[1];
		matterProperty[0] = matterPropertyModel;
		property.deleteMatterProperty(matterProperty);
	}

	@SuppressWarnings("unused")
	private int parseInteger(String strVal) {
		if (strVal == null || strVal.trim().isEmpty()) {
			return 0;
		}
		try {
			return Integer.parseInt(strVal.trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

		private String getListElementSafely(List<String> list, int index) {
		try {
			return list.get(index);
		} catch (Exception e) {
			return "";
		}
	}

	private String getUserParameterValue(final Map<String, Object> userParameter, String key) {
		// Check userParameter
	    if (userParameter == null) { return ""; }
	    
	    // Check if the data Exists
	    Object rawValue = userParameter.get(key);
	    if (rawValue == null) { return ""; }
	    
	    String resultString = rawValue.toString();
	    return resultString;
	}

	@SuppressWarnings("unchecked")
	private List<String> normalizeToList(Object param) {
		if (param instanceof String) {
			return Collections.singletonList((String) param);
		} else if (param instanceof List) {
			return (List<String>) param;
		} else {
			return new ArrayList<>();
		}
	}

	private boolean isValidFileEntity(AgreementAttachmentModel entity) {
        String name = entity.getFile_name();
        String realName = entity.getFile_real_name();
        
        // Check if the name empty
        if (name == null || name.isEmpty() || "-".equals(name)) {
            return false;
        }
        if (realName == null || realName.isEmpty() || "-".equals(realName)) {
            return false;
        }
        
        return true;
    }

}
