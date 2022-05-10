package com.jb.coupon2demo.repositories;

import com.jb.coupon2demo.beans.Category;
import com.jb.coupon2demo.beans.Company;
import com.jb.coupon2demo.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

public interface CompanyRepo extends JpaRepository<Company, Integer> {
    Company findByName(String name);
    Company findByEmail (String email);
    Company getByName(String name);
    Company findByEmailAndPassword(String email,String password);
    boolean existsByEmailAndPassword(String email,String password);
    boolean existsByEmailOrName(String email,String name);
    boolean existsByEmail(String email);
    boolean existsByName(String name);

//    @Transactional
//    @Modifying
//    @Query(value = "DELETE FROM customer_vs_coupons WHERE coupon_id = ?1", nativeQuery = true)
//    void deleteCoupon(int coupon_id);

    @Query(value = "select c from Coupon c where companyId = ?1")
    Set<Coupon> findCompanyCoupons(int companyId);

    @Query(value = "select c from Coupon c where category = ?1 AND companyId =?2")
    Set<Coupon> findCompanyCouponsByCategory(Category category, int companyId);

    @Query(value = "select c from Coupon c where price < ?1 AND companyId =?2")
    Set<Coupon> findCompanyCouponsByMaxPrice(double maxPrice, int companyId);
}
