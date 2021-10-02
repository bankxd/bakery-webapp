/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Services;

import com.bakerysystem.Model.Category;
import com.bakerysystem.Model.Customer;
import com.bakerysystem.Model.Ingredient;
import com.bakerysystem.Model.Order;
import com.bakerysystem.Model.ProductLineItem;
import com.bakerysystem.client.AccountsClient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Themba
 */
public class Reporter {

    private int numOrdersPlaced;
    private int numOrdersOutstanding;
    private int numOrdersDelivered;

    public ArrayList<Ingredient> getStock() {
        return null;//new IngredientsClient().getAllIngredients();
    }

    public ArrayList<Customer> viewUsersAndActivity() {
        return new AccountsClient().getAllAccounts();
    }

    public String validateSingleOrderStockAvailibity(Order order) {
        //  return error if order cannot be produced 
        return "";
    }

    public ArrayList<Order> orderPlacedPerDayDataRetriever() {

        return null;
    }

    public ArrayList<Category> categoryPopularityDataretriever() {

        return null;
    }

    public Set<ProductLineItem> mostPopularItem() {
        // iterate through every element
        //  -  (Plus for each product (line item) occurance * quanity) = number of times it was order
        //  -  Build temp line item for each

        Set<Integer> uniqueProducts = new HashSet<Integer>();
        ArrayList<ProductLineItem> products4rmOrderTable = new ArrayList<>();
        int counter = 0;

        //==========================================================================================
        for (int i = 0; i < 4; i++) {
            while (counter < 
                    2) {
                ProductLineItem pli = new ProductLineItem(i, "Repeated Product" + i, 1 + counter, 20);
                uniqueProducts.add(i);
                products4rmOrderTable.add(pli);
                counter++;
            } 
            counter = 0;
        }
        //==========================================================================================
        
        
        for (ProductLineItem pli : products4rmOrderTable) {
                System.out.print(pli.getProductID() + " ");
            }
        System.out.println("\n");
        
        Iterator itr = uniqueProducts.iterator();
        while(itr.hasNext()){
            Integer pli =  (Integer) itr.next();
            System.out.println(pli);
            
        }

        Set<ProductLineItem> graphData = new HashSet<>();
        
        //  not working so changing to set
//        int numTimesProductWasBought = 0;
        for (int i = 0 ; i < products4rmOrderTable.size()-1 ; i++) {
            if(!((i+1) > products4rmOrderTable.size()-1)){
                if(products4rmOrderTable.get(i).getProductID() == products4rmOrderTable.get(i+1).getProductID()){
//                  numTimesProductWasBought += (products4rmOrderTable.get(i+1).getQuantity() +  products4rmOrderTable.get(i).getQuantity());                    
                    products4rmOrderTable.get(i+1).setQuantity(products4rmOrderTable.get(i+1).getQuantity() + products4rmOrderTable.get(i).getQuantity());                    
                }else{
                    graphData.add(new ProductLineItem(products4rmOrderTable.get(i).getProductID(), products4rmOrderTable.get(i).getProductName(), products4rmOrderTable.get(i).getQuantity(),products4rmOrderTable.get(i).getProductPrice()));
                }
            }
            if((i+1)>products4rmOrderTable.size()-1){
                graphData.add(new ProductLineItem(products4rmOrderTable.get(i).getProductID(), products4rmOrderTable.get(i).getProductName(), products4rmOrderTable.get(i).getQuantity(),products4rmOrderTable.get(i).getProductPrice()));
            }
        }
        // List<Integer> productIDs = products4rmOrderTable.stream().map(p -> p.getProductID()).collect(Collectors.toCollection(ArrayList::new));
        // store into set
 
        // doing this for all order line items 
        return graphData;
    }
    
//    public static void main(String [] args){
//        Set<ProductLineItem> uniqueProducts = new Reporter().mostPopularItem();
//        Iterator itr = uniqueProducts.iterator();
//        while(itr.hasNext()){
//            ProductLineItem pli =  (ProductLineItem) itr.next();
//            System.out.println(pli.getProductName() + ": "+pli.getQuantity());
////            for(int i = 0 ; i < pli.getQuantity() ; i++){
////                System.out.print("X");
////            }
////            System.out.println("\n");
//        }
//    }
}
