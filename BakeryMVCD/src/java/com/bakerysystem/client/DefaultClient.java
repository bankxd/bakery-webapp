/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.client;

import com.bakerysystem.Model.Customer;
import com.bakerysystem.Model.Product;
import com.bakerysystem.extraz.Helper;
import com.bakerysystem.properties.BSConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Default Client [THE DOER OF ALL THINGS CRUD] - Don't ask why I created this
 * guy [SECRET: Probably got lazy again]
 *
 * @author Themba
 */
public class DefaultClient<T> {

//    private String URL;

    public DefaultClient() {
//        URL = new BSConfig().getURL(parentPathOfFunctions);
//        System.out.println(URL);
    }

    public String create(T obj, String methodPath) {
        String responseRes;

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(methodPath);
        responseRes = target.request().post(Entity.json(Helper.convert2Json(obj))).readEntity(String.class);

        return responseRes;
    }

    //   Create : CHECKED | Retrieve : _all__|_1_ : CHECKED | Update : CHECKED | Delete : CHECKED        
    public static void main(String[] args) {
        
            DefaultClient dc = new DefaultClient();
            String response = dc.get(5, "product/{id}").toString();//.getAll("accounts").toString();//.get(17, "user/{id}").toString();//.create(new Customer("Themba", "Ndwandwe", "themba.ndwandwe@yahoo.com", "NA", "0823527###", "#############", 101, "password"), "register"); //.remove(14, "remove/{id}");//.update(new Customer(8, "first", "last", "email@something.com", "tel-home", "mobile-no", "identityNo", 0, "password"), "editdetails");
            System.out.println(response);
        
    }

//        DefaultClient dc = new DefaultClient<>("users");
//        
    public String getAll(String methodPath) {
        String s="";
        ArrayList<Product> products = null;
        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target(methodPath);

            System.out.println("[GET ALL] Fetch...");
            
            s = webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class);
            

            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return s;
    }

    public String get(int objID, String methodPath) {
        String s="";
        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target( methodPath).resolveTemplate("id", objID);

            System.out.println("[GET] Fetch...");
            s = webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class);

            

        } catch (Exception ex) {
            System.err.println("ERROR: Couldnt retrieve resource");
        }

        return s;
    }
    
     public String getString(int objID, String methodPath) {
        String s="";
        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target( methodPath).resolveTemplate("id", objID);

            System.out.println("[GET] Fetch...");
            s = webTarget.request().accept(MediaType.TEXT_PLAIN).get(String.class);

            System.out.println(s);

        } catch (Exception ex) {
            System.err.println("ERROR: Couldnt retrieve string");
        }

        return s;
    }

    public String update(T cus, String methodPath) {
        Response generatedResponse = null;
        String s = "";
        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target(  methodPath);

            generatedResponse = webTarget.request().put(Entity.json(Helper.convert2Json(cus)));
            s = generatedResponse.readEntity(String.class);

            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public String remove(int objId, String methodPath) {
        //Response generatedResponse = null;
        String s = "";
        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target( methodPath).resolveTemplate("id", objId);

            s = webTarget.request().accept("application/json").post(Entity.json(Helper.convert2Json(objId))).readEntity(String.class);

            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}
