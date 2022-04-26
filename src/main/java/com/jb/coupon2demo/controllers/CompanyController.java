package com.jb.coupon2demo.controllers;

import com.jb.coupon2demo.beans.Coupon;
import com.jb.coupon2demo.exceptions.CustomExceptions;
import com.jb.coupon2demo.repositories.CompanyRepo;
import com.jb.coupon2demo.security.JWTutil;
import com.jb.coupon2demo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/GYGNcoupons/company")
public class CompanyController {
    private final CompanyService companyService;
    private final JWTutil jwTutil;

    @PutMapping("/addCoupon")
    public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon,@RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        companyService.addCoupon(coupon);
        return new ResponseEntity<>(newToken,HttpStatus.ACCEPTED);
    }
}
