package com.jb.coupon2demo.clr.RepoTests;

import com.jb.coupon2demo.beans.Coupon;
import com.jb.coupon2demo.beans.Customer;
import com.jb.coupon2demo.repositories.CompanyRepo;
import com.jb.coupon2demo.repositories.CouponRepo;
import com.jb.coupon2demo.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

//@Component
@Order(4)
@RequiredArgsConstructor
public class CustomerRepoCrudeTest implements CommandLineRunner {
    private final CustomerRepo customerRepo;
    private final CouponRepo couponRepo;
    private final CompanyRepo companyRepo;

    @Override
    public void run(String... args) throws Exception {
        //find by first and last name func.
        Customer customer = customerRepo.findByFirstNameAndLastName("geri", "glazer");
//        System.out.println("1- " + customer.toString());
//        //exist by email and pass func.
//        boolean isExist = customerRepo.existsByEmailAndPassword("gerig@gmail.com", "654321");
//        if(isExist){
//            System.out.println("2- " + customerRepo.findById(customerRepo.findByFirstNameAndLastName("geri", "glazer").getId()));
//        }
//        //exist by email func.
//        isExist = customerRepo.existsByEmail("zeevik@gmail.com");
//        if(isExist){
//            System.out.println("3- " + customerRepo.findById(customerRepo.findByFirstNameAndLastName("zeev", "mindali").getId()));
//        }
//        //update customer func.
//        customer = customerRepo.findByEmailAndPassword("zeevik@gmail.com", "12345678");
//        System.out.println("4- " + customer.toString());
//        customer.setPassword("1234");
//        customerRepo.save(customer);
//        System.out.println("5- " + customer);
        //add purchased coupon to customer func.(join table "customer_vs_coupon")
        //customerRepo.addCouponToCustomer(1, 1);
        //customerRepo.addCouponToCustomer(1, 2);
//        System.out.println("6- " + customerRepo.findById(2));
//        //is purchased coupon by customer func.
//        List<Coupon> isPurchased = couponRepo.isCouponPurchased(1, 2);
//        System.out.println("7- " + isPurchased.size());
//        //find one coupon from customer func.
//        Coupon coupon = customerRepo.findCustomerCoupon(2, 1);
//        System.out.println("8- " + coupon);
//        //find all customer coupons
//        Set<Coupon> allCustomerCoupons = customerRepo.findById(2).get().getCoupons();
//        allCustomerCoupons.forEach(System.out::println);
//        //delete coupon func.
//        System.out.println("9- " + couponRepo.findAll());
//        companyRepo.deleteById(1);
//        couponRepo.deleteById(5); //Deletes from Coupons table and Customer_vs coupon table
//        System.out.println("10- " + couponRepo.findAll());
//        System.out.println("11- " + companyRepo.findById(1));
//        System.out.println("12- " + customerRepo.findById(1));

    }
}
