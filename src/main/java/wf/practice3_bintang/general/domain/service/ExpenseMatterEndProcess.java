package wf.practice3_bintang.general.domain.service;

import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessEventListener;
import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;
import jp.co.intra_mart.framework.extension.spring.context.ApplicationContextProvider;

import wf.practice3_bintang.general.ExpenseMatterEndProcessService;

/**
 * 経費申請の案件終了処理クラス。
 * MatterEndProcessEventListenerのexecuteメソッドをオーバーライドし、
 * 経費申請の案件終了に関するビジネスロジックを実装する。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class ExpenseMatterEndProcess extends MatterEndProcessEventListener {

    public ExpenseMatterEndProcess() {
        super();
    }

    /**
     * 案件終了時のDB更新処理を行うメソッド。
     *
     * @param parameter ワークフローのパラメータ
     * @return 成功した場合はtrue
     * @throws Exception 処理中に例外が発生した場合
     */
    @Override
    public boolean execute(final MatterEndProcessParameter parameter) throws Exception {
        final ExpenseMatterEndProcessService service = ApplicationContextProvider.getApplicationContext()
                .getBean(ExpenseMatterEndProcessService.class);
        return service.execute(parameter);
    }

}
