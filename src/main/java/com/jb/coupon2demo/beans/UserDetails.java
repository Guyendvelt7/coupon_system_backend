package com.jb.coupon2demo.beans;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private ClientType clientType;
    private String email;
    private String pass;
}
