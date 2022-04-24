package com.jb.coupon2demo.clr.RepoTests;

import com.jb.coupon2demo.beans.Category;
import com.jb.coupon2demo.beans.Coupon;
import com.jb.coupon2demo.repositories.CompanyRepo;
import com.jb.coupon2demo.repositories.CouponRepo;
import com.jb.coupon2demo.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

//@Component
@Order(3)
@RequiredArgsConstructor
public class CouponRepoCrudeTest implements CommandLineRunner {
    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;
    private final CustomerRepo customerRepo;

    @Override
    public void run(String... args) throws Exception {
        //find by title and company ID func.
        System.out.println("1- " + couponRepo.findById(1));
        Coupon coupon = couponRepo.findByTitleAndCompanyId("4ktv", 1);
        //update coupon func.
        coupon.setAmount(250);
        couponRepo.save(coupon);
        System.out.println("2- " + couponRepo.findById(1));
        //exist by title and company ID func.
        boolean isExist = couponRepo.existsByTitleAndCompanyId("4ktv", 1);
        if (isExist) {
            System.out.println("3- " + couponRepo.findById(1));
        } else {
            System.out.println("3 - company not found");
        }
        //delete out of date coupon func.
       Coupon coupon1 = Coupon.builder()
               .amount(300)
               .category(Category.EXTREME)
               .companyId(companyRepo.findByName("rikushet").getId())
               .description("bangee_rope")
               .price(2575)
               .startDate(Date.valueOf(LocalDate.now().minusDays(30)))
               .endDate(Date.valueOf(LocalDate.now().minusDays(1)))
               .title("4ktv")
               .build();
        couponRepo.save(coupon1);
        System.out.println("4- ");
        List<Coupon> couponList = couponRepo.findAll();
        couponList.forEach(System.out::println);
       couponRepo.deleteCouponsByDate();
        System.out.println("5- ");
       couponList = couponRepo.findAll();
       couponList.forEach(System.out::println);

    }


}
