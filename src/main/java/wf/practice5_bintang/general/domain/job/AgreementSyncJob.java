package wf.practice5_bintang.general.domain.job;

import jp.co.intra_mart.foundation.job_scheduler.Job;
import jp.co.intra_mart.foundation.job_scheduler.JobResult;
import jp.co.intra_mart.foundation.job_scheduler.exception.JobExecuteException;
import wf.practice5_bintang.general.constant.AgreementDbConstants;
import wf.practice5_bintang.general.domain.service.AgreementAutoApplyService;

public class AgreementSyncJob implements Job {

    @Override
    public JobResult execute() throws JobExecuteException {
        System.out.println("========== [JOB START: AgreementSyncJob] ==========");
        try {
            AgreementAutoApplyService syncService = new AgreementAutoApplyService();

            String mysqlResult = syncService.syncPending(AgreementDbConstants.EXT_DB_NAME);
            System.out.println("MySQL Job Result: " + mysqlResult);

            String defaultResult = syncService.syncPending();
            System.out.println("Default DB Job Result: " + defaultResult);

            System.out.println("========== [JOB END: AgreementSyncJob SUCCESS] ==========");

            if ("ERROR".equals(mysqlResult) || "ERROR".equals(defaultResult)) {
                return JobResult.error("Job completed with errors. MySQL: " + mysqlResult + ", Default: " + defaultResult);
            }

            return JobResult.success("Sync completed. MySQL: " + mysqlResult + ", Default: " + defaultResult);
        } catch (Exception e) {
            System.err.println("Job Error: " + e.getMessage());
            e.printStackTrace();
            throw new JobExecuteException("AgreementSyncJob failed.", e);
        }
    }
}
