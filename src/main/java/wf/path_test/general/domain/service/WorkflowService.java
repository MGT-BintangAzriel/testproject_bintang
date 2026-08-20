package wf.path_test.general.domain.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.file.SessionScopeStorage;

import wf.path_test.general.app.*;
import wf.path_test.general.domain.model.*;
import wf.path_test.general.domain.repository.*;

public class WorkflowService {

    public ImartForm getInfoTemp(String select_value, String select_where) throws Exception {

        HeaderInfoTempRepository InfoTempHeaderDB = new HeaderInfoTempRepository();

        Collection<HeaderInfoTempModel> rows_headerInfo = InfoTempHeaderDB.selectDataInfoTempHeader(select_value,
                select_where);

        return setInfoTempForm(rows_headerInfo);
    }

    private ImartForm setInfoTempForm(Collection<HeaderInfoTempModel> rows_headerInfo) {
        ImartForm result = new ImartForm();

        HeaderInfoTempModel InfoTempHeaderRows = rows_headerInfo.iterator().next();

        DetailTableTempRepository DetailInfoTemp = new DetailTableTempRepository();
        Collection<DetailTableModel> entityDetailTemp = new ArrayList<DetailTableModel>();

        // Attachment
        AttachFileRepository FileInfoTemp = new AttachFileRepository();
        List<AttachmentModel> entityFileTemp = new ArrayList<AttachmentModel>();

        try {
            result.setF_system_matter_id(InfoTempHeaderRows.getSystem_matter_id());
            result.setF_user_data_id(InfoTempHeaderRows.getUser_data_id());
            result.setF_name(InfoTempHeaderRows.getName());
            result.setF_age(InfoTempHeaderRows.getAge());
            result.setF_note(InfoTempHeaderRows.getNote());

            String matter_id = InfoTempHeaderRows.getSystem_matter_id();
            entityDetailTemp = new ArrayList<DetailTableModel>(
                    DetailInfoTemp.selectDataTable(matter_id, "system_matter_id"));
            result.setD_list_detail_info(entityDetailTemp);

            entityFileTemp = new ArrayList<AttachmentModel>(FileInfoTemp.SelectTempInfo(matter_id, "system_matter_id"));
            result.setD_list_attachment(entityFileTemp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public HeaderInfoModel Move_DataTemp_to_InfoHeader(Collection<HeaderInfoTempModel> rows_temp_header) {
        HeaderInfoTempModel TempHeaderRows = rows_temp_header.iterator().next();
        HeaderInfoModel result = new HeaderInfoModel();

        result.setSystem_matter_id(TempHeaderRows.getSystem_matter_id());
        result.setUser_data_id(TempHeaderRows.getUser_data_id());
        result.setName(TempHeaderRows.getName());
        result.setAge(TempHeaderRows.getAge());
        result.setNote(TempHeaderRows.getNote());

        return result;
    }

    // Get Vendor List
    public Collection<VendorModel> getVendorList() throws Exception {
        VendorRepository VendorDB = new VendorRepository();
        Collection<VendorModel> result = VendorDB.selectDataVendor("", "");

        return result;
    }

    public DetailTableModel move_DetailTemp_to_DetailInfo(Collection<DetailTableModel> rows) {
        DetailTableModel result = new DetailTableModel();
        DetailTableModel tempDetailRows = rows.iterator().next();

        result.setSystem_matter_id(tempDetailRows.getSystem_matter_id());
        result.setUser_data_id(tempDetailRows.getUser_data_id());

        result.setSeq_number(tempDetailRows.getSeq_number());
        result.setItem_name(tempDetailRows.getItem_name());
        result.setQuantity(tempDetailRows.getQuantity());
        result.setVendor(tempDetailRows.getVendor());
        result.setSend_date(tempDetailRows.getSend_date());

        return result;
    }

    public final Boolean AttachmentFileTransfer(String systemMatterId, String file_real_name) {
        PublicStorage createDir = new PublicStorage("sample_workflow/" + systemMatterId + "/file_attachment");
        PublicStorage createFile = new PublicStorage(
                "sample_workflow/" + systemMatterId + "/file_attachment/" + file_real_name);
        SessionScopeStorage getOriginalFile = new SessionScopeStorage("file_attachment/" + file_real_name);
        try {
            createDir.makeDirectories();

            if (!createFile.isFile()) {
                createFile.save(org.apache.commons.io.IOUtils.toByteArray(getOriginalFile.open()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
