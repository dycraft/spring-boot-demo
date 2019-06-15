package io.dycraft.samples.springbootdemo.api;

import io.dycraft.samples.springbootdemo.exception.FileIOException;
import io.dycraft.samples.springbootdemo.service.FileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dayang Li on 13/06/2019
 */
@Slf4j
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void uploadFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException ex) {
            log.error("An error occurs uploading MultipartFile " + filename, ex);
            throw new FileIOException(filename);
        }
        fileService.uploadFile(filename, inputStream);
    }

    @GetMapping("/{key}")
    ResponseEntity downloadFile(@PathVariable String key) {
        File file = fileService.downloadFile(key);
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (IOException ex) {
            log.error("An error occurs downloading File " + file.getName(), ex);
            throw new FileIOException(file.getName());
        }
        Resource resource = new InputStreamResource(inputStream);
        return ResponseEntity.ok()
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
}
