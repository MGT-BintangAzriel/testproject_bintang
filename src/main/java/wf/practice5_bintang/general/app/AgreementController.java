package wf.practice5_bintang.general.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import jp.co.intra_mart.foundation.database.SQLManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.intra_mart.foundation.context.Contexts;
import jp.co.intra_mart.foundation.context.model.AccountContext;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.information.Identifier;
import jp.co.intra_mart.foundation.workflow.code.PageType;
import wf.common.constant.MatterEndStatus;
import wf.common.constant.WorkflowCommonConstants;
import wf.practice5_bintang.general.constant.AgreementFormConstants;
import wf.practice5_bintang.general.domain.model.AgreementAttachmentModel;
import wf.practice5_bintang.general.domain.model.AgreementHeaderInfoModel;
import wf.practice5_bintang.general.domain.repository.AgreementAttachFileRepository;
import wf.practice5_bintang.general.domain.repository.AgreementAttachFileTempRepository;
import wf.practice5_bintang.general.domain.service.AgreementAutoApplyService;
import wf.practice5_bintang.general.domain.service.AgreementGeneratePDFService;
import wf.practice5_bintang.general.domain.service.AgreementWorkflowService;

@Controller
@RequestMapping("practice5_bintang/")
public class AgreementController {

	private static final String MODEL_KEY_SAVED_FORM_DATA = "savedFormData";
	private static final String MODEL_KEY_WORKFLOW_REQUEST_FORM = "workflowRequestForm";
	private static final String MODEL_KEY_DOWNLOAD_FILE_NAME = "download_file_name";
	private static final String MODEL_KEY_STORAGE = "storage";
	private static final String MODEL_KEY_ERROR_MESSAGE_ENG = "error_message_eng";
	private static final String MODEL_KEY_MATTER_COMPLETE = "matterComplete";

	private static final String BASE_VIEW_PATH = "wf/practice5_bintang/general/";
	private static final String VIEW_PATH_APPLY = BASE_VIEW_PATH + "apply.jsp";
	private static final String VIEW_PATH_APPROVE = BASE_VIEW_PATH + "approve.jsp";
	private static final String VIEW_PATH_APPROVE_PSD = BASE_VIEW_PATH + "approve_psd.jsp";
	private static final String VIEW_PATH_APPROVE_CCO = BASE_VIEW_PATH + "approve_cco.jsp";
	private static final String VIEW_PATH_APPROVE_LEGAL = BASE_VIEW_PATH + "approve_legal.jsp";
	private static final String VIEW_PATH_DETAIL = BASE_VIEW_PATH + "detail.jsp";
	private static final String VIEW_PATH_CONFIRM = BASE_VIEW_PATH + "confirm.jsp";
	private static final String VIEW_PATH_ERROR = BASE_VIEW_PATH + "error_screen.jsp";
	private static final String VIEW_PATH_DOWNLOAD = "AgreementDownloadAttachmentService.Downloadview";

	private static final String CHARSET_UTF8 = "UTF-8";
	private static final String ERR_MSG_UNAUTHORIZED = "(Unauthorized access: User is not logged in.)";
	private static final String ERR_MSG_SESSION_EXPIRED = "(Unauthorized access: Session has expired. Please log in again.)";
	private static final String ERR_MSG_LINK_INVALID = "(Access denied: Link is expired or invalid.)";
	private static final String ERR_MSG_ATTACHMENT_NOT_FOUND = "(Data not found: No attachments found for the specified matter ID.)";

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
			savedFormData = workflowService.getAgreementFormData(workflowRequestForm.getImwSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, request);
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
			AgreementForm savedFormData = workflowService.getAgreementFormData(workflowRequestForm.getImwSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, request);

			model.addAttribute(MODEL_KEY_SAVED_FORM_DATA, savedFormData);
			model.addAttribute(MODEL_KEY_WORKFLOW_REQUEST_FORM, workflowRequestForm);

			String nodeId = workflowRequestForm.getImwNodeId();

			if ("node_psd".equals(nodeId)) {
				return VIEW_PATH_APPROVE_PSD;
			} else if ("node_cco".equals(nodeId)) {
				return VIEW_PATH_APPROVE_CCO;
			} else if ("node_legal".equals(nodeId)) {
				return VIEW_PATH_APPROVE_LEGAL;
			}
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
			AgreementForm savedFormData = workflowService.getAgreementFormData(workflowRequestForm.getImwSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, request);

			boolean isMatterComplete = MatterEndStatus.MATTER_COMPLETE.getStatus().equals(workflowService.getMatterStatus(workflowRequestForm.getImwSystemMatterId()));

			model.addAttribute(MODEL_KEY_MATTER_COMPLETE, isMatterComplete);
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
			AgreementForm savedFormData = workflowService.getAgreementFormData(workflowRequestForm.getImwSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID, request);

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
	public String downloadAttachment(@PathVariable("fileId") int fileId, final Model model, HttpServletRequest request) throws Exception {
		String validateDownload = validateDownload(model, request);
		if (validateDownload != null) {
			return validateDownload;
		}

		String systemMatterId = request.getParameter(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);
		AgreementWorkflowService workflowService = new AgreementWorkflowService();
		boolean isMatterComplete = MatterEndStatus.MATTER_COMPLETE.getStatus().equals(workflowService.getMatterStatus(systemMatterId));

		AgreementAttachmentModel attachment;
		if (isMatterComplete) {
			AgreementAttachFileRepository attachFileDb = new AgreementAttachFileRepository();
			attachment = attachFileDb.selectAttachmentBySystemMatterIdAndFileId(fileId, systemMatterId);
		} else {
			AgreementAttachFileTempRepository attachFileTempDb = new AgreementAttachFileTempRepository();
			attachment = attachFileTempDb.selectAttachmentTempBySystemMatterIdAndFileId(fileId, systemMatterId);
		}

		if (attachment == null) {
			model.addAttribute(MODEL_KEY_ERROR_MESSAGE_ENG, ERR_MSG_ATTACHMENT_NOT_FOUND);
			return VIEW_PATH_ERROR;
		}

		String fileName = attachment.getFile_name();
		String fileRealPath = attachment.getFile_path();
		String fileDecode = URLDecoder.decode(fileRealPath.toString(), CHARSET_UTF8);

		final PublicStorage storage = new PublicStorage(fileDecode);
		if (!storage.isFile()) {
			throw new FileNotFoundException("Could not find attachment file: " + fileDecode);
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
			return ERR_MSG_UNAUTHORIZED;
		}

		String matterId = request.getParameter(WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);
		AgreementWorkflowService workflowService = new AgreementWorkflowService();
		boolean isMatterComplete = MatterEndStatus.MATTER_COMPLETE.getStatus().equals(workflowService.getMatterStatus(matterId));
		if (!isMatterComplete) {
			return "(Error: Cannot generate PDF for incomplete matter)";
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
	public String downloadPdf(@PathVariable("fileName") String fileName, final Model model, HttpServletRequest request) throws Exception {
		String validateDownload = validateDownload(model, request);
		if (validateDownload != null) {
			return validateDownload;
		}

		String fileNameDecode = URLDecoder.decode(fileName, CHARSET_UTF8);

		final PublicStorage storage = new PublicStorage(AgreementFormConstants.STORAGE_PATH_GENERATE_PDF + fileNameDecode);
		if (!storage.isFile()) {
			throw new FileNotFoundException("Could not find generated PDF file: " + fileNameDecode);
		}

		model.addAttribute(MODEL_KEY_DOWNLOAD_FILE_NAME, fileName);
		model.addAttribute(MODEL_KEY_STORAGE, storage);

		final String path = VIEW_PATH_DOWNLOAD;
		return path;
	}

	@GetMapping(value = "getPostData")
	@ResponseBody
	public String getPostData(final HttpServletRequest request) {
		AccountContext accountContext = Contexts.get(AccountContext.class);

		String userId = accountContext != null ? accountContext.getUserCd() : null;
		if (userId == null || userId.isEmpty() || "anonymous".equals(userId)) {
			return "{\"status\": 401, \"message\": \"Unauthorized\"}";
		}

		String postCode = request.getParameter("postCode");
		if (postCode == null || postCode.trim().isEmpty()) {
			return "{\"status\": 400, \"message\": \"Postal code is required\"}";
		}

		String cleanPostCode = postCode.replace("-", "").trim();

		HttpURLConnection con = null;
		try {
			String apiUrl = "https://zipcloud.ibsnet.co.jp/api/search?zipcode=" + cleanPostCode;

			URL url = new URL(apiUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);

			int responseCode = con.getResponseCode();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					responseCode == HttpURLConnection.HTTP_OK ? con.getInputStream() : con.getErrorStream()
			));
			
			String inputLine;
			StringBuilder response = new StringBuilder();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			con.disconnect();
			
			return response.toString();

		} catch (Exception e) {
			return "{\"status\": 500, \"message\": \"Error fetching postal code: " + e.getMessage() + "\"}";
		}
	}

	@RequestMapping(value = "testAutoApply")
	@ResponseBody
	public String testAutoApply() {
		try {
			AgreementAutoApplyService testService = new AgreementAutoApplyService();
			return testService.syncPending();
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR: " + e.getMessage();
		}
	}

	@RequestMapping(value = "testExternalDb")
	@ResponseBody
	public AgreementHeaderInfoModel testExternalDb() {
		try {
	    	SQLManager sqlManager = new SQLManager();
			String sql = "Select * FROM ext_agreement_header_info WHERE sync_status = 'PENDING'";
			Collection<AgreementHeaderInfoModel> result = sqlManager.select(AgreementHeaderInfoModel.class, sql, new ArrayList<>());

			return result.iterator().next();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "testJndi")
	@ResponseBody
	public String testJndi() {
		String[] testNames = {
			"java:comp/env/jdbc/db_external",
			"jdbc/external_db",
			"java:comp/env/jdbc/default"
		};

		StringBuilder sb = new StringBuilder();
		for (String name : testNames) {
			try {
				InitialContext ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup(name);
				try (Connection conn = ds.getConnection()) {
					sb.append("✅ SUCCESS: Found JNDI [" + name + "]! DB: " + conn.getCatalog() + "<br>");
				}
			} catch (Exception e) {
				sb.append("❌ FAILED: [" + name + "] -> " + e.getMessage() + "<br>");
			}
		}
		return sb.toString();
	}

	private String validateDownload(final Model model, HttpServletRequest request) {
		try {
			AccountContext accountContext = Contexts.get(AccountContext.class);
			String userId = accountContext.getUserCd();

			if (userId == null || userId.isEmpty() || "anonymous".equals(userId)) {
				model.addAttribute(MODEL_KEY_ERROR_MESSAGE_ENG, ERR_MSG_UNAUTHORIZED);
				return VIEW_PATH_ERROR;
			}
		} catch (Exception e) {
			model.addAttribute(MODEL_KEY_ERROR_MESSAGE_ENG, ERR_MSG_UNAUTHORIZED);
			return VIEW_PATH_ERROR;
		}

		HttpSession session = request.getSession(false);
		String clientToken = request.getParameter(AgreementFormConstants.PARAM_TOKEN);

		if (session == null || clientToken == null) {
			model.addAttribute(MODEL_KEY_ERROR_MESSAGE_ENG, ERR_MSG_SESSION_EXPIRED);
			return VIEW_PATH_ERROR;
		}

		String sessionToken = (String) session.getAttribute(AgreementFormConstants.SESSION_KEY_DOWNLOAD_TOKEN);

		if (!clientToken.equals(sessionToken)) {
			model.addAttribute(MODEL_KEY_ERROR_MESSAGE_ENG, ERR_MSG_LINK_INVALID);
			return VIEW_PATH_ERROR;
		}
		return null;
	}

}
