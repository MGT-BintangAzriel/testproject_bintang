package wf.practice5_bintang.general.domain.service;

import java.util.Map;

import jp.co.intra_mart.foundation.workflow.plugin.process.action.ActionProcessEventListener;
import jp.co.intra_mart.foundation.workflow.plugin.process.action.ActionProcessParameter;
import jp.co.intra_mart.framework.extension.spring.context.ApplicationContextProvider;

import wf.practice5_bintang.general.AgreementActionProcessService;

public class AgreementActionProcess extends ActionProcessEventListener {

        @Override
        public final String apply(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                return service.apply(parameter, userParameter);

        }

        @Override
        public final String applyFromTempSave(final ActionProcessParameter parameter,
                        final Map<String, Object> userParameter) throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                return service.applyFromTempSave(parameter, userParameter);
        }

        @Override
        public final String applyFromUnapply(final ActionProcessParameter parameter,
                        final Map<String, Object> userParameter) throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                return service.applyFromUnapply(parameter, userParameter);
        }

        @Override
        public void approve(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);

                service.approve(parameter, userParameter);
        }

        @Override
        public final void approveEnd(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                service.approveEnd(parameter, userParameter);
        }

        @Override
        public final void deny(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                service.deny(parameter, userParameter);
        }

        @Override
        public final void discontinue(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                service.discontinue(parameter, userParameter);
        }

        @Override
        public final void matterHandle(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                service.matterHandle(parameter, userParameter);
        }

        @Override
        public final void pullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                service.pullBack(parameter, userParameter);
        }

        @Override
        public final String reapply(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                return service.reapply(parameter, userParameter);
        }

        @Override
        public final void reserve(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                service.reserve(parameter, userParameter);
        }

        @Override
        public final void reserveCancel(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                service.reserveCancel(parameter, userParameter);
        }

        @Override
        public final void sendBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                service.sendBack(parameter, userParameter);
        }

        @Override
        public final void sendBackToPullBack(final ActionProcessParameter parameter,
                        final Map<String, Object> userParameter) throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                service.sendBackToPullBack(parameter, userParameter);
        }

        @Override
        public final void tempSaveCreate(final ActionProcessParameter parameter,
                        final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                service.tempSaveCreate(parameter, userParameter);
        }

        @Override
        public final void tempSaveDelete(final ActionProcessParameter parameter,
                        final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                service.tempSaveDelete(parameter, userParameter);
        }

        @Override
        public final void tempSaveUpdate(final ActionProcessParameter parameter,
                        final Map<String, Object> userParameter)
                        throws Exception {
                final AgreementActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(AgreementActionProcessService.class);
                service.tempSaveUpdate(parameter, userParameter);
        }

}
