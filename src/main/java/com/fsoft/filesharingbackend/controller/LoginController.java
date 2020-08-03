package com.fsoft.filesharingbackend.controller;

import com.fsoft.filesharingbackend.dto.BaseResponse;
import com.fsoft.filesharingbackend.request.LoginRequest;
import com.fsoft.filesharingbackend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/login")
@Slf4j
@AllArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<BaseResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("[LoginController] Starting login.");
        return new ResponseEntity<>(userService.getByUsernameAndPassword(loginRequest), HttpStatus.OK);
    }
}
