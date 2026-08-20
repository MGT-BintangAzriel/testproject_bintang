<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="workflow" uri="http://www.intra-mart.co.jp/taglib/imw/workflow"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="im" uri="http://www.intra-mart.co.jp/taglib/im-tenant"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<imui:head>
    <title>${f:h(savedFormData.f_workflow_name)}</title>
    <workflow:workflowOpenPageCsjs />

    <link href="ui/css/select2.min.css" rel="stylesheet"/>
    <script src="ui/js/select2.min.js" type="text/javascript"></script>
    <script src="ui/js/jquery.validate.js" type="text/javascript"></script>
    
    <!-- Callback Upload -->
    <script type="text/javascript">
	    function callbackSuccess_resident_registration_without_mynumber(e, data) {
			var file = data.files[0];
			var fileName = file.name;
			var fileSize = file.size;
			var fileExtension = file.type;
			
			//console.log(file);
			//console.log
			
			var receiveFile = data.result[0];
			var receiveFileName = receiveFile.name;
			var receivePhysicalFileName = receiveFile.physicalName;
			var receiveFileSize = receiveFile.size;
			var fileType = receiveFile.type;
			
			console.log(receiveFile);
			
			$(".file_upload_resident_registration_without_mynumber").prepend("<div class='"+ receivePhysicalFileName +"'>"
			+ "<input type='hidden' value='0' class='f_upload_file_id' name='f_upload_file_id'> "
			+ "<input type='hidden' value=\""+ receiveFileName +"\" class='f_upload_file_name' name='f_upload_file_name' > "
			+ "<input type='hidden' value=\""+ receivePhysicalFileName +"\" class='f_upload_file_real_name' name='f_upload_file_real_name'> "
			+ "<input type='hidden' value=\""+ fileSize +"\" class='f_upload_file_size' name='f_upload_file_size'> "
			+ "<input type='hidden' value=\""+ fileExtension +"\" class='f_upload_file_extension' name='f_upload_file_extension'> "
			+ "<input type='hidden' value='resident_registration_without_mynumber' class='f_upload_file_type' name='f_upload_file_type'> "
			+ "</div>");
			
			validateWorkflowForm();
		}   
		
		function callbackRemove_resident_registration_without_mynumber(e, data) {
			var file = data.response[0];
			var fileName = file.name;
			
			$("." + fileName).remove();
		}
		
		function callbackError_resident_registration_without_mynumber(e, data) {
			var file = data.files[0];
			var fileName = file.name;
			var fileSize = file.size;
			var fileExtension = file.type;
		}
		
		///
		
		function callbackSuccess_resident_registration_with_mynumber(e, data) {
			var file = data.files[0];
			var fileName = file.name;
			var fileSize = file.size;
			var fileExtension = file.type;
			
			//console.log(file);
			//console.log
			
			var receiveFile = data.result[0];
			var receiveFileName = receiveFile.name;
			var receivePhysicalFileName = receiveFile.physicalName;
			var receiveFileSize = receiveFile.size;
			var fileType = receiveFile.type;
			
			console.log(receiveFile);
			
			$(".file_upload_resident_registration_with_mynumber").prepend("<div class='"+ receivePhysicalFileName +"'>"
			+ "<input type='hidden' value='0' class='f_upload_file_id' name='f_upload_file_id'> "
			+ "<input type='hidden' value=\""+ receiveFileName +"\" class='f_upload_file_name' name='f_upload_file_name' > "
			+ "<input type='hidden' value=\""+ receivePhysicalFileName +"\" class='f_upload_file_real_name' name='f_upload_file_real_name'> "
			+ "<input type='hidden' value=\""+ fileSize +"\" class='f_upload_file_size' name='f_upload_file_size'> "
			+ "<input type='hidden' value=\""+ fileExtension +"\" class='f_upload_file_extension' name='f_upload_file_extension'> "
			+ "<input type='hidden' value='resident_registration_with_mynumber' class='f_upload_file_type' name='f_upload_file_type'> "
			+ "</div>");
			
			validateWorkflowForm();
		}   
		
		function callbackRemove_resident_registration_with_mynumber(e, data) {
			var file = data.response[0];
			var fileName = file.name;
			
			$("." + fileName).remove();
		}
		
		function callbackError_resident_registration_with_mynumber(e, data) {
			var file = data.files[0];
			var fileName = file.name;
			var fileSize = file.size;
			var fileExtension = file.type;
		}
		
    </script>
    
    
    <!--  Validation JavaScript -->
    <script type="text/javascript">
    
		var rules = {
				f_category : {
								required : true, 
							},
				f_change_reason : {
								required : true, 
								noSpace : true,
							},
				
		};
		
		var messages = {}
		
		function validateWorkflowForm() {
			
			var groups = {
		    		change_name_kanji_name_group: "f_change_name_kanji_last_name f_change_name_kanji_first_name",
		    		change_name_kana_name_group: "f_change_name_kana_last_name f_change_name_kana_first_name",
		    		
		    		change_spouse_kanji_name_group: "f_change_spouse_kanji_last_name f_change_spouse_kanji_first_name",
		    		change_spouse_kana_name_group: "f_change_spouse_kana_last_name f_change_spouse_kana_first_name",
		    		
		    		change_emergency_contact_kanji_name_group: "f_change_emergency_contact_kanji_last_name f_change_emergency_contact_kanji_first_name",
		    		change_emergency_contact_kana_name_group: "f_change_emergency_contact_kana_last_name f_change_emergency_contact_kana_first_name",
		    		
		    		change_emergency_contact_2_kanji_name_group: "f_change_emergency_contact_2_kanji_last_name f_change_emergency_contact_2_kanji_first_name",
		    		change_emergency_contact_2_kana_name_group: "f_change_emergency_contact_2_kana_last_name f_change_emergency_contact_2_kana_first_name",
		    };
		
			$(".detail_dependent .dependent_rows").each(function(index) {
			    var newSerial = index + 1;
			    
			    groups["change_dependent_" + newSerial + "_kanji_name_group"] = "f_change_dependent_kanji_last_name_" + newSerial + " f_change_dependent_kanji_first_name_" + newSerial;
			    groups["change_dependent_" + newSerial + "_kana_name_group"] = "f_change_dependent_kana_last_name_" + newSerial + " f_change_dependent_kana_first_name_" + newSerial;
			});
            
			//// Add Validator for Multiple Rows
        	$.validator.addClassRules("f_change_dependent_change_date", {
        	    required: true,
        	    noSpace: true,
        	    noFullWidth : true,
	    		dateFormat : true,
	    		dateValid : true,
        	});
			
        	$.validator.addClassRules("f_change_dependent_kanji_last_name", {
        	    required: true,
        	    noSpace: true,
        	});
        	
        	// 
        	$.validator.addClassRules("f_change_dependent_kanji_first_name", {
        	    required: true,
        	    noSpace: true,
        	});
        	
        	// 
        	$.validator.addClassRules("f_change_dependent_kana_last_name", {
        	    required: true,
        	    noSpace: true,
        	    katakanaOnly: true,
        	});
        	
        	// 
        	$.validator.addClassRules("f_change_dependent_kana_first_name", {
        	    required: true,
        	    noSpace: true,
        	    katakanaOnly: true,
        	});
        	
        	// 
        	$.validator.addClassRules("f_change_dependent_relationship", {
        	    required: true,
        	    noSpace: true,
        	});
        	
        	// 
        	$.validator.addClassRules("f_change_dependent_gender", {
        	    required: true,
        	});
        	
        	// 
        	$.validator.addClassRules("f_change_dependent_birth_date", {
        	    required: true,
        	    noSpace: true,
        	    noFullWidth : true,
	    		dateFormat : true,
	    		dateValid : true,
        	});
        	
        	// 
        	$.validator.addClassRules("f_change_dependent_status", {
        	    required: true,
        	});
			
		    // 1. Clear previous error labels
		    $(".error_message").empty();
			
		    $(".error_message_resident_registration_without_mynumber").empty();
			$(".error_message_resident_registration_with_mynumber").empty();
			
		    // 2. Remove previous imui-validation-error classes from Select2 containers
		    $('.select2-selection').removeClass('imui-validation-error');
		    
		    var validator = $('#workflowOpenPageForm').validate({
		        // Pass the standard rules and messages generated by intra-mart
		        groups: groups,
		        rules: rules,
		        messages: messages,
		        
		        // Triggers validation immediately on every keypress/deletion
		        onkeyup: function(element, event) {
		            // Skip validation on tab/navigation keys to protect UX
		            if (event.which === 9 && this.elementValue(element) === "") return;
		            $(element).valid(); 
		        },
		        
		        // Triggers validation the moment the user clicks away from a field
		        onfocusout: function(element) {
		            $(element).valid();
		        },
	
		        // Triggers validation immediately on checkbox clicks and dropdown updates
		        onclick: function(element) {
		            $(element).valid();
		        },
	
		        // HIGHLIGHT: Runs when a field FAILS validation
		        highlight: function(element, errorClass, validClass) {
		            var $element = $(element);
		            
		            if ($element.attr("type") === "checkbox") {
		                $('input[name="' + $element.attr('name') + '"]').addClass('imui-validation-error');
		                
		            } else if ($element.attr("type") === "radio") {
		                $('input[name="' + $element.attr('name') + '"]').addClass('imui-validation-error');
		                
		            } else if ($element.hasClass('select2') || $element.hasClass('select2-hidden-accessible')) {
		                $element.parents('td').find('.select2-selection').addClass('imui-validation-error');
		                
		            } else {
		                $element.addClass('imui-validation-error');
		                
		            }
		        },
	
		        // UNHIGHLIGHT: Runs when a field PASSES validation
		        unhighlight: function(element, errorClass, validClass) {
		            var $element = $(element);
		            
		            console.log($element);
		            
		            if ($element.attr("type") === "checkbox") {
		                $('input[name="' + $element.attr('name') + '"]').removeClass('imui-validation-error');
		                
		            } else if ($element.attr("type") === "radio") {
		                $('input[name="' + $element.attr('name') + '"]').removeClass('imui-validation-error');
		                
		            } else if ($element.hasClass('select2') || $element.hasClass('select2-hidden-accessible')) {
		                $element.parents('td').find('.select2-selection').removeClass('imui-validation-error');
		                
		            } else {
		                $element.removeClass('imui-validation-error');
		                
		            }
		        },
	
		        // ERROR PLACEMENT: Handles WHERE the text message goes
		        errorPlacement: function (error, element) {
		            var $element = $(element);
		            var error_message = error.get(0);
		            
		            console.log($element);
		            
		            if ($element.hasClass('resident_registration_without_mynumber_anchor')) {
		                $(".error_message_resident_registration_without_mynumber").html(error_message);
		                
		            } else if ($element.hasClass('resident_registration_with_mynumber_anchor')) {
		                $(".error_message_resident_registration_with_mynumber").html(error_message);
		                
		            } else {
		                // Clears any older iterations to prevent double text appending
		                $element.parents('td').find('.error_message').html(error_message);
		            }
		        }
		    });
		    
		    return validator.form();
		}
    </script>
    
    <!--  Common JavaScript -->
    <script type="text/javascript">
        $(function () {
        	$(".eng-txt-version").hide();
        	
        	$('.select2').select2();
        	
        	$(".imuiCalendar").imuiCalendar({
                changeMonth: true,
                changeYear: true
            });
        	
        	// Add the custom validation method
			$.validator.addMethod("noSpace", function(value, element) {
			    return this.optional(element) || value.trim().length > 0;
			    
			});
			
			// ensureFieldExists, Use to check Uploaded File
			$.validator.addMethod("ensureFieldExists", function(value, element, param) {
			    var generatedCount = $(param).find(".f_upload_file_id").length; 
			    
			    console.log("check file upload = " + param);
			    console.log("check file upload = " + generatedCount);
			    
			    return generatedCount > 0;
			});
			
			// specialNumberOnly, use regex to check
			$.validator.addMethod("specialNumberOnly", function(value, element) {
			    return this.optional(element) || /^\d+$/.test(value);
			});
			
			// dateFormat
			$.validator.addMethod("dateFormat", function(value, element) {
			    if (this.optional(element)) {
			        return true;
			    }
				
			    // Check the basic format (yyyy/MM/dd) using Regex, Expects exactly 4 digits, a slash, 2 digits, a slash, and 2 digits
			    var regex = /^\d{4}\/\d{2}\/\d{2}$/;
			    if (!regex.test(value)) {
			        return false;
			    }
			    
			    return true;
			});
			
			// dateValid
			$.validator.addMethod("dateValid", function(value, element) {
				if (this.optional(element)) {
			        return true;
			    }
				
			    // Check if it's a real calendar date (e.g., catching Feb 30th)
			    var parts = value.split("/");
			    var year  = parseInt(parts[0], 10);
			    var month = parseInt(parts[1], 10) - 1; // JS months are 0-11
			    var day   = parseInt(parts[2], 10);

			    var date = new Date(year, month, day);

			    return date.getFullYear() === year && date.getMonth() === month && date.getDate() === day;
			});
			
			// startDateLessThan
			$.validator.addMethod("startDateLessThan", function(value, element, params) {
				if (this.optional(element)) {
			        return true;
			    }

			    var endDateValue = $(params).val();
			    if (!endDateValue) return true; // If end date isn't filled yet, pass validation

			    var startDate = new Date(value.replace(/\//g, '-'));
			    var endDate = new Date(endDateValue.replace(/\//g, '-'));

			    return startDate <= endDate;
			});

			// endDateGreaterThan
			$.validator.addMethod("endDateGreaterThan", function(value, element, params) {
				if (this.optional(element)) {
			        return true;
			    }

			    var startDateValue = $(params).val();
			    if (!startDateValue) return true; // If start date isn't filled yet, pass validation

			    var startDate = new Date(startDateValue.replace(/\//g, '-'));
			    var endDate = new Date(value.replace(/\//g, '-'));

			    return endDate >= startDate;
			});
			
			// noFullWidth
			$.validator.addMethod("noFullWidth", function(value, element) {
			    if (this.optional(element)) {
			        return true;
			    }
			    
			    // Regex ranges covering Japanese full-width character sets, EXCLUDING half-width Katakana:
			    // \uFF01-\uFF60 : Full-width ASCII variants (Numbers, Alphabet, Symbols - excludes space \uFF00)
			    // \uFFE0-\uFFEE : Full-width currency/mapping symbols (e.g., Yen sign ￥)
			    // \u3040-\u309F : Hiragana
			    // \u30A0-\u30FF : Full-width Katakana
			    // \u4E00-\u9FAF : Kanji
			    // \u3000-\u303F : Japanese punctuation/ideographic space
			    var fullWidthRegex = /[\uFF01-\uFF60\uFFE0-\uFFEE\u3040-\u309F\u30A0-\u30FF\u4E00-\u9FAF\u3000-\u303F\uFF61-\uFF9F]/;
			    
			    // If it tests true for containing full-width characters, we invert it to return false (error)
			    return !fullWidthRegex.test(value);
			});
			
			// Handle Katakana
			$.validator.addMethod("katakanaOnly", function(value, element) {
			    if (this.optional(element)) {
			        return true;
			    }
			
			    var katakanaRegex = /^[\u30A0-\u30FF\u3000\s]+$/;
			    return katakanaRegex.test(value);
			
			});
			
			// Add the custom method 'noHyphens'
			$.validator.addMethod("noHyphen", function(value, element) {
			    // Returns false if the value contains a hyphen '-'
			    
			    return this.optional(element) || value.indexOf('-') === -1;
			});
			
			
			$.validator.addClassRules("f_change_dependent_change_date", {
        	    noFullWidth : true,
        	});



			$.validator.addClassRules("f_change_dependent_birth_date", {
        	    noFullWidth : true,
        	});
			
			
        	var message_required = "この項目を入力してください。 ";
    		
    		var message_noFullWidth = "半角数字で入力してください。";
    		var message_specialNumberOnly = "数字のみ入力してください。"
    		
    		var message_dateFormat = "yyyy/MM/dd形式で正しい日付を入力してください。";
    		var message_dateValid = "有効な日付を入力してください。";
    		var message_startDateLessThan = "開始日は終了日より後に設定できません。 ";
    		var message_endDateGreaterThan = "終了日は開始日より前に設定できません。 ";
    		
    		var message_ensureFieldExists = "ファイルをアップロードしてください。";
    		var message_katakanaOnly = "カタカナで入力してください。"; 
    		
    		var message_specialNumberOnly = "数字のみ入力してください。";
    		var message_noHyphen = "ハイフン無しで入力してください。";
    		
    		var message_minlength = "郵便番号は{0}桁で入力してください。";
    		var message_maxlength = "郵便番号は{0}桁で入力してください。";
    		
        	$.validator.messages.required = message_required;
        	$.validator.messages.noSpace = message_required;
        	$.validator.messages.noFullWidth = message_noFullWidth;
        	$.validator.messages.specialNumberOnly = message_specialNumberOnly;
        	
        	$.validator.messages.dateFormat = message_dateFormat;
        	$.validator.messages.dateValid = message_dateValid;
        	$.validator.messages.startDateLessThan = message_startDateLessThan;
        	$.validator.messages.endDateGreaterThan = message_endDateGreaterThan;
        	$.validator.messages.ensureFieldExists = message_ensureFieldExists;
        	$.validator.messages.katakanaOnly = message_katakanaOnly;
        	
    		$.validator.messages.specialNumberOnly = message_specialNumberOnly;
    		$.validator.messages.noHyphen = message_noHyphen;
    		
        	$.validator.messages.minlength = message_minlength;
        	$.validator.messages.maxlength = message_maxlength;
        	
			// Init Validate noFullWidth & Date Validation
			var rules_noFullWidth = {
					f_change_name_date					:{ noFullWidth: true},
					f_change_address_date				:{ noFullWidth: true},
					f_change_spouse_date				:{ noFullWidth: true},
					f_change_emergency_contact_date		:{ noFullWidth: true},
					f_change_emergency_contact_2_date	:{ noFullWidth: true},
					
					f_change_address_new_phone_number			:{ noFullWidth: true },
		        	f_change_address_resident_phone_number		:{ noFullWidth: true },
		        	f_change_emergency_contact_phone_number		:{ noFullWidth: true },
		        	f_change_emergency_contact_2_phone_number	:{ noFullWidth: true },
		        	
		        	f_change_address_new_postal_code					:{ noFullWidth: true },
		        	f_change_address_resident_postal_code				:{ noFullWidth: true },
		        	f_change_emergency_contact_domestic_postal_code		:{ noFullWidth: true },
		        	f_change_emergency_contact_overseas_postal_code		:{ noFullWidth: true },
		        	f_change_emergency_contact_2_domestic_postal_code	:{ noFullWidth: true },
		        	f_change_emergency_contact_2_overseas_postal_code	:{ noFullWidth: true },
		    };
        	
        	var messages_noFullWidth = {};
			
		    // Init imuiValidate noFullWidth
		    imuiValidate('#workflowOpenPageForm', rules_noFullWidth, messages_noFullWidth, function (error, element) {
		        $(element).closest('td').find('.error_message').html(error);
		    });
			
			$('input[name="f_category"]').on('change', function() {
				$(".section_change_name").hide();
				$(".section_change_address").hide();
				$(".section_change_spouse").hide();
				$(".section_change_dependent").hide();
				$(".section_change_emergency_contact").hide();
				
				// delete rules change name
				delete rules.f_change_name_date;
				delete rules.f_change_name_kanji_last_name;
				delete rules.f_change_name_kanji_first_name;
				delete rules.f_change_name_kana_last_name;
				delete rules.f_change_name_kana_first_name;
				delete rules.f_change_name_internal_use;
				
				// delete rules change address
				delete rules.f_change_address_date;
				
				delete rules.f_change_address_register;
				
				delete rules.f_change_address_register_type;
				
				delete rules.f_change_address_new_postal_code;
				delete rules.f_change_address_new_address;
				
				delete rules.f_change_address_resident_postal_code;
				delete rules.f_change_address_resident_address;
				
				// delete rules change spouse
				
				delete rules.f_change_spouse_date;
				
				delete rules.f_change_spouse_kanji_last_name;
				delete rules.f_change_spouse_kanji_first_name;
				
				delete rules.f_change_spouse_kana_last_name;
				delete rules.f_change_spouse_kana_first_name;
				
				delete rules.f_change_spouse_type;
				
				delete rules.f_change_spouse_dependent;
				
				// delete rules change dependent
				
				// delete rules Emergency Contact
				
				delete rules.f_change_emergency_contact_date;
				
				delete rules.f_change_emergency_contact_kanji_last_name;
				delete rules.f_change_emergency_contact_kanji_first_name;
					
				delete rules.f_change_emergency_contact_kana_last_name;
				delete rules.f_change_emergency_contact_kana_first_name;
				
				delete rules.f_change_emergency_contact_relationship;
				
				delete rules.f_change_emergency_contact_type;
				
				delete rules.f_change_emergency_contact_phone_number;
				
				delete rules.f_change_emergency_contact_domestic_postal_code;
				delete rules.f_change_emergency_contact_overseas_postal_code;
				
				delete rules.f_change_emergency_contact_overseas_country;
				delete rules.f_change_emergency_contact_address;
				
				//
				
				delete rules.f_change_emergency_contact_2_date;
				
				delete rules.f_change_emergency_contact_2_kana_last_name;
				delete rules.f_change_emergency_contact_2_kana_first_name;
				
				var checkedValues = $('input[name="f_category"]:checked').map(function() {
					return $(this).val();
				}).get();
				
				console.log(checkedValues);
			    
				// Check Change Name
				if (checkedValues.includes("change_name")) {
				    $(".section_change_name").show();
				    
				    rules.f_change_name_date = {
				    		required: true,
				    		noSpace: true,
				    		noFullWidth : true,
				    		dateFormat : true,
				    		dateValid : true,
				    };
				    
				    rules.f_change_name_kanji_last_name = {
				    		required: true,
				    		noSpace: true,
				    };
				    rules.f_change_name_kanji_first_name = {
				    		required: true,
				    		noSpace: true,
				    };
				    
				    rules.f_change_name_kana_last_name = {
				    		required: true,
				    		noSpace: true,
				    		katakanaOnly: true,
				    };
				    rules.f_change_name_kana_first_name = {
				    		required: true,
				    		noSpace: true,
				    		katakanaOnly: true,
				    };
				    
				    rules.f_change_name_internal_use = {
				    		required: true,
				    };
				    
				}
				// Check Address
				if (checkedValues.includes("change_address")) {
				    $(".section_change_address").show();
				    
				    rules.f_change_address_date = {
				    		required: true,
				    		noSpace: true,
				    		noFullWidth : true,
				    		dateFormat : true,
				    		dateValid : true,
				    };
				    rules.f_change_address_register = {
				    		required: true,
				    };
				    rules.f_change_address_register_type = {
				    		required: true,
				    };
				    
				    // change address new
				    rules.f_change_address_new_postal_code = {
				    		required: true,
				    		noSpace: true,
				    		noFullWidth : true,
				    		noHyphen : true,
				    		minlength : 7,
				    		maxlength : 7,
				    		
				    };
				    rules.f_change_address_new_address = {
				    		required: true,
				    		noSpace: true,
				    };
				    rules.f_change_address_new_phone_number = {
				    		noFullWidth: true,
				    		specialNumberOnly: true,
				    };
				    
				    // change address resident
				    rules.f_change_address_resident_postal_code = {
				    		required: true,
				    		noSpace: true,
				    		noFullWidth : true,
				    		noHyphen : true,
				    		minlength : 7,
				    		maxlength : 7,
				    };
				    rules.f_change_address_resident_address = {
				    		required: true,
				    		noSpace: true,
				    };
				    rules.f_change_address_resident_phone_number = {
				    		noFullWidth: true,
				    		specialNumberOnly: true,
				    };
				}
				
				
				
				
				// Check Spouse
				if (checkedValues.includes("change_spouse")) {
				    $(".section_change_spouse").show();   
				    
				    rules.f_change_spouse_date = {
				    		required: true,
				    		noSpace: true,
				    		noFullWidth : true,
				    		dateFormat : true,
				    		dateValid : true,
				    };
				    
				    rules.f_change_spouse_kanji_last_name = {
				    		required: true,
				    		noSpace: true,
				    };
				    rules.f_change_spouse_kanji_first_name = {
				    		required: true,
				    		noSpace: true,
				    };
				    
				    rules.f_change_spouse_kana_last_name = {
				    		required: true,
				    		noSpace: true,
				    		katakanaOnly: true,
				    };
				    rules.f_change_spouse_kana_first_name = {
				    		required: true,
				    		noSpace: true,
				    		katakanaOnly: true,
				    };
				    
				    rules.f_change_spouse_type = {
				    		required: true,
				    };
				    rules.f_change_spouse_dependent = {
				    		required: true,
				    };
				}
				
				// Check Dependent
				if (checkedValues.includes("change_dependent")) {
				    $(".section_change_dependent").show();   
				}
				
				// Check Emergency Contact
				if (checkedValues.includes("change_emergency_contact")) {
				    $(".section_change_emergency_contact").show();   
				    
				    rules.f_change_emergency_contact_date = {
				    		required: true,
				    		noSpace: true,
				    		noFullWidth : true,
				    		dateFormat : true,
				    		dateValid : true,
				    };
				    //
				    rules.f_change_emergency_contact_kanji_last_name = {
				    		required: true,
				    		noSpace: true,
				    };
				    rules.f_change_emergency_contact_kanji_first_name = {
				    		required: true,
				    		noSpace: true,
				    };
				    //
				    rules.f_change_emergency_contact_kana_last_name = {
				    		required: true,
				    		noSpace: true,
				    		katakanaOnly: true,
				    };
				    rules.f_change_emergency_contact_kana_first_name = {
				    		required: true,
				    		noSpace: true,
				    		katakanaOnly: true,
				    };
				    //
				    rules.f_change_emergency_contact_relationship = {
				    		required: true,
				    		noSpace: true,
				    };
				    rules.f_change_emergency_contact_type = {
				    		required: true,
				    };
				    rules.f_change_emergency_contact_phone_number = {
				    		required: true,
				    		noSpace: true,
				    		noFullWidth: true,
				    		specialNumberOnly: true,
				    };
				    //
				    rules.f_change_emergency_contact_domestic_postal_code = {
				    		required: true,
				    		noSpace: true,
				    		noFullWidth : true,
				    		noHyphen : true,
				    		minlength : 7,
				    		maxlength : 7,
				    };
				    rules.f_change_emergency_contact_overseas_postal_code = {
				    		required: true,
				    		noSpace: true,
				    };
				    rules.f_change_emergency_contact_overseas_country = {
				    		required: true,
				    		noSpace: true,
				    };
				    rules.f_change_emergency_contact_address = {
				    		required: true,
				    		noSpace: true,
				    };
					
				    ///
				    
				    rules.f_change_emergency_contact_2_date = {
				    		noFullWidth : true,
				    		dateFormat : true,
				    		dateValid : true,
				    };
				    
				    rules.f_change_emergency_contact_2_kana_last_name = {
				    		katakanaOnly: true,
				    };
				    rules.f_change_emergency_contact_2_kana_first_name = {
				    		katakanaOnly: true,
				    };
				    rules.f_change_emergency_contact_2_domestic_postal_code = {
				    		noFullWidth : true,
				    		noHyphen : true,
				    		minlength : 7,
				    		maxlength : 7,
				    };
				    rules.f_change_emergency_contact_2_phone_number = {
				    		noFullWidth: true,
				    		specialNumberOnly: true,
				    };
				}
				
				toggleUpload_ResidentRegisterMyNumber();
				
			});
				
			
			$("input[name='f_change_address_register']").on("change", function () {
		        toggleUpload_ResidentRegisterMyNumber();
		    });
		    
			// Bind the event to the document, but filter for dynamic class <<< IMPORTANT
			$(document).on("change", ".f_change_dependent_status", function () {
			    toggleUpload_ResidentRegisterMyNumber();
			});
		    
		    $("input[name='f_change_spouse_dependent']").on("change", function () {
		        toggleUpload_ResidentRegisterMyNumber();
		    });
			
		    $("input[name='f_change_emergency_contact_type']").on("change", function () {
               	var contactType = $("input[name='f_change_emergency_contact_type']:checked").val();
                   $(".emergency_contact_domestic").hide();
               	$(".emergency_contact_overseas").hide();
                   
                   console.log(contactType);
                   
                   if (contactType == "overseas") {
                       	$(".emergency_contact_overseas").show();
                   } else {
						$(".emergency_contact_domestic").show();
                   }
			});
                
			$("input[name='f_change_emergency_contact_2_type']").on("change", function () {
				var contactType = $("input[name='f_change_emergency_contact_2_type']:checked").val();
				$(".emergency_contact_2_domestic").hide();
				$(".emergency_contact_2_overseas").hide();
				
				console.log(contactType);
				
				if (contactType == "overseas") {
					$(".emergency_contact_2_overseas").show();
				} else {
					$(".emergency_contact_2_domestic").show();
				}
			});
                
			$("input[name='f_category']").trigger("change");
            $("input[name='f_change_emergency_contact_type']").trigger("change");
            $("input[name='f_change_emergency_contact_2_type']").trigger("change");
            
            function toggleUpload_ResidentRegisterMyNumber() {
                // 1. Hide both elements upfront (chained for efficiency)
                const $withMyNumber = $(".t_resident_register_with_my_number").hide();
                const $withoutMyNumber = $(".t_resident_register_without_my_number").hide();

                $("#name_attach_header").hide();
                $("#name_attach_part").hide();

                delete rules.resident_registration_with_mynumber_anchor;
                delete rules.resident_registration_without_mynumber_anchor;

                // 2. Get checked categories
                const checkedValues = $('input[name="f_category"]:checked').map(function() {
                    return $(this).val();
                }).get();

                let upload_without_mynumber = false;
                let upload_with_mynumber = false;

                // 3. Evaluate conditional logic
                if (checkedValues.includes("change_spouse")) {
                    const selectValue_dependent = $("input[name='f_change_spouse_dependent']:checked").val();
                    if (selectValue_dependent === "add_to_dependent") {
                        console.log("enter change spouse dependant - add_to_dependent");
                        upload_with_mynumber = true;
                    }
                }

                // Only check dependents if we haven't already flagged "with_mynumber"
                if (!upload_with_mynumber && checkedValues.includes("change_dependent")) {
                    const selectValue_dependent = $(".f_change_dependent_status:checked[value='add']").length > 0;
                    
                    
                    if (selectValue_dependent == true) {
                        console.log("enter change dependant - add");
                        upload_with_mynumber = true;
                    }
                }

                if (checkedValues.includes("change_address")) {
                    const selectValue = $("input[name='f_change_address_register']:checked").val();
                    if (selectValue === "yes") {
                        console.log("enter change f_change_address_register - yes");
                        upload_without_mynumber = true;
                    }
                }

                // Because 'with_mynumber' overrides 'without_mynumber' when both are true,
                if (upload_with_mynumber) {
                    $("#name_attach_header").show();
                    $("#name_attach_part").show();

                    $withMyNumber.show();

                    // Add validation Rule
                    rules.resident_registration_with_mynumber_anchor = {
                        ensureFieldExists: ".file_upload_resident_registration_with_mynumber"
                    };

                } else if (upload_without_mynumber) {
                    $("#name_attach_header").show();
                    $("#name_attach_part").show();

                    $withoutMyNumber.show();

                    // Add validation Rule
                    rules.resident_registration_without_mynumber_anchor = {
                        ensureFieldExists: ".file_upload_resident_registration_without_mynumber"
                    };
                }
            }
            
            $("#addrows_dependent").click(function() {
                var table = $(".tbl_dependent tbody.detail_dependent");
                var rowCount = table.find(".dependent_rows").length + 1;

                var row = "" +
                    " <tr class='dependent_rows'>" +
                    " <td style='text-align:center;'>" +
                    "    	<input type='button' value='削除' class='f_change_dependent_delete'/>" +
                    " </td>" +
                    " <td style='text-align:center;'>" +
                    "    	<input type='text' value='" + rowCount + "' name='f_change_dependent_sequence_no'  " +
                    " 	   	class='imui-text-readonly f_change_dependent_sequence_no' style='width:40px; height:20px; text-align:center;' " +
                    " 		 readonly='readonly'>" +
                    " 	<div class='error_message'></div>" +
                    " </td>" +
                    " <td style='text-align:center;'>" +
                    "    	<input type='text' value='' name='f_change_dependent_change_date_" + rowCount + "' class='imuiCalendar f_change_dependent_change_date' " +
                    "     	placeholder='yyyy/mm/dd' style='width:70%; height:20px;'>" +
                    " 	<div class='error_message'></div>" +
                    " </td>" +
                    " <td style='text-align:center;'>" +
                    " 	<div style='display:flex; align-items:center; gap:6px; justify-content:center; white-space:nowrap;'>" +
                    "        	<span>姓 </span>" +
                    "        	<input type='text' value='' name='f_change_dependent_kanji_last_name_" + rowCount + "' class='f_change_dependent_kanji_last_name' " +
                    "				style='width:40%; height:20px;' >" +
                    "         <span> 名 </span>" +
                    "         <input type='text' value='' name='f_change_dependent_kanji_first_name_" + rowCount + "' class='f_change_dependent_kanji_first_name' " +
                    "            	style='width:40%; height:20px;'>" +
                    "     </div>" +
                    " 	<div class='error_message'></div>" +
                    " </td>" +
                    " <td style='text-align:center;'>" +
                    " 	<div style='display:flex; align-items:center; gap:6px; justify-content:center; white-space:nowrap;'>" +
                    "     	<span>セイ </span>" +
                    "         <input type='text' value='' name='f_change_dependent_kana_last_name_" + rowCount + "' class='f_change_dependent_kana_last_name' " +
                    "           	style='width:40%; height:20px;'>"
                    +
                    "         <span>メイ </span>" +
                    "         <input type='text' value='' name='f_change_dependent_kana_first_name_" + rowCount + "' class='f_change_dependent_kana_first_name' " +
                    "            	style='width:40%; height:20px;'>" +
                    " 	</div>" +
                    " 	<div class='error_message'></div>" +
                    " </td>" +
                    " <td style='text-align:center;'>" +
                    " 	<input type='text' value='' name='f_change_dependent_relationship_" + rowCount + "' class='f_change_dependent_relationship' " +
                    "       		style='width:90%; height:20px;'>" +
                    " 	<div class='error_message'></div>	" +
                    " </td>" +
                    " <td style='text-align:center;'>" +
                    " 	<select name='f_change_dependent_gender_" + rowCount + "' class='select2 f_change_dependent_gender' " +
                    "				style='width:90%;'>"
                    +
                    "     	<option value=''>&nbsp;</option>" +
                    "        	<option value='male'>男</option>" +
                    "        	<option value='female'>女</option>" +
                    "        	<option value='notset'>指定しない</option>" +
                    "    	</select>" +
                    " 	<div class='error_message'></div>	" +
                    " </td>" +
                    " <td style='text-align:center;'>" +
                    " 	<input type='text' value='' name='f_change_dependent_birth_date_" + rowCount + "' class='imuiCalendar f_change_dependent_birth_date' " +
                    "			placeholder='yyyy/mm/dd' style='width:70%; height:20px;'>" +
                    " 	<div class='error_message'></div>	" +
                    " </td>" +
                    " <td style='text-align:left;'>" +
                    " 	<table style='margin:0px;'>" +
                    "			<tr>" +
                    "				<td style='border:none; padding:0px;'>" +
                    "					<input type='radio' name='f_change_dependent_status_" + rowCount + "' value='add' class='f_change_dependent_status' " +
                    "						id='f_change_dependent_status_add_" + rowCount + "' class='f_change_dependent_status'>" +
                    "				</td>" +
                    "				<td style='border:none; padding:0px;'>" +
                    "					<label class='jpn-txt-version' for='f_change_dependent_status_add_" + rowCount + "'>扶養に入れる</label>" +
                    "			   		<label class='eng-txt-version' for='f_change_dependent_status_add_" + rowCount + "'>(Add Dependents)</label>" +
                    "				</td>" +
                    "			</tr>" +
                    "			<tr>" +
                    "				<td style='border:none; padding:0px;'>" +
                    "					<input type='radio' name='f_change_dependent_status_" + rowCount + "' value='remove'  class='f_change_dependent_status' " +
                    " 						id='f_change_dependent_status_remove_" + rowCount + "'  class='f_change_dependent_status'>" +
                    "				</td>" +
                    "				<td style='border:none; padding:0px;'>" +
                    "					<label class='jpn-txt-version' for='f_change_dependent_status_remove_" + rowCount + "'>扶養から外す</label>" +
                    "			   		<label class='eng-txt-version' for='f_change_dependent_status_remove_" + rowCount + "'>(Remove Dependents)</label>" +
                    "				</td>" +
                    "			</tr>" +
                    "		</table>" +
                    " 	<div class='error_message'></div>	" +
                    " </td>" +
                    " </tr>   ";

                table.append(row);

                $(".eng-txt-version").hide();

                $(".select2").select2().on('change', function() {
                    $(this).valid();
                });;
                
                $(".imuiCalendar").imuiCalendar({
                    //changeMonth: true,
                    //changeYear: true
                    //"altField":"#f_change_name_date",
                    "changeYear":true,
                    "nextText":"来月",
                    "format":"yyyy\/MM\/dd",
                    "dayNames":["日曜日","月曜日","火曜日","水曜日","木曜日","金曜日","土曜日"],
                    "dayNamesShort":["日","月","火","水","木","金","土"],
                    "prevText":"先月",
                    "url":"calendar\/tag\/caljson",
                    "currentText":"現在",
                    "changeMonth":true,
                    "calendarId":"JPN_CAL",
                    "firstDay":0,
                    "showMonthAfterYear":true,
                    "closeText":"閉じる",
                    "dayNamesMin":["日","月","火","水","木","金","土"],
                    "monthNamesShort":["1","2","3","4","5","6","7","8","9","10","11","12"],
                    "monthNames":["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"]
                });
            });
            
       		function dependent_onlyAddRowsApply(){
       			var check_pageType = $("#f_imwPageType").val();
       			
       			if(check_pageType == 0){
       				$("#addrows_dependent").trigger("click");	
       			}   	
       		}
       	
       		dependent_onlyAddRowsApply();
            
                
            $(".tbl_dependent").on("click", ".f_change_dependent_delete", function() {
                $(this).closest("tr").remove();
                refreshSequenceNumbers();
            });

            $(".f_delete_file").on('click', function() {
                $(this).closest("tr").remove();
                var div_name = $(this).attr('name');

                $("." + div_name).remove();
                
                checkDownloadTitle();
            });
                
            function OpenSearchPostalCode(element_postal_code, element_address) {
                var keyword = element_postal_code.val().trim();

                //If not 7 digit it return nothing
                if (!/^\d{7}$/.test(keyword)) {
                    //imuiShowErrorMessage('郵便番号は7桁で入力してください。', [], true, 2500, false);

                    return;
                }

                var fulladdress = "";

                console.log("test get postal code");

                $.ajax({
                    url: "personal_info_change/getPostData",
                    type: "GET",
                    data: {
                        postCode: keyword
                    },
                    success: function(response) {
                        console.log(response)
                        var data;
                        try {
                            data = (typeof response === 'string') ? JSON.parse(response) : response;

                        } catch (e) {
                            console.log('JSON parse error:', e);
                            data = [];
                        }

                        if (!Array.isArray(data)) data = [data];

                        if (data.length > 0 && data[0].allAddress) {
                            fulladdress = data[0].allAddress;
                        }

                        element_address.val(fulladdress);
                    },
                    error: function(err) {
                        console.log("Error: ", err);
                    }
                });

            }
                
                
            $('#f_change_address_new_postal_code_button_getpostalcode').on('click', function() {
                var element_postal_code = $("#f_change_address_new_postal_code");
                var element_address = $("#f_change_address_new_address");

                OpenSearchPostalCode(element_postal_code, element_address);
            });

            $('#f_change_address_resident_postal_code_button_getpostalcode').on('click', function() {
                var element_postal_code = $("#f_change_address_resident_postal_code");
                var element_address = $("#f_change_address_resident_address");

                OpenSearchPostalCode(element_postal_code, element_address);
            });

            $('#f_change_emergency_contact_domestic_postal_code_button_getpostalcode').on('click', function() {
                var element_postal_code = $("#f_change_emergency_contact_domestic_postal_code");
                var element_address = $("#f_change_emergency_contact_address");

                OpenSearchPostalCode(element_postal_code, element_address);
            });

            $('#f_change_emergency_contact_2_domestic_postal_code_button_getpostalcode').on('click', function() {
                var element_postal_code = $("#f_change_emergency_contact_2_domestic_postal_code");
                var element_address = $("#f_change_emergency_contact_2_address");

                OpenSearchPostalCode(element_postal_code, element_address);
            });

            
            $("input[name='f_change_address_register_type']").on("change", function () {
				var varSupportType = $("input[name='f_change_address_register_type']:checked").val();
				
				//delete rules.f_change_address_new_postal_code;
				//delete rules.f_change_address_new_address;
				
				//delete rules.f_change_address_resident_postal_code;
				//delete rules.f_change_address_resident_address;
				
				if(varSupportType == "same_as_new_address"){
					var varAddressNewPostalCode = $("#f_change_address_new_postal_code").val();
					var varAddressNewAddress = $("#f_change_address_new_address").val();
					var varAddressNewPhoneNumber = $("#f_change_address_new_phone_number").val();
					
					$("#f_change_address_resident_postal_code").val(varAddressNewPostalCode);
					$("#f_change_address_resident_address").val(varAddressNewAddress);
					$("#f_change_address_resident_phone_number").val(varAddressNewPhoneNumber);
				}
            });

            $("#back").click(function() {
                $("#backForm").submit();
                return false;
            });

            $('#openPage').click(function() {
                imuiResetForm('#workflowOpenPageForm');
                //workflowOpenPage('${f:h(workflowRequestForm.imwPageType)}');

                // Call the shared validation function
                if (!validateWorkflowForm()) {
                    imuiShowErrorMessage('入力エラーが発生しました。', [], true, 2500, false);
                    return false;
                } else {
                	// 1. Define your translation dictionary
                	var categoryMapping = {
                	    'change_name': '氏名',
                	    'change_address': '住所（会社で手配するレオパレスは除く）・電話番号',
                	    'change_spouse': '配偶者',
               	    	'change_dependent': '扶養',
           	    		'change_emergency_contact': '緊急連絡先'
                	};
                	
                	var checkWorkflowName = $("#f_workflow_name").val();
                	
                	// 2. Map and join them
                	var checkedValuesCategory = $('input[name="f_category"]:checked').map(function() {
                	    var rawValue = $(this).val();
                	    
                	    // Return the custom text if it exists in our map, otherwise fallback to the raw value
                	    return categoryMapping[rawValue] || rawValue; 
                	}).get().join('／');
                	
                	var final_MatterName = checkWorkflowName + "／" + checkedValuesCategory;
                	
                	$("#imwMatterName").val(final_MatterName.substring(0, 100));
                	
                    workflowOpenPage('${f:h(workflowRequestForm.imwPageType)}');
                }

                return false;
            });
            
            function refreshSequenceNumbers() {
                $(".detail_dependent .dependent_rows").each(function(index) {
                    var newSerial = index + 1;

                    $(this).find("input[name='f_change_dependent_sequence_no']").val(index + 1);
                    $(this).find("input[name^='f_change_dependent_change_date_']").attr("name", "f_change_dependent_change_date_" + newSerial);

                    $(this).find("input[name^='f_change_dependent_kanji_last_name_']").attr("name", "f_change_dependent_kanji_last_name_" + newSerial);
                    $(this).find("input[name^='f_change_dependent_kanji_first_name_']").attr("name", "f_change_dependent_kanji_first_name_" + newSerial);

                    $(this).find("input[name^='f_change_dependent_kana_last_name_']").attr("name", "f_change_dependent_kana_last_name_" + newSerial);
                    $(this).find("input[name^='f_change_dependent_kana_first_name_']").attr("name", "f_change_dependent_kana_first_name_" + newSerial);

                    $(this).find("input[name^='f_change_dependent_relationship_']").attr("name", "f_change_dependent_relationship_" + newSerial);
                    $(this).find("input[name^='f_change_dependent_gender_']").attr("name", "f_change_dependent_gender_" + newSerial);
                    $(this).find("input[name^='f_change_dependent_birth_date_']").attr("name", "f_change_dependent_birth_date_" + newSerial);

                    $(this).find("input[name^='f_change_dependent_status_']").attr("name", "f_change_dependent_status_" + newSerial);
                });
            }
            
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
		.title {
		    font-weight: bold;
		    font-size: 30px;
		    margin-left : 220px;
		}
		
		.imui-form td {
		    border-bottom: 1px solid #000; 
		    padding: 6px;
		}
		
		i{
			font-size:10px;
		}
		
        .numeric {
		text-align: right;
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
            
        
		
		.flex-item {
		align-items: center;
		}
		.flex-item label {
			margin-right: auto;
		}
            
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
		.msg_small{
			color:red;
			font-size:12px !important; 
		}
		
		.jpn-txt-version{
			
		}
		
		.eng-txt-version{
			font-size:10px;
			font-style: italic;
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
		
		/* Beautiful Delete Button */
		.delete-btn {
		    display: inline-flex;
		    align-items: center;
		    gap: 8px;
		    background-color: #fef2f2 !important; /* Soft light red/pink tint */
		    color: #ef4444 !important;
		    border: 1px solid #fecaca !important; /* Matching light red border */
		    padding: 8px 16px;
		    border-radius: 6px;
		    /*font-size: 0.9rem;
		    font-weight: 600;*/
		    text-decoration: none;
		    cursor: pointer;
		    transition: all 0.15s ease-in-out;
		}
		
		.delete-btn:hover {
		    background-color: #dc2626 !important;
		    color: #ffffff !important;
		    border-color: #dc2626;
		    box-shadow: 0 4px 12px rgba(220, 38, 38, 0.2); /* Matching red glow */
		}
		
		.delete-btn i {
		    /*font-size: 0.95rem;*/
		}
		
		.imui-text-readonly{
			height:20px; !important;
			padding-top: 0.25em !important; 
			padding-right: 0.4em !important; 
			padding-bottom: 0.25em !important; 
			padding-left: 0.4em !important;
			pointer-events: none !important;
		}
		
		
		
		
		/* Target the actual error text label instead of the input box */
		label.error:not(:empty) {
		    color: #d61657 !important;
		    
		    /* FIX: 'flex' forces the whole message block onto its own new line under the input */
		    display: flex !important; 
		    align-items: center;
		    
		    vertical-align: middle;
		    margin-top:5px !important;
		    clear: both;           /* Prevents floating elements from wrapping around it */
		}
		
		/* Inject your exact custom sprite icon code before the text */
		label.error:not(:empty)::before {
		    content: "" !important;
		    display: inline-block;
		    vertical-align: middle;
		    margin-top:0px;
		    margin-right:5px;
		    
		    /* Your exact custom asset dimensions and coordinate properties */
		    background: transparent url(ui/images/d.png) no-repeat -74px -162px !important;
		    width: 18px;
		    height: 18px;
		    flex-shrink: 0;      /* Prevents the icon sprite from squeezing on narrow rows */
		}
		
		label.error:empty {
		    display: none !important;
		}
		/*
		.error_message {
		    display: inline-flex !important;
		    flex-direction: column !important;
		    align-items: flex-start !important;
		    vertical-align: middle;
		}
		*/

		
	</style>
</imui:head>

<workflow:workflowUserContentsAuth
    imwApplyBaseDate='${f:h(workflowRequestForm.imwApplyBaseDate)}'
    imwAuthUserCode='${f:h(workflowRequestForm.imwAuthUserCode)}'
    imwFlowId='${f:h(workflowRequestForm.imwFlowId)}'
    imwNodeId='${f:h(workflowRequestForm.imwNodeId)}'
    imwPageType='${f:h(workflowRequestForm.imwPageType)}'
    imwSystemMatterId='${f:h(workflowRequestForm.imwSystemMatterId)}'
    imwUserDataId='${f:h(workflowRequestForm.imwUserDataId)}' />

<!-- Title content -->
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

<div class="imui-form-container-wide">

<workflow:workflowOpenPage
    name="workflowOpenPageForm"
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
    
    <input type="hidden" name="imwMatterName" id="imwMatterName" />
    <input type="hidden" name="imwForcedParamFlag" id="imwForcedParamFlag" value="1" />
    
    <input type="hidden" name="f_imwPageType" id="f_imwPageType" value="${f:h(workflowRequestForm.imwPageType)}"/>
    <input type="hidden" name="f_workflow_name" id="f_workflow_name" value="${f:h(savedFormData.f_workflow_name)}"/>
    
	<div class="file_upload_resident_registration_without_mynumber">
		<input type='text' value='' name='resident_registration_without_mynumber_anchor' class="resident_registration_without_mynumber_anchor"
		style="position: absolute;" tabindex="-1">
		
		<c:forEach items="${savedFormData.f_info_file_resident_registration_without_mynumber}" var="v_item_file">
			<div class="${v_item_file.file_real_name}">
				<input type='text' value='0' class='f_upload_file_id' name='f_upload_file_id'>
				<input type='text' value="${v_item_file.file_name}" class='f_upload_file_name' name='f_upload_file_name' >
				<input type='hidden' value="${v_item_file.file_real_name}" class='f_upload_file_real_name' name='f_upload_file_real_name'>
				<input type='hidden' value="${v_item_file.file_size}" class='f_upload_file_size' name='f_upload_file_size'>
				<input type='hidden' value="${v_item_file.file_extension}" class='f_upload_file_extension' name='f_upload_file_extension'>
				<input type='hidden' value="${v_item_file.file_type}" class='f_upload_file_type' name='f_upload_file_type'>
			</div>
		</c:forEach>
	</div>
	
	<div class="file_upload_resident_registration_with_mynumber">
		<input type='text' value='' name='resident_registration_with_mynumber_anchor' class="resident_registration_with_mynumber_anchor"
		style="position: absolute;" tabindex="-1">
		
		<c:forEach items="${savedFormData.f_info_file_resident_registration_with_mynumber}" var="v_item_file">
			<div class="${v_item_file.file_real_name}">
				<input type='hidden' value='0' class='f_upload_file_id' name='f_upload_file_id'>
				<input type='hidden' value="${v_item_file.file_name}" class='f_upload_file_name' name='f_upload_file_name' >
				<input type='hidden' value="${v_item_file.file_real_name}" class='f_upload_file_real_name' name='f_upload_file_real_name'>
				<input type='hidden' value="${v_item_file.file_size}" class='f_upload_file_size' name='f_upload_file_size'>
				<input type='hidden' value="${v_item_file.file_extension}" class='f_upload_file_extension' name='f_upload_file_extension'>
				<input type='hidden' value="${v_item_file.file_type}" class='f_upload_file_type' name='f_upload_file_type'>
			</div>
		</c:forEach>
	</div>
	
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
					<input type="text" value="${f:h(savedFormData.f_application_number)}" 
					id="f_application_number" name="f_application_number" 
					placeholder="" class="imui-text-readonly input_text_100" readonly
					style="" size="38" tabindex="-1">
					
				</td>
				<th>
					<label class="jpn-txt-version">申請日</label>
					<label class="eng-txt-version">Application Date</label>
				</th>
				<td>
					<input type="text" value="${f:h(savedFormData.f_application_date)}" 
					id="f_application_date" name="f_application_date" 
					placeholder="" class="imui-text-readonly input_text_100" readonly
					style="" size="38" tabindex="-1">
				</td>
			</tr>
			
			<tr>
				<th>
					<label class="jpn-txt-version">社員番号</label>
					<label class="eng-txt-version">Employee Number</label>
				</th>
				<td>
					<input type="text" value="${f:h(savedFormData.f_applicant_number)}" 
					id="f_applicant_number" name="f_applicant_number" 
					placeholder="" class="imui-text-readonly input_text_100" readonly
					style="" size="38" tabindex="-1">
					
				</td>
				<th>
				<label class="jpn-txt-version">所属名</label>
					<label class="eng-txt-version">Department Name</label>
					
				</th>
				<td>
					<input type="text" value="${f:h(savedFormData.f_applicant_department)}" 
					id="f_applicant_department" name="f_applicant_department" 
					placeholder="" class="imui-text-readonly input_text_100" readonly
					style="" size="38" tabindex="-1">
					
				</td>
			</tr>
			<tr>
				<th>
					<label class="jpn-txt-version">申請者名</label>
					<label class="eng-txt-version">Applicant Name</label>
				</th>
				<td>
					<input type="text" value="${f:h(savedFormData.f_applicant_name)}" 
					id="f_applicant_name" name="f_applicant_name" 
					placeholder="" class="imui-text-readonly input_text_100" readonly
					style="" size="38" tabindex="-1">
				</td>
				<th>
					<label class="jpn-txt-version">役職 </label>
					<label class="eng-txt-version">Applicant Post</label>
				</th>
				<td>
					<input type="text" value="${f:h(savedFormData.f_applicant_post)}" 
					id="f_applicant_post" name="f_applicant_post" 
					placeholder="" class="imui-text-readonly input_text_100" readonly
					style="" size="38" tabindex="-1">
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
	                <label class="jpn-txt-version imui-required">変更区分</label>
	                <label class="eng-txt-version">Change Category</label>
	            </th>
	            <td colspan="3">
	            	<label class="jpn-txt-version">
	            		<input type="checkbox" name="f_category" value="change_name" ${f:h(savedFormData.f_category_change_name)}>
	            		氏名
	            	</label>
	            	<label class="eng-txt-version">(Full Name)</label>
	            	<br>
	            	
	            	<label class="jpn-txt-version">
	            		<input type="checkbox" name="f_category" value="change_address" ${f:h(savedFormData.f_category_change_address)}>
	            		住所（会社で手配するレオパレスは除く）・電話番号
	            	</label>
	                <label class="eng-txt-version">(Address - Excluding company-arranged LeoPalace housing & Phone Number)</label>
	                <br>
	                
	                <label class="jpn-txt-version">
	            		<input type="checkbox" name="f_category" value="change_spouse" ${f:h(savedFormData.f_category_change_spouse)}>
	            		配偶者
	            	</label>
	                <label class="eng-txt-version">(Spouse)</label>
	                <br>
	                
	                <label class="jpn-txt-version">
	            		<input type="checkbox" name="f_category" value="change_dependent" ${f:h(savedFormData.f_category_change_dependent)}>
	            		扶養
	            	</label>
	                <label class="eng-txt-version">(Dependent)</label>
	                <br>
	                
	                <label class="jpn-txt-version">
	                	<input type="checkbox" name="f_category" value="change_emergency_contact" ${f:h(savedFormData.f_category_change_emergency_contact)}>
	            		緊急連絡先
	            	</label>
	                <label class="eng-txt-version">(Emergency Contact)</label>
	                <br>
	                
	                <div class="error_message"></div>
	            </td>
	        </tr>
	
	        <tr>
	            <th>
	            	<label class="jpn-txt-version imui-required">変更理由</label>
	                <label class="eng-txt-version">Change Reason</label>
	            </th>
	            <td style="padding-right:20px;"  colspan="3">
	                <textarea rows="5" name="f_change_reason" style="width:100%; max-width:100%;" class="input_text_100">${f:h(savedFormData.f_change_reason)}</textarea>
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
		            <label class="jpn-txt-version imui-required">変更日</label>
		            <label class="eng-txt-version">Change Date</label>
	           	</th>
	            <td colspan="3">
	                <span style="display: inline-block; white-space: nowrap;">
	                    <input value="${f:h(savedFormData.f_change_name_date)}" id="f_change_name_date" 
								name="f_change_name_date" type="text" style="height:20px;" size="35" placeholder="yyyy/mm/dd"
								class="input_text_90">
							<im:calendar format="yyyy/MM/dd" changeYear="true" changeMonth="true" floatable="true" altField="#f_change_name_date" 
								showMonthAfterYear="true" />
	                </span>
	                <div class="error_message"></div>
	            </td>
	        </tr>
	
	        <tr>
	            <th>
		            <label class="jpn-txt-version imui-required">変更後の氏名（漢字）</label>
		            <label class="eng-txt-version">Changed Name in (Kanji)</label>
				</th>
	            <td colspan="3">
	            	<span style="display: inline-block; white-space: nowrap;">
		                <span style="display:inline-block; width:30px;">姓</span>
		                <input type="text" value="${f:h(savedFormData.f_change_name_kanji_last_name)}" id="f_change_name_kanji_last_name" name="f_change_name_kanji_last_name" 
							placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
							
		                <span style="display:inline-block; width:30px; margin-left:12px;">名</span>
						<input type="text" value="${f:h(savedFormData.f_change_name_kanji_first_name)}" id="f_change_name_kanji_first_name" name="f_change_name_kanji_first_name" 
							placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
					</span>
					<div class="error_message"></div>
	            </td>
	        </tr>
	        <tr>
	            <th>
		            <label class="jpn-txt-version imui-required">変更後の氏名（フリガナ）</label>
		            <label class="eng-txt-version">Changed Name (Katakana)</label>
	            </th>
	            <td colspan="3">
	            	<span style="display: inline-block; white-space: nowrap;">
		                <span style="display:inline-block; width:30px;">セイ</span>
						<input type="text" value="${f:h(savedFormData.f_change_name_kana_last_name)}" id="f_change_name_kana_last_name" name="f_change_name_kana_last_name" 
							placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
		                       
		                <span style="display:inline-block; width:30px; margin-left:12px;">メイ</span>
						<input type="text" value="${f:h(savedFormData.f_change_name_kana_first_name)}" id="f_change_name_kana_first_name" name="f_change_name_kana_first_name" 
							placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
	                </span>
	                <div class="error_message"></div>
	            </td>
	        </tr>
	        <tr>
	            <th>
	            	<label class="jpn-txt-version imui-required">今後社内で使用する氏名</label>
	            	<label class="eng-txt-version">Name to Be Used Internally Going Forward</label>
	            </th>
	            <td colspan="3">
					<label class="jpn-txt-version">
						<input type="radio" name="f_change_name_internal_use" value="new_name" ${f:h(savedFormData.f_change_name_internal_use_new_name)}>
						新しい名前を使用する 
					</label>
			       	<label class="eng-txt-version"> (Use New Name)</label>
	                <br>
	                
	                <label class="jpn-txt-version">
	                	<input type="radio" name="f_change_name_internal_use" value="previous_name" ${f:h(savedFormData.f_change_name_internal_use_use_previous_name)}>
		               	今までの名前を利用する
		            </label>
			        <label class="eng-txt-version"> (Use Previously Entered Name)</label>
			        
			        <div class="error_message"></div>
	            </td>
	        </tr>
	    </tbody>
	</table>

	<header class="imui-chapter-title section_change_address">
	    <h2>住所情報<i class="eng-txt-version"><br>Address Information</i></h2>
	    <label class=" jpn-txt-version msg" >
	    	住民票に変更ある時は、住民票（マイナンバー記載なし）を添付して下さい。<br>
			宅建取引士証・管理業務主任者等の住所変更について、<br>
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
	            	<label class="jpn-txt-version  imui-required">変更日</label>
	            	<label class="eng-txt-version">Change Date</label>
	            </th>
	            <td>
	                <span style="display: inline-block; white-space: nowrap;">
	                    <input value="${f:h(savedFormData.f_change_address_date)}" id="f_change_address_date" 
								name="f_change_address_date" type="text" style="height:20px;" size="35" placeholder="yyyy/mm/dd"
								class="input_text_90">
						<im:calendar format="yyyy/MM/dd" changeYear="true" changeMonth="true" floatable="true" altField="#f_change_address_date" 
							showMonthAfterYear="true" />
	                </span>
	                
	                <div class="error_message"></div>
	            </td>
	            <td colspan="2"></td>
	        </tr>
			<tr>
	            <th>
		            <label class="jpn-txt-version imui-required">住民票住所変更</label>
		            <label class="eng-txt-version">Change of Registered Address</label>
				</th>
	            <td>
	            	<label class="jpn-txt-version">
	                	<input type="radio" name="f_change_address_register" value="yes" ${f:h(savedFormData.f_change_address_register_yes)}/>
	                	あり
	                </label>
		            <label class="eng-txt-version">(Yes)</label>
		            <br>
		   			
		   			<label class="jpn-txt-version">         
		            	<input type="radio" name="f_change_address_register" value="no" ${f:h(savedFormData.f_change_address_register_no)}>	                        
	                	なし
	                </label>
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
		            <label class="jpn-txt-version imui-required">新住所</label>
		            <label class="eng-txt-version">New Residence</label>
				</th>
	            <th>
		            <label class="jpn-txt-version imui-required">郵便番号</label>
		            <label class="eng-txt-version">Postal Code</label>
		            <label class="jpn-txt-version msg_small">ハイフン無しで入力してください。</label>
		            
				</th>
	            <td>
	            	<span style="display: inline-block; white-space: nowrap;" class="input_text_100">
		            	<input type="text" value="${f:h(savedFormData.f_change_address_new_postal_code)}" id="f_change_address_new_postal_code" name="f_change_address_new_postal_code" 
								placeholder="" class="" style="height:20px;">
		                <button type="button" id="f_change_address_new_postal_code_button_getpostalcode">住所自動入力</button>
	                </span>
	                <div class="error_message"></div>
	            </td>
	            <td colspan="2"></td>
	        </tr>
	        <tr>
	            <th>
	            	<label class="jpn-txt-version imui-required">住所</label>
	            	<label class="eng-txt-version">Address</label>
	            </th>
	            <td colspan="3">
	            	<input type="text" value="${f:h(savedFormData.f_change_address_new_address)}" id="f_change_address_new_address" name="f_change_address_new_address" 
							placeholder="" class="input_text_100" style="height:20px;" size="38">
					<div class="error_message"></div>
	            </td>
	        </tr>
	        <tr>
	            <th>
		            <label class="jpn-txt-version">電話番号</label>
		            <label class="eng-txt-version">Phone Number</label>
	            </th>
	            <td>
	            	<input type="text" value="${f:h(savedFormData.f_change_address_new_phone_number)}" id="f_change_address_new_phone_number" name="f_change_address_new_phone_number" 
							placeholder="" class="input_text_100" style="height:20px;" size="38">
					<div class="error_message"></div>
	           	</td>
	           	<td colspan="2"></td>
	        </tr>
	        <tr>
	            <th colspan="2">
		            <label class="jpn-txt-version imui-required">住民票住所</label>
		            <label class="eng-txt-version">Registered Address</label>
				</th>
	            <td>
	            	<label class="jpn-txt-version">
	                	<input type="radio" name="f_change_address_register_type" value="same_as_new_address" ${f:h(savedFormData.f_change_address_register_type_same_as_new_address)} />
	               	 	新住所と同じ
	               	</label>
		            <label class="eng-txt-version">(Same as New Address)</label>
		            <br>
		            
		            <label class="jpn-txt-version">
		            	<input type="radio" name="f_change_address_register_type" value="different_address" ${f:h(savedFormData.f_change_address_register_type_different_address)}/>    
	                	別住所
	                </label>
		            <label class="eng-txt-version">(Different Address)</label>
		            
		            <div class="error_message"></div>
	            </td>
	            <td colspan="2"></td>
	        </tr>
	        <tr>
	        	<th rowspan="3">
		            <label class="jpn-txt-version imui-required">住民票住所</label>
		            <label class="eng-txt-version">Resident Address</label>
				</th>
	            <th>
		            <label class="jpn-txt-version imui-required">郵便番号</label>
		            <label class="eng-txt-version">Postal Code</label>
		            <label class="jpn-txt-version msg_small">ハイフン無しで入力してください。</label>
				</th>
	            <td>
	            	<span style="display: inline-block; white-space: nowrap;" class="input_text_100">
		            	<input type="text" value="${f:h(savedFormData.f_change_address_resident_postal_code)}" id="f_change_address_resident_postal_code" name="f_change_address_resident_postal_code" 
								placeholder="" class="" style="height:20px;">
		                <button type="button" id="f_change_address_resident_postal_code_button_getpostalcode">住所自動入力</button>
	                </span>
	                <div class="error_message"></div>
	            </td>
	            <td colspan="2"></td>
	        </tr>
	        <tr>
	            <th>
		            <label class="jpn-txt-version imui-required">住所</label>
		            <label class="eng-txt-version">Address</label>
	            </th>
	            <td colspan="3">
	            	<input type="text" value="${f:h(savedFormData.f_change_address_resident_address)}" id="f_change_address_resident_address" name="f_change_address_resident_address" 
							placeholder="" class="input_text_100" style="height:20px;" size="38">
	            	<div class="error_message"></div>
	            </td>
	        </tr>
	        <tr>
	            <th>
		            <label class="jpn-txt-version">電話番号</label>
		            <label class="eng-txt-version">Phone Number</label>
	            </th>
	            <td>
	            	<input type="text" value="${f:h(savedFormData.f_change_address_resident_phone_number)}" id="f_change_address_resident_phone_number" name="f_change_address_resident_phone_number" 
							placeholder="" class="input_text_100" style="height:20px;" size="38">
	            	<div class="error_message"></div>
	            </td>
	            <td colspan="2"></td>
	        </tr>
	    </tbody>
	</table>

	<header class="imui-chapter-title section_change_spouse">
	    <h2>配偶者情報<i class="eng-txt-version"><br>Spouse Information</i></h2>
	    <label class=" jpn-txt-version msg" >
	    	扶養追加の場合には住民票（マイナンバー記載あり）を添付してください。<br>
       		扶養から外す場合で資格確認書（黄色のカード）を発行されている方については、人事部まで返却をお願いします。<br>
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
		            <label class="jpn-txt-version imui-required">変更日</label>
		            <label class="eng-txt-version">Change Date</label>
	            </th>
	            <td>
	            	<span style="display: inline-block; white-space: nowrap;">
	                    <input value="${f:h(savedFormData.f_change_spouse_date)}" id="f_change_spouse_date" name="f_change_spouse_date" 
	                    type="text" style="height:20px;" size="35" placeholder="yyyy/mm/dd" class="input_text_90">
						<im:calendar format="yyyy/MM/dd" changeYear="true" changeMonth="true" floatable="true" altField="#f_change_spouse_date" 
							showMonthAfterYear="true" />
	                </span>
	                <div class="error_message"></div>
	            </td>
	            <td colspan="2"></td>
	        </tr>
	
	        <tr>
	            <th>
	            	<label class="jpn-txt-version imui-required">配偶者名（漢字）</label>
	            	<label class="eng-txt-version">Spouse Name in Kanji</label>
	            </th>
	            <td colspan="3">
	            	<span style="display: inline-block; white-space: nowrap;">
		                <span style="display:inline-block; width:30px;">姓</span>
		                <input type="text" value="${f:h(savedFormData.f_change_spouse_kanji_last_name)}" id="f_change_spouse_kanji_last_name" name="f_change_spouse_kanji_last_name" 
							placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
							
		                <span style="display:inline-block; width:30px; margin-left:12px;">名</span>
						<input type="text" value="${f:h(savedFormData.f_change_spouse_kanji_first_name)}" id="f_change_spouse_kanji_first_name" name="f_change_spouse_kanji_first_name" 
							placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
					</span>
					<div class="error_message"></div>
	            </td>
	        </tr>
	
	        <tr>
	            <th>
	            	<label class="jpn-txt-version imui-required">配偶者名（フリガナ）</label>
	            	<label class="eng-txt-version">Spouse Name Katakana</label>
	            </th>
	            <td colspan="3">
	            	<span style="display: inline-block; white-space: nowrap;">
		                <span style="display:inline-block; width:30px;">セイ</span>
						<input type="text" value="${f:h(savedFormData.f_change_spouse_kana_last_name)}" id="f_change_spouse_kana_last_name" name="f_change_spouse_kana_last_name" 
							placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
		                       
		                <span style="display:inline-block; width:30px; margin-left:12px;">メイ</span>
						<input type="text" value="${f:h(savedFormData.f_change_spouse_kana_first_name)}" id="f_change_spouse_kana_first_name" name="f_change_spouse_kana_first_name" 
							placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
	                </span>
	                <div class="error_message"></div>
	            </td>
	        </tr>
	        <tr>
		        <th>
		        	<label class="jpn-txt-version imui-required">配偶者区分</label>
		        	<label class="eng-txt-version">Spouse Status</label>
		        </th>
	            <td colspan="3">
	            	<label class="jpn-txt-version">
	                	<input type="radio" name="f_change_spouse_type" value="become_spouse" ${f:h(savedFormData.f_change_spouse_type_become_spouse)}/> 
		               	 配偶者となる
		            </label>
		            <label class="eng-txt-version">(Become a Spouse)</label>
	                <br>
	                
	                <label class="jpn-txt-version">
	                	<input type="radio" name="f_change_spouse_type" value="cease_to_be_spouse" ${f:h(savedFormData.f_change_spouse_type_cease_to_be_spouse)}>
	                	配偶者でなくなる
	                </label>
		            <label class="eng-txt-version">(Cease to be a Spouse)</label>
		            
		            <div class="error_message"></div>
	            </td>
			</tr>
			<tr>
	            <th><label class="jpn-txt-version imui-required">扶養区分</label><label class="eng-txt-version">Dependent Status</label></th>
	            <td colspan="3">
	            	<label class="jpn-txt-version">
	                	<input type="radio" name="f_change_spouse_dependent" value="add_to_dependent" ${f:h(savedFormData.f_change_spouse_dependent_add_to_dependent)}/>
	                	扶養に入れる
	                </label>
		            <label class="eng-txt-version">(Add to Dependents)</label>
		            <br>
		            
		            <label class="jpn-txt-version">
	                	<input type="radio" name="f_change_spouse_dependent" value="not_dependent" ${f:h(savedFormData.f_change_spouse_dependent_not_dependent)}>
	               		扶養に入れない／扶養から外す
	               	</label>
		            <label class="eng-txt-version">(Not a dependent / Remove dependent status)</label>
		                
		           	<div class="error_message"></div>
	            </td>
			</tr>
	    </tbody>
	</table>


	<header class="imui-chapter-title section_change_dependent">
	    <h2>扶養情報<i class="eng-txt-version"><br>Dependent Information</i></h2>
	    <label class=" jpn-txt-version msg" >
	    	扶養追加の場合には住民票（マイナンバー記載あり）を添付してください。<br>
       		扶養から外す場合で資格確認書（黄色のカード）を発行されている方については、人事部まで返却をお願いします。<br>
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
                	<input type="button"value="追加" id="addrows_dependent" class="add-button"> 
                </th>
                <th style="text-align:center; width:60px;">
                	<label class="jpn-txt-version">番号</label><label class="eng-txt-version">No</label>
                </th>
                <th style="text-align:center; width:140px;">
                	<label class="jpn-txt-version imui-required">変更日</label><label class="eng-txt-version">Change Date</label>
                </th>
                <th style="text-align:center; width:170px;">
                	<label class="jpn-txt-version imui-required">氏名（漢字）</label><label class="eng-txt-version">Name (Kanji)</label>
                </th>
                <th style="text-align:center; width:190px;">
                	<label class="jpn-txt-version imui-required">氏名（フリガナ）</label><label class="eng-txt-version">Name (Katakana)</label>
                </th>
                <th style="text-align:center; width:130px;">
                	<label class="jpn-txt-version imui-required">申請者からみた続柄</label><label class="eng-txt-version">Relationship</label>
                </th>
                <th style="text-align:center; width:120px;">
                	<label class="jpn-txt-version imui-required">性別</label><label class="eng-txt-version">Gender</label>
                </th>
                <th style="text-align:center; width:140px;">
                	<label class="jpn-txt-version imui-required">生年月日</label><label class="eng-txt-version">Date of Birth</label>
                </th>
                <th style="text-align:center; width:100px;">
                	<label class="jpn-txt-version imui-required">扶養区分</label><label class="eng-txt-version">Dependent Status</label>
                </th>
            </tr>
        </thead>
        <tbody class="detail_dependent">
        	<c:forEach items="${savedFormData.f_list_detail_dependent}" var="detail_dependent">
        		<tr class="dependent_rows">
        			<td style="text-align:center;">
	                	<input type="button" value="削除" class="f_change_dependent_delete"/>
	                </td>
	                <td style="text-align:center;">
	                	<input type="text" value="${detail_dependent.sequence_no}" name="f_change_dependent_sequence_no"  
	                		class="imui-text-readonly f_change_dependent_sequence_no" style="width:40px; height:20px; text-align:center;" readonly="readonly">
	                	<div class='error_message'></div>
	                </td>
	                <td style="text-align:center;">
	                	<input type="text" value="${detail_dependent.change_date}" name="f_change_dependent_change_date_${detail_dependent.sequence_no}" class="imuiCalendar f_change_dependent_change_date" 
	                		placeholder="yyyy/mm/dd" style="width:70%; height:20px;">
                	</td>
                	<td style="text-align:center;">
	                	<div style="display:flex; align-items:center; gap:6px; justify-content:center; white-space:nowrap;">
		                	<span>姓 </span>
		                	<input type="text" value="${detail_dependent.dependent_kanji_last_name}" name="f_change_dependent_kanji_last_name_${detail_dependent.sequence_no}" class="f_change_dependent_kanji_last_name" 
		                		style="width:40%; height:20px;">
		                	
		                	<span> 名 </span>
		                	<input type="text" value="${detail_dependent.dependent_kanji_first_name}" name="f_change_dependent_kanji_first_name_${detail_dependent.sequence_no}" class="f_change_dependent_kanji_first_name" 
		                		style="width:40%; height:20px;">
	                	</div>
	                	<div class='error_message'></div>
	               	</td>
	               	<td style="text-align:center;">
	                	<div style="display:flex; align-items:center; gap:6px; justify-content:center; white-space:nowrap;">
		                	<span>セイ </span>
		                	<input type="text" value="${detail_dependent.dependent_kana_last_name}" name="f_change_dependent_kana_last_name_${detail_dependent.sequence_no}" class="f_change_dependent_kana_last_name" 
		                		style="width:40%; height:20px;">
		                	
		                	<span>メイ </span>
		                	<input type="text" value="${detail_dependent.dependent_kana_first_name}" name="f_change_dependent_kana_first_name_${detail_dependent.sequence_no}" class="f_change_dependent_kana_first_name" 
		                		style="width:40%; height:20px;">
	                	</div>
	                	<div class='error_message'></div>
	               	</td>
	               	<td style="text-align:center;">
	               		<input type="text" value="${detail_dependent.relationship}" name="f_change_dependent_relationship_${detail_dependent.sequence_no}" class="f_change_dependent_relationship" 
	               			style="width:90%; height:20px;">
	               		<div class='error_message'></div>
	               	</td>
	               	<td style="text-align:center;">
	                	<select name="f_change_dependent_gender_${detail_dependent.sequence_no}" class="select2 f_change_dependent_gender" style="width:90%;">
	                		<option value=""></option>
	                		<option value="male" ${detail_dependent.gender_male}>男</option>
	                		<option value="female" ${detail_dependent.gender_female}>女</option>
	                		<option value="notset" ${detail_dependent.gender_notset}>指定しない</option>
	                	</select>
	                	<div class='error_message'></div>
	                </td>
	               	<td style="text-align:center;">
	               		<input type="text" value="${detail_dependent.birth_date}" name="f_change_dependent_birth_date_${detail_dependent.sequence_no}" class="imuiCalendar f_change_dependent_birth_date" 
	                	placeholder="yyyy/mm/dd" style="width:70%; height:20px;">
	                </td>
	                <td style="text-align:left;">
	                    <table style="margin:0px;">
							<tr>
								<td style="border:none; padding:0px;">
									<input type="radio" name="f_change_dependent_status_${detail_dependent.sequence_no}" value="add" class="f_change_dependent_status"
									id="f_change_dependent_status_add_${detail_dependent.sequence_no}" ${detail_dependent.dependent_status_add}>
								</td>
								<td style="border:none; padding:0px;">
									<label class="jpn-txt-version" for="f_change_dependent_status_add_${detail_dependent.sequence_no}">扶養に入れる</label>
						   			<label class="eng-txt-version" for="f_change_dependent_status_add_${detail_dependent.sequence_no}">(Add Dependents)</label>
								</td>
							</tr>
							<tr>
								<td style="border:none; padding:0px;">
									<input type="radio" name="f_change_dependent_status_${detail_dependent.sequence_no}" value="remove"  class="f_change_dependent_status"
										id="f_change_dependent_status_remove_${detail_dependent.sequence_no}" ${detail_dependent.dependent_status_remove}>
								</td>
								<td style="border:none; padding:0px;">
									<label class="jpn-txt-version" for="f_change_dependent_status_remove_${detail_dependent.sequence_no}">扶養から外す</label>
						    		<label class="eng-txt-version" for="f_change_dependent_status_remove_${detail_dependent.sequence_no}">(Remove Dependents)</label>
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
					<label class="jpn-txt-version imui-required">第一緊急連絡先</label>
	                <label class="eng-txt-version">Primary Emergency Contact</label>
				</th>
	            <th>
	                <label class="jpn-txt-version imui-required">変更日</label>
	                <label class="eng-txt-version">Change Date</label>
	            </th>
	            <td>
	            	<span style="display: inline-block; white-space: nowrap;">
	                    <input value="${f:h(savedFormData.f_change_emergency_contact_date)}" id="f_change_emergency_contact_date" 
								name="f_change_emergency_contact_date" type="text" style="height:20px;" size="35" placeholder="yyyy/mm/dd"
								class="input_text_90">
						<im:calendar format="yyyy/MM/dd" changeYear="true" changeMonth="true" floatable="true" altField="#f_change_emergency_contact_date" 
								showMonthAfterYear="true" />
	                </span>
	                <div class="error_message"></div>
	            </td>
	            <td colspan="2"></td>
			</tr>
			<tr>
	            <th>
	                <label class="jpn-txt-version imui-required">連絡先氏名（漢字）</label>
	                <label class="eng-txt-version">Name in Kanji</label>
	            </th>
	            <td colspan="3">
	            	<span style="display: inline-block; white-space: nowrap;">
		                <span style="display:inline-block; width:30px;">姓</span>
		                <input type="text" value="${f:h(savedFormData.f_change_emergency_contact_kanji_last_name)}" id="f_change_emergency_contact_kanji_last_name" name="f_change_emergency_contact_kanji_last_name" 
							placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
							
		                <span style="display:inline-block; width:30px; margin-left:12px;">名</span>
						<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_kanji_first_name)}" id="f_change_emergency_contact_kanji_first_name" name="f_change_emergency_contact_kanji_first_name" 
							placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
					</span>
					<div class="error_message"></div>
	            </td>
	        </tr>
	        <tr>
	            <th>
	                <label class="jpn-txt-version imui-required">連絡先氏名（フリガナ）</label>
	                <label class="eng-txt-version">Name Katakana</label>
	            </th>
	            <td colspan="3">
	            	<span style="display: inline-block; white-space: nowrap;">
		                <span style="display:inline-block; width:30px;">セイ</span>
						<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_kana_last_name)}" id="f_change_emergency_contact_kana_last_name" name="f_change_emergency_contact_kana_last_name" 
							placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
		                       
		                <span style="display:inline-block; width:30px; margin-left:12px;">メイ</span>
						<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_kana_first_name)}" id="f_change_emergency_contact_kana_first_name" name="f_change_emergency_contact_kana_first_name" 
							placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
	                </span>
	                <div class="error_message"></div>
	            </td>
	        </tr>
	        <tr>
	            <th>
	                <label class="jpn-txt-version imui-required">申請者からみた続柄</label>
	                <label class="eng-txt-version">Relationship</label>
	            </th>
	            <td>
	            	<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_relationship)}" id="f_change_emergency_contact_relationship" name="f_change_emergency_contact_relationship" 
								placeholder="" class="input_text_100" style="height:20px;" size="38">
					<div class="error_message"></div>
	            </td>
	            <td colspan="2"></td>
	        </tr>
			<tr>
	            <th>
	                <label class="jpn-txt-version imui-required">緊急連絡先区分</label>
	                <label class="eng-txt-version">Emergency contact category</label>
	            </th>
	            <td colspan="3">
	            	<label class="jpn-txt-version">
	                	<input type="radio" name="f_change_emergency_contact_type" value="domestic" class="emergency-contact-type" ${f:h(savedFormData.f_change_emergency_contact_type_domestic)}/>
	                	国内
	                </label>
	                <label class="eng-txt-version">(Domestic)</label>                
	                <br>
	                
	                <label class="jpn-txt-version">                
	                	<input type="radio" name="f_change_emergency_contact_type" value="overseas" class="emergency-contact-type" ${f:h(savedFormData.f_change_emergency_contact_type_overseas)}>
						海外
					</label>
	                <label class="eng-txt-version">(Overseas)</label>
	                
	                <div class="error_message"></div>
	            </td>
	        </tr>
	        <tr>
	            <th>
	                <label class="jpn-txt-version imui-required">電話番号</label>
	                <label class="eng-txt-version">Phone Number</label>
	            </th>
	            <td>
	            	<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_phone_number)}" id="f_change_emergency_contact_phone_number" name="f_change_emergency_contact_phone_number" 
								placeholder="" class="input_text_100" style="height:20px;" size="38">
					<div class="error_message"></div>
	            </td>
	            <td colspan="2"></td>
	        </tr>
	        <tr class="emergency_contact_domestic">
	        	<th>
	                <label class="jpn-txt-version imui-required">郵便番号</label>
	                <label class="eng-txt-version">Postal Code</label>
	                <label class="jpn-txt-version msg_small">ハイフン無しで入力してください。</label>
	            </th>
	            <td colspan="3">
	            	<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_domestic_postal_code)}" id="f_change_emergency_contact_domestic_postal_code" name="f_change_emergency_contact_domestic_postal_code" 
								placeholder="" class="" style="height:20px;">
	                <button type="button" id="f_change_emergency_contact_domestic_postal_code_button_getpostalcode">住所自動入力</button>
	                <div class="error_message"></div>
	            </td>
			</tr>
			<tr class="emergency_contact_overseas">
	            <th>
	                <label class="jpn-txt-version imui-required">郵便番号</label>
	                <label class="eng-txt-version">Postal Code</label>
	            </th>
	            <td>
	            	<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_overseas_postal_code)}" id="f_change_emergency_contact_overseas_postal_code" name="f_change_emergency_contact_overseas_postal_code" 
								placeholder="" class="input_text_100" style="height:20px;" size="38">
					<div class="error_message"></div>
	            </td>
	            <td colspan="2"></td>
	        </tr>
			<tr class="emergency_contact_overseas">
	            <th>
	                <label class="jpn-txt-version imui-required">国</label>
	                <label class="eng-txt-version">Country</label>
	            </th>
	            <td>
	            	<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_overseas_country)}" id="f_change_emergency_contact_overseas_country" name="f_change_emergency_contact_overseas_country" 
								placeholder="" class="input_text_100" style="height:20px;" size="38">
					<div class="error_message"></div>
	            </td>
	            <td colspan="2"></td>
	        </tr>
			<tr>
	            <th>
	                <label class="jpn-txt-version imui-required">住所</label>
	                <label class="eng-txt-version">Address</label>
	            </th>
	            <td colspan="3">
	            	<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_address)}" id="f_change_emergency_contact_address" name="f_change_emergency_contact_address" 
								placeholder="" class="input_text_100" style="height:20px;" size="38">
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
            	<span style="display: inline-block; white-space: nowrap;">
                    <input value="${f:h(savedFormData.f_change_emergency_contact_2_date)}" id="f_change_emergency_contact_2_date" 
							name="f_change_emergency_contact_2_date" type="text" style="height:20px;" size="35" placeholder="yyyy/mm/dd"
							class="input_text_90">
					<im:calendar format="yyyy/MM/dd" changeYear="true" changeMonth="true" floatable="true" altField="#f_change_emergency_contact_2_date" 
							showMonthAfterYear="true" />
                </span>
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
            
            	<span style="display: inline-block; white-space: nowrap;">
	                <span style="display:inline-block; width:30px;">姓</span>
	                <input type="text" value="${f:h(savedFormData.f_change_emergency_contact_2_kanji_last_name)}" id="f_change_emergency_contact_2_kanji_last_name" name="f_change_emergency_contact_2_kanji_last_name" 
						placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
						
	                <span style="display:inline-block; width:30px; margin-left:12px;">名</span>
					<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_2_kanji_first_name)}" id="f_change_emergency_contact_2_kanji_first_name" name="f_change_emergency_contact_2_kanji_first_name" 
						placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
				</span>
				<div class="error_message"></div>
            </td>
        </tr>
        <tr>
            <th>
                <label class="jpn-txt-version">連絡先氏名（フリガナ）</label>
                <label class="eng-txt-version">Name Katakana</label>
            </th>
            <td colspan="3">
            	<span style="display: inline-block; white-space: nowrap;">
	                <span style="display:inline-block; width:30px;">セイ</span>
					<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_2_kana_last_name)}" id="f_change_emergency_contact_2_kana_last_name" name="f_change_emergency_contact_2_kana_last_name" 
						placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
	                       
	                <span style="display:inline-block; width:30px; margin-left:12px;">メイ</span>
					<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_2_kana_first_name)}" id="f_change_emergency_contact_2_kana_first_name" name="f_change_emergency_contact_2_kana_first_name" 
						placeholder="" class="input_text_100" style="height:20px; width:110px;" size="38">
                </span>
                <div class="error_message"></div>
            </td>
        </tr>
        <tr>
            <th>
                <label class="jpn-txt-version">申請者からみた続柄</label>
                <label class="eng-txt-version">Relationship</label>
            </th>
            <td>
            	<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_2_relationship)}" id="f_change_emergency_contact_2_relationship" name="f_change_emergency_contact_2_relationship" 
							placeholder="" class="input_text_100" style="height:20px;" size="38">
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
            	<label class="jpn-txt-version">
            		<input type="radio" name="f_change_emergency_contact_2_type" value="domestic" class="emergency-contact-type" ${f:h(savedFormData.f_change_emergency_contact_2_type_domestic)}/>
                	国内
                </label>
                <label class="eng-txt-version">(Domestic)</label>                
                <br>
                
                <label class="jpn-txt-version">                
                	<input type="radio" name="f_change_emergency_contact_2_type" value="overseas" class="emergency-contact-type" ${f:h(savedFormData.f_change_emergency_contact_2_type_overseas)}>
					海外
				</label>
                <label class="eng-txt-version">(Overseas)</label>
            </td>
        </tr>
        <tr>
            <th>
                <label class="jpn-txt-version">電話番号</label>
                <label class="eng-txt-version">Phone Number</label>
            </th>
            <td>
            	<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_2_phone_number)}" id="f_change_emergency_contact_2_phone_number" name="f_change_emergency_contact_2_phone_number" 
							placeholder="" class="input_text_100" style="height:20px;" size="38">
				<div class="error_message"></div>
            </td>
            <td colspan="2"></td>
        </tr>
        <tr class="emergency_contact_2_domestic">
        	<th>
                <label class="jpn-txt-version">郵便番号</label>
                <label class="eng-txt-version">Postal Code</label>
                <label class="jpn-txt-version msg_small">ハイフン無しで入力してください。</label>
            </th>
            <td colspan="3">
            	<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_2_domestic_postal_code)}" id="f_change_emergency_contact_2_domestic_postal_code" name="f_change_emergency_contact_2_domestic_postal_code" 
							placeholder="" class="" style="height:20px;">
                <button type="button" id="f_change_emergency_contact_2_domestic_postal_code_button_getpostalcode">住所自動入力</button>
                <div class="error_message"></div>
            </td>
		</tr>
		<tr class="emergency_contact_2_overseas">
            <th>
                <label class="jpn-txt-version">郵便番号</label>
                <label class="eng-txt-version">Postal Code</label>
            </th>
            <td>
            	<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_2_overseas_postal_code)}" id="f_change_emergency_contact_2_overseas_postal_code" name="f_change_emergency_contact_2_overseas_postal_code" 
							placeholder="" class="input_text_100" style="height:20px;" size="38">
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
            	<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_2_overseas_country)}" id="f_change_emergency_contact_2_overseas_country" name="f_change_emergency_contact_2_overseas_country" 
							placeholder="" class="input_text_100" style="height:20px;" size="38">
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
            	<input type="text" value="${f:h(savedFormData.f_change_emergency_contact_2_address)}" id="f_change_emergency_contact_2_address" name="f_change_emergency_contact_2_address" 
							placeholder="" class="input_text_100" style="height:20px;" size="38">
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
					<textarea rows="5" name="f_remark" style="width:100%; max-width:100%;" class="input_text_100">${f:h(savedFormData.f_remark)}</textarea>
				</td>
			</tr>
		</tbody>
	</table>

</workflow:workflowOpenPage>

	<header class="imui-chapter-title" id="name_attach_header">
	    <h2>添付ファイル<i class="eng-txt-version"><br>Attachment</i></h2>
	</header>
	
	<table class="imui-form" id="name_attach_part" style="table-layout:fixed; width:100%;">
		<colgroup>
			<col style="width:300px;">  
			<col style=""> 
		</colgroup>
		
		<tbody>
			<tr class="t_resident_register_without_my_number">
				<th>
					<label class="jpn-txt-version imui-required">住民票（マイナンバー記載なし）</label>
					<label class="eng-txt-version">Resident Registration (without My Number)</label>
				</th>
				<td>
					<imui:fileUpload id="f_upload_file_resident_registration_without_mynumber" name="f_upload_file_resident_registration_without_mynumber"
					enableDelete="true" uniqueFileName="true" storeTo="personal_info_change/"
					onSuccess="callbackSuccess_resident_registration_without_mynumber" 
					onError="callbackError_resident_registration_without_mynumber" 
					onRemove="callbackRemove_resident_registration_without_mynumber" />
					
					<div class="error_message_resident_registration_without_mynumber"></div>
				</td>
			</tr>
			<tr class="t_resident_register_with_my_number">
				<th>
					<label class="jpn-txt-version imui-required">住民票（マイナンバー記載あり）</label>
					<label class="eng-txt-version">Resident Registration (with My Number)</label>
				</th>
				<td>
					<imui:fileUpload id="f_upload_file_resident_registration_with_mynumber" name="f_upload_file_resident_registration_with_mynumber"
					enableDelete="true" uniqueFileName="true" storeTo="personal_info_change/"
					onSuccess="callbackSuccess_resident_registration_with_mynumber" 
					onError="callbackError_resident_registration_with_mynumber" 
					onRemove="callbackRemove_resident_registration_with_mynumber" />
					
					<div class="error_message_resident_registration_with_mynumber"></div>
				</td>
			</tr>
		</tbody>
	</table>


<imart:decision case="3" value="${f:h(workflowRequestForm.imwPageType)}">
	<header class="imui-chapter-title">
		<h2>アップロード済みファイル<br><i class="eng-txt-version">Uploaded Files</i></h2>
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
							<col style="width:250px;">
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
									<button type="button" class="delete-btn f_delete_file" name="${item_file.file_real_name}">
									    <i class="fa-solid fa-trash-can"></i> 削除
									</button>
									
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
							<col style="width:250px;">                       
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
										<button type="button" class="delete-btn f_delete_file" name="${item_file.file_real_name}">
										    <i class="fa-solid fa-trash-can"></i> 削除
										</button>
										
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
	
</imart:decision>

</div>

<div class="imui-operation-parts">
    <imart:decision case="0" value="${f:h(workflowRequestForm.imwPageType)}">
        <input type="button" value="次へ" id="openPage" name="openPage" class="imui-large-button"
               escapeXml="true" escapeJs="false" />
    </imart:decision>

    <imart:decision case="3" value="${f:h(workflowRequestForm.imwPageType)}">
        <input type="button" value="次へ" id="openPage" name="openPage" class="imui-large-button"
               escapeXml="true" escapeJs="false" />
    </imart:decision>
</div>

<form name="backForm" id="backForm" method="POST" action="${f:h(workflowRequestForm.imwCallOriginalPagePath)}">
    <input type="hidden" name="imwCallOriginalParams" value="${f:h(workflowRequestForm.imwCallOriginalParams)}" />
</form>

<script>

</script>