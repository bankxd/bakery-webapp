<%-- 
    Document   : welcome
    Created on : 17 Mar 2021, 12:05:15 PM
    Author     : David
--%>

<%@page import="com.bakerysystem.Model.ProductLineItem"%>
<%@page import="com.bakerysystem.Model.Cart"%>
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
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Playfair+Display" />
        <link rel="shortcut icon" type="image/x-icon" href="Images/pretzelogo.png">

        <style>
            body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}

            body, html {
                height: 100%;
                line-height: 1.8;
            }

            /*            body {
                            padding-top: 113px;
                        }*/

            /* Full height image header */
            .bgimg-1 {
                background-position: top;
                background-size: 100% 100%;
                background-image: url("Images/treaty.jpg");
                min-height: 100%;
                overflow: auto;
            }


            .w3-bar .w3-button {
                padding: 16px;
            }

            #navpic{
                height: 80px;
                border-radius: 10%;
            }

            .flip-card {
                background-color: transparent;
                width: 300px;
                height: 300px;
                perspective: 1000px;
            }

            .flip-card-inner {
                position: relative;
                width: 100%;
                height: 100%;
                text-align: center;
                transition: transform 0.6s;
                transform-style: preserve-3d;
                box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            }

            .flip-card:hover .flip-card-inner {
                transform: rotateY(180deg);
            }

            .flip-card-front, .flip-card-back {
                position: absolute;
                width: 100%;
                height: 100%;
                -webkit-backface-visibility: hidden;
                backface-visibility: hidden;
            }

            .flip-card-front {
                background-color: #bbb;
                color: black;
            }

            .flip-card-back {
                background-color: chocolate ;
                color: white;
                transform: rotateY(180deg);
            }
            button {
                background-color: white;
                color: black;
                border: 1px solid #555555;
                width: 100px;
                height: 40px;
            }

            button:hover {
                background-color: #555555;
                color: white;
            }

            #navpic{
                height: 45px;
                border-radius: 10%;
            }
        </style>

    </editor-fold>
</head>
<body>
    <% if (session.getAttribute("logcust") == null) { %>
    <% request.getRequestDispatcher("login.jsp").forward(request, response);%>
    <% } %>
    <!-- Navbar (sit on top) -->
<editor-fold defaultstate="collapsed" desc="">
    <div class="w3-top" style="opacity: 0.8">
        <div class="w3-bar w3-black w3-card" id="myNavbar">
            <div class="w3-bar-item w3-button w3-wide">
                <a href="welcome.jsp"><img src="Images/pretzellogo.png" id="navpic"/><a/>
            </div>
            <!-- Right-sided navbar links -->
            <div class="w3-right w3-hide-small" style="font-family: Playfair Display; font-size: 19px;">
                <a href="shop4.jsp" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i> CONTACT</a>
                <div class="w3-dropdown-click">
                    <a href="#acc" onclick="openAccount()" class="w3-bar-item w3-button"><i class="fa fa-user-circle-o"></i>ACCOUNT</a>
                    <div id="acco" class="w3-dropdown-content w3-bar-block w3-border">
                        <a href="javascript:void(0)" onclick="openAccount()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
                        <a href="account_details.jsp" class="w3-bar-item w3-button w3-black" onclick="">My Account</a>
                        <a href="account_orders.jsp" class="w3-bar-item w3-button" onclick="">View Order History</a>
                        <form action="logout" method="GET">
                            <a href="" class="w3-bar-item w3-button"><button type="submit">Log Out</button></a>
                        </form>
                    </div>
                </div>
                <a href="account_cart.jsp" onclick="" class="w3-bar-item w3-button"><i class="fa fa-cart-arrow-down"></i> CART</a>
            </div>
        </div>
        <!-- Hide right-floated links on small screens and replace them with a menu icon -->
        <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium w3-black" onclick="w3_open()">
            <i class="fa fa-bars"></i>
        </a>
    </div>
    <script>
        function openAccount() {
            var x = document.getElementById("acco");
            if (x.className.indexOf("w3-show") == -1) {
                x.className += " w3-show";
            } else {
                x.className = x.className.replace(" w3-show", "");
            }
        }
    </script>

    <!-- Sidebar on small screens when clicking the menu icon -->
    <nav class="w3-sidebar w3-bar-block w3-black w3-card w3-animate-left w3-hide-medium w3-hide-large" style="display:none" id="mySidebar">
        <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
        <a href="about.jsp" onclick="w3_close()" class="w3-bar-item w3-button">ABOUT</a>
        <a href="shop4.jsp" onclick="w3_close()" class="w3-bar-item w3-button">SHOP</a>
        <a href="account_details.jsp" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i>ACCOUNT</a>
        <a href="account_cart.jsp" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i>CART</a>
    </nav>

    <!-- Header with full-height image -->
    <header class="bgimg-1 w3-display-container w3-mobile" id="home"style="padding-top: 20px;">
        <div class="w3-display-bottomleft w3-text-grey w3-large" style="padding:24px 48px">
            <i class="fa fa-facebook-official w3-hover-opacity"></i>
            <i class="fa fa-instagram w3-hover-opacity"></i>
            <i class="fa fa-snapchat w3-hover-opacity"></i>
            <i class="fa fa-pinterest-p w3-hover-opacity"></i>
            <i class="fa fa-twitter w3-hover-opacity"></i>
            <i class="fa fa-linkedin w3-hover-opacity"></i>
        </div>
    </header><br>
</editor-fold>

<!-- Shop -->
<editor-fold defaultstate="collapsed" desc="Cart">
    <div class="w3-container" style="text-align: center;background-color: burlywood;margin: 30px 67px 30px 67px;border-radius: 20px;">
        <h1>Our Products</h1>
    </div>
    <% ArrayList<Product> prodArr = (ArrayList<Product>) new ProductsClient().recieveProducts(); %>

    <header class="w3-container" style="margin: 20px;border-radius: 20px;">
        <div class="w3-row w3-mobile "style="margin: auto;margin: 20px;" >
            <% for (int j = 0; j < prodArr.size(); j++) { %>
            <% Product p1 = prodArr.get(j);%>
            <div class="w3-quarter" style="margin: auto;border-radius: 20px;" >
                <div class="flip-card" style="margin: auto;border-radius: 20px;" >
                    <div class="flip-card-inner"style="border-radius: 20px;">
                        <div class="flip-card-front"style="border-radius: 20px;">
                            <img src="./Images/<%= p1.getProductName()%>.jpg" style="width:100%;height:100%;border-radius: 20px;">
                        </div>
                        <div class="flip-card-back"style="border-radius: 20px;">
                            <div class='w3-row' style='font-size: 14px; height: 80%;'>
                                <h2><%=p1.getProductName()%></h2> 
                                <p><%=p1.getProductDescription()%></p> 
                                <p><%=p1.getProductWarnings()%></p>
                                <b<h4>R<%=p1.getActualPrice()%></h4><b>
                            </div>
                            <form action="cart" method="GET">
                                <input type="hidden" name="prodID" value="<%= p1.getProductID()%>">
                                <button type="submit" name="cartaddbut" value="Add to Cart">Add to Cart</button>
                            </form>
                        </div>
                    </div>
                </div></br>
            </div>
            <% }%>
        </div>
    </header>
</editor-fold>

<!-- Footer -->
<editor-fold defaultstate="collapsed" desc="Cart">
    <footer class="w3-center w3-black w3-padding-64">
        <a href="#home" class="w3-button w3-light-grey"><i class="fa fa-arrow-up w3-margin-right"></i>To the top</a>
        <div class="w3-xlarge w3-section">
            <i class="fa fa-facebook-official w3-hover-opacity"></i>
            <i class="fa fa-instagram w3-hover-opacity"></i>
            <i class="fa fa-snapchat w3-hover-opacity"></i>
            <i class="fa fa-pinterest-p w3-hover-opacity"></i>
            <i class="fa fa-twitter w3-hover-opacity"></i>
            <i class="fa fa-linkedin w3-hover-opacity"></i>
        </div>
    </footer>

    <script>
        // Toggle between showing and hiding the sidebar when clicking the menu icon
        var mySidebar = document.getElementById("mySidebar");

        function w3_open() {
            if (mySidebar.style.display === 'block') {
                mySidebar.style.display = 'none';
            } else {
                mySidebar.style.display = 'block';
            }
        }

        // Close the sidebar with the close button
        function w3_close() {
            mySidebar.style.display = "none";
        }
    </script>
</editor-fold>
</body>
</html>
