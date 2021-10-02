/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Services;

import com.bakerysystem.Model.Ingredient;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public interface IngredientService {
    
    Ingredient getIngredient(int ingredientId);
    boolean addIngredient(Ingredient ingredient);
    boolean deleteIngredient(int ingredientId);
    boolean updateIngredient(Ingredient ingredient);
    ArrayList<Ingredient> getAllIngredients();
}
