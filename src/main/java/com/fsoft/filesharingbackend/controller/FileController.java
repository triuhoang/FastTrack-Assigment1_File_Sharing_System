package com.fsoft.filesharingbackend.controller;

import com.fsoft.filesharingbackend.dto.BaseResponse;
import com.fsoft.filesharingbackend.request.LoginRequest;
import com.fsoft.filesharingbackend.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/file")
@Slf4j
@AllArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<BaseResponse> uploadFile(@RequestPart(value = "attachedFiles") MultipartFile attachedFiles, HttpServletRequest request) throws IOException {
        log.info("[FileController] Starting upload file");
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        return new ResponseEntity<>(fileService.uploadFile(attachedFiles, token), HttpStatus.OK);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable(value = "fileId") Long fileId, HttpServletRequest request) throws IOException {
        log.info("[FileController] Starting download file");
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        return fileService.downloadFile(fileId, token);
    }
}
