package wf.practice4_bintang.general.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.terasoluna.gfw.common.exception.SystemException;
import org.terasoluna.gfw.web.download.AbstractFileDownloadView;

import jp.co.intra_mart.foundation.http.ResponseUtil;
import jp.co.intra_mart.foundation.service.client.file.Storage;

/**
 * 添付ファイルのダウンロード処理を行うクラス。
 *
 * @author MGT-BintangAzriel
 * @version 1.0.0
 */
@Component("PcDownloadAttachmentService.Downloadview")
public class PcDownloadAttachmentService extends AbstractFileDownloadView {

    /**
     * レスポンスヘッダを設定するメソッド。
     *
     * @param model    モデルオブジェクト
     * @param request  HTTPサーブレットリクエスト
     * @param response HTTPサーブレットレスポンス
     */
    @Override
    protected void addResponseHeader(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {
        String disposition = "";
        try {

            // ファイル名をエンコードする
            final String fileName = model.get("download_file_name").toString();
            disposition = ResponseUtil.encodeFileName(request, "UTF-8", fileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new SystemException("download view error code", e);
        }
        // レスポンスヘッダにContent-DispositionとContentTypeを設定する
        response.setHeader("Content-Disposition", "attachment;" + disposition);
        response.setContentType("application/force-download");
    }

    /**
     * ダウンロード対象ファイルの入力ストリームを取得するメソッド。
     *
     * @param model   モデルオブジェクト
     * @param request HTTPサーブレットリクエスト
     * @return ダウンロード対象ファイルの入力ストリーム
     * @throws IOException 入出力処理中に例外が発生した場合
     */
    @Override
    protected InputStream getInputStream(Map<String, Object> model, HttpServletRequest request) throws IOException {

        // モデルからストレージオブジェクトを取得して、ストリームを開く
        final Storage<?> storage = (Storage<?>) model.get("storage");
        return storage.open();
    }

}
