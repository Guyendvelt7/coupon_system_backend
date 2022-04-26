package com.jb.coupon2demo.controllers;

import com.jb.coupon2demo.beans.ClientType;
import com.jb.coupon2demo.exceptions.CustomExceptions;
import com.jb.coupon2demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/GYGNcoupons")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login/{email}/{pass}/{clientType}")
    public ResponseEntity<?> login(@PathVariable String email, @PathVariable String pass, @RequestBody ClientType clientType) throws CustomExceptions {
        return new ResponseEntity<>(loginService.login(email,pass,clientType),HttpStatus.ACCEPTED);
    }
}
