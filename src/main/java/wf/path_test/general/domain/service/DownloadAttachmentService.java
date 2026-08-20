package wf.path_test.general.domain.service;

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

@Component("DownloadAttachmentService.Downloadview")
public class DownloadAttachmentService extends AbstractFileDownloadView {

    @Override
    protected void addResponseHeader(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {
        final String disposition;
        try {

            final String fileName = model.get("download_file_name").toString();
            disposition = ResponseUtil.encodeFileName(request, "UTF-8", fileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new SystemException("download view error code", e);
        }
        response.setHeader("Content-Disposition", "attachment;" + disposition);
        response.setContentType("application/force-download");
    }

    @Override
    protected InputStream getInputStream(Map<String, Object> model, HttpServletRequest request) throws IOException {
        final Storage<?> storage = (Storage<?>) model.get("storage");
        return storage.open();
    }

}
