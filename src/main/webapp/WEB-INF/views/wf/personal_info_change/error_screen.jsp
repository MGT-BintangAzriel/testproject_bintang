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
			
		});
	</script>
	<style type="text/css">
		.main-section {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        .error-container {
            text-align: center;
            padding: 2rem;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
            max-width: 400px;
            border-top: 5px solid #dc3545; /* Crimson Red Accent */
        }
        h1 {
            color: #dc3545; /* Red Title */
            font-size: 2.5rem;
            margin-bottom: 0.5rem;
        }
        p {
            color: #6c757d;
            font-size: 1.1rem;
            line-height: 1.5;
            margin-top: 0;
        }
        .btn {
            display: inline-block;
            margin-top: 1.5rem;
            padding: 0.75rem 1.5rem;
            background-color: #dc3545;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
            transition: background 0.2s;
        }
        .btn:hover {
            background-color: #bd2130;
        }
	</style>
</imui:head>

<div class="imui-title-small-window">
	<h1>${f:h(savedFormData.f_workflow_name)}</h1>
</div>
<div class="imui-toolbar-wrap">
 	<div class="imui-toolbar-inner">
		<ul class="imui-list-toolbar">
			<li>
			</li>
		</ul>
	</div>
</div>

<div class="main-section">
	<div class="error-container">
	    <h1>System Error!</h1>
	    <p>${f:h(error_message_jpn)}<br>${f:h(error_message_eng)}</p>
	</div>
</div>


<!-- Intra-mart Action for Back Button	 -->
<form name="backForm" id="backForm" method="POST" action="${f:h(workflowRequestForm.imwCallOriginalPagePath)}">
	<input type="hidden" name=imwCallOriginalParams value="${f:h(workflowRequestForm.imwCallOriginalParams)}" />
</form>
