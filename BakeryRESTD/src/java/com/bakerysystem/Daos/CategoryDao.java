/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Daos;

import com.bakerysystem.Model.Category;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public interface CategoryDao {
    
   boolean addCategory(Category cat);
   boolean removeCategory(int categoryID);
   Category getCategory(int categoeyID);
   ArrayList<Category> getAllCategories();
   boolean updateCategory(Category cat);
}
