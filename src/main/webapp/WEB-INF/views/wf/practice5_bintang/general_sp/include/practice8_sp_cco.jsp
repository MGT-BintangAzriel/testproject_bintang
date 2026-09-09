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
					<input type="radio" name="f_psd_area" id="f_psd_area_psd" ${f:h(savedFormData.f_psd_area_psd)} disabled>
					PSD (go to #2)
				</label>
				<label for="f_psd_area_non_psd">
					<input type="radio" name="f_psd_area" id="f_psd_area_non_psd" ${f:h(savedFormData.f_psd_area_non_psd)} disabled>
					Non-PSD (end)
				</label>
			</div>
		</imsp:fieldContain>

		<c:if test="${savedFormData.f_psd_area == 'psd' or empty savedFormData.f_psd_area}">
			<imsp:fieldContain label="In PSD Area, PSD Process, or DIC Process:" required="true">
				<div class="custom-readonly">
					<label for="f_psd_process_psd">
						<input type="radio" name="f_psd_process" id="f_psd_process_psd" ${f:h(savedFormData.f_psd_process_psd)} disabled>
						PSD (Pitching result attached)
					</label>
					<label for="f_psd_process_dic">
						<input type="radio" name="f_psd_process" id="f_psd_process_dic" ${f:h(savedFormData.f_psd_process_dic)} disabled>
						DIC (Please describe the reason below)
					</label>
				</div>
			</imsp:fieldContain>

			<c:if test="${savedFormData.f_psd_process_dic == 'checked' or empty savedFormData.f_psd_process}">
				<div style="border-left: 2px solid #3b82f6; margin-left: 10px; padding-left: 10px;">
					<imsp:fieldContain label="Reason for DIC:">
						<div class="custom-readonly">${not empty savedFormData.f_dic_reason ? f:h(savedFormData.f_dic_reason) : '-'}</div>
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
					<input type="radio" name="f_dd_process" id="f_dd_process_yes" value="yes" ${f:h(savedFormData.f_dd_process_yes)}>
					Yes
				</label>
				<label for="f_dd_process_no">
					<input type="radio" name="f_dd_process" id="f_dd_process_no" value="no" ${f:h(savedFormData.f_dd_process_no)}>
					No
				</label>
				<div class="error_message"></div>
			</div>
		</imsp:fieldContain>

		<imsp:fieldContain label="Anti Bribery Clause Included:" required="true">
			<div class="custom-readonly">
				<label for="f_anti_bribery_yes">
					<input type="radio" name="f_anti_bribery" id="f_anti_bribery_yes" value="yes" ${f:h(savedFormData.f_anti_bribery_yes)}>
					Yes
				</label>
				<label for="f_anti_bribery_no">
					<input type="radio" name="f_anti_bribery" id="f_anti_bribery_no" value="no" ${f:h(savedFormData.f_anti_bribery_no)}>
					No
				</label>
				<div class="error_message"></div>
			</div>
		</imsp:fieldContain>

		<imsp:fieldContain label="Audit Rights Included:" required="true">
			<div class="custom-readonly">
				<label for="f_audit_rights_yes">
					<input type="radio" name="f_audit_rights" id="f_audit_rights_yes" value="yes" ${f:h(savedFormData.f_audit_rights_yes)}>
					Yes
				</label>
				<label for="f_audit_rights_no">
					<input type="radio" name="f_audit_rights" id="f_audit_rights_no" value="no" ${f:h(savedFormData.f_audit_rights_no)}>
					No
				</label>
				<div class="error_message"></div>
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
			<div class="custom-readonly">${not empty savedFormData.f_legal_agreement_number ? f:h(savedFormData.f_legal_agreement_number) : 'Not yet filled'}
			</div>
		</imsp:fieldContain>

		<imsp:fieldContain label="Agreement Date:" required="true">
			<div class="custom-readonly">${not empty savedFormData.f_legal_agreement_date ? f:h(savedFormData.f_legal_agreement_date) : 'Not yet filled'}
			</div>
		</imsp:fieldContain>
	</div>
</div>