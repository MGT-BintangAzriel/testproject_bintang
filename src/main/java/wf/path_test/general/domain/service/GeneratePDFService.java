package wf.path_test.general.domain.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.IOUtils;
import io.woo.htmltopdf.HtmlToPdf;
import io.woo.htmltopdf.HtmlToPdfObject;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;

//Import Package
import wf.path_test.general.domain.model.*;
import wf.path_test.general.domain.repository.*;

public class GeneratePDFService {

	public String check_null_string(String check_string) {
		if (check_string == null) {
			return "";
		} else {
			return check_string;
		}
	}

	public void createPDF(String systemMatterId) throws Exception {

		try {
			// Repository
			HeaderInfoTempRepository HeaderTempDB = new HeaderInfoTempRepository();
			DetailTableTempRepository DetailTempDB = new DetailTableTempRepository();

			// Get Data From DB
			Collection<HeaderInfoTempModel> rows_header = HeaderTempDB.selectDataInfoTempHeader(systemMatterId,
					"system_matter_id");
			HeaderInfoTempModel HeaderTempRows = rows_header.iterator().next();

			List<DetailTableModel> DetailData = new ArrayList<DetailTableModel>();
			// Try Fetch List Detail Data
			try {
				DetailData = new ArrayList<DetailTableModel>(
						DetailTempDB.selectDataTable(systemMatterId, "system_matter_id"));
			} catch (Exception e) {
				System.out.println("Error Fetching Data in Generate PDF");
			}

			// Create Header HTMl CSS
			String html = "<html><head>"
					+ "<meta charset='UTF-8'>"
					+ "<style type='text/css'>"

					+ "#table1 td {"
					+ "		font-family: MS PGothic; "
					+ "		font-size: 12pt;"
					+ "	}"

					+ "#table2 td {"
					+ "	  	border: 1px solid black;"
					+ "		font-family: MS PGothic; "
					+ "		padding: 5px;"
					+ "		font-size: 6pt;"
					+ "	}"
					+ "</style>";

			html += "</head><body>";

			html += "<table border='0' id='table1' width='100%'>"
					+ "<tr>"
					+ "<td align='center' colspan='4' style='font-size:20px;'>Summary Data<br><br></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td valign='top'><b>Name</b></td>"
					+ "<td valign='top'><b>Age</b></td>"
					+ "<td valign='top'><b>Notes</b></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td valign='top'>" + HeaderTempRows.getName() + "</td>"
					+ "<td valign='top'>" + HeaderTempRows.getAge() + "</td>"
					+ "<td valign='top'>" + HeaderTempRows.getNote() + "</td>"
					+ "</tr>"
					+ "</table>";

			html += "<table width='100%' id='table2' style='border-collapse: collapse;'>"
					+ "<tr>"
					+ "<td colspan='7' align='center' style='font-weight: BOLD;'>CONTENT</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td align='center'><b>No</b></td>"
					+ "<td align='center'><b>Item Name</b></td>"
					+ "<td align='center'><b>Quantity</b></td>"
					+ "<td align='center'><b>Vendor Courier</b></td>"
					+ "<td align='center'><b>Date Send</b></td>"
					+ "</tr>";

			for (int i = 0; i < DetailData.size(); i++) {

				html += "<tr>"

						+ "<td align='center'>" + check_null_string(DetailData.get(i).getSeq_number()) + "</td>"
						+ "<td align='left'>" + check_null_string(DetailData.get(i).getItem_name()) + "</td>"
						+ "<td align='left'>" + check_null_string(DetailData.get(i).getQuantity()) + "</td>"
						+ "<td align='left'>" + check_null_string(DetailData.get(i).getVendor()) + "</td>"
						+ "<td align='left'>" + check_null_string(DetailData.get(i).getSend_date()) + "</td>"
						+ "</tr>";
			}

			html += ""
					+ "</table>"
					+ "<br>";

			html += "<body><html>";

			InputStream success_pdf = HtmlToPdf.create()
					.object(HtmlToPdfObject.forHtml(html))
					.convert();

			try {
				// set folder and file path
				PublicStorage PDFFilePath = new PublicStorage("generate_pdf/sample.pdf");
				PublicStorage createNewDir = new PublicStorage("generate_pdf");

				try {
					createNewDir.makeDirectories();
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("error creating directory PDF Generate ", e);
				}

				PDFFilePath.save(IOUtils.toByteArray(success_pdf));
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error in Saving PDF to Public Storage", e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in Generating PDF", e);
		}
	}

}
