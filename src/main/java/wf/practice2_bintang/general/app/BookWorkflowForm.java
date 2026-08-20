package wf.practice2_bintang.general.app;

/**
 * ワークフローの共通パラメータを保持するベースクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
public class BookWorkflowForm {

    // ログイングループID
    private String imwGroupId;

    // 処理者CD（ログインユーザ本人のユーザコード）
    private String imwUserCode;

    // 画面種別
    private String imwPageType;

    // ユーザデータID
    private String imwUserDataId;

    // システム案件ID
    private String imwSystemMatterId;

    // 処理対象ノードID
    private String imwNodeId;

    // 到達種別
    private String imwArriveType;

    // 権限者CD
    private String imwAuthUserCode;

    // 申請基準日
    private String imwApplyBaseDate;

    // コンテンツID
    private String imwContentsId;

    // コンテンツバージョンID
    private String imwContentsVersionId;

    // ルートID
    private String imwRouteId;

    // ルートバージョンID
    private String imwRouteVersionId;

    // フローID
    private String imwFlowId;

    // フローバージョンID
    private String imwFlowVersionId;

    // 呼び出し元パラメータ
    private String imwCallOriginalParams;

    // 呼び出し元ページパス
    private String imwCallOriginalPagePath;

    // システム日で対象者を展開するフラグ
    private String imwSysDateTargetExpandFlag;

    // ショートカットフラグ
    private String imwShortCutFlag;

    /**
     * 申請基準日を取得する。
     *
     * @return 申請基準日
     */
    public String getImwApplyBaseDate() {
        return imwApplyBaseDate;
    }

    /**
     * 到達種別を取得する。
     *
     * @return 到達種別
     */
    public String getImwArriveType() {
        return imwArriveType;
    }

    /**
     * 権限者コードを取得する。
     *
     * @return 権限者コード
     */
    public String getImwAuthUserCode() {
        return imwAuthUserCode;
    }

    /**
     * 呼び出し元ページパスを取得する。
     *
     * @return 呼び出し元ページパス
     */
    public String getImwCallOriginalPagePath() {
        return imwCallOriginalPagePath;
    }

    /**
     * 呼び出し元パラメータを取得する。
     *
     * @return 呼び出し元パラメータ
     */
    public String getImwCallOriginalParams() {
        return imwCallOriginalParams;
    }

    /**
     * コンテンツIDを取得する。
     *
     * @return コンテンツID
     */
    public String getImwContentsId() {
        return imwContentsId;
    }

    /**
     * コンテンツバージョンIDを取得する。
     *
     * @return コンテンツバージョンID
     */
    public String getImwContentsVersionId() {
        return imwContentsVersionId;
    }

    /**
     * フローIDを取得する。
     *
     * @return フローID
     */
    public String getImwFlowId() {
        return imwFlowId;
    }

    /**
     * フローバージョンIDを取得する。
     *
     * @return フローバージョンID
     */
    public String getImwFlowVersionId() {
        return imwFlowVersionId;
    }

    /**
     * ログイングループIDを取得する。
     *
     * @return ログイングループID
     */
    public String getImwGroupId() {
        return imwGroupId;
    }

    /**
     * ノードIDを取得する。
     *
     * @return ノードID
     */
    public String getImwNodeId() {
        return imwNodeId;
    }

    /**
     * 画面種別を取得する。
     *
     * @return 画面種別
     */
    public String getImwPageType() {
        return imwPageType;
    }

    /**
     * ルートIDを取得する。
     *
     * @return ルートID
     */
    public String getImwRouteId() {
        return imwRouteId;
    }

    /**
     * ルートバージョンIDを取得する。
     *
     * @return ルートバージョンID
     */
    public String getImwRouteVersionId() {
        return imwRouteVersionId;
    }

    /**
     * ショートカットフラグを取得する。
     *
     * @return ショートカットフラグ
     */
    public String getImwShortCutFlag() {
        return imwShortCutFlag;
    }

    /**
     * システム日基準日展開フラグを取得する。
     *
     * @return システム日基準日展開フラグ
     */
    public String getImwSysDateTargetExpandFlag() {
        return imwSysDateTargetExpandFlag;
    }

    /**
     * システム案件IDを取得する。
     *
     * @return システム案件ID
     */
    public String getImwSystemMatterId() {
        return imwSystemMatterId;
    }

    /**
     * ユーザコードを取得する。
     *
     * @return ユーザコード
     */
    public String getImwUserCode() {
        return imwUserCode;
    }

    /**
     * ユーザデータIDを取得する。
     *
     * @return ユーザデータID
     */
    public String getImwUserDataId() {
        return imwUserDataId;
    }

    /**
     * 申請基準日を設定する。
     *
     * @param imwApplyBaseDate 申請基準日
     */
    public void setImwApplyBaseDate(final String imwApplyBaseDate) {
        this.imwApplyBaseDate = imwApplyBaseDate;
    }

    /**
     * 到達種別を設定する。
     *
     * @param imwArriveType 到達種別
     */
    public void setImwArriveType(final String imwArriveType) {
        this.imwArriveType = imwArriveType;
    }

    /**
     * 権限者コードを設定する。
     *
     * @param imwAuthUserCode 権限者コード
     */
    public void setImwAuthUserCode(final String imwAuthUserCode) {
        this.imwAuthUserCode = imwAuthUserCode;
    }

    /**
     * 呼び出し元ページパスを設定する。
     *
     * @param imwCallOriginalPagePath 呼び出し元ページパス
     */
    public void setImwCallOriginalPagePath(final String imwCallOriginalPagePath) {
        this.imwCallOriginalPagePath = imwCallOriginalPagePath;
    }

    /**
     * 呼び出し元パラメータを設定する。
     *
     * @param imwCallOriginalParams 呼び出し元パラメータ
     */
    public void setImwCallOriginalParams(final String imwCallOriginalParams) {
        this.imwCallOriginalParams = imwCallOriginalParams;
    }

    /**
     * コンテンツIDを設定する。
     *
     * @param imwContentsId コンテンツID
     */
    public void setImwContentsId(final String imwContentsId) {
        this.imwContentsId = imwContentsId;
    }

    /**
     * コンテンツバージョンIDを設定する。
     *
     * @param imwContentsVersionId コンテンツバージョンID
     */
    public void setImwContentsVersionId(final String imwContentsVersionId) {
        this.imwContentsVersionId = imwContentsVersionId;
    }

    /**
     * フローIDを設定する。
     *
     * @param imwFlowId フローID
     */
    public void setImwFlowId(final String imwFlowId) {
        this.imwFlowId = imwFlowId;
    }

    /**
     * フローバージョンIDを設定する。
     *
     * @param imwFlowVersionId フローバージョンID
     */
    public void setImwFlowVersionId(final String imwFlowVersionId) {
        this.imwFlowVersionId = imwFlowVersionId;
    }

    /**
     * ログイングループIDを設定する。
     *
     * @param imwGroupId ログイングループID
     */
    public void setImwGroupId(final String imwGroupId) {
        this.imwGroupId = imwGroupId;
    }

    /**
     * ノードIDを設定する。
     *
     * @param imwNodeId ノードID
     */
    public void setImwNodeId(final String imwNodeId) {
        this.imwNodeId = imwNodeId;
    }

    /**
     * 画面種別を設定する。
     *
     * @param imwPageType 画面種別
     */
    public void setImwPageType(final String imwPageType) {
        this.imwPageType = imwPageType;
    }

    /**
     * ルートIDを設定する。
     *
     * @param imwRouteId ルートID
     */
    public void setImwRouteId(final String imwRouteId) {
        this.imwRouteId = imwRouteId;
    }

    /**
     * ルートバージョンIDを設定する。
     *
     * @param imwRouteVersionId ルートバージョンID
     */
    public void setImwRouteVersionId(final String imwRouteVersionId) {
        this.imwRouteVersionId = imwRouteVersionId;
    }

    /**
     * ショートカットフラグを設定する。
     *
     * @param imwShortCutFlag ショートカットフラグ
     */
    public void setImwShortCutFlag(final String imwShortCutFlag) {
        this.imwShortCutFlag = imwShortCutFlag;
    }

    /**
     * システム日基準日展開フラグを設定する。
     *
     * @param imwSysDateTargetExpandFlag システム日基準日展開フラグ
     */
    public void setImwSysDateTargetExpandFlag(final String imwSysDateTargetExpandFlag) {
        this.imwSysDateTargetExpandFlag = imwSysDateTargetExpandFlag;
    }

    /**
     * システム案件IDを設定する。
     *
     * @param imwSystemMatterId システム案件ID
     */
    public void setImwSystemMatterId(final String imwSystemMatterId) {
        this.imwSystemMatterId = imwSystemMatterId;
    }

    /**
     * ユーザコードを設定する。
     *
     * @param imwUserCode ユーザコード
     */
    public void setImwUserCode(final String imwUserCode) {
        this.imwUserCode = imwUserCode;
    }

    /**
     * ユーザデータIDを設定する。
     *
     * @param imwUserDataId ユーザデータID
     */
    public void setImwUserDataId(final String imwUserDataId) {
        this.imwUserDataId = imwUserDataId;
    }

}
