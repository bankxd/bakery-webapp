package com.bakerysystem.Daos;

import com.bakerysystem.Model.User;
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
public class UserDaoImpl implements UserDao {

    private Connection myCon8;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserDaoImpl() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load JDBC/ODBC driver." + e.toString());
            e.printStackTrace();
        }

        String URL = "jdbc:mysql://localhost:3306/cakeshop";
//              String URL = "jdbc:mysql://localhost:3306/cakeshop";
        try {
            myCon8 = DriverManager.getConnection(URL, "root", "root");
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public User getUser(String email, String password) {

        try {
            ps = myCon8.prepareStatement("SELECT USERID, EMAIL, PASSWORD, USERTYPE FROM USERSTABLE WHERE EMAIL = ? AND PASSWORD = ? AND ACTIVITY = 'ACTIVE'");
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new User(rs.getInt("USERID"), rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("USERTYPE"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean removeUser(int userID) {
        int check = 0;

        try {
            ps = myCon8.prepareStatement("UPDATE USERSTABLE SET ACTIVITY = 'INACTIVE' WHERE USERID = ?");
            ps.setInt(1, userID);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check == 1);
    }

    @Override
    public boolean updateUser(User user) {
        int check = 0;

        try {
            ps = myCon8.prepareStatement("UPDATE USERSTABLE SET EMAIL = ?, PASSWORD = ?, USERTYPE = ? WHERE USERID = ?");
            // ps.setString(1, user.getUserName());
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserType());
            ps.setInt(4, user.getUserId());
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check == 1);
    }

    @Override
    public boolean addUser(User user) {
        int check = 0;

        try {
            ps = myCon8.prepareStatement("INSERT INTO USERSTABLE(USERID,EMAIL,PASSWORD,USERTYPE,ACTIVITY) VALUES(NULL,?,?,?,'ACTIVE')");
            //ps.setString(1, user.getUserName());
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserType());
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check == 1);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> listOfUsers = new ArrayList();

        try {
            ps = myCon8.prepareStatement("SELECT USERID, EMAIL, PASSWORD, USERTYPE FROM USERSTABLE WHERE ACTIVITY = 'ACTIVE'");
            rs = ps.executeQuery();

            while (rs.next()) {
                listOfUsers.add(new User(rs.getInt("USERID"), rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("USERTYPE")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfUsers;
    }

    @Override
    public boolean updatePassword(String email) { 
        int check =0;
        try {
        
        ps = myCon8.prepareStatement("UPDATE USERSTABLE SET PASSWORD = ? WHERE EMAIL = ?");
        ps.setString(1, "158799");
        ps.setString(2, email);
        check = ps.executeUpdate();
        
    } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (check == 1);
    }

}
