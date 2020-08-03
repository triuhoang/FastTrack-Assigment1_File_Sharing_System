package com.fsoft.filesharingbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fsoft.filesharingbackend.constant.Constant;
import org.springframework.http.HttpStatus;

public class BaseResponse<T> {
    protected String responseCode;
    protected String responseMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected T data;

    public BaseResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public BaseResponse(String responseCode) {
        this.responseCode = responseCode;
    }

    public BaseResponse(HttpStatus httpStatus) {
        this.responseCode = String.valueOf(httpStatus.value());
        if (httpStatus == HttpStatus.OK) {
            this.responseMessage = "Success";
        }
    }

    public BaseResponse(T data) {
        this.responseCode = Constant.RESPONSE_CODE_200;
        this.responseMessage = Constant.RESPONSE_MESSAGE_SUCCESS;
        this.data = data;
    }


    public BaseResponse() {
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
