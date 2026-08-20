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

import jp.co.intra_mart.foundation.context.Contexts;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.file.SessionScopeStorage;
import jp.co.intra_mart.foundation.user_context.model.UserContext;
import wf.practice5_bintang.general.app.AgreementForm;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderModel;
import wf.practice5_bintang.general.domain.model.AgreementPaymentDetailModel;
import wf.practice5_bintang.general.domain.repository.AgreementAttachFileTempRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderInfoTempRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderRepository;
import wf.practice5_bintang.general.domain.repository.AgreementPaymentDetailTempRepository;

public class AgreementWorkflowService {

	public AgreementForm getHeaderInfoTempForm(String selectValue, String selectWhere) throws Exception {
		AgreementHeaderRepository agreementHeaderDb = new AgreementHeaderRepository();
		AgreementHeaderInfoTempRepository headerInfoTempDb = new AgreementHeaderInfoTempRepository();
		AgreementPaymentDetailTempRepository agreementPaymentDetailTempDb = new AgreementPaymentDetailTempRepository();
		AgreementAttachFileTempRepository agreementAttachFileTempDb = new AgreementAttachFileTempRepository();

		Collection<AgreementHeaderModel> headerList = agreementHeaderDb.selectHeader(selectValue, selectWhere);
		AgreementHeaderModel headerModel = (headerList != null && !headerList.isEmpty()) ? headerList.iterator().next() : null;

		Collection<AgreementHeaderInfoModel> headerInfoTempModels = headerInfoTempDb.selectHeaderInfoTemp(selectValue, selectWhere);
		Collection<AgreementPaymentDetailModel> paymentDetailModels = agreementPaymentDetailTempDb.selectPaymentDetailTemp(selectValue, selectWhere);
		Collection<AgreementAttachmentModel> attachFileModels = agreementAttachFileTempDb.selectAttachmentTemp(selectValue, selectWhere);

		return buildFormFromTempModel(headerModel, headerInfoTempModels, paymentDetailModels, attachFileModels);
	}

	private AgreementForm buildFormFromTempModel(AgreementHeaderModel headerModel, Collection<AgreementHeaderInfoModel> headerInfoTempModels, Collection<AgreementPaymentDetailModel> paymentDetailModels,
			Collection<AgreementAttachmentModel> attachFileModels) {

		AgreementForm form = new AgreementForm();
		if (headerInfoTempModels == null || headerInfoTempModels.isEmpty()) {
			return form;
		}

		AgreementHeaderInfoModel headerInfoTempModel = headerInfoTempModels.iterator().next();

		form.setF_application_number(headerInfoTempModel.getApplication_number());
		form.setF_application_date(getDateFormat(headerInfoTempModel.getApplication_date(), "yyyy/MM/dd"));
		form.setF_applicant_number(headerInfoTempModel.getApplicant_number());
		form.setF_applicant_department(headerInfoTempModel.getApplicant_department());
		form.setF_applicant_name(headerInfoTempModel.getApplicant_name());
		form.setF_applicant_post(headerInfoTempModel.getApplicant_post());

		form.setF_system_matter_id(headerInfoTempModel.getSystem_matter_id());
		form.setF_user_data_id(headerInfoTempModel.getUser_data_id());

		form.setF_counter_party(headerInfoTempModel.getCounter_party());
		form.setF_currency(headerInfoTempModel.getCurrency());
		form.setF_total_amount(headerInfoTempModel.getTotal_amount());

		form.setF_agreement_status(headerInfoTempModel.getAgreement_status());
		if (headerInfoTempModel.getAgreement_status() != null) {
			if (headerInfoTempModel.getAgreement_status().contains("one_time")) {
				form.setF_agreement_status_one_time("checked");
			}
			if (headerInfoTempModel.getAgreement_status().contains("amendment")) {
				form.setF_agreement_status_amendment("checked");
			}
			if (headerInfoTempModel.getAgreement_status().contains("umbrella")) {
				form.setF_agreement_status_umbrella("checked");
			}
		}

		form.setF_total_duration(headerInfoTempModel.getTotal_duration());
		if (headerInfoTempModel.getTotal_duration() != null) {
			if (headerInfoTempModel.getTotal_duration().contains("more_than_1_year")) {
				form.setF_total_duration_more_than_1_year("checked");
			}
			if (headerInfoTempModel.getTotal_duration().contains("up_to_1_year")) {
				form.setF_total_duration_up_to_1_year("checked");
			}
		}

		form.setF_auto_extension(headerInfoTempModel.getAuto_extension());
		if (headerInfoTempModel.getAuto_extension() != null) {
			if (headerInfoTempModel.getAuto_extension().contains("yes")) {
				form.setF_auto_extension_yes("checked");
			}
			if (headerInfoTempModel.getAuto_extension().contains("no")) {
				form.setF_auto_extension_no("checked");
			}
		}

		form.setF_po_required(headerInfoTempModel.getPo_required());
		if (headerInfoTempModel.getPo_required() != null) {
			if (headerInfoTempModel.getPo_required().contains("yes")) {
				form.setF_po_required_yes("checked");
			}
			if (headerInfoTempModel.getPo_required().contains("no")) {
				form.setF_po_required_no("checked");
			}
		}

		form.setF_agreement_title(headerInfoTempModel.getAgreement_title());
		form.setF_effective_from(getDateFormat(headerInfoTempModel.getEffective_from(), "yyyy/MM/dd"));
		form.setF_effective_to(getDateFormat(headerInfoTempModel.getEffective_to(), "yyyy/MM/dd"));

		form.setF_company_relation(headerInfoTempModel.getCompany_relation());
		if (headerInfoTempModel.getCompany_relation() != null) {
			if (headerInfoTempModel.getCompany_relation().contains("related_parties")) {
				form.setF_company_relation_related_parties("checked");
			}
			if (headerInfoTempModel.getCompany_relation().contains("non_related_parties")) {
				form.setF_company_relation_non_related_parties("checked");
			}
		}

		form.setF_estimated_delivery_from(getDateFormat(headerInfoTempModel.getEstimated_delivery_from(), "yyyy/MM/dd"));
		form.setF_estimated_delivery_to(getDateFormat(headerInfoTempModel.getEstimated_delivery_to(), "yyyy/MM/dd"));
		form.setF_agreement_summary(headerInfoTempModel.getAgreement_summary());

		form.setF_purchase_category(headerInfoTempModel.getPurchase_category());
		if (headerInfoTempModel.getPurchase_category() != null) {
			if (headerInfoTempModel.getPurchase_category().contains("tangible_asset")) {
				form.setF_purchase_category_tangible_asset("checked");
			}
			if (headerInfoTempModel.getPurchase_category().contains("intangible_asset")) {
				form.setF_purchase_category_intangible_asset("checked");
			}
			if (headerInfoTempModel.getPurchase_category().contains("non_asset")) {
				form.setF_purchase_category_non_asset("checked");
			}
		}

		form.setF_start_using_date(getDateFormat(headerInfoTempModel.getStart_using_date(), "yyyy/MM/dd"));
		form.setF_deprec_month(headerInfoTempModel.getDeprec_month());

		form.setF_multidata(headerInfoTempModel.getMultidata());
		if (headerInfoTempModel.getMultidata() != null) {
			if (headerInfoTempModel.getMultidata().contains("pl")) {
				form.setF_multidata_pl("checked");
			}
			if (headerInfoTempModel.getMultidata().contains("asset")) {
				form.setF_multidata_asset("checked");
			}
			if (headerInfoTempModel.getMultidata().contains("estimated")) {
				form.setF_multidata_estimated("checked");
			}
		}

		form.setF_budget_pl_impact(headerInfoTempModel.getBudget_pl_impact());
		form.setF_budget_pl_month(headerInfoTempModel.getBudget_pl_month());
		form.setF_pl_impact(headerInfoTempModel.getPl_impact());
		form.setF_pl_month(headerInfoTempModel.getPl_month());
		form.setF_asset_number(headerInfoTempModel.getAsset_number());
		form.setF_book_value(headerInfoTempModel.getBook_value());
		form.setF_total_payment_amount(headerInfoTempModel.getTotal_payment_amount());

		form.setF_agreement_classification(headerInfoTempModel.getAgreement_classification());
		if ("pd".equals(headerInfoTempModel.getAgreement_classification())) {
			form.setF_agreement_classification_pd("checked");
		}
		if ("dic_director_approval".equals(headerInfoTempModel.getAgreement_classification())) {
			form.setF_agreement_classification_dic_director_approval("checked");
		}

		form.setF_pd_sub_condition(headerInfoTempModel.getPd_sub_condition());
		if (headerInfoTempModel.getPd_sub_condition() != null) {
			if (headerInfoTempModel.getPd_sub_condition().contains("more_than_1_billion")) {
				form.setF_agreement_classification_pd_more_than_1_billion("checked");
			}
			if (headerInfoTempModel.getPd_sub_condition().contains("more_than_12_months")) {
				form.setF_agreement_classification_pd_more_than_12_months("checked");
			}
			if (headerInfoTempModel.getPd_sub_condition().contains("specific_party")) {
				form.setF_agreement_classification_pd_specific_party("checked");
			}
			if (headerInfoTempModel.getPd_sub_condition().contains("special_issue")) {
				form.setF_agreement_classification_pd_special_issue("checked");
			}
			if (headerInfoTempModel.getPd_sub_condition().contains("direct_procurement")) {
				form.setF_agreement_classification_pd_direct_procurement("checked");
			}
			if (headerInfoTempModel.getPd_sub_condition().contains("agreement_not_more_than_12_months")) {
				form.setF_agreement_classification_pd_agreement_not_more_than_12_months("checked");
			}
		}

		form.setF_ec_approval(headerInfoTempModel.getEc_approval());
		if ("yes".equals(headerInfoTempModel.getEc_approval())) {
			form.setF_agreement_classification_ec_approval_yes("checked");
		}
		if ("no".equals(headerInfoTempModel.getEc_approval())) {
			form.setF_agreement_classification_ec_approval_no("checked");
		}

		form.setF_ec_sub_condition(headerInfoTempModel.getEc_sub_condition());
		if (headerInfoTempModel.getEc_sub_condition() != null) {
			if (headerInfoTempModel.getEc_sub_condition().contains("amount_equal_more_than_1_billion")) {
				form.setF_agreement_classification_ec_amount_equal_more_than_1_billion("checked");
			}
			if (headerInfoTempModel.getEc_sub_condition().contains("period_equal_more_than_12_months")) {
				form.setF_agreement_classification_ec_period_equal_more_than_12_months("checked");
			}
			if (headerInfoTempModel.getEc_sub_condition().contains("escalate_issue_to_ec")) {
				form.setF_agreement_classification_ec_escalate_issue_to_ec("checked");
			}
		}

		form.setF_psd_area(headerInfoTempModel.getPsd_area());
		if (headerInfoTempModel.getPsd_area() != null) {
			if (headerInfoTempModel.getPsd_area().contains("psd")) {
				form.setF_psd_area_psd("checked");
			}
			if (headerInfoTempModel.getPsd_area().contains("non_psd")) {
				form.setF_psd_area_non_psd("checked");
			}
		}

		form.setF_psd_process(headerInfoTempModel.getPsd_process());
		if (headerInfoTempModel.getPsd_process() != null) {
			if (headerInfoTempModel.getPsd_process().contains("psd")) {
				form.setF_psd_process_psd("checked");
			}
			if (headerInfoTempModel.getPsd_process().contains("dic")) {
				form.setF_psd_process_dic("checked");
			}
		}
		form.setF_dic_reason(headerInfoTempModel.getDic_reason());

		form.setF_dd_process(headerInfoTempModel.getDd_process());
		if (headerInfoTempModel.getDd_process() != null) {
			if (headerInfoTempModel.getDd_process().contains("yes")) {
				form.setF_dd_process_yes("checked");
			}
			if (headerInfoTempModel.getDd_process().contains("no")) {
				form.setF_dd_process_no("checked");
			}
		}

		form.setF_anti_bribery(headerInfoTempModel.getAnti_bribery());
		if (headerInfoTempModel.getAnti_bribery() != null) {
			if (headerInfoTempModel.getAnti_bribery().contains("yes")) {
				form.setF_anti_bribery_yes("checked");
			}
			if (headerInfoTempModel.getAnti_bribery().contains("no")) {
				form.setF_anti_bribery_no("checked");
			}
		}

		form.setF_audit_rights(headerInfoTempModel.getAudit_rights());
		if (headerInfoTempModel.getAudit_rights() != null) {
			if (headerInfoTempModel.getAudit_rights().contains("yes")) {
				form.setF_audit_rights_yes("checked");
			}
			if (headerInfoTempModel.getAudit_rights().contains("no")) {
				form.setF_audit_rights_no("checked");
			}
		}

		form.setF_legal_agreement_number(headerInfoTempModel.getLegal_agreement_number());
		form.setF_legal_agreement_date(getDateFormat(headerInfoTempModel.getLegal_agreement_date(), "yyyy/MM/dd"));

		form.setD_list_attachment(convertAttachmentFiles(attachFileModels));
		form.setD_list_payment_detail(convertPayment(paymentDetailModels));

		return form;

	}

	private Collection<AgreementPaymentDetailModel> convertPayment(Collection<AgreementPaymentDetailModel> paymentDetailModels) {

		Collection<AgreementPaymentDetailModel> converted = new ArrayList<>();

		for (AgreementPaymentDetailModel row : paymentDetailModels) {
			row.setPayment_date(getDateFormat(row.getPayment_date(), "yyyy/MM/dd"));

			if ("yes".equals(row.getRecurring())) {
				row.setRecurring_yes("checked");
			} else if ("no".equals(row.getRecurring())) {
				row.setRecurring_no("checked");
			}

			if (row.getPaid_by() != null) {
				if (row.getPaid_by().contains("cash")) {
					row.setPaid_by_cash("checked");
				}
				if (row.getPaid_by().contains("card")) {
					row.setPaid_by_card("checked");
				}
			}

			converted.add(row);
		}
		return converted;
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
		PublicStorage targetDir = new PublicStorage("practice5_bintang/" + systemMatterId + "/file_attachment");
		PublicStorage targetFile = new PublicStorage("practice5_bintang/" + systemMatterId + "/file_attachment/" + fileRealName);
		SessionScopeStorage sessionStorageFile = new SessionScopeStorage("file_attachment/" + fileRealName);
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

		// UserContext userContext = Contexts.get(UserContext.class);
		// String applicantNumber = userContext.getUserProfile().getUserCd() !=
		// null ? userContext.getUserProfile().getUserCd() : "1200002";
		// String applicantName = userContext.getUserProfile().getUserName() !=
		// null ? userContext.getUserProfile().getUserName() : "Bintang";
		// String applicantDepartment =
		// userContext.getCurrentDepartment().getDepartmentName() != null ?
		// userContext.getCurrentDepartment().getDepartmentName() : "人事部";

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

}
