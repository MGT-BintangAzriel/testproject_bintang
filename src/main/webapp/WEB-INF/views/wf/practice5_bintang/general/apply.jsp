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
      // Disable practice8 fields
      disablePractice8Fields();

      // Initialize calendar inputs and select2
      $(".imuiCalendar").imuiCalendar({
        changeMonth : true,
        changeYear : true,
        onSelect : function() {
          $(this).trigger('change');
        }
      });

      $('.select2').select2();
      $('.select2').select2().on('change', function() {
        $(this).valid();
    });;

      // Setup sub option on radio button
      setupSubOptionToggle('f_agreement_status', 'amendment', 'f_total_duration', 'f_total_duration_1');
      setupSubOptionToggle('f_agreement_classification', 'pd', 'f_pd_sub_condition', 'f_pd_sub_1');
      setupSubOptionToggle('f_ec_approval', 'yes', 'f_ec_sub_condition', 'f_ec_sub_1');

      // Toggle input for depreciation check
      toggleDepreciation();
      $('input[name="f_purchase_category"]').on("change", toggleDepreciation);

      // Multi data category section toggle
      setupMultiDataToggle();

      // Dynamic payment table setup
      setupDynamicPaymentTable();


      // Delete file attachment handler
      $(".file_attachment_list").on("click", "#delete_file", function() {
        $(this).closest("tr").remove();
        var fileName = $(this).attr("name");
        $("." + fileName).remove();
      });

      // Format numeric field
      $("#f_total_amount, #f_total_payment_amount").on("change", function() {
        var input = $(this);
        var value = input.val();
        if (value) {
          var unformatted = value.replace(/[^0-9.]/g, '');
          var unformattedNum = parseFloat(unformatted) || 0;
          if (unformattedNum) {
            input.val(unformattedNum.toLocaleString('en-US', {
              minimumFractionDigits : 2,
              maximumFractionDigits : 2
            }));
          } else {
            input.val('');
          }
        }
      });

      // Hide calendar icon
      $('.ui-datepicker-trigger').hide();

      $('#openPage').on('mousedown',
          function() {
            isApplyClicked = true;
            imuiResetForm('#workflowOpenPageForm'); 
            var valid = validateWorkflowForm();

            if (valid) {
              workflowOpenPage('${f:h(workflowRequestForm.imwPageType)}');

            } else {
              imuiShowErrorMessage('There are validation errors. Please check your inputs.', [],
                  true, 2500, false);
            }
          });
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

				<!-- Hidden Input Attachment -->
				<div class="file_attachment">
					<input type='text' value='' name='f_attachment_anchor' id='f_attachment_anchor' class="f_attachment_anchor"
						style="position: absolute; opacity: 0; pointer-events: none; width: 0; height: 0;" tabindex="-1">
					<c:forEach items="${savedFormData.d_list_attachment}" var="attachment">
						<div class="${attachment.file_real_name}">
							<input type='hidden' value='${attachment.id}' id='f_upload_file_id' name='f_upload_file_id' class='f_upload_file_id'>
							<input type='hidden' value="${attachment.file_name}" id='f_upload_file_name' name='f_upload_file_name'>
							<input type='hidden' value="${attachment.file_real_name}" id='f_upload_file_real_name' name='f_upload_file_real_name'>
							<input type='hidden' value="${attachment.file_type}" id="f_upload_file_type" name="f_upload_file_type">
							<input type='hidden' value="${attachment.file_size}" id="f_upload_file_size" name="f_upload_file_size">
							<input type='hidden' value="${attachment.file_extension}" id="f_upload_file_extension" name="f_upload_file_extension">
						</div>
					</c:forEach>
				</div>

			</workflow:workflowOpenPage>

			<%-- Practice 3 Section --%>
			<jsp:include page="include/practice3_attachment.jsp" />

		</div>
	</imui:tabItem>
</imui:tabs>

<!-- Operation Buttons -->
<div class="imui-operation-parts">
	<imart:decision case="0" value="${f:h(workflowRequestForm.imwPageType)}">
		<input type="button" value="Apply" id="openPage" name="openPage" class="imui-large-button" escapeXml="true" escapeJs="false" />
	</imart:decision>
	<imart:decision case="3" value="${f:h(workflowRequestForm.imwPageType)}">
		<input type="button" value="Reapply" id="openPage" name="openPage" class="imui-large-button" escapeXml="true" escapeJs="false" />
	</imart:decision>
</div>

<!-- Hidden Back Form -->
<form name="backForm" id="backForm" method="POST" action="${f:h(workflowRequestForm.imwCallOriginalPagePath)}">
	<input type="hidden" name="imwCallOriginalParams" value="${f:h(workflowRequestForm.imwCallOriginalParams)}" />
</form>