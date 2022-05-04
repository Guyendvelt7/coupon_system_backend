package com.jb.coupon2demo.controllers;

import com.jb.coupon2demo.beans.Category;
import com.jb.coupon2demo.beans.Coupon;
import com.jb.coupon2demo.exceptions.CustomExceptions;
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

    @PutMapping("/updateCoupon")
    public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon,@RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        companyService.updateCoupon(coupon);
        return new ResponseEntity<>(newToken,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{couponId}")
    public ResponseEntity<?> deleteCoupon(@PathVariable int couponId,@RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        companyService.deleteCoupon(couponId);
        return new ResponseEntity<>(newToken,HttpStatus.ACCEPTED);
    }
    @GetMapping("/getAllCoupons")
    public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        return ResponseEntity.ok()
                .header("Authorization",newToken)
                .body(companyService.getAllCompanyCoupons());
    }

    @GetMapping("/getOneCoupon/{couponId}")
    public ResponseEntity<?> getOneCoupon(@PathVariable int couponId, @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        return ResponseEntity.ok()
                .header("Authorization",newToken)
                .body(companyService.getOneCoupon(couponId));
    }

    @GetMapping("/getCouponsByCategory/{category}")
    public ResponseEntity<?> getOneCouponByCategory(@RequestParam Category category, @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        return ResponseEntity.ok()
                .header("Authorization",newToken)
                .body(companyService.getCompanyCouponsByCategory(category));
    }

    @PostMapping("/getCouponsByMaxPrice/{maxPrice}")
    public ResponseEntity<?> getCouponsByMaxPrice(@PathVariable int maxPrice, @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        return ResponseEntity.ok()
                .header("Authorization",newToken)
                .body(companyService.getCompanyCouponByMaxPrice(maxPrice));
    }

    @GetMapping("/companyDetails")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        return ResponseEntity.ok()
                .header("Authorization",newToken)
                .body(companyService.getCompanyDetails());
    }
}
