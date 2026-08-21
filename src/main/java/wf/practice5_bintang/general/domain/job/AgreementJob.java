package wf.practice5_bintang.general.domain.job;

import java.util.Collection;
import java.util.Date;

import jp.co.intra_mart.foundation.job_scheduler.Job;
import jp.co.intra_mart.foundation.job_scheduler.JobResult;
import jp.co.intra_mart.foundation.job_scheduler.exception.JobExecuteException;
import jp.co.intra_mart.foundation.master.user.UserManager;
import jp.co.intra_mart.foundation.master.user.model.User;
import jp.co.intra_mart.foundation.master.user.model.UserBizKey;
import jp.co.intra_mart.foundation.workflow.application.general.CplMatter;

import wf.common.constant.WorkflowCommonConstants;
import wf.practice5_bintang.general.app.AgreementForm;
import wf.practice5_bintang.general.domain.model.AgreementHeaderModel;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderRepository;
import wf.practice5_bintang.general.domain.service.AgreementEmailService;
import wf.practice5_bintang.general.domain.service.AgreementWorkflowService;
import wf.common.constant.MailStatus;

public class AgreementJob implements Job {

	public JobResult execute() throws JobExecuteException {

		try {
			System.out.println("-------- RUNNING JOB SUCCESS  -----------");
			AgreementHeaderRepository agreementHeaderDB = new AgreementHeaderRepository();

			String mailStatus = MailStatus.UNSENT.getCode();
			Collection<AgreementHeaderModel> models = agreementHeaderDB.selectHeader(mailStatus, "mail");

			for (AgreementHeaderModel model : models) {
				AgreementWorkflowService service = new AgreementWorkflowService();
				AgreementForm form = new AgreementForm();

				form = service.getHeaderInfoTempForm(model.getSystem_matter_id(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

				String matterId = model.getSystem_matter_id();
				String mailAddress = "";

				try {
					CplMatter cplMatter = new CplMatter(matterId);
					String matterApplicantCode = "";
					matterApplicantCode = cplMatter.getMatter().getApplyAuthUserCode();

					UserManager userManager = new UserManager();
					UserBizKey userBizKey = new UserBizKey();
					userBizKey.setUserCd(matterApplicantCode);
					User user = userManager.getUser(userBizKey, new Date());
					if (user != null) {
						mailAddress = user.getEmailAddress1();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (mailAddress == null || mailAddress.isEmpty()) {
					mailAddress = "employee@gmail.com";
				}

				AgreementEmailService mailService = new AgreementEmailService();
				mailService.sendApprovalNotificationEmail(matterId, mailAddress, form);
			}

		} catch (Exception e) {
			throw new JobExecuteException("Error during job execution.", e);
		}
		return JobResult.success("success");
	}

}
