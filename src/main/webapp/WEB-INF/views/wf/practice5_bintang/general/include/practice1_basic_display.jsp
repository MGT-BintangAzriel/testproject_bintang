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
				<label class="font-imart">${f:h(savedFormData.f_counter_party)}</label>
			</td>
		</tr>

		<!-- Currency -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Currency</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_currency)}</label>
			</td>
		</tr>

		<!-- Total Amount (Without Tax) -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required"> Total Amount (<em>Without Tax</em>)
				</label>
			</th>
			<td>
				<label class="font-imart" id="f_total_amount">${f:h(savedFormData.f_total_amount)}</label>
			</td>
		</tr>

		<!-- Agreement Status -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Agreement Status</label>
			</th>
			<td>
				<label class="pointer-locked"> <input type="radio" disabled tabindex="-1"
					${savedFormData.f_agreement_status == 'one_time' ? 'checked' : ''} /> One Time/New
				</label>
				<div class="mt-4">
					<label class="pointer-locked"> <input type="radio" disabled tabindex="-1"
						${savedFormData.f_agreement_status == 'amendment' ? 'checked' : ''} /> Amendment/Extension/Renewal
					</label>

					<%-- Sub-options --%>
					<div class="sub-options">
						<div>Total Duration from first cooperation until now</div>
						<label class="radio-item-sub-gap pointer-locked"> <input type="radio" disabled tabindex="-1"
							${savedFormData.f_total_duration == 'more_than_1_year' ? 'checked' : ''} /> More than 1 year
						</label> <label class="pointer-locked"> <input type="radio" disabled tabindex="-1"
							${savedFormData.f_total_duration == 'up_to_1_year' ? 'checked' : ''} /> Up to 1 Year
						</label>
					</div>
				</div>
			</td>
		</tr>

		<!-- Include auto extension condition -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Include auto extension condition</label>
			</th>
			<td>
				<label class="radio-item-gap pointer-locked"> <input type="radio" disabled tabindex="-1" ${f:h(savedFormData.f_auto_extension_yes)} />
					Yes
				</label> <label class="pointer-locked"> <input type="radio" disabled tabindex="-1" ${f:h(savedFormData.f_auto_extension_no)} /> No
				</label>
			</td>
		</tr>

		<!-- Purchase Order Required -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Purchase Order Required</label>
			</th>
			<td>
				<label class="radio-item-gap pointer-locked"> <input type="radio" disabled tabindex="-1" ${f:h(savedFormData.f_po_required_yes)} /> Yes
				</label> <label class="pointer-locked"> <input type="radio" disabled tabindex="-1" ${f:h(savedFormData.f_po_required_no)} /> No
				</label>
			</td>
		</tr>

		<!-- Title described in Agreement -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Title described in Agreement</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_agreement_title)}</label>
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
				<label class="font-imart">${f:h(savedFormData.f_effective_from)}</label>
			</td>
		</tr>
		<!-- Effective To -->
		<tr>
			<th class="th-sub">
				<label class="imui-required">To</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_effective_to)}</label>
			</td>
		</tr>

		<!-- Related / Non Related Company -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Related / Non Related Company</label>
			</th>
			<td>
				<div>
					<label class="pointer-locked"> <input type="radio" disabled tabindex="-1" ${f:h(savedFormData.f_company_relation_related_parties)}>
						Related Parties [Shareholders (KY, MFTBC, MC, MCAH, Daimler), Subsidiary (i.e. KRM, MKM, BAS, BBD, BMC, etc.), Affiliates (i.e. DSF, BSI, MMKSI,
						MMKI, etc.)]
					</label>
				</div>
				<div class="mt-6">
					<label class="pointer-locked"> <input type="radio" disabled tabindex="-1" ${f:h(savedFormData.f_company_relation_non_related_parties)}>
						Non Related Parties
					</label>
					<div>
						<span class="legal-consult-note">Consult with Legal. SHR may be required</span>
					</div>
				</div>
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
				<label class="font-imart">${f:h(savedFormData.f_estimated_delivery_from)}</label>
			</td>
		</tr>
		<!-- Estimated Delivery Schedule To -->
		<tr>
			<th class="th-sub">
				<label class="imui-required">To</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_estimated_delivery_to)}</label>
			</td>
		</tr>

		<!-- Agreement Summary -->
		<tr>
			<th colspan="${thColspan}">
				<label>Agreement Summary (main points only)</label> <span><em> (In case of contract in foreign currency need to describe exchange
						rate)</em></span>
			</th>
			<td>
				<label class="font-imart">${not empty savedFormData.f_agreement_summary ? f:h(savedFormData.f_agreement_summary) : '-'}</label>
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
				<label class="radio-item-gap pointer-locked"> <input type="radio" disabled tabindex="-1"
					${f:h(savedFormData.f_purchase_category_tangible_asset)} /> Tangible Asset
				</label> <label class="radio-item-gap pointer-locked"> <input type="radio" disabled tabindex="-1"
					${f:h(savedFormData.f_purchase_category_intangible_asset)} /> Intangible Asset
				</label> <label class="pointer-locked"> <input type="radio" disabled tabindex="-1" ${f:h(savedFormData.f_purchase_category_non_asset)} />
					Non-Asset
				</label>
			</td>
		</tr>

		<!-- Starting Using Date -->
		<c:if test="${savedFormData.f_purchase_category_non_asset != 'checked' }">
			<tr>
				<th colspan="${thColspan}">
					<label class="imui-required">Starting Using Date (Required if Asset)</label>
				</th>
				<td>
					<label class="font-imart">${f:h(savedFormData.f_start_using_date)}</label>
				</td>
			</tr>

			<!-- Deprec Amount/Month -->
			<tr>
				<th colspan="${thColspan}">
					<label class="imui-required"> Deprec Amount/Month (Required if Asset)</label>
				</th>
				<td>
					<label class="font-imart">${f:h(savedFormData.f_deprec_month)}</label>
				</td>
			</tr>
		</c:if>
	</tbody>
</table>