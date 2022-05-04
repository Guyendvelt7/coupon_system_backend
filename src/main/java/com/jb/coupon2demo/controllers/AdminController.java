package com.jb.coupon2demo.controllers;

import com.jb.coupon2demo.beans.Company;
import com.jb.coupon2demo.beans.Customer;
import com.jb.coupon2demo.exceptions.CustomExceptions;
import com.jb.coupon2demo.security.JWTutil;
import com.jb.coupon2demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/GYGNcoupons/admin")
public class AdminController {
    private final JWTutil jwTutil;
    private final AdminService adminService;

    @PostMapping("/addCompany")
    public ResponseEntity<?> addCompany (@RequestBody Company company, @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        adminService.addCompany (company);
        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }

    @PutMapping("/updateCompany")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateCompany (@RequestBody Company company, @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        adminService.updateCompany(company);
        return  new ResponseEntity<>(newToken, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCompany/{companyId}")
    public ResponseEntity<?> deleteCompany(
            @PathVariable int companyId, @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        adminService.deleteCompany(companyId);
        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }

    @GetMapping("/allCompanies")
    public ResponseEntity<?> getAllCompanies (@RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(adminService.getAllCompanies()
                );
    }

    @GetMapping("/oneCompany/{id}")
    public ResponseEntity<?> getOneCompany (@PathVariable int id, @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(adminService.getOneCompany(id)
                );
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<?> addCustomer (@RequestBody Customer customer, @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        adminService.addCustomer(customer);
        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer (@RequestBody Customer customer, @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        adminService.updateCustomer(customer);
        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }

    @GetMapping("/oneCustomer/{id}")
    public ResponseEntity<?> getOneCustomer(@PathVariable int id, @RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(adminService.getOneCustomer(id)
                );
    }

    @GetMapping("/allCustomers")
    public ResponseEntity<?> getAllCustomers (@RequestHeader (name = "Authorization") String token){
        String newToken = jwTutil.checkUser(token);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(adminService.getAllCustomers()
                );
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer (@PathVariable int id, @RequestHeader (name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token);
        adminService.deleteCustomer(id);
        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }
}
