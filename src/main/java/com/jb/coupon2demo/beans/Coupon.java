package com.jb.coupon2demo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private  int id;
    private int companyId;
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
