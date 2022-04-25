package com.jb.coupon2demo.service;

import com.jb.coupon2demo.beans.Category;
import com.jb.coupon2demo.beans.Coupon;
import com.jb.coupon2demo.beans.Customer;
import com.jb.coupon2demo.exceptions.CustomExceptions;
import com.jb.coupon2demo.exceptions.OptionalExceptionMessages;
import com.jb.coupon2demo.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private int customerId;

    public boolean login(String email, String password) throws CustomExceptions {
        Integer id = customerRepo.findByEmailAndPassword(email, password).getId();
        if (id == null) {
            throw new CustomExceptions(OptionalExceptionMessages.CUSTOMER_NOT_FOUND);
        }
        customerId = id;
        if (customerId > 0) {
            System.out.println("Customer connected.");
            return true;
        } else {
            throw new CustomExceptions(OptionalExceptionMessages.WRONG_EMAIL_OR_PASSWORD);
        }
    }

    public void purchaseCoupon(Coupon coupon) throws CustomExceptions {
        if(customerRepo.isCouponPurchased(customerId, coupon.getId()).isEmpty()){
            customerRepo.addCouponToCustomer(customerId, coupon.getId());
            System.out.println("Coupon purchased.");
        }
        else{
            throw new CustomExceptions(OptionalExceptionMessages.CANT_ADD_COUPON);
        }
    }

    public Set<Coupon> getAllCustomerCoupons(){
        return customerRepo.findAllCustomerCoupons(customerId);
    }

    public Set<Coupon> getCustomerCoupons(Category category) throws CustomExceptions {
        Set<Coupon> coupons = customerRepo.findAllCustomerCouponsByCategory(customerId, category);
        if(coupons.isEmpty()){
            throw new CustomExceptions(OptionalExceptionMessages.COUPON_NOT_FOUND_BY_CATEGORY);
        }
        return coupons;
    }

    public Set<Coupon> getCustomerCoupons(double maxPrice) throws CustomExceptions {
        Set<Coupon> coupons = customerRepo.findAllCustomerCouponsMaxPrice(customerId, maxPrice);
        if(coupons.isEmpty()){
            throw new CustomExceptions(OptionalExceptionMessages.COUPON_NOT_FOUND_MAX_PRICE);
        }
        return coupons;
    }

    public Customer getCustomerDetails() throws CustomExceptions {
        Customer customer = customerRepo.findById(customerId).get();
        if (customer == null){
            throw new CustomExceptions(OptionalExceptionMessages.CUSTOMER_NOT_FOUND);
        }
        return customer;
    }
}
