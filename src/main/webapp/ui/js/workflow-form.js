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
        .prop("disabled", true)
        .removeClass("imui-validation-error");
      $('input[name="' + subRadioName + '"]')
        .closest("td")
        .find(".error_message")
        .empty();
    }
  }
  $('input[name="' + parentRadioName + '"]').on("change", toggle);
  toggle();
}

// Toggle depreciation check field
function toggleDepreciation() {
  var category = $('input[name="f_purchase_category"]:checked').val();

  if (category === "non_asset") {
    $("#f_start_using_date, #f_deprec_month")
      .prop("disabled", true)
      .val("")
      .removeClass("imui-validation-error")
      .siblings(".error_message")
      .empty();

    $("#f_start_using_date, #f_deprec_month").closest("tr").hide();
  } else {
    $("#f_start_using_date, #f_deprec_month").closest("tr").show();
    $("#f_start_using_date, #f_deprec_month").prop("disabled", false);
  }
}

// Setup multiple user input fields based on node id
function setupMultiuserInput(nodeId) {

  if (nodeId === "node_psd") {
    $('input[name="f_psd_area"]')
      .on("change", togglePsdField)
      .trigger("change");

    $('input[name="f_psd_process"]')
      .on("change", toggleDicReason)
      .trigger("change");

  } else if (nodeId === "node_legal") {
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
      '<td style="text-align: center;" class="table-action-col">' +
      '<button type="button" class="imui-button btn-delete-row">Delete</button>' +
      "</td>" +
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

    var $allSections = $("#section-pl, #section-asset, #section-estimated");
    $allSections.find(".error_message").empty();
    $allSections
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
      $(
        "#f_budget_pl_impact, #f_budget_pl_month, #f_pl_impact, #f_pl_month",
      ).val("");
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
      $("#f_asset_number, #f_book_value").val("");
    }

    if (checkedValues.includes("estimated")) {
      $("#section-estimated").show();

      rules.f_total_payment_amount = {
        required: function () {
          return $("#payment_detail_table > tbody tr.payment-row").length === 0;
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

function formatNumberText() {
	$("#f_total_amount, #f_total_payment_amount, .payment-amount").each(
    function () {
      var val = $(this).text();
      if (val) {
        var num = parseFloat(val.replace(/[^0-9.]/g, "")) || 0;
        if (num) {
          $(this).text(
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

// Postal Code Search API Handler
function setupPostcodeSearch() {
  $("#btn_search_postcode").on("click", function () {
	  
    var rawPostalCode = $("#f_postal_code_search").val();
    
    if (!rawPostalCode) {
      $("#postcode_search_msg").css("color", "#dc3545").text("Please enter a postal code.");
      setTimeout(function () {
        $("#postcode_search_msg").text("");
      }, 3000);
      return;
    }

    $("#postcode_search_msg").css("color", "#0066cc").text("Searching...");

    $.ajax({
      url: "practice5_bintang/getPostData",
      type: "GET",
      data: { 
        postCode: rawPostalCode 
      },
      success: function (response) {
        if (typeof response === "string") {
          response = JSON.parse(response);
        }

        if (response && response.results && response.results.length > 0) {
          var addr = response.results[0];
          var fullAddress = (addr.address1 || "") + (addr.address2 || "") + (addr.address3 || "");

          var currentVal = $("#f_counter_party").val();
          if (currentVal && currentVal.trim() !== "") {
            $("#f_counter_party").val(currentVal + " (" + fullAddress + ")");
          } else {
            $("#f_counter_party").val(fullAddress);
          }
          
          $("#f_counter_party").removeClass("imui-validation-error").valid();
          $("#f_counter_party").closest("td").find(".error_message").empty();

          $("#postcode_search_msg").css("color", "#28a745").text("✓ Address populated!");
          setTimeout(function () {
            $("#postcode_search_msg").text("");
          }, 3000);

        } else {
          $("#postcode_search_msg").css("color", "#dc3545").text("Address not found.");
          setTimeout(function () {
              $("#postcode_search_msg").text("");
            }, 3000);
          alert(response && response.message ? response.message : "No address found for postal code: " + rawPostalCode);
        }
      },
      error: function () {
        alert("Failed to connect to postal code search API.");
      }
    });
  });

  // Trigger search on pressing Enter in the postal code search input
  $("#f_postal_code_search").on("keydown", function (e) {
    if (e.which === 13 || e.keyCode === 13) {
      e.preventDefault();
      $("#btn_search_postcode").trigger("click");
    }
  });
}

function setupDateCrossValidation() {
  $("#f_effective_from, #f_effective_to").on("change", function () {
    $("#f_effective_from, #f_effective_to").valid();
  });

  $("#f_estimated_delivery_from, #f_estimated_delivery_to").on("change", function () {
    $("#f_estimated_delivery_from, #f_estimated_delivery_to").valid();
  });
}

