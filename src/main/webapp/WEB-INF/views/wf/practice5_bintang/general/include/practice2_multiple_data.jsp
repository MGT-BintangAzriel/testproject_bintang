<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>

<!-- Multiple Data Choice -->
<header class="imui-chapter-title">
	<h2>Multiple Data Selection</h2>
</header>

<table class="imui-form tab_header">
	<colgroup>
		<col style="width: 180px;" />
		<col style="width: 70px;" />
		<col style="width: auto;" />
	</colgroup>
	<tbody>
		<!-- Multi Data Selection -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Multiple Data Selection</label>
			</th>
			<td>
				<label style="display: block; margin-bottom: 4px;">
					<input type="checkbox" name="f_multidata" value="pl" ${f:h(savedFormData.f_multidata_pl)} class="f_multidata"> PL Impact
				</label>
				<label style="display: block;">
					<input type="checkbox" name="f_multidata" value="asset" ${f:h(savedFormData.f_multidata_asset)} class="f_multidata"> Asset
				</label>
				<label style="display: block;">
					<input type="checkbox" name="f_multidata" value="estimated" ${f:h(savedFormData.f_multidata_estimated)} class="f_multidata"> Estimated Schedule
				</label>
				<div class="error_message"></div>
			</td>
		</tr>
	</tbody>
</table>

<!-- PL Impact -->
<div id="section-pl">
	<header class="imui-chapter-title">
		<h2>PL Impact</h2>
	</header>

	<table class="imui-form tab_header">
		<colgroup>
			<col style="width: 30%;" />
			<col style="width: 20%;" />
			<col style="width: 30%;" />
			<col style="width: 20%;" />
		</colgroup>
		<tbody>
			<!-- Header Row -->
			<tr>
				<th style="text-align: left;">
					<label class="imui-required">Budget PL Impact to current FY</label>
				</th>
				<th style="text-align: left;">
					<label class="imui-required">Month</label>
				</th>
				<th style="text-align: left;">
					<label class="imui-required">PL Impact to current FY</label>
				</th>
				<th style="text-align: left;">
					<label class="imui-required">Month</label>
				</th>
			</tr>

			<!-- Input Row underneath -->
			<tr>
				<td>
					<input type="text" id="f_budget_pl_impact" name="f_budget_pl_impact" value="${f:h(savedFormData.f_budget_pl_impact)}"
						style="width: 95%; height: 20px;">
					<div class="error_message"></div>
				</td>
				<td>
					<input type="text" id="f_budget_pl_month" name="f_budget_pl_month" value="${f:h(savedFormData.f_budget_pl_month)}"
						style="width: 95%; height: 20px;">
					<div class="error_message"></div>

				</td>
				<td>
					<input type="text" id="f_pl_impact" name="f_pl_impact" value="${f:h(savedFormData.f_pl_impact)}" style="width: 95%; height: 20px;">
					<div class="error_message"></div>

				</td>
				<td>
					<input type="text" id="f_pl_month" name="f_pl_month" value="${f:h(savedFormData.f_pl_month)}" style="width: 95%; height: 20px;">
					<div class="error_message"></div>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<!-- Asset -->
<div id="section-asset">
	<header class="imui-chapter-title">
		<h2>Asset</h2>
	</header>

	<table class="imui-form tab_header">
		<colgroup>
			<col style="width: 50%;" />
			<col style="width: 50%;" />
		</colgroup>
		<tbody>
			<!-- Header Row -->
			<tr>
				<th style="text-align: left;">
					<label class="imui-required">Asset Number</label>
				</th>
				<th style="text-align: left;">
					<label class="imui-required">Book Value</label>
				</th>
			</tr>

			<!-- Input Row underneath -->
			<tr>
				<td>
					<input type="text" id="f_asset_number" name="f_asset_number" value="${f:h(savedFormData.f_asset_number)}" style="width: 95%; height: 20px;">
					<div class="error_message"></div>
				</td>
				<td>
					<input type="text" id="f_book_value" name="f_book_value" value="${f:h(savedFormData.f_book_value)}" style="width: 95%; height: 20px;">
					<div class="error_message"></div>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<!-- Estimated Schedule (Payment Conditions) -->
<div id="section-estimated">
	<header class="imui-chapter-title">
		<h2>Estimated Schedule (Payment Conditions)</h2>
	</header>

	<div style="overflow-x: auto; width: 100%; margin-bottom: 15px;">
		<table id="payment_detail_table" class="imui-form tab_header" style="min-width: 1020px; table-layout: fixed;">
			<colgroup>
				<col style="width: 40px;" />
				<!-- No. -->
				<col style="width: 220px;" />
				<!-- Brand and Type -->
				<col style="width: 140px;" />
				<!-- Amount -->
				<col style="width: 130px;" />
				<!-- Date -->
				<col style="width: 140px;" />
				<!-- Category -->
				<col style="width: 120px;" />
				<!-- Recurring -->
				<col style="width: 150px;" />
				<!-- Paid By -->
				<col style="width: 80px;" class="table-action-col" />
				<!-- Action (Delete) -->
			</colgroup>
			<thead>
				<tr>
					<th colspan="8" style="text-align: left;">
						<label class="imui-required">Payment (Total Cash Flow Impact)</label>
					</th>
				</tr>
				<tr>
					<th style="text-align: center;">
						<label>No</label>
					</th>
					<th style="text-align: center;">
						<label class="imui-required">Brand and Type</label>
					</th>
					<th style="text-align: center;">
						<label class="imui-required">Amount</label>
					</th>
					<th style="text-align: center;">
						<label class="imui-required">Date</label>
					</th>
					<th style="text-align: center;">
						<label class="imui-required">Category</label>
					</th>
					<th style="text-align: center;">
						<label class="imui-required">Recurring</label>
					</th>
					<th style="text-align: center;">
						<label class="imui-required">Paid By</label>
					</th>
					<th style="text-align: center;" class="table-action-col">
						<label>Action</label>
					</th>
				</tr>
			</thead>
			<tbody>
				<!-- Dynamic Rows from Database -->
				<c:forEach items="${savedFormData.d_list_payment_detail}" var="item">
					<tr class="payment-row">
						<td style="text-align: center;" class="row-seq-no">${f:h(item.row_no)}</td>
						<td style="text-align: center;">
							<div style="display: flex; align-items: center; gap: 6px; justify-content: center; white-space: nowrap;">
								<input type="text" name="f_brand_${item.row_no}" class="f_brand" value="${f:h(item.brand)}" style="width: 45%; height: 20px;" placeholder="Brand">
								<input type="text" name="f_type_${item.row_no}" class="f_type" value="${f:h(item.type)}" style="width: 45%; height: 20px;" placeholder="Type">
							</div>
							<div class="error_message"></div>
						</td>
						<td>
							<input type="text" name="f_payment_amount_${item.row_no}" class="payment-amount f_payment_amount" value="${f:h(item.payment_amount)}"
								style="width: 85%; height: 20px;">
							<div class="error_message"></div>
						</td>
						<td>
							<input type="text" name="f_payment_date_${item.row_no}" class="imuiCalendar payment-date f_payment_date" value="${f:h(item.payment_date)}"
								style="width: 85%; height: 20px;">
							<div class="error_message"></div>
						</td>
						<td>
							<select name="f_category_${item.row_no}" class="select2 f_category" style="width: 85%; height: 24px;">
								<option value="">&nbsp;</option>
								<option value="1" ${item.category == '1' ? 'selected' : ''}>Equipment</option>
								<option value="2" ${item.category == '2' ? 'selected' : ''}>Software</option>
								<option value="3" ${item.category == '3' ? 'selected' : ''}>Utility</option>
								<option value="4" ${item.category == '4' ? 'selected' : ''}>Service</option>
								<option value="5" ${item.category == '5' ? 'selected' : ''}>Other</option>
							</select>
							<div class="error_message"></div>
						</td>
						<td>
							<table style="margin: 0px;">
								<tr>
									<td style="border: none; padding: 0px;">
										<input type="radio" name="f_recurring_${item.row_no}" value="yes" ${f:h(item.recurring_yes)} class="f_recurring" id="f_recurring_yes_${item.row_no}">
									</td>
									<td style="border: none; padding: 0px;">
										<label for="f_recurring_yes_${item.row_no}">Yes</label>
									</td>
								</tr>
								<tr>
									<td style="border: none; padding: 0px;">
										<input type="radio" name="f_recurring_${item.row_no}" value="no" ${f:h(item.recurring_no)} class="f_recurring" id="f_recurring_no_${item.row_no}">
									</td>
									<td style="border: none; padding: 0px;">
										<label for="f_recurring_no_${item.row_no}">No</label>
									</td>
								</tr>
							</table>
							<div class="error_message"></div>
						</td>
						<td>
							<label style="display: block; margin-bottom: 4px;">
								<input type="checkbox" name="f_paid_by_${item.row_no}" value="card" ${f:h(item.paid_by_card)} class="f_paid_by"> Card
							</label>
							<label style="display: block;">
								<input type="checkbox" name="f_paid_by_${item.row_no}" value="cash" ${f:h(item.paid_by_cash)} class="f_paid_by"> Cash
							</label>
							<div class="error_message"></div>
						</td>
						<td style="text-align: center;" class="table-action-col">
							<button type="button" class="imui-button btn-delete-row">Delete</button>
						</td>
					</tr>
				</c:forEach>

				<!-- Initial Blank Row for New Form -->
				<c:if test="${empty savedFormData.d_list_payment_detail and (workflowRequestForm.imwPageType == '0' || workflowRequestForm.imwPageType == '3')}">
					<tr class="payment-row">
						<td style="text-align: center;" class="row-seq-no">1</td>
						<td style="text-align: center;">
							<div style="display: flex; align-items: center; gap: 6px; justify-content: center; white-space: nowrap;">
								<input type="text" name="f_brand_1" class="f_brand" style="width: 45%; height: 20px;" placeholder="Brand"> <input type="text"
									name="f_type_1" class="f_type" style="width: 45%; height: 20px;" placeholder="Type">
							</div>
							<div class="error_message"></div>
						</td>
						<td>
							<input type="text" name="f_payment_amount_1" class="payment-amount f_payment_amount" style="width: 85%; height: 20px;">
							<div class="error_message"></div>
						</td>
						<td>
							<input type="text" name="f_payment_date_1" class="payment-date f_payment_date imuiCalendar" style="width: 85%; height: 20px;">
							<div class="error_message"></div>
						</td>
						<td>
							<select name="f_category_1" class="select2 f_category" style="width: 85%; height: 24px;">
								<option value="">&nbsp;</option>
								<option value="1">Equipment</option>
								<option value="2">Software</option>
								<option value="3">Utility</option>
								<option value="4">Service</option>
								<option value="5">Other</option>
							</select>
							<div class="error_message"></div>
						</td>
						<td>
							<!-- <label style="margin-right: 6px;"><input type="radio" name="f_recurring_1" class="f_recurring" value="1"> Yes</label> <label><input
							type="radio" name="f_recurring_1" class="f_recurring" value="0"> No</label> -->
							<table style="margin: 0px;">
								<tr>
									<td style="border: none; padding: 0px;">
										<input type="radio" name="f_recurring_1" value="yes" class="f_recurring" id="f_recurring_yes_1">
									</td>
									<td style="border: none; padding: 0px;">
										<label for="f_recurring_yes_1">Yes</label>
									</td>
								</tr>
								<tr>
									<td style="border: none; padding: 0px;">
										<input type="radio" name="f_recurring_1" value="no" class="f_recurring" id="f_recurring_no_1">
									</td>
									<td style="border: none; padding: 0px;">
										<label for="f_recurring_no_1">No</label>
									</td>
								</tr>
							</table>
							<div class="error_message"></div>
						</td>
						<td>
							<label style="display: block; margin-bottom: 4px;">
								<input type="checkbox" name="f_paid_by_1" class="f_paid_by" value="card"> Card
							</label>
							<label>
								<input type="checkbox" name="f_paid_by_1" class="f_paid_by" value="cash"> Cash
							</label>
							<div class="error_message"></div>
						</td>
						<td style="text-align: center;" class="table-action-col">
							<button type="button" class="imui-button btn-delete-row">Delete</button>
						</td>
					</tr>
				</c:if>
			</tbody>
			<tfoot>
				<!-- Add Row Button -->
				<tr>
					<td colspan="8" style="padding-top: 6px; padding-bottom: 6px;" class="table-action-col">
						<button type="button" id="btn_add_payment_row" class="imui-button">
							<span class="im-ui-icon-common-16-plus"></span> Add Row
						</button>
					</td>
				</tr>
				<tr>
					<th colspan="8" style="text-align: left;">
						<label class="imui-required">Total Amount</label>
					</th>
				</tr>
				<tr>
					<td colspan="8">
						<input readonly type="text" id="f_total_payment_amount" name="f_total_payment_amount" value="${f:h(savedFormData.f_total_payment_amount)}"
							style="width: 98%; height: 20px;">
						<div class="error_message"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>

