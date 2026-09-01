<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>

<!-- PSD Check (Display) -->
<header class="imui-chapter-title">
	<h2>PSD Check (by UH or DH, PSD)</h2>
</header>

<table class="imui-form tab_header">
	<colgroup>
		<col style="width: 180px;" />
		<col style="width: 70px;" />
		<col style="width: auto;" />
	</colgroup>
	<tbody>
		<!-- PSD Area or Non-PSD Area -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">PSD Area or Non-PSD Area (Based on Guideline)</label>
			</th>
			<td>
				<div class="mt-4">
					<label> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_psd_area_psd)}> PSD (go to #2)
					</label>
				</div>
				<div class="mt-4">
					<label> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_psd_area_non_psd)}> Non-PSD (end)
					</label>
				</div>
			</td>
		</tr>

		<!-- PSD Process or DIC Process & Reason Textarea -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">In PSD Area, PSD Process, or DIC Process</label>
			</th>
			<td>
				<div class="mt-4">
					<label> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_psd_process_psd)}> PSD (Pitching result attached)
					</label>
				</div>
				<div class="mt-4">
					<label> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_psd_process_dic)}> DIC (Please describe the reason in
						the box below)
					</label>
				</div>
				<div class="mt-4">
					<textarea rows="4" class="form-input-textarea" disabled tabindex="-1">${f:h(savedFormData.f_dic_reason)}</textarea>
				</div>
			</td>
		</tr>
	</tbody>
</table>

<!-- Compliance Check (Display) -->
<header class="imui-chapter-title">
	<h2>Compliance Check By CCO</h2>
</header>

<table class="imui-form tab_header">
	<colgroup>
		<col style="width: 180px;" />
		<col style="width: 70px;" />
		<col style="width: auto;" />
	</colgroup>
	<tbody>
		<!-- D / D Process Required -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">D / D Process Required</label>
			</th>
			<td>
				<label class="radio-item-gap"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_dd_process_yes)}> Yes
				</label> <label> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_dd_process_no)}> No
				</label>
			</td>
		</tr>

		<!-- Anti Bribery Clause Included -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Anti Bribery Clause Included</label>
			</th>
			<td>
				<label class="radio-item-gap"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_anti_bribery_yes)}> Yes
				</label> <label> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_anti_bribery_no)}> No
				</label>
			</td>
		</tr>

		<!-- Audit Rights Included -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Audit Rights Included</label>
			</th>
			<td>
				<label class="radio-item-gap"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_audit_rights_yes)}> Yes
				</label> <label> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_audit_rights_no)}> No
				</label>
			</td>
		</tr>
	</tbody>
</table>

<!-- Filled by Legal (Editable) -->
<header class="imui-chapter-title">
	<h2>Filled by Legal</h2>
</header>

<table class="imui-form tab_header">
	<colgroup>
		<col style="width: 180px;" />
		<col style="width: 70px;" />
		<col style="width: auto;" />
	</colgroup>
	<tbody>
		<!-- Legal Agreement Number -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Agreement Number</label>
			</th>
			<td>
				<input type="text" id="f_legal_agreement_number" name="f_legal_agreement_number" value="${f:h(savedFormData.f_legal_agreement_number)}"
					class="form-input-text">
				<div class="error_message"></div>
			</td>
		</tr>

		<!-- Legal Agreement Date -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Agreement Date</label>
			</th>
			<td>
				<input type="text" id="f_legal_agreement_date" name="f_legal_agreement_date" class="imuiCalendar form-input-text"
					value="${f:h(savedFormData.f_legal_agreement_date)}">
				<div class="error_message"></div>
			</td>
		</tr>
	</tbody>
</table>
