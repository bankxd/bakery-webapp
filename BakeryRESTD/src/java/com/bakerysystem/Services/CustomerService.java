/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Services;

import com.bakerysystem.Model.Customer;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public interface CustomerService {
    
    Customer registerCustomer(Customer cust);
    Customer login(String email, String password);
    boolean updateCustomer(Customer cust);
    boolean confirmEmail(String email);
    ArrayList<Customer> getAllCustomers();
    Customer getCustomer(int customerId);
    boolean deleteCustomer(int customerId);
    
}
