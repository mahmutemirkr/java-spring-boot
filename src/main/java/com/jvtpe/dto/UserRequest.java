package com.jvtpe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {


    @NotBlank(message="Enter name information")
    private String firstName;

    @NotBlank(message="Enter lastName information")
    private String lastName;

    @NotBlank(message="Please Provide UserName")
    @Size(min=5,max=10, message="Please provide a User Name min=5, max=10 chars long")
    private String userName;

    @NotBlank(message="Please enter password information")
    private String password;
}