/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Daos;

import com.bakerysystem.Model.Order;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public interface OrderDao {
    boolean addOrder(Order order);
    Order getOrder(int orderId);
    ArrayList<Order> getAllOrders();
    ArrayList<Order> getOrdersByStatus(String status);
    boolean updateOrderStatus(Order ord);
    boolean removeOrder(int orderId);
    ArrayList<Order> getOrdersByCustomer(int customerId);
}
