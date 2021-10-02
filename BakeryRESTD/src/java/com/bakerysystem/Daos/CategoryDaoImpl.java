/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Daos;

import com.bakerysystem.Model.Category;
import com.bakerysystem.properties.BSConfig;
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
public class CategoryDaoImpl implements CategoryDao {

    private Connection myCon3;
    private PreparedStatement ps;
    private ResultSet rs;

    public CategoryDaoImpl() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load JDBC/ODBC driver." + e.toString());
            e.printStackTrace();
        }

        String URL = "jdbc:mysql://localhost:3306/cakeshop";
        try {
            myCon3 = DriverManager.getConnection(URL, "root", "root");
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    @Override
    public boolean addCategory(Category cat) {
        int check = 0;

        try {
            ps = myCon3.prepareStatement("INSERT INTO CATEGORYTABLE(CATEGORYID,CATEGORYNAME,ACTIVITY) VALUES(?,?,'ACTIVE')");
            ps.setInt(1, cat.getCategoryID());
            ps.setString(2, cat.getCategoryName());
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check == 1);
    }

    @Override
    public boolean removeCategory(int categoryID) {
        int check = 0;

        try {
            ps = myCon3.prepareStatement("UPDATE CATEGORYTABLE SET ACTIVITY = 'INACTIVE' WHERE CATEGORYID = ?");
            ps.setInt(1, categoryID);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check == 1);
    }

    @Override
    public Category getCategory(int categoeyID) {

        try {
            ps = myCon3.prepareStatement("SELECT CATEGORYID, CATEGORYNAME FROM CATEGORYTABLE WHERE CATEGORYID = ?");
            ps.setInt(1, categoeyID);
            rs = ps.executeQuery();

            while (rs.next()) {

                return new Category(rs.getInt("CATEGORYID"), rs.getString("CATEGORYNAME"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList();

        try {
            ps = myCon3.prepareStatement("SELECT CATEGORYID, CATEGORYNAME FROM CATEGORYTABLE WHERE ACTIVITY = 'ACTIVE' ");
            rs = ps.executeQuery();

            while (rs.next()) {

                categories.add(new Category(rs.getInt("CATEGORYID"), rs.getString("CATEGORYNAME")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    @Override
    public boolean updateCategory(Category cat) {
        int check = 0;

        try {
            ps = myCon3.prepareStatement("UPDATE CATEGORYTABLE SET CATEGORYNAME = ? WHERE CATEGORYID = ?");
            ps.setString(1, cat.getCategoryName());
            ps.setInt(2, cat.getCategoryID());
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check == 1);
    }
}
