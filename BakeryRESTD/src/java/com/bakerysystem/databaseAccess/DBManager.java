/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.databaseAccess;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
//import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author keoagile
 */
public class DBManager {
    private static DataSource dataSource;
    
    static{
        try{
        
            Context initContext= new InitialContext();
            Context envContext=(Context)initContext.lookup("java:comp/env");
            dataSource=(DataSource)envContext.lookup("jdbc/tagdb");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}
