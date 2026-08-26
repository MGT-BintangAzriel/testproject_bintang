<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>

<!-- Upload Document Section -->
<c:if test="${workflowRequestForm.imwPageType == '0' || workflowRequestForm.imwPageType == '3'}">
	<div style="margin-top: 15px;">
		<header class="imui-chapter-title">
			<h2>Upload Document by DIC : Agreement, DD, etc</h2>
		</header>
		<table class="imui-form">
			<tbody>
				<tr>
					<th width="250">
						<label class="imui-required">Upload File</label>
					</th>
					<td>
						<imui:fileUpload enableDelete="true" uniqueFileName="true" storeTo="file_attachment/" onSuccess="callbackSuccess" onError="callbackError"
							onRemove="callbackRemove" />
						<div class="error_message_upload"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</c:if>

<c:if test="${workflowRequestForm.imwPageType == '3' || workflowRequestForm.imwPageType == '4' || workflowRequestForm.imwPageType == '6'}">
	<!-- Uploaded Document Display Section -->
	<div class="imui-form-container-full" style="margin-top: 15px;">
		<header class="imui-chapter-title">
			<h2>To see the uploaded document</h2>
		</header>
		<table class="imui-form file_attachment_list">
			<tbody>
				<c:forEach items="${savedFormData.d_list_attachment}" var="file">
					<tr>
						<td>
							<a href="practice5_bintang/download/${file.id}?token=${f:h(savedFormData.f_download_token)}&system_matter_id=${f:h(workflowRequestForm.imwSystemMatterId)}">
								${f:h(file.file_name)}
							</a>
							<span style="color: #666; margin-left: 8px;">(${f:h(file.file_size_convert)})</span>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>
