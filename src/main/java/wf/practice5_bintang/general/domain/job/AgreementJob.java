package wf.practice5_bintang.general.domain.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import jp.co.intra_mart.foundation.job_scheduler.Job;
import jp.co.intra_mart.foundation.job_scheduler.JobResult;
import jp.co.intra_mart.foundation.job_scheduler.exception.JobExecuteException;
import wf.common.constant.WorkflowCommonConstants;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderModel;
import wf.practice5_bintang.general.domain.repository.AgreementAttachFileRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderRepository;
import wf.practice5_bintang.general.domain.service.AgreementEmailService;
import wf.practice5_bintang.general.domain.service.AgreementGeneratePDFService;
import wf.practice5_bintang.general.domain.service.AgreementWorkflowService;

public class AgreementJob implements Job {

	public JobResult execute() throws JobExecuteException {

		try {
			System.out.println("-------- RUNNING JOB SUCCESS  -----------");
			AgreementHeaderRepository agreementHeaderDb = new AgreementHeaderRepository();
			AgreementAttachFileRepository agreementAttachFileDb = new AgreementAttachFileRepository();

			Collection<AgreementHeaderModel> models = agreementHeaderDb.selectPendingMailHeader();

			for (AgreementHeaderModel model : models) {
				String matterId = model.getSystem_matter_id();

				AgreementWorkflowService workflowService = new AgreementWorkflowService();
				Map<String, String> matterData = workflowService.getMatterData(matterId);

				AgreementGeneratePDFService generatePDFService = new AgreementGeneratePDFService();
				String pdfFileName = generatePDFService.createPDF(matterId);

				List<AgreementAttachmentModel> attachments = new ArrayList<>(agreementAttachFileDb.selectAttachment(matterId, WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID));

				AgreementEmailService mailService = new AgreementEmailService();
				mailService.sendApprovalNotificationEmail(matterId, matterData.get("recipientEmail"), matterData.get("matterNumber"), matterData.get("matterName"), matterData.get("matterDate"), pdfFileName, attachments);
			}

		} catch (Exception e) {
			throw new JobExecuteException("Error during job execution.", e);
		}
		return JobResult.success("success");
	}

}
