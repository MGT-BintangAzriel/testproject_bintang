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

<table class="imui-form tab_header tab_applicant_header" style="table-layout: fixed; width: 100%">

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
				<label class="font-imart">${f:h(savedFormData.f_application_number)}</label>
			</td>
			<th>
				<label class="eng-txt-version">Application Date</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_application_date)}</label>
			</td>
		</tr>

		<tr>
			<th>
				<label class="eng-txt-version">Applicant Number</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_applicant_number)}</label>
			</td>
			<th>
				<label class="eng-txt-version">Department Name</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_applicant_department)}</label>
			</td>
		</tr>
		<tr>
			<th>
				<label class="eng-txt-version">Applicant Name</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_applicant_name)}</label>
			</td>
			<th>
				<label class="eng-txt-version">Applicant Post</label>
			</th>
			<td>
				<label class="font-imart">${not empty f:h(savedFormData.f_applicant_post) ? f:h(savedFormData.f_applicant_post) : "Intramart"}</label>
			</td>
		</tr>
	</tbody>
</table>