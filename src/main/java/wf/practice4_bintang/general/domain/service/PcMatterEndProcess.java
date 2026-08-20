package wf.practice4_bintang.general.domain.service;

import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessEventListener;
import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;
import jp.co.intra_mart.framework.extension.spring.context.ApplicationContextProvider;
import wf.practice4_bintang.general.PcMatterEndProcessService;

/**
 * PC購入申請の案件終了処理クラス。
 * MatterEndProcessEventListenerのexecuteメソッドをオーバーライドし、
 * PC購入申請の案件終了に関するビジネスロジックを実装する。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class PcMatterEndProcess extends MatterEndProcessEventListener {

    public PcMatterEndProcess() {
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
        final PcMatterEndProcessService service = ApplicationContextProvider.getApplicationContext()
                .getBean(PcMatterEndProcessService.class);
        return service.execute(parameter);
    }

}
