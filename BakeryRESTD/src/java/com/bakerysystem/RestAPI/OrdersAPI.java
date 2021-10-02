/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.RestAPI;

import com.bakerysystem.Model.Order;
import com.bakerysystem.Services.OrderServiceImpl;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Themba
 */

@Path("/order-content")
public class OrdersAPI {
    
    private final String SUCCESSFUL = "SUCCESSFUL!";
    private final String FAILED = "FAILED!";

    
    @GET
    @Path("/order/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder(@PathParam("id") int orderId){
        
        return new OrderServiceImpl().getOrder(orderId);
    }
    
    @GET
    @Path("/collection")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Order> getAllOrders(){
        return new OrderServiceImpl().getAllOrders();
    }
    
    @POST
    @Path("/build")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addOrder(Order order){
         if (new OrderServiceImpl().addOrder(order) == true) {
            return SUCCESSFUL;
        }
        return FAILED;
    }
    
    @POST
    @Path("/remove/{id}")
    public String removeOrder(@PathParam("id") int orderId){
        if (new OrderServiceImpl().cancelOrder(orderId) == true) {
            return SUCCESSFUL;
        }
        return FAILED;
    }
    
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public String editOrder(Order order){
//        if (new OrderServiceImpl().(order) == true) {
//            return SUCCESSFUL;
//        }
        return FAILED;
    }
    
}
