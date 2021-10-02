package com.bakerysystem.Model;

public class ProductLineItem {

    private int productID;
    private String productName;
    private int quantity;
    private double productPrice;

    public ProductLineItem() {
    }

    public ProductLineItem(int productID, String productName, int quantity, double productPrice) {
        super();
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.productPrice = productPrice;
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

    @Override
    public String toString() {
        return "ProductLineItem [productID=" + productID + ", productName=" + productName + ", quantity=" + quantity
          + "]";
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
