package com.bakerysystem.Model;

import com.bakerysystem.Model.Ingredient;
import java.io.Serializable;
import java.util.ArrayList;

   public class Product implements Serializable {
	private int productID ;
	private String productName ; 
        private String photo;
	private ArrayList<Ingredient> recipeArr ;       
        private String productDescription;
        private String productWarnings;
        private double price;
        private double actualPrice;
        private int discount;   
        private int categoryID; 
        
        public Product() {
    
        }

    public Product(int productID, String productName, String photo, String productDescription, String productWarnings, double price, int discount, int categoryID) {
        this.productID = productID;
        this.productName = productName;
        this.photo = photo;
        this.productDescription = productDescription;
        this.productWarnings = productWarnings;
        this.price = price;
        this.discount = discount;
        this.categoryID = categoryID;
        this.recipeArr = new ArrayList<>();
    }

    public Product(String productName, String photo, String productDescription, String productWarnings, double price, int discount, int categoryID) {
        this.productName = productName;
        this.photo = photo;
        this.productDescription = productDescription;
        this.productWarnings = productWarnings;
        this.price = price;
        this.discount = discount;
        this.categoryID = categoryID;
        this.recipeArr = new ArrayList<>();
    }
   
    
    
	

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", category=" + categoryID
				 + "]";
	}
    public int getProductID() {
        return productID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
        setActualPrice((double)((100-discount)/100)*this.price);
    }
        
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

        public int getCategoryID() {
            return categoryID;
        }

        public void setCategoryID(int categoryID) {
            this.categoryID = categoryID;
        }
        
	public ArrayList<Ingredient> getRecipeArr() {
		return recipeArr;
	}
	public void setRecipeArr(ArrayList<Ingredient> recipeArr) {
		this.recipeArr = recipeArr;
	}
	
        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public double getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(double actualPrice) {
            this.actualPrice = actualPrice;
        }

        public String getProductDescription() {
            return productDescription;
        }

        public void setProductDescription(String productDescription) {
            this.productDescription = productDescription;
        }

    public String getProductWarnings() {
        return this.productWarnings;
    }

    public void setProductWarnings(String productWarnings) {
        this.productWarnings = productWarnings;
    }
}
