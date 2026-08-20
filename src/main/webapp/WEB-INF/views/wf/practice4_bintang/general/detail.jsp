<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="workflow" uri="http://www.intra-mart.co.jp/taglib/imw/workflow"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="im" uri="http://www.intra-mart.co.jp/taglib/im-tenant"%>

<imui:head>
	<title>PC購入申請</title>
	<workflow:workflowOpenPageCsjs />

	<link href="ui/css/select2.min.css" rel="stylesheet" />
	<script src="ui/js/select2.min.js" type="text/javascript"></script>
	<script src="ui/js/jquery.validate.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(function() {
			// 購入金額の自動計算処理
			function calculateTotalPrice() {
				var qty = parseInt($('#f_quantity').val(), 10) || 0;
				var price = parseInt($('#f_unit_price').val(), 10) || 0;
				var total = qty * price;
				$('#f_total_price').val(total.toLocaleString() + ' 円');
			}

			// PC用途「その他」選択時の制御処理
			function togglePcUseOther() {
				var selectedType = $('#f_pc_use_type').val();
				if (selectedType === 'その他') {
					$('#f_pc_use_other').prop('disabled', false);
					$('#lbl_pc_use_other').addClass('imui-required');
				} else {
					$('#f_pc_use_other').val('').prop('disabled', true);
					$('#lbl_pc_use_other').removeClass('imui-required');
				}
			}
			// 初期表示時の計算と用途項目の制御実行
			calculateTotalPrice();
			togglePcUseOther();

			// PDF生成処理の実行
			$('#pdfgenerate').click(function() {
				var system_matter_id = '${f:h(ApplyForm.imwSystemMatterId)}';
				$.ajax({
					type: "POST",
                    url: "practice4_bintang/generatepdf", 
					data: { 
						system_matter_id : system_matter_id,
					},
					success: function (response) {
						if (response.indexOf("error") === -1) {
							window.location.href = "/imarttest/practice4_bintang/downloadpdf/" + encodeURIComponent(response.trim());
						} else {
							console.log("LOG Error :" , response);
							alert("Failed to Generate PDF");
						}
					},
					error: function (xhr, status, e) {
						console.error("AJAX ERROR :", e);
					}
				});
			});
		});
	</script>

	<style type="text/css">
		.imui-form th {
			width: 200px;
		}
	</style>
</imui:head>

<workflow:workflowUserContentsAuth
	imwApplyBaseDate='${f:h(ApplyForm.imwApplyBaseDate)}'
	imwAuthUserCode='${f:h(ApplyForm.imwAuthUserCode)}'
	imwFlowId='${f:h(ApplyForm.imwFlowId)}'
	imwNodeId='${f:h(ApplyForm.imwNodeId)}'
	imwPageType='${f:h(ApplyForm.imwPageType)}'
	imwSystemMatterId='${f:h(ApplyForm.imwSystemMatterId)}'
	imwUserDataId='${f:h(ApplyForm.imwUserDataId)}' />

<div class="imui-title-small-window">
	<h1>ワークフロー</h1>
</div>
<div class="imui-toolbar-wrap">
	<div class="imui-toolbar-inner">
		<ul class="imui-list-toolbar">
			<li>
				<a href="javascript:void(0);" id="back">
					<span class="im-ui-icon-common-16-back"></span>
				</a>
			</li>
		</ul>
	</div>
</div>

<imui:tabs selected="0">
	<imui:tabItem title="ワークフロー">
		<div class="imui-form-container">
			<workflow:workflowOpenPage name="workflowOpenPageForm"
				id="workflowOpenPageForm" method="POST" target="_top"
				imwUserDataId="${f:h(ApplyForm.imwUserDataId)}"
				imwSystemMatterId="${f:h(ApplyForm.imwSystemMatterId)}"
				imwAuthUserCode="${f:h(ApplyForm.imwAuthUserCode)}"
				imwApplyBaseDate="${f:h(ApplyForm.imwApplyBaseDate)}"
				imwNodeId="${f:h(ApplyForm.imwNodeId)}"
				imwFlowId="${f:h(ApplyForm.imwFlowId)}"
				imwCallOriginalParams="${f:h(ApplyForm.imwCallOriginalParams)}"
				imwNextScriptPath="${f:h(ApplyForm.imwCallOriginalPagePath)}">

				<header class="imui-chapter-title">
					<h2>PC購入申請フォーム</h2>
				</header>

				<table class="imui-form tab_header">
					<tbody>
						<tr>
							<th><label class="imui-required">申請日</label></th>
							<td>
								<input type="text" id="f_apply_date" name="f_apply_date"
									value="${FormClassRows.getF_apply_date()}" style="height: 20px;" placeholder="yyyy/MM/dd" readonly>
							</td>
						</tr>

						<tr>
							<th><label class="imui-required">申請者</label></th>
							<td>
								<input type="text" id="f_applicant" name="f_applicant"
									value="${FormClassRows.getF_applicant()}" size="40" style="height: 20px;" readonly>
							</td>
						</tr>

						<tr>
							<th><label class="imui-required">部署</label></th>
							<td>
								<input type="text" id="f_department" name="f_department"
									value="${FormClassRows.getF_department()}" size="40" style="height: 20px;" readonly>
							</td>
						</tr>

						<tr>
							<th><label class="imui-required">PC用途</label></th>
							<td>
								<select id="f_pc_use_type" name="f_pc_use_type" style="height: 25px;" disabled>
									<option value="">-- 選択してください --</option>
									<option value="開発" ${FormClassRows.getF_pc_use_type() == '開発' ? 'selected' : ''}>開発</option>
									<option value="事務" ${FormClassRows.getF_pc_use_type() == '事務' ? 'selected' : ''}>事務</option>
									<option value="デザイン" ${FormClassRows.getF_pc_use_type() == 'デザイン' ? 'selected' : ''}>デザイン</option>
									<option value="その他" ${FormClassRows.getF_pc_use_type() == 'その他' ? 'selected' : ''}>その他</option>
								</select>
							</td>
						</tr>

						<tr>
							<th><label id="lbl_pc_use_other">その他用途</label></th>
							<td>
								<input type="text" id="f_pc_use_other" name="f_pc_use_other"
									value="${FormClassRows.getF_pc_use_other()}" size="50" style="height: 20px;" readonly>
							</td>
						</tr>

						<tr>
							<th><label class="imui-required">メーカー</label></th>
							<td>
								<input type="text" id="f_manufacturer" name="f_manufacturer"
									value="${FormClassRows.getF_manufacturer()}" size="40" style="height: 20px;" readonly>
							</td>
						</tr>

						<tr>
							<th><label class="imui-required">型番</label></th>
							<td>
								<input type="text" id="f_model_number" name="f_model_number"
									value="${FormClassRows.getF_model_number()}" size="40" style="height: 20px;" readonly>
							</td>
						</tr>

						<tr>
							<th><label class="imui-required">数量</label></th>
							<td>
								<input type="number" id="f_quantity" name="f_quantity"
									value="${FormClassRows.getF_quantity()}" min="1" style="height: 20px;" readonly>
							</td>
						</tr>

						<tr>
							<th><label class="imui-required">単価</label></th>
							<td>
								<input type="number" id="f_unit_price" name="f_unit_price"
									value="${FormClassRows.getF_unit_price()}" min="0" style="height: 20px;" readonly> 円
							</td>
						</tr>

						<tr>
							<th><label>購入金額（自動計算）</label></th>
							<td>
								<input type="text" id="f_total_price" name="f_total_price"
									readonly style="height: 20px; background-color: #eee; border: 1px solid #ccc;">
							</td>
						</tr>

						<tr>
							<th><label>備考</label></th>
							<td>
								<textarea id="f_remarks" name="f_remarks" rows="3" cols="50" readonly>${FormClassRows.getF_remarks()}</textarea>
							</td>
						</tr>
					</tbody>
				</table>

			</workflow:workflowOpenPage>
		</div>
	</imui:tabItem>
</imui:tabs>

<div class="imui-operation-parts">
	<input type="button" value='PDFを生成' id="pdfgenerate" name="pdfgenerate" class="imui-large-button" escapeXml="true" escapeJs="false" />
</div>

<form name="backForm" id="backForm" method="POST"
	action="${f:h(ApplyForm.imwCallOriginalPagePath)}">
	<input type="hidden" name="imwCallOriginalParams"
		value="${f:h(ApplyForm.imwCallOriginalParams)}" />
</form>
