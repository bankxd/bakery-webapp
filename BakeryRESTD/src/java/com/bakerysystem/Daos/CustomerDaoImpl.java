package com.bakerysystem.Daos;

import com.bakerysystem.Model.Customer;
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
public class CustomerDaoImpl implements CustomerDao {

    private Connection myCon30;
    private PreparedStatement ps;
    private ResultSet rs;

    public CustomerDaoImpl() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load JDBC/ODBC driver." + e.toString());
            e.printStackTrace();
        }

        String URL = "jdbc:mysql://localhost:3306/cakeshop";
        try {
            myCon30 = DriverManager.getConnection(URL, "root", "root");
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    @Override
    public boolean addCustomer(Customer cust) {
        int check = 0;

        try {
            ps = myCon30.prepareStatement("INSERT INTO CUSTOMERTABLE(CUSTOMERID,FIRSTNAME,LASTNAME,EMAIL,TELEPHONEHOME,TELEPHONEMOBILE,ID,ADDRESSID,PASSWORD,ACTIVITY) VALUES(null,?,?,?,?,?,?,?,?,'ACTIVE')");
            ps.setString(1, cust.getFirstName());
            ps.setString(2, cust.getLastName());
            ps.setString(3, cust.getEmail());
            ps.setString(4, cust.getTelephonehome());
            ps.setString(5, cust.getTelephonemobile());
            ps.setString(6, cust.getID());
            ps.setString(7, cust.getAddressId());
            ps.setString(8, cust.getPassword());
            check = ps.executeUpdate();
//            ps = myCon30.prepareStatement("SELECT_LAST_INDEX AS ID");
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check == 1);
    }

    @Override
    public Customer getCustomer(int userId) {

        try {
            ps = myCon30.prepareStatement("SELECT CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL, TELEPHONEHOME, TELEPHONEMOBILE, ID, ADDRESSID, PASSWORD "
                    + "FROM CUSTOMERTABLE "
                    + "WHERE CUSTOMERID = ? AND ACTIVITY = 'ACTIVE'");
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Customer(rs.getInt("CUSTOMERID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("EMAIL"), rs.getString("TELEPHONEHOME"), rs.getString("TELEPHONEMOBILE"), rs.getString("ID"), rs.getString("ADDRESSID"), rs.getString("PASSWORD"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean updateCustomer(Customer cust) {
        int check = 0;

        try {
            ps = myCon30.prepareStatement("UPDATE CUSTOMERTABLE SET FIRSTNAME = ?, LASTNAME = ?, EMAIL = ?, TELEPHONEHOME = ? , TELEPHONEMOBILE = ?, ID = ?, ADDRESSID = ?, PASSWORD = ? WHERE CUSTOMERID = ?");
            ps.setString(1, cust.getFirstName());
            ps.setString(2, cust.getLastName());
            ps.setString(3, cust.getEmail());
            ps.setString(4, cust.getTelephonehome());
            ps.setString(5, cust.getTelephonemobile());
            ps.setString(6, cust.getID());
            ps.setString(7, cust.getAddressId());
            ps.setString(8, cust.getPassword());
            ps.setInt(9, cust.getCustomerId());
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check == 1);
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> listofcustomers = new ArrayList();

        try {
            ps = myCon30.prepareStatement("SELECT CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL, TELEPHONEHOME, TELEPHONEMOBILE, ID, ADDRESSID, PASSWORD FROM CUSTOMERTABLE");
            rs = ps.executeQuery();

            while (rs.next()) {

                listofcustomers.add(new Customer(rs.getInt("CUSTOMERID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("EMAIL"), rs.getString("TELEPHONEHOME"), rs.getString("TELEPHONEMOBILE"), rs.getString("ID"), rs.getString("ADDRESSID"), rs.getString("PASSWORD")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listofcustomers;
    }

    @Override
    public boolean removeCustomer(int customerId) {
        int check = 0;

        try {
            ps = myCon30.prepareStatement("UPDATE CUSTOMERTABLE SET ACTIVITY = 'INACTIVE' WHERE CUSTOMERID = ?");
            ps.setInt(1, customerId);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check == 1);
    }

    @Override
    public String getCustomerEmail(String email) {

        try {
            ps = myCon30.prepareStatement("SELECT EMAIL FROM CUSTOMERTABLE WHERE EMAIL = ? AND ACTIVITY = 'ACTIVE'");
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString("EMAIL");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String generateOtp(String email) {

        String num = "12335";

        try {
            ps = myCon30.prepareStatement("UPDATE CUSTOMERTABLE SET PASSWORD = ? WHERE");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(new CustomerDaoImpl().addCustomer(new Customer("gg", "yeah", "kep@gmail.com", " ", "0125585698", "0000000056348", "kwathema", "1234")));
    }
}
