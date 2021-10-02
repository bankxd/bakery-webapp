/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Services;

//import com.bakerysystem.Daos.CartDao;
//import com.bakerysystem.Daos.CartDaoImpl;
import com.bakerysystem.Daos.CustomerDao;
import com.bakerysystem.Daos.CustomerDaoImpl;
import com.bakerysystem.Daos.UserDao;
import com.bakerysystem.Daos.UserDaoImpl;
import com.bakerysystem.Model.Customer;
import com.bakerysystem.Model.ProductLineItem;
import com.bakerysystem.Model.User;
import com.bakerysystem.Notifier.NotificationSystem;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao custD;
    private UserDao ud;
//    private CartDao cartD;

    public CustomerServiceImpl() {

        ud = new UserDaoImpl();
        custD = new CustomerDaoImpl();
//        cartD = new CartDaoImpl();
    }

    @Override
    public Customer registerCustomer(Customer cust) {
        System.out.println(cust.toString());
        if (ud.getUser(cust.getEmail(), cust.getPassword()) != null) {
            return null;
        }
        custD.addCustomer(cust);
        ud.addUser(new User(cust.getEmail(), cust.getPassword(), "CUSTOMER"));
         int last = custD.getAllCustomers().size();
         
         new NotificationSystem().sendGmail(cust.getEmail(), "DoughKnot Bakery Store", "Hey " + cust.getFirstName() +" You have successfully registered your account with the DoughKnot online bakery store. Treat yourself to our collection of timeless delicious treats by browsing through our store.");
         return custD.getAllCustomers().get(last);
    }
    @Override
    public Customer login(String email, String password) {
        Customer cust = custD.getCustomer(ud.getUser(email, password).getUserId());
     
        return cust;
    }
    
     


    @Override
    public boolean updateCustomer(Customer cust) {
        return custD.updateCustomer(cust);
    }

    public static void main(String[] args) {
         //.login("email0@gmail","password");
         System.out.println( new CustomerServiceImpl().getAllCustomers());//c.getFirstName() + "'s Cart: ");
        
       
    }
    
    @Override
    public boolean confirmEmail(String email) {
        boolean sent = false;
        if (custD.getCustomerEmail(email) == null) {
            return false;
        }
        sent = ud.updatePassword(email);
        new NotificationSystem().sendGmail(email, "Password Recovery for DoughKnot Online Bakery", "your new password for log in is 158799 be we advise you update password after login");
        return true;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        return custD.getAllCustomers();
    }

    @Override
    public Customer getCustomer(int customerId) {
        return custD.getCustomer(customerId);
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        return custD.removeCustomer(customerId);
    }
     
   
}
