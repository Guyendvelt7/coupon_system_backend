package com.jb.coupon2demo.clr.RepoTests;

import com.jb.coupon2demo.beans.Category;
import com.jb.coupon2demo.beans.Company;
import com.jb.coupon2demo.beans.Coupon;
import com.jb.coupon2demo.repositories.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

//@Component
@Order(2)
@RequiredArgsConstructor
public class CompanyRepoCrudTest implements CommandLineRunner {
    private final CompanyRepo companyRepo;

    @Override
    public void run(String... args) throws Exception {
        //update
        Company company = companyRepo.findByEmail("rikushet@rikushet.com");
        company.setPassword("rik123");
        companyRepo.save(company);
        //exist by email or name func.
        boolean isExist = companyRepo.existsByEmailOrName("rikushet@rikushet.com", "rikushet");
        if (isExist) {
            System.out.println("1- " + companyRepo.getByName("rikushet"));
        } else {
            System.out.println("1 - company not found");
        }
        //get by name func.
        company = companyRepo.getByName("rikushet");
        System.out.println("2- " + company.toString());
        //find by email and password func
        company = companyRepo.findByEmailAndPassword("samsung@samsung.com", "samsam");
        System.out.println("3- " + company.toString());
        //exist by email and password func.
        isExist = companyRepo.existsByEmailAndPassword("samsung@samsung.com", "samsam");
        if (isExist) {
            System.out.println("4- " + company.toString());
        } else {
            System.out.println("4 - company not found");
        }
        //exist by email func.
        isExist = companyRepo.existsByEmail("samsung@samsung.com");
        if (isExist) {
            System.out.println("5- " + company.toString());
        } else {
            System.out.println("5 - company not found");
        }
        //exist by name func.
        isExist = companyRepo.existsByName("samsung");
        if (isExist) {
            System.out.println("6- " + company.toString());
        } else {
            System.out.println("6 - company not found");
        }
        //find company coupons func.
        Set<Coupon> companyCoupons = companyRepo.findCompanyCoupons(companyRepo.findByName("samsung").getId());
        if (!companyCoupons.isEmpty()) {
            System.out.println("7- \n");
            companyCoupons.forEach(System.out::println);
        } else {
            System.out.println("7 - No coupons for this company found");
        }
      //find one company`s coupon
        Coupon coupon = companyRepo.findOneCompanyCoupon(company.getId());
        System.out.println(coupon);

        //find company coupons by category
        companyCoupons = companyRepo.findCompanyCouponsByCategory(Category.ELECTRIC_APPLIANCE, company.getId());
        if (!companyCoupons.isEmpty()) {
            System.out.println("8- \n");
            companyCoupons.forEach(System.out::println);
        } else {
            System.out.println("8 - No coupons for this company found");
        }
        //find company coupons by max price
        companyCoupons = companyRepo.findCompanyCouponsByMaxPrice(1000, company.getId());
        if (!companyCoupons.isEmpty()) {
            System.out.println("9- \n");
            companyCoupons.forEach(System.out::println);
        } else {
            System.out.println("9 - No coupons for this company found");
        }
    }

}
