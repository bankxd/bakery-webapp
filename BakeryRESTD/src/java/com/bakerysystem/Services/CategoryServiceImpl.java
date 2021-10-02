/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Services;

import com.bakerysystem.Daos.CategoryDao;
import com.bakerysystem.Daos.CategoryDaoImpl;
import com.bakerysystem.Model.Category;
import java.util.ArrayList;

/**
 *
 * @author keoagile
 */
public class CategoryServiceImpl implements CategoryService {
    
    private CategoryDao catD;

    public CategoryServiceImpl() {
    
        catD = new CategoryDaoImpl();
    }
    
   

    @Override
    public ArrayList<Category> getAllCategories() {
        return catD.getAllCategories();
    }

    @Override
    public Category getCategory(int categoryId) {
        return catD.getCategory(categoryId);
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        return catD.removeCategory(categoryId);
    }

    @Override
    public boolean updateCategory(Category category) {
        return catD.updateCategory(category);
    } 

    @Override
    public boolean addCategory(Category category) {
        return catD.addCategory(category);
    }
}
