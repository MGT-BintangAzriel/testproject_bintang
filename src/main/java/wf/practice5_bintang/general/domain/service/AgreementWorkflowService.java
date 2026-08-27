package wf.practice5_bintang.general.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import jp.co.intra_mart.foundation.context.Contexts;
import jp.co.intra_mart.foundation.exception.BizApiException;
import jp.co.intra_mart.foundation.master.user.UserManager;
import jp.co.intra_mart.foundation.master.user.model.User;
import jp.co.intra_mart.foundation.master.user.model.UserBizKey;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.file.SessionScopeStorage;
import jp.co.intra_mart.foundation.user_context.model.UserContext;
import jp.co.intra_mart.foundation.workflow.application.general.ActvMatter;
import jp.co.intra_mart.foundation.workflow.application.general.CplMatter;
import wf.common.constant.MatterEndStatus;
import wf.practice5_bintang.general.app.AgreementForm;
import wf.practice5_bintang.general.constant.AgreementFormConstants;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderModel;
import wf.practice5_bintang.general.domain.model.AgreementPaymentDetailModel;
import wf.practice5_bintang.general.domain.repository.AgreementAttachFileRepository;
import wf.practice5_bintang.general.domain.repository.AgreementAttachFileTempRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderInfoRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderInfoTempRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderRepository;
import wf.practice5_bintang.general.domain.repository.AgreementPaymentDetailRepository;
import wf.practice5_bintang.general.domain.repository.AgreementPaymentDetailTempRepository;

public class AgreementWorkflowService {

	public AgreementForm getAgreementFormData(String selectValue, String selectWhere, HttpServletRequest request) throws Exception {
		boolean isMatterComplete = MatterEndStatus.MATTER_COMPLETE.getStatus().equals(getMatterStatus(selectValue));
		return getAgreementFormData(selectValue, selectWhere, request, isMatterComplete);
	}

	public AgreementForm getAgreementFormData(String selectValue, String selectWhere, HttpServletRequest request, boolean isMatterComplete) throws Exception {
		AgreementHeaderRepository agreementHeaderDb = new AgreementHeaderRepository();
		Collection<AgreementHeaderModel> headerList = agreementHeaderDb.selectHeader(selectValue, selectWhere);
		AgreementHeaderModel headerModel = (headerList != null && !headerList.isEmpty()) ? headerList.iterator().next() : null;

		Collection<AgreementHeaderInfoModel> headerInfoModels = null;
		Collection<AgreementPaymentDetailModel> paymentDetailModels = null;
		Collection<AgreementAttachmentModel> attachFileModels = null;

		if (isMatterComplete) {
			AgreementHeaderInfoRepository headerInfoDb = new AgreementHeaderInfoRepository();
			AgreementPaymentDetailRepository agreementPaymentDetailDb = new AgreementPaymentDetailRepository();
			AgreementAttachFileRepository agreementAttachFileDb = new AgreementAttachFileRepository();

			headerInfoModels = headerInfoDb.selectHeaderInfo(selectValue, selectWhere);
			paymentDetailModels = agreementPaymentDetailDb.selectPaymentDetail(selectValue, selectWhere);
			attachFileModels = agreementAttachFileDb.selectAttachment(selectValue, selectWhere);
			
		} else {
			AgreementHeaderInfoTempRepository headerInfoTempDb = new AgreementHeaderInfoTempRepository();
			AgreementPaymentDetailTempRepository agreementPaymentDetailTempDb = new AgreementPaymentDetailTempRepository();
			AgreementAttachFileTempRepository agreementAttachFileTempDb = new AgreementAttachFileTempRepository();

			headerInfoModels = headerInfoTempDb.selectHeaderInfoTemp(selectValue, selectWhere);
			paymentDetailModels = agreementPaymentDetailTempDb.selectPaymentDetailTemp(selectValue, selectWhere);
			attachFileModels = agreementAttachFileTempDb.selectAttachmentTemp(selectValue, selectWhere);
		}

		return buildFormFromModel(headerModel, headerInfoModels, paymentDetailModels, attachFileModels, request);
	}

	private AgreementForm buildFormFromModel(AgreementHeaderModel headerModel, Collection<AgreementHeaderInfoModel> headerInfoModels, Collection<AgreementPaymentDetailModel> paymentDetailModels,
			Collection<AgreementAttachmentModel> attachFileModels, HttpServletRequest request) {

		AgreementForm form = new AgreementForm();
		if (headerInfoModels == null || headerInfoModels.isEmpty()) {
			return form;
		}

		AgreementHeaderInfoModel headerInfoModel = headerInfoModels.iterator().next();

		form.setF_download_token(getDownloadToken(request));

		form.setF_application_number(headerInfoModel.getApplication_number());
		form.setF_application_date(getDateFormat(headerInfoModel.getApplication_date(), "yyyy/MM/dd"));
		form.setF_applicant_number(headerInfoModel.getApplicant_number());
		form.setF_applicant_department(headerInfoModel.getApplicant_department());
		form.setF_applicant_name(headerInfoModel.getApplicant_name());
		form.setF_applicant_post(headerInfoModel.getApplicant_post());

		form.setF_system_matter_id(headerInfoModel.getSystem_matter_id());
		form.setF_user_data_id(headerInfoModel.getUser_data_id());

		form.setF_counter_party(headerInfoModel.getCounter_party());
		form.setF_currency(headerInfoModel.getCurrency());
		form.setF_total_amount(headerInfoModel.getTotal_amount());

		form.setF_agreement_status(headerInfoModel.getAgreement_status());
		form.setF_agreement_status_one_time(isChecked(headerInfoModel.getAgreement_status(), "one_time"));
		form.setF_agreement_status_amendment(isChecked(headerInfoModel.getAgreement_status(), "amendment"));
		form.setF_agreement_status_umbrella(isChecked(headerInfoModel.getAgreement_status(), "umbrella"));

		form.setF_total_duration(headerInfoModel.getTotal_duration());
		form.setF_total_duration_more_than_1_year(isChecked(headerInfoModel.getTotal_duration(), "more_than_1_year"));
		form.setF_total_duration_up_to_1_year(isChecked(headerInfoModel.getTotal_duration(), "up_to_1_year"));

		form.setF_auto_extension(headerInfoModel.getAuto_extension());
		form.setF_auto_extension_yes(isChecked(headerInfoModel.getAuto_extension(), "yes"));
		form.setF_auto_extension_no(isChecked(headerInfoModel.getAuto_extension(), "no"));

		form.setF_po_required(headerInfoModel.getPo_required());
		form.setF_po_required_yes(isChecked(headerInfoModel.getPo_required(), "yes"));
		form.setF_po_required_no(isChecked(headerInfoModel.getPo_required(), "no"));

		form.setF_agreement_title(headerInfoModel.getAgreement_title());
		form.setF_effective_from(getDateFormat(headerInfoModel.getEffective_from(), "yyyy/MM/dd"));
		form.setF_effective_to(getDateFormat(headerInfoModel.getEffective_to(), "yyyy/MM/dd"));

		form.setF_company_relation(headerInfoModel.getCompany_relation());
		form.setF_company_relation_related_parties(isChecked(headerInfoModel.getCompany_relation(), "related_parties"));
		form.setF_company_relation_non_related_parties(isChecked(headerInfoModel.getCompany_relation(), "non_related_parties"));

		form.setF_estimated_delivery_from(getDateFormat(headerInfoModel.getEstimated_delivery_from(), "yyyy/MM/dd"));
		form.setF_estimated_delivery_to(getDateFormat(headerInfoModel.getEstimated_delivery_to(), "yyyy/MM/dd"));
		form.setF_agreement_summary(headerInfoModel.getAgreement_summary());

		form.setF_purchase_category(headerInfoModel.getPurchase_category());
		form.setF_purchase_category_tangible_asset(isChecked(headerInfoModel.getPurchase_category(), "tangible_asset"));
		form.setF_purchase_category_intangible_asset(isChecked(headerInfoModel.getPurchase_category(), "intangible_asset"));
		form.setF_purchase_category_non_asset(isChecked(headerInfoModel.getPurchase_category(), "non_asset"));

		form.setF_start_using_date(getDateFormat(headerInfoModel.getStart_using_date(), "yyyy/MM/dd"));
		form.setF_deprec_month(headerInfoModel.getDeprec_month());

		form.setF_multidata(headerInfoModel.getMultidata());
		form.setF_multidata_pl(isMultiChecked(headerInfoModel.getMultidata(), "pl"));
		form.setF_multidata_asset(isMultiChecked(headerInfoModel.getMultidata(), "asset"));
		form.setF_multidata_estimated(isMultiChecked(headerInfoModel.getMultidata(), "estimated"));

		form.setF_budget_pl_impact(headerInfoModel.getBudget_pl_impact());
		form.setF_budget_pl_month(headerInfoModel.getBudget_pl_month());
		form.setF_pl_impact(headerInfoModel.getPl_impact());
		form.setF_pl_month(headerInfoModel.getPl_month());
		form.setF_asset_number(headerInfoModel.getAsset_number());
		form.setF_book_value(headerInfoModel.getBook_value());
		form.setF_total_payment_amount(headerInfoModel.getTotal_payment_amount());

		form.setF_agreement_classification(headerInfoModel.getAgreement_classification());
		form.setF_agreement_classification_pd(isChecked(headerInfoModel.getAgreement_classification(), "pd"));
		form.setF_agreement_classification_dic_director_approval(isChecked(headerInfoModel.getAgreement_classification(), "dic_director_approval"));

		form.setF_pd_sub_condition(headerInfoModel.getPd_sub_condition());
		form.setF_agreement_classification_pd_more_than_1_billion(isChecked(headerInfoModel.getPd_sub_condition(), "pd_more_than_1_billion"));
		form.setF_agreement_classification_pd_more_than_12_months(isChecked(headerInfoModel.getPd_sub_condition(), "pd_more_than_12_months"));
		form.setF_agreement_classification_pd_specific_party(isChecked(headerInfoModel.getPd_sub_condition(), "pd_specific_party"));
		form.setF_agreement_classification_pd_special_issue(isChecked(headerInfoModel.getPd_sub_condition(), "pd_special_issue"));
		form.setF_agreement_classification_pd_direct_procurement(isChecked(headerInfoModel.getPd_sub_condition(), "pd_direct_procurement"));
		form.setF_agreement_classification_pd_agreement_not_more_than_12_months(isChecked(headerInfoModel.getPd_sub_condition(), "pd_agreement_not_more_than_12_months"));

		form.setF_ec_approval(headerInfoModel.getEc_approval());
		form.setF_agreement_classification_ec_approval_yes(isChecked(headerInfoModel.getEc_approval(), "yes"));
		form.setF_agreement_classification_ec_approval_no(isChecked(headerInfoModel.getEc_approval(), "no"));

		form.setF_ec_sub_condition(headerInfoModel.getEc_sub_condition());
		form.setF_agreement_classification_ec_amount_equal_more_than_1_billion(isChecked(headerInfoModel.getEc_sub_condition(), "ec_amount_equal_more_than_1_billion"));
		form.setF_agreement_classification_ec_period_equal_more_than_12_months(isChecked(headerInfoModel.getEc_sub_condition(), "ec_period_equal_more_than_12_months"));
		form.setF_agreement_classification_ec_escalate_issue_to_ec(isChecked(headerInfoModel.getEc_sub_condition(), "ec_escalate_issue_to_ec"));

		form.setF_psd_area(headerInfoModel.getPsd_area());
		form.setF_psd_area_psd(isChecked(headerInfoModel.getPsd_area(), "psd"));
		form.setF_psd_area_non_psd(isChecked(headerInfoModel.getPsd_area(), "non_psd"));

		form.setF_psd_process(headerInfoModel.getPsd_process());
		form.setF_psd_process_psd(isChecked(headerInfoModel.getPsd_process(), "psd"));
		form.setF_psd_process_dic(isChecked(headerInfoModel.getPsd_process(), "dic"));
		form.setF_dic_reason(headerInfoModel.getDic_reason());

		form.setF_dd_process(headerInfoModel.getDd_process());
		form.setF_dd_process_yes(isChecked(headerInfoModel.getDd_process(), "yes"));
		form.setF_dd_process_no(isChecked(headerInfoModel.getDd_process(), "no"));

		form.setF_anti_bribery(headerInfoModel.getAnti_bribery());
		form.setF_anti_bribery_yes(isChecked(headerInfoModel.getAnti_bribery(), "yes"));
		form.setF_anti_bribery_no(isChecked(headerInfoModel.getAnti_bribery(), "no"));

		form.setF_audit_rights(headerInfoModel.getAudit_rights());
		form.setF_audit_rights_yes(isChecked(headerInfoModel.getAudit_rights(), "yes"));
		form.setF_audit_rights_no(isChecked(headerInfoModel.getAudit_rights(), "no"));

		form.setF_legal_agreement_number(headerInfoModel.getLegal_agreement_number());
		form.setF_legal_agreement_date(getDateFormat(headerInfoModel.getLegal_agreement_date(), "yyyy/MM/dd"));

		form.setD_list_attachment(convertAttachmentFiles(attachFileModels));
		form.setD_list_payment_detail(convertPayment(paymentDetailModels));

		return form;

	}

	private String getDownloadToken(HttpServletRequest request) {
		String downloadToken = UUID.randomUUID().toString();
		request.getSession().setAttribute(AgreementFormConstants.SESSION_KEY_DOWNLOAD_TOKEN, downloadToken);
		return downloadToken;
	}

	private Collection<AgreementPaymentDetailModel> convertPayment(Collection<AgreementPaymentDetailModel> paymentDetailModels) {

		Collection<AgreementPaymentDetailModel> converted = new ArrayList<>();

		for (AgreementPaymentDetailModel row : paymentDetailModels) {
			row.setPayment_date(getDateFormat(row.getPayment_date(), "yyyy/MM/dd"));
			row.setRecurring_yes(isChecked(row.getRecurring(), "yes"));
			row.setRecurring_no(isChecked(row.getRecurring(), "no"));
			row.setPaid_by_cash(isMultiChecked(row.getPaid_by(), "cash"));
			row.setPaid_by_card(isMultiChecked(row.getPaid_by(), "card"));

			converted.add(row);
		}
		return converted;
	}

	private String isChecked(String actualValue, String expectedValue) {
		if (expectedValue.equals(actualValue)) {
			return "checked";
		}
		return "";
	}

	private String isMultiChecked(String actualValue, String expectedValue) {
		if (actualValue != null && actualValue.contains(expectedValue)) {
			return "checked";
		}
		return "";
	}

	public String getDateFormat(String date, String date_format) {
		try {
			if (date == null) {
				return "";
			}

			Date date_old = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			String date_fix = new SimpleDateFormat(date_format).format(date_old);

			return date_fix;
		} catch (ParseException e) {
			System.out.println(e);
			return "";
		}
	}

	public final Boolean transferAttachmentFile(String systemMatterId, String fileRealName) {
		String dirPath = AgreementFormConstants.STORAGE_DIR_PRACTICE5 + "/" + systemMatterId + "/" + AgreementFormConstants.STORAGE_DIR_FILE_ATTACHMENT;

		PublicStorage targetDir = new PublicStorage(dirPath);
		PublicStorage targetFile = new PublicStorage(dirPath + "/" + fileRealName);
		SessionScopeStorage sessionStorageFile = new SessionScopeStorage(AgreementFormConstants.STORAGE_DIR_FILE_ATTACHMENT + "/" + fileRealName);
		try {
			targetDir.makeDirectories();

			if (!targetFile.isFile()) {
				targetFile.save(org.apache.commons.io.IOUtils.toByteArray(sessionStorageFile.open()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public AgreementForm getHeaderInfoTempFormApply() {
		UserContext userContext = Contexts.get(UserContext.class);

		AgreementForm agreementForm = new AgreementForm();
		AgreementHeaderRepository headerDb = new AgreementHeaderRepository();

		AgreementHeaderModel entityHeaderMaxId = new AgreementHeaderModel();
		try {
			entityHeaderMaxId = headerDb.selectAgreementHeaderMaxId();
		} catch (Exception e) {
			e.printStackTrace();
		}

		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String formattedDate = today.format(formatter);

		agreementForm.setF_application_number("AD-" + String.format("%06d", (entityHeaderMaxId.getId() + 1)));
		agreementForm.setF_application_date(formattedDate);
		agreementForm.setF_applicant_number(userContext.getUserProfile().getUserCd());
		agreementForm.setF_applicant_name(userContext.getUserProfile().getUserName());
		agreementForm.setF_applicant_department("人事部");
		agreementForm.setF_applicant_post("Intramart");

		return agreementForm;
	}

	public static String getFileExtensionCategory(String mimeType) {
		// This function will decide group document
		if (mimeType == null || mimeType.equals("")) {
			return "UNKNOWN";
		}

		String mime = mimeType.trim().toLowerCase();

		if (mime.startsWith("image/")) {
			return "IMAGE";
		} else if (mime.startsWith("video/")) {
			return "VIDEO";
		} else if (mime.startsWith("audio/")) {
			return "AUDIO";
		} else if (mime.contains("zip") || mime.contains("compressed") || mime.contains("tar")) {
			return "ARCHIVE";
		}

		// Default fallback for general files, office files, and text documents
		return "DOCUMENT";
	}

	public static String getFileExtension(String mimeType) {
		// This function will change the mime type to proper extension
		if (mimeType == null || mimeType.equals("")) {
			return "UNKNOWN";
		}

		String mime = mimeType.trim().toLowerCase();

		// 1. Direct matching for tricky/complex office and text types
		if (mime.equals("text/plain"))
			return "TXT";
		if (mime.contains("msword") || mime.contains("wordprocessingml"))
			return "DOCX";
		if (mime.contains("ms-excel") || mime.contains("spreadsheetml"))
			return "XLSX";
		if (mime.contains("ms-powerpoint") || mime.contains("presentationml"))
			return "PPTX";

		// 2. Fallback rule for standard types (extracts everything after the
		// '/')
		if (mime.contains("/")) {
			String subType = mime.substring(mime.indexOf("/") + 1).toUpperCase();

			if (subType.startsWith("X-"))
				subType = subType.substring(2);
			if (subType.contains("VND."))
				subType = subType.substring(subType.lastIndexOf(".") + 1);
			if (subType.contains(";"))
				subType = subType.split(";")[0].trim();

			return subType;
		}

		return "UNKNOWN";
	}

	public String formatFileSize(long bytes) {
		// This function will convert 1000 byte to GB, MB, KB
		if (bytes < 0) {
			throw new IllegalArgumentException("Byte count cannot be negative.");
		}

		// Define our units based on the 1000-byte standard
		double kilo = 1000.0;
		double mega = 1000.0 * 1000.0;
		double giga = 1000.0 * 1000.0 * 1000.0;

		DecimalFormat df = new DecimalFormat("0.00");

		if (bytes >= giga) {
			double value = bytes / giga;
			return df.format(round(value)) + " GB";

		} else if (bytes >= mega) {
			double value = bytes / mega;
			return df.format(round(value)) + " MB";

		} else if (bytes >= kilo) {
			double value = bytes / kilo;
			return df.format(round(value)) + " KB";

		} else {
			return bytes + " Bytes";

		}
	}

	private double round(double value) {
		// Round up value
		return new BigDecimal(Double.toString(value)).setScale(2, RoundingMode.HALF_UP).doubleValue();

	}

	public Collection<AgreementAttachmentModel> convertAttachmentFiles(Collection<AgreementAttachmentModel> attachments) {
		// This function, will set the extension category & file size data
		Collection<AgreementAttachmentModel> result = new ArrayList<>();

		for (AgreementAttachmentModel attachment : attachments) {
			String originalFileExtension = attachment.getFile_extension();

			attachment.setFile_extension_category(getFileExtensionCategory(originalFileExtension));
			attachment.setFile_extension_convert(getFileExtension(originalFileExtension));

			String attachmentFileSize = attachment.getFile_size() != null ? attachment.getFile_size() : "0";
			attachment.setFile_size_convert(formatFileSize(Long.parseLong(attachmentFileSize)));

			result.add(attachment);
		}

		return result;
	}

	public String getMatterStatus(String systemMatterId) {
		try {
			CplMatter cplMatter = new CplMatter(systemMatterId);
			return cplMatter.getMatter().getStatus();
		} catch (Exception cplEx) {
		}
		return "";
	}

	public Map<String, String> getMatterData(String matterId) {
		Map<String, String> matterData = new HashMap<String, String>();

		String matterNumber = "";
		String matterName = "";
		String matterDatetime = "";
		String matterDate = "";
		String matterApplicantCode = "";
		String recipientEmail = "emailnotfound@gmail.com";

		try {
			try {
				CplMatter cplMatter = new CplMatter(matterId);
				if (cplMatter.getMatter() != null) {
					matterNumber = cplMatter.getMatter().getMatterNumber();
					matterName = cplMatter.getMatter().getMatterName();
					matterDatetime = cplMatter.getMatter().getApplyDate();
					matterApplicantCode = cplMatter.getMatter().getApplyAuthUserCode();
				} else {
					throw new Exception("CplMatter.getMatter() is null");
				}
			} catch (Exception e) {
				System.out.println("Matter data is generated using active matter object");
				try {
					ActvMatter actvMatter = new ActvMatter(matterId);
					if (actvMatter.getMatter() != null) {
						matterNumber = actvMatter.getMatter().getMatterNumber();
						matterName = actvMatter.getMatter().getMatterName();
						matterDatetime = actvMatter.getMatter().getApplyDate();
						matterApplicantCode = actvMatter.getMatter().getApplyAuthUserCode();
					} else {
						throw new Exception("ActvMatter.getMatter() is null");
					}
				} catch (Exception e1) {
					System.out.println("Error on matter object retrieval: " + e.getMessage());
				}
			}

			if (matterDatetime != null && matterDatetime.contains(" ")) {
				matterDate = matterDatetime.split(" ")[0];
			} else {
				matterDate = matterDatetime != null ? matterDatetime : "";
			}

			try {
				UserManager userManager = new UserManager();
				UserBizKey userBizKey = new UserBizKey();
				userBizKey.setUserCd(matterApplicantCode);

				User user = userManager.getUser(userBizKey, new Date());

				if (user != null && user.getEmailAddress1() != null && !user.getEmailAddress1().isEmpty()) {
					recipientEmail = user.getEmailAddress1();
				}
			} catch (BizApiException e) {
				System.out.println("Error on email address retrieval: " + e.getMessage());
			}
		} catch (Exception e) {
			System.out.println("Error on matter data retrieval: " + e.getMessage());
		}

		matterData.put("matterNumber", matterNumber);
		matterData.put("matterName", matterName);
		matterData.put("matterDate", matterDate);
		matterData.put("applicantCode", matterApplicantCode);
		matterData.put("recipientEmail", recipientEmail);

		return matterData;
	}
}
