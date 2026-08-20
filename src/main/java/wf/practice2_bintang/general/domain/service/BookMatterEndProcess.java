package wf.practice2_bintang.general.domain.service;

import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessEventListener;
import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;
import jp.co.intra_mart.framework.extension.spring.context.ApplicationContextProvider;

import wf.practice2_bintang.general.BookMatterEndProcessService;

/**
 * 書籍申請の案件終了処理クラス。
 * MatterEndProcessEventListenerのexecuteメソッドをオーバーライドし、
 * 書籍申請の案件終了に関するビジネスロジックを実装する。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class BookMatterEndProcess extends MatterEndProcessEventListener {

    public BookMatterEndProcess() {
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
        final BookMatterEndProcessService service = ApplicationContextProvider.getApplicationContext()
                .getBean(BookMatterEndProcessService.class);
        return service.execute(parameter);
    }

}
