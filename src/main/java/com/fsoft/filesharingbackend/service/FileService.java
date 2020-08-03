package com.fsoft.filesharingbackend.service;

import com.fsoft.filesharingbackend.constant.Constant;
import com.fsoft.filesharingbackend.dto.BaseResponse;
import com.fsoft.filesharingbackend.entity.Files;
import com.fsoft.filesharingbackend.entity.Users;
import com.fsoft.filesharingbackend.repository.FileRepository;
import com.fsoft.filesharingbackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
@Slf4j
public class FileService {

    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private final UserService userService;

    @Value("${file.storage.path}")
    private String resourceUrl;

    public FileService(UserRepository userRepository, FileRepository fileRepository, UserService userService) {
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
        this.userService = userService;
    }

    public BaseResponse uploadFile(MultipartFile attachedFiles, String token) throws IOException {

        Long userId = userService.getUserId(token);
        Optional<Users> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            log.warn("[FileService] Upload File >> User with id = {} is not exist!", userId);
            return new BaseResponse(Constant.RESPONSE_CODE_404, Constant.USER_NOT_FOUND);
        }
        String fileName = FilenameUtils.getName(attachedFiles.getOriginalFilename());

        byte[] md5 = DigestUtils.md5Digest(attachedFiles.getInputStream());
        String stMd5 = Arrays.toString(md5);

        Optional<Files> systemFileOpt = fileRepository.findByMd5(stMd5);
        if (!systemFileOpt.isPresent()) {
            File file = new File(resourceUrl + stMd5);
            attachedFiles.transferTo(file);
            log.info("[FileService] Upload File >> Upload file {} to {} successful.", fileName, resourceUrl);
        }
        else {
            if (userId.equals(systemFileOpt.get().getUsersId().getId()) && fileName.equals(systemFileOpt.get().getFilename())) {
                log.info("[FileService] Upload File >> Upload file {} to {} successful.", fileName, resourceUrl);
                return new BaseResponse(Constant.RESPONSE_CODE_200, Constant.UPLOAD_FILE_SUCCESSFUL);
            }
        }

        Files fileInfo = new Files();
        fileInfo.setFilename(fileName);
        fileInfo.setMd5(stMd5);
        fileInfo.setUsersId(user.get());
        fileRepository.save(fileInfo);

        return new BaseResponse(Constant.RESPONSE_CODE_200, Constant.UPLOAD_FILE_SUCCESSFUL);

    }

    public ResponseEntity<InputStreamResource> downloadFile(Long fileId, String token) throws IOException {

        Long userId = userService.getUserId(token);
        Optional<Files> filesOptional = fileRepository.findById(fileId);
        if (!filesOptional.isPresent()) {
            log.warn("[FileService] Download File >> File with id = {} is not exist!", fileId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Files fileToDownload = filesOptional.get();
        String fileName = fileToDownload.getFilename();

        if (!userId.equals(fileToDownload.getUsersId().getId())) {
            log.warn("[FileService] Download File >> User with id = {} does not have permission to download file {}!", userId, fileName);
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName);

        //Get content type by file name in DB
        Path path = new File(fileName).toPath();
        String contentType = java.nio.file.Files.probeContentType(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        File fileResource = new File(resourceUrl + fileToDownload.getMd5());
        InputStream fileInputStream = FileUtils.openInputStream(fileResource);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(0)
                .contentType(MediaType.parseMediaType(contentType))
                .body(new InputStreamResource(fileInputStream));
    }

}
