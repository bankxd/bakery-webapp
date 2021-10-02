/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.RestAPI;

import com.bakerysystem.Model.*;
import com.bakerysystem.Services.IngredientServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Themba
 */

@Path("/ingredient-content")
public class IngredientsAPI {

    private final String SUCCESSFUL = "SUCCESSFUL!";
    private final String FAILED = "FAILED!";

    // ADDING A CATEGORY
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addIngredient(Ingredient ingr) {
        if (new IngredientServiceImpl().addIngredient(ingr) == true) {
            return SUCCESSFUL;
        }
        return FAILED;
    }

    @POST
    @Path("/delete/{id}")
    public String removeIngredient(@PathParam("id") int ingredientID) {
if (new IngredientServiceImpl().deleteIngredient(ingredientID) == true) {
            return SUCCESSFUL;
        }
        return FAILED;
    }

//    @PUT
//    @Path("/restock/{id}")
//    public String restock(Ingredient ingr) { // WHICH IS Essentially an Edit of the ingredient ++Quantity
//        if (new IngredientServiceImpl().updateIngredient(ingr) == true) {
//            return SUCCESSFUL;
//        }
//        return FAILED;
//    }

    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public String editIngreidient(Ingredient ingr) { // changin the name of the ingredient  [MEASUREMENT FIELD - kg, l, g .etc]
        if (new IngredientServiceImpl().updateIngredient(ingr) == true) {
            return SUCCESSFUL;
        }
        return FAILED;
    }

    @GET
    @Path("/collection")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Ingredient> retrieveAllIngredients() {
        
        return new IngredientServiceImpl().getAllIngredients();
    }

    @GET
    @Path("/ingredient/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ingredient getIngredient(@PathParam("id") int ingredientID) {

        // return new dao.getIngredient(ingredientID);
        return new IngredientServiceImpl().getIngredient(ingredientID);
    }
}
