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
	<title>Agreement Detail</title>
	<workflow:workflowOpenPageCsjs />

	<link href="ui/css/select2.min.css" rel="stylesheet" />
	<link href="ui/css/workflow-custom.css" rel="stylesheet" />
	<script src="ui/js/select2.min.js" type="text/javascript"></script>
	<script src="ui/js/jquery.validate.js" type="text/javascript"></script>
	<script src="ui/js/workflow-form.js" type="text/javascript"></script>
	<script src="ui/js/workflow-form-validation.js" type="text/javascript"></script>

	<script type="text/javascript">
		 $(function() {
      // Initialize select2 and dynamic section visibility
      $('.select2').select2();
      setupMultiDataToggle();

      // Lock form fields as read-only
      initReadOnlyAgreementForm();
      toggleDepreciation();
      disablePractice8Fields();
    });
	</script>

</imui:head>

<workflow:workflowUserContentsAuth imwApplyBaseDate='${f:h(workflowRequestForm.imwApplyBaseDate)}'
	imwAuthUserCode='${f:h(workflowRequestForm.imwAuthUserCode)}' imwFlowId='${f:h(workflowRequestForm.imwFlowId)}'
	imwNodeId='${f:h(workflowRequestForm.imwNodeId)}' imwPageType='${f:h(workflowRequestForm.imwPageType)}'
	imwSystemMatterId='${f:h(workflowRequestForm.imwSystemMatterId)}' imwUserDataId='${f:h(workflowRequestForm.imwUserDataId)}' />

<div class="imui-title-small-window">
	<h1>Workflow</h1>
</div>
<div class="imui-toolbar-wrap">
	<div class="imui-toolbar-inner">
		<ul class="imui-list-toolbar">
			<li><a href="javascript:void(0);" id="back"> <span class="im-ui-icon-common-16-back"></span>
			</a></li>
		</ul>
	</div>
</div>

<imui:tabs selected="0">
	<imui:tabItem title="Agreement Detail">
		<div class="imui-form-container">
			<workflow:workflowOpenPage name="workflowOpenPageForm" id="workflowOpenPageForm" method="POST" target="_top"
				imwUserDataId="${f:h(workflowRequestForm.imwUserDataId)}" imwSystemMatterId="${f:h(workflowRequestForm.imwSystemMatterId)}"
				imwAuthUserCode="${f:h(workflowRequestForm.imwAuthUserCode)}" imwApplyBaseDate="${f:h(workflowRequestForm.imwApplyBaseDate)}"
				imwNodeId="${f:h(workflowRequestForm.imwNodeId)}" imwFlowId="${f:h(workflowRequestForm.imwFlowId)}"
				imwCallOriginalParams="${f:h(workflowRequestForm.imwCallOriginalParams)}" imwNextScriptPath="${f:h(workflowRequestForm.imwCallOriginalPagePath)}">

				<%-- Global th colspan setting --%>
				<c:set var="thColspan" value="2" scope="request" />
				
				<%-- Practice 0 Section --%>
				<jsp:include page="include/practice0_header.jsp" />

				<%-- Practice 1 Section --%>
				<jsp:include page="include/practice1_basic.jsp" />

				<%-- Practice 2 Section --%>
				<jsp:include page="include/practice2_multiple_data.jsp" />

				<%-- Practice 7 Section --%>
				<jsp:include page="include/practice7_multiple_branch.jsp" />

				<%-- Practice 8 Section --%>
				<jsp:include page="include/practice8_multiple_user.jsp" />

			</workflow:workflowOpenPage>
			
			<%-- Practice 3 Section --%>
			<jsp:include page="include/practice3_attachment.jsp" />
			
		</div>
	</imui:tabItem>
</imui:tabs>

<!-- Hidden Back Form -->
<form name="backForm" id="backForm" method="POST" action="${f:h(workflowRequestForm.imwCallOriginalPagePath)}">
	<input type="hidden" name="imwCallOriginalParams" value="${f:h(workflowRequestForm.imwCallOriginalParams)}" />
</form>
