package com.bakerysystem.tools;

import com.bakerysystem.Model.Product;
import com.bakerysystem.Services.ProductsClientService;
import com.bakerysystem.client.ProductsClient;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author David
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Servlet Context Initialized");
//        ServletContext sc = sce.getServletContext();

//        ArrayList<Customer> custArr = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            custArr.add(new Customer(0, "test", "test", "test", "test", "test", "test", "test", "test"));
//        }
//
//        ArrayList<Ingredient> ingArr = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            ingArr.add(new Ingredient("squash", 1));
//        }
//        ArrayList<Category> catArr = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            catArr.add(new Category(1, "test"));
//        }
////
//        ArrayList<Product> prodArr = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            prodArr.add(new Product("test", "test", "test", "test", 1.1, 1, 1));
//        }
////
//        ArrayList<Order> ordArr = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            ordArr.add(new Order(1, 1, "test", "test", "test"));
//        }


//        ArrayList<Product> prodArr = (ArrayList<Product>) new ProductsClient().recieveProducts();
//        for (Product product : prodArr) {
//            new ProductsClientService().clientStore(new ProductsClient().getImage(product.getProductID()), product.getProductName());
//        }
//        sc.setAttribute("ProdArr", prodArr);

//        ArrayList<Category> catArr = (ArrayList<Category>) new CategoriesClient().getCategories();
//        sc.setAttribute("CatArr", catArr);
//
//        ArrayList<Customer> custArr = (ArrayList<Customer>) new AccountsClient().getAllAccounts();
//        sc.setAttribute("CustArr", custArr);
//
//        ArrayList<Ingredient> ingArr = (ArrayList<Ingredient>) new IngredientsClient().getAllIngredients();
//        sc.setAttribute("IngArr", ingArr);
//
//        ArrayList<Order> ordArr = (ArrayList<Order>) new OrdersClient().getAllOrders();
//        sc.setAttribute("OrdArr", ordArr);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Servlet Context Destroyed");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
