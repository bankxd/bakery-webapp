/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.client;

import com.bakerysystem.Model.Ingredient;
import com.bakerysystem.properties.BSConfig;
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
public class IngredientsClient {
    
    private DefaultClient<Ingredient> dc;
    
    
    public IngredientsClient() {
            dc = new DefaultClient<>();        
    }
    
     public String addIngredient(Ingredient admin){
        return dc.create(admin, "http://localhost:8081/BakerySystemRest/app/ingredient-content/add");
    }
    
    public Ingredient getIngredient(int userid) {
        Ingredient ingr = null;
        try {
            ObjectMapper ob = new ObjectMapper();
            ingr = ob.readValue(dc.get(userid, "http://localhost:8081/BakerySystemRest/app/ingredient-content/ingredient/{id}"), Ingredient.class);
            
            
        } catch (IOException ex) {
            Logger.getLogger(IngredientsClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ingr; 
    }
    
    public   String remove(int userId) {
        return dc.remove(userId, "http://localhost:8081/BakerySystemRest/app/ingredient-content/delete/{id}");
    }
    
    public String updateDetails(Ingredient cus) {
        return dc.update(cus, "http://localhost:8081/BakerySystemRest/app/ingredient-content/edit");
    }

    public   ArrayList<Ingredient> getAllIngredients() {    
        Ingredient [] ingrs = null;
        ArrayList<Ingredient> list = null;
        try {
            ObjectMapper ob = new ObjectMapper();
            ingrs = ob.readValue(dc.getAll("http://localhost:8081/BakerySystemRest/app/ingredient-content/collection"), Ingredient[].class);
            list = new ArrayList(Arrays.asList(ingrs));
            
        } catch (IOException ex) {
            Logger.getLogger(IngredientsClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static void main(String[] args) {
         
//         Ingredient c = new IngredientsClient().getIngredient(3);
//          for(Ingredient c : new IngredientsClient().getAllIngredients()){
//              System.out.println(c.getIngredientName());
//          }

//           Ingredient c = new Ingredient(0, "Random-Ingr", 50);
           String s = new IngredientsClient().remove(9);//.updateDetails(c);
           System.out.println(s);
    }
    
}
