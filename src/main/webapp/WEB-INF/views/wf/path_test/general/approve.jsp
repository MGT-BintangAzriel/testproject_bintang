
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
		

        $(function(){
        	

        	$('#pdfgenerate').click(function() {
            var system_matter_id = '${f:h(ApplyForm.imwSystemMatterId)}';
            $.ajax({
                  type: "POST",
                    url: "path_test/generatepdf", 
                    data: { 
                      system_matter_id : system_matter_id,
                    },
                    success: function (response) {
                        if (response.trim() === "success") {
                          
                            // Do Something
                          window.location.href = "/imarttest/path_test/downloadpdf/sample.pdf";
                            
                        } else {
                          console.log("LOG Error :" , response);
                            alert("Failed to Generate PDF");
                        }
                    },
                    error: function (xhr, status, e) {
                        console.error("AJAX ERROR :", e);
                        //alert("Server error.");
                    }
                });
          });


			$('#ajaxtest').click(function() {
				var system_matter_id = '${f:h(ApplyForm.imwSystemMatterId)}';
				console.log("Matter id", system_matter_id);

				$.ajax({
					type: "POST",
					url: "path_test/ajaxtest", 
					data: { 
						system_matter_id : system_matter_id,
					},
					success: function (response) {
						if (response.trim() === "success") {
							
							// Do Something
							console.log("Ajax Sucess");
							
						} else {
							console.log("LOG Error :" , response);
							alert("Failed to access Ajax.");
						}
					},
					error: function (xhr, status, e) {
						console.error("AJAX ERROR :", e);
						//alert("Server error.");
					}
				});
			});

			
			$('.select2').select2();
			$(".imuiCalendar").imuiCalendar({
			changeMonth: true,
			changeYear: true
			});
           
            $('#openPage').click(function() {
   
            	
            	workflowOpenPage('${f:h(ApplyForm.imwPageType)}');
            	
                
            });  
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

				<input type="hidden" name="vendorList" id="vendorList" value="${f:h(vendorList)}">
				
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
								<input type="text" readonly value="${FormClassRows.getF_name()}" id="f_name" name="f_name" placeholder="Name.." style="height:20px" size="50">
								
							</td>
						</tr>
              
	                    <tr>
                    		<th>
	                            <label class="imui-required" style="margin-left:20px">Age</label>
	                        </th>
	                        <td>
	                            <div class="">
									<input type="number" readonly value="${FormClassRows.getF_age()}" id="f_age" name="f_age" min="0" max="500" placeholder="0">                              
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
	                                            style="margin-left: 5px;"
	                                            readonly
												>${FormClassRows.getF_note()}</textarea>
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
						<input type="text" class="numeric sequence" name="d_sequence_no" value="${d_detail.seq_number}" size="5" minlength="1" readonly/>
					</td>
					<td>
						<input type="text" name="d_item_name" value="${d_detail.item_name}" size="25" readonly/>
					</td>
					<td>
						<input type="number" id="d_quantity" name="d_quantity" min="0" max="100" readonly value="${d_detail.quantity}">                              
								</td>
								<td>
						<select name="d_vendor" class="d_vendor select2" style="width:308px">
						<option selected disabled value="">Choose Vendor</option>
						<c:forEach items="${vendorList}" var="vendor">
							<c:choose>
							<c:when test="${vendor.vendor_name == d_detail.vendor}">
								<option disabled selected value="${vendor.vendor_name}" >${vendor.vendor_name}</option>
							</c:when>
							<c:otherwise>
								<option disabled value="${vendor.vendor_name}">${vendor.vendor_name}</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
						</select>
					</td>
					<td>
						<input type="text" class="imuiCalendar" style="height:20px;" placeholder="yyyy/MM/dd" name="d_senddate"  readonly value="${d_detail.send_date}"/>
					</td>
					
					
					</tr>
				
				</c:forEach>
				</tbody>
				</table>
				</div>

				<!-- Attachment Show File -->
				<div class="imui-form-container-full">
					<header class="imui-chapter-title">
						<h2>To See Upload Attachment</h2>
					</header>
					<table class="imui-form">
						<tbody>
							<c:forEach items="${FormClassRows.d_list_attachment}" var="file">
								<tr>
									<td>
										<a href="path_test/download/${file.id}">${file.file_name}</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div> 
				
		</workflow:workflowOpenPage>
		
		
		
	</div>
	
</imui:tabItem>
</imui:tabs>


		

<!-- Button Default -->
<div class="imui-operation-parts">
	<input type="button" value='Process' id="openPage" name="openPage" class="imui-large-button" escapeXml="true" escapeJs="false" />
</div>

<div class="imui-operation-parts">
	<input type="button" value='Ajax' id="ajaxtest" name="ajaxtest" class="imui-large-button" escapeXml="true" escapeJs="false" />
</div>

<div class="imui-operation-parts">
	<input type="button" value='PDF' id="pdfgenerate" name="pdfgenerate" class="imui-large-button" escapeXml="true" escapeJs="false" />
</div>

<form name="backForm" id="backForm" method="POST" action="${f:h(ApplyForm.imwCallOriginalPagePath)}">
    <input type="hidden" name=imwCallOriginalParams value="${f:h(ApplyForm.imwCallOriginalParams)}" />
</form>