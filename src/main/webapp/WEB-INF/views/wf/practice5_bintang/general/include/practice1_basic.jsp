<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>

<header class="imui-chapter-title">
	<h2>Agreement Detail</h2>
</header>

<table class="imui-form tab_header">
	<colgroup>
		<col style="width: 180px;" />
		<col style="width: 70px;" />
		<col style="width: auto;" />
	</colgroup>
	<tbody>

		<!-- Counter Party -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Counter Party (Vendor name, etc)</label>
			</th>
			<td>
				<input type="text" id="f_counter_party" name="f_counter_party" value="${f:h(savedFormData.f_counter_party)}" class="form-input-text">
				<div class="error_message"></div>
			</td>
		</tr>

		<!-- Currency -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Currency</label>
			</th>
			<td>
				<select id="f_currency_select" name="f_currency_select" class="form-select-currency" disabled>
					<option value="IDR" ${savedFormData.f_currency == 'IDR' ? 'selected' : ''}>IDR</option>
					<option value="USD" ${savedFormData.f_currency == 'USD' ? 'selected' : ''}>USD</option>
					<option value="JPY" ${savedFormData.f_currency == 'JPY' ? 'selected' : ''}>JPY</option>
					<option value="EUR" ${savedFormData.f_currency == 'EUR' ? 'selected' : ''}>EUR</option>
					<option value="SGD" ${savedFormData.f_currency == 'SGD' ? 'selected' : ''}>SGD</option>
				</select> <input type="hidden" id="f_currency" name="f_currency" value="${f:h(empty savedFormData.f_currency ? 'IDR' : savedFormData.f_currency)}">
			</td>
		</tr>

		<!-- Total Amount (Without Tax) -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required"> Total Amount (<em>Without Tax</em>)
				</label>
			</th>
			<td>
				<input type="text" id="f_total_amount" name="f_total_amount" value="${f:h(savedFormData.f_total_amount)}" class="form-input-text">
				<div class="error_message"></div>
			</td>
		</tr>

		<!-- Agreement Status -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Agreement Status</label>
			</th>
			<td>
				<div>
					<label> <input type="radio" id="f_agreement_status_1" name="f_agreement_status" value="one_time"
						${f:h(savedFormData.f_agreement_status_one_time)} /> One Time/New
					</label>
				</div>
				<div class="mt-4">
					<label> <input type="radio" id="f_agreement_status_2" name="f_agreement_status" value="amendment"
						${f:h(savedFormData.f_agreement_status_amendment)} /> Amendment/Extension/Renewal
					</label>
					<div class="sub-options">
						<div>Total Duration from first cooperation until now</div>
						<label class="radio-item-sub-gap"> <input type="radio" id="f_total_duration_1" name="f_total_duration" value="more_than_1_year"
							${f:h(savedFormData.f_total_duration_more_than_1_year)} /> More than 1 year
						</label> <label> <input type="radio" id="f_total_duration_2" name="f_total_duration" value="up_to_1_year"
							${f:h(savedFormData.f_total_duration_up_to_1_year)} /> Up to 1 Year
						</label>
					</div>
				</div>
				<div class="mt-4">
					<label> <input type="radio" id="f_agreement_status_3" name="f_agreement_status" value="umbrella"
						${f:h(savedFormData.f_agreement_status_umbrella)} /> Umbrella Agreement
					</label>
				</div>
				<div class="error_message"></div>
			</td>
		</tr>

		<!-- Include auto extension condition -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Include auto extension condition</label>
			</th>
			<td>
				<label class="radio-item-gap"> <input type="radio" id="f_auto_extension_1" name="f_auto_extension" value="yes"
					${f:h(savedFormData.f_auto_extension_yes)} /> Yes
				</label> <label> <input type="radio" id="f_auto_extension_0" name="f_auto_extension" value="no" ${f:h(savedFormData.f_auto_extension_no)} /> No
				</label>
				<div class="error_message"></div>
			</td>
		</tr>

		<!-- Purchase Order Required -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Purchase Order Required</label>
			</th>
			<td>
				<label class="radio-item-gap"> <input type="radio" id="f_po_required_1" name="f_po_required" value="yes"
					${f:h(savedFormData.f_po_required_yes)} /> Yes
				</label> <label> <input type="radio" id="f_po_required_0" name="f_po_required" value="no" ${f:h(savedFormData.f_po_required_no)} /> No
				</label>
				<div class="error_message"></div>
			</td>
		</tr>

		<!-- Title described in Agreement -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Title described in Agreement</label>
			</th>
			<td>
				<input type="text" id="f_agreement_title" name="f_agreement_title" value="${f:h(savedFormData.f_agreement_title)}" class="form-input-text">
				<div class="error_message"></div>
			</td>
		</tr>

		<!-- Effective From -->
		<tr>
			<th rowspan="2" class="th-main">
				<label class="imui-required">Effective</label>
			</th>
			<th class="th-sub">
				<label class="imui-required">From</label>
			</th>
			<td>
				<input type="text" id="f_effective_from" name="f_effective_from" class="imuiCalendar form-input-text"
					value="${f:h(savedFormData.f_effective_from)}">
				<div class="error_message"></div>
			</td>
		</tr>
		<!-- Effective To -->
		<tr>
			<th class="th-sub">
				<label class="imui-required">To</label>
			</th>
			<td>
				<input type="text" id="f_effective_to" name="f_effective_to" class="imuiCalendar form-input-text" value="${f:h(savedFormData.f_effective_to)}">
				<div class="error_message"></div>
			</td>
		</tr>

		<!-- Related / Non Related Company -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Related / Non Related Company</label>
			</th>
			<td>
				<div>
					<label> <input type="radio" id="f_company_relation_1" name="f_company_relation" value="related_parties"
						${f:h(savedFormData.f_company_relation_related_parties)}> Related Parties [Shareholders (KY, MFTBC, MC, MCAH, Daimler), Subsidiary (i.e.
						KRM, MKM, BAS, BBD, BMC, etc.), Affiliates (i.e. DSF, BSI, MMKSI, MMKI, etc.)]
					</label>
				</div>
				<div class="mt-6">
					<label> <input type="radio" id="f_company_relation_2" name="f_company_relation" value="non_related_parties"
						${f:h(savedFormData.f_company_relation_non_related_parties)}> Non Related Parties
					</label>
					<div>
						<span class="legal-consult-note">Consult with Legal. SHR may be required</span>
					</div>
				</div>
				<div class="error_message"></div>
			</td>
		</tr>

		<!-- Estimated Delivery Schedule From -->
		<tr>
			<th rowspan="2" class="th-main">
				<label class="imui-required">Estimated Delivery Schedule</label>
			</th>
			<th class="th-sub">
				<label class="imui-required">From</label>
			</th>
			<td>
				<input type="text" id="f_estimated_delivery_from" name="f_estimated_delivery_from" class="imuiCalendar form-input-text"
					value="${f:h(savedFormData.f_estimated_delivery_from)}">
				<div class="error_message"></div>
			</td>
		</tr>
		<!-- Estimated Delivery Schedule To -->
		<tr>
			<th class="th-sub">
				<label class="imui-required">To</label>
			</th>
			<td>
				<input type="text" id="f_estimated_delivery_to" name="f_estimated_delivery_to" class="imuiCalendar form-input-text"
					value="${f:h(savedFormData.f_estimated_delivery_to)}">
				<div class="error_message"></div>
			</td>
		</tr>

		<!-- Agreement Summary -->
		<tr>
			<th colspan="${thColspan}">
				<label>Agreement Summary (main points only)</label> <span><em> (In case of contract in foreign currency need to describe exchange
						rate)</em></span>
			</th>
			<td>
				<textarea rows="4" id="f_agreement_summary" name="f_agreement_summary" class="form-input-textarea">${f:h(savedFormData.f_agreement_summary)}</textarea>
				<div class="error_message"></div>
			</td>
		</tr>

	</tbody>
</table>

<header class="imui-chapter-title">
	<h2>Depreciation Check</h2>
</header>

<table class="imui-form tab_header">
	<colgroup>
		<col style="width: 180px;" />
		<col style="width: 70px;" />
		<col style="width: auto;" />
	</colgroup>
	<tbody>
		<!-- Purchase Category -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Purchase Category</label>
			</th>
			<td>
				<label class="radio-item-gap"> <input type="radio" id="f_purchase_category_1" name="f_purchase_category" value="tangible_asset"
					${f:h(savedFormData.f_purchase_category_tangible_asset)} /> Tangible Asset
				</label> <label class="radio-item-gap"> <input type="radio" id="f_purchase_category_2" name="f_purchase_category" value="intangible_asset"
					${f:h(savedFormData.f_purchase_category_intangible_asset)} /> Intangible Asset
				</label> <label> <input type="radio" id="f_purchase_category_3" name="f_purchase_category" value="non_asset"
					${f:h(savedFormData.f_purchase_category_non_asset)} /> Non-Asset
				</label>
				<div class="error_message"></div>
			</td>
		</tr>

		<!-- Starting Using Date -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Starting Using Date (Required if Asset)</label>
			</th>
			<td>
				<input type="text" id="f_start_using_date" name="f_start_using_date" class="imuiCalendar form-input-text start-using-date"
					value="${f:h(savedFormData.f_start_using_date)}">
				<div class="error_message"></div>
			</td>
		</tr>

		<!-- Deprec Amount/Month -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required"> Deprec Amount/Month (Required if Asset)</label>
			</th>
			<td>
				<input type="text" id="f_deprec_month" name="f_deprec_month" value="${f:h(savedFormData.f_deprec_month)}" class="form-input-text">
				<div class="error_message"></div>
			</td>
		</tr>
	</tbody>
</table>