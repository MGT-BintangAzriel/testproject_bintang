package wf.practice2_bintang.general.domain.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;

import wf.common.constant.WorkflowCommonConstants;
import wf.common.constant.MatterEndStatus;
import wf.common.constant.WorkflowStatus;
import wf.practice2_bintang.general.BookMatterEndProcessService;
import wf.practice2_bintang.general.domain.model.BookHeaderInfoModel;
import wf.practice2_bintang.general.domain.model.BookHeaderInfoTempModel;
import wf.practice2_bintang.general.domain.model.BookHeaderModel;
import wf.practice2_bintang.general.domain.repository.BookHeaderInfoRepository;
import wf.practice2_bintang.general.domain.repository.BookHeaderInfoTempRepository;
import wf.practice2_bintang.general.domain.repository.BookHeaderRepository;

/**
 * ワークフローの案件終了処理の実装クラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
@Service
@Transactional(propagation = Propagation.MANDATORY)
public class BookMatterEndProcessServiceImpl implements BookMatterEndProcessService {

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
		BookHeaderRepository BookHeaderDB = new BookHeaderRepository();
		BookHeaderInfoRepository BookHeaderInfoDB = new BookHeaderInfoRepository();
		BookHeaderInfoTempRepository BookHeaderInfoTempDB = new BookHeaderInfoTempRepository();

		// ワークフローのパラメータ (parameter)からモデルを構築する
		BookHeaderModel rows_header = BookHeaderDB
				.selectDataHeader(parameter.getSystemMatterId(), WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID)
				.iterator().next();
		Collection<BookHeaderInfoTempModel> rows_temp_header = BookHeaderInfoTempDB
				.selectDataInfoTempHeader(parameter.getSystemMatterId(),
						WorkflowCommonConstants.COLUMN_SYSTEM_MATTER_ID);

		// 本保存モデルをインスタンス化する
		BookHeaderInfoModel rows_info_header = new BookHeaderInfoModel();

		// ワークフローサービスをインスタンス化する
		BookWorkflowService service = new BookWorkflowService();
		// 一時保存データを本保存モデルに変換する
		rows_info_header = service.Move_DataTemp_to_InfoHeader(rows_temp_header);

		// 案件が完了した場合
		if (MatterEndStatus.MATTER_COMPLETE.getStatus().equals(parameter.getLastResultStatus())) {
			// ステータスを設定する
			rows_header.setStatus(WorkflowStatus.COMPLETED.getCode());

			// ヘッダーDBを更新する
			BookHeaderDB.updateDataHeader(rows_header);

			// 一時保存データを本保存テーブルへの登録を行う
			BookHeaderInfoDB.insertDataHeader(rows_info_header);

			// 案件が否認された場合
		} else if (MatterEndStatus.DENY.getStatus().equals(parameter.getLastResultStatus())) {
			// ステータスを設定する
			rows_header.setStatus(WorkflowStatus.DENIED.getCode());

			// ヘッダーDBを更新する
			BookHeaderDB.updateDataHeader(rows_header);
		}

		return true;

	}

}
