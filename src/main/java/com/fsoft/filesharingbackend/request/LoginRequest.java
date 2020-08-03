package com.fsoft.filesharingbackend.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "username is mandatory")
    @NotBlank
    @Size(min = 1, max = 20, message = "username '${validatedValue}' must be between {min} and {max} characters")
    private String username;

    @NotNull(message = "password is mandatory")
    @Size(min = 1, max = 20, message = "username '${validatedValue}' must be between {min} and {max} characters")
    private String password;
}
