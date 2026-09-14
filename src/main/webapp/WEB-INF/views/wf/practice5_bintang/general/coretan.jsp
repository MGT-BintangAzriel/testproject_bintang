
<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br"><label for="imui-8i59xvapkwg9po8">Payment Date:
    
      &nbsp;<span class="imui-smart-ui-container-required">*</span>
    
  </label><div id="f_payment_date_1_form" class="imsp-datepicker-wrapper">
	<span class="imsp-textbox">
		<div class="ui-input-text ui-shadow-inset ui-corner-all ui-btn-shadow ui-body-a"><input type="text" name="f_payment_date_1" id="f_payment_date_1" value="" readonly="" placeholder="Enter payment date..." class="payment-date f_payment_date ui-input-text ui-body-a"></div>
	</span>
	<span>
		<a data-role="button" href="#f_payment_date_1_pop" data-rel="dialog" data-icon="grid" "="" data-iconpos="notext" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-theme="a" title="" class="ui-btn ui-btn-up-a ui-shadow ui-btn-corner-all ui-btn-icon-notext"><span class="ui-btn-inner"><span class="ui-btn-text"></span><span class="ui-icon ui-icon-grid ui-icon-shadow">&nbsp;</span></span></a>
	</span>
	<span>
		<a data-imsp-role="datePicker-remove" data-role="button" data-icon="delete" data-inline="true" data-iconpos="notext" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-theme="a" title="" class="ui-btn ui-btn-up-a ui-shadow ui-btn-corner-all ui-btn-inline ui-btn-icon-notext"><span class="ui-btn-inner"><span class="ui-btn-text"></span><span class="ui-icon ui-icon-delete ui-icon-shadow">&nbsp;</span></span></a>
	</span>
</div>

<script>
$("#f_payment_date_1_pop").appendTo(document.body);
(function($) {
	$(document).bind("pagecreate", function() {
		$("#f_payment_date_1_form").find("a[data-imsp-role='datePicker-remove']")
		.unbind("tap").tap(function() {
			$("input[name='f_payment_date_1']").val("");
		});
		$("#f_payment_date_1_pop")
			.imspDialogCalendar({
			   calendarId:"USA_CAL",
			   format:    "yyyy/MM/dd",
			   displayFormat: "MMM d, yyyy",
			   dayLabels:["SUN","MON","TUE","WED","THU","FRI","SAT"],
			   actionGetHoliday:"/imarttraining/system/common/parts/mobile_fw/calendar/spCalendar/getHoliday",
			   actionFormatDate:"/imarttraining/system/common/parts/mobile_fw/calendar/spCalendar/formatDate",
			   firstDay:0,
			   date:new Date("2026/09/14"),
			   callbackFunction: onDatePickerClose,
			   targetName:"f_payment_date_1"
		});
	});
})(jQuery);
</script>

<div class="error_message"></div></div>

<div data-role="page" id="f_payment_date_1_pop" data-close-btn="none" data-url="f_payment_date_1_pop">
	<div data-role="header">
		<a data-role="button" data-iconpos="notext" data-icon="delete" data-rel="back"></a>
		<h3>Date settings</h3>
		<a data-role="button" data-iconpos="notext" data-icon="gear" data-imsp-role="datePicker-change"></a>
	</div>
	<div data-role="content" data-theme="c">
		<div data-imsp-role="calendarLayer">
			<span class="imui-smart-ui-calendar-header">
				<a data-role="button" data-imsp-role="previous" data-icon="minus" data-inline="true" data-iconpos="notext"></a>
			</span>
			<span class="imui-smart-ui-calendar-header imui-smart-ui-calendar-title" data-imsp-role="calendar-title">Sep 14, 2026<br></span>
			<span class="imui-smart-ui-calendar-header imui-smart-ui-calendar-header-right">
				<a data-role="button" data-imsp-role="next" data-icon="plus" data-inline="true" data-iconpos="notext"></a>
			</span>
			<table width="100%" cellpadding="3" cellspacing="0" style="font-size:9pt;color:black" data-imsp-role="calendarBody">
			<tbody><tr style="text-align:center;font-weight:bold"><td style="color: rgb(255, 0, 0);">SUN</td><td style="color: rgb(0, 0, 0);">MON</td><td style="color: rgb(0, 0, 0);">TUE</td><td style="color: rgb(0, 0, 0);">WED</td><td style="color: rgb(0, 0, 0);">THU</td><td style="color: rgb(0, 0, 0);">FRI</td><td style="color: rgb(0, 0, 255);">SAT</td></tr><tr class="imui-smart-ui-tr-calendar"><td data-imsp-date="" data-imsp-datelabel="" style="color: rgb(255, 0, 0);"></td><td data-imsp-date="" data-imsp-datelabel="" style="color: rgb(0, 0, 0);"></td><td data-imsp-date="1" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">1</td><td data-imsp-date="2" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">2</td><td data-imsp-date="3" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">3</td><td data-imsp-date="4" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">4</td><td data-imsp-date="5" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 255);">5</td></tr><tr class="imui-smart-ui-tr-calendar"><td data-imsp-date="6" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(255, 0, 0);">6</td><td data-imsp-date="7" data-imsp-datelabel="Labor&amp;nbsp;Day" class="imui-smart-ui-datepicker-cell" style="color: rgb(255, 0, 0);">7</td><td data-imsp-date="8" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">8</td><td data-imsp-date="9" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">9</td><td data-imsp-date="10" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">10</td><td data-imsp-date="11" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">11</td><td data-imsp-date="12" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 255);">12</td></tr><tr class="imui-smart-ui-tr-calendar"><td data-imsp-date="13" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(255, 0, 0);">13</td><td data-imsp-date="14" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell imui-smart-ui-datepicker-cell-selected" style="color: rgb(0, 0, 0);">14</td><td data-imsp-date="15" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">15</td><td data-imsp-date="16" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">16</td><td data-imsp-date="17" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">17</td><td data-imsp-date="18" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">18</td><td data-imsp-date="19" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 255);">19</td></tr><tr class="imui-smart-ui-tr-calendar"><td data-imsp-date="20" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(255, 0, 0);">20</td><td data-imsp-date="21" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">21</td><td data-imsp-date="22" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">22</td><td data-imsp-date="23" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">23</td><td data-imsp-date="24" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">24</td><td data-imsp-date="25" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">25</td><td data-imsp-date="26" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 255);">26</td></tr><tr class="imui-smart-ui-tr-calendar"><td data-imsp-date="27" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(255, 0, 0);">27</td><td data-imsp-date="28" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">28</td><td data-imsp-date="29" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">29</td><td data-imsp-date="30" data-imsp-datelabel="" class="imui-smart-ui-datepicker-cell" style="color: rgb(0, 0, 0);">30</td><td data-imsp-date="" data-imsp-datelabel="" style="color: rgb(0, 0, 0);"></td><td data-imsp-date="" data-imsp-datelabel="" style="color: rgb(0, 0, 0);"></td><td data-imsp-date="" data-imsp-datelabel="" style="color: rgb(0, 0, 255);"></td></tr><tr class="imui-smart-ui-tr-calendar"><td data-imsp-date="" data-imsp-datelabel="" style="color: rgb(255, 0, 0);"></td><td data-imsp-date="" data-imsp-datelabel="" style="color: rgb(0, 0, 0);"></td><td data-imsp-date="" data-imsp-datelabel="" style="color: rgb(0, 0, 0);"></td><td data-imsp-date="" data-imsp-datelabel="" style="color: rgb(0, 0, 0);"></td><td data-imsp-date="" data-imsp-datelabel="" style="color: rgb(0, 0, 0);"></td><td data-imsp-date="" data-imsp-datelabel="" style="color: rgb(0, 0, 0);"></td><td data-imsp-date="" data-imsp-datelabel="" style="color: rgb(0, 0, 255);"></td></tr></tbody></table>
			<a data-role="button" data-imsp-role="desideDate">Select</a>
		</div>
		<div data-imsp-role="dateSelectLayer" style="display:none;width:100%">
			<div style="float:left;width:40%;">
				<div style="text-align:center"><a data-role="button" data-imsp-role="datePicker-addYear" data-inline="true" data-icon="plus" data-iconpos="bottom"></a></div>
				<div style="text-align:center"><input type="text" readonly="" style="text-align:center"></div>
				<div style="text-align:center"><a data-role="button" data-imsp-role="datePicker-pullYear" data-inline="true" data-icon="minus" data-iconpos="bottom"></a></div>
			</div>
			<div style="float:left;width:40%;margin-right:0px;padding-left:15%">
				<div style="text-align:center"><a data-role="button" data-imsp-role="datePicker-addMonth" data-inline="true" data-icon="plus" data-iconpos="bottom"></a></div>
				<div style="text-align:center"><input type="text" readonly="" style="text-align:center"></div>
				<div style="text-align:center"><a data-role="button" data-imsp-role="datePicker-pullMonth" data-inline="true" data-icon="minus" data-iconpos="bottom"></a></div>
			</div>
			<table width="100%"></table>
			<a data-role="button" data-imsp-role="datePicker-jumpDate" data-icon="check">Decide</a>
		</div>
	</div>
</div>  

