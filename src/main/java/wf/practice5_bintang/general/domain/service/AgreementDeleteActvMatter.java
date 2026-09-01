package wf.practice5_bintang.general.domain.service;

import jp.co.intra_mart.foundation.workflow.exception.WorkflowException;
import jp.co.intra_mart.foundation.workflow.listener.IWorkflowActvMatterDeleteListener;

public class AgreementDeleteActvMatter implements IWorkflowActvMatterDeleteListener {

	@Override
	public void execute(final String loginGroupId, final String localeId, final String systemMatterId, final String userDataId) throws WorkflowException {
		System.out.println("----- WorkflowActvMatterDeleteListener - execute -----");
		System.out.println("LoginGroupId        : " + loginGroupId);
		System.out.println("LocaleId            : " + localeId);
		System.out.println("systemMatterId      : " + systemMatterId);
		System.out.println("userDataId          : " + userDataId);
		System.out.println("----- WorkflowActvMatterDeleteListener - execute -----");
		
	}

}
