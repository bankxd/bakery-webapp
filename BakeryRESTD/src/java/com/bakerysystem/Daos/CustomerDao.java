/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Daos;

import com.bakerysystem.Model.Customer;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public interface CustomerDao {
    
    boolean addCustomer(Customer cust);
    Customer getCustomer(int custerID);
    boolean updateCustomer(Customer cust);
    ArrayList<Customer> getAllCustomers();
    boolean removeCustomer(int customerId);
    String getCustomerEmail(String email);
    String generateOtp(String email);
}
