package com.jb.coupon2demo.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author Yoav Hacmon, Guy Endvelt, Niv Pablo and Gery Glazer
 * 05.2022
 */

@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
/**
 * this class is used to concentrate the specific fields of the client information in the creation of a new token
 * {@link com.jb.coupon2demo.service.LoginService}
 */
public class UserDetails {
    private ClientType clientType;
    private String email;
    private String pass;
}
