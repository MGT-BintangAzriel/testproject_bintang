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
		/* Base & Layout */
		body {
			overscroll-behavior: none;
			background-color: #f4f5f7 !important;
			color: #333333 !important;
		}

		/* jQuery Mobile Page content background */
		.ui-page, .ui-content {
			background: #f4f5f7 !important;
			color: #333333 !important;
		}

		/* Card Container (custom-corners) */
		.custom-corners {
			background: #ffffff !important;
			border: 1px solid #d1d5db !important;
			border-radius: 8px !important;
			overflow: hidden !important;
			box-shadow: 0 1px 3px rgba(0, 0, 0, 0.08) !important;
			margin-bottom: 15px;
		}

		/* Card Header & Body */
		.custom-corners .ui-bar,
		.custom-corners .ui-bar-a {
			background: #2b3a4a !important;
			color: #ffffff !important;
			text-shadow: none !important;
			border: none !important;
			padding: 4px 12px !important;
		}
		.custom-corners .ui-bar h3 {
			color: #ffffff !important;
			margin: 6px 0 !important;
			font-size: 15px !important;
			font-weight: 600 !important;
		}
		.custom-corners .ui-body,
		.custom-corners .ui-body-a {
			background: #ffffff !important;
			color: #333333 !important;
			text-shadow: none !important;
			border: none !important;
			padding: 0 10px !important;
		}

		/* Field containment & labels */
		.ui-field-contain label,
		.ui-field-contain .ui-controlgroup-label {
			color: #1f2937 !important;
			font-weight: 600 !important;
			text-shadow: none !important;
			font-size: 13px !important;
			margin-bottom: 4px !important;
		}

		/* Custom spacing for each field row */
		.ui-field-contain {
			margin: 10px 0 !important;       /* Space between rows */
			padding: 0.1em 0 !important;
		}

		/* Remove border on the last field inside a card */
		.ui-field-contain:last-child {
			border-bottom: none !important;
		}

		/* Readonly text display styling */
		.custom-readonly {
			color: #111827 !important;
			font-size: 14px !important;
			font-weight: 500 !important;
			line-height: 1.5 !important;
			text-shadow: none !important;
			padding: 4px 0 !important;
			word-break: break-word !important;
		}

		.custom-readonly input.ui-input-text,
		.custom-readonly textarea.ui-input-text,
		.custom-readonly div.ui-input-text,
		.custom-readonly .ui-field-contain input.ui-input-text,
		.custom-readonly .ui-field-contain textarea.ui-input-text,
		.custom-readonly .ui-field-contain div.ui-input-text {
			background-color: #f3f4f6 !important;
			color: #111827 !important;
			text-shadow: none !important;
			border: 1px solid #d1d5db !important;
			border-radius: 6px !important;
			font-size: 14px !important;
			font-weight: 500 !important;
			padding: 6px 10px !important;
		}

		/* Select2 styling */
		.select2 {
			width: 100% !important;
		}
		.select2-container .select2-selection--single .select2-selection__rendered {
			font-size: 14px;
			color: #111827 !important;
		}
		.custom-readonly .select2-container .select2-selection {
			background-color: #f3f4f6 !important;
			border: 1px solid #d1d5db !important;
			border-radius: 6px !important;
		}

		/* Radio & Checkbox button styling for mobile */
		.ui-radio.ui-disabled,
		.ui-checkbox.ui-disabled {
			opacity: 1 !important;
			filter: none !important;
		}
		.ui-radio,
		.ui-checkbox {
			margin: 4px 0 !important;
		}
		.ui-radio label.ui-btn,
		.ui-checkbox label.ui-btn {
			background: #ffffff !important;
			border: 1px solid #d1d5db !important;
			border-radius: 6px !important;
			box-shadow: none !important;
			margin: 0 !important;
		}
		.ui-radio label.ui-btn .ui-btn-inner,
		.ui-checkbox label.ui-btn .ui-btn-inner {
			padding: 6px 10px 6px 36px !important;
			font-size: 13px !important;
			font-weight: 500 !important;
			border: none !important;
		}
		.ui-radio label.ui-btn .ui-btn-text,
		.ui-checkbox label.ui-btn .ui-btn-text {
			color: #1f2937 !important;
			text-shadow: none !important;
		}
		.ui-radio label.ui-radio-on,
		.ui-radio label.ui-btn-active,
		.ui-checkbox label.ui-checkbox-on,
		.ui-checkbox label.ui-btn-active {
			background: #eff6ff !important;
			border-color: #2563eb !important;
		}
		.ui-radio label.ui-radio-on .ui-btn-text,
		.ui-radio label.ui-btn-active .ui-btn-text,
		.ui-checkbox label.ui-checkbox-on .ui-btn-text,
		.ui-checkbox label.ui-btn-active .ui-btn-text {
			color: #1d4ed8 !important;
			font-weight: 600 !important;
		}

		/* Table styling */
		.imui-form {
			width: 100%;
			border-collapse: collapse;
			background: #ffffff;
		}
		.header-cell {
			background-color: #e5e7eb !important;
			border: 1px solid #d1d5db !important;
			text-align: left;
			padding: 8px 10px;
			color: #1f2937 !important;
			font-size: 13px;
			font-weight: 600;
			text-shadow: none !important;
		}
		.last-cell {
			background-color: #e5e7eb !important;
			border: 1px solid #d1d5db !important;
			text-align: left;
			padding: 8px 10px;
			color: #1f2937 !important;
			font-size: 13px;
			font-weight: 600;
			text-shadow: none !important;
		}
		.imui-form tbody td {
			border: 1px solid #e5e7eb;
			padding: 8px 10px;
			color: #1f2937;
			font-size: 13px;
			background: #ffffff;
		}
		.imui-form tfoot th {
			background-color: #f9fafb !important;
			border: 1px solid #d1d5db !important;
			color: #111827 !important;
			padding: 8px 10px;
			font-size: 13px;
			text-shadow: none !important;
		}

		.required-symbol {
			color: #dc2626 !important;
			font-weight: bold;
		}

		/* Scrollbars */
		::-webkit-scrollbar {
			height: 6px;
		}
		::-webkit-scrollbar-thumb {
			background-color: #9ca3af;
			border-radius: 4px;
		}
		::-webkit-scrollbar-track {
			background-color: #f3f4f6;
		}
	</style>

	<script type="text/javascript">
		function formatNumberText() {
			$("#f_total_amount, #f_total_payment_amount, .payment-amount, .number-format").each(function () {
				var val = $(this).text().trim();
				if (val && val !== "-" && val !== "") {
					var cleanVal = val.replace(/[^0-9.-]/g, "");
					var num = parseFloat(cleanVal);
					if (!isNaN(num)) {
						$(this).text(
							num.toLocaleString("en-US", {
								minimumFractionDigits: 2,
								maximumFractionDigits: 2,
							})
						);
					}
				}
			});
		}

		$(function() {
			formatNumberText();

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
					<imsp:fieldContain label="Application Number:">
						<div class="custom-readonly">
							${f:h(savedFormData.f_application_number)}
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Application Date:">
						<div class="custom-readonly">
							${f:h(savedFormData.f_application_date)}
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Applicant Number:">
						<div class="custom-readonly">
							${f:h(savedFormData.f_applicant_number)}
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Applicant Name:">
						<div class="custom-readonly">
							${f:h(savedFormData.f_applicant_name)}
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Department:">
						<div class="custom-readonly">
							${f:h(savedFormData.f_applicant_department)}
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Position / Post:">
						<div class="custom-readonly">
							${f:h(savedFormData.f_applicant_post)}
						</div>
					</imsp:fieldContain>
				</div>
			</div>

			<!-- Practice 1: Basic Agreement Info Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Agreement Detail</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="Counter Party:" required="true">
						<div class="custom-readonly">
							${f:h(savedFormData.f_counter_party)}
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Currency:" required="true">
						<div class="custom-readonly">
							${f:h(savedFormData.f_currency)}
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Total Amount (Without Tax):" required="true">
						<div class="custom-readonly">
							<span id="f_total_amount">${f:h(savedFormData.f_total_amount)}</span>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Agreement Status:" required="true">
						<div class="custom-readonly">
						<!-- 1. One Time / New -->
						<label for="f_agreement_status_one_time">
							<input type="radio" name="f_agreement_status" id="f_agreement_status_one_time" ${f:h(savedFormData.f_agreement_status_one_time)} disabled>One Time / New
						</label>

						<!-- 2. Amendment / Extension / Renewal -->
						<label for="f_agreement_status_amendment">
							<input type="radio" name="f_agreement_status" id="f_agreement_status_amendment" ${f:h(savedFormData.f_agreement_status_amendment)} disabled>Amendment / Extension / Renewal
						</label>

						<!-- Sub-options under Amendment (Indented) -->
						<div style="margin-left: 20px; padding-left: 12px; ${savedFormData.f_agreement_status_amendment == 'checked' ? 'border-left: 2px solid #3b82f6;' : ''} margin-bottom: 8px;">
							<div style="font-size: 12px; color: #4b5563; margin-bottom: 4px; font-weight: 600;">
								Total Duration from first cooperation until now:
							</div>
							<label for="f_total_duration_more_than_1_year">
								<input type="radio" name="f_total_duration" id="f_total_duration_more_than_1_year" ${savedFormData.f_agreement_status_amendment == 'checked' ? f:h(savedFormData.f_total_duration_more_than_1_year) : ''} disabled>More than 1 year
							</label>
							<label for="f_total_duration_up_to_1_year">
								<input type="radio" name="f_total_duration" id="f_total_duration_up_to_1_year" ${savedFormData.f_agreement_status_amendment == 'checked' ? f:h(savedFormData.f_total_duration_up_to_1_year) : ''} disabled>Up to 1 year
							</label>
						</div>

						<!-- 3. Umbrella Agreement -->
						<label for="f_agreement_status_umbrella">
							<input type="radio" name="f_agreement_status" id="f_agreement_status_umbrella" ${f:h(savedFormData.f_agreement_status_umbrella)} disabled>Umbrella Agreement
						</label>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Auto Extension Condition:" required="true">
						<div class="custom-readonly">
							<label for="f_auto_extension_yes">
								<input type="radio" name="f_auto_extension" id="f_auto_extension_yes" ${f:h(savedFormData.f_auto_extension_yes)} disabled>Yes
							</label>
							<label for="f_auto_extension_no">
								<input type="radio" name="f_auto_extension" id="f_auto_extension_no" ${f:h(savedFormData.f_auto_extension_no)} disabled>No
							</label>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Purchase Order Required:" required="true">
						<div class="custom-readonly">
							<label for="f_po_required_yes">
								<input type="radio" name="f_po_required" id="f_po_required_yes" ${f:h(savedFormData.f_po_required_yes)} disabled>Yes
							</label>
							<label for="f_po_required_no">
								<input type="radio" name="f_po_required" id="f_po_required_no" ${f:h(savedFormData.f_po_required_no)} disabled>No
							</label>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Title described in Agreement:" required="true">
						<div class="custom-readonly">
							${f:h(savedFormData.f_agreement_title)}
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Effective Period:" required="true">
						<div class="custom-readonly">
							${f:h(savedFormData.f_effective_from)} ~ ${f:h(savedFormData.f_effective_to)}
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Company Relation:" required="true">
						<div class="custom-readonly">
							<label for="f_company_relation_related_parties">
								<input type="radio" name="f_company_relation" id="f_company_relation_related_parties" ${f:h(savedFormData.f_company_relation_related_parties)} disabled>Related Parties (Shareholders, Subsidiary, Affiliates)
							</label>
							<label for="f_company_relation_non_related_parties">
								<input type="radio" name="f_company_relation" id="f_company_relation_non_related_parties" ${f:h(savedFormData.f_company_relation_non_related_parties)} disabled>Non Related Parties
							</label>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Estimated Delivery Schedule:" required="true">
						<div class="custom-readonly">
							${f:h(savedFormData.f_estimated_delivery_from)} ~ ${f:h(savedFormData.f_estimated_delivery_to)}
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Agreement Summary:">
						<div class="custom-readonly">
							${not empty savedFormData.f_agreement_summary ? f:h(savedFormData.f_agreement_summary) : '-'}
						</div>
					</imsp:fieldContain>
				</div>
			</div>

			<!-- Practice 1: Depreciation Check Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Depreciation Check</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="Purchase Category:" required="true">
						<div class="custom-readonly">
							<label for="f_purchase_category_tangible_asset">
								<input type="radio" name="f_purchase_category" id="f_purchase_category_tangible_asset" ${f:h(savedFormData.f_purchase_category_tangible_asset)} disabled>Tangible Asset
							</label>
							<label for="f_purchase_category_intangible_asset">
								<input type="radio" name="f_purchase_category" id="f_purchase_category_intangible_asset" ${f:h(savedFormData.f_purchase_category_intangible_asset)} disabled>Intangible Asset
							</label>
							<label for="f_purchase_category_non_asset">
								<input type="radio" name="f_purchase_category" id="f_purchase_category_non_asset" ${f:h(savedFormData.f_purchase_category_non_asset)} disabled>Non-Asset
							</label>
						</div>
					</imsp:fieldContain>

					<c:if test="${savedFormData.f_purchase_category_non_asset != 'checked'}">
						<imsp:fieldContain label="Starting Using Date:">
							<div class="custom-readonly">
								${f:h(savedFormData.f_start_using_date)}
							</div>
						</imsp:fieldContain>

						<imsp:fieldContain label="Deprec Amount / Month:">
							<div class="custom-readonly">
								${f:h(savedFormData.f_deprec_month)}
							</div>
						</imsp:fieldContain>
					</c:if>
				</div>
			</div>

			<!-- Practice 2: Multiple Data Selection Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Multiple Data Selection</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="Multiple Data Selection:">
						<div class="custom-readonly">
							<label for="f_multidata_pl">
								<input type="checkbox" name="f_multidata_pl" id="f_multidata_pl" ${f:h(savedFormData.f_multidata_pl)} disabled>PL Impact
							</label>
							<label for="f_multidata_asset">
								<input type="checkbox" name="f_multidata_asset" id="f_multidata_asset" ${f:h(savedFormData.f_multidata_asset)} disabled>Asset
							</label>
							<label for="f_multidata_estimated">
								<input type="checkbox" name="f_multidata_estimated" id="f_multidata_estimated" ${f:h(savedFormData.f_multidata_estimated)} disabled>Estimated Schedule
							</label>
						</div>
					</imsp:fieldContain>
				</div>
			</div>

			<!-- Practice 2: PL Impact Card -->
			<c:if test="${savedFormData.f_multidata_pl == 'checked'}">
				<div class="ui-corner-all custom-corners">
					<div class="ui-bar ui-bar-a">
						<h3>PL Impact</h3>
					</div>
					<div class="ui-body ui-body-a">
						<!-- PL Impact Details -->
							<imsp:fieldContain label="Budget PL Impact / Month:">
								<div class="custom-readonly">
									${f:h(savedFormData.f_budget_pl_impact)} (${f:h(savedFormData.f_budget_pl_month)})
								</div>
							</imsp:fieldContain>
							<imsp:fieldContain label="Actual PL Impact / Month:">
								<div class="custom-readonly">
									${f:h(savedFormData.f_pl_impact)} (${f:h(savedFormData.f_pl_month)})
								</div>
							</imsp:fieldContain>
					</div>
				</div>
			</c:if>

			<!-- Practice 2: Asset Details Card -->
			<c:if test="${savedFormData.f_multidata_asset == 'checked'}">
				<div class="ui-corner-all custom-corners">
					<div class="ui-bar ui-bar-a">
						<h3>Asset Details</h3>
					</div>
					<div class="ui-body ui-body-a">
						<!-- Asset Details -->
							<imsp:fieldContain label="Asset Number:">
								<div class="custom-readonly">
									${f:h(savedFormData.f_asset_number)}
								</div>
							</imsp:fieldContain>
							<imsp:fieldContain label="Book Value:">
								<div class="custom-readonly">
									${f:h(savedFormData.f_book_value)}
								</div>
							</imsp:fieldContain>
					</div>
				</div>
			</c:if>

			<!-- Practice 2: Estimated Schedule (Payment Conditions Table) -->
			<c:if test="${savedFormData.f_multidata_estimated == 'checked'}">
				<div class="ui-corner-all custom-corners">
					<div class="ui-bar ui-bar-a">
						<h3>Estimated Schedule (Payment Conditions)</h3>
					</div>
					<div class="ui-body ui-body-a">
						<div class="custom-readonly" style="overflow-x:scroll">
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
											<td><span class="payment-amount">${f:h(item.payment_amount)}</span></td>
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
												<c:set var="paidBy" value="" />
												<c:if test="${item.paid_by_card == 'checked'}">
													<c:set var="paidBy" value="${empty paidBy ? 'Card' : paidBy.concat(', Card')}" />
												</c:if>
												<c:if test="${item.paid_by_cash == 'checked'}">
													<c:set var="paidBy" value="${empty paidBy ? 'Cash' : paidBy.concat(', Cash')}" />
												</c:if>
												${empty paidBy ? '-' : paidBy}
											</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th colspan="7" class="header-cell" style="text-align: left;">
											Total Amount: <strong id="f_total_payment_amount">${f:h(savedFormData.f_total_payment_amount)}</strong>
										</th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</c:if>

			<!-- Practice 7: Agreement Classification & Multiple Branch Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Agreement Classification</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="Agreement Classification:" required="true">
						<div class="custom-readonly">
							<!-- PD Approval -->
							<label for="f_agreement_classification_pd">
								<input type="radio" name="f_agreement_classification" id="f_agreement_classification_pd" ${f:h(savedFormData.f_agreement_classification_pd)} disabled>PD Approval (either one of condition below)
							</label>

							<!-- PD Approval Sub-Options (Indented) -->
							<div style="margin-left: 20px; padding-left: 12px; ${savedFormData.f_agreement_classification_pd == 'checked' ? 'border-left: 2px solid #3b82f6;' : ''} margin-bottom: 8px;">
								<label for="f_agreement_classification_pd_more_than_1_billion">
									<input type="radio" name="f_agreement_classification_pd_sub" id="f_agreement_classification_pd_more_than_1_billion" ${savedFormData.f_agreement_classification_pd == 'checked' ? f:h(savedFormData.f_agreement_classification_pd_more_than_1_billion) : ''} disabled>Agreement with amount is equal or more than 1 billion
								</label>
								<label for="f_agreement_classification_pd_more_than_12_months">
									<input type="radio" name="f_agreement_classification_pd_sub" id="f_agreement_classification_pd_more_than_12_months" ${savedFormData.f_agreement_classification_pd == 'checked' ? f:h(savedFormData.f_agreement_classification_pd_more_than_12_months) : ''} disabled>Period is equal or more than 12 months
								</label>
								<label for="f_agreement_classification_pd_specific_party">
									<input type="radio" name="f_agreement_classification_pd_sub" id="f_agreement_classification_pd_specific_party" ${savedFormData.f_agreement_classification_pd == 'checked' ? f:h(savedFormData.f_agreement_classification_pd_specific_party) : ''} disabled>Agreement related to specific party
								</label>
								<div style="font-size: 11px; color: #6b7280; margin: -2px 0 6px 4px;">
									<em>Bank, Related Parties, Dealer, Consultant/Lawyer/Appraise, Government, Production, Customer, Etc</em>
								</div>
								<label for="f_agreement_classification_pd_special_issue">
									<input type="radio" name="f_agreement_classification_pd_sub" id="f_agreement_classification_pd_special_issue" ${savedFormData.f_agreement_classification_pd == 'checked' ? f:h(savedFormData.f_agreement_classification_pd_special_issue) : ''} disabled>Special issue
								</label>
								<div style="font-size: 11px; color: #6b7280; margin: -2px 0 6px 4px;">
									<em>New project/issue (more than 50 M), not included in budget plan</em>
								</div>
								<label for="f_agreement_classification_pd_direct_procurement">
									<input type="radio" name="f_agreement_classification_pd_sub" id="f_agreement_classification_pd_direct_procurement" ${savedFormData.f_agreement_classification_pd == 'checked' ? f:h(savedFormData.f_agreement_classification_pd_direct_procurement) : ''} disabled>Direct Procurement (Emergency or Specific Goods/Items)
								</label>
								<label for="f_agreement_classification_pd_agreement_not_more_than_12_months">
									<input type="radio" name="f_agreement_classification_pd_sub" id="f_agreement_classification_pd_agreement_not_more_than_12_months" ${savedFormData.f_agreement_classification_pd == 'checked' ? f:h(savedFormData.f_agreement_classification_pd_agreement_not_more_than_12_months) : ''} disabled>Agreement not more than 12 months
								</label>
							</div>

							<!-- DIC Director Approval -->
							<label for="f_agreement_classification_dic_director_approval">
								<input type="radio" name="f_agreement_classification" id="f_agreement_classification_dic_director_approval" ${f:h(savedFormData.f_agreement_classification_dic_director_approval)} disabled>DIC Director Approval
							</label>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="EC Approval is Required or Not:" required="true">
						<div class="custom-readonly">
							<!-- Yes -->
							<label for="f_agreement_classification_ec_approval_yes">
								<input type="radio" name="f_agreement_classification_ec_approval" id="f_agreement_classification_ec_approval_yes" ${f:h(savedFormData.f_agreement_classification_ec_approval_yes)} disabled>Yes
							</label>

							<!-- EC Approval Sub-Options (Indented) -->
							<div style="margin-left: 20px; padding-left: 12px; ${savedFormData.f_agreement_classification_ec_approval_yes == 'checked' ? 'border-left: 2px solid #3b82f6;' : ''} margin-bottom: 8px;">
								<label for="f_agreement_classification_ec_amount_equal_more_than_1_billion">
									<input type="radio" name="f_agreement_classification_ec_sub" id="f_agreement_classification_ec_amount_equal_more_than_1_billion" ${savedFormData.f_agreement_classification_ec_approval_yes == 'checked' ? f:h(savedFormData.f_agreement_classification_ec_amount_equal_more_than_1_billion) : ''} disabled>Amount is equal or more than 1 billion
								</label>
								<label for="f_agreement_classification_ec_period_equal_more_than_12_months">
									<input type="radio" name="f_agreement_classification_ec_sub" id="f_agreement_classification_ec_period_equal_more_than_12_months" ${savedFormData.f_agreement_classification_ec_approval_yes == 'checked' ? f:h(savedFormData.f_agreement_classification_ec_period_equal_more_than_12_months) : ''} disabled>Period is equal or more than 12 months
								</label>
								<label for="f_agreement_classification_ec_escalate_issue_to_ec">
									<input type="radio" name="f_agreement_classification_ec_sub" id="f_agreement_classification_ec_escalate_issue_to_ec" ${savedFormData.f_agreement_classification_ec_approval_yes == 'checked' ? f:h(savedFormData.f_agreement_classification_ec_escalate_issue_to_ec) : ''} disabled>Director believes it is necessary to escalate the issue to EC
								</label>
							</div>

							<!-- No -->
							<label for="f_agreement_classification_ec_approval_no">
								<input type="radio" name="f_agreement_classification_ec_approval" id="f_agreement_classification_ec_approval_no" ${f:h(savedFormData.f_agreement_classification_ec_approval_no)} disabled>No
							</label>
						</div>
					</imsp:fieldContain>
				</div>
			</div>

			<!-- Practice 8: Approver Node Checks (PSD, CCO, Legal) -->
			<!-- 1. PSD Check Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>PSD Check (by UH or DH, PSD)</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="PSD Area or Non-PSD Area:" required="true">
						<div class="custom-readonly">
							<label for="f_psd_area_psd">
								<input type="radio" name="f_psd_area" id="f_psd_area_psd" ${f:h(savedFormData.f_psd_area_psd)} disabled>PSD (go to #2)
							</label>
							<label for="f_psd_area_non_psd">
								<input type="radio" name="f_psd_area" id="f_psd_area_non_psd" ${f:h(savedFormData.f_psd_area_non_psd)} disabled>Non-PSD (end)
							</label>
						</div>
					</imsp:fieldContain>

					<c:if test="${savedFormData.f_psd_area == 'psd'}">
						<imsp:fieldContain label="In PSD Area, PSD Process, or DIC Process:" required="true">
							<div class="custom-readonly">
								<label for="f_psd_process_psd">
									<input type="radio" name="f_psd_process" id="f_psd_process_psd" ${f:h(savedFormData.f_psd_process_psd)} disabled>PSD (Pitching result attached)
								</label>
								<label for="f_psd_process_dic">
									<input type="radio" name="f_psd_process" id="f_psd_process_dic" ${f:h(savedFormData.f_psd_process_dic)} disabled>DIC (Please describe the reason below)
								</label>
							</div>
						</imsp:fieldContain>

						<c:if test="${savedFormData.f_psd_process_dic == 'checked'}">
							<div style="border-left: 2px solid #3b82f6; margin-left: 10px; padding-left: 10px;">
								<imsp:fieldContain label="Reason for DIC:">
									<div class="custom-readonly">
										${not empty savedFormData.f_dic_reason ? f:h(savedFormData.f_dic_reason) : '-'}
									</div>
								</imsp:fieldContain>
							</div>
						</c:if>
					</c:if>
				</div>
			</div>

			<!-- 2. Compliance Check By CCO Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Compliance Check By CCO</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="D / D Process Required:" required="true">
						<div class="custom-readonly">
							<label for="f_dd_process_yes">
								<input type="radio" name="f_dd_process" id="f_dd_process_yes" ${f:h(savedFormData.f_dd_process_yes)} disabled>Yes
							</label>
							<label for="f_dd_process_no">
								<input type="radio" name="f_dd_process" id="f_dd_process_no" ${f:h(savedFormData.f_dd_process_no)} disabled>No
							</label>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Anti Bribery Clause Included:" required="true">
						<div class="custom-readonly">
							<label for="f_anti_bribery_yes">
								<input type="radio" name="f_anti_bribery" id="f_anti_bribery_yes" ${f:h(savedFormData.f_anti_bribery_yes)} disabled>Yes
							</label>
							<label for="f_anti_bribery_no">
								<input type="radio" name="f_anti_bribery" id="f_anti_bribery_no" ${f:h(savedFormData.f_anti_bribery_no)} disabled>No
							</label>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Audit Rights Included:" required="true">
						<div class="custom-readonly">
							<label for="f_audit_rights_yes">
								<input type="radio" name="f_audit_rights" id="f_audit_rights_yes" ${f:h(savedFormData.f_audit_rights_yes)} disabled>Yes
							</label>
							<label for="f_audit_rights_no">
								<input type="radio" name="f_audit_rights" id="f_audit_rights_no" ${f:h(savedFormData.f_audit_rights_no)} disabled>No
							</label>
						</div>
					</imsp:fieldContain>
				</div>
			</div>

			<!-- 3. Filled by Legal Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Filled by Legal</h3>
				</div>
				<div class="ui-body ui-body-a">
					<imsp:fieldContain label="Agreement Number:" required="true">
						<div class="custom-readonly">
							${not empty savedFormData.f_legal_agreement_number ? f:h(savedFormData.f_legal_agreement_number) : 'Not yet filled'}
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Agreement Date:" required="true">
						<div class="custom-readonly">
							${not empty savedFormData.f_legal_agreement_date ? f:h(savedFormData.f_legal_agreement_date) : 'Not yet filled'}
						</div>
					</imsp:fieldContain>
				</div>
			</div>

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
