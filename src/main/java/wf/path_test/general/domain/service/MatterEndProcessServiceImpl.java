package wf.path_test.general.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;
import wf.path_test.general.domain.repository.HeaderInfoRepository;
import wf.path_test.general.domain.repository.HeaderInfoTempRepository;
import wf.path_test.general.domain.repository.HeaderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wf.path_test.general.*;
import wf.path_test.general.domain.model.*;
import wf.path_test.general.domain.repository.*;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class MatterEndProcessServiceImpl implements MatterEndProcessService {
	@Override
	public boolean execute(final MatterEndProcessParameter parameter) throws Exception {

		// Repository
		HeaderRepository HeaderDB = new HeaderRepository();
		HeaderInfoRepository HeaderInfoDB = new HeaderInfoRepository();
		HeaderInfoTempRepository HeaderInfoTempDB = new HeaderInfoTempRepository();

		DetailTableRepository DetailDB = new DetailTableRepository();
		DetailTableTempRepository DetailTempDB = new DetailTableTempRepository();
		AttachFileRepository AttachFileDB = new AttachFileRepository();

		// Model
		HeaderModel rows_header = HeaderDB.selectDataHeader(parameter.getSystemMatterId(), "system_matter_id")
				.iterator().next();

		Collection<HeaderInfoTempModel> rows_temp_header = HeaderInfoTempDB
				.selectDataInfoTempHeader(parameter.getSystemMatterId(), "system_matter_id");

		HeaderInfoModel rows_info_header = new HeaderInfoModel();

		WorkflowService Service = new WorkflowService();
		rows_info_header = Service.Move_DataTemp_to_InfoHeader(rows_temp_header);

		@SuppressWarnings("unused")
		DetailTableModel row_detailInfo = new DetailTableModel();
		Collection<DetailTableModel> var_DetailInfoTemp = DetailTempDB.selectDataTable(parameter.getSystemMatterId(),
				"system_matter_id");

		row_detailInfo = Service.move_DetailTemp_to_DetailInfo(var_DetailInfoTemp);
		List<DetailTableModel> var_ListDataDetail = new ArrayList<DetailTableModel>(
				DetailTempDB.selectDataTable(parameter.getSystemMatterId(), "system_matter_id"));

		// Condition
		if (parameter.getLastResultStatus() == "mattercomplete") {
			// Update Header DB
			rows_header.setStatus("2");
			rows_header.setMail_status("1");
			HeaderDB.updateDataHeader(rows_header);

			// Create Data to Header Info DB
			HeaderInfoDB.insertDataHeader(rows_info_header);

			// Detail Content
			for (int i = 0; i < var_ListDataDetail.size(); i++) {
				DetailTableModel result = new DetailTableModel();
				result.setSystem_matter_id(var_ListDataDetail.get(i).getSystem_matter_id());
				result.setUser_data_id(var_ListDataDetail.get(i).getUser_data_id());

				result.setSeq_number(var_ListDataDetail.get(i).getSeq_number());
				result.setItem_name(var_ListDataDetail.get(i).getItem_name());
				result.setQuantity(var_ListDataDetail.get(i).getQuantity());
				result.setVendor(var_ListDataDetail.get(i).getVendor());
				result.setSend_date(var_ListDataDetail.get(i).getSend_date());

				DetailDB.insertDataHeader(result);

			}

			// Attachment File
			String matter_id = parameter.getSystemMatterId();
			AttachFileDB.MoveInfoFile(matter_id);

		} else if (parameter.getLastResultStatus() == "deny") {
			// Update Header DB
			rows_header.setStatus("99");
			HeaderDB.updateDataHeader(rows_header);

		}

		return true;
	}

}
