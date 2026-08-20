// Custom Validator Methods & Messages Setup
$.validator.addMethod("noSpace", function (value, element) {
  return this.optional(element) || value.trim().length > 0;
});

// ensureFieldExists, Use to check Uploaded File
$.validator.addMethod("ensureFieldExists", function (value, element, param) {
  var generatedCount = $(param).find(".f_upload_file_id").length;
  return generatedCount > 0;
});

// specialNumberOnly, use regex to check
$.validator.addMethod("specialNumberOnly", function (value, element) {
  return this.optional(element) || /^\d+$/.test(value);
});

// dateFormat
$.validator.addMethod("dateFormat", function (value, element) {
  if (this.optional(element)) {
    return true;
  }
  var regex = /^\d{4}\/\d{2}\/\d{2}$/;
  return regex.test(value);
});

// dateValid
$.validator.addMethod("dateValid", function (value, element) {
  if (this.optional(element)) {
    return true;
  }
  var parts = value.split("/");
  var year = parseInt(parts[0], 10);
  var month = parseInt(parts[1], 10) - 1;
  var day = parseInt(parts[2], 10);

  var date = new Date(year, month, day);
  return (
    date.getFullYear() === year &&
    date.getMonth() === month &&
    date.getDate() === day
  );
});

// startDateLessThan
$.validator.addMethod("startDateLessThan", function (value, element, params) {
  if (this.optional(element)) {
    return true;
  }
  var endDateValue = $(params).val();
  if (!endDateValue) return true;

  var startDate = new Date(value.replace(/\//g, "-"));
  var endDate = new Date(endDateValue.replace(/\//g, "-"));
  return startDate <= endDate;
});

// endDateGreaterThan
$.validator.addMethod("endDateGreaterThan", function (value, element, params) {
  if (this.optional(element)) {
    return true;
  }
  var startDateValue = $(params).val();
  if (!startDateValue) return true;

  var startDate = new Date(startDateValue.replace(/\//g, "-"));
  var endDate = new Date(value.replace(/\//g, "-"));
  return endDate >= startDate;
});

// noFullWidth
$.validator.addMethod("noFullWidth", function (value, element) {
  if (this.optional(element)) {
    return true;
  }
  var fullWidthRegex =
    /[\uFF01-\uFF60\uFFE0-\uFFEE\u3040-\u309F\u30A0-\u30FF\u4E00-\u9FAF\u3000-\u303F\uFF61-\uFF9F]/;
  return !fullWidthRegex.test(value);
});

// Handle Katakana
$.validator.addMethod("katakanaOnly", function (value, element) {
  if (this.optional(element)) {
    return true;
  }
  var katakanaRegex = /^[\u30A0-\u30FF\u3000\s]+$/;
  return katakanaRegex.test(value);
});

// Add the custom method 'noHyphens'
$.validator.addMethod("noHyphen", function (value, element) {
  return this.optional(element) || value.indexOf("-") === -1;
});

// English Messages Setup
var message_required = "Please enter this item.";
var message_noFullWidth = "Please enter half-width numbers.";
var message_specialNumberOnly = "Please enter numbers only.";
var message_dateFormat = "Please enter a valid date in yyyy/MM/dd format.";
var message_dateValid = "Please enter a valid calendar date.";
var message_startDateLessThan = "Start date cannot be after end date.";
var message_endDateGreaterThan = "End date cannot be before start date.";
var message_ensureFieldExists = "Please upload a file.";
var message_katakanaOnly = "Please enter in Katakana.";
var message_noHyphen = "Please enter without hyphens.";
var message_minlength = "Postal code must be {0} digits.";
var message_maxlength = "Postal code must be {0} digits.";

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
$.validator.messages.noHyphen = message_noHyphen;
$.validator.messages.minlength = message_minlength;
$.validator.messages.maxlength = message_maxlength;

var rules = {
  f_counter_party: {
    required: true,
  },
  f_currency: {
    required: true,
  },
  f_total_amount: {
    required: true,
    number: true,
  },
  f_agreement_status: {
    required: true,
  },
  f_total_duration: {
    required: function () {
      return $('input[name="f_agreement_status"]:checked').val() === "amendment";
    },
  },
  f_auto_extension: {
    required: true,
  },
  f_po_required: {
    required: true,
  },
  f_agreement_title: {
    required: true,
  },
  f_effective_from: {
    required: true,
    dateFormat: true,
    dateValid: true,
    startDateLessThan: "#f_effective_to",
  },
  f_effective_to: {
    required: true,
    dateFormat: true,
    dateValid: true,
    endDateGreaterThan: "#f_effective_from",
  },
  f_company_relation: {
    required: true,
  },
  f_estimated_delivery_from: {
    required: true,
    dateFormat: true,
    dateValid: true,
    startDateLessThan: "#f_estimated_delivery_to",
  },
  f_estimated_delivery_to: {
    required: true,
    dateFormat: true,
    dateValid: true,
    endDateGreaterThan: "#f_estimated_delivery_from",
  },
  f_purchase_category: {
    required: true,
  },
  f_start_using_date: {
    required: function () {
      return $('input[name="f_purchase_category"]:checked').val() !== "non_asset";
    },
    dateFormat: true,
    dateValid: true,
  },
  f_deprec_month: {
    required: function () {
      return $('input[name="f_purchase_category"]:checked').val() !== "non_asset";
    },
  },
  f_multidata: {
    required: true,
  },
  f_agreement_classification: {
    required: true,
  },
  f_pd_sub_condition: {
    required: function () {
      return (
        $('input[name="f_agreement_classification"]:checked').val() === "pd"
      );
    },
  },
  f_ec_approval: {
    required: true,
  },
  f_ec_sub_condition: {
    required: function () {
      return $('input[name="f_ec_approval"]:checked').val() === "yes";
    },
  },
  f_psd_area: {
    required: true,
  },
  f_psd_process: {
    required: true,
  },
  f_dic_reason: {
    required: function () {
      return $('input[name="f_psd_process"]:checked').val() === "dic";
    },
  },
  f_dd_process: {
    required: true,
  },
  f_anti_bribery: {
    required: true,
  },
  f_audit_rights: {
    required: true,
  },
  f_legal_agreement_number: {
    required: true,
  },
  f_legal_agreement_date: {
    required: true,
    dateFormat: true,
    dateValid: true,
  },
  f_attachment_anchor: {
    ensureFieldExists: ".file_attachment",
  },
};

var messages = {};
var groups = {};

function validateWorkflowForm() {
  if ($.validator && $.validator.methods && $.validator.methods.id) {
    delete $.validator.methods.id;
  }

  $(".payment-row").each(function (index) {
    var newSerial = index + 1;
    groups["f_brand_type_" + newSerial + "_group"] =
      "f_brand_" + newSerial + " f_type_" + newSerial;
  });

  $.validator.addClassRules("f_brand", {
    required: true,
  });

  $.validator.addClassRules("f_type", {
    required: true,
  });

  $.validator.addClassRules("f_payment_amount", {
    required: true,
  });

  $.validator.addClassRules("f_payment_date", {
    required: true,
    dateFormat: true,
    dateValid: true,
  });

  $.validator.addClassRules("f_category", {
    required: true,
  });

  $.validator.addClassRules("f_recurring", {
    required: true,
  });

  $.validator.addClassRules("f_paid_by", {
    required: true,
  });

  $.validator.addClassRules("f_multidata", {
    required: true,
  });

  $(".error_message").empty();
  $(".error_message_upload").empty();
  $(".select2-selection").removeClass("imui-validation-error");

  var validator = $("#workflowOpenPageForm").validate({
    groups: groups,
    rules: rules,
    messages: messages,

    onkeyup: function (element, event) {
      if (event.which === 9 && this.elementValue(element) === "") return;
      $(element).valid();
    },

    onfocusout: function (element) {
      $(element).valid();
    },

    onclick: function (element) {
      $(element).valid();
    },

    highlight: function (element, errorClass, validClass) {
      var $element = $(element);

      if (
        $element.attr("type") === "checkbox" ||
        $element.attr("type") === "radio"
      ) {
        $('input[name="' + $element.attr("name") + '"]').addClass(
          "imui-validation-error",
        );
      } else if (
        $element.hasClass("select2") ||
        $element.hasClass("select2-hidden-accessible")
      ) {
        $element
          .parents("td")
          .find(".select2-selection")
          .addClass("imui-validation-error");
      } else if (!$element.hasClass("f_attachment_anchor")) {
        $element.addClass("imui-validation-error");
      }
    },

    unhighlight: function (element, errorClass, validClass) {
      var $element = $(element);

      if (
        $element.attr("type") === "checkbox" ||
        $element.attr("type") === "radio"
      ) {
        $('input[name="' + $element.attr("name") + '"]').removeClass(
          "imui-validation-error",
        );
      } else if (
        $element.hasClass("select2") ||
        $element.hasClass("select2-hidden-accessible")
      ) {
        $element
          .parents("td")
          .find(".select2-selection")
          .removeClass("imui-validation-error");
      } else {
        $element.removeClass("imui-validation-error");
      }
    },

    errorPlacement: function (error, element) {
      var $element = $(element);
      var error_message = error.get(0);

      if ($element.hasClass("f_attachment_anchor")) {
        $(".error_message_upload").html(error_message);
      } else {
        $element.parents("td").find(".error_message").html(error_message);
      }
    },
  });

  return validator.form();
}
