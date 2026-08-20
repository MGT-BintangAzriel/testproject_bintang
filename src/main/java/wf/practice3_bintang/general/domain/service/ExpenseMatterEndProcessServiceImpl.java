package wf.practice3_bintang.general.domain.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;

import wf.common.constant.WorkflowCommonConstants;
import wf.common.constant.WorkflowStatus;
import wf.common.constant.MatterEndStatus;
import wf.common.constant.MailStatus;
import wf.practice3_bintang.general.ExpenseMatterEndProcessService;
import wf.practice3_bintang.general.domain.model.*;
import wf.practice3_bintang.general.domain.repository.*;

/**
 * ワークフローの案件終了処理の実装クラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
@Service
@Transactional(propagation = Propagation.MANDATORY)
public class ExpenseMatterEndProcessServiceImpl implements ExpenseMatterEndProcessService {

	/**
	 * 案件終了時のDB更新処理を行うメソッド。
	 *
	 * @param parameter ワークフローのパラメータ
	 * @return 成功した場合はtrue
	 * @throws Exception 処理中に例外が発生した場合
	 */
	@Override
	public boolean execute(MatterEndProcessParameter parameter) throws Exception {

		// リポジトリをインスタンス化する
		ExpenseHeaderRepository ExpenseHeaderDB = new ExpenseHeaderRepository();
		ExpenseHeaderInfoRepository ExpenseHeaderInfoDB = new ExpenseHeaderInfoRepository();
		ExpenseHeaderInfoTempRepository ExpenseHeaderInfoTempDB = new ExpenseHeaderInfoTempRepository();
		ExpenseAttachFileRepository ExpenseAttachFileDB = new ExpenseAttachFileRepository();

		// ワークフローのパラメータ (parameter)からモデルを構築する
		ExpenseHeaderModel rows_header = ExpenseHeaderDB
				.selectDataHeader(parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID)
				.iterator().next();
		Collection<ExpenseHeaderInfoTempModel> rows_temp_header = ExpenseHeaderInfoTempDB
				.selectDataInfoTempHeader(parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

		// 本保存モデルをインスタンス化する
		ExpenseHeaderInfoModel rows_info_header = new ExpenseHeaderInfoModel();

		// ワークフローサービスをインスタンス化する
		ExpenseWorkflowService service = new ExpenseWorkflowService();
		// 一時保存データを本保存モデルに変換する
		rows_info_header = service.Move_DataTemp_to_InfoHeader(rows_temp_header);

		// 案件が完了した場合
		if (MatterEndStatus.MATTER_COMPLETE.getStatus().equals(parameter.getLastResultStatus())) {
			// ステータスとメールステータスを設定する
			rows_header.setStatus(WorkflowStatus.COMPLETED.getCode());
			rows_header.setMail_status(MailStatus.UNSENT.getCode());

			// ヘッダーDBを更新する
			ExpenseHeaderDB.updateDataHeader(rows_header);

			// 一時保存データを本保存テーブルに登録する
			ExpenseHeaderInfoDB.insertDataHeader(rows_info_header);

			// 添付ファイルを本保存ディレクトリに移動する
			String matter_id = parameter.getSystemMatterId();
			ExpenseAttachFileDB.MoveInfoFile(matter_id);

			// 案件が否認された場合
		} else if (MatterEndStatus.DENY.getStatus().equals(parameter.getLastResultStatus())) {
			// ステータスを設定する
			rows_header.setStatus(WorkflowStatus.DENIED.getCode());

			// ヘッダーDBを更新する
			ExpenseHeaderDB.updateDataHeader(rows_header);
		}

		return true;

	}

}
