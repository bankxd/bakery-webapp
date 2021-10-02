/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.client;

import com.bakerysystem.Model.Customer;
import com.bakerysystem.extraz.Helper;
import com.bakerysystem.properties.BSConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Themba
 */
public class AccountsClient {
String url;
    private DefaultClient dc;
    public AccountsClient() {
        url = new BSConfig().getURL("user-content");
        dc = new DefaultClient();
    }

     public static void main(String[] args) {
         
//         Customer c = new AccountsClient().getAccount(17);
//          for(Customer c : new AccountsClient().getAllAccounts()){
//              System.out.println(c.getFirstName());
//          }
//        String s = new AccountsClient().updateDetails(new Customer(19, "Frickie", "De Boer", "proper-email", "0111211121", "0111211211", "11111111", "0", "password")); // 
//        String s = new AccountsClient().remove(19); // 
//        System.out.println(s);
//        Customer u = new Customer("Sphiwe", "Ntusi", "sphiwe.n@gmail.com", "tel-home", "mobile-no", "identityNo", "0", "password");//new AccountsClient().register(new Customer("Sphiwe", "Ntusi", "sphiwe.n@gmail.com", "tel-home", "mobile-no", "identityNo", "0", "password"));
        Customer u = new AccountsClient().login("email0@gmail", "password");
        System.out.println(u.getFirstName());
        if(u == null){
            System.out.println("Unregistered User!");
        }else{
            System.out.println(u.getFirstName());
        }

    }
    
    public Customer login(String username, String password) {
        Customer user = null;
        try {
            Client client = ClientBuilder.newClient();
            //            Map map = new HashMap();
//            map.put("username", username);
//            map.put("password", password);
//            WebTarget webTarget = client.target(URL + "login/{username}/{password}").resolveTemplate(map.keySet(),map.values());
//            //"http://10.7.7.109:8088/BakerySystemRest/app/"
            WebTarget webTarget = client.target(url + "login/" + username + "/" + password);
            System.out.println("Fetching User...");
            String s = webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class);
            ObjectMapper ob = new ObjectMapper();
            user = (Customer) ob.readValue(s, Customer.class);
            System.out.println("Login successfull");
        } catch (IOException ex) {
            Logger.getLogger(AccountsClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public String recoverAccount(String providedEmail) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url +"recover/{email}").resolveTemplate("email", providedEmail);
        String r = target.request().accept("application/json").get(String.class);

        //EXPECT "Link sent if user account exists"
        return r;
    }

    public Customer register(Customer newUser) {
        Response generatedResponse = null;
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(url + "register");

            Response r = target.request().post(Entity.json(Helper.convert2Json(newUser)));
            
            ObjectMapper ob = new ObjectMapper();
            String s = generatedResponse.readEntity(String.class);

            Customer u = (Customer) ob.readValue(s, Customer.class);
            
            return u;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String remove(int userId) {
        return dc.remove(userId, url + "remove/{id}");
    }

    public String updateDetails(Customer cus) {
        return dc.update(cus, url + "edit");
    }

    public ArrayList<Customer> getAllAccounts() {
        ArrayList<Customer> catalogue = null;
        Customer[] users = null;
        try {
            
            ObjectMapper ob = new ObjectMapper();
            users = ob.readValue(dc.getAll(url + "collection"), Customer[].class);

            catalogue = new ArrayList(Arrays.asList(users));

        } catch (Exception ex) {
            System.out.println("ERROR: Couldn't get users");
        }

        return catalogue;
    }
    
    //10.7.7.105

    public Customer getAccount(int userid) {
        Customer cus = null;
        try {
            ObjectMapper ob = new ObjectMapper();
            cus = (Customer) ob.readValue(dc.get(userid, url + "user/{id}"), Customer.class);
            
        } catch (IOException ex) {
            Logger.getLogger(AccountsClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cus;
    }
}
