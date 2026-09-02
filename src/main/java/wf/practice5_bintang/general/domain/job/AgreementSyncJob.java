package wf.practice5_bintang.general.domain.job;

import jp.co.intra_mart.foundation.job_scheduler.Job;
import jp.co.intra_mart.foundation.job_scheduler.JobResult;
import jp.co.intra_mart.foundation.job_scheduler.exception.JobExecuteException;
import wf.practice5_bintang.general.domain.service.AgreementAutoApplyTestService;

public class AgreementSyncJob implements Job {

    @Override
    public JobResult execute() throws JobExecuteException {
        System.out.println("========== [JOB START: AgreementSyncJob] ==========");
        try {
            AgreementAutoApplyTestService syncService = new AgreementAutoApplyTestService();
            String resultMessage = syncService.syncPending();
            
            System.out.println("Job Result: " + resultMessage);
            System.out.println("========== [JOB END: AgreementSyncJob SUCCESS] ==========");
            
            return JobResult.success("Sync completed: " + resultMessage);
        } catch (Exception e) {
            System.err.println("Job Error: " + e.getMessage());
            e.printStackTrace();
            throw new JobExecuteException("AgreementSyncJob failed.", e);
        }
    }
}
