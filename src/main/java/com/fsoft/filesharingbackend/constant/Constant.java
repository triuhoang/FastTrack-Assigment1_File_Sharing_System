package com.fsoft.filesharingbackend.constant;

public class Constant {
    private Constant() {
        throw new IllegalStateException("Constant is Utility class");
    }

    public static final String RESPONSE_CODE_200 = "200";
    public static final String RESPONSE_CODE_201 = "201";
    public static final String RESPONSE_CODE_401 = "401";
    public static final String RESPONSE_CODE_404 = "404";

    public static final String RESPONSE_MESSAGE_SUCCESS = "Success";

    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error.";
    public static final String BAD_REQUEST_ERROR_MESSAGE = "Invalid Request.";
    public static final String FILE_NOT_FOUND = "File Not found.";
    public static final String USER_NOT_FOUND = "User Not found.";
    public static final String USERNAME_OR_PASSWORD_INCORRECT = "Username or Password is incorrect.";
    public static final String UPLOAD_FILE_SUCCESSFUL = "Upload file Successful!.";

    public static final String SYSTEM = "SYSTEM";
}
