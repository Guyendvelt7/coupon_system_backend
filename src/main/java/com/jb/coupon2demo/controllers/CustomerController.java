package com.jb.coupon2demo.controllers;

import com.jb.coupon2demo.exceptions.CustomExceptions;
import com.jb.coupon2demo.security.JWTutil;
import com.jb.coupon2demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/GYGNcoupons/customer")
public class CustomerController {
    private final JWTutil jwTutil;
    private final CustomerService customerService;

    @PutMapping("/purchaseCoupon/{id}")
    public ResponseEntity<?> purchaseCoupon(@PathVariable int id, @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        customerService.purchaseCoupon(id);
        return new ResponseEntity<>(newToken,HttpStatus.ACCEPTED);
    }
}
