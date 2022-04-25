package com.jb.coupon2demo.service;

import com.jb.coupon2demo.exceptions.CustomExceptions;
import com.jb.coupon2demo.repositories.CompanyRepo;
import com.jb.coupon2demo.repositories.CouponRepo;
import com.jb.coupon2demo.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected CompanyRepo companyRepo;
    @Autowired
    protected CouponRepo couponRepo;
    @Autowired
    protected CustomerRepo customerRepo;

    public abstract boolean login(String mail, String password)throws CustomExceptions;
}
