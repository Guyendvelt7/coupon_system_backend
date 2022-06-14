package com.jb.coupon3.controllers;

import com.jb.coupon3.exceptions.CustomExceptions;
import com.jb.coupon3.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/GYGNcoupons/guest")
public class GuestController {
    private final GuestService guestService;

    @GetMapping
    public ResponseEntity<?> allCoupons() throws CustomExceptions {
        return ResponseEntity.ok()
                .body(guestService.getAllCoupons());
    }
}
