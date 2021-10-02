/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.client;

import com.bakerysystem.Model.Product;
import com.bakerysystem.Services.ProductsClientService;
//import com.bakerysystem.Services.ProductsClientService;
import com.bakerysystem.properties.BSConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Themba
 */
public class ProductsClient {
    
    //private ProductsClientService pCS;
    DefaultClient<Product> dc ;
    private String url;
    
    public ProductsClient(){
        url = new BSConfig().getURL("product-content");
        dc = new DefaultClient<Product>();
       //pCS = new ProductsClientService();
        
        System.out.println(url);
    }

    public String getImage(int productID){
        String response = dc.getString(productID, url + "image/{id}");
        System.out.println(response);
        return  response;
    }
    
//    public String sendImage(int productID){
//        return  dc.create(productID, "http://localhost:8081/BakerySystemRest/app/product-content/image/{id}");
//    }
    
    
    public ArrayList<Product> recieveProducts() {
       Product [] prods = null;
       ArrayList<Product> list = null ;
        try {
            ObjectMapper ob  = new ObjectMapper();
            prods = ob.readValue(dc.getAll(url + "collection"), Product[].class);
            list = new ArrayList(Arrays.asList(prods));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public Product recieveProduct(int productID){
        Product prod = null;
        try {
            ObjectMapper ob  = new ObjectMapper();
            prod = ob.readValue(dc.get(productID, "http://localhost:8081/BakerySystemRest/app/product-content/product/{id}"), Product.class);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return prod;
    }

    public  String addProduct(Product newProd) {
        return dc.create(newProd, "http://localhost:8081/BakerySystemRest/app/product-content/add") ;
    }
    
    public String updateProduct(Product prod){
        return dc.update(prod, "http://localhost:8081/BakerySystemRest/app/product-content/update");
    }
    
    public String removeProduct(int prodId){
        return dc.remove(prodId, "http://localhost:8081/BakerySystemRest/app/product-content/remove/{id}");
    }

    public static void main(String [] args){
        
//            System.out.println(new ProductsClient().recieveProduct(4).getProductName());
            //        System.out.println();
            ArrayList<Product> arr = new ProductsClient().recieveProducts();
            if(arr.isEmpty()){
                System.out.println("yebo iyabheda");
            }
            for (Product object : arr) {
                System.out.println(object.getProductName());
            }
    }
}