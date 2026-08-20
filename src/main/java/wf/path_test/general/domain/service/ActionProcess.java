package wf.path_test.general.domain.service;



import java.util.Map;


import jp.co.intra_mart.foundation.workflow.plugin.process.action.ActionProcessEventListener;
import jp.co.intra_mart.foundation.workflow.plugin.process.action.ActionProcessParameter;
import jp.co.intra_mart.framework.extension.spring.context.ApplicationContextProvider;


//Import from General Package
import wf.path_test.general.ActionProcessService;

public class ActionProcess extends ActionProcessEventListener  {

    // 申請
    @Override
    public final String apply(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        return service.apply(parameter, userParameter);
        
    }

    // 申請（一時保存）
    @Override
    public final String applyFromTempSave(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        return service.applyFromTempSave(parameter, userParameter);
    }

    // 申請(未申請状態案件)
    @Override
    public final String applyFromUnapply(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        return service.applyFromUnapply(parameter, userParameter);
    }

    // 承認
    @Override
    public void approve(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        
        service.approve(parameter, userParameter);
    }

    // 承認終了
    @Override
    public final void approveEnd(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        service.approveEnd(parameter, userParameter);
    }

    // 否認
    @Override
    public final void deny(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        service.deny(parameter, userParameter);
    }

    // 取止め
    @Override
    public final void discontinue(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        service.discontinue(parameter, userParameter);
    }

    // 案件操作
    @Override
    public final void matterHandle(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        service.matterHandle(parameter, userParameter);
    }

    // 引戻し
    @Override
    public final void pullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        service.pullBack(parameter, userParameter);
    }

    // 再申請
    @Override
    public final String reapply(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        return service.reapply(parameter, userParameter);
    }

    // 保留
    @Override
    public final void reserve(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        service.reserve(parameter, userParameter);
    }

    // 保留解除
    @Override
    public final void reserveCancel(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        service.reserveCancel(parameter, userParameter);
    }

    // 差戻し
    @Override
    public final void sendBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        service.sendBack(parameter, userParameter);
    }

    // 差戻し後引戻し
    @Override
    public final void sendBackToPullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        service.sendBackToPullBack(parameter, userParameter);
    }

    // 一時保存(新規登録)
    @Override
    public final void tempSaveCreate(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        service.tempSaveCreate(parameter, userParameter);
    }

    // 一時保存(削除)
    @Override
    public final void tempSaveDelete(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        service.tempSaveDelete(parameter, userParameter);
    }

    // 一時保存(更新)
    @Override
    public final void tempSaveUpdate(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        // アクション処理用のサービスを取得します。
        final ActionProcessService service = ApplicationContextProvider.getApplicationContext().getBean(ActionProcessService.class);
        service.tempSaveUpdate(parameter, userParameter);
    }
}