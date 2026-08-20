package wf.practice5_bintang.general.domain.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;

import wf.common.constant.WorkflowCommonConstants;
import wf.common.constant.WorkflowStatus;
import wf.practice5_bintang.general.AgreementMatterEndProcessService;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoTempModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderModel;
import wf.practice5_bintang.general.domain.repository.AgreementAttachFileRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderInfoRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderInfoTempRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderRepository;
import wf.practice5_bintang.general.domain.repository.AgreementPaymentDetailRepository;
import wf.common.constant.MatterEndStatus;
import wf.common.constant.MailStatus;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class AgreementMatterEndProcessServiceImpl implements AgreementMatterEndProcessService {

	@Override
	public boolean execute(MatterEndProcessParameter parameter) throws Exception {
		AgreementWorkflowService workflowService = new AgreementWorkflowService();

		AgreementHeaderRepository agreementHeaderDb = new AgreementHeaderRepository();
		AgreementHeaderInfoRepository agreementHeaderInfoDb = new AgreementHeaderInfoRepository();
		AgreementHeaderInfoTempRepository agreementHeaderInfoTempDb = new AgreementHeaderInfoTempRepository();
		AgreementAttachFileRepository agreementAttachFileDb = new AgreementAttachFileRepository();
		AgreementPaymentDetailRepository agreementPaymentDetailDb = new AgreementPaymentDetailRepository();

		AgreementHeaderModel headerModel = agreementHeaderDb
				.selectHeader(parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID)
				.iterator().next();
		Collection<AgreementHeaderInfoTempModel> tempHeaderInfoModels = agreementHeaderInfoTempDb
				.selectHeaderInfoTemp(parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);
		AgreementHeaderInfoModel headerInfoModel = workflowService.moveTempHeaderToInfoHeader(tempHeaderInfoModels);

		if (MatterEndStatus.MATTER_COMPLETE.getStatus().equals(parameter.getLastResultStatus())) {
			headerModel.setStatus(WorkflowStatus.COMPLETED.getCode());
			headerModel.setMail_status(MailStatus.UNSENT.getCode());
			agreementHeaderDb.updateHeader(headerModel);
			agreementHeaderInfoDb.insertHeaderInfo(headerInfoModel);

			String matterId = parameter.getSystemMatterId();
			agreementAttachFileDb.moveAttachmentFromTempToMain(matterId);
			agreementPaymentDetailDb.movePaymentDetailFromTempToMain(matterId);

		} else if (MatterEndStatus.DENY.getStatus().equals(parameter.getLastResultStatus())) {
			headerModel.setStatus(WorkflowStatus.DENIED.getCode());
			agreementHeaderDb.updateHeader(headerModel);
		}

		return true;

	}

}
