package wf.practice4_bintang.general.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.information.Identifier;
import jp.co.intra_mart.foundation.workflow.code.PageType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import wf.common.constant.WorkflowCommonConstants;
import wf.practice4_bintang.general.domain.service.PcGeneratePDFService;
import wf.practice4_bintang.general.domain.service.PcWorkflowService;

/**
 * PC購入申請の画面遷移を制御するコントローラクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
@Controller
@RequestMapping("practice4_bintang/")
public class PcController {

    // 画面に渡すモデル名を定数化する
    private static final String MODEL_KEY_FORM_ROWS = "savedFormData";
    private static final String MODEL_KEY_APPLY_FORM = "workflowRequestForm";
    private static final String MODEL_KEY_DOWNLOAD_FILE_NAME = "download_file_name";
    private static final String MODEL_KEY_STORAGE = "storage";

    // JSPの共通パス
    private static final String BASE_VIEW_PATH = "wf/personal_info_change/";
    // 申請画面のJSPパス
    private static final String VIEW_PATH_APPLY = BASE_VIEW_PATH + "apply.jsp";
    // 承認画面のJSPパス
    private static final String VIEW_PATH_APPROVE = BASE_VIEW_PATH + "approve.jsp";
    // 詳細確認画面のJSPパス
    private static final String VIEW_PATH_DETAIL = BASE_VIEW_PATH + "detail.jsp";
    // ダウンロードパス
    private static final String VIEW_PATH_DOWNLOAD = "PcDownloadAttachmentService.Downloadview";

    // PDF保存用ディレクトリ名
    private static final String PDF_DIR_NAME = "generate_pdf/";
    // UTF-8 エンコーディング名
    private static final String CHAR_ENCODING_UTF8 = "UTF-8";
    // エラーレスポンス接頭辞
    private static final String ERROR_RESPONSE_PREFIX = "error: ";

    /**
     * 申請画面・再申請画面の処理を行うメソッド。
     *
     * @param model     画面にデータを渡すためのModelオブジェクト
     * @param ApplyForm ワークフローの遷移パラメータを保持するフォームオブジェクト
     * @return 遷移先JSPのパス
     * @throws Exception 画面表示時に例外が発生した場合
     */
    @RequestMapping(value = "apply")
    public final String apply(final Model model, final PcForm ApplyForm) throws Exception {

        // 新規申請の場合、ユーザデータIDを発行し、設定する
        if (PageType.pageTyp_App.toString().equals(ApplyForm.getImwPageType())) {
            String userDataId = "";
            final Identifier identifier = new Identifier();
            userDataId = identifier.get();

            ApplyForm.setImwUserDataId(userDataId);

            // 再申請の場合、過去に入力されたデータを一時保存テーブルから取得し、画面に渡す
        } else {
            // 過去のデータをシステム案件IDで取得
            PcWorkflowService service = new PcWorkflowService();
            PcForm FormClassRows = new PcForm();
            FormClassRows = service.getInfoTemp(ApplyForm.getImwSystemMatterId(),
                    WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

            model.addAttribute(MODEL_KEY_FORM_ROWS, FormClassRows);

        }

        model.addAttribute(MODEL_KEY_APPLY_FORM, ApplyForm);
        final String path = VIEW_PATH_APPLY;
        return path;
    }

    /**
     * 承認画面の処理を行うメソッド。
     * 過去に入力されたデータを一時保存テーブルから取得し、画面に渡す。
     *
     * @param model     画面にデータを渡すためのModelオブジェクト
     * @param ApplyForm ワークフローの遷移パラメータを保持するフォームオブジェクト
     * @return 遷移先JSPのパス
     * @throws AccessSecurityException アクセスセキュリティ例外が発生した場合
     * @throws IOException             入出力例外が発生した場合
     */
    @RequestMapping(value = "approve")
    public final String approve(final Model model, final PcForm ApplyForm)
            throws AccessSecurityException, IOException {
        // 過去に入力されたデータを一時保存テーブルから取得し、画面に渡す
        try {
            // 過去のデータをシステム案件IDで取得
            PcWorkflowService service = new PcWorkflowService();
            PcForm formRows = new PcForm();
            formRows = service.getInfoTemp(ApplyForm.getImwSystemMatterId(),
                    WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

            model.addAttribute(MODEL_KEY_FORM_ROWS, formRows);
            model.addAttribute(MODEL_KEY_APPLY_FORM, ApplyForm);
        } catch (Exception e) {
            System.out.println("Error Approve :" + e);
        }

        final String path = VIEW_PATH_APPROVE;
        return path;
    }

    /**
     * 詳細確認画面の処理を行うメソッド。
     * 過去の入力データを一時保存テーブルから取得し、詳細確認画面に設定する。
     *
     * @param model     画面にデータを渡すためのModelオブジェクト
     * @param ApplyForm ワークフローの遷移パラメータを保持するフォームオブジェクト
     * @return 遷移先JSPのパス
     * @throws AccessSecurityException アクセスセキュリティ例外が発生した場合
     * @throws IOException             入出力例外が発生した場合
     */
    @RequestMapping(value = "detail")
    public final String detail(final Model model, final PcForm ApplyForm)
            throws AccessSecurityException, IOException {
        // 過去に入力されたデータを一時保存テーブルから取得し、画面に渡す
        try {
            // 過去のデータをシステム案件IDで取得
            PcWorkflowService service = new PcWorkflowService();
            PcForm formRows = new PcForm();
            formRows = service.getInfoTemp(ApplyForm.getImwSystemMatterId(),
                    WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

            model.addAttribute(MODEL_KEY_FORM_ROWS, formRows);
            model.addAttribute(MODEL_KEY_APPLY_FORM, ApplyForm);
        } catch (Exception e) {
            System.out.println("Error Approve :" + e);
        }

        final String path = VIEW_PATH_DETAIL;
        return path;
    }

    /**
     * システム案件IDを元にPDFを生成するメソッド。
     *
     * @param request HTTPリクエストオブジェクト (system_matter_idを含む)
     * @return 生成されたPDFのファイル名、またはエラーメッセージ
     * @throws Exception 処理中に例外が発生した場合
     */
    @PostMapping("generatepdf")
    @ResponseBody
    public String generatepdf(final HttpServletRequest request) throws Exception {

        // システム案件IDを取得する
        String MatterId = request.getParameter(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

        // PDFを生成する
        try {
            PcGeneratePDFService pdfGenerate = new PcGeneratePDFService();
            String pdfFileName = pdfGenerate.createPDF(MatterId);
            return pdfFileName;

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR_RESPONSE_PREFIX + e.getMessage();
        }

    }

    /**
     * 生成されたPDFファイルをクライアントにダウンロードさせるメソッド。
     *
     * @param model   画面データ送信用Modelオブジェクト
     * @param request HTTPリクエストオブジェクト
     * @return ダウンロード用サービスビューパス
     * @throws Exception ファイル操作やデコード処理中に例外が発生した場合
     */
    @RequestMapping(value = "downloadpdf/**")
    public String downloadpdf(final Model model, HttpServletRequest request) throws Exception {

        // リクエストURLからファイル名を取得する
        String urlStr = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String fileName = urlStr.substring(urlStr.lastIndexOf('/') + 1);

        // パブリックストレージ（PublicStorage）から対象ファイルを取得する
        String file_decode = URLDecoder.decode(PDF_DIR_NAME + fileName, CHAR_ENCODING_UTF8);
        final PublicStorage storage = new PublicStorage(file_decode);

        // 存在しない場合はエラーを返す
        if (!storage.isFile()) {
            throw new FileNotFoundException("Could not find a file");
        }

        // ファイル名とストレージオブジェクトを設定する
        model.addAttribute(MODEL_KEY_DOWNLOAD_FILE_NAME, fileName);
        model.addAttribute(MODEL_KEY_STORAGE, storage);

        // ダウンロードを実行するクラスへ遷移する
        final String path = VIEW_PATH_DOWNLOAD;
        return path;
    }

}
