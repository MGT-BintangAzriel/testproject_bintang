package wf.practice5_bintang.general;

import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;

public interface AgreementMatterEndProcessService {

    boolean execute(final MatterEndProcessParameter parameter) throws Exception;

}
