package wf.path_test.general.domain.job;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import jp.co.intra_mart.foundation.job_scheduler.Job;
import jp.co.intra_mart.foundation.job_scheduler.JobResult;
import jp.co.intra_mart.foundation.job_scheduler.exception.JobExecuteException;
import wf.path_test.general.app.ImartForm;
import wf.path_test.general.domain.model.HeaderModel;
import wf.path_test.general.domain.repository.HeaderRepository;
import wf.path_test.general.domain.service.EmailService;
import wf.path_test.general.domain.service.WorkflowService;

public class TestingJob implements Job {

    public JobResult execute() throws JobExecuteException {

        try {

            System.out.println("-------- RUNNING JOB SUCCESS  -----------");
            LocalDateTime now = LocalDateTime.now();
            @SuppressWarnings("unused")
			LocalDate dateOnly = now.toLocalDate();

            // Define Repo
            HeaderRepository HeaderDB = new HeaderRepository();

            String mail_status = "1";
            Collection<HeaderModel> HeaderFormRows = HeaderDB.selectDataHeader(mail_status, "mail");

            for(HeaderModel header : HeaderFormRows) {
                WorkflowService Service = new WorkflowService();
                ImartForm FormRows = new ImartForm();

                FormRows = Service.getInfoTemp(header.getSystem_matter_id(), "system_matter_id");

                String mailAddress = "dummytest@gmail.com";
                String matter_id = header.getSystem_matter_id();
                EmailService SendMailService = new EmailService();

                SendMailService.send_email(matter_id, mailAddress, FormRows);
            
            }

        } catch (Exception e) {
            throw new JobExecuteException("Error during job execution.", e);
        }
        return JobResult.success("success");
    }

}
