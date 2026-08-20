package wf.path_test.general;


import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;

public interface MatterEndProcessService {

    boolean execute(final MatterEndProcessParameter parameter) throws Exception;

}

