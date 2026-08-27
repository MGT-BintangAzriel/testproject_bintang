package wf.practice5_bintang.general.domain.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import jp.co.intra_mart.foundation.job_scheduler.Job;
import jp.co.intra_mart.foundation.job_scheduler.JobResult;
import jp.co.intra_mart.foundation.job_scheduler.exception.JobExecuteException;
import jp.co.intra_mart.foundation.master.user.UserManager;
import jp.co.intra_mart.foundation.master.user.model.User;
import jp.co.intra_mart.foundation.master.user.model.UserBizKey;
import jp.co.intra_mart.foundation.workflow.application.general.CplMatter;
import wf.common.constant.WorkflowCommonConstants;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderModel;
import wf.practice5_bintang.general.domain.repository.AgreementAttachFileRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderRepository;
import wf.practice5_bintang.general.domain.service.AgreementEmailService;
import wf.practice5_bintang.general.domain.service.AgreementGeneratePDFService;

public class AgreementJob implements Job {

	public JobResult execute() throws JobExecuteException {

		String matterNumber = "";
		String matterName = "";
		String matterDate = "";
		String matterApplicantCode = "";
		String recipientEmail = "";

		try {
			System.out.println("-------- RUNNING JOB SUCCESS  -----------");
			AgreementHeaderRepository agreementHeaderDb = new AgreementHeaderRepository();
			AgreementAttachFileRepository agreementAttachFileDb = new AgreementAttachFileRepository();

			Collection<AgreementHeaderModel> models = agreementHeaderDb.selectPendingMailHeader();

			for (AgreementHeaderModel model : models) {
				String matterId = model.getSystem_matter_id();

				try {
					CplMatter cplMatter = new CplMatter(matterId);
					matterNumber = cplMatter.getMatter().getMatterNumber();
					matterName = cplMatter.getMatter().getMatterName();
					matterApplicantCode = cplMatter.getMatter().getApplyAuthUserCode();

					String matterDatetime = cplMatter.getMatter().getApplyDate();
					if (matterDatetime != null && matterDatetime.contains(" ")) {
						matterDate = matterDatetime.split(" ")[0];
					} else {
						matterDate = matterDatetime != null ? matterDatetime : "";
					}

					UserManager userManager = new UserManager();
					UserBizKey userBizKey = new UserBizKey();
					userBizKey.setUserCd(matterApplicantCode);
					User user = userManager.getUser(userBizKey, new Date());
					if (user != null) {
						recipientEmail = user.getEmailAddress1();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (recipientEmail == null || recipientEmail.isEmpty()) {
					recipientEmail = "employee@gmail.com";
				}

				AgreementGeneratePDFService generatePDFService = new AgreementGeneratePDFService();
				String pdfFileName = generatePDFService.createPDF(matterId);

				List<AgreementAttachmentModel> attachments = new ArrayList<>(agreementAttachFileDb.selectAttachment(matterId, WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID));

				AgreementEmailService mailService = new AgreementEmailService();
				mailService.sendApprovalNotificationEmail(matterId, recipientEmail, matterNumber, matterName, matterDate, pdfFileName, attachments);
			}

		} catch (Exception e) {
			throw new JobExecuteException("Error during job execution.", e);
		}
		return JobResult.success("success");
	}

}
