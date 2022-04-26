package com.jb.coupon2demo.thread;

import com.jb.coupon2demo.repositories.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CouponExpirationDailyJob {
    @Autowired
    private CouponRepo couponRepo;

    @Scheduled(fixedRate = 86_400_000)
    public void deleteByDate(){
        System.out.println("im start");
        couponRepo.deleteCouponsByDate();
    }
}

