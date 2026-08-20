package wf.practice4_bintang.general.domain.job;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import wf.common.constant.MailStatus;
import wf.practice4_bintang.general.app.PcForm;
import wf.practice4_bintang.general.domain.model.PcHeaderModel;
import wf.practice4_bintang.general.domain.repository.PcHeaderRepository;
import wf.practice4_bintang.general.domain.service.PcEmailService;
import wf.practice4_bintang.general.domain.service.PcWorkflowService;

/**
 * PC購入申請のメール送信ジョブを実行するクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class PcJob implements Job {

    /**
     * ジョブの実行処理を行うメソッド。
     *
     * @return ジョブの実行結果
     * @throws JobExecuteException ジョブの実行中に例外が発生した場合
     */
    public JobResult execute() throws JobExecuteException {

        try {

            System.out.println("-------- RUNNING JOB SUCCESS  -----------");
            LocalDateTime now = LocalDateTime.now();
            @SuppressWarnings("unused")
            LocalDate dateOnly = now.toLocalDate();

            // リポジトリをインスタンス化する
            PcHeaderRepository PcHeaderDB = new PcHeaderRepository();

            // メール未送信のデータをDBから取得する
            String mail_status = MailStatus.UNSENT.getCode();
            Collection<PcHeaderModel> PcHeaderFormRows = PcHeaderDB.selectDataHeader(mail_status,
                    "mail");

            // メール送信を行う
            for (PcHeaderModel header : PcHeaderFormRows) {
                PcWorkflowService Service = new PcWorkflowService();
                PcForm FormRows = new PcForm();

                // システム案件IDで一時保存情報を取得する
                FormRows = Service.getInfoTemp(header.getSystem_matter_id(),
                        WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

                // 案件IDに基づいて申請者のメールアドレスを取得する
                String matter_id = header.getSystem_matter_id();
                String mailAddress = "";

                try {
                    // 完了案件情報から申請者のユーザコードを取得する
                    CplMatter cplMatter = new CplMatter(matter_id);
                    String matter_applicant_code = "";
                    matter_applicant_code = cplMatter.getMatter().getApplyAuthUserCode();

                    // ユーザコードに基づいて登録されているメールアドレスを取得する
                    UserManager userManager = new UserManager();
                    UserBizKey userBizKey = new UserBizKey();
                    userBizKey.setUserCd(matter_applicant_code);
                    User user = userManager.getUser(userBizKey, new Date());
                    if (user != null) {
                        mailAddress = user.getEmailAddress1();
                    }
                } catch (Exception e) {
                    // 取得処理中に例外が発生した場合はスタックトレースを出力する
                    e.printStackTrace();
                }

                // メールアドレスが未登録・取得不可の場合はデフォルトアドレスを設定する
                if (mailAddress == null || mailAddress.isEmpty()) {
                    mailAddress = "employee@gmail.com";
                }

                PcEmailService SendMailService = new PcEmailService();
                SendMailService.send_email(matter_id, mailAddress, FormRows);
            }

        } catch (Exception e) {
            throw new JobExecuteException("Error during job execution.", e);
        }
        return JobResult.success("success");
    }

}
