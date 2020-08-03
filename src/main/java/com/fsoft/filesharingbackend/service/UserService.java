package com.fsoft.filesharingbackend.service;

import com.fsoft.filesharingbackend.constant.Constant;
import com.fsoft.filesharingbackend.dto.BaseResponse;
import com.fsoft.filesharingbackend.entity.Users;
import com.fsoft.filesharingbackend.repository.UserRepository;
import com.fsoft.filesharingbackend.request.LoginRequest;
import com.fsoft.filesharingbackend.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private BidiMap<Long,String> tokenMap;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.tokenMap = new DualHashBidiMap<>();;
    }


    public boolean isVerified(String token) {
        return tokenMap.containsValue(token);
    }

    public Long getUserId(String token) {
        return tokenMap.getKey(token);
    }

    public BaseResponse<LoginResponse> getByUsernameAndPassword(LoginRequest loginRequest) {
        Optional<Users> user = userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (user.isPresent()) {
            String generatedToken = RandomStringUtils.random(30, true, true);
            tokenMap.put(user.get().getId(), generatedToken);
            log.info("[LoginService] Login successful with user id = {}", user.get().getId());
            return new BaseResponse(new LoginResponse(generatedToken));
        }
        log.info("[LoginService] Login fail with username = {} and password = {}", loginRequest.getUsername(),loginRequest.getPassword());
        return new BaseResponse(Constant.RESPONSE_CODE_404, Constant.USERNAME_OR_PASSWORD_INCORRECT);
    }
}
