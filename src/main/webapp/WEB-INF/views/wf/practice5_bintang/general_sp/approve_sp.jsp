<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="imartj2ee" uri="http://www.intra-mart.co.jp/taglib/core/framework" %>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui" %>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard" %>
<%@ taglib prefix="workflowSmartphone" uri="http://www.intra-mart.co.jp/taglib/imw/workflow-smartphone" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="imsp" uri="http://www.intra-mart.co.jp/taglib/imsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<imui:head>
	<title>Agreement Workflow (Smartphone)</title>
	<workflowSmartphone:spWorkflowOpenPageCsjs/>
	<link rel="stylesheet" href="ui/jq/jquery-ui.css">
	<link href="ui/css/select2.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
	<script src="ui/jq/jquery-ui.js"></script>
	<script src="ui/js/select2.min.js"></script>
	<script src="ui/js/jquery.validate.js"></script>

	<style>
		body {
			overscroll-behavior: none;
		}
		.custom-corners .ui-bar {
			-webkit-border-top-left-radius: inherit;
			border-top-left-radius: inherit;
			-webkit-border-top-right-radius: inherit;
			border-top-right-radius: inherit;
		}
		.custom-corners .ui-body {
			border-top-width: 0;
			-webkit-border-bottom-left-radius: inherit;
			border-bottom-left-radius: inherit;
			-webkit-border-bottom-right-radius: inherit;
			border-bottom-right-radius: inherit;
		}
		.custom-readonly .ui-field-contain div.ui-input-text,
		.custom-readonly div.ui-input-text,
		.custom-readonly input.ui-input-text,
		.custom-readonly textarea.ui-input-text {
			background-color: lightgray !important;
		}
		.header-cell {
			background-color: #dcdcdc;
			border-right: 4px solid lightgray;
			text-align: left;
			padding: 10px;
			color: black;
		}
		.last-cell {
			background-color: #dcdcdc;
			text-align: left;
			padding: 10px;
			color: black;
		}
		.select2 {
			width: 100% !important;
		}
	</style>

	<script type="text/javascript">
		$(function() {
			$(".custom-readonly .ui-field-contain div.ui-input-text, .custom-readonly div.ui-input-text")
				.css("background-color", "lightgray");

			$(".select2").select2({ theme: "classic" });
			$(".select2").prop("disabled", true).trigger("change");

			$('.back').click(function() {
				$('#backForm').submit();
				return false;
			});

			$('#openPage').click(function() {
				workflowOpenPage4Sp('${f:h(workflowRequestForm.imwPageType)}');
				return false;
			});
		});
	</script>
</imui:head>

<workflowSmartphone:spWorkflowUserContentsAuth 
	imwApplyBaseDate='${f:h(workflowRequestForm.imwApplyBaseDate)}'
	imwAuthUserCode='${f:h(workflowRequestForm.imwAuthUserCode)}'
	imwFlowId='${f:h(workflowRequestForm.imwFlowId)}'
	imwNodeId='${f:h(workflowRequestForm.imwNodeId)}'
	imwPageType='${f:h(workflowRequestForm.imwPageType)}'
	imwSystemMatterId='${f:h(workflowRequestForm.imwSystemMatterId)}'
	imwUserDataId='${f:h(workflowRequestForm.imwUserDataId)}' />

<div data-role="page" id="imw-sp-agreement-approve" data-theme="a">
	<div data-theme="a" data-role="header" data-position="fixed">
		<a data-role="button" data-icon="back" id="back" class="back">Back</a>
		<h1>Agreement Workflow</h1>
	</div>

	<div data-role="content">
		<workflowSmartphone:spWorkflowOpenPage name="workflowOpenPageForm"
			id="workflowOpenPageForm"
			method="POST"
			target="_top"
			imwUserDataId='${f:h(workflowRequestForm.imwUserDataId)}'
			imwSystemMatterId='${f:h(workflowRequestForm.imwSystemMatterId)}'
			imwAuthUserCode='${f:h(workflowRequestForm.imwAuthUserCode)}'
			imwApplyBaseDate='${f:h(workflowRequestForm.imwApplyBaseDate)}'
			imwNodeId='${f:h(workflowRequestForm.imwNodeId)}'
			imwFlowId='${f:h(workflowRequestForm.imwFlowId)}'
			imwCallOriginalParams='${f:h(workflowRequestForm.imwCallOriginalParams)}'
			imwNextScriptPath='${f:h(workflowRequestForm.imwCallOriginalPagePath)}'>

			<!-- Practice 0: Applicant Info Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Applicant Information</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="Application Number :">
						<div class="ui-field-contain custom-readonly">
							<input type="text" id="f_application_number" name="f_application_number" value="${f:h(savedFormData.f_application_number)}" readonly />
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Application Date :">
						<div class="ui-field-contain custom-readonly">
							<input type="text" id="f_application_date" name="f_application_date" value="${f:h(savedFormData.f_application_date)}" readonly />
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Applicant Number :">
						<div class="ui-field-contain custom-readonly">
							<input type="text" id="f_applicant_number" name="f_applicant_number" value="${f:h(savedFormData.f_applicant_number)}" readonly />
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Applicant Name :">
						<div class="ui-field-contain custom-readonly">
							<input type="text" id="f_applicant_name" name="f_applicant_name" value="${f:h(savedFormData.f_applicant_name)}" readonly />
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Department :">
						<div class="ui-field-contain custom-readonly">
							<input type="text" id="f_applicant_department" name="f_applicant_department" value="${f:h(savedFormData.f_applicant_department)}" readonly />
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Position / Post :">
						<div class="ui-field-contain custom-readonly">
							<input type="text" id="f_applicant_post" name="f_applicant_post" value="${f:h(savedFormData.f_applicant_post)}" readonly />
						</div>
					</imsp:fieldContain>
				</div>
			</div>
			<br>

			<!-- Practice 1: Basic Agreement Info Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Agreement Detail</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="Counter Party :" required="true">
						<div class="ui-field-contain custom-readonly">
							<input type="text" id="f_counter_party" name="f_counter_party" value="${f:h(savedFormData.f_counter_party)}" readonly />
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Currency :" required="true">
						<div class="ui-field-contain custom-readonly">
							<input type="text" id="f_currency" name="f_currency" value="${f:h(savedFormData.f_currency)}" readonly />
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Total Amount (Without Tax) :" required="true">
						<div class="ui-field-contain custom-readonly">
							<input type="text" id="f_total_amount" name="f_total_amount" value="${f:h(savedFormData.f_total_amount)}" readonly />
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Agreement Status :" required="true">
						<div class="ui-field-contain custom-readonly">
							<c:choose>
								<c:when test="${savedFormData.f_agreement_status_one_time == 'checked'}">One Time / New</c:when>
								<c:when test="${savedFormData.f_agreement_status_amendment == 'checked'}">
									Amendment / Extension / Renewal
									<c:if test="${savedFormData.f_total_duration_more_than_1_year == 'checked'}"> (More than 1 year)</c:if>
									<c:if test="${savedFormData.f_total_duration_up_to_1_year == 'checked'}"> (Up to 1 year)</c:if>
								</c:when>
								<c:when test="${savedFormData.f_agreement_status_umbrella == 'checked'}">Umbrella Agreement</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Auto Extension Condition :" required="true">
						<div class="ui-field-contain custom-readonly">
							<c:choose>
								<c:when test="${savedFormData.f_auto_extension_yes == 'checked'}">Yes</c:when>
								<c:when test="${savedFormData.f_auto_extension_no == 'checked'}">No</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Purchase Order Required :" required="true">
						<div class="ui-field-contain custom-readonly">
							<c:choose>
								<c:when test="${savedFormData.f_po_required_yes == 'checked'}">Yes</c:when>
								<c:when test="${savedFormData.f_po_required_no == 'checked'}">No</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Title described in Agreement :" required="true">
						<div class="ui-field-contain custom-readonly">
							<input type="text" id="f_agreement_title" name="f_agreement_title" value="${f:h(savedFormData.f_agreement_title)}" readonly />
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Effective Period :" required="true">
						<div class="ui-field-contain custom-readonly">
							<input type="text" value="${f:h(savedFormData.f_effective_from)} ~ ${f:h(savedFormData.f_effective_to)}" readonly />
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Company Relation :" required="true">
						<div class="ui-field-contain custom-readonly">
							<c:choose>
								<c:when test="${savedFormData.f_company_relation_related_parties == 'checked'}">Related Parties</c:when>
								<c:when test="${savedFormData.f_company_relation_non_related_parties == 'checked'}">Non Related Parties</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Estimated Delivery Schedule :" required="true">
						<div class="ui-field-contain custom-readonly">
							<input type="text" value="${f:h(savedFormData.f_estimated_delivery_from)} ~ ${f:h(savedFormData.f_estimated_delivery_to)}" readonly />
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Agreement Summary :">
						<div class="ui-field-contain custom-readonly">
							<textarea id="f_agreement_summary" name="f_agreement_summary" rows="3" readonly>${f:h(savedFormData.f_agreement_summary)}</textarea>
						</div>
					</imsp:fieldContain>
				</div>
			</div>
			<br>

			<!-- Practice 1: Depreciation Check Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Depreciation Check</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="Purchase Category :" required="true">
						<div class="ui-field-contain custom-readonly">
							<c:choose>
								<c:when test="${savedFormData.f_purchase_category_tangible_asset == 'checked'}">Tangible Asset</c:when>
								<c:when test="${savedFormData.f_purchase_category_intangible_asset == 'checked'}">Intangible Asset</c:when>
								<c:when test="${savedFormData.f_purchase_category_non_asset == 'checked'}">Non-Asset</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</div>
					</imsp:fieldContain>

					<c:if test="${savedFormData.f_purchase_category_non_asset != 'checked'}">
						<imsp:fieldContain label="Starting Using Date :">
							<div class="ui-field-contain custom-readonly">
								<input type="text" id="f_start_using_date" name="f_start_using_date" value="${f:h(savedFormData.f_start_using_date)}" readonly />
							</div>
						</imsp:fieldContain>

						<imsp:fieldContain label="Deprec Amount / Month :">
							<div class="ui-field-contain custom-readonly">
								<input type="text" id="f_deprec_month" name="f_deprec_month" value="${f:h(savedFormData.f_deprec_month)}" readonly />
							</div>
						</imsp:fieldContain>
					</c:if>
				</div>
			</div>
			<br>

			<!-- Practice 2: Multiple Data Selection Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Multiple Data Selection</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="Selected Options :">
						<div class="ui-field-contain custom-readonly">
							<c:if test="${savedFormData.f_multidata_pl == 'checked'}">[ PL Impact ] </c:if>
							<c:if test="${savedFormData.f_multidata_asset == 'checked'}">[ Asset ] </c:if>
							<c:if test="${savedFormData.f_multidata_estimated == 'checked'}">[ Estimated Schedule ] </c:if>
							<c:if test="${savedFormData.f_multidata_pl != 'checked' && savedFormData.f_multidata_asset != 'checked' && savedFormData.f_multidata_estimated != 'checked'}">-</c:if>
						</div>
					</imsp:fieldContain>

					<!-- PL Impact Details -->
					<c:if test="${savedFormData.f_multidata_pl == 'checked'}">
						<imsp:fieldContain label="Budget PL Impact / Month :">
							<div class="ui-field-contain custom-readonly">
								<input type="text" value="${f:h(savedFormData.f_budget_pl_impact)} (${f:h(savedFormData.f_budget_pl_month)})" readonly />
							</div>
						</imsp:fieldContain>
						<imsp:fieldContain label="Actual PL Impact / Month :">
							<div class="ui-field-contain custom-readonly">
								<input type="text" value="${f:h(savedFormData.f_pl_impact)} (${f:h(savedFormData.f_pl_month)})" readonly />
							</div>
						</imsp:fieldContain>
					</c:if>

					<!-- Asset Details -->
					<c:if test="${savedFormData.f_multidata_asset == 'checked'}">
						<imsp:fieldContain label="Asset Number :">
							<div class="ui-field-contain custom-readonly">
								<input type="text" value="${f:h(savedFormData.f_asset_number)}" readonly />
							</div>
						</imsp:fieldContain>
						<imsp:fieldContain label="Book Value :">
							<div class="ui-field-contain custom-readonly">
								<input type="text" value="${f:h(savedFormData.f_book_value)}" readonly />
							</div>
						</imsp:fieldContain>
					</c:if>
				</div>
			</div>
			<br>

			<!-- Practice 2: Estimated Schedule (Payment Conditions Table) -->
			<c:if test="${savedFormData.f_multidata_estimated == 'checked'}">
				<div class="ui-corner-all custom-corners">
					<div class="ui-bar ui-bar-a">
						<h3>Estimated Schedule (Payment Conditions)</h3>
					</div>
					<div class="ui-body ui-body-a">
						<div class="ui-field-contain custom-readonly" style="overflow-x:scroll">
							<table class="imui-form" style="min-width: 700px;">
								<thead>
									<tr>
										<th class="header-cell" style="width: 40px; text-align: center;">No</th>
										<th class="header-cell" style="width: 180px;">Brand & Type</th>
										<th class="header-cell" style="width: 120px;">Amount</th>
										<th class="header-cell" style="width: 110px;">Date</th>
										<th class="header-cell" style="width: 100px;">Category</th>
										<th class="header-cell" style="width: 90px;">Recurring</th>
										<th class="last-cell" style="width: 100px;">Paid By</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${savedFormData.d_list_payment_detail}" var="item">
										<tr>
											<td style="text-align: center;">${f:h(item.row_no)}</td>
											<td>
												${f:h(item.brand)}
												<c:if test="${not empty item.brand and not empty item.type}">&nbsp;/&nbsp;</c:if>
												${f:h(item.type)}
											</td>
											<td>${f:h(item.payment_amount)}</td>
											<td>${f:h(item.payment_date)}</td>
											<td>
												<c:choose>
													<c:when test="${item.category == '1'}">Equipment</c:when>
													<c:when test="${item.category == '2'}">Software</c:when>
													<c:when test="${item.category == '3'}">Utility</c:when>
													<c:when test="${item.category == '4'}">Service</c:when>
													<c:when test="${item.category == '5'}">Other</c:when>
													<c:otherwise>-</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${item.recurring_yes == 'checked'}">Yes</c:when>
													<c:when test="${item.recurring_no == 'checked'}">No</c:when>
													<c:otherwise>-</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:if test="${item.paid_by_card == 'checked'}">[Card] </c:if>
												<c:if test="${item.paid_by_cash == 'checked'}">[Cash] </c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th colspan="7" class="header-cell" style="text-align: left;">
											Total Amount: <strong>${f:h(savedFormData.f_total_payment_amount)}</strong>
										</th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
				<br>
			</c:if>

			<!-- Practice 7: Agreement Classification & Multiple Branch Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Agreement Classification & Approval Route</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="Agreement Classification :" required="true">
						<div class="ui-field-contain custom-readonly">
							<c:choose>
								<c:when test="${savedFormData.f_agreement_classification_pd == 'checked'}">
									PD Approval
									<div style="font-size: 13px; color: #555; margin-top: 4px;">
										<c:if test="${savedFormData.f_agreement_classification_pd_more_than_1_billion == 'checked'}">• Amount &gt;= 1 Billion<br></c:if>
										<c:if test="${savedFormData.f_agreement_classification_pd_more_than_12_months == 'checked'}">• Period &gt;= 12 Months<br></c:if>
										<c:if test="${savedFormData.f_agreement_classification_pd_specific_party == 'checked'}">• Related to Specific Party<br></c:if>
										<c:if test="${savedFormData.f_agreement_classification_pd_special_issue == 'checked'}">• Special Issue<br></c:if>
										<c:if test="${savedFormData.f_agreement_classification_pd_direct_procurement == 'checked'}">• Direct Procurement<br></c:if>
										<c:if test="${savedFormData.f_agreement_classification_pd_agreement_not_more_than_12_months == 'checked'}">• Not more than 12 Months<br></c:if>
									</div>
								</c:when>
								<c:when test="${savedFormData.f_agreement_classification_dic_director_approval == 'checked'}">
									DIC Director Approval
								</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="EC Approval Required :" required="true">
						<div class="ui-field-contain custom-readonly">
							<c:choose>
								<c:when test="${savedFormData.f_agreement_classification_ec_approval_yes == 'checked'}">
									Yes
									<div style="font-size: 13px; color: #555; margin-top: 4px;">
										<c:if test="${savedFormData.f_agreement_classification_ec_amount_equal_more_than_1_billion == 'checked'}">• Amount &gt;= 1 Billion<br></c:if>
										<c:if test="${savedFormData.f_agreement_classification_ec_period_equal_more_than_12_months == 'checked'}">• Period &gt;= 12 Months<br></c:if>
										<c:if test="${savedFormData.f_agreement_classification_ec_escalate_issue_to_ec == 'checked'}">• Escalate issue to EC<br></c:if>
									</div>
								</c:when>
								<c:when test="${savedFormData.f_agreement_classification_ec_approval_no == 'checked'}">No</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</div>
					</imsp:fieldContain>
				</div>
			</div>
			<br>

			<!-- Practice 8: Approver Node Checks (PSD, CCO, Legal) -->
			<!-- 1. PSD Check Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>PSD Check</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="PSD Area / Non-PSD Area :" required="true">
						<div class="ui-field-contain custom-readonly">
							<c:choose>
								<c:when test="${savedFormData.f_psd_area_psd == 'checked'}">PSD Area</c:when>
								<c:when test="${savedFormData.f_psd_area_non_psd == 'checked'}">Non-PSD Area</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</div>
					</imsp:fieldContain>

					<c:if test="${savedFormData.f_psd_area_psd == 'checked'}">
						<imsp:fieldContain label="PSD / DIC Process :" required="true">
							<div class="ui-field-contain custom-readonly">
								<c:choose>
									<c:when test="${savedFormData.f_psd_process_psd == 'checked'}">PSD (Pitching result attached)</c:when>
									<c:when test="${savedFormData.f_psd_process_dic == 'checked'}">
										DIC (Direct Procurement)
										<c:if test="${not empty savedFormData.f_dic_reason}">
											<br><em>Reason: ${f:h(savedFormData.f_dic_reason)}</em>
										</c:if>
									</c:when>
									<c:otherwise>-</c:otherwise>
								</c:choose>
							</div>
						</imsp:fieldContain>
					</c:if>
				</div>
			</div>
			<br>

			<!-- 2. Compliance Check By CCO Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Compliance Check (CCO)</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="D / D Process Required :" required="true">
						<div class="ui-field-contain custom-readonly">
							<c:choose>
								<c:when test="${savedFormData.f_dd_process_yes == 'checked'}">Yes</c:when>
								<c:when test="${savedFormData.f_dd_process_no == 'checked'}">No</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Anti Bribery Clause Included :" required="true">
						<div class="ui-field-contain custom-readonly">
							<c:choose>
								<c:when test="${savedFormData.f_anti_bribery_yes == 'checked'}">Yes</c:when>
								<c:when test="${savedFormData.f_anti_bribery_no == 'checked'}">No</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Audit Rights Included :" required="true">
						<div class="ui-field-contain custom-readonly">
							<c:choose>
								<c:when test="${savedFormData.f_audit_rights_yes == 'checked'}">Yes</c:when>
								<c:when test="${savedFormData.f_audit_rights_no == 'checked'}">No</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</div>
					</imsp:fieldContain>
				</div>
			</div>
			<br>

			<!-- Practice 3: Attachments Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Attached Documents</h3>
				</div>
				<div class="ui-body ui-body-a">
					<c:choose>
						<c:when test="${not empty savedFormData.d_list_attachment}">
							<table class="imui-form">
								<tbody>
									<c:forEach items="${savedFormData.d_list_attachment}" var="file">
										<tr>
											<td style="padding: 10px 5px; vertical-align: middle;">
												<a target="_blank" href="practice5_bintang/download/${f:h(file.id)}?token=${f:h(savedFormData.f_download_token)}&system_matter_id=${f:h(workflowRequestForm.imwSystemMatterId)}" style="text-decoration: none; font-size: 14px;">
													<i class="fa-solid fa-file-arrow-down fa-lg"></i> ${f:h(file.file_name)}
												</a>
												<span style="font-size: 12px; color: #777; margin-left: 6px;">(${f:h(file.file_size_convert)})</span>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<div style="padding: 10px; color: #777; text-align: center;">No documents attached</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<br>

		</workflowSmartphone:spWorkflowOpenPage>

		<!-- Action Button -->
		<fieldset style="margin-top: 10px; margin-bottom: 20px;">
			<button type="button" value="Process" id="openPage" name="openPage" data-theme="b" style="padding: 12px; font-size: 16px;">
				<i class="fa-solid fa-check"></i> Process
			</button>
		</fieldset>
	</div>

	<!-- Bottom Navigation Footer -->
	<div data-role="footer" data-position="fixed" data-theme="b">
		<div data-role="navbar">
			<ul>
				<li>
					<a data-role="button" class="back">
						<i class="fa-solid fa-left-long fa-lg"></i><br>
						<i>Back</i>
					</a>
				</li>
				<li>
					<a href="javascript:void(0);" onclick="window.top.close();" data-ajax="false">
						<i class="fa-solid fa-house fa-lg"></i><br>
						<i>Home</i>
					</a>
				</li>
				<li>
					<a href="/imarttraining/logout" data-ajax="false">
						<i class="fa-solid fa-right-from-bracket fa-lg"></i><br>
						<i>Logout</i>
					</a>
				</li>
			</ul>
		</div>
	</div>

	<!-- Hidden Back Form -->
	<form name="backForm" id="backForm" method="POST" action='${f:h(workflowRequestForm.imwCallOriginalPagePath)}' data-ajax="false">
		<input type="hidden" name="imwCallOriginalParams" value='${f:h(workflowRequestForm.imwCallOriginalParams)}' />
	</form>
</div>
