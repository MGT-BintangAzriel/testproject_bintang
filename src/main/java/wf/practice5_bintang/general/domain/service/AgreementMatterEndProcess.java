package wf.practice5_bintang.general.domain.service;

import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessEventListener;
import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;
import jp.co.intra_mart.framework.extension.spring.context.ApplicationContextProvider;
import wf.practice5_bintang.general.AgreementMatterEndProcessService;

public class AgreementMatterEndProcess extends MatterEndProcessEventListener {

    public AgreementMatterEndProcess() {
        super();
    }

    @Override
    public boolean execute(final MatterEndProcessParameter parameter) throws Exception {
        final AgreementMatterEndProcessService service = ApplicationContextProvider.getApplicationContext()
                .getBean(AgreementMatterEndProcessService.class);
        return service.execute(parameter);
    }

}
