package com.bakerysystem.RestAPI;

//import com.bakerysystem.Daos.UserDaoImpl;
import com.bakerysystem.Daos.CustomerDaoImpl;
import com.bakerysystem.Model.Customer;
import com.bakerysystem.Services.CustomerServiceImpl;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Themba
 */

@Path("/user-content")
public class AccountsAPI {
    
    private final String SUCCESSFUL = "SUCCEEDED";
    private final String FAILED = "UNSUCCESSFUL";
    
    @GET
    @Path("/login/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer signingInUser(@PathParam("username") String username, @PathParam("password") String password){
//        Customer user = new Customer(321, "firstname", "lastname", "email", "tel-home", "mobile-no", "identityNo", 321, "password"); // new CustomerServiceImpl().login(username,password);
       return new CustomerServiceImpl().login(username, password);
    }
    
 
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Customer registerInUser(Customer regU){
        
//        if(new CustomerServiceImpl().registerCustomer(regU)!=null ){
//            return Response.status(Response.Status.OK).entity(regU).build();
//        }
//        
       return new CustomerServiceImpl().registerCustomer(regU);
    }
 
   
    @GET
    @Path("/recover/{email}")
    public String recoverAccount(@PathParam("email") String customerEmail){
        // need db handling changes
        String response = (new CustomerServiceImpl().confirmEmail(customerEmail)) == true ? SUCCESSFUL : FAILED;
        
        return response;
        //return "ATTEMPTED TO RECOVER";
    }
    
    @POST
    @Path("/remove/{id}")
    public String removeUser(@PathParam("id") int userID){   //***************
         String result = (new CustomerServiceImpl().deleteCustomer(userID)) == true ? SUCCESSFUL : FAILED;
        
        return result;
    }
    
    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public String editUserDetails(Customer cus){ 
        // temp Change to start at service
        String response = (new CustomerDaoImpl().updateCustomer(cus)) == true ? SUCCESSFUL : FAILED;
         
        return response;
    }
    
    @GET
    @Path("/collection")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Customer> retrieveAllUsers(){
        return new CustomerDaoImpl().getAllCustomers(); 
    }
    
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer retrieveUser(@PathParam("id") int userid){
        return new CustomerDaoImpl().getCustomer(userid); 
    }    
}
    
    
 
  