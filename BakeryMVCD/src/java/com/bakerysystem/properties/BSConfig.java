/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.properties;

/**
 * This is a configure for our bakery system. Used to alter properties
 *
 * @author Themba
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.util.Properties;

public class BSConfig {

//    private String filename = "./data/config.properties";
//    private Properties property;
//    private ObjectInputStream reader;
//    private ObjectOutputStream writer;

    private final String HOST = "localhost";
    private String PORT = "8081";
    private String dbhost = "localhost";

    // USE THIS IF YOU DONT NEED TO CHANGE THE EXISTING PROPERTIES IN THE CONFIG FILE
//    public BSConfig() {
//        try {
//            property = new Properties();
//            read();
//        } catch (FileNotFoundException ex) {
//            System.err.println(ex.getMessage());
//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
//
//    // USE THIS IF YOU WISH TO CHANGE THE EXISTING PROPERTIES IN THE CONFIG FILE
//    public BSConfig(String host, int port, String databaseHost) {
//        try {
//            property = new Properties();
//
//            writer = new ObjectOutputStream(new FileOutputStream(filename));
//            property.setProperty("port", port + "");
//            property.setProperty("DatabaseHostAddress", databaseHost);
//            property.setProperty("APIHostAddress", host);
//            property.store(writer, null);
//
//            read();
//        } catch (FileNotFoundException ex) {
//            System.err.println(ex.getMessage());
//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
    public String getHost() {
        return HOST;
    }
    public String getPort() {
        return PORT;
    }
    public String getDbhost() {
        return dbhost;
    }
    public String getURL(String pathParent) {
        return "http://" + getHost() + ":" + getPort() + "/BakerySystemRest/app/" + pathParent + "/";
    }

    @Override
    public String toString() {
        return "BSConfig{" + "HOST=" + HOST + ", PORT=" + PORT + ", dbhost=" + dbhost + '}';
    }
    


    public static void main(String[] args) {
        // Run to change configuration on the file
        System.out.println(new BSConfig().getURL("product-content"));
    }
}