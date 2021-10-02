package com.bakerysystem.Model;

public class ProductLineItem {

    private int productID;
    private String productName;
    private double productPrice;
    private int quantity;

    public ProductLineItem() {
    }

    public ProductLineItem(int productID, String productName, int quantity, double productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return "ProductLineItem [productID=" + productID + ", productName=" + productName + ", quantity=" + quantity
                + "]";
    }
}
