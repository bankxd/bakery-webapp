/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.RestAPI;

import com.bakerysystem.Model.Category;
import com.bakerysystem.Services.CategoryServiceImpl;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("/categories-content")
public class CategoriesAPI {

    private final String SUCCESSFUL = "SUCCESSFUL!";
    private final String FAILED = "FAILED!";

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createCategory(Category cat) {
        if (new CategoryServiceImpl().addCategory(cat) == true) {
            return SUCCESSFUL;
        }
        return FAILED;
    }

    @POST
    @Path("/delete/{id}")
    public String removeCategory(@PathParam("id") int categoryId) {
        if (new CategoryServiceImpl().deleteCategory(categoryId) == true) {
            return SUCCESSFUL;
        }
        return FAILED;
    }

    @PUT
    @Path("/edit")
    public String editCategoryDetails(Category cat) {
        if (new CategoryServiceImpl().updateCategory(cat) == true) {
            return SUCCESSFUL;
        }
        return FAILED;
    }

    @GET
    @Path("/category/{id}")
    public Category retrieveCategory(@PathParam("id") int categoryId) {
          return new CategoryServiceImpl().getCategory(categoryId);
    }

    @GET
    @Path("/collection")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Category> retrieveAllCategories(@PathParam("category") String selectedCategory) {
        return new CategoryServiceImpl().getAllCategories();
    }
}
