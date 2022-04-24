package com.jb.coupon2demo.repositories;

import com.jb.coupon2demo.beans.Coupon;
import com.jb.coupon2demo.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    Customer findByFirstNameAndLastName(String fName, String lname);
    Customer findByEmailAndPassword(String email,String password);
    Customer findByEmail (String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `customer_vs_coupons` (customer_id, coupon_id) VALUES (?,?)", nativeQuery = true)
    void addCouponToCustomer(int customer_id, int coupon_id);

    @Query("SELECT coup FROM Coupon coup WHERE coup.id IN (SELECT coup.id FROM coup.customers cust WHERE cust.id = ?1 and coup.id = ?2)")
    Coupon findCustomerCoupon(int custId, int cpnId);

    //NOT WORKING
//    @Query(value = "SELECT c FROM customer_vs_coupons c WHERE customer_id=?1")
//    Set<Coupon> findAllCustomerCoupons (int customer_id);
}
