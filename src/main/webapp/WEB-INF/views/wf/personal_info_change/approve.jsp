<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui" %>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard" %>
<%@ taglib prefix="workflow" uri="http://www.intra-mart.co.jp/taglib/imw/workflow" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="im" uri="http://www.intra-mart.co.jp/taglib/im-tenant" %>

<imui:head>
	<title>${f:h(savedFormData.f_workflow_name)}</title>
	<workflow:workflowOpenPageCsjs />
	<script src="ui/js/maskMoney.js"></script>
 	<script src="ui/js/jquery.mask.min.js"></script>
 	
	<link href="ui/css/select2.min.css" rel="stylesheet" />
	<script src="ui/js/select2.min.js"></script>
	<script src="ui/js/jquery.validate.js"></script>
	
	<script type="text/javascript">
		$(function() {
			$('#back').click(function() {
				$('#backForm').submit();
				return false;
			});
			
			$('.select2').select2();
			
			$('.only-numbers-thousands').maskMoney({precision:0,});
			
			$('.only-numbers-thousands').each(function(){
				$(this).maskMoney('mask', $(this).val() );
			});
			
			$('input[type="checkbox"]').on('click', function(e) {
			    e.preventDefault();
			});
			
			$('input[type="radio"]').on('click', function(e) {
			    e.preventDefault();
			});
			
			$('#openPage').click(function(){
				workflowOpenPage('${f:h(workflowRequestForm.imwPageType)}');
				return false;
		    });
			
			$(".eng-txt-version").hide();
		});
	</script>
	<style type="text/css">
        table {
		      width: 100%;
		      border-collapse: collapse;
		      margin-top: 10px;
		}
	    th, td {
	      padding: 6px;
	      border: 1px solid #ccc;
	      text-align: start;
	    }
	    
	    .scrollbox {
			overflow-x: auto;
			overflow-y: auto;
			max-height: 350px;
		}
		
		.scrollbox th {
			position: sticky;
			top: 0;
			z-index: 1;
		}
		
	    .imui-medium-button {
	      margin-bottom: 10px;
	    }
		
	    .select2-selection.imui-validation-error {
		    border: 1px solid red !important;
		}
		
		.select2-selection.imui-validation-success {
		    border: 2px solid #3c763d !important;
		}
		
		.imui-validation-error-text {
		    color: #D1001C;
		    margin-top: 5px;
		    font-size: 12px;
		}
		
		.input_text_100{
			width:96%;
		}
		
		.input_text_big_100{
			width:99%;
		}
		
		.input_text_90{
			width:85%;
		}
		
		.msg{
			color:red;
			font-size:12px !important; 
		}
		
		.jpn-txt-version{
			
		}
		
		.eng-txt-version{
			font-size:10px;
			font-style: italic;
		}
		
		
		/* Chrome, Safari, Edge, and Opera */
		input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
			-webkit-appearance: none;
			margin: 0;
		}
		
		/* Firefox */
		input[type=number] {
			-moz-appearance: textfield;
		}
		
		
		/* Beautiful Download Button */
		.download-btn {
		    display: inline-flex;
		    align-items: center;
		    gap: 8px;
		    background-color: #eff6ff !important; /* Soft light blue tint */
		    color: #2563eb !important;
		    border: 1px solid #bfdbfe !important; /* Matching light blue border */
		    padding: 8px 16px;
		    border-radius: 6px;
		    /*font-size: 0.9rem;
		    font-weight: 600;*/
		    text-decoration: none;
		    cursor: pointer;
		    transition: all 0.15s ease-in-out;
		}
		
		.download-btn:hover {
		    background-color: #2563eb !important;
		    color: #ffffff !important;
		    border-color: #2563eb;
		    box-shadow: 0 4px 12px rgba(37, 99, 235, 0.2);
		}
		
		.download-btn i {
		    /*font-size: 0.95rem;*/
		}
		
		
		.imui-text-readonly{
			height:20px; !important;
			padding-top: 0.25em !important; 
			padding-right: 0.4em !important; 
			padding-bottom: 0.25em !important; 
			padding-left: 0.4em !important;
		}
		
		.no-pointer-event{
			pointer-events: none !important;
		}
	
	</style>
</imui:head>
<workflow:workflowUserContentsAuth imwApplyBaseDate='${f:h(workflowRequestForm.imwApplyBaseDate)}'
            imwAuthUserCode = '${f:h(workflowRequestForm.imwAuthUserCode)}'
            imwFlowId='${f:h(workflowRequestForm.imwFlowId)}'
            imwNodeId ='${f:h(workflowRequestForm.imwNodeId)}'
            imwPageType = '${f:h(workflowRequestForm.imwPageType)}'
            imwSystemMatterId='${f:h(workflowRequestForm.imwSystemMatterId)}'
            imwUserDataId='${f:h(workflowRequestForm.imwUserDataId)}'/>
            

<div class="imui-title-small-window">
	<h1>${f:h(savedFormData.f_workflow_name)}</h1>
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

<workflow:workflowOpenPage name="workflowOpenPageForm"
           id="workflowOpenPageForm"
           method="POST"
           target="_top"
           imwUserDataId="${f:h(workflowRequestForm.imwUserDataId)}"
           imwSystemMatterId="${f:h(workflowRequestForm.imwSystemMatterId)}"
           imwAuthUserCode="${f:h(workflowRequestForm.imwAuthUserCode)}"
           imwApplyBaseDate="${f:h(workflowRequestForm.imwApplyBaseDate)}"
           imwNodeId="${f:h(workflowRequestForm.imwNodeId)}"
           imwFlowId="${f:h(workflowRequestForm.imwFlowId)}"
           imwCallOriginalParams="${f:h(workflowRequestForm.imwCallOriginalParams)}"
           imwNextScriptPath="${f:h(workflowRequestForm.imwCallOriginalPagePath)}">
           
<div class="imui-form-container-wide">
	<jsp:include page="approve_personal_info_change.jsp"></jsp:include>
</div>
</workflow:workflowOpenPage>

<div class="imui-operation-parts">
	<input type="button" value='次へ' id="openPage" name="openPage" class="imui-large-button" escapeXml="true" escapeJs="false" tabindex="1" />
</div>

<!-- Intra-mart Action for Back Button	 -->
<form name="backForm" id="backForm" method="POST" action="${f:h(workflowRequestForm.imwCallOriginalPagePath)}">
	<input type="hidden" name=imwCallOriginalParams value="${f:h(workflowRequestForm.imwCallOriginalParams)}" />
</form>