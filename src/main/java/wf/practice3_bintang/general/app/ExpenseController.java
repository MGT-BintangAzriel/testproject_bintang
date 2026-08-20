package wf.practice3_bintang.general.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.information.Identifier;
import jp.co.intra_mart.foundation.workflow.code.PageType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import wf.common.constant.WorkflowCommonConstants;
import wf.practice3_bintang.general.domain.model.ExpenseAttachmentModel;
import wf.practice3_bintang.general.domain.repository.ExpenseAttachFileRepository;
import wf.practice3_bintang.general.domain.service.ExpenseWorkflowService;

/**
 * 経費申請の画面遷移を制御するコントローラクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
@Controller
@RequestMapping("practice3_bintang/")
public class ExpenseController {

    // 画面に渡すフォーム名を定数化する
    private static final String MODEL_KEY_FORM_ROWS = "FormClassRows";
    private static final String MODEL_KEY_APPLY_FORM = "ApplyForm";

    // JSPの共通パス
    private static final String BASE_VIEW_PATH = "wf/practice3_bintang/general/";
    // 申請画面のJSPパス
    private static final String VIEW_PATH_APPLY = BASE_VIEW_PATH + "apply.jsp";
    // 承認画面のJSPパス
    private static final String VIEW_PATH_APPROVE = BASE_VIEW_PATH + "approve.jsp";
    // 詳細確認画面のJSPパス
    private static final String VIEW_PATH_DETAIL = BASE_VIEW_PATH + "detail.jsp";
    // ダウンロードパス
    private static final String VIEW_PATH_DOWNLOAD = "ExpenseDownloadAttachmentService.Downloadview";

    /**
     * 申請画面・再申請画面の処理を行うメソッド。
     *
     * @param model     画面にデータを渡すためのModelオブジェクト
     * @param ApplyForm ワークフローの遷移パラメータを保持するフォームオブジェクト
     * @return 遷移先JSPのパス
     * @throws Exception 画面表示時に例外が発生した場合
     */
    @RequestMapping(value = "apply")
    public final String apply(final Model model, final ExpenseForm ApplyForm) throws Exception {

        // 新規申請の場合、ユーザデータIDを発行し、設定する
        if (PageType.pageTyp_App.toString().equals(ApplyForm.getImwPageType())) {
            String userDataId = "";
            final Identifier identifier = new Identifier();
            userDataId = identifier.get();

            ApplyForm.setImwUserDataId(userDataId);

            // 再申請の場合、過去に入力されたデータを一時保存テーブルから取得し、画面に渡す
        } else {
            // 過去のデータをシステム案件IDで取得
            ExpenseWorkflowService service = new ExpenseWorkflowService();
            ExpenseForm FormClassRows = new ExpenseForm();
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
    public final String approve(final Model model, final ExpenseForm ApplyForm)
            throws AccessSecurityException, IOException {
        // 過去に入力されたデータを一時保存テーブルから取得し、画面に渡す
        try {
            // 過去のデータをシステム案件IDで取得
            ExpenseWorkflowService service = new ExpenseWorkflowService();
            ExpenseForm formRows = new ExpenseForm();
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
    public final String detail(final Model model, final ExpenseForm ApplyForm)
            throws AccessSecurityException, IOException {
        // 過去に入力されたデータを一時保存テーブルから取得し、画面に渡す
        try {
            // 過去のデータをシステム案件IDで取得
            ExpenseWorkflowService service = new ExpenseWorkflowService();
            ExpenseForm formRows = new ExpenseForm();
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
     * 添付ファイルをダウンロードするメソッド。
     *
     * @param model   画面にデータを渡すためのModelオブジェクト
     * @param request リクエスト情報を持つHttpServletRequestオブジェクト
     * @return 遷移先ビュー名
     * @throws Exception ファイルダウンロード処理中に例外が発生した場合
     */
    @RequestMapping(value = "download/**")
    public String download(final Model model, HttpServletRequest request) throws Exception {

        // リクエストURLからファイルIDを取得する
        String urlStr = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String fileId = urlStr.substring(urlStr.lastIndexOf('/') + 1);

        // DBからファイル情報を取得する
        ExpenseAttachFileRepository FileRepository = new ExpenseAttachFileRepository();
        List<ExpenseAttachmentModel> rowsFile = new ArrayList<ExpenseAttachmentModel>(
                FileRepository.SelectTempInfo(fileId.toString(), "id"));
        String fileName = rowsFile.get(0).getFile_name();
        String fileRealPath = rowsFile.get(0).getFile_path();
        String fileDecode = URLDecoder.decode(fileRealPath.toString(), "UTF-8");

        // PublicStorageからファイルを取得する
        final PublicStorage storage = new PublicStorage(fileDecode);
        if (!storage.isFile()) {

            throw new FileNotFoundException("Could not find a file");
        }

        // ダウンロード用のファイル情報をモデルに設定する
        model.addAttribute("download_file_name", fileName);
        model.addAttribute("storage", storage);
        final String path = VIEW_PATH_DOWNLOAD;
        return path;
    }

}
