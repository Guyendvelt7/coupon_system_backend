package com.jb.coupon3.service;

import com.jb.coupon3.beans.Coupon;
import com.jb.coupon3.exceptions.CustomExceptions;
import com.jb.coupon3.exceptions.OptionalExceptionMessages;
import com.jb.coupon3.repositories.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    @Autowired
    private CouponRepo couponRepo;

    public GuestService() {
    }

    public List<Coupon> getAllCoupons() throws CustomExceptions {
        if(couponRepo.findAll().isEmpty()){
            throw new CustomExceptions(OptionalExceptionMessages.EMPTY_LIST);
        }
        return couponRepo.findAll();
    }




}
