/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.client;

import com.bakerysystem.Model.Order;
import com.bakerysystem.properties.BSConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Themba
 */
public class OrdersClient {
    
    private DefaultClient<Order> dc;
    
    
    public OrdersClient() {
            dc = new DefaultClient<>();        
    }
    
     public String addOrder(Order ord){
        return dc.create(ord, "http://localhost:8081/BakerySystemRest/app/order-content/build");
    }
    
    public Order getOrder(int userid) {
        try {
            ObjectMapper ob = new ObjectMapper();
            Order o = ob.readValue(dc.get(userid, "http://localhost:8081/BakerySystemRest/app/order-content/order/{id}"), Order.class);
            
            return o;
        } catch (IOException ex) {
            Logger.getLogger(OrdersClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public   String remove(int userId) {
        return dc.remove(userId, "http://localhost:8081/BakerySystemRest/app/order-content/remove/{id}");
    }
    
    public String editOrder(Order cus) {
        return dc.update(cus, "http://localhost:8081/BakerySystemRest/app/order-content/edit");
    }

    public   ArrayList<Order> getAllOrders() {    
        ArrayList<Order> list = null;
        try {
            ObjectMapper ob = new ObjectMapper();
            Order [] o = ob.readValue(dc.getAll("http://localhost:8081/BakerySystemRest/app/order-content/collection"), Order[].class);
            list = new ArrayList<>(Arrays.asList(o));
            
        } catch (IOException ex) {
            Logger.getLogger(OrdersClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<Order> getCustomersOrders(int userId) {    
        ArrayList<Order> arr = getAllOrders();
        ArrayList<Order> usersOrders = new ArrayList<>();
        for (Order order : arr) {
            if(order.getCustomerId() == userId){
                usersOrders.add(order);
            }
        }
        return usersOrders;
    }
    
}
