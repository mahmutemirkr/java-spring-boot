package com.jvtpe.controller;

import com.jvtpe.dto.UserRequest;
import com.jvtpe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRequest userRequest){

        userService.saveUser(userRequest);

        String myResponse = "Succesfull";

        return new ResponseEntity<>(myResponse,HttpStatus.CREATED);

    }


}
