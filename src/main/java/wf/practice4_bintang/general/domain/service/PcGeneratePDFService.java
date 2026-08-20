package wf.practice4_bintang.general.domain.service;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.IOUtils;

import io.woo.htmltopdf.HtmlToPdf;
import io.woo.htmltopdf.HtmlToPdfObject;

import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.workflow.application.general.ActvMatter;
import jp.co.intra_mart.foundation.workflow.application.general.CplMatter;
import jp.co.intra_mart.foundation.workflow.application.history.MatterHistory;
import jp.co.intra_mart.foundation.workflow.application.model.MatterHistoryResultModel;

import static wf.common.constant.WorkflowCommonConstants.HTML_TAG_TABLE_END;
import static wf.common.constant.WorkflowCommonConstants.HTML_TAG_TABLE_START;
import static wf.common.constant.WorkflowCommonConstants.HTML_TAG_TD_END;
import static wf.common.constant.WorkflowCommonConstants.HTML_TAG_TD_START;
import static wf.common.constant.WorkflowCommonConstants.HTML_TAG_TH_END;
import static wf.common.constant.WorkflowCommonConstants.HTML_TAG_TH_START;
import static wf.common.constant.WorkflowCommonConstants.HTML_TAG_TR_END;
import static wf.common.constant.WorkflowCommonConstants.HTML_TAG_TR_START;

import wf.practice4_bintang.general.domain.model.PcHeaderInfoTempModel;
import wf.practice4_bintang.general.domain.repository.PcHeaderInfoTempRepository;

/**
 * PC購入申請書PDFの生成処理を行うサービスクラス。
 * HTMLテンプレートを用いてPDFファイルを出力し、PublicStorageに保存する。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class PcGeneratePDFService {

	/**
	 * 文字列がnull値の場合に空文字列に変換するユーティリティメソッド。
	 *
	 * @param check_string チェック対象の文字列
	 * @return nullの場合は空文字、それ以外の場合はそのままの文字列
	 */
	public String check_null_string(String check_string) {
		if (check_string == null) {
			return "";
		} else {
			return check_string;
		}
	}

	/**
	 * システム案件IDに紐づく申請情報を取得し、PC購入申請書のPDFファイルを生成する。
	 *
	 * @param systemMatterId システム案件ID
	 * @return 生成されたPDFファイルのファイル名 (案件番号.pdf)
	 * @throws Exception データ取得、ワークフローAPI呼び出し、PDF生成処理、またはファイル保存時に例外が発生した場合
	 */
	public String createPDF(String systemMatterId) throws Exception {

		try {
			// リポジトリをインスタンス化
			PcHeaderInfoTempRepository PcHeaderTempDB = new PcHeaderInfoTempRepository();

			// データベースから申請データを取得する
			Collection<PcHeaderInfoTempModel> rows_header = PcHeaderTempDB.selectDataInfoTempHeader(systemMatterId,
					"system_matter_id");
			PcHeaderInfoTempModel HeaderTempRows = rows_header.iterator().next();

			// ワークフロー案件情報から「案件番号」を取得する
			String matter_number = "";

			try {
				// 未完了案件情報から取得を試してみる
				ActvMatter actvMatter = new ActvMatter(systemMatterId);
				matter_number = actvMatter.getMatter().getMatterNumber();
			} catch (Exception activeEx) {
				try {
					// 取得できない場合は、完了案件情報から取得を試してみる
					CplMatter cplMatter = new CplMatter(systemMatterId);
					matter_number = cplMatter.getMatter().getMatterNumber();
				} catch (Exception cplEx) {
					// 案件情報が取得できない場合はログを出力し、空文字列のまま処理を継続する
                    cplEx.printStackTrace();
				}
			}

			// フォームに入力されるデータを取得する（nullチェックを行う）
			String apply_date = check_null_string(HeaderTempRows.getApply_date());
			String applicant = check_null_string(HeaderTempRows.getApplicant());
			String department = check_null_string(HeaderTempRows.getDepartment());
			String pc_use_type = check_null_string(HeaderTempRows.getPc_use_type());
			String pc_use_other = check_null_string(HeaderTempRows.getPc_use_other());
			String manufacturer = check_null_string(HeaderTempRows.getManufacturer());
			String model_number = check_null_string(HeaderTempRows.getModel_number());
			int quantity = HeaderTempRows.getQuantity();
			int unit_price = HeaderTempRows.getUnit_price();

			// 合計金額を計算する
			int total_price = quantity * unit_price;

			// PDFに出力するHTMLテンプレートの作成
			String html = "<html>"
					+ "<head>"
					+ "<meta charset='UTF-8'>"
					+ "<style type='text/css'>"
					+ "  body {"
					+ "    font-family: 'Meiryo', sans-serif;"
					+ "    padding: 20px;"
					+ "  }"
					+ "  h1 {"
					+ "    text-align: center;"
					+ "    font-size: 18pt;"
					+ "    margin-bottom: 25px;"
					+ "  }"
					+ "  table {"
					+ "    width: 100%;"
					+ "    border-collapse: collapse;"
					+ "    margin-bottom: 20px;"
					+ "  }"
					+ "  th, td {"
					+ "    border: 1px solid black;"
					+ "    padding: 8px;"
					+ "    font-size: 10.5pt;"
					+ "    text-align: left;"
					+ "  }"
					+ "  th {"
					+ "    background-color: #f2f2f2;"
					+ "    width: 25%;"
					+ "  }"
					+ "</style>"
					+ "</head>"
					+ "<body>";

			html += "<h1>PC購入申請書</h1>";

			// 申請情報テーブルの組み立て
			html += HTML_TAG_TABLE_START
					+ HTML_TAG_TR_START + HTML_TAG_TH_START + "案件番号" + HTML_TAG_TH_END + HTML_TAG_TD_START + matter_number + HTML_TAG_TD_END + HTML_TAG_TR_END
					+ HTML_TAG_TR_START + HTML_TAG_TH_START + "申請日" + HTML_TAG_TH_END + HTML_TAG_TD_START + apply_date + HTML_TAG_TD_END + HTML_TAG_TR_END
					+ HTML_TAG_TR_START + HTML_TAG_TH_START + "申請者" + HTML_TAG_TH_END + HTML_TAG_TD_START + applicant + HTML_TAG_TD_END + HTML_TAG_TR_END
					+ HTML_TAG_TR_START + HTML_TAG_TH_START + "部署" + HTML_TAG_TH_END + HTML_TAG_TD_START + department + HTML_TAG_TD_END + HTML_TAG_TR_END
					+ HTML_TAG_TR_START + HTML_TAG_TH_START + "用途" + HTML_TAG_TH_END + HTML_TAG_TD_START + pc_use_type + HTML_TAG_TD_END + HTML_TAG_TR_END
					+ HTML_TAG_TR_START + HTML_TAG_TH_START + "その他用途" + HTML_TAG_TH_END + HTML_TAG_TD_START + pc_use_other + HTML_TAG_TD_END + HTML_TAG_TR_END
					+ HTML_TAG_TR_START + HTML_TAG_TH_START + "メーカー" + HTML_TAG_TH_END + HTML_TAG_TD_START + manufacturer + HTML_TAG_TD_END + HTML_TAG_TR_END
					+ HTML_TAG_TR_START + HTML_TAG_TH_START + "型番" + HTML_TAG_TH_END + HTML_TAG_TD_START + model_number + HTML_TAG_TD_END + HTML_TAG_TR_END
					+ HTML_TAG_TR_START + HTML_TAG_TH_START + "数量" + HTML_TAG_TH_END + HTML_TAG_TD_START + quantity + HTML_TAG_TD_END + HTML_TAG_TR_END
					+ HTML_TAG_TR_START + HTML_TAG_TH_START + "単価" + HTML_TAG_TH_END + HTML_TAG_TD_START + String.format("%,d", unit_price) + " 円" + HTML_TAG_TD_END + HTML_TAG_TR_END
					+ HTML_TAG_TR_START + HTML_TAG_TH_START + "購入金額" + HTML_TAG_TH_END + HTML_TAG_TD_START + String.format("%,d", total_price) + " 円" + HTML_TAG_TD_END + HTML_TAG_TR_END
					+ HTML_TAG_TABLE_END;

			html += "<br><h3>承認履歴</h3>";

			// 承認履歴テーブルの組み立て
			html += HTML_TAG_TABLE_START
					+ HTML_TAG_TR_START
					+ "<th style='width: 50%;'>承認者" + HTML_TAG_TH_END
					+ "<th style='width: 50%;'>承認日時" + HTML_TAG_TH_END
					+ HTML_TAG_TR_END;

			// 対象案件の承認履歴一覧を取得する
			MatterHistory matterHistory = new MatterHistory(systemMatterId, "ja");
			List<MatterHistoryResultModel> historiesList = matterHistory.getMatterHistory();
			boolean hasHistory = false;

			// 履歴情報の中から「承認」ステータスの履歴のみを表に追加する
			for (MatterHistoryResultModel history : historiesList) {
				if (history.getStatusName().equals("承認")) {
					html += HTML_TAG_TR_START
							+ HTML_TAG_TD_START + history.getExecuteUserName() + HTML_TAG_TD_END
							+ HTML_TAG_TD_START + history.getEndDate() + HTML_TAG_TD_END
							+ HTML_TAG_TR_END;
					hasHistory = true;
				}
			}

			// 承認履歴が存在しなかった場合の表示
			if (!hasHistory) {
				html += HTML_TAG_TR_START + "<td colspan='2'>承認履歴はありません。" + HTML_TAG_TD_END + HTML_TAG_TR_END;
			}

			html += HTML_TAG_TABLE_END
					+ "</body></html>";

			// HTMLからPDFファイルを生成する
			InputStream success_pdf = HtmlToPdf.create()
					.object(HtmlToPdfObject.forHtml(html))
					.convert();

			try {
				// パブリックストレージ上にPDFファイルを保存する
				PublicStorage PDFFilePath = new PublicStorage("generate_pdf/" + matter_number + ".pdf");
				PublicStorage createNewDir = new PublicStorage("generate_pdf");

				try {
					// 出力先フォルダがない場合は新規作成する
					createNewDir.makeDirectories();
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("error creating directory PDF Generate ", e);
				}

				// PDFデータを保存する
				PDFFilePath.save(IOUtils.toByteArray(success_pdf));
				return matter_number + ".pdf";
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
