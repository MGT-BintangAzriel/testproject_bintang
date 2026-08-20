package wf.practice1_bintang.general;

import java.util.Map;

import jp.co.intra_mart.foundation.workflow.plugin.process.action.ActionProcessParameter;

/**
 * ワークフローの各アクション（申請、承認、否認など）に対する処理を定義するインターフェース。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public interface EquipmentActionProcessService {

        /**
         * 申請時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @return ユーザデータID
         * @throws Exception 処理実行中に例外が発生した場合
         */
        String apply(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

        /**
         * 一時保存からの申請時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @return ユーザデータID
         * @throws Exception 処理実行中に例外が発生した場合
         */
        String applyFromTempSave(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception;

        /**
         * 未申請状態案件からの申請時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @return ユーザデータID
         * @throws Exception 処理実行中に例外が発生した場合
         */
        String applyFromUnapply(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception;

        /**
         * 承認時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void approve(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

        /**
         * 承認終了時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void approveEnd(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception;

        /**
         * 否認時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void deny(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

        /**
         * 取止め時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void discontinue(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception;

        /**
         * 案件操作時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void matterHandle(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception;

        /**
         * 引き戻し時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void pullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

        /**
         * 再申請時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @return ユーザデータID
         * @throws Exception 処理実行中に例外が発生した場合
         */
        String reapply(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception;

        /**
         * 保留時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void reserve(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

        /**
         * 保留解除時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void reserveCancel(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception;

        /**
         * 差し戻し時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void sendBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception;

        /**
         * 差し戻し後引き戻し時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void sendBackToPullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception;

        /**
         * 一時保存新規登録時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void tempSaveCreate(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception;

        /**
         * 一時保存削除時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void tempSaveDelete(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception;

        /**
         * 一時保存更新時の処理を実行するメソッド。
         *
         * @param parameter     ワークフローのパラメータ
         * @param userParameter 画面入力値（ユーザパラメータ）
         * @throws Exception 処理実行中に例外が発生した場合
         */
        void tempSaveUpdate(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
                        throws Exception;
}
