package wf.path_test.general.domain.service;

import jp.co.intra_mart.foundation.mail.MailSenderException;
import jp.co.intra_mart.foundation.mail.javamail.JavaMailSender;
import jp.co.intra_mart.foundation.mail.javamail.StandardMail;

import wf.path_test.general.app.*;
import wf.path_test.general.domain.model.*;
import wf.path_test.general.domain.repository.*;

public class EmailService {

    public void send_email(String matterId, String mail, ImartForm FormClassRows) throws Exception {

        // set Data
        String matterID = FormClassRows.getF_system_matter_id();
        String name = FormClassRows.getF_name();
        String age = FormClassRows.getF_age();
        String note = FormClassRows.getF_note();

        // Set Mail
        StandardMail create_mail = new StandardMail();
        create_mail.setFrom("mailTesting@yahoo.jp", "Email Notification");
        create_mail.setSubject(" [Test Mail]");
        create_mail.setText("Dear Mr./Mrs./Ms.,\r\n" +
                "\r\n" +
                "Application with Matter Number " + matterID
                + " has been Apply. Here the cover of Applicant document. \r\n" +
                "\r\n" +
                "\r\n" +
                "Customer Information*  \r\n" +
                "___________________________________________________ \r\n\r\n" +
                "| Customer Name 		: " + name + "\r\n" +
                "| Customer Age 		: " + age + "\r\n" +
                "| Customer Notes 		: " + note + "\r\n" +
                "___________________________________________________ \r\n\r\n" +
                "\r\n" +
                "\r\n" +
                "Best Regards,\r\n" +
                "Testing Team\r\n");
        create_mail.addTo(mail);

        // Execute Mail
        try {
            JavaMailSender sender = new JavaMailSender(create_mail);
            sender.send();
            // Update mail_status in HeaderDB
            HeaderRepository HeaderDB = new HeaderRepository();
            HeaderModel rows_header = HeaderDB.selectDataHeader(matterId, "system_matter_id").iterator().next();
            rows_header.setMail_status("2");
            HeaderDB.updateDataHeader(rows_header);
        } catch (MailSenderException var30) {
            // Update mail_status in HeaderDB
            HeaderRepository HeaderDB = new HeaderRepository();
            HeaderModel rows_header = HeaderDB.selectDataHeader(matterId, "system_matter_id").iterator().next();
            rows_header.setMail_status("99");
            HeaderDB.updateDataHeader(rows_header);

            var30.printStackTrace();
            throw new MailSenderException("Error in sendEmailWithAttachment()", var30);
        }
    }

}
