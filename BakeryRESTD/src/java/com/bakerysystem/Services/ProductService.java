/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Services;

import com.bakerysystem.Model.Product;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public interface ProductService {
    
    ArrayList<Product> getAllProducts();
    Product getProduct(int productId);
    ArrayList<Product> getProductsByCategory(int category);
    boolean removeProduct(int productId);
}
