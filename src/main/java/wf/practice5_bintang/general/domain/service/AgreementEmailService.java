package wf.practice5_bintang.general.domain.service;

import jp.co.intra_mart.foundation.mail.MailSenderException;
import jp.co.intra_mart.foundation.mail.javamail.ExtendedMail;
import jp.co.intra_mart.foundation.mail.javamail.JavaMailSender;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.workflow.application.general.CplMatter;
import wf.common.constant.WorkflowCommonConstants;
import wf.practice5_bintang.general.app.AgreementForm;
import wf.practice5_bintang.general.domain.model.AgreementHeaderModel;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderRepository;
import wf.common.constant.MailStatus;

public class AgreementEmailService {

	public void sendApprovalNotificationEmail(String matterId, String recipientEmail, AgreementForm form) throws Exception {

		String matterNumber = "";
		String matterName = "";
		String matterDatetime = "";
		String matterDate = "";

		// Get information related to the matter
		try {
			CplMatter cplMatter = new CplMatter(matterId);
			matterNumber = cplMatter.getMatter().getMatterNumber();
			matterName = cplMatter.getMatter().getMatterName();
			matterDatetime = cplMatter.getMatter().getApplyDate();

			if (matterDatetime != null && matterDatetime.contains(" ")) {
				matterDate = matterDatetime.split(" ")[0];
			} else {
				matterDate = matterDatetime != null ? matterDatetime : "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ExtendedMail createMail = new ExtendedMail();

		createMail.setFrom("system-notification@imart.co.jp", "intra-mart Workflow Notification");
		createMail.setSubject("[Notification] Agreement Workflow Approval Completed (Matter No: " + matterNumber + ")");

		StringBuilder body = new StringBuilder();
		body.append("Dear Concerned Parties,\r\n\r\n")
		    .append("The approval process for the following agreement workflow has been completed.\r\n\r\n")
		    .append("  - Matter Number : ").append(matterNumber).append("\r\n")
		    .append("  - Matter Name   : ").append(matterName).append("\r\n")
		    .append("  - Apply Date    : ").append(matterDate).append("\r\n\r\n")
		    .append("* For further details, please log in to intra-mart and check the \"Processed Matters\" list.\r\n\r\n")
		    .append("---------------------------------------------------\r\n")
		    .append("This is an automated notification sent from the system.\r\n")
		    .append("---------------------------------------------------\r\n");

		createMail.setText(body.toString());
		createMail.addTo(recipientEmail);

		// Attach PDF
		try {
			AgreementGeneratePDFService pdfService = new AgreementGeneratePDFService();
			String pdfFileName = pdfService.createPDF(matterId);

			PublicStorage pdfStorage = new PublicStorage("generate_pdf/" + pdfFileName);
			if (pdfStorage.isFile()) {
				createMail.addAttachmentStorage(pdfFileName, pdfStorage);
			}

		} catch (Exception pdfEx) {
			System.err.println("Warning: Failed to attach PDF to email: " + pdfEx.getMessage());
			pdfEx.printStackTrace();
		}

		try {
			JavaMailSender sender = new JavaMailSender(createMail);
			sender.send();

			AgreementHeaderRepository agreementHeaderDb = new AgreementHeaderRepository();
			AgreementHeaderModel rowsHeader = agreementHeaderDb.selectHeader(matterId, WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID).iterator().next();
			rowsHeader.setMail_status(MailStatus.SENT.getCode());
			agreementHeaderDb.updateHeader(rowsHeader);

		} catch (MailSenderException e) {
			AgreementHeaderRepository agreementHeaderDb = new AgreementHeaderRepository();
			AgreementHeaderModel rowsHeader = agreementHeaderDb.selectHeader(matterId, WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID).iterator().next();
			rowsHeader.setMail_status(MailStatus.FAILED.getCode());
			agreementHeaderDb.updateHeader(rowsHeader);

			e.printStackTrace();
			throw new MailSenderException("Error in sendApprovalNotificationEmail()", e);
		}
	}

}
