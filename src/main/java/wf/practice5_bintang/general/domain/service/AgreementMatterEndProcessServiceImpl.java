package wf.practice5_bintang.general.domain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.intra_mart.foundation.exception.BizApiException;
import jp.co.intra_mart.foundation.master.user.UserManager;
import jp.co.intra_mart.foundation.master.user.model.User;
import jp.co.intra_mart.foundation.master.user.model.UserBizKey;
import jp.co.intra_mart.foundation.workflow.application.general.ActvMatter;
import jp.co.intra_mart.foundation.workflow.application.general.CplMatter;
import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;
import wf.common.constant.MailStatus;
import wf.common.constant.MatterEndStatus;
import wf.common.constant.WorkflowCommonConstants;
import wf.common.constant.WorkflowStatus;
import wf.practice5_bintang.general.AgreementMatterEndProcessService;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderModel;
import wf.practice5_bintang.general.domain.model.AgreementPaymentDetailModel;
import wf.practice5_bintang.general.domain.repository.AgreementAttachFileRepository;
import wf.practice5_bintang.general.domain.repository.AgreementAttachFileTempRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderInfoRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderInfoTempRepository;
import wf.practice5_bintang.general.domain.repository.AgreementHeaderRepository;
import wf.practice5_bintang.general.domain.repository.AgreementPaymentDetailRepository;
import wf.practice5_bintang.general.domain.repository.AgreementPaymentDetailTempRepository;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class AgreementMatterEndProcessServiceImpl implements AgreementMatterEndProcessService {

	@Override
	public boolean execute(MatterEndProcessParameter parameter) throws Exception {
		AgreementHeaderRepository agreementHeaderDb = new AgreementHeaderRepository();
		AgreementHeaderInfoRepository agreementHeaderInfoDb = new AgreementHeaderInfoRepository();
		AgreementHeaderInfoTempRepository agreementHeaderInfoTempDb = new AgreementHeaderInfoTempRepository();
		AgreementPaymentDetailRepository agreementPaymentDetailDb = new AgreementPaymentDetailRepository();
		AgreementPaymentDetailTempRepository agreementPaymentDetailTempDb = new AgreementPaymentDetailTempRepository();
		AgreementAttachFileRepository agreementAttachFileDb = new AgreementAttachFileRepository();
		AgreementAttachFileTempRepository agreementAttachFileTempDb = new AgreementAttachFileTempRepository();

		AgreementHeaderModel headerModel = agreementHeaderDb.selectHeader(parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID).iterator().next();
		AgreementHeaderInfoModel headerInfoTempModel = agreementHeaderInfoTempDb.selectHeaderInfoTemp(parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID).iterator().next();
		List<AgreementPaymentDetailModel> paymentDetailTempModel = new ArrayList<>(agreementPaymentDetailTempDb.selectPaymentDetailTemp(parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID));
		List<AgreementAttachmentModel> attachmentTempModel = new ArrayList<>(agreementAttachFileTempDb.selectAttachmentTemp(parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID));

		if (MatterEndStatus.MATTER_COMPLETE.getStatus().equals(parameter.getLastResultStatus())) {
			headerModel.setStatus(WorkflowStatus.COMPLETED.getCode());
			headerModel.setMail_status(MailStatus.UNSENT.getCode());
			agreementHeaderDb.updateHeader(headerModel);
			agreementHeaderInfoDb.insertHeaderInfo(headerInfoTempModel);

			for (int i = 0; i < paymentDetailTempModel.size(); i++) {
				AgreementPaymentDetailModel model = new AgreementPaymentDetailModel();
				model = paymentDetailTempModel.get(i);
				agreementPaymentDetailDb.insertPaymentDetail(model);
			}

			for (int i = 0; i < attachmentTempModel.size(); i++) {
				AgreementAttachmentModel model = new AgreementAttachmentModel();
				model = attachmentTempModel.get(i);
				agreementAttachFileDb.insertAttachment(model);
			}

			// Get information related to the matter
			String matterId = parameter.getSystemMatterId();
			List<String> matterData = this.getMatterData(matterId);
			
			AgreementGeneratePDFService generatePDFService = new AgreementGeneratePDFService();
			String pdfFileName = generatePDFService.createPDF(matterId);
			
			AgreementEmailService mailService = new AgreementEmailService();
			mailService.sendApprovalNotificationEmail(matterId, matterData.get(0), matterData.get(1), matterData.get(2), matterData.get(3), pdfFileName);

		} else if (MatterEndStatus.DENY.getStatus().equals(parameter.getLastResultStatus())) {
			headerModel.setStatus(WorkflowStatus.DENIED.getCode());
			agreementHeaderDb.updateHeader(headerModel);
		}

		return true;
	}

	private List<String> getMatterData(String matterId) {
		List<String> matterData = new ArrayList<String>();
		
		String matterNumber = "";
		String matterName = "";
		String matterDatetime = "";
		String matterDate = "";
		String matterApplicantCode = "";
		String recipientEmail = "emailnotfound@gmail.com";

		try {
			CplMatter cplMatter = new CplMatter(matterId);
			matterNumber = cplMatter.getMatter().getMatterNumber();
			matterName = cplMatter.getMatter().getMatterName();
			matterDatetime = cplMatter.getMatter().getApplyDate();
			matterApplicantCode = cplMatter.getMatter().getApplyAuthUserCode();
		} catch (Exception e) {
			try {
				ActvMatter actvMatter = new ActvMatter(matterId);
				matterNumber = actvMatter.getMatter().getMatterNumber();
				matterName = actvMatter.getMatter().getMatterName();
				matterDatetime = actvMatter.getMatter().getApplyDate();
				matterApplicantCode = actvMatter.getMatter().getApplyAuthUserCode();
			} catch (Exception e2) {
				System.out.println("Error on matter data retrieval");
				e.printStackTrace();
			}
			System.out.println("Mail sent using Active Matter data");
			e.printStackTrace();
		}

		if (matterDatetime != null && matterDatetime.contains(" ")) {
			matterDate = matterDatetime.split(" ")[0];
		} else {
			matterDate = matterDatetime != null ? matterDatetime : "";
		}
		
		try {
			UserManager userManager;
			User user;

			userManager = new UserManager();
			UserBizKey userBizKey = new UserBizKey();
			userBizKey.setUserCd(matterApplicantCode);
			
			user = userManager.getUser(userBizKey, new Date());
			
			if (user != null) {
				recipientEmail = user.getEmailAddress1();
			}
		} catch (BizApiException e1) {
			e1.printStackTrace();
		}
			
		matterData.add(recipientEmail);
		matterData.add(matterNumber);
		matterData.add(matterName);
		matterData.add(matterDate);

		return matterData;
	}
}
