/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Daos;

import com.bakerysystem.Model.Product;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public interface ProductDao {
    boolean addProduct(Product product);
    Product getProduct(int productId);
    ArrayList<Product> getProducts();
    public boolean removeProduct(int productId);
    public boolean updateProduct(Product prod); 
}
