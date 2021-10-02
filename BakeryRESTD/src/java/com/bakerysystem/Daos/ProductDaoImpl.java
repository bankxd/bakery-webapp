/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Daos;

import com.bakerysystem.Model.Ingredient;
import com.bakerysystem.Model.Product;
import com.bakerysystem.databaseAccess.DBManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author keoagile
 */
public class ProductDaoImpl implements ProductDao {

    private Connection myCon9;
    private PreparedStatement ps;
    private PreparedStatement ps1;
    private ResultSet rs;

    public ProductDaoImpl() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load JDBC/ODBC driver." + e.toString());
            e.printStackTrace();
        }

        String URL = "jdbc:mysql://localhost:3306/cakeshop";
        try {
            myCon9 = DriverManager.getConnection(URL, "root", "root");
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

//    public static void main(String[] args) {
//        Product p = new Product("Bread", "photo", "Made form whole grain", "made in a nut factory may contain nuts", 15.0, 0, 2);
//        p.setRecipeArr(new ArrayList<>());
//        p.getRecipeArr().add(new Ingredient(2, "Cake Flour", 3));
//        p.getRecipeArr().add(new Ingredient(3,"Cake Flour", 3));
//        System.out.println( new ProductDaoImpl().addProduct(p));
//        
//        System.out.println( new ProductDaoImpl().getProduct(1));
//        
//       ;
//    }
    @Override
    public boolean addProduct(Product product) {
        int check = 0;
        int productId = 0;
        try {
            myCon9.setAutoCommit(false);
            ps = myCon9.prepareStatement("INSERT INTO PRODUCTSTABLE(PRODUCTID, PRODUCTNAME, PHOTO, PRODUCTDESCRIPTION, PRODUCTWARNINGS, PRICE, DISCOUNT, ACTIVITY, CATEGORYID ) VALUES(null,?,?,?,?,?,?,'ACTIVE',?)");
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getPhoto());
            ps.setString(3, product.getProductDescription());
            ps.setString(4, product.getProductWarnings());
            ps.setDouble(5, product.getPrice());
            ps.setInt(6, product.getDiscount());
            ps.setInt(7, product.getCategoryID());
            check = ps.executeUpdate();

            ps1 = myCon9.prepareStatement("SELECT LAST_INSERT_ID() AS ID");
            rs = ps1.executeQuery();

            while (rs.next()) {
                productId = rs.getInt("ID");
            }

            for (Ingredient ing : product.getRecipeArr()) {
                ps1 = myCon9.prepareStatement("INSERT INTO PRODUCTINGREDIENTTABLE(PRODUCTID,INGREDIENTID,QUANTITY) VALUES(?,?,?)");
                ps1.setInt(1, productId);
                ps1.setInt(2, ing.getIngredientId());
                ps1.setInt(3, ing.getQuantity());
                check = check + ps1.executeUpdate();
            }

            if (check != product.getRecipeArr().size() + 1) {
                myCon9.rollback();

            } else {
                myCon9.commit();
            }

        } catch (SQLException ex) {
            ex.getMessage();//            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStreams();
        }
        return (check == product.getRecipeArr().size() + 1);
    }

    @Override
    public ArrayList<Product> getProducts() {
        ArrayList<Product> listOfProducts = new ArrayList();

        try {
            ps = myCon9.prepareStatement("SELECT PRODUCTID, PRODUCTNAME, PHOTO, PRODUCTDESCRIPTION, PRODUCTWARNINGS, PRICE, DISCOUNT, CATEGORYID  FROM PRODUCTSTABLE WHERE ACTIVITY = 'ACTIVE'");
            rs = ps.executeQuery();

            while (rs.next()) {
                listOfProducts.add(new Product(rs.getInt("PRODUCTID"), rs.getString("PRODUCTNAME"), rs.getString("PHOTO"), rs.getString("PRODUCTDESCRIPTION"), rs.getString("PRODUCTWARNINGS"), rs.getDouble("PRICE"), rs.getInt("DISCOUNT"), rs.getInt("CATEGORYID")));
            }

            for (int i = 0; i < listOfProducts.size(); i++) {
                ps1 = myCon9.prepareStatement("SELECT PRODUCTINGREDIENTTABLE.INGREDIENTID, PRODUCTINGREDIENTTABLE.QUANTITY, INGREDIENTTABLE.INGREDIENTNAME, INGREDIENTTABLE.UNIT FROM PRODUCTINGREDIENTTABLE INNER JOIN INGREDIENTTABLE ON PRODUCTINGREDIENTTABLE.INGREDIENTID = INGREDIENTTABLE.INGREDIENTID WHERE PRODUCTINGREDIENTTABLE.PRODUCTID = ?");
                ps.setInt(1, listOfProducts.get(i).getProductID());
                rs = ps1.executeQuery();

                ArrayList<Ingredient> listOfIngredients = new ArrayList();
                while (rs.next()) {
                    listOfIngredients.add(new Ingredient(rs.getInt("INGREDIENTID"), rs.getString("INGREDIENTNAME"), rs.getInt("QUANTITY"), rs.getString("UNIT")));
//                    listOfIngredients.add(new Ingredient(rs.getInt("INGREDIENTID"), rs.getString("INGREDIENTNAME"), rs.getInt("QUANTITY")));
                }
                listOfProducts.get(i).setRecipeArr(listOfIngredients);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStreams();
        }

        return listOfProducts;
    }

    private synchronized void closeStreams() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ps1 != null) {

            try {
                ps1.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (myCon9 != null) {
            try {
                myCon9.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public Product getProduct(int productId) {
        Product prd = null;
        try {
            ps = myCon9.prepareStatement("SELECT PRODUCTID, PRODUCTNAME, PHOTO, PRODUCTDESCRIPTION, PRODUCTWARNINGS, PRICE, DISCOUNT, CATEGORYID  FROM PRODUCTSTABLE WHERE PRODUCTID = ? AND ACTIVITY = 'ACTIVE'");
            ps.setInt(1, productId);
            rs = ps.executeQuery();

            while (rs.next()) {
                prd = new Product(rs.getInt("PRODUCTID"), rs.getString("PRODUCTNAME"), rs.getString("PHOTO"), rs.getString("PRODUCTDESCRIPTION"), rs.getString("PRODUCTWARNINGS"), rs.getDouble("PRICE"), rs.getInt("DISCOUNT"), rs.getInt("CATEGORYID"));
            }

            ps1 = myCon9.prepareStatement("SELECT PRODUCTINGREDIENTTABLE.INGREDIENTID, PRODUCTINGREDIENTTABLE.QUANTITY, INGREDIENTTABLE.INGREDIENTNAME, INGREDIENTTABLE.UNIT FROM PRODUCTINGREDIENTTABLE INNER JOIN INGREDIENTTABLE ON PRODUCTINGREDIENTTABLE.INGREDIENTID = INGREDIENTTABLE.INGREDIENTID WHERE PRODUCTINGREDIENTTABLE.PRODUCTID = ?");
            ps1.setInt(1, prd.getProductID());
            rs = ps1.executeQuery();

            ArrayList<Ingredient> listOfIngredients = new ArrayList();
            while (rs.next()) {
                listOfIngredients.add(new Ingredient(rs.getInt("INGREDIENTID"), rs.getString("INGREDIENTNAME"), rs.getInt("QUANTITY"), rs.getString("UNIT")));
//                listOfIngredients.add(new Ingredient(rs.getInt("INGREDIENTID"), rs.getString("INGREDIENTNAME"), rs.getInt("QUANTITY")));
            }
            prd.setRecipeArr(listOfIngredients);

        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStreams();
        }
        return prd;
    }

    @Override
    public boolean removeProduct(int productId) {
        int check = 0;
        try {
            ps = myCon9.prepareStatement("UPDATE PRODUCTSTABLE SET ACTIVITY = 'INACTIVE' WHERE PRODUCTID = ?");
            ps.setInt(1, productId);
            check = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStreams();
        }
        return (check == 1);
    }

    @Override
    public boolean updateProduct(Product prod) {
        int check = 0;
        try {
            ps = myCon9.prepareStatement("UPDATE PRODUCTSTABLE SET PRODUCTNAME = ?, PHOTO = ?, PRODUCTDESCRIPTION = ?, PRODUCTWARNINGS = ?, PRICE = ?, DISCOUNT = ?, CATEGORYID = ? WHERE PRODUCTID = ? ");
            ps.setString(1, prod.getProductName());
            ps.setString(2, prod.getPhoto());
            ps.setString(3, prod.getProductDescription());
            ps.setString(4, prod.getProductWarnings());
            ps.setDouble(5, prod.getPrice());
            ps.setInt(6, prod.getDiscount());
            ps.setInt(7, prod.getCategoryID());
            ps.setInt(8, prod.getProductID());
            check = ps.executeUpdate();
            if (check != 1) {
                return false;
            }

            ps = myCon9.prepareStatement("DELETE FROM PRODUCTINGREDIENTTABLE WHERE PRODUCTID = ?");
            ps.setInt(1, prod.getProductID());
            ps.executeUpdate();

            for (int i = 0; i < prod.getRecipeArr().size(); i++) {

                ps = myCon9.prepareStatement("UPDATE PRODUCTINGREDIENTTABLE SET INGREDIENTID = ?, QUANTITY = ? WHERE PRODUCTID = ?");
                ps.setInt(1, prod.getRecipeArr().get(i).getIngredientId());
                ps.setInt(2, prod.getRecipeArr().get(i).getQuantity());
                ps.setInt(3, prod.getProductID());
                check = ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStreams();
        }

        return (check == 1 + prod.getRecipeArr().size());
    }

//    
}
