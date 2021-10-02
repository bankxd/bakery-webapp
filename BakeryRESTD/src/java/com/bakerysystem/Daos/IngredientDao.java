/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Daos;

import com.bakerysystem.Model.Ingredient;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public interface IngredientDao {
    Ingredient getIngredient(int ingredientID);
    boolean addIngredient(Ingredient ingred);
    boolean updateIngredient(Ingredient ingred);
    boolean removeIngredient(int ingredientID);
    ArrayList<Ingredient> getAllIngredients();
    boolean reduceIngredients(ArrayList<Ingredient> recipe, int quantity);
}
