package com.jb.coupon2demo.beans;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * @author Yoav Hacmon, Guy Endvelt, Niv Pablo and Gery Glazer
 * 05.2022
 */

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coupons")
/**
 * this class defines the required information for the creation of a new coupon.
 */
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private int companyId;
    /**
     * @annotations:
     * ManyToMany is to define the relationship between the coupons and the customers.
     * Many coupons may be purchased by many customers
     *
     *
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "customer_vs_coupons", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
    @ToString.Exclude
    private Set<Customer> customers;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
    @Column(nullable = false, length = 40)
    private String title;
    private String description;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startDate;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date endDate;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private double price;
    private String image;

    private void setId(int id) {
        this.id = id;
    }
}
