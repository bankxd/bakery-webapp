<%-- 
    Document   : welcome
    Created on : 17 Mar 2021, 12:05:15 PM
    Author     : David
--%>

<%@page import="com.bakerysystem.client.ProductsClient"%>
<%@page import="com.bakerysystem.Model.Customer"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bakerysystem.Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <editor-fold defaultstate="collapsed" desc="Sign In">
        <title>The Dough Knot G</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}

            body, html {
                height: 100%;
                line-height: 1.8;
            }

            /* Full height image header */
            .bgimg-1 {
                background-position: center;
                background-size: cover;
                background-image: url("Images/donuts.jpg");
                min-height: 100%;
            }

            .w3-bar .w3-button {
                padding: 16px;
            }

            #navpic{
                height: 80px;
                border-radius: 10%;
            }


        </style>

    </editor-fold>
</head>
<body>
    <!-- Navbar (sit on top) -->
<editor-fold defaultstate="collapsed" desc="">
    <div class="w3-top">
        <div class="w3-bar w3-white w3-card" id="myNavbar">
            <div class="w3-bar-item w3-button w3-wide">
                <img src="Images/pretzellogo.png" id="navpic"/>
            </div>
            <a href="#home" class="w3-bar-item w3-button w3-wide ">The Dough Knot Bakery</a>
            <!-- Right-sided navbar links -->
            <div class="w3-right w3-hide-small">
                <a href="#about" class="w3-bar-item w3-button">ABOUT</a>
                <a href="shop.jsp" class="w3-bar-item w3-button"><i class="fa fa-usd"></i> SHOP</a>
                <a href="#contact" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i> CONTACT</a>
                <div class="w3-dropdown-click">
                    <a href="#account" onclick="openAccount()" class="w3-bar-item w3-button"><i class="fa fa-user-circle-o"></i>ACCOUNT</a>
                    <div id="acco" class="w3-dropdown-content w3-bar-block w3-border">
                        <a href="javascript:void(0)" onclick="openAccount()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
                        <a href="login.jsp" class="w3-bar-item w3-button" onclick="">Sign In</a>
                        <a href="login.jsp" class="w3-bar-item w3-button" onclick="">My Account</a>
                        <a href="login.jsp" class="w3-bar-item w3-button" onclick="">View Order History</a>
                        <a href="login.jsp" class="w3-bar-item w3-button" onclick="">Log Out</a>
                    </div>
                </div>
                <div class="w3-dropdown-click">
                    <a href="#cart" onclick="openRightMenu()" class="w3-bar-item w3-button"><i class="fa fa-cart-arrow-down"></i> CART</a>
                    <div class="w3-sidebar w3-bar-block w3-card w3-animate-right" style="display:none;right:0;" id="rightMenu">
                        <button onclick="closeRightMenu()" class="w3-bar-item w3-button w3-large">Close &times;</button>
                        <a href="#" class="w3-bar-item w3-button">Link 1</a>
                        <a href="#" class="w3-bar-item w3-button">Link 2</a>
                        <a href="#" class="w3-bar-item w3-button">Link 3</a>
                        <a href="confirmorder.jsp" class="w3-bar-item w3-button">Proceed to Checkout</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Hide right-floated links on small screens and replace them with a menu icon -->
        <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="w3_open()">
            <i class="fa fa-bars"></i>
        </a>
    </div>
    <script>
        function openCart() {
            var x = document.getElementById("carrt");
            if (x.className.indexOf("w3-show") == -1) {
                x.className += " w3-show";
            } else {
                x.className = x.className.replace(" w3-show", "");
            }
        }

        function openAccount() {
            var x = document.getElementById("acco");
            if (x.className.indexOf("w3-show") == -1) {
                x.className += " w3-show";
            } else {
                x.className = x.className.replace(" w3-show", "");
            }
        }

        function openRightMenu() {
            document.getElementById("rightMenu").style.display = "block";
        }

        function closeRightMenu() {
            document.getElementById("rightMenu").style.display = "none";
        }
    </script>

    <!-- Sidebar on small screens when clicking the menu icon -->
    <nav class="w3-sidebar w3-bar-block w3-black w3-card w3-animate-left w3-hide-medium w3-hide-large" style="display:none" id="mySidebar">
        <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
        <a href="about.jsp" onclick="w3_close()" class="w3-bar-item w3-button">ABOUT</a>
        <a href="shop.jsp" onclick="w3_close()" class="w3-bar-item w3-button">SHOP</a>
        <a href="contact.jsp" onclick="w3_close()" class="w3-bar-item w3-button">CONTACT</a>
        <a href="account.jsp" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i>ACCOUNT</a>
        <a href="" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i>CART</a>
    </nav>

    <!-- Header with full-height image -->
    <header class="bgimg-1 w3-display-container w3-grayscale-min" id="home">
        <div class="w3-display-left w3-text-white" style="padding:48px; background: rgb(190, 199, 230, 0.4)">
            <span class="w3-jumbo w3-hide-small" style="color:black">The Dough Knot Bakery</span><br>
            <span class="w3-xxlarge w3-hide-large w3-hide-medium"></span><br>
            <span class="w3-large" style="color:black">Next day delivery of freshly baked goods including Pretzals, Cinnabuns and Macaroons!</br>We are ready to take your order!</span>
            <p><a href="login.jsp" class="w3-button w3-white w3-padding-large w3-large w3-margin-top w3-opacity w3-hover-opacity-off">Shop Now</a></p>
        </div> 
        <div class="w3-display-bottomleft w3-text-grey w3-large" style="padding:24px 48px">
            <i class="fa fa-facebook-official w3-hover-opacity"></i>
            <i class="fa fa-instagram w3-hover-opacity"></i>
            <i class="fa fa-snapchat w3-hover-opacity"></i>
            <i class="fa fa-pinterest-p w3-hover-opacity"></i>
            <i class="fa fa-twitter w3-hover-opacity"></i>
            <i class="fa fa-linkedin w3-hover-opacity"></i>
        </div>
    </header>
</editor-fold>
<!-- Shop -->
<editor-fold defaultstate="collapsed" desc="Cart">
    <% ArrayList<Product> prodArr1 = (ArrayList<Product>) getServletContext().getAttribute("ProductArr"); %>

    <%ArrayList<Product> prodArr = (ArrayList<Product>) new ProductsClient().recieveProducts();%>
    <div class="w3-row w3-mobile " >
        <h3 class="w3-center"style="padding:64px 16px" id="shop">Shop</h3>
        <% for (int j = 0; j < prodArr.size();) {%>
        <div class="w3-col l3 s6" >
            <%Product p1 = prodArr.get(j);%>
            <div class="w3-container">
                <div class="w3-display-container">
                    <img src="<%=p1.getPhoto()%>" style="width:100%">
                    <div class="w3-display-middle w3-display-hover">
                        <button class="w3-button w3-black">Add to Cart<i class="fa fa-cart-plus"></i></button>
                    </div>
                </div>
                <p>  <br><b><%= p1.getPrice()%></b></p>
            </div>
            <% j++; %>
            <% if (j < prodArr.size()) {%>
            <%p1 = prodArr.get(j);%>
            <div class="w3-container">
                <div class="w3-display-container">
                    <img src="<%p1.getPhoto();%>" style="width:100%">
                    <div class="w3-display-middle w3-display-hover">
                        <button class="w3-button w3-black">Add to Cart<i class="fa fa-cart-plus"></i></button>
                    </div>
                </div>
                <p>  <br><b><% p1.getPrice();%></b></p>
            </div>
            <% }%>
        </div>
        <% }%>
    </div>
</editor-fold>
</body>
</html>
