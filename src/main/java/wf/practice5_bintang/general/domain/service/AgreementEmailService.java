package wf.practice5_bintang.general.domain.service;

import jp.co.intra_mart.foundation.mail.MailSenderException;
import jp.co.intra_mart.foundation.mail.javamail.JavaMailSender;
import jp.co.intra_mart.foundation.mail.javamail.StandardMail;
import jp.co.intra_mart.foundation.workflow.application.general.CplMatter;
import wf.common.constant.WorkflowCommonConstants;
import wf.practice5_bintang.general.app.AgreementForm;
import wf.practice5_bintang.general.domain.model.AgreementHeaderModel;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderRepository;
import wf.common.constant.MailStatus;

public class AgreementEmailService {

	public void sendApprovalNotificationEmail(String matterId, String recipientEmail, AgreementForm formClassRows) throws Exception {

		String targetMatterId = formClassRows.getF_system_matter_id();

		String matterNumber = "";
		String matterName = "";
		String matterDatetime = "";
		String matterDate = "";

		try {
			CplMatter cplMatter = new CplMatter(targetMatterId);
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

		StandardMail createMail = new StandardMail();

		createMail.setFrom("system-notification@imart.co.jp", "intra-mart ワークフロー自動通知");
		createMail.setSubject("【通知】契約捺印申請 承認完了のお知らせ（案件番号: " + matterNumber + "）");
		createMail.setText("関係者 各位\r\n" + "\r\n" + "以下の契約捺印申請の承認処理が完了いたしました。\r\n" + "\r\n" + "【案件番号】: " + matterNumber + "\r\n" + "【案件名】  : " + matterName + "\r\n" + "【申請日】  : " + matterDate + "\r\n" + "\r\n"
				+ "※申請の詳細内容につきましては、intra-martにログインして「案件一覧（処理済み）」画面よりご確認ください。\r\n" + "\r\n" + "---------------------------------------------------\r\n" + "本メールはシステムからの自動送信メールです。\r\n"
				+ "---------------------------------------------------\r\n");
		createMail.addTo(recipientEmail);

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
