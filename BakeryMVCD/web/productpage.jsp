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
    <header class="" id="">

    </header>
</editor-fold>
<!-- Modal for full size images on click-->
<editor-fold defaultstate="collapsed" desc="Cart">
    <div id="modal01" class="w3-modal w3-black" onclick="this.style.display = 'none'">
        <span class="w3-button w3-xxlarge w3-black w3-padding-large w3-display-topright" title="Close Modal Image">×</span>
        <div class="w3-modal-content w3-animate-zoom w3-center w3-transparent w3-padding-64">
            <img id="img01" class="w3-image">
            <p id="caption" class="w3-opacity w3-large"></p>
        </div>
    </div>
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
        // Modal Image Gallery
        function onClick(element) {
            document.getElementById("img01").src = element.src;
            document.getElementById("modal01").style.display = "block";
            var captionText = document.getElementById("caption");
            captionText.innerHTML = element.alt;
        }


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
