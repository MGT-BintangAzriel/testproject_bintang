var isApplyClicked = false;

// File Attachment Callbacks
function callbackSuccess(e, data) {
  var file = data.files[0];
  var fileName = file.name;
  var fileSize = file.size;
  var fileExtension = file.type;

  var receiveFile = data.result[0];
  var receiveFileName = receiveFile.name;
  var receivePhysicalFileName = receiveFile.physicalName;
  var receiveFileSize = receiveFile.size;

  var fileHtml = `
    <div class="${receivePhysicalFileName}">
      <input type="hidden" value="0" class="f_upload_file_id" name="f_upload_file_id">
      <input type="hidden" value="${receiveFileName}" class="f_upload_file_name" name="f_upload_file_name">
      <input type="hidden" value="${receivePhysicalFileName}" class="f_upload_file_real_name" name="f_upload_file_real_name">
      <input type="hidden" value="${fileSize}" class="f_upload_file_size" name="f_upload_file_size">
      <input type="hidden" value="${fileExtension}" class="f_upload_file_extension" name="f_upload_file_extension">
      <input type="hidden" value="agreement" class="f_upload_file_type" name="f_upload_file_type">
    </div>
  `;

  $(".file_attachment").prepend(fileHtml);
  validateWorkflowForm();
}

function callbackRemove(e, data) {
  var file = data.response[0];
  var fileName = file.name;
  $("." + fileName).remove();
  validateWorkflowForm();
}

function callbackError(e, data) {
  var file = data.files[0];
}

// Toggle radio sub option
function setupSubOptionToggle(
  parentRadioName,
  activeValue,
  subRadioName,
  defaultSubRadioId,
) {
  function toggle() {
    var selectedVal = $('input[name="' + parentRadioName + '"]:checked').val();
    if (selectedVal === activeValue) {
      $('input[name="' + subRadioName + '"]').prop("disabled", false);
      if (!$('input[name="' + subRadioName + '"]:checked').val()) {
        $("#" + defaultSubRadioId).prop("checked", true);
      }
    } else {
      $('input[name="' + subRadioName + '"]')
        .prop("checked", false)
        .prop("disabled", true);
      $('input[name="' + subRadioName + '"]').removeClass(
        "imui-validation-error",
      );
      $('input[name="' + subRadioName + '"]')
        .closest("td")
        .find("label.imui-validation-error, div.imui-validation-error")
        .remove();
    }
  }
  $('input[name="' + parentRadioName + '"]').on("change", toggle);
  toggle();
}

// Toggle depreciation check field
function toggleDepreciation() {
  var category = $('input[name="f_purchase_category"]:checked').val();
  if (category === "non_asset") {
    $("#f_start_using_date").prop("disabled", true).val("");
    $("#f_deprec_month").prop("disabled", true).val("");

    $("#f_start_using_date, #f_deprec_month").removeClass(
      "imui-validation-error",
    );
    $("#f_start_using_date, #f_deprec_month")
      .siblings(".error_message")
      .empty();

    $("#f_start_using_date").closest("tr").hide();
    $("#f_deprec_month").closest("tr").hide();
  } else {
    $("#f_start_using_date").closest("tr").show();
    $("#f_deprec_month").closest("tr").show();

    $("#f_start_using_date").prop("disabled", false);
    $("#f_deprec_month").prop("disabled", false);
  }
}

// Initialize readonly view of workflow form
function initReadOnlyAgreementForm() {
  // Set text inputs & textareas to readonly
  $(
    '#workflowOpenPageForm input[type="text"], #workflowOpenPageForm textarea',
  ).prop("readonly", true);

  // Disable radios, checkboxes & selects
  $(
    '#workflowOpenPageForm input[type="radio"], #workflowOpenPageForm input[type="checkbox"], #workflowOpenPageForm select',
  ).prop("disabled", true);

  // Pointer-lock radios & checkboxes so they cannot be clicked
  $(
    '#workflowOpenPageForm input[type="radio"], #workflowOpenPageForm input[type="checkbox"]',
  ).on("click.lock", function (e) {
    e.preventDefault();
  });

  // Disable depreciation check if non-asset
  if ($('input[name="f_purchase_category"]:checked').val() === "non_asset") {
    $("#f_start_using_date, #f_deprec_month").prop("disabled", true);
  }

  // Hide action column and buttons and resize payment table
  $(".table-action-col, #btn_add_payment_row").hide();
  $("#payment_detail_table colgroup col.table-action-col").remove();
  $("#payment_detail_table thead th[colspan='8']").attr("colspan", "7");
  var readOnlyWidths = ["4%", "25%", "15%", "14%", "15%", "12%", "15%"];
  $("#payment_detail_table colgroup col").each(function (index) {
      $(this).css("width", readOnlyWidths[index]);
  });

  // Format saved numbers with commas (e.g. 100000000 -> 100,000,000.00)
  $("#f_total_amount, #f_total_payment_amount, .payment-amount").each(
    function () {
      var val = $(this).val();
      if (val) {
        var num = parseFloat(val.replace(/[^0-9.]/g, "")) || 0;
        if (num) {
          $(this).val(
            num.toLocaleString("en-US", {
              minimumFractionDigits: 2,
              maximumFractionDigits: 2,
            }),
          );
        }
      }
    },
  );
}

// Disable multiple user input section fields
function disablePractice8Fields() {
  $(
    'input[name="f_psd_area"], input[name="f_psd_process"], input[name="f_dd_process"], input[name="f_anti_bribery"], input[name="f_audit_rights"]',
  ).prop("disabled", true);
  $("#f_dic_reason, #f_legal_agreement_number, #f_legal_agreement_date").prop(
    "disabled",
    true,
  );
}

// Apply node-based field permissions on Approve screen
function applyNodePermissions(nodeId) {
  // Disable Practice 8 section fields by default
  disablePractice8Fields();

  // Unlock fields based on active Node ID
  if (nodeId === "node_psd") {

    // PSD Officer Node -> Unlock PSD fields
    $('input[name="f_psd_area"], input[name="f_psd_process"]')
      .prop("disabled", false)
      .off("click.lock");

    $("#f_dic_reason").prop("readonly", false);

    $('input[name="f_psd_area"]')
      .on("change", togglePsdField)
      .trigger("change");

    $('input[name="f_psd_process"]')
      .on("change", toggleDicReason)
      .trigger("change");

  } else if (nodeId === "node_cco") {
    // CCO Compliance Node -> Unlock Compliance fields
    $(
      'input[name="f_dd_process"], input[name="f_anti_bribery"], input[name="f_audit_rights"]',
    )
      .prop("disabled", false)
      .off("click.lock");

  } else if (nodeId === "node_legal") {
    // Legal Officer Node -> Unlock Legal fields & initialize calendar
    $("#f_legal_agreement_number, #f_legal_agreement_date")
      .prop("disabled", false)
      .prop("readonly", false);

    $("#f_legal_agreement_date")
      .removeClass("hasDatepicker")
      .imuiCalendar({
        changeMonth: true,
        changeYear: true,
        onSelect: function () {
          $(this).trigger("change");
        },
      });

    $(".ui-datepicker-trigger").hide();
  }
}

// Setup dynamic payment table
function setupDynamicPaymentTable() {
  function calculateTotalPaymentAmount() {
    var totalSum = 0;
    $("#payment_detail_table .payment-amount").each(function () {
      var rawValue = $(this).val();
      if (rawValue) {
        var num = parseFloat(rawValue.replace(/[^0-9.]/g, "")) || 0;
        totalSum += num;
      }
    });

    if (totalSum > 0) {
      $("#f_total_payment_amount").val(
        totalSum.toLocaleString("en-US", {
          minimumFractionDigits: 2,
          maximumFractionDigits: 2,
        }),
      );
    } else {
      $("#f_total_payment_amount").val("");
    }
  }

  $("#payment_detail_table").on("input change", ".payment-amount", function () {
    calculateTotalPaymentAmount();
  });

  $("#payment_detail_table").on("change", ".payment-amount", function () {
    var input = $(this);
    var val = input.val();
    if (val) {
      var num = parseFloat(val.replace(/[^0-9.]/g, "")) || 0;
      if (num) {
        input.val(
          num.toLocaleString("en-US", {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2,
          }),
        );
      } else {
        input.val("");
      }
    }
  });

  $("#btn_add_payment_row").click(function () {
    var rowIdx = $("#payment_detail_table tbody tr.payment-row").length + 1;
    var rowTemplate =
      '<tr class="payment-row">' +
      '<td style="text-align: center;" class="row-seq-no">' +
      rowIdx +
      "</td>" +
      '<td style="text-align: center;">' +
      '<div style="display: flex; align-items: center; gap: 6px; justify-content: center; white-space: nowrap;">' +
      '<input type="text" name="f_brand_' +
      rowIdx +
      '" class="f_brand" style="width: 45%; height: 20px;" placeholder="Brand">' +
      '<input type="text" name="f_type_' +
      rowIdx +
      '" class="f_type" style="width: 45%; height: 20px;" placeholder="Type">' +
      "</div>" +
      '<div class="error_message"></div>' +
      "</td>" +
      '<td><input type="text" name="f_payment_amount_' +
      rowIdx +
      '" class="f_payment_amount payment-amount" style="width: 85%; height: 20px;"><div class="error_message"></div></td>' +
      '<td><input type="text" name="f_payment_date_' +
      rowIdx +
      '" class="f_payment_date payment-date imuiCalendar" style="width: 85%; height: 20px;"><div class="error_message"></div></td>' +
      "<td>" +
      '<select name="f_category_' +
      rowIdx +
      '" class="select2 f_category" style="width: 85%; height: 24px;">' +
      '<option value="">&nbsp;</option>' +
      '<option value="1">Equipment</option>' +
      '<option value="2">Software</option>' +
      '<option value="3">Utility</option>' +
      '<option value="4">Service</option>' +
      '<option value="5">Other</option>' +
      "</select>" +
      '<div class="error_message"></div>' +
      "</td>" +
      "<td>" +
      '<table style="margin: 0px;">' +
      "<tr>" +
      '<td style="border: none; padding: 0px;">' +
      '<input type="radio" name="f_recurring_' +
      rowIdx +
      '" value="yes" class="f_recurring" id="f_recurring_yes_' +
      rowIdx +
      '">' +
      "</td>" +
      '<td style="border: none; padding: 0px;">' +
      '<label for="f_recurring_yes_' +
      rowIdx +
      '">Yes</label>' +
      "</td>" +
      "</tr>" +
      "<tr>" +
      '<td style="border: none; padding: 0px;">' +
      '<input type="radio" name="f_recurring_' +
      rowIdx +
      '" value="no" class="f_recurring" id="f_recurring_no_' +
      rowIdx +
      '">' +
      "</td>" +
      '<td style="border: none; padding: 0px;">' +
      '<label for="f_recurring_no_' +
      rowIdx +
      '">No</label>' +
      "</td>" +
      "</tr>" +
      "</table>" +
      '<div class="error_message"></div>' +
      "</td>" +
      "<td>" +
      '<label style="display: block; margin-bottom: 4px;"><input type="checkbox" name="f_paid_by_' +
      rowIdx +
      '" class="f_paid_by" value="card"> Card</label>' +
      '<label style="display: block;"><input type="checkbox" name="f_paid_by_' +
      rowIdx +
      '" class="f_paid_by" value="cash"> Cash</label>' +
      '<div class="error_message"></div>' +
      "</td>" +
      '<td style="text-align: center;" class="table-action-col">' +
      '<button type="button" class="imui-button btn-delete-row">Delete</button>' +
      "</td>" +
      "</tr>";

    var $newRow = $(rowTemplate);
    $("#payment_detail_table > tbody").append($newRow);

    $newRow
      .find(".imuiCalendar")
      .removeClass("hasDatepicker")
      .imuiCalendar({
        changeMonth: true,
        changeYear: true,
        onSelect: function () {
          $(this).trigger("change");
        },
      });
    $(".ui-datepicker-trigger").hide();

    $newRow.find(".select2").select2();
    $newRow.find(".select2").on("change", function () {
      $(this).valid();
    });
  });

  $("#payment_detail_table").on("click", ".btn-delete-row", function () {
    var totalRows = $("#payment_detail_table > tbody tr.payment-row").length;

    if (totalRows > 1) {
      $(this).closest("tr").remove();
      refreshSequenceNumbers();
    }

    calculateTotalPaymentAmount();
  });

  calculateTotalPaymentAmount();
}

function togglePsdField() {
  var isPsdArea = $('input[name="f_psd_area"]:checked').val() === "psd";

  if (!isPsdArea) {
    $('input[name="f_psd_process"]')
      .prop("checked", false)
      .prop("disabled", true)
      .removeClass("imui-validation-error")
      .closest("td")
      .find(".error_message")
      .empty();

    $("#f_dic_reason")
      .prop("disabled", true)
      .val("")
      .removeClass("imui-validation-error")
      .closest("td")
      .find(".error_message")
      .empty();
  } else {
    $('input[name="f_psd_process"]').prop("disabled", false);
    
    toggleDicReason();
  }
}

function toggleDicReason() {
  var isPsdArea = $('input[name="f_psd_area"]:checked').val() === "psd";
  var isDicProcess = $('input[name="f_psd_process"]:checked').val() === "dic";

  if (isPsdArea && isDicProcess) {
    $("#f_dic_reason")
      .prop("disabled", false)
      .prop("readonly", false);
  } else {
    $("#f_dic_reason")
      .prop("disabled", true)
      .val("")
      .removeClass("imui-validation-error")
      .closest("td")
      .find(".error_message")
      .empty();
  }
}

function refreshSequenceNumbers() {
  $(".payment-row").each(function (index) {
    var newSerial = index + 1;

    $(this).find(".row-seq-no").text(newSerial);

    $(this)
      .find("input[name^='f_brand_']")
      .attr("name", "f_brand_" + newSerial);
    $(this)
      .find("input[name^='f_type_']")
      .attr("name", "f_type_" + newSerial);
    $(this)
      .find("input[name^='f_payment_amount_']")
      .attr("name", "f_payment_amount_" + newSerial);
    $(this)
      .find("input[name^='f_payment_date_']")
      .attr("name", "f_payment_date_" + newSerial);
    $(this)
      .find("select[name^='f_category_']")
      .attr("name", "f_category_" + newSerial);
    $(this)
      .find("input[name^='f_recurring_']")
      .attr("name", "f_recurring_" + newSerial);

    // Update radio IDs and label for attributes
    $(this)
      .find("input[id^='f_recurring_yes_']")
      .attr("id", "f_recurring_yes_" + newSerial);
    $(this)
      .find("label[for^='f_recurring_yes_']")
      .attr("for", "f_recurring_yes_" + newSerial);
    $(this)
      .find("input[id^='f_recurring_no_']")
      .attr("id", "f_recurring_no_" + newSerial);
    $(this)
      .find("label[for^='f_recurring_no_']")
      .attr("for", "f_recurring_no_" + newSerial);

    $(this)
      .find("input[name^='f_paid_by_']")
      .attr("name", "f_paid_by_" + newSerial);
  });
}

// Setup validation rules for multidata checkbox
function setupMultiDataToggle() {
  $('input[name="f_multidata"]').on("change", function () {
    $("#section-pl").hide();
    $("#section-asset").hide();
    $("#section-estimated").hide();

    // For global rules
    delete rules.f_budget_pl_impact;
    delete rules.f_budget_pl_month;
    delete rules.f_pl_impact;
    delete rules.f_pl_month;
    delete rules.f_asset_number;
    delete rules.f_book_value;
    delete rules.f_total_payment_amount;

    // For current active rules
    if (isApplyClicked) {
      $(
        "#f_budget_pl_impact, #f_budget_pl_month, #f_pl_impact, #f_pl_month, #f_asset_number, #f_book_value, #f_total_payment_amount",
      ).rules("remove", "required");
    }

    $("#section-pl, #section-asset, #section-estimated")
      .find(".error_message")
      .empty();
    $("#section-pl, #section-asset, #section-estimated")
      .find(".imui-validation-error")
      .removeClass("imui-validation-error");

    var checkedValues = $('input[name="f_multidata"]:checked')
      .map(function () {
        return $(this).val();
      })
      .get();

    if (checkedValues.includes("pl")) {
      $("#section-pl").show();

      rules.f_budget_pl_impact = { required: true };
      rules.f_budget_pl_month = { required: true };
      rules.f_pl_impact = { required: true };
      rules.f_pl_month = { required: true };

      if (isApplyClicked) {
        $("#f_budget_pl_impact").rules("add", { required: true });
        $("#f_budget_pl_month").rules("add", { required: true });
        $("#f_pl_impact").rules("add", { required: true });
        $("#f_pl_month").rules("add", { required: true });
      }
    } else {
    	    $("#f_budget_pl_impact").val("");
          $("#f_budget_pl_month").val("");
          $("#f_pl_impact").val("");
          $("#f_pl_month").val("");
    }

    if (checkedValues.includes("asset")) {
      $("#section-asset").show();

      rules.f_asset_number = { required: true };
      rules.f_book_value = { required: true };

      if (isApplyClicked) {
        $("#f_asset_number").rules("add", { required: true });
        $("#f_book_value").rules("add", { required: true });
      }
    } else {
    	  $("#f_asset_number").val("");
    	  $("#f_book_value").val("");
    }

    if (checkedValues.includes("estimated")) {
      $("#section-estimated").show();

      rules.f_total_payment_amount = {
        required: function () {
          return (
            $("#payment_detail_table > tbody tr.payment-row").length === 0
          );
        },
      };
    } else {
      resetPaymentTable();
    }
  });

  $("input[name='f_multidata']").trigger("change");
}

// Reset table payment if estimated is not checked
function resetPaymentTable() {
  $("#payment_detail_table > tbody tr.payment-row:gt(0)").remove();

  var $firstRow = $("#payment_detail_table > tbody tr.payment-row:first");
  $firstRow.find("input[type='text']").val("");
  $firstRow
    .find("input[type='radio'], input[type='checkbox']")
    .prop("checked", false);
  $firstRow.find(".select2").val("").trigger("change");
  $firstRow.find(".error_message").empty();
  $firstRow.find(".imui-validation-error").removeClass("imui-validation-error");

  $("#f_total_payment_amount").val("");
}
