package wf.practice5_bintang.general.domain.service;

import java.util.List;

import jp.co.intra_mart.foundation.mail.MailSenderException;
import jp.co.intra_mart.foundation.mail.javamail.ExtendedMail;
import jp.co.intra_mart.foundation.mail.javamail.JavaMailSender;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import wf.common.constant.MailStatus;
import wf.common.constant.WorkflowCommonConstants;
import wf.practice5_bintang.general.constant.AgreementFormConstants;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderModel;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderRepository;

public class AgreementEmailService {

	public void sendApprovalNotificationEmail(String matterId, String recipientEmail, String matterNumber, String matterName, String matterDate, String pdfFileName, List<AgreementAttachmentModel> attachments) throws Exception {

		ExtendedMail createMail = new ExtendedMail();

		createMail.setFrom("system-notification@imart.co.jp", "intra-mart Workflow Notification");
		createMail.setSubject("[Notification] Agreement Workflow Approval Completed (Matter No: " + matterNumber + ")");

		StringBuilder body = new StringBuilder();
		body.append("Dear Concerned Parties,\r\n\r\n")
		    .append("The approval process for the following agreement workflow has been completed.\r\n\r\n")
		    .append("  - Matter Number : ").append(matterNumber).append("\r\n")
		    .append("  - Matter Name   : ").append(matterName).append("\r\n")
		    .append("  - Apply Date    : ").append(matterDate).append("\r\n\r\n")
		    .append("* For further details, please log in to intra-mart and check the \"Processed (complete matter)\" list.\r\n\r\n")
		    .append("---------------------------------------------------\r\n")
		    .append("This is an automated notification sent from the system.\r\n")
		    .append("---------------------------------------------------\r\n");

		createMail.setText(body.toString());
		createMail.addTo(recipientEmail);

		// Attach PDF
		try {
			PublicStorage pdfStorage = new PublicStorage(AgreementFormConstants.STORAGE_PATH_GENERATE_PDF + pdfFileName);
			if (pdfStorage.isFile()) {
				createMail.addAttachmentStorage(pdfFileName, pdfStorage);
			}
		} catch (Exception pdfEx) {
			System.err.println("Warning: Failed to attach PDF to email: " + pdfEx.getMessage());
			pdfEx.printStackTrace();
		}
		
		// Attach Uploaded File
		if (attachments != null && !attachments.isEmpty()) {
			for (AgreementAttachmentModel attachment : attachments) {
				try {
					String fileRealName = attachment.getFile_real_name();
					String originalFileName = attachment.getFile_name();
					PublicStorage attachedFileStorage = new PublicStorage("practice5_bintang/" + matterId + "/file_attachment/" + fileRealName);
					
					if(attachedFileStorage.isFile()) {
						createMail.addAttachmentStorage(originalFileName, attachedFileStorage);
					}
				} catch (Exception e){
					System.out.println("Failed to attach uploaded files in the maill");
					e.printStackTrace();
				}
			}
		};

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
