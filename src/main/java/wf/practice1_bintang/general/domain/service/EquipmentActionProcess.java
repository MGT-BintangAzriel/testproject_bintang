package wf.practice1_bintang.general.domain.service;

import java.util.Map;

import jp.co.intra_mart.foundation.workflow.plugin.process.action.ActionProcessEventListener;
import jp.co.intra_mart.foundation.workflow.plugin.process.action.ActionProcessParameter;
import jp.co.intra_mart.framework.extension.spring.context.ApplicationContextProvider;

import wf.practice1_bintang.general.EquipmentActionProcessService;

/**
 * 備品申請のアクション処理クラス。
 * ActionProcessEventListenerの各メソッドをオーバーライドし、
 * 備品申請に関するビジネスロジックを実装する。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class EquipmentActionProcess extends ActionProcessEventListener {

        /**
         * 申請時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @return ユーザデータID
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final String apply(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                return service.apply(parameter, userParameter);

        }

        /**
         * 一時保存からの申請時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @return ユーザデータID
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final String applyFromTempSave(final ActionProcessParameter parameter,
                        final Map<String, Object> userParameter) throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                return service.applyFromTempSave(parameter, userParameter);
        }

        /**
         * 未申請状態案件からの申請時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @return ユーザデータID
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final String applyFromUnapply(final ActionProcessParameter parameter,
                        final Map<String, Object> userParameter) throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                return service.applyFromUnapply(parameter, userParameter);
        }

        /**
         * 承認時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public void approve(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);

                service.approve(parameter, userParameter);
        }

        /**
         * 承認終了時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final void approveEnd(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                service.approveEnd(parameter, userParameter);
        }

        /**
         * 否認時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final void deny(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                service.deny(parameter, userParameter);
        }

        /**
         * 取止め時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final void discontinue(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                service.discontinue(parameter, userParameter);
        }

        /**
         * 案件操作時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final void matterHandle(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                service.matterHandle(parameter, userParameter);
        }

        /**
         * 引き戻し時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final void pullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                service.pullBack(parameter, userParameter);
        }

        /**
         * 再申請時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @return ユーザデータID
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final String reapply(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                return service.reapply(parameter, userParameter);
        }

        /**
         * 保留時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final void reserve(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                service.reserve(parameter, userParameter);
        }

        /**
         * 保留解除時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final void reserveCancel(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                service.reserveCancel(parameter, userParameter);
        }

        /**
         * 差し戻し時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final void sendBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                service.sendBack(parameter, userParameter);
        }

        /**
         * 差し戻し後引き戻し時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final void sendBackToPullBack(final ActionProcessParameter parameter,
                        final Map<String, Object> userParameter) throws Exception {
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                service.sendBackToPullBack(parameter, userParameter);
        }

        /**
         * 一時保存新規登録時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final void tempSaveCreate(final ActionProcessParameter parameter,
                        final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                service.tempSaveCreate(parameter, userParameter);
        }

        /**
         * 一時保存削除時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final void tempSaveDelete(final ActionProcessParameter parameter,
                        final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                service.tempSaveDelete(parameter, userParameter);
        }

        /**
         * 一時保存更新時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        @Override
        public final void tempSaveUpdate(final ActionProcessParameter parameter,
                        final Map<String, Object> userParameter)
                        throws Exception {
                // アクション処理用のサービスを取得する。
                final EquipmentActionProcessService service = ApplicationContextProvider.getApplicationContext()
                                .getBean(EquipmentActionProcessService.class);
                service.tempSaveUpdate(parameter, userParameter);
        }

}
