/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.client;

import com.bakerysystem.Model.Category;
import com.bakerysystem.Model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Themba
 */
public class CategoriesClient {

    DefaultClient dc;

    public CategoriesClient() {
        dc = new DefaultClient();
    }

    public String addCategory(Category cat) { //  
        return dc.create(cat, "http://localhost:8081/BakerySystemRest/app/categories-content/add");// retrieved response
    }

    public ArrayList<Category> getCategories() {
        ArrayList<Category> list = null;
        try {
            ObjectMapper ob = new ObjectMapper();
            Category[] cats = ob.readValue(dc.getAll("http://localhost:8081/BakerySystemRest/app/categories-content/collection"), Category[].class);
            list = new ArrayList(Arrays.asList(cats));

        } catch (IOException ex) {
            Logger.getLogger(CategoriesClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Category getACategory(int catID) {
        Category cat = null;
        try {
            ObjectMapper ob = new ObjectMapper();
            cat = ob.readValue(dc.get(catID, "http://localhost:8081/BakerySystemRest/app/categories-content/category/{id}"), Category.class);

        } catch (IOException ex) {
            ex.getMessage();
        }
        return cat;// retrieved response
    }

    public String deleteCategory(int catID) {  //public Response addCategory(){

        return dc.remove(catID, "http://localhost:8081/BakerySystemRest/app/categories-content/delete/{id}"); // retrieved response
    }

    public String updateCategory(Category cat) {  //public Response addCategory(){

        return dc.update(cat, "http://localhost:8081/BakerySystemRest/app/categories-content/edit"); //update response
    }

//    public static void main(String[] args) {
//        ArrayList<Category> cats = new CategoriesClient().getCategories();
//        for (Category c : cats) {
//            System.out.println(c.getCategoryName());
//        }
//    }
}
