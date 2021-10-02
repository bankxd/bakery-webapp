/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Services;

import com.bakerysystem.Model.Category;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public interface CategoryService {
    
    ArrayList<Category> getAllCategories();
    boolean addCategory(Category category);
    Category getCategory(int categoryId);
    boolean deleteCategory(int categoryId);
    boolean updateCategory(Category category);
}
