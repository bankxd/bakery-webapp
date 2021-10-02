/*
 * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controller;
//
//import com.bakerysystem.Model.Customer;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author David
// */
//public class LoginProcess extends ProcessRequestA {
//
//    @Override
//    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            String email = (String) request.getParameter("ue");
//            String password = (String) request.getParameter("pass");
//
//            // -------------------- for test
//            //Customer customer = new Customer(email,password);
//            // -------------------- end for test
//            //String json = {email: password};
//            //Customer customer = new Customer(email, password);
//            //connectServer("http://10.7.7.111:8080/BakerySystemRest/app/");
//            //writeToServer(customer);
//            //Customer retCust = (Customer) readJSONFromServer(customer);
//            
//            //request.setAttribute("retcust", retCust);
//            RequestDispatcher view = request.getRequestDispatcher("/indexCS.jsp");
//            view.forward(request, response);
//        } catch (ServletException | IOException ex) {
//            ex.getMessage();
//        }
//    }
//}
