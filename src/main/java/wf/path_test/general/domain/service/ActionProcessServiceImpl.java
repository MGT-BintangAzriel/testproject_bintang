package wf.path_test.general.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import jp.co.intra_mart.common.aid.jdk.java.util.LocaleUtil;
import jp.co.intra_mart.foundation.security.message.MessageManager;
import jp.co.intra_mart.foundation.workflow.application.general.UserActvMatterPropertyValue;
import jp.co.intra_mart.foundation.workflow.application.model.UserMatterPropertyModel;
import jp.co.intra_mart.foundation.workflow.exception.WorkflowException;
import jp.co.intra_mart.foundation.workflow.exception.WorkflowExternalException;
import jp.co.intra_mart.foundation.workflow.plugin.process.action.ActionProcessParameter;
import jp.co.intra_mart.foundation.workflow.util.WorkflowNumberingManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//Import all package
import wf.path_test.general.*;
import wf.path_test.general.domain.model.*;
import wf.path_test.general.domain.repository.*;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class ActionProcessServiceImpl implements ActionProcessService {
    @Override
    public final String apply(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        String number = null;
        try {

            // Repository
            HeaderRepository HeaderDB = new HeaderRepository();
            @SuppressWarnings("unused")
            HeaderInfoRepository HeaderInfoDB = new HeaderInfoRepository();
            HeaderInfoTempRepository HeaderInfoTempDB = new HeaderInfoTempRepository();
            DetailTableTempRepository DetailInfoTempDB = new DetailTableTempRepository();
            AttachFileRepository AttachDB = new AttachFileRepository();

            // Model to Entity
            HeaderModel entity_Header = getEntity_Header(parameter, userParameter);
            HeaderInfoTempModel entity_HeaderInfoTemp = getEntity_HeaderInfoTemp(parameter, userParameter);

            // Execute Data
            HeaderDB.insertDataHeader(entity_Header);
            HeaderInfoTempDB.insertDataHeader(entity_HeaderInfoTemp);

            final List<DetailTableModel> entity_DetailTable = getEntity_DetailTable(parameter, userParameter);

            for (int i = 0; i < entity_DetailTable.size(); i++) {
                DetailInfoTempDB.insertDataHeader(entity_DetailTable.get(i));
            }

            final List<AttachmentModel> entity_Attachment = getEntity_File(parameter, userParameter);

            WorkflowService Service = new WorkflowService();

            for (int i = 0; i < entity_Attachment.size(); i++) {
                AttachDB.createTempInfoFile(entity_Attachment.get(i));
                Service.AttachmentFileTransfer(parameter.getSystemMatterId(),
                        entity_Attachment.get(i).getFile_real_name());
            }

            String get_Name = entity_HeaderInfoTemp.getName();
            createMatterProperty(parameter.getUserDataId(), "name_branch", get_Name);

            number = WorkflowNumberingManager.getNumber();

        } catch (final WorkflowException e) {
            throw new WorkflowExternalException(MessageManager.getInstance()
                    .getMessage(LocaleUtil.toLocale(parameter.getLocaleId()), "SAMPLE.IMW.ERR.003"));

        }

        return number;
    }

    private final void createMatterProperty(final String userDataId, final String matterPropertyKey,
            final String matterPropertyValue) throws WorkflowException {
        final UserMatterPropertyModel matterPropertyModel = new UserMatterPropertyModel();
        matterPropertyModel.setUserDataId(userDataId);
        matterPropertyModel.setMatterPropertyKey(matterPropertyKey);
        matterPropertyModel.setMatterPropertyValue(matterPropertyValue);

        UserActvMatterPropertyValue property;
        property = new UserActvMatterPropertyValue();
        final UserMatterPropertyModel[] matterProperty = new UserMatterPropertyModel[1];
        matterProperty[0] = matterPropertyModel;
        property.createMatterProperty(matterProperty);
    }

    @SuppressWarnings("unused")
    private final void updateMatterProperty(final String userDataId, final String matterPropertyKey,
            final String matterPropertyValue) throws WorkflowException {
        final UserMatterPropertyModel matterPropertyModel = new UserMatterPropertyModel();
        matterPropertyModel.setUserDataId(userDataId);
        matterPropertyModel.setMatterPropertyKey(matterPropertyKey);
        matterPropertyModel.setMatterPropertyValue(matterPropertyValue);

        UserActvMatterPropertyValue property;
        property = new UserActvMatterPropertyValue();
        final UserMatterPropertyModel[] matterProperty = new UserMatterPropertyModel[1];
        matterProperty[0] = matterPropertyModel;
        property.updateMatterProperty(matterProperty);
    }

    @SuppressWarnings("unused")
    private final void deleteMatterProperty(final String userDataId, final String matterPropertyKey)
            throws WorkflowException {
        final UserMatterPropertyModel matterPropertyModel = new UserMatterPropertyModel();
        matterPropertyModel.setUserDataId(userDataId);
        matterPropertyModel.setMatterPropertyKey(matterPropertyKey);

        UserActvMatterPropertyValue property;
        property = new UserActvMatterPropertyValue();
        final UserMatterPropertyModel[] matterProperty = new UserMatterPropertyModel[1];
        matterProperty[0] = matterPropertyModel;
        property.deleteMatterProperty(matterProperty);
    }

    private List<DetailTableModel> getEntity_DetailTable(final ActionProcessParameter parameter,
            final Map<String, Object> userParameter) {
        List<DetailTableModel> result = new ArrayList<DetailTableModel>();

        List<String> sequence_no = normalizeToList(userParameter.get("d_sequence_no"));
        List<String> itemname = normalizeToList(userParameter.get("d_item_name"));
        List<String> quantity = normalizeToList(userParameter.get("d_quantity"));
        List<String> vendor = normalizeToList(userParameter.get("d_vendor"));
        List<String> send_date = normalizeToList(userParameter.get("d_senddate"));

        for (int i = 0; i < sequence_no.size(); i++) {
            DetailTableModel entity = new DetailTableModel();

            entity.setSystem_matter_id(parameter.getSystemMatterId());
            entity.setUser_data_id(parameter.getUserDataId());

            entity.setSeq_number(getEntityListString(sequence_no, i));
            entity.setItem_name(getEntityListString(itemname, i));
            entity.setQuantity(getEntityListString(quantity, i));
            entity.setVendor(getEntityListString(vendor, i));
            entity.setSend_date(getEntityListString(send_date, i));

            result.add(entity);
        }

        return result;
    }

    private HeaderModel getEntity_Header(ActionProcessParameter parameter, Map<String, Object> userParameter) {
        HeaderModel entity = new HeaderModel();

        entity.setUser_data_id(parameter.getUserDataId());
        entity.setSystem_matter_id(parameter.getSystemMatterId());
        entity.setStatus("1");
        entity.setMail_status("0");

        return entity;
    }

    private HeaderInfoTempModel getEntity_HeaderInfoTemp(ActionProcessParameter parameter,
            Map<String, Object> userParameter) {
        HeaderInfoTempModel entity = new HeaderInfoTempModel();

        entity.setUser_data_id(parameter.getUserDataId());
        entity.setSystem_matter_id(parameter.getSystemMatterId());

        entity.setName(getEntity_TryCatch_UserParameter(userParameter, "f_name"));
        entity.setAge(getEntity_TryCatch_UserParameter(userParameter, "f_age"));
        entity.setNote(getEntity_TryCatch_UserParameter(userParameter, "f_note"));

        return entity;
    }

    private String getEntityListString(List<String> input_form, int i) {
        try {
            // System.out.println("Input data :" + input_form.get(i));
            return input_form.get(i);
        } catch (Exception e) {
            return "";
        }
    }

    @SuppressWarnings("unused")
    private String getEntityUserParameter(final Map<String, Object> userParameter, String input_form) {
        try {
            return userParameter.get(input_form).toString();
        } catch (Exception e) {
            return "";
        }
    }

    private String getEntity_TryCatch_UserParameter(final Map<String, Object> userParameter, String input_form) {
        try {
            return userParameter.get(input_form).toString();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public final String applyFromTempSave(final ActionProcessParameter parameter,
            final Map<String, Object> userParameter) throws Exception {
        String number = null;
        try {
            number = WorkflowNumberingManager.getNumber();
        } catch (final WorkflowException e) {
            throw new WorkflowExternalException(MessageManager.getInstance()
                    .getMessage(LocaleUtil.toLocale(parameter.getLocaleId()), "SAMPLE.IMW.ERR.003"));
        }
        return number;
    }

    @Override
    public final String applyFromUnapply(final ActionProcessParameter parameter,
            final Map<String, Object> userParameter) throws Exception {
        String number = null;
        try {
            number = WorkflowNumberingManager.getNumber();
        } catch (final WorkflowException e) {
            throw new WorkflowExternalException(MessageManager.getInstance()
                    .getMessage(LocaleUtil.toLocale(parameter.getLocaleId()), "SAMPLE.IMW.ERR.003"));
        }
        return number;
    }

    private List<AttachmentModel> getEntity_File(ActionProcessParameter parameter, Map<String, Object> userParameter) {
        List<AttachmentModel> result = new ArrayList<AttachmentModel>();

        try {

            // New Code
            List<String> attachmentId = normalizeToList(userParameter.get("f_upload_file_id"));
            List<String> attachmentFileName = normalizeToList(userParameter.get("f_upload_file_name"));
            List<String> attachmentRealName = normalizeToList(userParameter.get("f_upload_file_real_name"));
            List<String> attachmentFileType = normalizeToList(userParameter.get("f_upload_file_type"));

            for (int i = 0; i < attachmentId.size(); i++) {
                AttachmentModel entity = new AttachmentModel();
                entity.setSystem_matter_id(parameter.getSystemMatterId());
                entity.setUser_data_id(parameter.getUserDataId());

                entity.setFile_name(getEntityListString(attachmentFileName, i));
                entity.setFile_real_name(getEntityListString(attachmentRealName, i));
                entity.setFile_path("sample_workflow/" + parameter.getSystemMatterId() + "/file_attachment/"
                        + entity.getFile_real_name());
                entity.setFile_type(getEntityListString(attachmentFileType, i));

                if (!entity.getFile_name().equals("") && !entity.getFile_real_name().equals("")) {
                    if (!entity.getFile_name().equals("-") && !entity.getFile_real_name().equals("-")) {
                        result.add(entity);
                    }
                }
            }
        } catch (Exception e) {

            System.out.println("Error Get Entity File");

        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private List<String> normalizeToList(Object param) {
        if (param instanceof String) {
            return Collections.singletonList((String) param);
        } else if (param instanceof List) {
            return (List<String>) param;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void approve(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        System.out.println("masuk approve");
    }

    @Override
    public final void approveEnd(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        System.out.println("masuk approve end");
    }

    @Override
    public final void deny(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        System.out.println("masuk deny");
    }

    @Override
    public final void discontinue(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        System.out.println("masuk discontinue");
    }

    @Override
    public final void matterHandle(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        System.out.println("masuk matterHandle");
    }

    @Override
    public final void pullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        System.out.println("masuk pullBack");
    }

    @Override
    public final String reapply(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {

        try {

            // Temp repository
            HeaderRepository HeaderDB = new HeaderRepository();
            HeaderInfoTempRepository HeaderInfoTempDB = new HeaderInfoTempRepository();
            DetailTableTempRepository DetailTempDB = new DetailTableTempRepository();
            AttachFileRepository AttachDB = new AttachFileRepository();

            // Header
            HeaderModel entityHeader = getEntity_Header(parameter, userParameter);
            HeaderDB.updateDataHeader(entityHeader);

            // HeaderInfoTemp
            HeaderInfoTempModel entityHeaderTempDB = getEntity_HeaderInfoTemp(parameter, userParameter);
            HeaderInfoTempDB.updateDataInfoTempHeader(entityHeaderTempDB);

            // DetailTableTemp
            final List<DetailTableModel> entity_TableTemp = getEntity_DetailTable(parameter, userParameter);
            DetailTempDB.deleteDataDetailTemp(parameter.getSystemMatterId(), "system_matter_id");
            for (int i = 0; i < entity_TableTemp.size(); i++) {
                DetailTempDB.insertDataHeader(entity_TableTemp.get(i));
            }

            // AttachFile
            final List<AttachmentModel> entity_AttachFile = getEntity_File(parameter, userParameter);
            AttachDB.deleteTempInfoFile(parameter.getSystemMatterId(), "system_matter_id");
            for (int i = 0; i < entity_AttachFile.size(); i++) {
                AttachDB.createTempInfoFile(entity_AttachFile.get(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public final void reserve(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        System.out.println("masuk reserve");
    }

    @Override
    public final void reserveCancel(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        System.out.println("masuk reserveCancel");
    }

    @Override
    public final void sendBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        System.out.println("masuk sendBack");
    }

    @Override
    public final void sendBackToPullBack(final ActionProcessParameter parameter,
            final Map<String, Object> userParameter) throws Exception {
        System.out.println("masuk sendBackToPullBack");
    }

    @Override
    public final void tempSaveCreate(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        System.out.println("masuk tempSaveCreate");
    }

    @Override
    public final void tempSaveDelete(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        System.out.println("masuk tempSaveDelete");
    }

    @Override
    public final void tempSaveUpdate(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
            throws Exception {
        System.out.println("masuk tempSaveUpdate");
    }

    @SuppressWarnings("unused")
    private void outputLog(final ActionProcessParameter parameter) {
        System.out.println("LoginGroupId        : " + parameter.getLoginGroupId());
        System.out.println("LocaleId            : " + parameter.getLocaleId());
        if (parameter.getTargetLocales() != null) {
            System.out.print("TargetLocales       : ");
            for (final String str : parameter.getTargetLocales()) {
                System.out.print(str + " ");
            }
            System.out.println();
        } else {
            System.out.println("TargetLocales       : ");
        }
        System.out.println("ContentsId          : " + parameter.getContentsId());
        System.out.println("ContentsVersionId   : " + parameter.getContentsVersionId());
        System.out.println("RouteId             : " + parameter.getRouteId());
        System.out.println("RouteVersionId      : " + parameter.getRouteVersionId());
        System.out.println("FlowId              : " + parameter.getFlowId());
        System.out.println("FlowVersionId       : " + parameter.getFlowVersionId());
        System.out.println("ApplyBaseDate       : " + parameter.getApplyBaseDate());
        System.out.println("ProcessDate         : " + parameter.getProcessDate());
        System.out.println("SystemMatterId      : " + parameter.getSystemMatterId());
        System.out.println("UserDataId          : " + parameter.getUserDataId());
        System.out.println("MatterName          : " + parameter.getMatterName());
        System.out.println("MatterNumber        : " + parameter.getMatterNumber());
        System.out.println("PriorityLevel       : " + parameter.getPriorityLevel());
        System.out.println("Parameter           : " + parameter.getParameter());
        System.out.println("ActFlag             : " + parameter.getActFlag());
        System.out.println("NodeId              : " + parameter.getNodeId());
        if (parameter.getNextNodeIds() != null) {
            System.out.print("NextNodeIds         : ");
            for (final String str : parameter.getNextNodeIds()) {
                System.out.print(str + " ");
            }
            System.out.println();
        } else {
            System.out.println("NextNodeIds         : ");
        }
        System.out.println("AuthUserCd          : " + parameter.getAuthUserCd());
        System.out.println("ExecUserCd          : " + parameter.getExecUserCd());
        System.out.println("ResultStatus        : " + parameter.getResultStatus());
        System.out.println("AuthCompanyCode     : " + parameter.getAuthCompanyCode());
        System.out.println("AuthOrgzSetCode     : " + parameter.getAuthOrgzSetCode());
        System.out.println("AuthOrgzCode        : " + parameter.getAuthOrgzCode());
        System.out.println("ProcessComment      : " + parameter.getProcessComment());
        System.out.println("LumpProcessFlag     : " + parameter.getLumpProcessFlag());
        System.out.println("AutoProcessFlag     : " + parameter.getAutoProcessFlag());
    }
}
