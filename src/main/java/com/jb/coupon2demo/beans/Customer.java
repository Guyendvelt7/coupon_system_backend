package com.jb.coupon2demo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @Column(nullable = false, length = 40)
    private String firstName;
    @Column(nullable = false, length = 40)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false, length = 11)
    private String password;
    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "customers", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    //@ToString.Exclude
    private Set<Coupon> coupons;

    private void setId(int id) {
        this.id = id;
    }

    public Set<Coupon> getCoupons() {
        return coupons;
    }
}
