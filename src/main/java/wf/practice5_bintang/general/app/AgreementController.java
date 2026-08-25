package wf.practice5_bintang.general.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.information.Identifier;
import jp.co.intra_mart.foundation.workflow.code.PageType;
import jp.co.intra_mart.foundation.context.model.AccountContext;
import jp.co.intra_mart.foundation.context.Contexts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	private static final String VIEW_PATH_CONFIRM = BASE_VIEW_PATH + "confirm.jsp";
	private static final String VIEW_PATH_DOWNLOAD = "AgreementDownloadAttachmentService.Downloadview";
	
	@RequestMapping(value = "apply")
	public final String apply(final Model model, final AgreementForm workflowRequestForm, final HttpServletRequest request) throws Exception {

		AgreementForm savedFormData = new AgreementForm();
		AgreementWorkflowService workflowService = new AgreementWorkflowService();

		if (PageType.pageTyp_App.toString().equals(workflowRequestForm.getImwPageType())) {
			String userDataId = "";
			final Identifier identifier = new Identifier();
			userDataId = identifier.get();
			workflowRequestForm.setImwUserDataId(userDataId);

			savedFormData = workflowService.getHeaderInfoTempFormApply();

		} else {
			savedFormData = workflowService.getHeaderInfoTempForm(workflowRequestForm.getImwSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, request);
		}

		model.addAttribute(MODEL_KEY_SAVED_FORM_DATA, savedFormData);
		model.addAttribute(MODEL_KEY_WORKFLOW_REQUEST_FORM, workflowRequestForm);
		final String path = VIEW_PATH_APPLY;
		return path;
	}

	@RequestMapping(value = "approve")
	public final String approve(final Model model, final AgreementForm workflowRequestForm, final HttpServletRequest request) throws AccessSecurityException, IOException {
		try {
			AgreementWorkflowService workflowService = new AgreementWorkflowService();
			AgreementForm savedFormData = workflowService.getHeaderInfoTempForm(workflowRequestForm.getImwSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, request);

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
	public final String detail(final Model model, final AgreementForm workflowRequestForm, final HttpServletRequest request) throws AccessSecurityException, IOException {
		try {
			AgreementWorkflowService workflowService = new AgreementWorkflowService();
			AgreementForm savedFormData = workflowService.getHeaderInfoTempForm(workflowRequestForm.getImwSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, request);
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
	
	@RequestMapping(value = "confirm")
	public final String confirm(final Model model, final AgreementForm workflowRequestForm, final HttpServletRequest request) throws AccessSecurityException, IOException {
		try {
			AgreementWorkflowService workflowService = new AgreementWorkflowService();
			AgreementForm savedFormData = workflowService.getHeaderInfoTempForm(workflowRequestForm.getImwSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, request);
			
			model.addAttribute(MODEL_KEY_SAVED_FORM_DATA, savedFormData);
			model.addAttribute(MODEL_KEY_WORKFLOW_REQUEST_FORM, workflowRequestForm);
		} catch (Exception e) {
			System.out.println("Error Confirm Exception: " + e.getMessage());
			e.printStackTrace();
		}

		final String path = VIEW_PATH_CONFIRM;
		return path;
	}

	@RequestMapping(value = "download/{fileId}")
	public String downloadAttachment(@PathVariable("fileId") int fileId, final Model model, HttpServletRequest request) throws Exception{
		String validateDownload = validateDownload(model, request);
		if(validateDownload != null) {
			return validateDownload;
		}

		String systemMatterId = request.getParameter(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);
		AgreementAttachFileTempRepository attachFileTempDb = new AgreementAttachFileTempRepository();
		AgreementAttachmentModel attachment = attachFileTempDb.selectAttachmentTempBySystemMatterIdAndFileId(fileId, systemMatterId);
		
		if(attachment == null) {
			model.addAttribute("error_message_eng", "(Data not found: No attachments found for the specified matter ID.)");
        	return "wf/practice5_bintang/general/error_screen.jsp";
		}

		String fileName = attachment.getFile_name();
		String fileRealPath = attachment.getFile_path();
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
		AccountContext accountContext = Contexts.get(AccountContext.class);
	    String userId = accountContext != null ? accountContext.getUserCd() : null;
	    if (userId == null || userId.isEmpty() || "anonymous".equals(userId)) {
			return "Unauthorized access: User is not logged in.";
		}
	    
	    String matterId = request.getParameter(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);
	    AgreementWorkflowService workflowService = new AgreementWorkflowService();
	    boolean isMatterComplete = MatterEndStatus.MATTER_COMPLETE.getStatus().equals(workflowService.getMatterStatus(matterId));
	    if (!isMatterComplete) {
	        return "Error: Cannot generate PDF for incomplete matter";
	    }

        try {
            AgreementGeneratePDFService pdfGenerate = new AgreementGeneratePDFService();
            String pdfFileName = pdfGenerate.createPDF(matterId);
            return pdfFileName;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error:" + e.getMessage();
        }

    }

	@RequestMapping(value = "downloadpdf/{fileName}")
	public String downloadPdf(@PathVariable("fileName") String fileName, final Model model, HttpServletRequest request) throws Exception{
		String validateDownload = validateDownload(model, request);
		if(validateDownload != null) {
			return validateDownload;
		}

        String fileNameDecode = URLDecoder.decode(fileName, "UTF-8");

        final PublicStorage storage = new PublicStorage("generate_pdf/" + fileNameDecode);
        if (!storage.isFile()) {
            throw new FileNotFoundException("Could not find a file");
        }

        model.addAttribute(MODEL_KEY_DOWNLOAD_FILE_NAME, fileName);
        model.addAttribute(MODEL_KEY_STORAGE, storage);

        final String path = VIEW_PATH_DOWNLOAD;
        return path;
	}

	private String validateDownload(final Model model, HttpServletRequest request) {
		try {
			AccountContext accountContext = Contexts.get(AccountContext.class);
			String userId = accountContext.getUserCd();

			if (userId == null || userId.isEmpty() || "anonymous".equals(userId)) {
				model.addAttribute("error_message_eng", "(Unauthorized access: User is not logged in.)");
	        	return "wf/practice5_bintang/general/error_screen.jsp";
			}
		} catch (Exception e) {
			model.addAttribute("error_message_eng", "(Unauthorized access: User is not logged in.)");
        	return "wf/practice5_bintang/general/error_screen.jsp";
		}

		HttpSession session = request.getSession(false);
		String clientToken = request.getParameter("token");

		if (session == null || clientToken == null) {
			model.addAttribute("error_message_eng", "(Unauthorized access: Session has expired. Please log in again.)");
        	return "wf/practice5_bintang/general/error_screen.jsp";
		}

		String sessionToken = (String) session.getAttribute("agreement_download_token");

		if (!clientToken.equals(sessionToken)) {
        	model.addAttribute("error_message_eng", "(Access denied: Link is expired or invalid.)");
        	return "wf/practice5_bintang/general/error_screen.jsp";
	    }
		return null;
	}

}
