package com.jb.coupon2demo.clr.servicesTests;

import com.jb.coupon2demo.beans.Category;
import com.jb.coupon2demo.beans.Company;
import com.jb.coupon2demo.beans.Coupon;
import com.jb.coupon2demo.beans.Customer;
import com.jb.coupon2demo.repositories.CompanyRepo;
import com.jb.coupon2demo.repositories.CouponRepo;
import com.jb.coupon2demo.repositories.CustomerRepo;
import com.jb.coupon2demo.service.CompanyService;
import com.jb.coupon2demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Component
@Order(8)
@RequiredArgsConstructor
public class CustomerServiceTests implements CommandLineRunner {
private final CustomerService customerService;
private final CustomerRepo customerRepo;
private final CouponRepo couponRepo;
private final CompanyService companyService;
private final CompanyRepo companyRepo;

    @Override
    public void run(String... args) throws Exception {

        Customer customer = customerRepo.findByEmail("zeevik@gmail.com");
        //customer login
        customerService.login(customer.getEmail(), customer.getPassword());

        Coupon coupon = couponRepo.findById(1).get();
        //purchase coupon
        //customerService.purchaseCoupon(coupon);
        //get all customer`s coupons
        Set<Coupon> customerCoupons = customerService.getAllCustomerCoupons();
        System.out.println("1 - " + customerCoupons);
        //get all customer`s coupons by category
        Set<Coupon> customerCouponsByCategory = customerService.getCustomerCoupons(Category.ELECTRIC_APPLIANCE);
        System.out.println("2 - " + customerCouponsByCategory);
        //get all customer's coupons by maxPrice
        Set<Coupon> customerCouponsByMaxPrice = customerService.getCustomerCoupons(2001);
        System.out.println("3- " + customerCouponsByMaxPrice);
        //get customer details
        System.out.println(customerService.getCustomerDetails());
    }

}
//        companyService.addCoupon(new Coupon(
//                0, companyRepo.findByName("rikushet").getId(), null, Category.EXTREME, "helmet", "for_dirt_bike",
//                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(30)), 500, 199, ""));