package com.bakerysystem.Model;

import java.util.ArrayList;

public class Cart {
	private int customerId;
        private ArrayList<ProductLineItem> products;
        private int cartId;

    public Cart() {
    }

    public Cart(int customerId, ArrayList<ProductLineItem> listOfProducts, int cartId) {
        super();
        this.customerId = customerId;
        this.products = listOfProducts;
        this.cartId = cartId;
    }

    public Cart(int customerId,int cartId){
        super();
        this.customerId=customerId;
        this.cartId=cartId;

    }

    public Cart(int customerId, ArrayList<ProductLineItem> products) {
        this.customerId = customerId;
        this.products = products;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public ArrayList<ProductLineItem> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductLineItem> products) {
        this.products = products;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
}
