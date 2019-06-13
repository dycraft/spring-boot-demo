package io.dycraft.samples.springbootdemo.service;

import java.io.File;
import java.io.InputStream;

/**
 * @author Dayang Li on 13/06/2019
 */
public interface FileService {

    String uploadFile(String filename, InputStream fileContent);

    File downloadFile(String fileKey);
}
