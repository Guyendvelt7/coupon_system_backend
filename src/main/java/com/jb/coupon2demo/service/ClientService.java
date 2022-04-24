package com.jb.coupon2demo.service;

import com.jb.coupon2demo.exceptions.CustomExceptions;
import com.jb.coupon2demo.repositories.CompanyRepo;
import com.jb.coupon2demo.repositories.CouponRepo;
import com.jb.coupon2demo.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class ClientService {

    protected CompanyRepo companyRepo;
    protected CouponRepo couponRepo;
    protected CustomerRepo customerRepo;


    abstract boolean login (String email, String password) throws CustomExceptions;

}
