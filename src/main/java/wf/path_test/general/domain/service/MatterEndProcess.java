package wf.path_test.general.domain.service;



import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessEventListener;
import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;
import jp.co.intra_mart.framework.extension.spring.context.ApplicationContextProvider;

//Import from General
import wf.path_test.general.MatterEndProcessService;

public class MatterEndProcess extends MatterEndProcessEventListener {

    public MatterEndProcess() {
        super();
    }

    @Override
    public boolean execute(final MatterEndProcessParameter parameter) throws Exception {
        final MatterEndProcessService service = ApplicationContextProvider.getApplicationContext().getBean(MatterEndProcessService.class);
        return service.execute(parameter);
    }
}

