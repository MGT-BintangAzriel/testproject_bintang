
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart"
	uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="workflow"
	uri="http://www.intra-mart.co.jp/taglib/imw/workflow"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="im"
	uri="http://www.intra-mart.co.jp/taglib/im-tenant"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>

<imui:head>
	<title>備品購入申請</title>
	<workflow:workflowOpenPageCsjs />

	<link href="ui/css/select2.min.css" rel="stylesheet" />
	<script src="ui/js/select2.min.js" type="text/javascript"></script>
	<script src="ui/js/jquery.validate.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(function() {
			var rules = {
				f_equipment_name: {required: true},
				f_price : {required: true}, 
				f_reason : {required: true},
			};
			
			var messages = {
				f_equipment_name: {required: "備品名を入力してください。" },
				f_price : {required: "金額を入力してください。"},
				f_reason : {required: "理由を入力してください。"},
			};
	           
			$('#openPage').click(function() {
				var valid = imuiValidate('#workflowOpenPageForm', rules, messages);
				
				if (valid) {
					workflowOpenPage('${f:h(ApplyForm.imwPageType)}');
				} else {
					imuiShowErrorMessage('インプットのエラーが発生しました。', [], true, 2500, false);
				}
			}); 
		});
	</script>

	<!-- CSS Scripts -->
	<style type="text/css">
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
			<li><a href="javascript:void(0);" id="back"> <span
					class="im-ui-icon-common-16-back"></span>
			</a></li>
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

				<input type="hidden" id="jsonVendor" value="${f:h(jsonVendor)}">

				<header class="imui-chapter-title">
					<h2>備品購入申請フォーム</h2>
				</header>

				<table class="imui-form tab_header">
					<tbody>
						<tr>
							<th width="250"><label class="imui-required"
								style="margin-left: 20px">備品名</label></th>
							<td><input type="text" readonly value="${FormClassRows.getF_equipment_name()}"  id="f_equipment_name"
								name="f_equipment_name"
								style="height: 20px" size="50"></td>
						</tr>

						<tr>
							<th><label class="imui-required" style="margin-left: 20px">金額</label>
							</th>
							<td>
								<div class="">
									<input type="number" readonly value="${FormClassRows.getF_price()}" id="f_price" name="f_price" min="0">
								</div>
							</td>

						</tr>

						<tr>
							<th width="250"><label class="imui-required" style="margin-left: 20px">理由</label>
							</th>
							<td>
								<div class="form-group">
									<textarea rows="3" cols="40" name="f_reason" id="f_reason"
										class="" style="margin-left: 5px;" readonly>${FormClassRows.getF_reason()}</textarea>
								</div>
							</td>
						</tr>
					</tbody>
				</table>

			</workflow:workflowOpenPage>

		</div>

	</imui:tabItem>
</imui:tabs>



<!-- Button Default -->
<div class="imui-operation-parts">
	<input type="button" value='処理' id="openPage" name="openPage" class="imui-large-button" escapeXml="true" escapeJs="false" />
</div>

<form name="backForm" id="backForm" method="POST"
	action="${f:h(ApplyForm.imwCallOriginalPagePath)}">
	<input type="hidden" name=imwCallOriginalParams
		value="${f:h(ApplyForm.imwCallOriginalParams)}" />
</form>
