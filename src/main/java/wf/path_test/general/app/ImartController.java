package wf.path_test.general.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.information.Identifier;
import jp.co.intra_mart.foundation.workflow.code.PageType;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import wf.path_test.general.domain.model.AttachmentModel;
import wf.path_test.general.domain.model.VendorModel;
import wf.path_test.general.domain.repository.*;
import wf.path_test.general.domain.service.*;

@Controller
@RequestMapping("path_test/")
public class ImartController {

	@RequestMapping(value = "apply")
	public final String apply(final Model model, final ImartForm ApplyForm) throws Exception {

		if (PageType.pageTyp_App.toString().equals(ApplyForm.getImwPageType())) {
			String userDataId = "";
			final Identifier identifier = new Identifier();
			userDataId = identifier.get();

			ApplyForm.setImwUserDataId(userDataId);

		} else {
			System.out.println("============GO TO REAPPLY ============");

			WorkflowService Service = new WorkflowService();
			ImartForm FormClassRows = new ImartForm();
			FormClassRows = Service.getInfoTemp(ApplyForm.getImwSystemMatterId(), "system_matter_id");

			Collection<VendorModel> vendorList = Service.getVendorList();

			model.addAttribute("vendorList", vendorList);
			model.addAttribute("FormClassRows", FormClassRows);

		}

		WorkflowService Service = new WorkflowService();
		Collection<VendorModel> vendorList = Service.getVendorList();

		ObjectMapper vendorObjMapper = new ObjectMapper();
		String jsonVendor = vendorObjMapper.writeValueAsString(vendorList);

		model.addAttribute("jsonVendor", jsonVendor);
		model.addAttribute("ApplyForm", ApplyForm);
		return "wf/path_test/general/apply.jsp";
	}

	@RequestMapping(value = "approve")
	public final String approve(final Model model, final ImartForm ApplyForm)
			throws AccessSecurityException, IOException {
		try {
			WorkflowService Service = new WorkflowService();
			ImartForm FormClassRows = new ImartForm();
			FormClassRows = Service.getInfoTemp(ApplyForm.getImwSystemMatterId(), "system_matter_id");

			// Set Master Data
			Collection<VendorModel> vendorList = Service.getVendorList();

			model.addAttribute("vendorList", vendorList);
			model.addAttribute("FormClassRows", FormClassRows);
			model.addAttribute("ApplyForm", ApplyForm);

		} catch (Exception e) {
			System.out.println("Error Approve :" + e);
		}

		return "wf/path_test/general/approve.jsp";
	}

	@RequestMapping(value = "detail")
	public final String detail(final Model model, final ImartForm ApplyForm)
			throws AccessSecurityException, IOException {
		try {
			WorkflowService Service = new WorkflowService();
			ImartForm FormClassRows = new ImartForm();
			FormClassRows = Service.getInfoTemp(ApplyForm.getImwSystemMatterId(), "system_matter_id");

			model.addAttribute("FormClassRows", FormClassRows);
			model.addAttribute("ApplyForm", ApplyForm);

		} catch (Exception e) {
			System.out.println("Error Detail :" + e);
		}

		return "wf/path_test/general/detail.jsp";
	}

	@RequestMapping(value = "download/**")
	public String download(final Model model, HttpServletRequest request) throws Exception {

		String urlStr = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

		String fileId = urlStr.substring(urlStr.lastIndexOf('/') + 1);

		AttachFileRepository FileRepository = new AttachFileRepository();
		List<AttachmentModel> rowsFile = new ArrayList<AttachmentModel>(
				FileRepository.SelectTempInfo(fileId.toString(), "id"));
		String fileName = rowsFile.get(0).getFile_name();
		String fileRealPath = rowsFile.get(0).getFile_path();
		String fileDecode = URLDecoder.decode(fileRealPath.toString(), "UTF-8");

		final PublicStorage storage = new PublicStorage(fileDecode);
		if (!storage.isFile()) {

			throw new FileNotFoundException("Could not find a file");
		}

		model.addAttribute("download_file_name", fileName);
		model.addAttribute("storage", storage);
		return "DownloadAttachmentService.Downloadview";
	}

	@PostMapping("ajaxtest")
	@ResponseBody
	public String ajaxtest(final HttpServletRequest request) throws Exception {

		String MatterId = request.getParameter("system_matter_id");

		try {

			System.out.println(" ----- Success ---- Matter_ID :" + MatterId);

			return "success";
		} catch (Exception e) {

			e.printStackTrace();
			return "error: " + e.getMessage();
		}

	}

	@PostMapping("generatepdf")
	@ResponseBody
	public String generatepdf(final HttpServletRequest request) throws Exception {

		String MatterId = request.getParameter("system_matter_id");

		try {
			GeneratePDFService pdfGenerate = new GeneratePDFService();

			pdfGenerate.createPDF(MatterId);

			return "success";
		} catch (Exception e) {

			e.printStackTrace();
			return "error: " + e.getMessage();
		}

	}

	@RequestMapping(value = "downloadpdf/**")
	public String downloadpdf(final Model model, HttpServletRequest request) throws Exception {

		String urlStr = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String fileName = urlStr.substring(urlStr.lastIndexOf('/') + 1);

		String file_decode = URLDecoder.decode("generate_pdf/" + fileName, "UTF-8");
		final PublicStorage storage = new PublicStorage(file_decode);
		if (!storage.isFile()) {

			throw new FileNotFoundException("Could not find a file");
		}

		model.addAttribute("download_file_name", fileName);
		model.addAttribute("storage", storage);

		return "DownloadAttachmentService.Downloadview";
	}

}
