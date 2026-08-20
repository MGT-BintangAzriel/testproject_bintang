package wf.path_test.general;

import java.util.Map;
import jp.co.intra_mart.foundation.workflow.plugin.process.action.ActionProcessParameter;


public interface ActionProcessService {

	String apply(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    String applyFromTempSave(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    String applyFromUnapply(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    void approve(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    void approveEnd(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    void deny(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    void discontinue(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    void matterHandle(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    void pullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    String reapply(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;
    
    void reserve(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    void reserveCancel(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    void sendBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    void sendBackToPullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    void tempSaveCreate(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    void tempSaveDelete(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

    void tempSaveUpdate(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;
 }

