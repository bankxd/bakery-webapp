<%-- 
    Document   : shop
    Created on : 17 Mar 2021, 1:15:49 PM
    Author     : David
--%>

<%@page import="com.bakerysystem.client.ProductsClient"%>
<%@page import="com.bakerysystem.Model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Shop -->
    <editor-fold defaultstate="collapsed" desc="Cart">
        <% ArrayList<Product> prodArr = (ArrayList<Product>) getServletContext().getAttribute("ProductArr"); %>

        <div class="w3-row w3-mobile " >
            <h3 class="w3-center"style="padding:64px 16px" id="shop">Shop</h3>
            <% for(int j = 0; j < prodArr.size();) {%>
            <div class="w3-col l3 s6" >
                <% Product p1 = prodArr.get(j); %>
                <div class="w3-container">
                    <div class="w3-display-container">
                        <img src="<%=p1.getPhoto()%>" style="width:100%">
                        <div class="w3-display-middle w3-display-hover">
                            <button href="productpage.jsp" class="w3-button w3-black">More Info<i class="fa fa-cart-plus"></i></button>
                        </div>
                    </div>
                    <p>  <br><b><%= p1.getPrice()%></b></p>
                </div>
                <% j++; %>
                <% if(j < prodArr.size()) {%>
                <% p1 = prodArr.get(j); %>
                <div class="w3-container">
                    <div class="w3-display-container">
                        <img src="<%=p1.getPhoto()%>" style="width:100%">
                        <div class="w3-display-middle w3-display-hover">
                            <button class="w3-button w3-black">More Info<i class="fa fa-cart-plus"></i></button>
                        </div>
                    </div>
                    <p>  <br><b><%= p1.getPrice()%></b></p>
                </div>
                <% }%>
            </div>
            <% }%>
        </div>
    </editor-fold>
</body>
</html>
