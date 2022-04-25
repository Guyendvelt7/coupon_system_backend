package com.jb.coupon2demo.login;

import com.jb.coupon2demo.beans.ClientType;
import com.jb.coupon2demo.exceptions.CustomExceptions;
import com.jb.coupon2demo.service.AdminService;
import com.jb.coupon2demo.service.ClientService;
import com.jb.coupon2demo.service.CompanyService;
import com.jb.coupon2demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginManager {
    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;

    public ClientService login (String email, String password, ClientType clientType) throws CustomExceptions {
        ClientService clientService = null;
        boolean isLogin = false;
        switch (clientType){
            case ADMIN:
                clientService=adminService;
                isLogin= clientService.login(email, password);
                break;
            case COMPANY:
                clientService = companyService;
                isLogin = clientService.login(email, password);
                break;
            case CUSTOMER:
                clientService = customerService;
                isLogin = clientService.login(email, password);
                break;
        }
        if(!isLogin){
            return null;
        }else{
            return clientService;
        }
    }
}
