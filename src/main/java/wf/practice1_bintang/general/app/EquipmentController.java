package wf.practice1_bintang.general.app;

import java.io.IOException;

import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import jp.co.intra_mart.foundation.service.client.information.Identifier;
import jp.co.intra_mart.foundation.workflow.code.PageType;

import wf.common.constant.WorkflowCommonConstants;
import wf.practice1_bintang.general.domain.service.EquipmentWorkflowService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 備品申請の画面遷移を制御するコントローラクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
@Controller
@RequestMapping("practice1_bintang/")
public class EquipmentController {

    // 画面に渡すフォーム名を定数化する
    private static final String MODEL_KEY_FORM_ROWS = "savedFormData";
    private static final String MODEL_KEY_APPLY_FORM = "workflowRequestForm";

    // JSPの共通パス
    private static final String BASE_VIEW_PATH = "wf/practice5_bintang/general/";
    // 申請画面のJSPパス
    private static final String VIEW_PATH_APPLY = BASE_VIEW_PATH + "apply.jsp";
    // 承認画面のJSPパス
    private static final String VIEW_PATH_APPROVE = BASE_VIEW_PATH + "approve.jsp";
    // 詳細確認画面のJSPパス
    private static final String VIEW_PATH_DETAIL = BASE_VIEW_PATH + "detail.jsp";

    /**
     * 申請画面・再申請画面の処理を行うメソッド。
     *
     * @param model     画面にデータを渡すためのModelオブジェクト
     * @param ApplyForm ワークフローの遷移パラメータを保持するフォームオブジェクト
     * @return 遷移先JSPのパス
     * @throws Exception 画面表示時に例外が発生した場合
     */
    @RequestMapping(value = "apply")
    public final String apply(final Model model, final EquipmentForm ApplyForm) throws Exception {

        // 新規申請の場合、ユーザデータIDを発行し、設定する
        if (PageType.pageTyp_App.toString().equals(ApplyForm.getImwPageType())) {
            String userDataId = "";
            final Identifier identifier = new Identifier();
            userDataId = identifier.get();

            // ユーザデータIDをフォームに設定する
            ApplyForm.setImwUserDataId(userDataId);

            // 再申請の場合、過去に入力されたデータを一時保存テーブルから取得し、画面に渡す
        } else {
            // 過去のデータをシステム案件IDで取得
            EquipmentWorkflowService service = new EquipmentWorkflowService();
            EquipmentForm FormClassRows = new EquipmentForm();
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
    public final String approve(final Model model, final EquipmentForm ApplyForm)
            throws AccessSecurityException, IOException {

        // 過去に入力されたデータを一時保存テーブルから取得し、画面に渡す
        try {
            // 過去のデータをシステム案件IDで取得
            EquipmentWorkflowService service = new EquipmentWorkflowService();
            EquipmentForm formRows = new EquipmentForm();
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
    public final String detail(final Model model, final EquipmentForm ApplyForm)
            throws AccessSecurityException, IOException {

        // 過去に入力されたデータを一時保存テーブルから取得し、画面に渡す
        try {
            // 過去のデータをシステム案件IDで取得
            EquipmentWorkflowService service = new EquipmentWorkflowService();
            EquipmentForm formRows = new EquipmentForm();
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

}
