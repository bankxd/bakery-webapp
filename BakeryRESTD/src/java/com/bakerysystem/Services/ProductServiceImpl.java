/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Services;

import com.bakerysystem.Daos.ProductDao;
import com.bakerysystem.Daos.ProductDaoImpl;
import com.bakerysystem.Model.Product;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author keoagile
 */
public class ProductServiceImpl implements ProductService {

    private final String SUCCESSFUL = "SUCCESSFUL";
    private final String FAILED = "FAILED";

    ProductDao pdl;

    public ProductServiceImpl() {
        pdl = new ProductDaoImpl();
    }

    public static void main(String[] arg) {
//        ArrayList<Product> arr = new ProductServiceImpl().getAllProducts();
//        for (Product s : arr) {
//            System.out.println(s.getProductName());
//        }
//        System.out.println(new ProductServiceImpl().getProduct(4).getProductName());

        String s = new ProductServiceImpl().serverSend(new File("./web/Images/pretz.jpg"));
        System.out.println(s);
    }

    @Override
    public ArrayList<Product> getProductsByCategory(int categoryId) {
        return null;//pdl.getProductByCategory(categoryId);
    }

    public String createNewProduct(Product prod) {
        prod.setPhoto(serverStore(prod.getPhoto(), prod.getProductName()));
        if (new ProductDaoImpl().addProduct(prod) == true) {
            return SUCCESSFUL;
        }
        return FAILED;
    }

    @Override
    public ArrayList<Product> getAllProducts() {

        ArrayList<Product> prods = pdl.getProducts();

        return prods;
    }

    @Override
    public Product getProduct(int productId) {
        Product prod = null;

        prod = pdl.getProduct(productId);

//        File prodsImage = new File(prod.getPhoto());
//        prod.setPhoto(serverSend(prodsImage));
//        
        return prod;
    }

    public String getImage(int prodID) {
        Product prod = new ProductDaoImpl().getProduct(prodID);
        System.out.println(prod.getPhoto());
        String filename = "C:\\Users\\David\\Documents\\NetBeansProjects\\Bakery2\\BakerySystemRest\\web\\Images\\" + prod.getPhoto();
        File prodsImage = new File(filename);
        String encode = serverSend(prodsImage);
        System.out.println(encode + "\n");

        return encode;
    }

    public String serverStore(String contents, String productName) {

        byte[] buf = Base64.getDecoder().decode(contents);//contents.getBytes();//StandardCharsets.UTF_8);
        File file2Save = null;
        String filename = "C:\\Users\\David\\Documents\\NetBeansProjects\\Bakery2\\BakerySystemRest\\web\\Images\\" + productName + ".jpg";

        try {
            file2Save = new File(filename);

            FileUtils.writeByteArrayToFile(file2Save, buf);
        } catch (IOException e) {
            System.out.println("COULDN'T REBUILD IMGAE");
        }
        
        return productName + ".jpg";
    }

    public String serverSend(File file) {
        Encoder encoder = Base64.getEncoder();
        byte[] buf = null;
        try {
            buf = FileUtils.readFileToByteArray(file);//Files.readAllBytes(file.toPath());
        } catch (NoSuchFileException x) {
//            try {
//                System.err.println(x.getMessage());
//                file = new File("./web/Images/default.jpg");
//                buf = FileUtils.readFileToByteArray(file);//Files.readAllBytes(file.toPath());
//
//            } catch (IOException ex) {
//                Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//                System.out.println("CANT FIND IT");
//            }
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, x);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }

        String encoded = encoder.encodeToString(buf);
        System.out.println(encoded);

        return encoded;
    }

    @Override
    public boolean removeProduct(int productId) {
        return pdl.removeProduct(productId);
    }

}
