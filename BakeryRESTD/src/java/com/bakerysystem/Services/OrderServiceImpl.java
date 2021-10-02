/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Services;

//import com.bakerysystem.Reports;
import com.bakerysystem.Model.Order;
import com.bakerysystem.Model.ProductLineItem;
import com.bakerysystem.Daos.IngredientDao;
import com.bakerysystem.Daos.IngredientDaoImpl;
import com.bakerysystem.Daos.OrderDao;
import com.bakerysystem.Daos.OrderDaoImpl;
import com.bakerysystem.Daos.ProductDao;
import com.bakerysystem.Daos.ProductDaoImpl;
import com.bakerysystem.Model.Product;
import com.bakerysystem.Notifier.NotificationSystem;
import java.util.ArrayList;
import javax.management.Notification;

/**
 *
 * @author keoagile
 */
public class OrderServiceImpl implements OrderService{
    
    private OrderDao od ;
    private IngredientDao ingrD;
    private ProductDao prodD;

    public OrderServiceImpl() {
        od = new OrderDaoImpl();
        ingrD  = new IngredientDaoImpl();
        prodD = new ProductDaoImpl();
    }

    @Override
    public boolean addOrder(Order order) {
        
        boolean done = od.addOrder(order);
        
            for(ProductLineItem pli: order.getOrderLineArr()){
             done = ingrD.reduceIngredients(prodD.getProduct(pli.getProductID()).getRecipeArr(), pli.getQuantity());
            }
        return done;
    }

    @Override
    public Order getOrder(int orderId) {
        return od.getOrder(orderId);
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        return od.getAllOrders();
    }

    @Override
    public boolean cancelOrder(int orderId) {
        return od.removeOrder(orderId);
    }

    @Override
    public ArrayList<Order> getOrdersByCustomer(int customerId) {
        return od.getOrdersByCustomer(customerId);
    }

    @Override
    public ArrayList<Order> getOrdersByStatus(String status) {
        return od.getOrdersByStatus(status);
    }
    
    public static void main(String [] args){
        Order o = new Order(100,2, "ROSEBANK VZAP", "PREPARING", "PAID");
        
        o.setOrderLineArr(new ArrayList<>());
        for(int i = 0; i < 10; i++){
              o.getOrderLineArr().add(new ProductLineItem(1, "Macroons", 1, 9.99));
        }
        System.out.println(o.toString());
        new OrderServiceImpl().addOrder(o);
    }
}
