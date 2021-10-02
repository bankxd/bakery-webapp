/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Services;

import com.bakerysystem.Daos.IngredientDao;
import com.bakerysystem.Daos.IngredientDaoImpl;
import com.bakerysystem.Daos.ProductDao;
import com.bakerysystem.Daos.ProductDaoImpl;
import com.bakerysystem.Model.Ingredient;
import com.bakerysystem.Model.ProductLineItem;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public class IngredientServiceImpl implements IngredientService {
    
    private IngredientDao ingrD;
    private ProductDao prodD;

    public IngredientServiceImpl() {
        ingrD = new IngredientDaoImpl();
        prodD = new ProductDaoImpl();
    }

    @Override
    public Ingredient getIngredient(int ingredientId) {
        return ingrD.getIngredient(ingredientId);
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) {
        return ingrD.addIngredient(ingredient);
    }

    @Override
    public boolean deleteIngredient(int ingredientId) {
        return ingrD.removeIngredient(ingredientId);
    }

    @Override
    public boolean updateIngredient(Ingredient ingredient) {
        return ingrD.updateIngredient(ingredient);
    }

    @Override
    public ArrayList<Ingredient> getAllIngredients() {
        return ingrD.getAllIngredients();
    }
    
   
}
