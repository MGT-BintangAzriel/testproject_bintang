<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui" %>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard" %>
<%@ taglib prefix="workflow" uri="http://www.intra-mart.co.jp/taglib/imw/workflow" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="im" uri="http://www.intra-mart.co.jp/taglib/im-tenant" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">

	function toggleUpload_ResidentRegisterMyNumber() {
	    // 1. Hide both elements upfront (chained for efficiency)
	    const $withMyNumber = $(".t_resident_register_with_my_number").hide();
	    const $withoutMyNumber = $(".t_resident_register_without_my_number").hide();
	
	    //$("#name_attach_header").hide();
	    //$("#name_attach_part").hide();
	
	    //delete rules.resident_registration_with_mynumber_anchor;
	    //delete rules.resident_registration_without_mynumber_anchor;
	
	    // 2. Get checked categories
	    const checkedValues = $('input[name="f_category"]:checked').map(function() {
	        return $(this).val();
	    }).get();
	
	    var upload_without_mynumber = false;
	    var upload_with_mynumber = false;
	
	    // 3. Evaluate conditional logic
	    if (checkedValues.includes("change_spouse")) {
	        const selectValue_dependent = $("input[name='f_change_spouse_dependent']:checked").val();
	        if (selectValue_dependent === "add_to_dependent") {
	            upload_with_mynumber = true;
	        }
	    }
	
	    // Only check dependents if we haven't already flagged "with_mynumber"
	    if (!upload_with_mynumber && checkedValues.includes("change_dependent")) {
	        const selectValue_dependent = $(".f_change_dependent_status:checked[value='add']").length > 0;
	        
	        if (selectValue_dependent == true) {
	            //console.log("enter change dependant - add");
	            upload_with_mynumber = true;
	        }
	    }
	
	    if (checkedValues.includes("change_address")) {
	        const selectValue = $("input[name='f_change_address_register']:checked").val();
	        if (selectValue === "yes") {
	            //console.log("enter change f_change_address_register - yes");
	            upload_without_mynumber = true;
	        }
	    }
	
	    // Because 'with_mynumber' overrides 'without_mynumber' when both are true,
	    if (upload_with_mynumber) {
	        //$("#name_attach_header").show();
	        //$("#name_attach_part").show();
	
	        $withMyNumber.show();
	
	
	    } else if (upload_without_mynumber) {
	        //$("#name_attach_header").show();
	        //$("#name_attach_part").show();
	
	        $withoutMyNumber.show();
	
	    }
	}
	
	$(function () {
		$(".eng-txt-version").hide();
		$(".msg").hide();
		
		$(".section_change_name").hide();
		$(".section_change_address").hide();
		$(".section_change_spouse").hide();
		$(".section_change_dependent").hide();
		$(".section_change_emergency_contact").hide();
		
		$(".emergency_contact_domestic").hide();
    	$(".emergency_contact_overseas").hide();
    	
    	$(".emergency_contact_2_domestic").hide();
    	$(".emergency_contact_2_overseas").hide();
		
		var checkedValues = $('input[name="f_category"]:checked').map(function() {
			return $(this).val();
		}).get();
		console.log(checkedValues);
		
		var contactType = $("input[name='f_change_emergency_contact_type']:checked").val();
		var contactType_2 = $("input[name='f_change_emergency_contact_2_type']:checked").val();
	
	
		if (checkedValues.includes("change_name")) {
		    $(".section_change_name").show();   
		}
	
		if (checkedValues.includes("change_address")) {
		    $(".section_change_address").show();
		}
	
		if (checkedValues.includes("change_spouse")) {
		    $(".section_change_spouse").show();   
		}
	
		if (checkedValues.includes("change_dependent")) {
		    $(".section_change_dependent").show();   
		}
	
		if (checkedValues.includes("change_emergency_contact")) {
		    $(".section_change_emergency_contact").show();   
		}
		
		//
		
		if (contactType == "overseas") {
            $(".emergency_contact_overseas").show();
        } else {
        	$(".emergency_contact_domestic").show();
        }
		
		//
		
		if (contactType_2 == "overseas") {
            $(".emergency_contact_2_overseas").show();
        } else {
        	$(".emergency_contact_2_domestic").show();
        }
		
		toggleUpload_ResidentRegisterMyNumber();
		
		
		$('.pointer-dis-textarea')
		.prop('readonly', true)
		.attr('tabindex', '-1')
		.css({
			'caret-color': 'transparent',
			'user-select': 'none',
			'-webkit-user-select': 'none',
			'cursor': 'default'
		})
		.on('focus', function () { this.blur(); })
		.on('selectstart dragstart', function (e) { e.preventDefault(); });
		
		function checkDownloadTitle(){
        	var count_with_mynumber = $(".download_detail_resident_registration_with_mynumber").find(".download-btn").length;
        	var count_without_mynumber = $(".download_detail_resident_registration_without_mynumber").find(".download-btn").length;
        	
        	$(".download_detail_resident_registration_with_mynumber").hide();
        	$(".download_detail_resident_registration_without_mynumber").hide();
        	
        	if(count_with_mynumber != 0){
        		$(".download_detail_resident_registration_with_mynumber").show();
        	}
        	
        	if(count_without_mynumber != 0){
        		$(".download_detail_resident_registration_without_mynumber").show();
        	}
        }
        
        checkDownloadTitle();
	});
	
	
	
</script>

<style type="text/css">
	.font-imart {
	    height: 20px !important;
	    font-family: Arial, "メイリオ", "Meiryo", sans-serif !important;
	    font-size: 1.3rem !important;
	    font-style: normal !important;
	    font-weight: normal !important;
	    color: #050505 !important;
	    
	    -webkit-user-select: none !important;
		-moz-user-select: none !important;
		-ms-user-select: none !important;
		user-select: none !important;
	}
</style>

<header class="imui-chapter-title">
	<h2>
		申請者情報<br>
		<i class="eng-txt-version">Applicant Information</i>
	</h2>
</header>

<table class="imui-form tab_header"  style="table-layout:fixed; width:100%">

	<colgroup>
		<col style="width:300px;">  
		<col style=""> 
		<col style="width:300px;">  
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th>
				<label class="jpn-txt-version">申請番号</label>
				<label class="eng-txt-version">Application Number</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_application_number)}</label>
			</td>
			<th>
				<label class="jpn-txt-version">申請日</label>
				<label class="eng-txt-version">Application Date</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_application_date)}</label>
			</td>
		</tr>
		
		<tr>
			<th>
				<label class="jpn-txt-version">社員番号</label>
				<label class="eng-txt-version">Employee Number</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_applicant_number)}</label>
			</td>
			<th>
			<label class="jpn-txt-version">所属名</label>
				<label class="eng-txt-version">Department Name</label>
				
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_applicant_department)}</label>
			</td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version">申請者名</label>
				<label class="eng-txt-version">Applicant Name</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_applicant_name)}</label>			
			</td>
			<th>
				<label class="jpn-txt-version">役職 </label>
				<label class="eng-txt-version">Applicant Post</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_applicant_post)}</label>
			</td>
		</tr>
	</tbody>
</table>

<header class="imui-chapter-title">
    <h2>変更内容 <br><i class="eng-txt-version">Change details</i></h2>
    <label class=" jpn-txt-version msg" >変更する項目にチェックをつけてください。<br></label>
	<label class=" eng-txt-version msg">Check the terms you want to change.</label>
</header>

<table class="imui-form" style="table-layout:fixed; width:100%;">
	<colgroup>
		<col style="width:300px;">  
		<col style="">
		<col style="width:300px;">  
		<col> 
	</colgroup>
	
    <tbody>
        <tr>
            <th>
                <label class="jpn-txt-version ">変更区分</label>
                <label class="eng-txt-version">Change Category</label>
            </th>
            <td colspan="3">
            	<input type="checkbox" name="f_category" value="change_name" ${f:h(savedFormData.f_category_change_name)} tabindex="-1">
            		<label class="jpn-txt-version">氏名</label>
            		<label class="eng-txt-version">(Full Name)</label>
            		<br>
            	<input type="checkbox" name="f_category" value="change_address" ${f:h(savedFormData.f_category_change_address)} tabindex="-1">
            		<label class="jpn-txt-version">住所（会社で手配するレオパレスは除く）・電話番号</label>
                	<label class="eng-txt-version">(Address - Excluding company-arranged LeoPalace housing & Phone Number)</label>
                	<br>
            	<input type="checkbox" name="f_category" value="change_spouse" ${f:h(savedFormData.f_category_change_spouse)} tabindex="-1">
            		<label class="jpn-txt-version">配偶者</label>
                	<label class="eng-txt-version">(Spouse)</label>
                	<br>
            	<input type="checkbox" name="f_category" value="change_dependent" ${f:h(savedFormData.f_category_change_dependent)} tabindex="-1">
            		<label class="jpn-txt-version">扶養</label>
                	<label class="eng-txt-version">(Dependent)</label>
                	<br>
                <input type="checkbox" name="f_category" value="change_emergency_contact" ${f:h(savedFormData.f_category_change_emergency_contact)} tabindex="-1">
            		<label class="jpn-txt-version">緊急連絡先</label>
                	<label class="eng-txt-version">(Emergency Contact)</label>
                	<br>
                <div class="error_message"></div>
            </td>
        </tr>
        <tr>
            <th>
            	<label class="jpn-txt-version ">変更理由</label>
                <label class="eng-txt-version">Change Reason</label>
            </th>
            <td style="padding-right:20px;"  colspan="3">
            	<label class="font-imart">${f:h(savedFormData.f_change_reason)}</label>
                <div class="error_message"></div>
            </td>
        </tr>
    </tbody>
</table>

<header class="imui-chapter-title section_change_name">
    <h2>氏名情報<i class="eng-txt-version"><br>Name Information</i></h2>
    <label class=" jpn-txt-version msg" >口座名義を変更された場合は「給与口座変更申請書」もご提出ください。<br></label>
	<label class=" eng-txt-version msg">If the account holder name has been changed, please also submit the "Salary Account Change Application Form"</label>
</header>

<table class="imui-form section_change_name" style="table-layout:fixed; width:100%;">
	<colgroup>
		<col style="width:300px;">  
		<col style="">
		<col style="width:300px;">  
		<col>  
	</colgroup>
    <tbody>
        <tr>
            <th>
	            <label class="jpn-txt-version ">変更日</label>
	            <label class="eng-txt-version">Change Date</label>
           	</th>
            <td colspan="3">
            	<label class="font-imart">${f:h(savedFormData.f_change_name_date)}</label>
                <div class="error_message"></div>
            </td>
        </tr>

        <tr>
            <th>
	            <label class="jpn-txt-version ">変更後の氏名（漢字）</label>
	            <label class="eng-txt-version">Changed Name in (Kanji)</label>
			</th>
            <td colspan="3">
            	<label class="font-imart">${f:h(savedFormData.f_change_name_kanji_last_name)} ${f:h(savedFormData.f_change_name_kanji_first_name)}</label>
				<div class="error_message"></div>
            </td>
        </tr>
        <tr>
            <th>
	            <label class="jpn-txt-version ">変更後の氏名（フリガナ）</label>
	            <label class="eng-txt-version">Changed Name (Katakana)</label>
            </th>
            <td colspan="3">
            	<label class="font-imart">${f:h(savedFormData.f_change_name_kana_last_name)} ${f:h(savedFormData.f_change_name_kana_first_name)}</label>
            	<div class="error_message"></div>
            </td>
        </tr>
        <tr>
            <th>
            	<label class="jpn-txt-version ">今後社内で使用する氏名</label>
            	<label class="eng-txt-version">Name to Be Used Internally Going Forward</label>
            </th>
            <td colspan="3">
				<input type="radio" name="f_change_name_internal_use" value="new_name" ${f:h(savedFormData.f_change_name_internal_use_new_name)} tabindex="-1">
					<label class="jpn-txt-version">新しい名前を使用する </label>
		            <label class="eng-txt-version"> (Use New Name)</label>
                <br>
                <input type="radio" name="f_change_name_internal_use" value="previous_name" ${f:h(savedFormData.f_change_name_internal_use_use_previous_name)} tabindex="-1">
	                <label class="jpn-txt-version">今までの名前を利用する</label>
		            <label class="eng-txt-version"> (Use Previously Entered Name)</label>
		        <div class="error_message"></div>
            </td>
        </tr>
    </tbody>
</table>

<header class="imui-chapter-title section_change_address">
    <h2>住所情報<i class="eng-txt-version"><br>Address Information</i></h2>
    <label class=" jpn-txt-version msg" >
    	※住民票に変更ある時は、住民票(マイナンバー記載なし)を添付して下さい。<br>
		※宅建取引士証・管理業務主任者等の住所変更について<br>
		住民票住所を変更した場合は遅滞なく登録の変更を行政へ申請してください。（費用は自己負担）<br>
		宅建取引士については登録変更が完了したらHD人事部へ報告してください。<br>
	</label>
	<label class=" eng-txt-version msg">
		*If there are any changes to your resident registration address, please attach your resident registration certificate (without My Number).<br>
		*If you change your registered address, please apply to update your registration without delay.
	</label>
</header>

<table class="imui-form section_change_address" style="table-layout:fixed; width:100%;">
	<colgroup>
		<col style="width:300px;">  
		<col style="">
		<col style="width:300px;">  
		<col> 
	</colgroup>

    <tbody>
        <tr>
            <th>
            	<label class="jpn-txt-version  ">変更日</label>
            	<label class="eng-txt-version">Change Date</label>
            </th>
            <td>
            	<label class="font-imart">${f:h(savedFormData.f_change_address_date)}</label>
                <div class="error_message"></div>
            </td>
            <td colspan="2"></td>
        </tr>
		<tr>
            <th>
	            <label class="jpn-txt-version ">住民票住所変更</label>
	            <label class="eng-txt-version">Change of Registered Address</label>
			</th>
            <td>
                <input type="radio" name="f_change_address_register" value="yes" ${f:h(savedFormData.f_change_address_register_yes)} tabindex="-1">
                <label class="jpn-txt-version">あり</label>
	            <label class="eng-txt-version">(Yes)</label>
	            <br>
	            <input type="radio" name="f_change_address_register" value="no" ${f:h(savedFormData.f_change_address_register_no)} tabindex="-1">
                        
                <label class="jpn-txt-version">なし</label>
	            <label class="eng-txt-version">(No)</label>
	            
	            <div class="error_message"></div>
            </td>
            <td colspan="2"></td>
        </tr>
        
    </tbody>
</table>

<table class="imui-form section_change_address" style="table-layout:fixed; width:100%;">
    <colgroup>
		<col style="width:125px;">
		<col style="width:175px;">  
		<col style="">
		<col style="width:300px;">  
		<col>
	</colgroup>
    <tbody>
        <tr>
        	<th rowspan="3">
	            <label class="jpn-txt-version ">新住所</label>
	            <label class="eng-txt-version">New Residence</label>
			</th>
            <th>
	            <label class="jpn-txt-version ">郵便番号</label>
	            <label class="eng-txt-version">Postal Code</label>
			</th>
            <td>
            	<label class="font-imart">${f:h(savedFormData.f_change_address_new_postal_code)}</label>
                <div class="error_message"></div>
            </td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>
            	<label class="jpn-txt-version ">住所</label>
            	<label class="eng-txt-version">Address</label>
            </th>
            <td colspan="3">
            	<label class="font-imart">${f:h(savedFormData.f_change_address_new_address)}</label>
				<div class="error_message"></div>
            </td>
        </tr>
        <tr>
            <th>
	            <label class="jpn-txt-version">電話番号</label>
	            <label class="eng-txt-version">Phone Number</label>
            </th>
            <td>
            	<label class="font-imart">${f:h(savedFormData.f_change_address_new_phone_number)}</label>
				<div class="error_message"></div>
           	</td>
           	<td colspan="2"></td>
        </tr>
        <tr>
            <th colspan="2">
	            <label class="jpn-txt-version ">住民票住所</label>
	            <label class="eng-txt-version">Registered Address</label>
			</th>
            <td>
                <input type="radio" name="f_change_address_register_type" value="same_as_new_address" ${f:h(savedFormData.f_change_address_register_type_same_as_new_address)} tabindex="-1">
                <label class="jpn-txt-version">新住所と同じ</label>
	            <label class="eng-txt-version">(Same as New Address)</label>
	            <br>
	            <input type="radio" name="f_change_address_register_type" value="different_address" ${f:h(savedFormData.f_change_address_register_type_different_address)} tabindex="-1">
                <label class="jpn-txt-version">別住所</label>
	            <label class="eng-txt-version">(Different Address)</label>
	            <div class="error_message"></div>
            </td>
            <td colspan="2"></td>
        </tr>
    </tbody>
</table>

<table class="imui-form section_change_address" style="table-layout:fixed; width:100%;">
    <colgroup>
    	<col style="width:125px;">
		<col style="width:175px;"> 
    	<col>
    	<col style="width:300px;">  
		<col>
    </colgroup>
    <tbody>
        <tr>
        	<th rowspan="3">
	            <label class="jpn-txt-version ">住民票住所</label>
	            <label class="eng-txt-version">Resident Address</label>
			</th>
            <th>
	            <label class="jpn-txt-version ">郵便番号</label>
	            <label class="eng-txt-version">Postal Code</label>
			</th>
            <td>
            	<label class="font-imart">${f:h(savedFormData.f_change_address_resident_postal_code)}</label>
                <div class="error_message"></div>
            </td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <th>
	            <label class="jpn-txt-version ">住所</label>
	            <label class="eng-txt-version">Address</label>
            </th>
            <td colspan="3">
            	<label class="font-imart">${f:h(savedFormData.f_change_address_resident_address)}</label>
            	<div class="error_message"></div>
            </td>
        </tr>
        <tr>
            <th>
	            <label class="jpn-txt-version">電話番号</label>
	            <label class="eng-txt-version">Phone Number</label>
            </th>
            <td>
            	<label class="font-imart">${f:h(savedFormData.f_change_address_resident_phone_number)}</label>
            	<div class="error_message"></div>
            </td>
            <td colspan="2"></td>
        </tr>
    </tbody>
</table>

<header class="imui-chapter-title section_change_spouse">
    <h2>配偶者情報<i class="eng-txt-version"><br>Spouse Information</i></h2>
    <label class=" jpn-txt-version msg" >
    	※扶養追加の場合には住民票（マイナンバー記載あり）を添付してください<br>
      		※扶養から外す場合で資格確認書(黄色のカード)を発行されている方については、人事部まで返却をお願いします。<br>
	</label>
	<label class=" eng-txt-version msg">
		*If adding a dependent, please attach a resident certificate (including My Number).<br>
		*If you are removing someone from your dependent list and have been issued a qualification confirmation certificate (yellow card), 
		please return it to the Human Resources Department.
	</label>
</header>

<table class="imui-form section_change_spouse" style="table-layout:fixed; width:100%;">
	<colgroup>
		<col style="width:300px;">
		<col style="">
		<col style="width:300px;">  
		<col>
	</colgroup>

    <tbody>
        <tr>
            <th>
	            <label class="jpn-txt-version ">変更日</label>
	            <label class="eng-txt-version">Change Date</label>
            </th>
            <td>
            	<label class="font-imart">${f:h(savedFormData.f_change_spouse_date)}</label>
                <div class="error_message"></div>
            </td>
            <td colspan="2"></td>
        </tr>

        <tr>
            <th>
            	<label class="jpn-txt-version ">配偶者名（漢字）</label>
            	<label class="eng-txt-version">Spouse Name in Kanji</label>
            </th>
            <td colspan="3">
            	<label class="font-imart">${f:h(savedFormData.f_change_spouse_kanji_last_name)} ${f:h(savedFormData.f_change_spouse_kanji_first_name)}</label>
				<div class="error_message"></div>
            </td>
        </tr>

        <tr>
            <th>
            	<label class="jpn-txt-version ">配偶者名（フリガナ）</label>
            	<label class="eng-txt-version">Spouse Name Katakana</label>
            </th>
            <td colspan="3">
            	<label class="font-imart">${f:h(savedFormData.f_change_spouse_kana_last_name)} ${f:h(savedFormData.f_change_spouse_kana_first_name)}</label>
                <div class="error_message"></div>
            </td>
        </tr>
        <tr>
	        <th>
	        	<label class="jpn-txt-version ">配偶者区分</label>
	        	<label class="eng-txt-version">Spouse Status</label>
	        </th>
            <td colspan="3">
                <input type="radio" name="f_change_spouse_type" value="become_spouse" ${f:h(savedFormData.f_change_spouse_type_become_spouse)} tabindex="-1"> 
	                <label class="jpn-txt-version">配偶者となる</label>
	                <label class="eng-txt-version">(Become a Spouse)</label>
                <br>
                <input type="radio" name="f_change_spouse_type" value="cease_to_be_spouse" ${f:h(savedFormData.f_change_spouse_type_cease_to_be_spouse)} tabindex="-1">
                	<label class="jpn-txt-version">配偶者でなくなる</label>
	                <label class="eng-txt-version">(Cease to be a Spouse)</label>
	            
	            <div class="error_message"></div>
            </td>
		</tr>
		<tr>
            <th><label class="jpn-txt-version ">扶養区分</label><label class="eng-txt-version">Dependent Status</label></th>
            <td colspan="3">
                <input type="radio" name="f_change_spouse_dependent" value="add_to_dependent" ${f:h(savedFormData.f_change_spouse_dependent_add_to_dependent)} tabindex="-1">
                	<label class="jpn-txt-version">扶養に入れる</label>
	                <label class="eng-txt-version">(Add to Dependents)</label>
	            <br>
                <input type="radio" name="f_change_spouse_dependent" value="not_dependent" ${f:h(savedFormData.f_change_spouse_dependent_not_dependent)} tabindex="-1">
               		<label class="jpn-txt-version">扶養に入れない／扶養から外す</label>
	                <label class="eng-txt-version">(Not a dependent / Remove dependent status)</label>
	                
	           	<div class="error_message"></div>
            </td>
		</tr>
    </tbody>
</table>


<header class="imui-chapter-title section_change_dependent">
    <h2>扶養情報<i class="eng-txt-version"><br>Dependent Information</i></h2>
    <label class=" jpn-txt-version msg" >
    	※扶養追加の場合には住民票（マイナンバー記載あり）を添付してください。<br>
      		※扶養から外す場合で資格確認書(黄色のカード)を発行されている方については、人事部まで返却をお願いします。<br>
	</label>
	<label class=" eng-txt-version msg">
		*When adding a dependent, please attach a resident registration certificate (with your My Number included).<br>
		*If you are removing someone from your dependent list and have been issued a qualification confirmation certificate (yellow card), 
		please return it to the Human Resources Department.
	</label>
</header>

<div class="imui-form-container-full scrollbox section_change_dependent">
   <table class="imui-form tbl_dependent" id="dependent_part" style="table-layout:fixed; width:100%;">
       <thead>
           <tr>
               <th style="text-align:center; width:60px;">
               	<label class="jpn-txt-version">番号</label><label class="eng-txt-version">No</label>
               </th>
               <th style="text-align:center; width:140px;">
               	<label class="jpn-txt-version ">変更日</label><label class="eng-txt-version">Change Date</label>
               </th>
               <th style="text-align:center; width:170px;">
               	<label class="jpn-txt-version ">氏名（漢字）</label><label class="eng-txt-version">Name (Kanji)</label>
               </th>
               <th style="text-align:center; width:190px;">
               	<label class="jpn-txt-version ">氏名（フリガナ）</label><label class="eng-txt-version">Name (Katakana)</label>
               </th>
               <th style="text-align:center; width:130px;">
               	<label class="jpn-txt-version ">申請者からみた続柄</label><label class="eng-txt-version">Relationship</label>
               </th>
               <th style="text-align:center; width:70px;">
               	<label class="jpn-txt-version ">性別</label><label class="eng-txt-version">Gender</label>
               </th>
               <th style="text-align:center; width:140px;">
               	<label class="jpn-txt-version ">生年月日</label><label class="eng-txt-version">Date of Birth</label>
               </th>
               <th style="text-align:center; width:150px;">
               	<label class="jpn-txt-version ">扶養区分</label><label class="eng-txt-version">Dependent Status</label>
               </th>
           </tr>
       </thead>
       <tbody class="detail_dependent">
       	<c:forEach items="${savedFormData.f_list_detail_dependent}" var="detail_dependent">
       		<tr class="dependent_rows">
                <td style="text-align:center;">
                	<label class="font-imart">${f:h(detail_dependent.sequence_no)}</label>
                	<div class='error_message'></div>
                </td>
                <td style="text-align:center;">
                	<label class="font-imart">${f:h(detail_dependent.change_date)}</label>
               	</td>
               	<td style="text-align:center;">
               		<label class="font-imart">${f:h(detail_dependent.dependent_kanji_last_name)} ${detail_dependent.dependent_kanji_first_name}</label>
                	<div class='error_message'></div>
               	</td>
               	<td style="text-align:center;">
               		<label class="font-imart">${f:h(detail_dependent.dependent_kana_last_name)} ${detail_dependent.dependent_kana_first_name}</label>
                	<div class='error_message'></div>
               	</td>
               	<td style="text-align:center;">
               		<label class="font-imart">${f:h(detail_dependent.relationship)}</label>
               		<div class='error_message'></div>
               	</td>
               	<td style="text-align:center;">
               		<label class="font-imart">${f:h(detail_dependent.gender_fix)}</label>
                	<div class='error_message'></div>
                </td>
               	<td style="text-align:center;">
               		<label class="font-imart">${f:h(detail_dependent.birth_date)}</label>
                </td>
                <td style="text-align:left;">
                    <table style="margin:0px;">
						<tr>
							<td style="border:none; padding:0px;">
								<input type="radio" name="f_change_dependent_status_${detail_dependent.sequence_no}" class="f_change_dependent_status" value="add" ${detail_dependent.dependent_status_add} tabindex="-1">
							</td>
							<td style="border:none; padding:0px;">
								<label class="jpn-txt-version">扶養に入れる</label>
					   			<label class="eng-txt-version">(Add Dependents)</label>
							</td>
						</tr>
						<tr>
							<td style="border:none; padding:0px;">
								<input type="radio" name="f_change_dependent_status_${detail_dependent.sequence_no}" class="f_change_dependent_status" value="remove" ${detail_dependent.dependent_status_remove} tabindex="-1">
							</td>
							<td style="border:none; padding:0px;">
								<label class="jpn-txt-version">扶養から外す</label>
					    		<label class="eng-txt-version">(Remove Dependents)</label>
							</td>
						</tr>
					</table>
					<div class='error_message'></div>
                </td>
			</tr>   
       	</c:forEach>
       </tbody>
   </table>
</div>

<!-- 緊急連絡先情報 -->
<header class="imui-chapter-title section_change_emergency_contact">
    <h2>緊急連絡先情報<i class="eng-txt-version"><br>Emergency Contact Information</i></h2>
</header>

<table class="imui-form section_change_emergency_contact" style="table-layout:fixed; width:100%;">
	<colgroup>
		<col style="width:125px;">
		<col style="width:175px;">
		<col>
		<col style="width:300px;">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th rowspan="10">
				<label class="jpn-txt-version ">第一緊急連絡先</label>
				<label class="eng-txt-version">Primary Emergency Contact</label>
			</th>
			<th>
				<label class="jpn-txt-version ">変更日</label>
				<label class="eng-txt-version">Change Date</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_date)}</label>
				<div class="error_message"></div>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version ">連絡先氏名（漢字）</label>
				<label class="eng-txt-version">Name in Kanji</label>
			</th>
			<td colspan="3">
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_kanji_last_name)} ${f:h(savedFormData.f_change_emergency_contact_kanji_first_name)}</label>
				<div class="error_message"></div>
			</td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version ">連絡先氏名（フリガナ）</label>
				<label class="eng-txt-version">Name Katakana</label>
			</th>
			<td colspan="3">
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_kana_last_name)} ${f:h(savedFormData.f_change_emergency_contact_kana_first_name)}</label>
				<div class="error_message"></div>
			</td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version ">申請者からみた続柄</label>
				<label class="eng-txt-version">Relationship</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_relationship)}</label>
				<div class="error_message"></div>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version ">緊急連絡先区分</label>
				<label class="eng-txt-version">Emergency contact category</label>
			</th>
			<td colspan="3">
				<input type="radio" name="f_change_emergency_contact_type" value="domestic" class="emergency-contact-type" ${f:h(savedFormData.f_change_emergency_contact_type_domestic)} tabindex="-1">
				<label class="jpn-txt-version">国内</label>
				<label class="eng-txt-version">(Domestic)</label>
				<br>
				<input type="radio" name="f_change_emergency_contact_type" value="overseas" class="emergency-contact-type" ${f:h(savedFormData.f_change_emergency_contact_type_overseas)} tabindex="-1">
				<label class="jpn-txt-version">海外</label>
				<label class="eng-txt-version">(Overseas)</label>
				<div class="error_message"></div>
			</td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version ">電話番号</label>
				<label class="eng-txt-version">Phone Number</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_phone_number)}</label>
				<div class="error_message"></div>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr class="emergency_contact_domestic">
			<th>
				<label class="jpn-txt-version ">郵便番号</label>
				<label class="eng-txt-version">Postal Code</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_domestic_postal_code)}</label>
				<div class="error_message"></div>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr class="emergency_contact_overseas">
			<th>
				<label class="jpn-txt-version ">郵便番号</label>
				<label class="eng-txt-version">Postal Code</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_overseas_postal_code)}</label>
				<div class="error_message"></div>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr class="emergency_contact_overseas">
			<th>
				<label class="jpn-txt-version ">国</label>
				<label class="eng-txt-version">Country</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_overseas_country)}</label>
				<div class="error_message"></div>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version ">住所</label>
				<label class="eng-txt-version">Address</label>
			</th>
			<td colspan="3">
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_address)}</label>
				<div class="error_message"></div>
			</td>
		</tr>
	</tbody>
</table>
<table class="imui-form section_change_emergency_contact" style="table-layout:fixed; width:100%;">
	<colgroup>
		<col style="width:125px;">
		<col style="width:175px;">
		<col>
		<col style="width:300px;">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th rowspan="10">
				<label class="jpn-txt-version">第二緊急連絡先</label>
				<label class="eng-txt-version">Secondary Emergency Contact</label>
			</th>
			<th>
				<label class="jpn-txt-version">変更日</label>
				<label class="eng-txt-version">Change Date</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_2_date)}</label>
				<div class="error_message"></div>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version">連絡先氏名（漢字）</label>
				<label class="eng-txt-version">Name in Kanji</label>
			</th>
			<td colspan="3">
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_2_kanji_last_name)} ${f:h(savedFormData.f_change_emergency_contact_2_kanji_first_name)}</label>
				<div class="error_message"></div>
			</td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version">連絡先氏名（フリガナ）</label>
				<label class="eng-txt-version">Name Katakana</label>
			</th>
			<td colspan="3">
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_2_kana_last_name)} ${f:h(savedFormData.f_change_emergency_contact_2_kana_first_name)}</label>
				<div class="error_message"></div>
			</td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version">申請者からみた続柄</label>
				<label class="eng-txt-version">Relationship</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_2_relationship)}</label>
				<div class="error_message"></div>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version">緊急連絡先区分</label>
				<label class="eng-txt-version">Emergency contact category</label>
			</th>
			<td colspan="3">
				<input type="radio" name="f_change_emergency_contact_2_type" value="domestic" class="emergency-contact-type" ${f:h(savedFormData.f_change_emergency_contact_2_type_domestic)} tabindex="-1">
				<label class="jpn-txt-version">国内</label>
				<label class="eng-txt-version">(Domestic)</label>
				<br>
				<input type="radio" name="f_change_emergency_contact_2_type" value="overseas" class="emergency-contact-type" ${f:h(savedFormData.f_change_emergency_contact_2_type_overseas)} tabindex="-1">
				<label class="jpn-txt-version">海外</label>
				<label class="eng-txt-version">(Overseas)</label>
			</td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version">電話番号</label>
				<label class="eng-txt-version">Phone Number</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_2_phone_number)}</label>
				<div class="error_message"></div>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr class="emergency_contact_2_domestic">
			<th>
				<label class="jpn-txt-version">郵便番号</label>
				<label class="eng-txt-version">Postal Code</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_2_domestic_postal_code)}</label>
				<div class="error_message"></div>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr class="emergency_contact_2_overseas">
			<th>
				<label class="jpn-txt-version">郵便番号</label>
				<label class="eng-txt-version">Postal Code</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_2_overseas_postal_code)}</label>
				<div class="error_message"></div>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr class="emergency_contact_2_overseas">
			<th>
				<label class="jpn-txt-version">国</label>
				<label class="eng-txt-version">Country</label>
			</th>
			<td>
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_2_overseas_country)}</label>
				<div class="error_message"></div>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<th>
				<label class="jpn-txt-version">住所</label>
				<label class="eng-txt-version">Address</label>
			</th>
			<td colspan="3">
				<label class="font-imart">${f:h(savedFormData.f_change_emergency_contact_2_address)}</label>
				<div class="error_message"></div>
			</td>
		</tr>
	</tbody>
</table>

<header class="imui-chapter-title" id="name_note_header">
    <h2>備考<i class="eng-txt-version"><br>Remarks</i></h2>
</header>

<table class="imui-form" id="name_note_part" style="table-layout:fixed; width:100%;">
	<colgroup>
		<col style="width:300px;">  
		<col style="">
	</colgroup>
	
	<tbody>
		<tr>
			<th>
				<label class="jpn-txt-version">補足事項</label>
				<label class="eng-txt-version">Additional information</label>
			</th>
			<td style="padding-right:20px;">
				<label class="font-imart">${f:h(savedFormData.f_remark)}</label>
			</td>
		</tr>
	</tbody>
</table>


<header class="imui-chapter-title">
	<h2>添付ファイル<br><i class="eng-txt-version">Attached Files</i></h2>
</header>

<table class="imui-form download_detail_resident_registration_without_mynumber" style="width:100%">
	<colgroup> 
		<col style="width:300px;"> 
		<col style="">
	<tbody>
		<tr>
			<th>
			    <label class="jpn-txt-version">住民票（マイナンバー記載なし）</label>
			    <label class="eng-txt-version">Resident Registration (without My Number)</label>
			</th>
			<td style="padding:0px;">
			
				<table class="imui-form download_detail_resident_registration_without_mynumber" style="width:100%; margin-top:0px; margin-bottom:0px;">
					<colgroup>
						<col style="">
						<col style="width:100px;">  
						<col style="width:150px;">
					</colgroup>
					<c:forEach items="${savedFormData.f_info_file_resident_registration_without_mynumber}" var="item_file"  varStatus="status">
						<tr>
							<td style="vertical-align: middle;">
								<label class="">${item_file.file_name}</label>
							</td>
							<td style="vertical-align: middle;">
								<label class="">${item_file.file_size_convert}</label>
							</td>
							<td style="text-align:center; vertical-align: middle; white-space: nowrap;">
								<button type="button" class="download-btn" name="${item_file.file_real_name}" 
									onclick="window.open('personal_info_change/download/${item_file.file_id}?token=${f:h(savedFormData.f_download_token)}&system_matter_id=${f:h(workflowRequestForm.imwSystemMatterId)}', '_blank');">
								    <i class="fa-solid fa-file-arrow-down"></i> ダウンロード
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			
			</td>
		</tr>
	</tbody>
</table>

<table class="imui-form download_detail_resident_registration_with_mynumber"  style="width:100%">
	<colgroup>
		<col style="width:300px;">
		<col style="">                   
	</colgroup>
	<tbody>
		<tr>
			<th>
			    <label class="jpn-txt-version">住民票（マイナンバー記載あり）</label>
			    <label class="eng-txt-version">Resident Registration (with My Number)</label>
			</th>
			<td style="padding:0px;">
				
				<table class="imui-form download_detail_resident_registration_with_mynumber"  style="width:100%; margin-top:0px; margin-bottom:0px;">
					<colgroup>
						<col style=""> 
						<col style="width:100px;">  
						<col style="width:150px;">                       
					</colgroup>
					<tbody>
						<c:forEach items="${savedFormData.f_info_file_resident_registration_with_mynumber}" var="item_file" varStatus="status">
							<tr>
								<td style="vertical-align: middle;">
									<label class="">${item_file.file_name}</label>
								</td>
								<td style="vertical-align: middle;">
									<label class="">${item_file.file_size_convert}</label>
								</td>
								<td style="text-align:center; vertical-align: middle;">
									<button type="button" class="download-btn" name="${item_file.file_real_name}" 
										onclick="window.open('personal_info_change/download/${item_file.file_id}?token=${f:h(savedFormData.f_download_token)}&system_matter_id=${f:h(workflowRequestForm.imwSystemMatterId)}', '_blank');">
									    <i class="fa-solid fa-file-arrow-down"></i> ダウンロード
									</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</td>
		</tr>
		
	</tbody>
</table>
