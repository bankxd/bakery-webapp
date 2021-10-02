package com.bakerysystem.Services;

import com.bakerysystem.Model.Order;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public interface OrderService {
     boolean addOrder(Order order);
    Order getOrder(int orderId);
    ArrayList<Order> getAllOrders();
    ArrayList<Order> getOrdersByStatus(String status);
    boolean cancelOrder(int orderId);
    ArrayList<Order> getOrdersByCustomer(int customerId);
}