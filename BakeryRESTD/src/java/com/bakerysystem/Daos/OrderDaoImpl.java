/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Daos;

import com.bakerysystem.Model.Order;
import com.bakerysystem.Model.ProductLineItem;
import com.bakerysystem.databaseAccess.DBManager;
import java.sql.Connection;
import java.sql.Date;
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
public class OrderDaoImpl implements OrderDao{

    private PreparedStatement ps1;
    private PreparedStatement ps;
    private Connection con1;
    private ResultSet rs;

    public OrderDaoImpl() {
//        try {
//            con1 = DBManager.getConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load JDBC/ODBC driver." + e.toString());
            e.printStackTrace();
        }

        String URL = "jdbc:mysql://localhost:3306/cakeshop";
        try {
            con1 = DriverManager.getConnection(URL, "root", "root");
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    @Override
    public boolean addOrder(Order order) {
        int check = 0;
        int id = 0;
        
        try {
            con1.setAutoCommit(false);
            ps = con1.prepareStatement("INSERT INTO ORDERSTABLE(ORDERID, CUSTOMERID, TOTALAMOUNT, ORDERSTATUS, DELIVERADDRESSID, ORDERDATE, PAYMENTSTATUS) VALUES(NULL,?,?,'PREPARING',?,CURDATE(), ?)");
            ps.setInt(1, order.getCustomerId());
            ps.setDouble(2, order.getTotalPrice());
            ps.setString(3, order.getDeliveryAddressId());
            ps.setString(4, order.getPaymentStatus());
            check = ps.executeUpdate();
        
            ps1 = con1.prepareStatement("SELECT LAST_INSERT_ID() AS ID");
            rs = ps1.executeQuery();
            
            if(rs.next()){
                id = rs.getInt("ID");
            }
            
            for(ProductLineItem pll: order.getOrderLineArr()){
                ps1 = con1.prepareStatement("INSERT INTO ORDERDETAILSTABLE(ORDERDETAILSID, ORDERID, PRODUCTID, QUANTITY, PRODUCTPRICE) VALUES(NULL,?,?,?,?)");
                ps1.setInt(1, id);
                ps1.setInt(2, pll.getProductID());
                ps1.setInt(3, pll.getQuantity());
                ps1.setDouble(4, pll.getProductPrice());
                check = check + ps1.executeUpdate();
            }
            
            if(check != order.getOrderLineArr().size() + 1){
                con1.rollback();
                return false;
            }
            
            con1.commit();
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeStreams();
        }
        return (check == order.getOrderLineArr().size() + 1);
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

        if (con1!= null) {
            try {
                con1.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public Order getOrder(int orderId) {
        Order order = null;
        try {
            ps = con1.prepareStatement("SELECT CUSTOMERID, TOTALAMOUNT, ORDERSTATUS, DELIVERADDRESSID, ORDERDATE, PAYMENTSTATUS FROM ORDERSTABLE WHERE ORDERID = ? ");
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            if(rs.next()){
                order = new Order(orderId, rs.getInt("CUSTOMERID"), rs.getDouble("TOTALAMOUNT"), rs.getString("ORDERSTATUS"), rs.getString("DELIVERADDRESSID"), rs.getDate("ORDERDATE"), rs.getString("PAYMENTSTATUS"));
            }
            
            ps1 = con1.prepareStatement("SELECT ORDERDETAILSTABLE.PRODUCTID, ORDERDETAILSTABLE.QUANTITY, ORDERDETAILSTABLE.PRODUCTPRICE, PRODUCTSTABLE.PRODUCTNAME FROM ORDERDETAILSTABLE INNER JOIN PRODUCTSTABLE ON ORDERDETAILSTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID WHERE ORDERDETAILSTABLE.ORDERID = ? ");
            ps1.setInt(1, orderId);
            rs = ps1.executeQuery();
            order.setOrderLineArr(new ArrayList<>());
            while(rs.next()){
                order.getOrderLineArr().add(new ProductLineItem(rs.getInt("PRODUCTID"), rs.getString("PRODUCTNAME"), rs.getInt("QUANTITY"), rs.getDouble("PRODUCTPRICE")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeStreams(); 
        }
        
        return order;
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList();
         try {
            ps = con1.prepareStatement("SELECT ORDERID, CUSTOMERID, TOTALAMOUNT, ORDERSTATUS, DELIVERADDRESSID, ORDERDATE, PAYMENTSTATUS FROM ORDERSTABLE ");
            rs = ps.executeQuery();
            while(rs.next()){
                orders.add(new Order(rs.getInt("ORDERID"), rs.getInt("CUSTOMERID"), rs.getDouble("TOTALAMOUNT"), rs.getString("ORDERSTATUS"), rs.getString("DELIVERADDRESSID"), rs.getDate("ORDERDATE"), rs.getString("PAYMENTSTATUS")));
            }
                for(Order ord: orders){   
                ps = con1.prepareStatement("SELECT ORDERDETAILSTABLE.PRODUCTID, ORDERDETAILSTABLE.QUANTITY, ORDERDETAILSTABLE.PRODUCTPRICE, PRODUCTSTABLE.PRODUCTNAME FROM ORDERDETAILSTABLE INNER JOIN PRODUCTSTABLE ON ORDERDETAILSTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID WHERE ORDERDETAILSTABLE.ORDERID = ? ");
                ps.setInt(1, ord.getOrderID());
                rs = ps.executeQuery();
             ord.setOrderLineArr(new ArrayList<>());
             while(rs.next()){
                    ord.getOrderLineArr().add(new ProductLineItem(rs.getInt("PRODUCTID"), rs.getString("PRODUCTNAME"), rs.getInt("QUANTITY"), rs.getDouble("PRODUCTPRICE")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeStreams(); 
        }
        return orders;
    }

    @Override
    public boolean updateOrderStatus(Order ord) {
        int check = 0;
        try {
            ps = con1.prepareStatement("UPDATE ORDERSTABLE SET ORDERSTATUS = ? WHERE ORDERID = ?");
            ps.setString(1, ord.getOrderStatus());
            ps.setInt(2, ord.getOrderID());
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeStreams();
        }
        return (check == 1);
    }

    @Override
    public boolean removeOrder(int orderId) {
         int check = 0;
        try {
            ps = con1.prepareStatement("UPDATE ORDERSTABLE SET ORDERSTATUS = 'CANCELLED' WHERE ORDERID = ?");
            ps.setInt(1, orderId);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeStreams();
        }
        return (check == 1);
    }

    @Override
    public ArrayList<Order> getOrdersByCustomer(int customerId) {
         ArrayList<Order> orders = new ArrayList();
         try {
            ps = con1.prepareStatement("SELECT ORDERID, CUSTOMERID, TOTALAMOUNT, ORDERSTATUS, DELIVERADDRESSID, ORDERDATE, PAYMENTSTATUS FROM ORDERSTABLE WHERE CUSTOMERID = ?");
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            while(rs.next()){
                orders.add(new Order(rs.getInt("ORDERID"), rs.getInt("CUSTOMERID"), rs.getDouble("TOTALAMOUNT"), rs.getString("ORDERSTATUS"), rs.getString("DELIVERADDRESSID"), rs.getDate("ORDERDATE"), rs.getString("PAYMENTSTATUS")));
                for(Order ord: orders){   
                ps = con1.prepareStatement("SELECT ORDERDETAILSTABLE.PRODUCTID, ORDERDETAILSTABLE.QUANTITY, ORDERDETAILSTABLE.PRODUCTPRICE, PRODUCTSTABLE.PRODUCTNAME FROM ORDERDETAILSTABLE INNER JOIN PRODUCTSTABLE ON ORDERDETAILSTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID WHERE ORDERID = ? ");
                ps.setInt(1, ord.getOrderID());
                rs = ps.executeQuery();
             ArrayList<ProductLineItem> list = new ArrayList();
             while(rs.next()){
                    list.add(new ProductLineItem(rs.getInt("PRODUCTID"), rs.getString("PRODUCTNAME"), rs.getInt("QUANTITY"), rs.getDouble("PRODUCTPRICE")));
                }
             ord.setOrderLineArr(list);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeStreams(); 
        }
        return orders;
    }
   
    @Override
    public ArrayList<Order> getOrdersByStatus(String status) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            ps = con1.prepareStatement("SELECT ORDERID, CUSTOMERID, TOTALAMOUNT, ORDERSTATUS, DELIVERADDRESSID, ORDERDATE, PAYMENTSTATUS FROM ORDERSTABLE WHERE ORDERSTATUS = ?");
            ps.setString(1, status);
            rs = ps.executeQuery();
            while(rs.next()){
                orders.add(new Order(rs.getInt("ORDERID"), rs.getInt("CUSTOMERID"), rs.getDouble("TOTALAMOUNT"), rs.getString("ORDERSTATUS"), rs.getString("DELIVERADDRESSID"), rs.getDate("ORDERDATE"), rs.getString("PAYMENTSTATUS")));
            }
                for(Order ord: orders){   
                ps = con1.prepareStatement("SELECT ORDERDETAILSTABLE.PRODUCTID, ORDERDETAILSTABLE.QUANTITY, ORDERDETAILSTABLE.PRODUCTPRICE, PRODUCTSTABLE.PRODUCTNAME FROM ORDERDETAILSTABLE INNER JOIN PRODUCTSTABLE ON ORDERDETAILSTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID WHERE ORDERDETAILSTABLE.ORDERID = ? ");
                ps.setInt(1, ord.getOrderID());
                rs = ps.executeQuery();
             ord.setOrderLineArr(new ArrayList<>());
             while(rs.next()){
                    ord.getOrderLineArr().add(new ProductLineItem(rs.getInt("PRODUCTID"), rs.getString("PRODUCTNAME"), rs.getInt("QUANTITY"), rs.getDouble("PRODUCTPRICE")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeStreams(); 
        }
        return orders;
    }
}