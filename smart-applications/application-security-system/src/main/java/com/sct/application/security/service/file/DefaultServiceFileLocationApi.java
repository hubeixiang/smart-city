package com.sct.application.security.service.file;

import com.sct.service.core.api.service.file.ServiceFileLocationApi;
import org.springframework.stereotype.Service;

@Service
public class DefaultServiceFileLocationApi implements ServiceFileLocationApi {

    @Override
    public String locationDir() {
        return Constants.PROGRAM_HOME;
    }

    @Override
    public String locationLogDir() {
        return Constants.PROGRAM_LOG_HOME;
    }

    @Override
    public String locationUploadTemporaryDir() {
        return Constants.PROGRAM_UPLOAD_TemporaryDir;
    }

    @Override
    public String locationUploadPermanentDir() {
        return Constants.PROGRAM_UPLOAD_PermanentDir;
    }
}
