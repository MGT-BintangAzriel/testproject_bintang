<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>

<header class="imui-chapter-title">
	<h2>
		Applicant Information
	</h2>
</header>

<table class="imui-form tab_header" style="table-layout: fixed; width: 100%">

	<colgroup>
		<col style="width: 250px;" />
		<col style="width: auto;" />
		<col style="width: 250px;" />
		<col style="width: auto;" />
	</colgroup>
	<tbody>
		<tr>
			<th>
				<label class="eng-txt-version">Application Number</label>
			</th>
			<td>
				<input type="text" value="${not empty f:h(savedFormData.f_application_number) ? f:h(savedFormData.f_application_number) : "PC22205984"}" id="f_application_number" name="f_application_number" placeholder=""
					class="imui-text-readonly input_text_100" readonly style="" tabindex="-1">
			</td>
			<th>
				<label class="eng-txt-version">Application Date</label>
			</th>
			<td>
				<input type="text" value="${not empty f:h(savedFormData.f_application_date) ? f:h(savedFormData.f_application_date) : "2026/08/08" }" id="f_application_date" name="f_application_date" placeholder=""
					class="imui-text-readonly input_text_100" readonly style="" tabindex="-1">
			</td>
		</tr>

		<tr>
			<th>
				<label class="eng-txt-version">Applicant Number</label>
			</th>
			<td>
				<input type="text" value="${not empty f:h(savedFormData.f_applicant_number) ? f:h(savedFormData.f_applicant_number) : "2610"}" id="f_applicant_number" name="f_applicant_number" placeholder=""
					class="imui-text-readonly input_text_100" readonly style="" tabindex="-1">

			</td>
			<th>
				<label class="eng-txt-version">Department Name</label>
			</th>
			<td>
				<input type="text" value="${not empty f:h(savedFormData.f_applicant_department) ? f:h(savedFormData.f_applicant_department) : "人事部"}" id="f_applicant_department" name="f_applicant_department" placeholder=""
					class="imui-text-readonly input_text_100" readonly style="" tabindex="-1">

			</td>
		</tr>
		<tr>
			<th>
				<label class="eng-txt-version">Applicant Name</label>
			</th>
			<td>
				<input type="text" value="${not empty f:h(savedFormData.f_applicant_name) ? f:h(savedFormData.f_applicant_name) : "Bintang"}" id="f_applicant_name" name="f_applicant_name" placeholder=""
					class="imui-text-readonly input_text_100" readonly style="" tabindex="-1">
			</td>
			<th>
				<label class="eng-txt-version">Applicant Post</label>
			</th>
			<td>
				<input type="text" value="${not empty f:h(savedFormData.f_applicant_post) ? f:h(savedFormData.f_applicant_post) : "Intramart"}" id="f_applicant_post" name="f_applicant_post" placeholder=""
					class="imui-text-readonly input_text_100" readonly style="" tabindex="-1">
			</td>
		</tr>
	</tbody>
</table>