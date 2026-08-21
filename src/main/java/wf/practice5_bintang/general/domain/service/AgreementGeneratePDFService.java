package wf.practice5_bintang.general.domain.service;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import io.woo.htmltopdf.HtmlToPdf;
import io.woo.htmltopdf.HtmlToPdfObject;

import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.workflow.application.history.MatterHistory;
import jp.co.intra_mart.foundation.workflow.application.model.MatterHistoryResultModel;

import wf.common.constant.WorkflowCommonConstants;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoModel;
import wf.practice5_bintang.general.domain.model.AgreementPaymentDetailModel;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderInfoRepository;
import wf.practice5_bintang.general.domain.repository.AgreementPaymentDetailRepository;

public class AgreementGeneratePDFService {

	public String convertPaymentCategory(String category) {
		if ("1".equals(category)) {
			return "Equipment";
		} else if ("2".equals(category)) {
			return "Software";
		} else if ("3".equals(category)) {
			return "Utility";
		} else if ("4".equals(category)) {
			return "Service";
		} else if ("5".equals(category)) {
			return "Other";
		}
		return category;
	}

	public String convertPaidBy(String paidBy) {
		if (paidBy == null || paidBy.trim().isEmpty()) {
			return "";
		}
		String res = "";
		if (paidBy.contains("card"))
			res += "Card ";
		if (paidBy.contains("cash"))
			res += "Cash ";
		return res;
	}

	public String formatAmount(String amount) {
		if (amount == null || amount.trim().isEmpty()) {
			return "";
		}
		try {
			double val = Double.parseDouble(amount.replaceAll("[^0-9.]", ""));
			return String.format("%,.2f", val);
		} catch (Exception e) {
			return amount;
		}
	}

	public String convertYesNo(String val) {
		if ("yes".equalsIgnoreCase(val)) {
			return "Yes";
		} else if ("no".equalsIgnoreCase(val)) {
			return "No";
		}
		return val;
	}

	public String convertAgreementStatus(String status) {
		if ("one_time".equals(status)) {
			return "One Time / New";
		} else if ("amendment".equals(status)) {
			return "Amendment/Extension/Renewal";
		} else if ("umbrella".equals(status)) {
			return "Umbrella Agreement";
		}
		return status;
	}

	public String convertTotalDuration(String duration) {
		if ("more_than_1_year".equals(duration)) {
			return "More than 1 year";
		} else if ("up_to_1_year".equals(duration)) {
			return "Up to 1 Year";
		}
		return duration;
	}

	public String convertCompanyRelation(String relation) {
		if ("related_parties".equals(relation)) {
			return "Related Parties [Shareholders, Subsidiary, Affiliates]";
		} else if ("non_related_parties".equals(relation)) {
			return "Non Related Parties";
		}
		return relation;
	}

	public String convertPurchaseCategory(String category) {
		if ("tangible_asset".equals(category)) {
			return "Tangible Asset";
		} else if ("intangible_asset".equals(category)) {
			return "Intangible Asset";
		} else if ("non_asset".equals(category)) {
			return "Non-Asset";
		}
		return category;
	}

	public String convertAgreementClassification(String classification) {
		if ("pd".equals(classification)) {
			return "PD Approval";
		} else if ("dic_director_approval".equals(classification)) {
			return "DIC Director Approval";
		}
		return classification;
	}

	public String convertPdSubCondition(String sub) {
		if ("pd_more_than_1_billion".equals(sub)) {
			return "Agreement with amount is equal or more than 1 billion";
		} else if ("pd_more_than_12_months".equals(sub)) {
			return "Period is equal or more than 12 months";
		} else if ("pd_specific_party".equals(sub)) {
			return "Agreement related to specific party";
		} else if ("pd_special_issue".equals(sub)) {
			return "Special issue (New project/issue > 50M, not in budget plan)";
		} else if ("pd_direct_procurement".equals(sub)) {
			return "Direct Procurement (Emergency or Specific Goods)";
		} else if ("pd_agreement_not_more_than_12_months".equals(sub)) {
			return "Agreement not more than 12 months";
		}
		return sub;
	}

	public String convertEcSubCondition(String sub) {
		if ("ec_amount_equal_more_than_1_billion".equals(sub)) {
			return "Amount is equal or more than 1 billion";
		} else if ("ec_period_equal_more_than_12_months".equals(sub)) {
			return "Period is equal or more than 12 months";
		} else if ("ec_escalate_issue_to_ec".equals(sub)) {
			return "Director believes it is necessary to escalate the issue to EC";
		}
		return sub;
	}

	public String convertPsdArea(String area) {
		if ("psd".equals(area)) {
			return "PSD";
		} else if ("non_psd".equals(area)) {
			return "Non-PSD";
		}
		return area;
	}

	public String convertPsdProcess(String process) {
		if ("psd".equals(process)) {
			return "PSD (Pitching result attached)";
		} else if ("dic".equals(process)) {
			return "DIC";
		}
		return process;
	}

	public String createPDF(String systemMatterId) throws Exception {
		try {
			AgreementHeaderInfoRepository agreementHeaderInfoDb = new AgreementHeaderInfoRepository();
			AgreementHeaderInfoModel model = agreementHeaderInfoDb.selectHeaderInfo(systemMatterId, WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID).iterator().next();

			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>")
			    .append("<html>")
			    .append("<head>")
			    .append("<meta charset='UTF-8'>")
			    .append("<style type='text/css'>")
			    .append("body { font-family: Arial; padding: 20px; color: #333; }")
			    .append("h1 { text-align: center; font-size: 18pt; margin-bottom: 20px; border-bottom: 2px solid #333; padding-bottom: 8px; }")
			    .append("h2 { font-size: 11pt; margin-top: 18px; margin-bottom: 8px; background: #eef2f7; padding: 5px 8px; border-left: 4px solid #1a56a0; }")
			    .append("h3 { font-size: 10pt; margin-top: 12px; margin-bottom: 6px; color: #1a56a0; font-weight: bold; }")
			    .append("table { width: 100%; border-collapse: collapse; margin-bottom: 12px; table-layout: fixed; }")
			    .append("th, td { border: 1px solid #999; padding: 6px 8px; font-size: 9.5pt; text-align: left; word-break: break-word; }")
			    .append("th { background-color: #f7f7f7; width: 28%; font-weight: bold; }")
			    .append("</style>")
			    .append("</head>")
			    .append("<body>")
			    .append("<h1>Agreement Detail</h1>");

			html.append("<h2>Applicant Information</h2>")
			    .append("<table>");
			addRow2Col(html, "Application Number", model.getApplication_number(), "Application Date", model.getApplication_date());
			addRow2Col(html, "Applicant Number", model.getApplicant_number(), "Department Name", model.getApplicant_department());
			addRow2Col(html, "Applicant Name", model.getApplicant_name(), "Applicant Post", model.getApplicant_post());
			html.append("</table>");

			html.append("<h2>Agreement Detail</h2>")
			    .append("<table>");
			addRow(html, "Counter Party (Vendor name, etc)", model.getCounter_party());
			addRow(html, "Currency", model.getCurrency());

			String formattedTotalAmount = formatAmount(model.getTotal_amount());
			String currencyStr = model.getCurrency();
			addRow(html, "Total Amount (Without Tax)", formattedTotalAmount + " " + currencyStr);

			String statusDisplay = convertAgreementStatus(model.getAgreement_status());
			if ("amendment".equals(model.getAgreement_status())) {
				statusDisplay += " (Duration: " + convertTotalDuration(model.getTotal_duration()) + ")";
			}
			addRow(html, "Agreement Status", statusDisplay);

			addRow(html, "Include auto extension condition", convertYesNo(model.getAuto_extension()));
			addRow(html, "Purchase Order Required", convertYesNo(model.getPo_required()));
			addRow(html, "Title described in Agreement", model.getAgreement_title());
			addRow(html, "Effective Period", model.getEffective_from() + " ~ " + model.getEffective_to());
			addRow(html, "Related / Non Related Company", convertCompanyRelation(model.getCompany_relation()));
			addRow(html, "Estimated Delivery Schedule", model.getEstimated_delivery_from() + " ~ " + model.getEstimated_delivery_to());
			addRow(html, "Agreement Summary (main points only)", model.getAgreement_summary());
			html.append("</table>");

			html.append("<h2>Depreciation Check</h2>")
			    .append("<table>");
			addRow(html, "Purchase Category", convertPurchaseCategory(model.getPurchase_category()));
			if (!"non_asset".equals(model.getPurchase_category())) {
				addRow(html, "Starting Using Date", model.getStart_using_date());
				addRow(html, "Deprec Amount/Month", model.getDeprec_month());
			}
			html.append("</table>");

			String multidata = model.getMultidata();
			html.append("<h2>Multiple Data Selection</h2>")
			    .append("<table>");
			String multiDisplay = "";
			if (multidata.contains("pl"))
				multiDisplay += "[ PL Impact ] ";
			if (multidata.contains("asset"))
				multiDisplay += "[ Asset ] ";
			if (multidata.contains("estimated"))
				multiDisplay += "[ Estimated Schedule ] ";
			addRow(html, "Multiple Data Selection", multiDisplay);
			html.append("</table>");

			if (!multidata.isEmpty()) {
				if (multidata.contains("pl")) {
					html.append("<h2>PL Impact</h2>")
					    .append("<table>");
					addRow(html, "Budget PL Impact to current FY", model.getBudget_pl_impact());
					addRow(html, "Budget PL Month", model.getBudget_pl_month());
					addRow(html, "PL Impact to current FY", model.getPl_impact());
					addRow(html, "PL Month", model.getPl_month());
					html.append("</table>");
				}
				if (multidata.contains("asset")) {
					html.append("<h2>Asset</h2>")
					    .append("<table>");
					addRow(html, "Asset Number", model.getAsset_number());
					addRow(html, "Book Value", model.getBook_value());
					html.append("</table>");
				}
				if (multidata.contains("estimated")) {
					html.append("<h2>Estimated Schedule</h2>")
					    .append("<table>");
					addRow(html, "Total Payment Amount", formatAmount(model.getTotal_payment_amount()));
					html.append("</table>");

					AgreementPaymentDetailRepository paymentRepo = new AgreementPaymentDetailRepository();
					List<AgreementPaymentDetailModel> paymentList = (List<AgreementPaymentDetailModel>) paymentRepo
							.selectPaymentDetail(systemMatterId, WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

					if (paymentList != null && !paymentList.isEmpty()) {
						html.append("<h3>Payment (Total Cash Flow Impact)</h3>")
						    .append("<table>")
						    .append("<thead><tr>")
						    .append("<th style='width: 6%; text-align: center;'>No</th>")
						    .append("<th style='width: 24%; text-align: center;'>Brand and Type</th>")
						    .append("<th style='width: 16%; text-align: center;'>Amount</th>")
						    .append("<th style='width: 14%; text-align: center;'>Date</th>")
						    .append("<th style='width: 14%; text-align: center;'>Category</th>")
						    .append("<th style='width: 12%; text-align: center;'>Recurring</th>")
						    .append("<th style='width: 14%; text-align: center;'>Paid By</th>")
						    .append("</tr></thead><tbody>");

						for (AgreementPaymentDetailModel pay : paymentList) {
							String catName = convertPaymentCategory(pay.getCategory());
							String recName = convertYesNo(pay.getRecurring());
							String paidByName = convertPaidBy(pay.getPaid_by());

							html.append("<tr>")
							    .append("<td style='text-align: center;'>").append(pay.getRow_no()).append("</td>")
							    .append("<td style='text-align: center;'>").append(pay.getBrand()).append(" / ").append(pay.getType()).append("</td>")
							    .append("<td style='text-align: center;'>").append(formatAmount(pay.getPayment_amount())).append("</td>")
							    .append("<td style='text-align: center;'>").append(pay.getPayment_date()).append("</td>")
							    .append("<td style='text-align: center;'>").append(catName).append("</td>")
							    .append("<td style='text-align: center;'>").append(recName).append("</td>")
							    .append("<td>").append(paidByName).append("</td>")
							    .append("</tr>");
						}

						html.append("</tbody></table>");
					}
				}
			}

			html.append("<h2>Agreement Classification</h2>")
			    .append("<table>");
			addRow(html, "Agreement Classification", convertAgreementClassification(model.getAgreement_classification()));
			if ("pd".equals(model.getAgreement_classification()) && model.getPd_sub_condition() != null && !model.getPd_sub_condition().isEmpty()) {
				addRow(html, "PD Approval Sub Condition", convertPdSubCondition(model.getPd_sub_condition()));
			}
			addRow(html, "EC Approval is Required or Not", convertYesNo(model.getEc_approval()));
			if ("yes".equalsIgnoreCase(model.getEc_approval()) && model.getEc_sub_condition() != null && !model.getEc_sub_condition().isEmpty()) {
				addRow(html, "EC Approval Sub Condition", convertEcSubCondition(model.getEc_sub_condition()));
			}
			html.append("</table>");

			html.append("<h2>PSD Check (by UH or DH, PSD)</h2>")
			    .append("<table>");
			addRow(html, "PSD Area or Non-PSD Area", convertPsdArea(model.getPsd_area()));
			addRow(html, "In PSD Area, PSD Process, or DIC Process", convertPsdProcess(model.getPsd_process()));
			if ("dic".equals(model.getPsd_process())) {
				addRow(html, "DIC Reason", model.getDic_reason());
			}
			html.append("</table>");

			html.append("<h2>Compliance Check By CCO</h2>")
			    .append("<table>");
			addRow(html, "D / D Process Required", convertYesNo(model.getDd_process()));
			addRow(html, "Anti Bribery Clause Included", convertYesNo(model.getAnti_bribery()));
			addRow(html, "Audit Rights Included", convertYesNo(model.getAudit_rights()));
			html.append("</table>");

			html.append("<h2>Filled by Legal</h2>")
			    .append("<table>");
			addRow(html, "Agreement Number", model.getLegal_agreement_number());
			addRow(html, "Agreement Date", model.getLegal_agreement_date());
			html.append("</table>");

			html.append("<h2>Approval History</h2>")
			    .append("<table>")
			    .append("<tr><th style='width: 40%;'>Approver</th><th style='width: 30%;'>Status</th><th style='width: 30%;'>Process Date</th></tr>");

			MatterHistory matterHistory = new MatterHistory(systemMatterId, "en");
			List<MatterHistoryResultModel> historiesList = matterHistory.getMatterHistory();
			boolean hasHistory = false;

			if (historiesList != null) {
				for (MatterHistoryResultModel history : historiesList) {
					if (history.getStatusName().equals("Approve")) {
						html.append("<tr>")
						    .append("<td>").append(history.getExecuteUserName()).append("</td>")
						    .append("<td>").append(history.getStatusName()).append("</td>")
						    .append("<td>").append(history.getEndDate()).append("</td>")
						    .append("</tr>");
						hasHistory = true;
					}
				}
			}

			if (!hasHistory) {
				html.append("<tr><td colspan='3'>No approval history found.</td></tr>");
			}

			html.append("</table>")
			    .append("</body></html>");

			InputStream successPdf = HtmlToPdf.create().object(HtmlToPdfObject.forHtml(html.toString())).convert();

			try {
				PublicStorage createNewDir = new PublicStorage("generate_pdf");
				try {
					createNewDir.makeDirectories();
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("Error creating directory for PDF generation", e);
				}

				String applicationNumber = model.getApplication_number();
				PublicStorage pdfFilePath = new PublicStorage("generate_pdf/" + applicationNumber + ".pdf");
				pdfFilePath.save(IOUtils.toByteArray(successPdf));
				return applicationNumber + ".pdf";
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void addRow(StringBuilder sb, String label, Object value) {
		sb.append("<tr>")
		  .append("<th>").append(label).append("</th>")
		  .append("<td colspan='3'>").append(value).append("</td>")
		  .append("</tr>");
	}

	private void addRow2Col(StringBuilder sb, String label1, Object value1, String label2, Object value2) {
		sb.append("<tr>")
		  .append("<th style='width: 28%;'>").append(label1).append("</th>")
		  .append("<td style='width: 22%;'>").append(value1).append("</td>")
		  .append("<th style='width: 28%;'>").append(label2).append("</th>")
		  .append("<td style='width: 22%;'>").append(value2).append("</td>")
		  .append("</tr>");
	}

}
