package io.dycraft.samples.springbootdemo.service.impl;

import io.dycraft.samples.springbootdemo.exception.FileIOException;
import io.dycraft.samples.springbootdemo.service.FileService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Dayang Li on 13/06/2019
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Value("${spring.file-root}")
    private String root;

    @Override
    public String uploadFile(String filename, InputStream fileContent) {

        String ext = getFileExtension(filename);
        byte[] buffer;
        try {
            buffer = new byte[fileContent.available()];
            fileContent.read(buffer);
        } catch (IOException ex) {
            log.error("An error occurs reading File " + filename, ex);
            throw new FileIOException(filename);
        }

        String uid = UUID.randomUUID().toString();
        String newFilename = uid + ext;
        String path = root + newFilename;
        try {
            OutputStream outputStream = new FileOutputStream(path);
            outputStream.write(buffer);
        } catch (IOException ex) {
            log.error("An error occurs writing File " + newFilename, ex);
            throw new FileIOException(filename);
        }

        log.info("Uploading ${filename} success, resource name ${newFilename}");
        return newFilename;
    }

    @Override
    public File downloadFile(String fileKey) {
        return null;
    }

    private static String getFileExtension(String filename) {
        int lastIndexOf = filename.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return filename.substring(lastIndexOf);
    }
}
