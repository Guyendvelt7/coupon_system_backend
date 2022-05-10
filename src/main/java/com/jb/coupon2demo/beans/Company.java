package com.jb.coupon2demo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
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
@Table(name = "companies")
/**
 *
 */
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @Column(unique = true, nullable = false, length = 40)
    private String name;
    @Column(unique = true, nullable = false, length = 40)
    private String email;
    @Column(nullable = false, length = 11)
    private String password;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "companyId", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Coupon> coupons;

    /**
     *
     */
    private void setId(int id) {
        this.id = id;
    }
}
