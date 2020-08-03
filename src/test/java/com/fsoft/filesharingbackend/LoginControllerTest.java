package com.fsoft.filesharingbackend;

import com.fsoft.filesharingbackend.dto.BaseResponse;
import com.fsoft.filesharingbackend.request.LoginRequest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
public class LoginControllerTest {

    @Test
    /*
     * Login with correct username and password
     */
    public void testLoginSuccess() throws URISyntaxException
    {
        /*RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://10.34.217.19:8080" + "/api/login";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        LoginRequest loginRequest = new LoginRequest("triuhn", "123456");
        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<BaseResponse> result = restTemplate.postForEntity(uri, request, BaseResponse.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());*/
    }
}
