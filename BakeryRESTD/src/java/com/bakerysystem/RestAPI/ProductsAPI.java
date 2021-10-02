package com.bakerysystem.RestAPI;

import com.bakerysystem.Daos.ProductDaoImpl;
import com.bakerysystem.Model.*;
import com.bakerysystem.Services.ProductServiceImpl;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Themba
 */

@Path("/product-content")
public class ProductsAPI {

    private final String SUCCESSFUL = "SUCCESSFUL";
    private final String FAILED = "FAILED";
    
    @GET
    @Path("/image/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getImage(@PathParam("id") int prodID){
        return new ProductServiceImpl().getImage(prodID);
    }
    
    @GET
    @Path("/collection")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Product> getProducts(){
        return  new ProductServiceImpl().getAllProducts();
    }
    
    @GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getAProduct(@PathParam("id") int productid ){
        return new ProductServiceImpl().getProduct(productid) ;
    }
    
   
    
    @POST
    @Path("/remove/{id}")
    public String removeProduct(@PathParam("id") int productid){
        if(new ProductServiceImpl().removeProduct(productid) == true){
            return SUCCESSFUL;
        }
        return FAILED;
    }
    
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public String editProduct(Product prod){
        if(new ProductDaoImpl().updateProduct(prod)){
            return SUCCESSFUL;
        }
        return FAILED;
    }
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addProduct(Product prod){ //Query @FormParam("")){
      return new ProductServiceImpl().createNewProduct(prod);
    }
}
