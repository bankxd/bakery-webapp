package com.bakerysystem.Model;

import java.util.ArrayList;

public class Category {

    //Attributes
    private int categoryID;
    private String categoryName ; 

    public Category(){}
    
    public Category(int catID, String catName) {
        setCategoryID(catID);
        setCategoryName(catName);
    }

    public Category(String catNAme){
        setCategoryName(catNAme);
    }

    @Override
    public String toString() {
        return "Category{" + "categoryID=" + categoryID + ", categoryName=" + categoryName + '}';
    }

    
    //Acessors/Mutators
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
