/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Services;

import com.bakerysystem.Model.Product;
import com.bakerysystem.client.ProductsClient;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Themba
 */
public class ProductsClientService {
    
    public void loadProductsWithImages(){
        for (Product p : new ProductsClient().recieveProducts()) {
            clientStore(new ProductsClient().getImage(p.getProductID()),p.getPhoto());
        }
    }
    
    public void clientStore(String contents, String productName) {
        byte[] buf = null;
        File file2Save = null;

        try {
            buf = Base64.getDecoder().decode(contents);//contents.getBytes();//StandardCharsets.UTF_8);
            String filename = "./Images/" + productName ;
            file2Save = new File(filename);
            FileUtils.writeByteArrayToFile(file2Save, buf);
            
        } catch (IOException e) {
            System.out.println("COULDN'T REBUILD IMAGE");
        }
    }
    
    public String addProduct(Product prod, File file){
        prod.setPhoto(clientSend(file));
        System.out.println(prod);
        return new ProductsClient().addProduct(prod);
    }

//    public static void main(String [] args){
//        File f = new File("C:\\Users\\Themba\\Desktop\\Bean.jpg");
//        Product p = new Product("Bean", "Bean.jpg", "Description", "Product Warning", 4231.0, 0, 01);
//        System.out.println(p);
//        String s = new ProductsClientService().addProduct(p,f);
//    }
    
    public String clientSend(File file) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] buf = null;
        try {
            buf = FileUtils.readFileToByteArray(file);//Files.readAllBytes(file.toPath());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String encoded = encoder.encodeToString(buf);
        System.out.println(encoded);

        return encoded;
    }
}
