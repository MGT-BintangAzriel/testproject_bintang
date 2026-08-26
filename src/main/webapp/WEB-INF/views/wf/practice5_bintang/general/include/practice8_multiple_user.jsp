<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>

<!-- PSD Check -->
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
			<c:choose>
				<c:when test="${workflowRequestForm.imwNodeId == 'node_psd'}">
					<td>
						<div class="mt-4">
							<label> <input type="radio" id="f_psd_area_1" name="f_psd_area" value="psd" ${f:h(savedFormData.f_psd_area_psd)}> PSD (go to
								#2)
							</label>
						</div>
						<div class="mt-4">
							<label> <input type="radio" id="f_psd_area_2" name="f_psd_area" value="non_psd" ${f:h(savedFormData.f_psd_area_non_psd)}>
								Non-PSD (end)
							</label>
						</div>
						<div class="error_message"></div>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<div class="mt-4">
							<label class="pointer-locked"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_psd_area_psd)}> PSD (go to #2)
							</label>
						</div>
						<div class="mt-4">
							<label class="pointer-locked"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_psd_area_non_psd)}> Non-PSD
								(end)
							</label>
						</div>
					</td>
				</c:otherwise>
			</c:choose>
		</tr>

		<!-- PSD Process or DIC Process & Reason Textarea -->
		<c:choose>
			<c:when test="${workflowRequestForm.imwNodeId == 'node_psd'}">
				<tr>
					<th colspan="${thColspan}">
						<label class="imui-required">In PSD Area, PSD Process, or DIC Process</label>
					</th>
					<td>
						<div class="mt-4">
							<label> <input type="radio" id="f_psd_process_1" name="f_psd_process" value="psd" ${f:h(savedFormData.f_psd_process_psd)}> PSD
								(Pitching result attached)
							</label>
						</div>
						<div class="mt-4">
							<label> <input type="radio" id="f_psd_process_2" name="f_psd_process" value="dic" ${f:h(savedFormData.f_psd_process_dic)}> DIC
								(Please describe the reason in the box below)
							</label>
						</div>
						<div class="mt-4">
							<textarea rows="4" id="f_dic_reason" name="f_dic_reason" class="form-input-textarea">${f:h(savedFormData.f_dic_reason)}</textarea>
						</div>
						<div class="error_message"></div>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<th colspan="${thColspan}">
						<label class="imui-required">In PSD Area, PSD Process, or DIC Process</label>
					</th>
					<td>
						<div class="mt-4">
							<label class="pointer-locked"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_psd_process_psd)}> PSD
								(Pitching result attached)
							</label>
						</div>
						<div class="mt-4">
							<label class="pointer-locked"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_psd_process_dic)}> DIC (Please
								describe the reason in the box below)
							</label>
						</div>
						<div class="mt-4">
							<textarea rows="4" class="form-input-textarea pointer-locked" disabled tabindex="-1">${f:h(savedFormData.f_dic_reason)}</textarea>
						</div>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<!-- Compliance Check -->
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
			<c:choose>
				<c:when test="${workflowRequestForm.imwNodeId == 'node_cco'}">
					<td>
						<label class="radio-item-gap"> <input type="radio" id="f_dd_process_1" name="f_dd_process" value="yes"
							${f:h(savedFormData.f_dd_process_yes)}> Yes
						</label> <label> <input type="radio" id="f_dd_process_0" name="f_dd_process" value="no" ${f:h(savedFormData.f_dd_process_no)}> No
						</label>
						<div class="error_message"></div>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<label class="radio-item-gap pointer-locked"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_dd_process_yes)}>
							Yes
						</label> <label class="pointer-locked"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_dd_process_no)}> No
						</label>
					</td>
				</c:otherwise>
			</c:choose>
		</tr>

		<!-- Anti Bribery Clause Included -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Anti Bribery Clause Included</label>
			</th>
			<c:choose>
				<c:when test="${workflowRequestForm.imwNodeId == 'node_cco'}">
					<td>
						<label class="radio-item-gap"> <input type="radio" id="f_anti_bribery_1" name="f_anti_bribery" value="yes"
							${f:h(savedFormData.f_anti_bribery_yes)}> Yes
						</label> <label> <input type="radio" id="f_anti_bribery_0" name="f_anti_bribery" value="no" ${f:h(savedFormData.f_anti_bribery_no)}> No
						</label>
						<div class="error_message"></div>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<label class="radio-item-gap pointer-locked"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_anti_bribery_yes)}>
							Yes
						</label> <label class="pointer-locked"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_anti_bribery_no)}> No
						</label>
					</td>
				</c:otherwise>
			</c:choose>
		</tr>

		<!-- Audit Rights Included -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Audit Rights Included</label>
			</th>
			<c:choose>
				<c:when test="${workflowRequestForm.imwNodeId == 'node_cco'}">
					<td>
						<label class="radio-item-gap"> <input type="radio" id="f_audit_rights_1" name="f_audit_rights" value="yes"
							${f:h(savedFormData.f_audit_rights_yes)}> Yes
						</label> <label> <input type="radio" id="f_audit_rights_0" name="f_audit_rights" value="no" ${f:h(savedFormData.f_audit_rights_no)}> No
						</label>
						<div class="error_message"></div>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<label class="radio-item-gap pointer-locked"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_audit_rights_yes)}>
							Yes
						</label> <label class="pointer-locked"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_audit_rights_no)}> No
						</label>
					</td>
				</c:otherwise>
			</c:choose>
		</tr>
	</tbody>
</table>

<!-- Filled by Legal -->
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
			<c:choose>
				<c:when test="${workflowRequestForm.imwNodeId == 'node_legal'}">
					<td>
						<input type="text" id="f_legal_agreement_number" name="f_legal_agreement_number" value="${f:h(savedFormData.f_legal_agreement_number)}"
							class="form-input-text">
						<div class="error_message"></div>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<label class="font-imart">${not empty savedFormData.f_legal_agreement_number ? f:h(savedFormData.f_legal_agreement_number) : 'Not yet filled'}</label>
					</td>
				</c:otherwise>
			</c:choose>
		</tr>

		<!-- Legal Agreement Date -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Agreement Date</label>
			</th>
			<c:choose>
				<c:when test="${workflowRequestForm.imwNodeId == 'node_legal'}">
					<td>
						<input type="text" id="f_legal_agreement_date" name="f_legal_agreement_date" class="imuiCalendar form-input-text"
							value="${f:h(savedFormData.f_legal_agreement_date)}">
						<div class="error_message"></div>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<label class="font-imart">${not empty savedFormData.f_legal_agreement_date ? f:h(savedFormData.f_legal_agreement_date) : 'Not yet filled'}</label>
					</td>
				</c:otherwise>
			</c:choose>
		</tr>
	</tbody>
</table>