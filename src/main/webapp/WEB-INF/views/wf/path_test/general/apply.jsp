
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="workflow" uri="http://www.intra-mart.co.jp/taglib/imw/workflow"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="im" uri="http://www.intra-mart.co.jp/taglib/im-tenant"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>

<imui:head>
	<title>Sample Workflow</title>
	<workflow:workflowOpenPageCsjs />
	
	<link href="ui/css/select2.min.css" rel="stylesheet" />
    <script src="ui/js/select2.min.js" type="text/javascript"></script>
    <script src="ui/js/jquery.validate.js" type="text/javascript"></script>
	
	<script type="text/javascript">

		
		function callbackSuccess(e, data) {
			var file = data.files[0];
			var fileName = file.name;
			var fileSize = file.size;
			var fileType = file.type;

			//受信した情報
			var receiveFile = data.result[0];
			var receiveFileName = receiveFile.name;
			var receivePhysicalFileName = receiveFile.physicalName;
			var receiveFileSize = receiveFile.size;

			var fileExtension = receiveFileName.split('.').pop().toLowerCase();
			
			$(".file_attachment").prepend("<div class='" + receivePhysicalFileName + "'>"
				+ "<input type='hidden' id='f_upload_file_id' name='f_upload_file_id'>"
				+ "<input type='hidden' value='" + receiveFileName + "' id='f_upload_file_name' name='f_upload_file_name'>"
				+ "<input type='hidden' value='" + receivePhysicalFileName + "' id='f_upload_file_real_name' name='f_upload_file_real_name'>"
				+ "<input type='hidden' value='" + fileExtension + "' id='f_upload_file_type' name='f_upload_file_type'>"
				+ "</div>");
		}
		function callbackRemove(e, data) {
			var file = data.response[0];
			var fileName = file.name;
			$("." + fileName).remove();
		}
		function callbackError(e, data) {
			var file = data.files[0];
			var fileName = file.name;
			var fileSize = file.size;
			var fileType = file.type;
			
		}

		var rules = {
			f_name: {required: true},
			f_age : {required: true}, 
		};
		
		var messages = {
			f_name: {required: "名前を入力してください" },
			f_age : {required: "年齢を入力してください"},		    
		};

        $(function(){
            // Initialize calendar and select2 for existing rows on page load
            $(".imuiCalendar").imuiCalendar({
                changeMonth: true,
                changeYear: true
            });
            $("select").select2();
           
			$('#openPage').click(function() {
		
				var valid = imuiValidate('#workflowOpenPageForm', rules, messages);
				
				
				if (valid) {
					workflowOpenPage('${f:h(ApplyForm.imwPageType)}');
				} else {
					imuiShowErrorMessage('インプットのエラーが発生しまいした。.', [], true, 2500, false);
				}
				
			}); 

			//Delete File Attachment
			$(".file_attachment_list").on("click", "#delete_file", function() {
				$(this).closest("tr").remove();
				var fileName = $(this).attr("name");
				$("." + fileName).remove();
			});

			$("#addrows_function").click(function (){
							
			var data_vendor = JSON.parse($("#jsonVendor").val());
					var d_vendor = data_vendor;
					var vendor_id = d_vendor.map(function(vendor){
					return vendor.id;
					})
					var vendor_name = d_vendor.map(function(vendor){
					return vendor.vendor_name;
					})
					
				
				var table = $(".table_sample");
				var rowCount = table.find('tr').length;
				var row = $("<tr>");
				
				var select_vendor = "<option value='' selected disabled>Choose Vendor</option>";
			for (var i=0 ; i< vendor_name.length; i++){
				data_vendor = vendor_name[i];
				select_vendor += "<option value='" + data_vendor + "'" + ">" + data_vendor + "</option>";
			} 
				
				var cellDelete = $("<td></td>").append(
				"<input type='button' value='Delete' class='data_delete imui-medium-button'/>");

				var cellSeqNumber = $("<td></td>").append(
				"<input type='text' class='numeric sequence' id='d_sequence_no' name='d_sequence_no' value='" + rowCount + "' size='5' minlength='1' readonly placeholder='Sequence'>"
				);

				var cellItem = $("<td></td>").append(
				"<input type='text' name='d_item_name' value='' placeholder='Input..'>"	
				);

				var cellQuantity = $("<td></td>").append(
					"<div class=''>"
					+"<input type='number' id='d_quantity' name='d_quantity' min='1' max='100'>"
					+"</div>"
					);

				var cellSelect = $("<td></td>").append(
					"<select name='d_vendor' class='d_vendor' style='width:200px'>"	
					+ select_vendor
					+ "</select>"
					);

				var cellDateTime = $("<td></td>").append(
					"<input name='d_senddate' class='imuiCalendar' value='' style=' height: 20px;' type='text' placeholder='yyyy-MM-dd'>"  				
					);
				
				row.append(cellDelete, cellSeqNumber, cellItem, cellQuantity, cellSelect, cellDateTime);
				
				table.find("tbody").append(row);
				
				$("select").select2();
				$(".imuiCalendar").imuiCalendar({
				changeMonth: true,
				changeYear: true
				});

			});
						
			$(".table_sample").on("click", ".data_delete", function() {
			$(this).closest("tr").remove();
			refreshSequenceNumbers();

			});
						
			function refreshSequenceNumbers(){
			$(".rows_detail tr").each(function (index) {
			var newSerial = index + 1;

			$(this).find("input[name='d_sequence_no']").val(index + 1);
			
			}); 	
			};
           

        });   
	</script>
	
	<!-- CSS Scripts -->
    <style type="text/css">
        
    </style>
</imui:head>

<workflow:workflowUserContentsAuth imwApplyBaseDate='${f:h(ApplyForm.imwApplyBaseDate)}'
            imwAuthUserCode = '${f:h(ApplyForm.imwAuthUserCode)}'
            imwFlowId='${f:h(ApplyForm.imwFlowId)}'
            imwNodeId ='${f:h(ApplyForm.imwNodeId)}'
            imwPageType = '${f:h(ApplyForm.imwPageType)}'
            imwSystemMatterId='${f:h(ApplyForm.imwSystemMatterId)}'
            imwUserDataId='${f:h(ApplyForm.imwUserDataId)}'/>
            

<div class="imui-title-small-window">
	<h1>Workflow</h1>
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
<imui:tabItem title="Workflow" >
	<div class="imui-form-container">
		<workflow:workflowOpenPage name="workflowOpenPageForm"
				id="workflowOpenPageForm"
				method="POST"
				target="_top"
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
					<h2>Input Form</h2>
				</header>
				
				<table class="imui-form tab_header">
					<tbody>
						<tr>
							<th width="250">
								<label class="imui-required" style="margin-left:20px">Name</label>
							</th>
							<td>
								<input type="text" value="${FormClassRows.getF_name()}" id="f_name" name="f_name" placeholder="Name.." style="height:20px" size="50">
								
							</td>
						</tr>
              
	                    <tr>
                    		<th>
	                            <label class="imui-required" style="margin-left:20px">Age</label>
	                        </th>
	                        <td>
	                            <div class="">
									<input type="number" value="${FormClassRows.getF_age()}" id="f_age" name="f_age" min="0" max="500" placeholder="0">                              
	                            </div>
	                        </td>
	                            
	                    </tr>
	                    
	                    <tr>
	                    	<th width="250">
								<label style="margin-left : 20px">Notes</label>
							</th>
	                        <td>
	                            <div class="form-group">
	                                <textarea rows="3"
	                                            cols="40" 
	                                            name="f_note" 
	                                            id="f_note" 
	                                            class=""
	                                            style="margin-left: 5px;">${FormClassRows.getF_note()}</textarea>
	                            </div>
	                        </td>
	                    </tr>
					</tbody>
				</table>

				
				<header class="imui-chapter-title">
					<h2>Courier Service</h2>
				</header>
				<div id="tbl_send" class="imui-form-container-full scrollbox tbl_content">
					<table class="imui-form table_sample">
					<thead>
						<tr>
						<th><label><imui:button value="Add" id="addrows_function" class="imui-medium-button " /></label></th>
						<th><label>No</label></th>
						<th><label class="imui-required">Item Name</label></th>
						<th><label>Quantity</label></th>
						<th><label>Vendor Courier</label></th>
						<th><label class="imui-required">Date Send</label></th>
						
						</tr>
					</thead>
					
					<tbody class="rows_detail">
						<c:forEach items="${FormClassRows.d_list_detail_info}" var="d_detail">
						<tr>
						<td>
							<input type="button" value="Delete" class="table_delete imui-medium-button"/>
						</td>
						
						<td>
							<input type="text" class="numeric sequence" name="d_sequence_no" value="${d_detail.seq_number}" size="5" minlength="1"/>
						</td>
						<td>
							<input type="text" name="d_item_name" value="${d_detail.item_name}" size="25"/>
						</td>
						<td>
							<input type="number" id="d_quantity" name="d_quantity" min="0" max="100" value="${d_detail.quantity}">                              
									</td>
									<td>
							<select name="d_vendor" class="d_vendor select2" style="width:308px">
							<option selected value="">Choose Vendor</option>
							<c:forEach items="${vendorList}" var="vendor">
								<c:choose>
								<c:when test="${vendor.vendor_name == d_detail.vendor}">
									<option selected value="${vendor.vendor_name}" >${vendor.vendor_name}</option>
								</c:when>
								<c:otherwise>
									<option value="${vendor.vendor_name}">${vendor.vendor_name}</option>
								</c:otherwise>
								</c:choose>
							</c:forEach>
							</select>
						</td>
						<td>
							<input name='d_senddate' class='imuiCalendar' value='${d_detail.send_date}' style=' height: 20px;' type='text' placeholder='yyyy-MM-dd'>  				
						</td>
						
						
						</tr>
					
					</c:forEach>
					
					</tbody>
					</table>
				</div>

				<!-- Hidden Input Attachment -->
				<div class="file_attachment">
					<c:forEach items="${FormClassRows.d_list_attachment}" var="attachment">
						<div class="${attachment.file_real_name}">
							<input
									type='hidden'
									value='${attachment.id}'
									id='f_upload_file_id'
									name='f_upload_file_id'
							>
							<input
									type='hidden'
									value="${attachment.file_name}"
									id='f_upload_file_name'
									name='f_upload_file_name'
							>
							<input
									type='hidden'
									value="${attachment.file_real_name}"
									id='f_upload_file_real_name'
									name='f_upload_file_real_name'
							>
							<input
									type='hidden'
									value="${attachment.file_type}" 
									id="f_upload_file_type"
									name="f_upload_file_type"
							>
						</div>
					</c:forEach>
				</div>	
				
		</workflow:workflowOpenPage>

		
		<div class="imui-form-container-full">
			<header class="imui-chapter-title">
				
				<h2>
				Attachment
				</h2>
				
			</header>
			<table class="imui-form">
				<tbody>
					<tr>
						<th width="250"><label style="font-weight:Bold">Document Upload File</label></th>
						<td>
							<imui:fileUpload
									enableDelete="true"
									uniqueFileName="true"
									storeTo="file_attachment/"
									onSuccess="callbackSuccess"
									onError="callbackError"
									onRemove="callbackRemove"
							/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<imart:decision case="3" value="${f:h(ApplyForm.imwPageType)}">
		<div class="imui-form-container-full">
		<header class="imui-chapter-title">
			<h2>File Attached</h2>
		</header>
		<table class="imui-form file_attachment_list">
			<tbody>
			<c:forEach items="${FormClassRows.d_list_attachment}" var="file">
				<tr>
				<td width='100'>
					<input type='button' value='Delete' name="${item_file.file_real_name}" id='delete_file' class='imui-medium-button' />
				</td>
				<td>
					<a href="path_test/download/${file.id}">${file.file_name}</a>
				</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		</imart:decision>

		
	</div>
	
</imui:tabItem>
</imui:tabs>


		
<!-- Button Default -->
<div class="imui-operation-parts">
	<imart:decision case="0" value="${f:h(ApplyForm.imwPageType)}">	
		<input type="button" value='Apply' id="openPage" name="openPage" class="imui-large-button"
			escapeXml="true" escapeJs="false" />
	</imart:decision>
	<imart:decision case="3" value="${f:h(ApplyForm.imwPageType)}">
		<input type="button" value='Re-Apply' id="openPage" name="openPage" class="imui-large-button"
			escapeXml="true" escapeJs="false" />
	</imart:decision>
	
</div>

<form name="backForm" id="backForm" method="POST" action="${f:h(ApplyForm.imwCallOriginalPagePath)}">
    <input type="hidden" name=imwCallOriginalParams value="${f:h(ApplyForm.imwCallOriginalParams)}" />
</form>
