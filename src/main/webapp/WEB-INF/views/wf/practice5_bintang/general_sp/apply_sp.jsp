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
	<script src="ui/js/workflow-form.js" type="text/javascript"></script>
	<script src="ui/js/workflow-form-validation.js" type="text/javascript"></script>
	<script src="ui/js/select2-4013.min.js"></script>
	<script src="ui/js/select2.min.js"></script>
	<link href="ui/css/select2-4013.min.css" rel="stylesheet" />
	<link href="ui/css/select2.min.css" rel="stylesheet" />
	<link href="ui/css/workflow-sp.css" rel="stylesheet" />

	<script type="text/javascript">

		$(function() {
			setupMobileRadioValidation();
			setupMobileDatePickerValidation();

			formatNumberText();

			setupSubOptionToggle('f_agreement_status', 'amendment', 'f_total_duration', 'f_total_duration_1');
      		setupSubOptionToggle('f_agreement_classification', 'pd', 'f_pd_sub_condition', 'f_pd_sub_1');
      		setupSubOptionToggle('f_ec_approval', 'yes', 'f_ec_sub_condition', 'f_ec_sub_1');

			toggleDepreciation();
      		$('input[name="f_purchase_category"]').on("change", toggleDepreciation);

			setupMultiDataToggle();
			setupDynamicPaymentSp();

			$('.back').click(function() {
				$('#backForm').submit();
				return false;
			});

			$(".select2").select2({
				theme: "classic",
			});

			// Format numeric field
			$("#f_total_amount, #f_total_payment_amount").on("change", function() {
				var input = $(this);
				var value = input.val();
				if (value) {
					var unformatted = value.replace(/[^0-9.]/g, '');
					var unformattedNum = parseFloat(unformatted) || 0;
					if (unformattedNum) {
						input.val(unformattedNum.toLocaleString('en-US', {
						minimumFractionDigits : 2,
						maximumFractionDigits : 2
						}));
					} else {
						input.val('');
					}
				}
			});

			$('#openPage').click(function() {
				isApplyClicked = true;
				var valid = validateWorkflowForm();

				if (valid) {
					workflowOpenPage4Sp('${f:h(workflowRequestForm.imwPageType)}');
					return false;
				} else {
					alert('There are validation errors. Please check your inputs.');
					return false;
				}
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

<div data-role="page" id="imw-sp-agreement-apply" data-theme="a">
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
						<div class="custom-readonly">${f:h(savedFormData.f_application_number)}</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Application Date:">
						<div class="custom-readonly">${f:h(savedFormData.f_application_date)}</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Applicant Number:">
						<div class="custom-readonly">${f:h(savedFormData.f_applicant_number)}</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Applicant Name:">
						<div class="custom-readonly">${f:h(savedFormData.f_applicant_name)}</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Department:">
						<div class="custom-readonly">${f:h(savedFormData.f_applicant_department)}</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Position / Post:">
						<div class="custom-readonly">${f:h(savedFormData.f_applicant_post)}</div>
					</imsp:fieldContain>

					<input type="hidden" id="f_application_number" name="f_application_number" value="${f:h(savedFormData.f_application_number)}">
					<input type="hidden" id="f_application_date" name="f_application_date" value="${f:h(savedFormData.f_application_date)}">
					<input type="hidden" id="f_applicant_number" name="f_applicant_number" value="${f:h(savedFormData.f_applicant_number)}">
					<input type="hidden" id="f_applicant_name" name="f_applicant_name" value="${f:h(savedFormData.f_applicant_name)}">
					<input type="hidden" id="f_applicant_department" name="f_applicant_department" value="${f:h(savedFormData.f_applicant_department)}">
					<input type="hidden" id="f_applicant_post" name="f_applicant_post" value="${f:h(savedFormData.f_applicant_post)}">
					
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
							<input type="text" id="f_counter_party" name="f_counter_party" value="${f:h(savedFormData.f_counter_party)}"
								placeholder="Enter counter party...">
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Currency:" required="true">
						<select name="f_currency" id="f_currency" data-native-menu="false" data-role="none" class="select2" disabled>
							<option value="IDR" ${empty savedFormData.f_currency || savedFormData.f_currency == 'IDR' ? 'selected' : ''}>IDR - Indonesian Rupiah</option>
							<option value="USD" ${savedFormData.f_currency == 'USD' ? 'selected' : ''}>USD - US Dollar</option>
							<option value="JPY" ${savedFormData.f_currency == 'JPY' ? 'selected' : ''}>JPY - Japanese Yen</option>
							<option value="EUR" ${savedFormData.f_currency == 'EUR' ? 'selected' : ''}>EUR - Euro</option>
							<option value="SGD" ${savedFormData.f_currency == 'SGD' ? 'selected' : ''}>SGD - Singapore Dollar</option>
						</select>
						<input type="hidden" id="f_currency" name="f_currency" value="${f:h(empty savedFormData.f_currency ? 'IDR' : savedFormData.f_currency)}">
						<div class="error_message"></div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Total Amount (Without Tax):" required="true">
						<div class="custom-readonly">
							<input type="text" id="f_total_amount" name="f_total_amount" value="${f:h(savedFormData.f_total_amount)}"
								placeholder="Enter total amount...">
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Agreement Status:" required="true">
						<div class="custom-readonly">
							<!-- 1. One Time / New -->
							<label for="f_agreement_status_1">
								<input type="radio" name="f_agreement_status" id="f_agreement_status_1" value="one_time" ${f:h(savedFormData.f_agreement_status_one_time)}>
								One Time / New
							</label>

							<!-- 2. Amendment / Extension / Renewal -->
							<label for="f_agreement_status_2">
								<input type="radio" name="f_agreement_status" id="f_agreement_status_2" value="amendment" ${f:h(savedFormData.f_agreement_status_amendment)}>
								Amendment / Extension / Renewal
							</label>

							<!-- Sub-options under Amendment (Indented) -->
							<div style="margin-left: 20px; padding-left: 12px; ${savedFormData.f_agreement_status_amendment == 'checked' ? 'border-left: 2px solid #3b82f6;' : ''} margin-bottom: 8px;">
								<div style="font-size: 12px; color: #4b5563; margin-bottom: 4px; font-weight: 600;">Total Duration from first cooperation until now:</div>
								<label for="f_total_duration_1">
									<input type="radio" name="f_total_duration" id="f_total_duration_1" value="more_than_1_year"
										${savedFormData.f_agreement_status_amendment == 'checked' ? f:h(savedFormData.f_total_duration_more_than_1_year) : ''}>
									More than 1 year
								</label>
								<label for="f_total_duration_2">
									<input type="radio" name="f_total_duration" id="f_total_duration_2" value="up_to_1_year"
										${savedFormData.f_agreement_status_amendment == 'checked' ? f:h(savedFormData.f_total_duration_up_to_1_year) : ''}>
									Up to 1 year
								</label>
							</div>

							<!-- 3. Umbrella Agreement -->
							<label for="f_agreement_status_3">
								<input type="radio" name="f_agreement_status" id="f_agreement_status_3" value="umbrella" ${f:h(savedFormData.f_agreement_status_umbrella)}>
								Umbrella Agreement
							</label>
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Auto Extension Condition:" required="true">
						<div class="custom-readonly">
							<label for="f_auto_extension_1">
								<input type="radio" name="f_auto_extension" id="f_auto_extension_1" value="yes" ${f:h(savedFormData.f_auto_extension_yes)}>
								Yes
							</label>
							<label for="f_auto_extension_2">
								<input type="radio" name="f_auto_extension" id="f_auto_extension_2" value="no" ${f:h(savedFormData.f_auto_extension_no)}>
								No
							</label>
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Purchase Order Required:" required="true">
						<div class="custom-readonly">
							<label for="f_po_required_1">
								<input type="radio" name="f_po_required" id="f_po_required_1" value="yes" ${f:h(savedFormData.f_po_required_yes)}>
								Yes
							</label>
							<label for="f_po_required_2">
								<input type="radio" name="f_po_required" id="f_po_required_2" value="no" ${f:h(savedFormData.f_po_required_no)}>
								No
							</label>
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Title described in Agreement:" required="true">
						<div class="custom-readonly">
							<input type="text" id="f_agreement_title" name="f_agreement_title" value="${f:h(savedFormData.f_agreement_title)}"
								placeholder="Enter agreement title...">
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Effective From:" required="true">
						<div class="custom-readonly">
							<imsp:datePicker id="f_effective_from" name="f_effective_from" format="yyyy/MM/dd" value="${f:h(savedFormData.f_effective_from)}"
								placeholder="Enter effective from..." onClose="onDatePickerClose"/>
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Effective To:" required="true">
						<div class="custom-readonly">
							<imsp:datePicker id="f_effective_to" name="f_effective_to" format="yyyy/MM/dd" value="${f:h(savedFormData.f_effective_to)}"
								placeholder="Enter effective to..." onClose="onDatePickerClose"/>
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Company Relation:" required="true">
						<div class="custom-readonly">
							<label for="f_company_relation_1">
								<input type="radio" name="f_company_relation" id="f_company_relation_1" value="related_parties" ${f:h(savedFormData.f_company_relation_related_parties)}>
								Related Parties (Shareholders, Subsidiary, Affiliates)
							</label>
							<label for="f_company_relation_2">
								<input type="radio" name="f_company_relation" id="f_company_relation_2" value="non_related_parties" ${f:h(savedFormData.f_company_relation_non_related_parties)}>
								Non Related Parties
							</label>
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Estimated Delivery From:" required="true">
						<imsp:datePicker id="f_estimated_delivery_from" name="f_estimated_delivery_from" format="yyyy/MM/dd" value="${f:h(savedFormData.f_estimated_delivery_from)}"
							placeholder="Enter estimated delivery from..." onClose="onDatePickerClose"/>
						<div class="error_message"></div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Estimated Delivery To:" required="true">
						<imsp:datePicker id="f_estimated_delivery_to" name="f_estimated_delivery_to" format="yyyy/MM/dd" value="${f:h(savedFormData.f_estimated_delivery_to)}"
							placeholder="Enter estimated delivery to..." onClose="onDatePickerClose"/>
						<div class="error_message"></div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Agreement Summary:">
						<div class="custom-readonly">
							<textarea rows="4" id="f_agreement_summary" name="f_agreement_summary" class="form-input-textarea">${f:h(savedFormData.f_agreement_summary)}</textarea>
							<div class="error_message"></div>
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
								<input type="radio" name="f_purchase_category" id="f_purchase_category_1" value="tangible_asset"
									${f:h(savedFormData.f_purchase_category_tangible_asset)}>
								Tangible Asset
							</label>
							<label for="f_purchase_category_2">
								<input type="radio" name="f_purchase_category" id="f_purchase_category_2" value="intangible_asset"
									${f:h(savedFormData.f_purchase_category_intangible_asset)}>
								Intangible Asset
							</label>
							<label for="f_purchase_category_3">
								<input type="radio" name="f_purchase_category" id="f_purchase_category_3" value="non_asset" ${f:h(savedFormData.f_purchase_category_non_asset)}>
								Non-Asset
							</label>
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>

					<div id="section-depreciation">
						<imsp:fieldContain label="Starting Using Date:" required="true">
						<imsp:datePicker id="f_start_using_date" name="f_start_using_date" format="yyyy/MM/dd" value="${f:h(savedFormData.f_start_using_date)}"
							placeholder="Enter starting using date..." onClose="onDatePickerClose"/>
						<div class="error_message"></div>
					</imsp:fieldContain>

					<imsp:fieldContain label="Deprec Amount / Month:" required="true">
						<div class="custom-readonly">
							<input type="text" id="f_deprec_month" name="f_deprec_month" value="${f:h(savedFormData.f_deprec_month)}"
								placeholder="Enter deprec amount / month...">
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>
					</div>
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
								<input type="checkbox" name="f_multidata" value="pl" id="f_multidata_pl" class="f_multidata" ${f:h(savedFormData.f_multidata_pl)}>
								PL Impact
							</label>
							<label for="f_multidata_asset">
								<input type="checkbox" name="f_multidata" value="asset" id="f_multidata_asset" class="f_multidata" ${f:h(savedFormData.f_multidata_asset)}>
								Asset
							</label>
							<label for="f_multidata_estimated">
								<input type="checkbox" name="f_multidata" value="estimated" id="f_multidata_estimated" class="f_multidata" ${f:h(savedFormData.f_multidata_estimated)}>
								Estimated Schedule
							</label>
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>
				</div>
			</div>

			<!-- Practice 2: PL Impact Card -->
			<div id="section-pl" class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>PL Impact</h3>
				</div>
				<div class="ui-body ui-body-a">
					<!-- PL Impact Details -->
					<imsp:fieldContain label="Budget PL Impact to current FY:" required="true">
						<div class="custom-readonly">
							<input type="text" id="f_budget_pl_impact" name="f_budget_pl_impact" value="${f:h(savedFormData.f_budget_pl_impact)}"
								placeholder="Enter budget PL impact...">
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>
					<imsp:fieldContain label="Budget PL Month:" required="true">
						<div class="custom-readonly">
							<input type="text" id="f_budget_pl_month" name="f_budget_pl_month" value="${f:h(savedFormData.f_budget_pl_month)}"
								placeholder="Enter budget PL month...">
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>
					<imsp:fieldContain label="PL Impact to current FY:" required="true">
						<div class="custom-readonly">
							<input type="text" id="f_pl_impact" name="f_pl_impact" value="${f:h(savedFormData.f_pl_impact)}"
								placeholder="Enter PL impact...">
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>
					<imsp:fieldContain label="PL Month:" required="true">
						<div class="custom-readonly">
							<input type="text" id="f_pl_month" name="f_pl_month" value="${f:h(savedFormData.f_pl_month)}"
								placeholder="Enter PL month...">
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>
				</div>
			</div>

			<!-- Practice 2: Asset Details Card -->
			<div id="section-asset" class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Asset</h3>
				</div>
				<div class="ui-body ui-body-a">
					<!-- Asset Details -->
					<imsp:fieldContain label="Asset Number:" required="true">
						<div class="custom-readonly">
							<input type="text" id="f_asset_number" name="f_asset_number" value="${f:h(savedFormData.f_asset_number)}"
								placeholder="Enter asset number...">
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>
					<imsp:fieldContain label="Book Value:" required="true">
						<div class="custom-readonly">
							<input type="text" id="f_book_value" name="f_book_value" value="${f:h(savedFormData.f_book_value)}"
								placeholder="Enter book value...">
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>
				</div>
			</div>

			<!-- Practice 2: Estimated Schedule (Payment Conditions Table) -->
			<div id="section-estimated" class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Estimated Schedule (Payment Conditions)</h3>
				</div>
				<div class="ui-body ui-body-a" style="padding: 10px 8px !important;">
					<div id="sp_payment_cards_container">
						<c:choose>
							<c:when test="${not empty savedFormData.d_list_payment_detail}">
								<c:forEach items="${savedFormData.d_list_payment_detail}" var="item">
									<div class="payment-card payment-row">
										<div class="payment-card-header">
											<span class="payment-card-title">Payment Item #<span class="row-seq-no">${f:h(item.row_no)}</span></span>
											<button type="button" class="btn-delete-row payment-card-del-btn" data-role="none">
												<i class="fa-solid fa-trash-can"></i> Delete
											</button>
										</div>
										<div class="payment-card-body">
											<imsp:fieldContain label="Brand & Type:" required="true">
												<div style="display: flex; gap: 8px;">
													<input type="text" name="f_brand_${item.row_no}" class="f_brand" value="${f:h(item.brand)}" placeholder="Enter brand...">
													<input type="text" name="f_type_${item.row_no}" class="f_type" value="${f:h(item.type)}" placeholder="Enter type...">
												</div>
												<div class="error_message"></div>
											</imsp:fieldContain>

											<imsp:fieldContain label="Amount:" required="true">
												<input type="text" name="f_payment_amount_${item.row_no}" class="payment-amount f_payment_amount" value="${f:h(item.payment_amount)}" placeholder="Enter amount...">
												<div class="error_message"></div>
											</imsp:fieldContain>

											<imsp:fieldContain label="Payment Date:" required="true">
												<imsp:datePicker id="f_payment_date_${item.row_no}" name="f_payment_date_${item.row_no}" class="payment-date f_payment_date" format="yyyy/MM/dd" value="${f:h(item.payment_date)}"
																placeholder="Enter payment date..." onClose="onDatePickerClose"/>
												<div class="error_message"></div>
											</imsp:fieldContain>

											<imsp:fieldContain label="Category:" required="true">
												<select name="f_category_${item.row_no}" id="f_category_${item.row_no}" data-native-menu="false" data-role="none" class="select2 f_category">
													<option value="">Choose category...</option>
													<option value="1" ${item.category == '1' ? 'selected' : ''}>Equipment</option>
													<option value="2" ${item.category == '2' ? 'selected' : ''}>Software</option>
													<option value="3" ${item.category == '3' ? 'selected' : ''}>Utility</option>
													<option value="4" ${item.category == '4' ? 'selected' : ''}>Service</option>
													<option value="5" ${item.category == '5' ? 'selected' : ''}>Other</option>
												</select>
												<div class="error_message"></div>
											</imsp:fieldContain>

											<imsp:fieldContain label="Recurring:" required="true">
												<div class="custom-readonly">
													<label for="f_recurring_yes_${item.row_no}">
														<input type="radio" name="f_recurring_${item.row_no}" id="f_recurring_yes_${item.row_no}" value="yes" ${f:h(item.recurring_yes)} class="f_recurring"> Yes
													</label>
													<label for="f_recurring_no_${item.row_no}">
														<input type="radio" name="f_recurring_${item.row_no}" id="f_recurring_no_${item.row_no}" value="no" ${f:h(item.recurring_no)} class="f_recurring"> No
													</label>
													<div class="error_message"></div>
												</div>
											</imsp:fieldContain>

											<imsp:fieldContain label="Paid By:" required="true">
												<div class="custom-readonly">
													<label for="f_paid_by_card_${item.row_no}">
														<input type="checkbox" name="f_paid_by_${item.row_no}" id="f_paid_by_card_${item.row_no}" value="card" ${f:h(item.paid_by_card)} class="f_paid_by"> Card
													</label>
													<label for="f_paid_by_cash_${item.row_no}">
														<input type="checkbox" name="f_paid_by_${item.row_no}" id="f_paid_by_cash_${item.row_no}" value="cash" ${f:h(item.paid_by_cash)} class="f_paid_by"> Cash
													</label>
													<div class="error_message"></div>
												</div>
											</imsp:fieldContain>
										</div>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<!-- Initial Default Item #1 -->
								<div class="payment-card payment-row">
									<div class="payment-card-header">
										<span class="payment-card-title">Payment Item #<span class="row-seq-no">1</span></span>
										<button type="button" class="btn-delete-row payment-card-del-btn" data-role="none">
											<i class="fa-solid fa-trash-can"></i> Delete
										</button>
									</div>
									<div class="payment-card-body">
										<imsp:fieldContain label="Brand & Type:" required="true">
											<div style="display: flex; gap: 8px;">
												<input type="text" name="f_brand_1" class="f_brand" placeholder="Enter brand...">
												<input type="text" name="f_type_1" class="f_type" placeholder="Enter type...">
											</div>
											<div class="error_message"></div>
										</imsp:fieldContain>

										<imsp:fieldContain label="Amount:" required="true">
											<input type="text" name="f_payment_amount_1" class="payment-amount f_payment_amount" placeholder="Enter amount...">
											<div class="error_message"></div>
										</imsp:fieldContain>

										<imsp:fieldContain label="Payment Date:" required="true">
											<imsp:datePicker id="f_payment_date_1" name="f_payment_date_1" class="payment-date f_payment_date" format="yyyy/MM/dd" value=""
																placeholder="Enter payment date..." onClose="onDatePickerClose"/>
											<div class="error_message"></div>
										</imsp:fieldContain>

										<imsp:fieldContain label="Category:" required="true">
											<select name="f_category_1" class="select2 f_category" id="f_category_1" data-native-menu="false" data-role="none">
												<option value="">Choose category...</option>
												<option value="1">Equipment</option>
												<option value="2">Software</option>
												<option value="3">Utility</option>
												<option value="4">Service</option>
												<option value="5">Other</option>
											</select>
											<div class="error_message"></div>
										</imsp:fieldContain>

										<imsp:fieldContain label="Recurring:" required="true">
											<div class="custom-readonly">
												<label for="f_recurring_yes_1">
													<input type="radio" name="f_recurring_1" id="f_recurring_yes_1" value="yes" class="f_recurring"> Yes
												</label>
												<label for="f_recurring_no_1">
													<input type="radio" name="f_recurring_1" id="f_recurring_no_1" value="no" class="f_recurring"> No
												</label>
												<div class="error_message"></div>
											</div>
										</imsp:fieldContain>

										<imsp:fieldContain label="Paid By:" required="true">
											<div class="custom-readonly">
												<label for="f_paid_by_card_1">
													<input type="checkbox" name="f_paid_by_1" id="f_paid_by_card_1" value="card" class="f_paid_by"> Card
												</label>
												<label for="f_paid_by_cash_1">
													<input type="checkbox" name="f_paid_by_1" id="f_paid_by_cash_1" value="cash" class="f_paid_by"> Cash
												</label>
												<div class="error_message"></div>
											</div>
										</imsp:fieldContain>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>

					<!-- Button to Add Payment Item -->
					<div style="margin: 14px 0 10px 0;">
						<button type="button" id="btn_add_payment_sp" class="btn-add-payment-sp" data-role="none">
							<i class="fa-solid fa-plus"></i> Add Payment Item
						</button>
					</div>

					<!-- Total Payment Amount -->
					<imsp:fieldContain label="Total Payment Amount:">
						<div class="custom-readonly">
							<input type="text" id="f_total_payment_amount" name="f_total_payment_amount" value="${f:h(savedFormData.f_total_payment_amount)}" readonly placeholder="0.00">
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>
				</div>
			</div>

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
								<input type="radio" name="f_agreement_classification" id="f_agreement_classification_2" value="pd" ${f:h(savedFormData.f_agreement_classification_pd)}>
								PD Approval (either one of condition below)
							</label>

							<!-- PD Approval Sub-Options (Indented) -->
							<div style="margin-left: 20px; padding-left: 12px; ${savedFormData.f_agreement_classification_pd == 'checked' ? 'border-left: 2px solid #3b82f6;' : ''} margin-bottom: 8px;">
								<label for="f_agreement_classification_pd_more_than_1_billion">
									<input type="radio" name="f_pd_sub_condition" id="f_pd_sub_1" value="pd_more_than_1_billion"
										${savedFormData.f_agreement_classification_pd == 'checked' ? f:h(savedFormData.f_agreement_classification_pd_more_than_1_billion) : ''}>
									Agreement with amount is equal or more than 1 billion
								</label>
								<label for="f_agreement_classification_pd_more_than_12_months">
									<input type="radio" name="f_pd_sub_condition" id="f_pd_sub_2" value="pd_more_than_12_months"
										${savedFormData.f_agreement_classification_pd == 'checked' ? f:h(savedFormData.f_agreement_classification_pd_more_than_12_months) : ''}>
									Period is equal or more than 12 months
								</label>
								<label for="f_agreement_classification_pd_specific_party">
									<input type="radio" name="f_pd_sub_condition" id="f_pd_sub_3" value="pd_specific_party"
										${savedFormData.f_agreement_classification_pd == 'checked' ? f:h(savedFormData.f_agreement_classification_pd_specific_party) : ''}>
									Agreement related to specific party
								</label>
								<div style="font-size: 11px; color: #6b7280; margin: -2px 0 6px 4px;">
									<em>Bank, Related Parties, Dealer, Consultant/Lawyer/Appraise, Government, Production, Customer, Etc</em>
								</div>
								<label for="f_agreement_classification_pd_special_issue">
									<input type="radio" name="f_pd_sub_condition" id="f_pd_sub_4" value="pd_special_issue"
										${savedFormData.f_agreement_classification_pd == 'checked' ? f:h(savedFormData.f_agreement_classification_pd_special_issue) : ''}>
									Special issue
								</label>
								<div style="font-size: 11px; color: #6b7280; margin: -2px 0 6px 4px;">
									<em>New project/issue (more than 50 M), not included in budget plan</em>
								</div>
								<label for="f_agreement_classification_pd_direct_procurement">
									<input type="radio" name="f_pd_sub_condition" id="f_pd_sub_5" value="pd_direct_procurement"
										${savedFormData.f_agreement_classification_pd == 'checked' ? f:h(savedFormData.f_agreement_classification_pd_direct_procurement) : ''}>
									Direct Procurement (Emergency or Specific Goods/Items)
								</label>
								<label for="f_agreement_classification_pd_agreement_not_more_than_12_months">
									<input type="radio" name="f_pd_sub_condition" id="f_pd_sub_6" value="pd_agreement_not_more_than_12_months"
										${savedFormData.f_agreement_classification_pd == 'checked' ? f:h(savedFormData.f_agreement_classification_pd_agreement_not_more_than_12_months) : ''}>
									Agreement not more than 12 months
								</label>
							</div>

							<!-- DIC Director Approval -->
							<label for="f_agreement_classification_dic_director_approval">
								<input type="radio" name="f_agreement_classification" id="f_agreement_classification_3" value="dic_director_approval"
									${f:h(savedFormData.f_agreement_classification_dic_director_approval)}>
								DIC Director Approval
							</label>
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>

					<imsp:fieldContain label="EC Approval is Required or Not:" required="true">
						<div class="custom-readonly">
							<!-- Yes -->
							<label for="f_ec_approval_1">
								<input type="radio" name="f_ec_approval" id="f_ec_approval_1" value="yes"
									${f:h(savedFormData.f_agreement_classification_ec_approval_yes)}>
								Yes
							</label>

							<!-- EC Approval Sub-Options (Indented) -->
							<div
								style="margin-left: 20px; padding-left: 12px; ${savedFormData.f_agreement_classification_ec_approval_yes == 'checked' ? 'border-left: 2px solid #3b82f6;' : ''} margin-bottom: 8px;">
								<label for="f_ec_sub_1">
									<input type="radio" name="f_ec_sub_condition" id="f_ec_sub_1" value="ec_amount_equal_more_than_1_billion"
										${savedFormData.f_agreement_classification_ec_approval_yes == 'checked' ? f:h(savedFormData.f_agreement_classification_ec_amount_equal_more_than_1_billion) : ''}>
									Amount is equal or more than 1 billion
								</label>
								<label for="f_ec_sub_2">
									<input type="radio" name="f_ec_sub_condition" id="f_ec_sub_2" value="ec_period_equal_more_than_12_months"
										${savedFormData.f_agreement_classification_ec_approval_yes == 'checked' ? f:h(savedFormData.f_agreement_classification_ec_period_equal_more_than_12_months) : ''}>
									Period is equal or more than 12 months
								</label>
								<label for="f_ec_sub_3">
									<input type="radio" name="f_ec_sub_condition" id="f_ec_sub_3" value="ec_escalate_issue_to_ec"
										${savedFormData.f_agreement_classification_ec_approval_yes == 'checked' ? f:h(savedFormData.f_agreement_classification_ec_escalate_issue_to_ec) : ''}>
									Director believes it is necessary to escalate the issue to EC
								</label>
							</div>

							<!-- No -->
							<label for="f_ec_approval_0">
								<input type="radio" name="f_ec_approval" id="f_ec_approval_0" value="no"
									${f:h(savedFormData.f_agreement_classification_ec_approval_no)}>
								No
							</label>
							<div class="error_message"></div>
						</div>
					</imsp:fieldContain>
				</div>
			</div>

			<jsp:include page="include/practice8_sp_display.jsp" />

		<div class="file_attachment">
			<input type='text' value='' name='f_attachment_anchor' id='f_attachment_anchor' class="f_attachment_anchor"
				data-role="none"
				style="position: absolute; opacity: 0; pointer-events: none; width: 1px; height: 1px; left: -9999px; border: 0; margin: 0; padding: 0; outline: none;" tabindex="-1">
			<c:forEach items="${savedFormData.d_list_attachment}" var="attachment">
				<div class="${attachment.file_real_name}">
					<input type='hidden' value='${attachment.id}' id='f_upload_file_id' name='f_upload_file_id' class='f_upload_file_id'>
					<input type='hidden' value="${attachment.file_name}" id='f_upload_file_name' name='f_upload_file_name'>
					<input type='hidden' value="${attachment.file_real_name}" id='f_upload_file_real_name' name='f_upload_file_real_name'>
					<input type='hidden' value="${attachment.file_type}" id="f_upload_file_type" name="f_upload_file_type">
					<input type='hidden' value="${attachment.file_size}" id="f_upload_file_size" name="f_upload_file_size">
					<input type='hidden' value="${attachment.file_extension}" id="f_upload_file_extension" name="f_upload_file_extension">
				</div>
			</c:forEach>
		</div>

		</workflowSmartphone:spWorkflowOpenPage>

		<c:if test="${workflowRequestForm.imwPageType == '10' || workflowRequestForm.imwPageType == '13'}">
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Upload Document by DIC : Agreement, DD, etc</h3>
				</div>
				<div class="ui-body ui-body-a attachment-card-body">
					<imsp:fieldContain label="Upload File" required="true">
						<imsp:fileUpload 
							id="spFileUpload" 
							storeTo="file_attachment/" 
							autoUpload="true" 
							multiple="true" 
							enableDelete="true" 
							onSuccess="callbackSuccessSp" 
							onError="callbackErrorSp"
							onRemove="callbackRemoveSp" />
						<div class="error_message_upload"></div>
					</imsp:fieldContain>
				</div>
			</div>
		</c:if>

		<c:if test="${workflowRequestForm.imwPageType == '13' || workflowRequestForm.imwPageType == '14' || workflowRequestForm.imwPageType == '15'}">
			<!-- Practice 3: Attachments Card -->
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Attached Documents</h3>
				</div>
				<div class="ui-body ui-body-a attachment-card-body">
					<c:choose>
						<c:when test="${not empty savedFormData.d_list_attachment}">
							<table class="attachment-list">
								<tbody>
									<c:forEach items="${savedFormData.d_list_attachment}" var="file">
										<tr>
											<td>
												<a target="_blank"
													href="practice5_bintang/download/${f:h(file.id)}?token=${f:h(savedFormData.f_download_token)}&system_matter_id=${f:h(workflowRequestForm.imwSystemMatterId)}"
													class="attachment-link">
													<i class="fa-solid fa-file-arrow-down fa-lg"></i>
													<span>${f:h(file.file_name)}</span>
												</a>
												<span class="attachment-size">(${f:h(file.file_size_convert)})</span>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<div class="no-attachment-msg">No documents attached</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:if>

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
					<a href="${pageContext.request.contextPath}/home" data-ajax="false" target="_top">
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
