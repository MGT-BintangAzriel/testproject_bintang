package wf.practice1_bintang.general;

import jp.co.intra_mart.foundation.workflow.plugin.process.matter_end.MatterEndProcessParameter;

/**
 * ワークフロー終了時の処理を定義するインターフェース。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public interface EquipmentMatterEndProcessService {

    /**
     * 案件終了時のDB更新処理を行うメソッド。
     *
     * @param parameter ワークフローのパラメータ
     * @return 成功した場合はtrue
     * @throws Exception 処理中に例外が発生した場合
     */
    boolean execute(final MatterEndProcessParameter parameter) throws Exception;

}
