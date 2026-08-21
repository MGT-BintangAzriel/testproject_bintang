package wf.practice5_bintang.general.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.information.Identifier;
import jp.co.intra_mart.foundation.workflow.code.PageType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import wf.common.constant.MatterEndStatus;
import wf.common.constant.WorkflowCommonConstants;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.repository.AgreementAttachFileTempRepository;
import wf.practice5_bintang.general.domain.service.AgreementGeneratePDFService;
import wf.practice5_bintang.general.domain.service.AgreementWorkflowService;

@Controller
@RequestMapping("practice5_bintang/")
public class AgreementController {

	private static final String MODEL_KEY_SAVED_FORM_DATA = "savedFormData";
	private static final String MODEL_KEY_WORKFLOW_REQUEST_FORM = "workflowRequestForm";
	private static final String MODEL_KEY_DOWNLOAD_FILE_NAME = "download_file_name";
    private static final String MODEL_KEY_STORAGE = "storage";

	private static final String BASE_VIEW_PATH = "wf/practice5_bintang/general/";
	private static final String VIEW_PATH_APPLY = BASE_VIEW_PATH + "apply.jsp";
	private static final String VIEW_PATH_APPROVE = BASE_VIEW_PATH + "approve.jsp";
	private static final String VIEW_PATH_DETAIL = BASE_VIEW_PATH + "detail.jsp";
	private static final String VIEW_PATH_DOWNLOAD = "AgreementDownloadAttachmentService.Downloadview";
	
	@RequestMapping(value = "apply")
	public final String apply(final Model model, final AgreementForm workflowRequestForm) throws Exception {

		AgreementForm savedFormData = new AgreementForm();
		AgreementWorkflowService workflowService = new AgreementWorkflowService();

		if (PageType.pageTyp_App.toString().equals(workflowRequestForm.getImwPageType())) {
			String userDataId = "";
			final Identifier identifier = new Identifier();
			userDataId = identifier.get();
			workflowRequestForm.setImwUserDataId(userDataId);

			savedFormData = workflowService.getHeaderInfoTempFormApply();

		} else {
			savedFormData = workflowService.getHeaderInfoTempForm(workflowRequestForm.getImwSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);
		}

		model.addAttribute(MODEL_KEY_SAVED_FORM_DATA, savedFormData);
		model.addAttribute(MODEL_KEY_WORKFLOW_REQUEST_FORM, workflowRequestForm);
		final String path = VIEW_PATH_APPLY;
		return path;
	}

	@RequestMapping(value = "approve")
	public final String approve(final Model model, final AgreementForm workflowRequestForm) throws AccessSecurityException, IOException {
		try {
			AgreementWorkflowService workflowService = new AgreementWorkflowService();
			AgreementForm savedFormData = workflowService.getHeaderInfoTempForm(workflowRequestForm.getImwSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

			model.addAttribute(MODEL_KEY_SAVED_FORM_DATA, savedFormData);
			model.addAttribute(MODEL_KEY_WORKFLOW_REQUEST_FORM, workflowRequestForm);
		} catch (Exception e) {
			System.out.println("Error Approve Exception: " + e.getMessage());
			e.printStackTrace();
		}

		final String path = VIEW_PATH_APPROVE;
		return path;
	}

	@RequestMapping(value = "detail")
	public final String detail(final Model model, final AgreementForm workflowRequestForm) throws AccessSecurityException, IOException {
		try {
			AgreementWorkflowService workflowService = new AgreementWorkflowService();
			AgreementForm savedFormData = workflowService.getHeaderInfoTempForm(workflowRequestForm.getImwSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);
			boolean isMatterComplete = MatterEndStatus.MATTER_COMPLETE.getStatus().equals(workflowService.getMatterStatus(workflowRequestForm.getImwSystemMatterId()));
			
			model.addAttribute("matterComplete", isMatterComplete);
			model.addAttribute(MODEL_KEY_SAVED_FORM_DATA, savedFormData);
			model.addAttribute(MODEL_KEY_WORKFLOW_REQUEST_FORM, workflowRequestForm);
		} catch (Exception e) {
			System.out.println("Error Detail Exception: " + e.getMessage());
			e.printStackTrace();
		}

		final String path = VIEW_PATH_DETAIL;
		return path;
	}

	@RequestMapping(value = "download/**")
	public String download(final Model model, HttpServletRequest request) throws Exception {

		String urlStr = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String fileId = urlStr.substring(urlStr.lastIndexOf('/') + 1);

		AgreementAttachFileTempRepository FileRepository = new AgreementAttachFileTempRepository();
		List<AgreementAttachmentModel> rowsFile = new ArrayList<AgreementAttachmentModel>(FileRepository.selectAttachmentTemp(fileId.toString(), "id"));
		String fileName = rowsFile.get(0).getFile_name();
		String fileRealPath = rowsFile.get(0).getFile_path();
		String fileDecode = URLDecoder.decode(fileRealPath.toString(), "UTF-8");

		final PublicStorage storage = new PublicStorage(fileDecode);
		if (!storage.isFile()) {

			throw new FileNotFoundException("Could not find a file");
		}

		model.addAttribute(MODEL_KEY_DOWNLOAD_FILE_NAME, fileName);
		model.addAttribute(MODEL_KEY_STORAGE, storage);
		final String path = VIEW_PATH_DOWNLOAD;
		return path;
	}
	
	@PostMapping("generatepdf")
    @ResponseBody
    public String generatepdf(final HttpServletRequest request) throws Exception {

        String MatterId = request.getParameter(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

        try {
            AgreementGeneratePDFService pdfGenerate = new AgreementGeneratePDFService();
            String pdfFileName = pdfGenerate.createPDF(MatterId);
            return pdfFileName;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error:" + e.getMessage();
        }

    }

    @RequestMapping(value = "downloadpdf/**")
    public String downloadpdf(final Model model, HttpServletRequest request) throws Exception {

        String urlStr = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        
        String fileNameRaw = urlStr.substring(urlStr.lastIndexOf('/') + 1);
        String fileName = URLDecoder.decode(fileNameRaw, "UTF-8");

        final PublicStorage storage = new PublicStorage("generate_pdf/" + fileName);
        if (!storage.isFile()) {
            throw new FileNotFoundException("Could not find a file");
        }

        model.addAttribute(MODEL_KEY_DOWNLOAD_FILE_NAME, fileName);
        model.addAttribute(MODEL_KEY_STORAGE, storage);

        final String path = VIEW_PATH_DOWNLOAD;
        return path;
    }

}
