package com.jb.coupon2demo.clr;

import com.jb.coupon2demo.beans.Category;
import com.jb.coupon2demo.beans.Company;
import com.jb.coupon2demo.beans.Coupon;
import com.jb.coupon2demo.beans.Customer;
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
@RequiredArgsConstructor
@Order(1)
public class DbBuilderTests implements CommandLineRunner {
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;
    private final CouponRepo couponRepo;

    @Override
    public void run(String... args) throws Exception {

        Company comp1 = Company.builder()
                .name("samsung")
                .email("samsung@samsung.com")
                .password("samsam")
                .build();
        Company comp2 = Company.builder()
                .name("rikushet")
                .email("rikushet@rikushet.com")
                .password("riky")
                .build();
        companyRepo.saveAll(List.of(comp1, comp2));

        Coupon coup1 = Coupon.builder()
                .amount(200)
                .category(Category.ELECTRIC_APPLIANCE)
                .companyId(companyRepo.findByName("samsung").getId())
                .description("tv")
                .price(2000)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30L)))
                .title("4ktv")
                .build();
        Coupon coup2 = Coupon.builder()
                .amount(100)
                .category(Category.OUTDOOR)
                .companyId(companyRepo.findByName("rikushet").getId())
                .description("sleeping_bag")
                .price(150)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30L)))
                .title("6people")
                .build();
        couponRepo.saveAll(List.of(coup1, coup2));

        Customer cmr1 = Customer.builder()
                .firstName("zeev")
                .lastName("mindali")
                .email("zeevik@gmail.com")
                .password("12345678")
                .build();
        Customer cmr2 = Customer.builder()
                .firstName("geri")
                .lastName("glazer")
                .email("gerig@gmail.com")
                .password("654321")
                .build();
        customerRepo.saveAll(List.of(cmr1, cmr2));
    }
}
