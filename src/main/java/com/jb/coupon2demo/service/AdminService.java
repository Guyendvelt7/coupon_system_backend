package com.jb.coupon2demo.service;

import com.jb.coupon2demo.beans.Company;
import com.jb.coupon2demo.beans.Customer;
import com.jb.coupon2demo.exceptions.CustomExceptions;
import com.jb.coupon2demo.exceptions.OptionalExceptionMessages;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminService extends  ClientService{

    public AdminService() {
    }

    @Override
    public boolean login(String email, String password) throws CustomExceptions {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            System.out.println("Admin Connected.");
            return true;
        }
        throw new CustomExceptions(OptionalExceptionMessages.LOGIN_EXCEPTION);
    }

    public void addCompany(Company company) throws CustomExceptions {
        if (companyRepo.existsByName(company.getName()) || companyRepo.existsByEmail(company.getEmail())) {
            throw new CustomExceptions(OptionalExceptionMessages.EMAIL_OR_NAME_EXISTS);
        }
        companyRepo.save(company);
        System.out.println("Company added successfully");
    }

    public void updateCompany(Company company) throws CustomExceptions {
        if (!Objects.equals(company.getName(), companyRepo.findById(company.getId()).get().getName())) {
            throw new CustomExceptions(OptionalExceptionMessages.CANT_UPDATE_COMPANY_NAME);
        }
        companyRepo.save(company);
        System.out.println("Company updated successfully");
    }

    public void deleteCompany(int companyId) throws CustomExceptions {
        if (!companyRepo.existsById(companyId)) {
            throw new CustomExceptions(OptionalExceptionMessages.COMPANY_NOT_FOUND);
        }
        companyRepo.deleteById(companyId);
        System.out.println("Company deleted successfully");
    }

    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    public Company getOneCompany(int companyId) throws CustomExceptions {
        if (!companyRepo.existsById(companyId)) {
            throw new CustomExceptions(OptionalExceptionMessages.COMPANY_NOT_FOUND);
        }
        return companyRepo.findById(companyId).get();
    }

    public void addCustomer(Customer customer) throws CustomExceptions {
        if (customerRepo.existsByEmail(customer.getEmail())) {
            throw new CustomExceptions(OptionalExceptionMessages.EMAIL_EXISTS);
        }
        customerRepo.save(customer);
        System.out.println("Customer added successfully");
    }

    public void updateCustomer(Customer customer) throws CustomExceptions {
        if (!customerRepo.existsById(customer.getId())) {
            throw new CustomExceptions(OptionalExceptionMessages.CUSTOMER_NOT_FOUND);
        }
        customerRepo.save(customer);
        System.out.println("Customer updated successfully");
    }

    public Customer getOneCustomer(int customerId) throws CustomExceptions {
        if(!customerRepo.existsById(customerId)){
            throw new CustomExceptions(OptionalExceptionMessages.CUSTOMER_NOT_FOUND);
        } else{
            return customerRepo.findById(customerId).get();
        }
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public void deleteCustomer(int customerId) throws CustomExceptions {
        if(!customerRepo.existsById(customerId)){
            throw new CustomExceptions(OptionalExceptionMessages.CUSTOMER_NOT_FOUND);
        } else{
            customerRepo.deleteById(customerId);
            System.out.println("Customer deleted successfully");
        }
    }
}
