package wf.practice3_bintang.general.domain.service;

import jp.co.intra_mart.foundation.mail.MailSenderException;
import jp.co.intra_mart.foundation.mail.javamail.JavaMailSender;
import jp.co.intra_mart.foundation.mail.javamail.StandardMail;
import jp.co.intra_mart.foundation.workflow.application.general.CplMatter;
import wf.common.constant.WorkflowCommonConstants;
import wf.common.constant.MailStatus;
import wf.practice3_bintang.general.app.ExpenseForm;
import wf.practice3_bintang.general.domain.model.ExpenseHeaderModel;
import wf.practice3_bintang.general.domain.repository.ExpenseHeaderRepository;

/**
 * 経費精算のメール送信処理を行うサービス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class ExpenseEmailService {

    /**
     * 申請データをもとに、メールを送信し、送信ステータスをDBに記録するメソッド。
     *
     * @param matterId      システム案件ID
     * @param mail          送信先メールアドレス
     * @param FormClassRows 経費申請情報が格納されたフォーム
     * @throws Exception メール送信またはデータベース更新処理中に例外が発生した場合
     */
    public void send_email(String matterId, String mail, ExpenseForm FormClassRows) throws Exception {

        // フォームデータから送信対象の案件IDを取得する
        String matterID = FormClassRows.getF_system_matter_id();

        // 案件IDを基づいて概要の案件情報を取得する
        String matter_number = "";
        String matter_name = "";
        String matter_datetime = "";
        String matter_date = "";

        try {
            // 完了案件情報から取得を試してみる
            CplMatter cplMatter = new CplMatter(matterID);
            matter_number = cplMatter.getMatter().getMatterNumber();
            matter_name = cplMatter.getMatter().getMatterName();
            matter_datetime = cplMatter.getMatter().getApplyDate();

            // 日付の形式を調整する
            if (matter_datetime != null && matter_datetime.contains(" ")) {
                matter_date = matter_datetime.split(" ")[0];
            } else {
                matter_date = matter_datetime != null ? matter_datetime : "";
            }

        } catch (Exception cplEx) {
            // 案件情報が取得できない場合はログを出力し、空文字列のまま処理を継続する
            cplEx.printStackTrace();
        }

        // メール送信オブジェクトをインスタンス化する
        StandardMail create_mail = new StandardMail();

        // メール情報を設定する
        create_mail.setFrom("system-notification@imart.co.jp", "intra-mart ワークフロー自動通知");
        create_mail.setSubject("【通知】経費申請 承認完了のお知らせ（案件番号: " + matter_number + "）");
        create_mail.setText("関係者 各位\r\n" +
                "\r\n" +
                "以下の経費申請の承認処理が完了いたしました。\r\n" +
                "\r\n" +
                "【案件番号】: " + matter_number + "\r\n" +
                "【案件名】  : " + matter_name + "\r\n" +
                "【申請日】  : " + matter_date + "\r\n" +
                "\r\n" +
                "※申請の詳細内容につきましては、intra-martにログインして「案件一覧（処理済み）」画面よりご確認ください。\r\n" +
                "\r\n" +
                "---------------------------------------------------\r\n" +
                "本メールはシステムからの自動送信メールです。\r\n" +
                "---------------------------------------------------\r\n");
        create_mail.addTo(mail);

        try {
            // メール送信を実行する
            JavaMailSender sender = new JavaMailSender(create_mail);
            sender.send();

            // 送信成功時：ヘッダーDBのメール送信状況を成功(2)に更新する
            ExpenseHeaderRepository ExpenseHeaderDB = new ExpenseHeaderRepository();
            ExpenseHeaderModel rows_header = ExpenseHeaderDB
                    .selectDataHeader(matterId, WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID).iterator()
                    .next();
            rows_header.setMail_status(MailStatus.SENT.getCode());
            ExpenseHeaderDB.updateDataHeader(rows_header);

        } catch (MailSenderException var30) {
            // 送信失敗時：ヘッダーDBのメール送信状況をエラー(99)に更新する
            ExpenseHeaderRepository ExpenseHeaderDB = new ExpenseHeaderRepository();
            ExpenseHeaderModel rows_header = ExpenseHeaderDB
                    .selectDataHeader(matterId, WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID).iterator()
                    .next();
            rows_header.setMail_status(MailStatus.FAILED.getCode());
            ExpenseHeaderDB.updateDataHeader(rows_header);

            var30.printStackTrace();
            throw new MailSenderException("Error in sendEmailWithAttachment()", var30);
        }
    }

}
