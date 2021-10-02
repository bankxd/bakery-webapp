package com.bakerysystem.Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public class Order {
    
    private int orderID;
    private ArrayList<ProductLineItem> orderLineArr;
    private double totalPrice;
    private int customerId;
    private String deliveryAddressId;
    private String orderStatus;
    private String paymentStatus;
    private Date orderDate;

    public Order() {
    }

    public Order(int orderID,  int customerId, double totalPrice, String orderStatus, String deliveryAddressId, Date orderDate, String paymentStatus) {
        this.orderID = orderID;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.deliveryAddressId = deliveryAddressId;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.orderDate = orderDate;
    }

    public Order(double totalPrice, int customerId, String deliveryAddressId, String orderStatus, String paymentStatus) {
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.deliveryAddressId = deliveryAddressId;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public ArrayList<ProductLineItem> getOrderLineArr() {
        return orderLineArr;
    }

    public void setOrderLineArr(ArrayList<ProductLineItem> orderLineArr) {
        this.orderLineArr = orderLineArr;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
