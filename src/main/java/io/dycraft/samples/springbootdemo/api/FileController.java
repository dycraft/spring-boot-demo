package io.dycraft.samples.springbootdemo.api;

import io.dycraft.samples.springbootdemo.service.FileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    ResponseEntity uploadFile(MultipartFile file) throws IOException {
        @Cleanup InputStream inputStream = file.getInputStream();
        String key = fileService.uploadFile(file.getOriginalFilename(), inputStream);
        return Response.created(key);
    }

    @GetMapping("/{key}")
    ResponseEntity downloadFile(@PathVariable String key) throws IOException {
        File file = fileService.downloadFile(key);
        @Cleanup InputStream inputStream = new FileInputStream(file);
        Resource resource = new InputStreamResource(inputStream);
        return ResponseEntity.ok()
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
}
