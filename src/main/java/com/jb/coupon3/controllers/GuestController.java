package com.jb.coupon3.controllers;

import com.jb.coupon3.beans.ClientType;
import com.jb.coupon3.beans.Company;
import com.jb.coupon3.beans.Customer;
import com.jb.coupon3.beans.UserDetails;
import com.jb.coupon3.exceptions.CustomExceptions;
import com.jb.coupon3.security.JWTutil;
import com.jb.coupon3.service.AdminService;
import com.jb.coupon3.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/GYGNcoupons/guest")
public class GuestController {
    private final GuestService guestService;
    private final JWTutil jwTutil;

    @GetMapping
    public ResponseEntity<?> allCoupons() throws CustomExceptions {
        String tempToken = jwTutil.generateToken(new UserDetails(ClientType.GUEST, "", ""));
        return ResponseEntity.ok()
                .header(  "Authorization", tempToken)
                .body(guestService.getAllCoupons());
    }

    /**
     * this method is for adding a new company in to the database
     * @param company new company information
     * @return request status response
     * @throws CustomExceptions in case the server found a company with similar data
     */
    @PostMapping("/addCompany")
    public ResponseEntity<?> addCompany (@RequestBody Company company,
                                         @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String tempToken = jwTutil.generateToken(new UserDetails(ClientType.GUEST, "", ""));
        token = tempToken;
        ClientType clientType = jwTutil.extractClientType(token);
        String newToken = jwTutil.checkUser(token, clientType);
        guestService.addCompany (company);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body("company " + company.getName() + " added");
    }

    /**
     * this method is for adding a new customer in to the database
     * @param customer new customer information
     * @return request status response
     * @throws CustomExceptions in case the server found a customer with similar data
     */
    @PostMapping("/addCustomer")
    public ResponseEntity<?> addCustomer (@RequestBody Customer customer,
                                          @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        ClientType clientType = jwTutil.extractClientType(token);
        String newToken = jwTutil.checkUser(token, clientType);
        guestService.addCustomer(customer);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body("customer " + customer.getFirstName() + " " + customer.getLastName() + " added");
    }

}
