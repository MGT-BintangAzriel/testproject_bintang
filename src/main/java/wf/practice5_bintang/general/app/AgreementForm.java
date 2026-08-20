package wf.practice5_bintang.general.app;

import java.util.Collection;

import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementPaymentDetailModel;

public class AgreementForm extends AgreementWorkflowForm {

	private String f_id;
	private String f_system_matter_id;
	private String f_user_data_id;
	
	private String f_application_number;
	private String f_application_date;
	private String f_applicant_number;
	private String f_applicant_department;
	private String f_applicant_name;
	private String f_applicant_post;

	private String f_counter_party;
	private String f_currency;
	private String f_total_amount;

	private String f_agreement_status;
	private String f_agreement_status_one_time;
	private String f_agreement_status_amendment;
	private String f_agreement_status_umbrella;
	
	private String f_total_duration;
	private String f_total_duration_more_than_1_year;
	private String f_total_duration_up_to_1_year;
	
	private String f_auto_extension;
	private String f_auto_extension_yes;
	private String f_auto_extension_no;
	
	private String f_po_required;
	private String f_po_required_yes;
	private String f_po_required_no;

	private String f_agreement_title;
	private String f_effective_from;
	private String f_effective_to;

	private String f_company_relation;
	private String f_company_relation_related_parties;
	private String f_company_relation_non_related_parties;

	private String f_estimated_delivery_from;
	private String f_estimated_delivery_to;
	private String f_agreement_summary;

	private String f_purchase_category;
	private String f_purchase_category_tangible_asset;
	private String f_purchase_category_intangible_asset;
	private String f_purchase_category_non_asset;

	private String f_start_using_date;
	private String f_deprec_month;

	private String f_multidata;
	private String f_multidata_pl;
	private String f_multidata_asset;
	private String f_multidata_estimated;

	private String f_budget_pl_impact;
	private String f_budget_pl_month;
	private String f_pl_impact;
	private String f_pl_month;
	private String f_asset_number;
	private String f_book_value;
	private String f_total_payment_amount;

	private String f_agreement_classification;
	private String f_pd_sub_condition;

	private String f_agreement_classification_pd;
	private String f_agreement_classification_pd_more_than_1_billion;
	private String f_agreement_classification_pd_more_than_12_months;
	private String f_agreement_classification_pd_specific_party;
	private String f_agreement_classification_pd_special_issue;
	private String f_agreement_classification_pd_direct_procurement;
	private String f_agreement_classification_pd_agreement_not_more_than_12_months;
	private String f_agreement_classification_dic_director_approval;

	private String f_ec_approval;
	private String f_agreement_classification_ec_approval_yes;
	private String f_agreement_classification_ec_amount_equal_more_than_1_billion;
	private String f_agreement_classification_ec_period_equal_more_than_12_months;
	private String f_agreement_classification_ec_escalate_issue_to_ec;
	private String f_agreement_classification_ec_approval_no;
	private String f_ec_sub_condition;

	private String f_psd_area;
	private String f_psd_area_psd;
	private String f_psd_area_non_psd;

	private String f_psd_process;
	private String f_psd_process_psd;
	private String f_psd_process_dic;
	private String f_dic_reason;

	private String f_dd_process;
	private String f_dd_process_yes;
	private String f_dd_process_no;

	private String f_anti_bribery;
	private String f_anti_bribery_yes;
	private String f_anti_bribery_no;

	private String f_audit_rights;
	private String f_audit_rights_yes;
	private String f_audit_rights_no;

	private String f_legal_agreement_number;
	private String f_legal_agreement_date;

	private Collection<AgreementAttachmentModel> d_list_attachment;
	private Collection<AgreementPaymentDetailModel> d_list_payment_detail;

	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}

	public String getF_system_matter_id() {
		return f_system_matter_id;
	}

	public void setF_system_matter_id(String f_system_matter_id) {
		this.f_system_matter_id = f_system_matter_id;
	}

	public String getF_user_data_id() {
		return f_user_data_id;
	}

	public void setF_user_data_id(String f_user_data_id) {
		this.f_user_data_id = f_user_data_id;
	}

	public String getF_counter_party() {
		return f_counter_party;
	}

	public void setF_counter_party(String f_counter_party) {
		this.f_counter_party = f_counter_party;
	}

	public String getF_currency() {
		return f_currency;
	}

	public void setF_currency(String f_currency) {
		this.f_currency = f_currency;
	}

	public String getF_total_amount() {
		return f_total_amount;
	}

	public void setF_total_amount(String f_total_amount) {
		this.f_total_amount = f_total_amount;
	}

	public String getF_agreement_status() {
		return f_agreement_status;
	}

	public void setF_agreement_status(String f_agreement_status) {
		this.f_agreement_status = f_agreement_status;
	}

	public String getF_total_duration() {
		return f_total_duration;
	}

	public void setF_total_duration(String f_total_duration) {
		this.f_total_duration = f_total_duration;
	}

	public String getF_auto_extension() {
		return f_auto_extension;
	}

	public void setF_auto_extension(String f_auto_extension) {
		this.f_auto_extension = f_auto_extension;
	}

	public String getF_po_required() {
		return f_po_required;
	}

	public void setF_po_required(String f_po_required) {
		this.f_po_required = f_po_required;
	}

	public String getF_agreement_title() {
		return f_agreement_title;
	}

	public void setF_agreement_title(String f_agreement_title) {
		this.f_agreement_title = f_agreement_title;
	}

	public String getF_effective_from() {
		return f_effective_from;
	}

	public void setF_effective_from(String f_effective_from) {
		this.f_effective_from = f_effective_from;
	}

	public String getF_effective_to() {
		return f_effective_to;
	}

	public void setF_effective_to(String f_effective_to) {
		this.f_effective_to = f_effective_to;
	}

	public String getF_company_relation() {
		return f_company_relation;
	}

	public void setF_company_relation(String f_company_relation) {
		this.f_company_relation = f_company_relation;
	}

	public String getF_estimated_delivery_from() {
		return f_estimated_delivery_from;
	}

	public void setF_estimated_delivery_from(String f_estimated_delivery_from) {
		this.f_estimated_delivery_from = f_estimated_delivery_from;
	}

	public String getF_estimated_delivery_to() {
		return f_estimated_delivery_to;
	}

	public void setF_estimated_delivery_to(String f_estimated_delivery_to) {
		this.f_estimated_delivery_to = f_estimated_delivery_to;
	}

	public String getF_agreement_summary() {
		return f_agreement_summary;
	}

	public void setF_agreement_summary(String f_agreement_summary) {
		this.f_agreement_summary = f_agreement_summary;
	}

	public String getF_purchase_category() {
		return f_purchase_category;
	}

	public void setF_purchase_category(String f_purchase_category) {
		this.f_purchase_category = f_purchase_category;
	}

	public String getF_start_using_date() {
		return f_start_using_date;
	}

	public void setF_start_using_date(String f_start_using_date) {
		this.f_start_using_date = f_start_using_date;
	}

	public String getF_deprec_month() {
		return f_deprec_month;
	}

	public void setF_deprec_month(String f_deprec_month) {
		this.f_deprec_month = f_deprec_month;
	}

	public String getF_budget_pl_impact() {
		return f_budget_pl_impact;
	}

	public void setF_budget_pl_impact(String f_budget_pl_impact) {
		this.f_budget_pl_impact = f_budget_pl_impact;
	}

	public String getF_budget_pl_month() {
		return f_budget_pl_month;
	}

	public void setF_budget_pl_month(String f_budget_pl_month) {
		this.f_budget_pl_month = f_budget_pl_month;
	}

	public String getF_pl_impact() {
		return f_pl_impact;
	}

	public void setF_pl_impact(String f_pl_impact) {
		this.f_pl_impact = f_pl_impact;
	}

	public String getF_pl_month() {
		return f_pl_month;
	}

	public void setF_pl_month(String f_pl_month) {
		this.f_pl_month = f_pl_month;
	}

	public String getF_asset_number() {
		return f_asset_number;
	}

	public void setF_asset_number(String f_asset_number) {
		this.f_asset_number = f_asset_number;
	}

	public String getF_book_value() {
		return f_book_value;
	}

	public void setF_book_value(String f_book_value) {
		this.f_book_value = f_book_value;
	}

	public String getF_total_payment_amount() {
		return f_total_payment_amount;
	}

	public void setF_total_payment_amount(String f_total_payment_amount) {
		this.f_total_payment_amount = f_total_payment_amount;
	}

	public String getF_agreement_classification() {
		return f_agreement_classification;
	}

	public void setF_agreement_classification(String f_agreement_classification) {
		this.f_agreement_classification = f_agreement_classification;
	}

	public String getF_pd_sub_condition() {
		return f_pd_sub_condition;
	}

	public void setF_pd_sub_condition(String f_pd_sub_condition) {
		this.f_pd_sub_condition = f_pd_sub_condition;
	}

	public String getF_ec_approval() {
		return f_ec_approval;
	}

	public void setF_ec_approval(String f_ec_approval) {
		this.f_ec_approval = f_ec_approval;
	}

	public String getF_ec_sub_condition() {
		return f_ec_sub_condition;
	}

	public void setF_ec_sub_condition(String f_ec_sub_condition) {
		this.f_ec_sub_condition = f_ec_sub_condition;
	}

	public String getF_psd_area() {
		return f_psd_area;
	}

	public void setF_psd_area(String f_psd_area) {
		this.f_psd_area = f_psd_area;
	}

	public String getF_psd_process() {
		return f_psd_process;
	}

	public void setF_psd_process(String f_psd_process) {
		this.f_psd_process = f_psd_process;
	}

	public String getF_dic_reason() {
		return f_dic_reason;
	}

	public void setF_dic_reason(String f_dic_reason) {
		this.f_dic_reason = f_dic_reason;
	}

	public String getF_dd_process() {
		return f_dd_process;
	}

	public void setF_dd_process(String f_dd_process) {
		this.f_dd_process = f_dd_process;
	}

	public String getF_anti_bribery() {
		return f_anti_bribery;
	}

	public void setF_anti_bribery(String f_anti_bribery) {
		this.f_anti_bribery = f_anti_bribery;
	}

	public String getF_audit_rights() {
		return f_audit_rights;
	}

	public void setF_audit_rights(String f_audit_rights) {
		this.f_audit_rights = f_audit_rights;
	}

	public String getF_legal_agreement_number() {
		return f_legal_agreement_number;
	}

	public void setF_legal_agreement_number(String f_legal_agreement_number) {
		this.f_legal_agreement_number = f_legal_agreement_number;
	}

	public String getF_legal_agreement_date() {
		return f_legal_agreement_date;
	}

	public void setF_legal_agreement_date(String f_legal_agreement_date) {
		this.f_legal_agreement_date = f_legal_agreement_date;
	}

	public Collection<AgreementAttachmentModel> getD_list_attachment() {
		return d_list_attachment;
	}

	public void setD_list_attachment(Collection<AgreementAttachmentModel> d_list_attachment) {
		this.d_list_attachment = d_list_attachment;
	}

	public Collection<AgreementPaymentDetailModel> getD_list_payment_detail() {
		return d_list_payment_detail;
	}

	public void setD_list_payment_detail(Collection<AgreementPaymentDetailModel> d_list_payment_detail) {
		this.d_list_payment_detail = d_list_payment_detail;
	}

	public String getF_application_number() {
		return f_application_number;
	}

	public void setF_application_number(String f_application_number) {
		this.f_application_number = f_application_number;
	}

	public String getF_application_date() {
		return f_application_date;
	}

	public void setF_application_date(String f_application_date) {
		this.f_application_date = f_application_date;
	}

	public String getF_applicant_number() {
		return f_applicant_number;
	}

	public void setF_applicant_number(String f_applicant_number) {
		this.f_applicant_number = f_applicant_number;
	}

	public String getF_applicant_department() {
		return f_applicant_department;
	}

	public void setF_applicant_department(String f_applicant_department) {
		this.f_applicant_department = f_applicant_department;
	}

	public String getF_applicant_name() {
		return f_applicant_name;
	}

	public void setF_applicant_name(String f_applicant_name) {
		this.f_applicant_name = f_applicant_name;
	}

	public String getF_applicant_post() {
		return f_applicant_post;
	}

	public void setF_applicant_post(String f_applicant_post) {
		this.f_applicant_post = f_applicant_post;
	}

	public String getF_agreement_status_one_time() {
		return f_agreement_status_one_time;
	}

	public void setF_agreement_status_one_time(String f_agreement_status_one_time) {
		this.f_agreement_status_one_time = f_agreement_status_one_time;
	}

	public String getF_agreement_status_amendment() {
		return f_agreement_status_amendment;
	}

	public void setF_agreement_status_amendment(String f_agreement_status_amendment) {
		this.f_agreement_status_amendment = f_agreement_status_amendment;
	}

	public String getF_agreement_status_umbrella() {
		return f_agreement_status_umbrella;
	}

	public void setF_agreement_status_umbrella(String f_agreement_status_umbrella) {
		this.f_agreement_status_umbrella = f_agreement_status_umbrella;
	}

	public String getF_total_duration_more_than_1_year() {
		return f_total_duration_more_than_1_year;
	}

	public void setF_total_duration_more_than_1_year(String f_total_duration_more_than_1_year) {
		this.f_total_duration_more_than_1_year = f_total_duration_more_than_1_year;
	}

	public String getF_total_duration_up_to_1_year() {
		return f_total_duration_up_to_1_year;
	}

	public void setF_total_duration_up_to_1_year(String f_total_duration_up_to_1_year) {
		this.f_total_duration_up_to_1_year = f_total_duration_up_to_1_year;
	}

	public String getF_auto_extension_yes() {
		return f_auto_extension_yes;
	}

	public void setF_auto_extension_yes(String f_auto_extension_yes) {
		this.f_auto_extension_yes = f_auto_extension_yes;
	}

	public String getF_auto_extension_no() {
		return f_auto_extension_no;
	}

	public void setF_auto_extension_no(String f_auto_extension_no) {
		this.f_auto_extension_no = f_auto_extension_no;
	}

	public String getF_po_required_yes() {
		return f_po_required_yes;
	}

	public void setF_po_required_yes(String f_po_required_yes) {
		this.f_po_required_yes = f_po_required_yes;
	}

	public String getF_po_required_no() {
		return f_po_required_no;
	}

	public void setF_po_required_no(String f_po_required_no) {
		this.f_po_required_no = f_po_required_no;
	}

	public String getF_company_relation_related_parties() {
		return f_company_relation_related_parties;
	}

	public void setF_company_relation_related_parties(String f_company_relation_related_parties) {
		this.f_company_relation_related_parties = f_company_relation_related_parties;
	}

	public String getF_company_relation_non_related_parties() {
		return f_company_relation_non_related_parties;
	}

	public void setF_company_relation_non_related_parties(String f_company_relation_non_related_parties) {
		this.f_company_relation_non_related_parties = f_company_relation_non_related_parties;
	}

	public String getF_purchase_category_tangible_asset() {
		return f_purchase_category_tangible_asset;
	}

	public void setF_purchase_category_tangible_asset(String f_purchase_category_tangible_asset) {
		this.f_purchase_category_tangible_asset = f_purchase_category_tangible_asset;
	}

	public String getF_purchase_category_intangible_asset() {
		return f_purchase_category_intangible_asset;
	}

	public void setF_purchase_category_intangible_asset(String f_purchase_category_intangible_asset) {
		this.f_purchase_category_intangible_asset = f_purchase_category_intangible_asset;
	}

	public String getF_purchase_category_non_asset() {
		return f_purchase_category_non_asset;
	}

	public void setF_purchase_category_non_asset(String f_purchase_category_non_asset) {
		this.f_purchase_category_non_asset = f_purchase_category_non_asset;
	}

	public String getF_multidata() {
		return f_multidata;
	}

	public void setF_multidata(String f_multidata) {
		this.f_multidata = f_multidata;
	}

	public String getF_multidata_pl() {
		return f_multidata_pl;
	}

	public void setF_multidata_pl(String f_multidata_pl) {
		this.f_multidata_pl = f_multidata_pl;
	}

	public String getF_multidata_asset() {
		return f_multidata_asset;
	}

	public void setF_multidata_asset(String f_multidata_asset) {
		this.f_multidata_asset = f_multidata_asset;
	}

	public String getF_multidata_estimated() {
		return f_multidata_estimated;
	}

	public void setF_multidata_estimated(String f_multidata_estimated) {
		this.f_multidata_estimated = f_multidata_estimated;
	}

	public String getF_agreement_classification_pd() {
		return f_agreement_classification_pd;
	}

	public void setF_agreement_classification_pd(String f_agreement_classification_pd) {
		this.f_agreement_classification_pd = f_agreement_classification_pd;
	}

	public String getF_agreement_classification_pd_more_than_1_billion() {
		return f_agreement_classification_pd_more_than_1_billion;
	}

	public void setF_agreement_classification_pd_more_than_1_billion(
			String f_agreement_classification_pd_more_than_1_billion) {
		this.f_agreement_classification_pd_more_than_1_billion = f_agreement_classification_pd_more_than_1_billion;
	}

	public String getF_agreement_classification_pd_more_than_12_months() {
		return f_agreement_classification_pd_more_than_12_months;
	}

	public void setF_agreement_classification_pd_more_than_12_months(
			String f_agreement_classification_pd_more_than_12_months) {
		this.f_agreement_classification_pd_more_than_12_months = f_agreement_classification_pd_more_than_12_months;
	}

	public String getF_agreement_classification_pd_specific_party() {
		return f_agreement_classification_pd_specific_party;
	}

	public void setF_agreement_classification_pd_specific_party(String f_agreement_classification_pd_specific_party) {
		this.f_agreement_classification_pd_specific_party = f_agreement_classification_pd_specific_party;
	}

	public String getF_agreement_classification_pd_special_issue() {
		return f_agreement_classification_pd_special_issue;
	}

	public void setF_agreement_classification_pd_special_issue(String f_agreement_classification_pd_special_issue) {
		this.f_agreement_classification_pd_special_issue = f_agreement_classification_pd_special_issue;
	}

	public String getF_agreement_classification_pd_direct_procurement() {
		return f_agreement_classification_pd_direct_procurement;
	}

	public void setF_agreement_classification_pd_direct_procurement(
			String f_agreement_classification_pd_direct_procurement) {
		this.f_agreement_classification_pd_direct_procurement = f_agreement_classification_pd_direct_procurement;
	}

	public String getF_agreement_classification_pd_agreement_not_more_than_12_months() {
		return f_agreement_classification_pd_agreement_not_more_than_12_months;
	}

	public void setF_agreement_classification_pd_agreement_not_more_than_12_months(
			String f_agreement_classification_pd_agreement_not_more_than_12_months) {
		this.f_agreement_classification_pd_agreement_not_more_than_12_months = f_agreement_classification_pd_agreement_not_more_than_12_months;
	}

	public String getF_agreement_classification_dic_director_approval() {
		return f_agreement_classification_dic_director_approval;
	}

	public void setF_agreement_classification_dic_director_approval(
			String f_agreement_classification_dic_director_approval) {
		this.f_agreement_classification_dic_director_approval = f_agreement_classification_dic_director_approval;
	}

	public String getF_agreement_classification_ec_approval_yes() {
		return f_agreement_classification_ec_approval_yes;
	}

	public void setF_agreement_classification_ec_approval_yes(String f_agreement_classification_ec_approval_yes) {
		this.f_agreement_classification_ec_approval_yes = f_agreement_classification_ec_approval_yes;
	}

	public String getF_agreement_classification_ec_amount_equal_more_than_1_billion() {
		return f_agreement_classification_ec_amount_equal_more_than_1_billion;
	}

	public void setF_agreement_classification_ec_amount_equal_more_than_1_billion(
			String f_agreement_classification_ec_amount_equal_more_than_1_billion) {
		this.f_agreement_classification_ec_amount_equal_more_than_1_billion = f_agreement_classification_ec_amount_equal_more_than_1_billion;
	}

	public String getF_agreement_classification_ec_period_equal_more_than_12_months() {
		return f_agreement_classification_ec_period_equal_more_than_12_months;
	}

	public void setF_agreement_classification_ec_period_equal_more_than_12_months(
			String f_agreement_classification_ec_period_equal_more_than_12_months) {
		this.f_agreement_classification_ec_period_equal_more_than_12_months = f_agreement_classification_ec_period_equal_more_than_12_months;
	}

	public String getF_agreement_classification_ec_escalate_issue_to_ec() {
		return f_agreement_classification_ec_escalate_issue_to_ec;
	}

	public void setF_agreement_classification_ec_escalate_issue_to_ec(
			String f_agreement_classification_ec_escalate_issue_to_ec) {
		this.f_agreement_classification_ec_escalate_issue_to_ec = f_agreement_classification_ec_escalate_issue_to_ec;
	}

	public String getF_agreement_classification_ec_approval_no() {
		return f_agreement_classification_ec_approval_no;
	}

	public void setF_agreement_classification_ec_approval_no(String f_agreement_classification_ec_approval_no) {
		this.f_agreement_classification_ec_approval_no = f_agreement_classification_ec_approval_no;
	}

	public String getF_psd_area_psd() {
		return f_psd_area_psd;
	}

	public void setF_psd_area_psd(String f_psd_area_psd) {
		this.f_psd_area_psd = f_psd_area_psd;
	}

	public String getF_psd_area_non_psd() {
		return f_psd_area_non_psd;
	}

	public void setF_psd_area_non_psd(String f_psd_area_non_psd) {
		this.f_psd_area_non_psd = f_psd_area_non_psd;
	}

	public String getF_psd_process_psd() {
		return f_psd_process_psd;
	}

	public void setF_psd_process_psd(String f_psd_process_psd) {
		this.f_psd_process_psd = f_psd_process_psd;
	}

	public String getF_psd_process_dic() {
		return f_psd_process_dic;
	}

	public void setF_psd_process_dic(String f_psd_process_dic) {
		this.f_psd_process_dic = f_psd_process_dic;
	}

	public String getF_dd_process_yes() {
		return f_dd_process_yes;
	}

	public void setF_dd_process_yes(String f_dd_process_yes) {
		this.f_dd_process_yes = f_dd_process_yes;
	}

	public String getF_dd_process_no() {
		return f_dd_process_no;
	}

	public void setF_dd_process_no(String f_dd_process_no) {
		this.f_dd_process_no = f_dd_process_no;
	}

	public String getF_anti_bribery_yes() {
		return f_anti_bribery_yes;
	}

	public void setF_anti_bribery_yes(String f_anti_bribery_yes) {
		this.f_anti_bribery_yes = f_anti_bribery_yes;
	}

	public String getF_anti_bribery_no() {
		return f_anti_bribery_no;
	}

	public void setF_anti_bribery_no(String f_anti_bribery_no) {
		this.f_anti_bribery_no = f_anti_bribery_no;
	}

	public String getF_audit_rights_yes() {
		return f_audit_rights_yes;
	}

	public void setF_audit_rights_yes(String f_audit_rights_yes) {
		this.f_audit_rights_yes = f_audit_rights_yes;
	}

	public String getF_audit_rights_no() {
		return f_audit_rights_no;
	}

	public void setF_audit_rights_no(String f_audit_rights_no) {
		this.f_audit_rights_no = f_audit_rights_no;
	}

}
