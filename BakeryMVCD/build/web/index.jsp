<%-- 
    Document   : index1
    Created on : 08 Mar 2021, 8:55:43 AM
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
    <!-- Tags -->
    <editor-fold defaultstate="collapsed" desc="Objects">

        <jsp:useBean id="customerlogin" class="com.bakerysystem.Model.Customer" scope="application">

        </jsp:useBean>

        <jsp:useBean id="cart" class="com.bakerysystem.Model.Cart" scope="application">

        </jsp:useBean>

    </editor-fold>
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
                <a href="#team" class="w3-bar-item w3-button"><i class="fa fa-user"></i> TEAM</a>
                <a href="#catalogue" class="w3-bar-item w3-button"><i class=""></i> CATEGORIES</a>
                <a href="#shop" class="w3-bar-item w3-button"><i class="fa fa-usd"></i> SHOP</a>
                <a href="#contact" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i> CONTACT</a>
                <div class="w3-dropdown-click">
                    <a href="#cart" onclick ="openCart()" class="w3-bar-item w3-button"><i class="fa fa-cart-arrow-down"></i> CART</a>
                    <div id="carrt" class="w3-dropdown-content w3-bar-block w3-border" style="width: 200%">
                        <a href="javascript:void(0)" onclick="openCart()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>

                        <p>Total <span class="price" style="color:black"><b>Rx</b></span></p>
                        </br>
                        <button class="w3-button w3-brown w3-mobile" onclick="document.getElementById('conforderf').style.display = 'block'" style="width:auto;">Proceed to Checkout</button>
                    </div>
                </div>
                <div class="w3-dropdown-click">
                    <a href="#account" onclick="openAccount()" class="w3-bar-item w3-button"><i class="fa fa-user-circle-o"></i>ACCOUNT</a>
                    <div id="acco" class="w3-dropdown-content w3-bar-block w3-border">
                        <a href="javascript:void(0)" onclick="openAccount()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
                        <a href="#" class="w3-bar-item w3-button" onclick="document.getElementById('signinf').style.display = 'block'">Sign In</a>
                        <a href="#" class="w3-bar-item w3-button" onclick="document.getElementById('signinf').style.display = 'block'">My Account</a>
                        <a href="#" class="w3-bar-item w3-button" onclick="document.getElementById('signinf').style.display = 'block'">View Order History</a>
                        <a href="#" class="w3-bar-item w3-button" onclick="document.getElementById('signinf').style.display = 'block'">Log Out</a>
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
    </script>

    <!-- Sidebar on small screens when clicking the menu icon -->
    <nav class="w3-sidebar w3-bar-block w3-black w3-card w3-animate-left w3-hide-medium w3-hide-large" style="display:none" id="mySidebar">
        <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
        <a href="#about" onclick="w3_close()" class="w3-bar-item w3-button">ABOUT</a>
        <a href="#team" onclick="w3_close()" class="w3-bar-item w3-button">TEAM</a>
        <a href="#categories" onclick="w3_close()" class="w3-bar-item w3-button">CATEGORIES</a>
        <a href="#shop" onclick="w3_close()" class="w3-bar-item w3-button">SHOP</a>
        <a href="#contact" onclick="w3_close()" class="w3-bar-item w3-button">CONTACT</a>
        <a href="" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i> ACCOUNT</a>
        <a href="#cart" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i> CART</a>
    </nav>

    <!-- Header with full-height image -->
    <header class="bgimg-1 w3-display-container w3-grayscale-min" id="home">
        <div class="w3-display-left w3-text-white" style="padding:48px">
            <span class="w3-jumbo w3-hide-small">The Dough Knot Bakery</span><br>
            <span class="w3-xxlarge w3-hide-large w3-hide-medium"></span><br>
            <span class="w3-large">Next day delivery of freshly baked goods including Pretzals, Cinnabuns and Macaroons!</br>We are ready to take your order!</span>
            <p><a href="#shop" class="w3-button w3-white w3-padding-large w3-large w3-margin-top w3-opacity w3-hover-opacity-off">Shop Now</a></p>
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
<!-- Login Form -->
<editor-fold defaultstate="collapsed" desc="login">
    <div id="signinf" class="modal">
        <form class="modal-content animate" action="j_security_check" method="POST">
            <div class="imgcontainer">
                <span onclick="document.getElementById('signinf').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                <h2>Sign In!</h2>
            </div>

            <div class="w3-container">
                <label for="uname"><b>Email</b></label>
                <input type="text" placeholder="Enter Username" name="j_username" required>

                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="j_password" required>

                <button type="button" type="submit" value="Login">Login</button>

                <button type="button" onclick="document.getElementById('signupf').style.display = 'block'">Sign Up</button>

                <button type="button" onclick="document.getElementById('signinf').style.display = 'none'">Cancel</button>
                <label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </label>
                <span class="psw">Forgot <a onclick="document.getElementById('pwrec').style.display = 'block'">password?</a></span>
            </div>
        </form>
    </div>
    <editor-fold defaultstate="collapsed" desc="Styles">
        <style>
            body {font-family: Arial, Helvetica, sans-serif;}
            * {box-sizing: border-box;}

            /* Full-width input fields */
            input[type=text], input[type=password] {
                width: 100%;
                padding: 15px;
                margin: 5px 0 22px 0;
                display: inline-block;
                border: none;
                background: #f1f1f1;
            }

            /* Add a background color when the inputs get focus */
            input[type=text]:focus, input[type=password]:focus {
                background-color: #ddd;
                outline: none;
            }

            /* Set a style for all buttons */
            button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
                opacity: 0.9;
            }

            button:hover {
                opacity:1;
            }

            /* Extra styles for the cancel button */
            .cancelbtn {
                padding: 14px 20px;
                background-color: #f44336;
            }

            /* Float cancel and signup buttons and add an equal width */
            .cancelbtn, .signupbtn {
                float: left;
                width: 50%;
            }

            /* Add padding to container elements */
            .container {
                padding: 16px;
            }

            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 3; /* Sit on top */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: #474e5d;
                padding-top: 50px;
            }

            /* Modal Content/Box */
            .modal-content {
                background-color: #fefefe;
                margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
                border: 1px solid #888;
                width: 40%; /* Could be more or less, depending on screen size */
            }

            /* Style the horizontal ruler */
            hr {
                border: 1px solid #f1f1f1;
                margin-bottom: 25px;
            }

            /* The Close Button (x) */
            .close {
                position: absolute;
                right: 35px;
                top: 15px;
                font-size: 40px;
                font-weight: bold;
                color: #f1f1f1;
            }

            .close:hover,
            .close:focus {
                color: #f44336;
                cursor: pointer;
            }

            /* Clear floats */
            .clearfix::after {
                content: "";
                clear: both;
                display: table;
            }

            /* Change styles for cancel button and signup button on extra small screens */
            @media screen and (max-width: 300px) {
                .cancelbtn, .signupbtn {
                    width: 100%;
                }
            }
        </style>

        <script>
            // Get the modal
            var modal = document.getElementById('signinf');

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>
    </editor-fold>
</editor-fold>
<!-- Password Recovery Form -->
<editor-fold defaultstate="collapsed" desc="Sign Up">
    <div id="pwrec" class="modal">

        <form class="modal-content animate" action="recovery" method="post">
            <div class="imgcontainer">
                <span onclick="document.getElementById('pwrec').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                <h2>Password Recovery!</h2>
            </div>

            <div class="w3-container">
                <label for="psw"><b>Email Address</b></label>
                <input type="text" placeholder="Enter Email" name="ue" required>

                <button type="submit" >Recover Password</button>

                <button type="button" onclick="document.getElementById('pwrec').style.display = 'none'">Cancel</button>
            </div>
        </form>
    </div>

    <script>
        // Get the modal
        var modal = document.getElementById('signupf');

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
</editor-fold>
<!-- Registration Form -->
<editor-fold defaultstate="collapsed" desc="Sign Up">
    <div id="signupf" class="modal">

        <form id="Sign Up" class="modal-content animate" action="register" method="POST">
            <div class="imgcontainer">
                <span onclick="document.getElementById('signupf').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                <h2>Sign Up!</h2>
            </div>

            <div class="w3-container">
                <label for="fn"><b>First Name</b></label>
                <input type="text" placeholder="Enter Username" name="firstName" required>

                <label for="ln"><b>Last Name</b></label>
                <input type="text" placeholder="Enter Username" name="lastName" required>

                <label for="em"><b>Email Address</b></label>
                <input type="text" placeholder="Enter Password" name="email" required>

                <label for="cn"><b>Contact Number</b></label>
                <input type="text" placeholder="Enter Password" name="telephonemobile" required>

                <label for="da"><b>Delivery Address</b></label>
                <input type="text" placeholder="Enter Password" name="da" required>

                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>

                <button type="submit" onclick="document.getElementById('indexCS.jsp').style.display = 'none'">Sign Up</button>

                <button type="button" onclick="document.getElementById('signupf').style.display = 'none'" class="">Cancel</button>

            </div>
        </form>
    </div>

    <script>
        // Get the modal
        var modal = document.getElementById('signupf');

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
</editor-fold>
<!-- Confirm Order Form -->
<editor-fold defaultstate="collapsed" desc="Cart">
    <div id="conforderf" class="modal">
        <span onclick="document.getElementById('conforderf').style.display = 'none'" class="close" title="Close Modal">&times;</span>
        <form class="modal-content animate" action="" style="width:60%;">

            <div class="w3-container w3-center">
                <h4>Order <span class="price" style="color:black"><i class="fa fa-shopping-cart"></i> <b>4</b></span></h4>
                <p><a href="#">Product 1</a> <span class="price">$15</span></p>
                <p><a href="#">Product 2</a> <span class="price">$5</span></p>
                <p><a href="#">Product 3</a> <span class="price">$8</span></p>
                <p><a href="#">Product 4</a> <span class="price">$2</span></p>
                <hr>
                <p>Total <span class="price" style="color:black"><b>$30</b></span></p>
            </div>
            <input type="submit" onclick="document.getElementById('checkoutconf').style.display = 'block'" value="Confirm Order" class="btn">
        </form>

        <style>

            .container {
                background-color: #f2f2f2;
                padding: 5px 20px 15px 20px;
                border: 1px solid lightgrey;
                border-radius: 3px;
                z-index: 4
            }

            input[type=text] {
                width: 100%;
                margin-bottom: 20px;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            label {
                margin-bottom: 10px;
                display: block;
            }

            .icon-container {
                margin-bottom: 20px;
                padding: 7px 0;
                font-size: 24px;
            }

            .btn {
                background-color: #4CAF50;
                color: white;
                padding: 12px;
                margin: 10px 0;
                border: none;
                width: 100%;
                border-radius: 3px;
                cursor: pointer;
                font-size: 17px;
            }

            .btn:hover {
                background-color: #45a049;
            }

            a {
                color: #2196F3;
            }

            hr {
                border: 1px solid lightgrey;
            }

            span.price {
                float: right;
                color: grey;
            }

            /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
            @media (max-width: 800px) {
                .row {
                    flex-direction: column-reverse;
                }
                .col-25 {
                    margin-bottom: 20px;
                }
            }
        </style>
    </div>

</editor-fold>
<!-- Checkout Form -->
<editor-fold defaultstate="collapsed" desc="Cart">
    <div id="checkoutconf" class="modal">
        <span onclick="document.getElementById('checkoutconf').style.display = 'none'" class="close" title="Close Modal">&times;</span>
        <form class="modal-content animate" action="" style="width:60%;">

            <div class="row">
                <div class="col-50">
                    <h3>Billing Address</h3>
                    <label for="fname"><i class="fa fa-user"></i> Full Name</label>
                    <input type="text" id="fname" name="firstname" placeholder="John M. Doe">
                    <label for="email"><i class="fa fa-envelope"></i> Email</label>
                    <input type="text" id="email" name="email" placeholder="john@example.com">
                    <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
                    <input type="text" id="adr" name="address" placeholder="12 15th Street">
                    <label for="city"><i class="fa fa-institution"></i> City</label>
                    <input type="text" id="city" name="city" placeholder="South Africa">

                    <div class="row">
                        <div class="col-50">
                            <label for="state">State</label>
                            <input type="text" id="state" name="Province" placeholder="Gauteng">
                        </div>
                        <div class="col-50">
                            <label for="zip">Zip</label>
                            <input type="text" id="zip" name="zip" placeholder="0000">
                        </div>
                    </div>
                </div>

                <div class="col-50">
                    <h3>Payment</h3>
                    <label for="fname">Accepted Cards</label>
                    <div class="icon-container">
                        <i class="fa fa-cc-visa" style="color:navy;"></i>
                        <i class="fa fa-cc-amex" style="color:blue;"></i>
                        <i class="fa fa-cc-mastercard" style="color:red;"></i>
                        <i class="fa fa-cc-discover" style="color:orange;"></i>
                    </div>
                    <label for="cname">Name on Card</label>
                    <input type="text" id="cname" name="cardname" placeholder="John More Doe">
                    <label for="ccnum">Credit card number</label>
                    <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444">
                    <label for="expmonth">Exp Month</label>
                    <input type="text" id="expmonth" name="expmonth" placeholder="September">
                    <div class="row">
                        <div class="col-50">
                            <label for="expyear">Exp Year</label>
                            <input type="text" id="expyear" name="expyear" placeholder="2018">
                        </div>
                        <div class="col-50">
                            <label for="cvv">CVV</label>
                            <input type="text" id="cvv" name="cvv" placeholder="352">
                        </div>
                    </div>
                </div>

            </div>
            <label>
                <input type="checkbox" checked="checked" name="sameadr"> Shipping address same as billing
            </label>
            <input type="submit" onclick="document.getElementById('signupf').style.display = 'block'" value="Make Payment" class="btn">
        </form>
        <style>
            * {
                box-sizing: border-box;
            }

            .row {
                display: -ms-flexbox; /* IE10 */
                display: flex;
                -ms-flex-wrap: wrap; /* IE10 */
                flex-wrap: wrap;
                margin: 0 +16px;
            }

            .col-25 {
                -ms-flex: 25%; /* IE10 */
                flex: 25%;
            }

            .col-50 {
                -ms-flex: 50%; /* IE10 */
                flex: 50%;
            }

            .col-75 {
                -ms-flex: 75%; /* IE10 */
                flex: 75%;
            }

            .col-25,
            .col-50,
            .col-75 {
                padding: 0 16px;
            }

            .container {
                background-color: #f2f2f2;
                padding: 5px 20px 15px 20px;
                border: 1px solid lightgrey;
                border-radius: 3px;
            }

            input[type=text] {
                width: 100%;
                margin-bottom: 20px;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            label {
                margin-bottom: 10px;
                display: block;
            }

            .icon-container {
                margin-bottom: 20px;
                padding: 7px 0;
                font-size: 24px;
            }

            .btn {
                background-color: #4CAF50;
                color: white;
                padding: 12px;
                margin: 10px 0;
                border: none;
                width: 100%;
                border-radius: 3px;
                cursor: pointer;
                font-size: 17px;
            }

            .btn:hover {
                background-color: #45a049;
            }

            a {
                color: #2196F3;
            }

            hr {
                border: 1px solid lightgrey;
            }

            span.price {
                float: right;
                color: grey;
            }

            /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
            @media (max-width: 800px) {
                .row {
                    flex-direction: column-reverse;
                }
                .col-25 {
                    margin-bottom: 20px;
                }
            }
        </style>
    </div>

</editor-fold>
<!-- Payment Stub -->
<editor-fold defaultstate="collapsed" desc="Cart">
    <div id="conforderf" class="modal">
        <span onclick="document.getElementById('conforderf').style.display = 'none'" class="close" title="Close Modal">&times;</span>
        <form class="modal-content animate" action="" style="width:60%;">

            <div class="w3-container w3-center">
                <h4>Order <span class="price" style="color:black"><i class="fa fa-shopping-cart"></i> <b>4</b></span></h4>
                <p><a href="#">Product 1</a> <span class="price">$15</span></p>
                <p><a href="#">Product 2</a> <span class="price">$5</span></p>
                <p><a href="#">Product 3</a> <span class="price">$8</span></p>
                <p><a href="#">Product 4</a> <span class="price">$2</span></p>
                <hr>
                <p>Total <span class="price" style="color:black"><b>$30</b></span></p>
            </div>
            <input type="submit" onclick="document.getElementById('payment').style.display = 'block'" value="Confirm Order" class="btn">
        </form>

        <style>
            * {
                box-sizing: border-box;
            }

            .row {
                display: -ms-flexbox; /* IE10 */
                display: flex;
                -ms-flex-wrap: wrap; /* IE10 */
                flex-wrap: wrap;
                margin: 0 +16px;
            }

            .col-25 {
                -ms-flex: 25%; /* IE10 */
                flex: 25%;
            }

            .col-50 {
                -ms-flex: 50%; /* IE10 */
                flex: 50%;
            }

            .col-75 {
                -ms-flex: 75%; /* IE10 */
                flex: 75%;
            }

            .col-25,
            .col-50,
            .col-75 {
                padding: 0 16px;
            }

            .container {
                background-color: #f2f2f2;
                padding: 5px 20px 15px 20px;
                border: 1px solid lightgrey;
                border-radius: 3px;
            }

            input[type=text] {
                width: 100%;
                margin-bottom: 20px;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            label {
                margin-bottom: 10px;
                display: block;
            }

            .icon-container {
                margin-bottom: 20px;
                padding: 7px 0;
                font-size: 24px;
            }

            .btn {
                background-color: #4CAF50;
                color: white;
                padding: 12px;
                margin: 10px 0;
                border: none;
                width: 100%;
                border-radius: 3px;
                cursor: pointer;
                font-size: 17px;
            }

            .btn:hover {
                background-color: #45a049;
            }

            a {
                color: #2196F3;
            }

            hr {
                border: 1px solid lightgrey;
            }

            span.price {
                float: right;
                color: grey;
            }

            /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
            @media (max-width: 800px) {
                .row {
                    flex-direction: column-reverse;
                }
                .col-25 {
                    margin-bottom: 20px;
                }
            }
        </style>
    </div>

</editor-fold>
<!-- Successful Order -->
<editor-fold defaultstate="collapsed" desc="Cart">
    <div id="conforderf" class="modal">
        <span onclick="document.getElementById('conforderf').style.display = 'none'" class="close" title="Close Modal">&times;</span>
        <form class="modal-content animate" action="" style="width:60%;">

            <div class="w3-container w3-center">
                <h4>Order <span class="price" style="color:black"><i class="fa fa-shopping-cart"></i> <b>4</b></span></h4>
                <p><a href="#">Product 1</a> <span class="price">$15</span></p>
                <p><a href="#">Product 2</a> <span class="price">$5</span></p>
                <p><a href="#">Product 3</a> <span class="price">$8</span></p>
                <p><a href="#">Product 4</a> <span class="price">$2</span></p>
                <hr>
                <p>Total <span class="price" style="color:black"><b>$30</b></span></p>
            </div>
            <input type="submit" onclick="document.getElementById('payment').style.display = 'block'" value="Confirm Order" class="btn">
        </form>

        <style>
            * {
                box-sizing: border-box;
            }

            .row {
                display: -ms-flexbox; /* IE10 */
                display: flex;
                -ms-flex-wrap: wrap; /* IE10 */
                flex-wrap: wrap;
                margin: 0 +16px;
            }

            .col-25 {
                -ms-flex: 25%; /* IE10 */
                flex: 25%;
            }

            .col-50 {
                -ms-flex: 50%; /* IE10 */
                flex: 50%;
            }

            .col-75 {
                -ms-flex: 75%; /* IE10 */
                flex: 75%;
            }

            .col-25,
            .col-50,
            .col-75 {
                padding: 0 16px;
            }

            .container {
                background-color: #f2f2f2;
                padding: 5px 20px 15px 20px;
                border: 1px solid lightgrey;
                border-radius: 3px;
            }

            input[type=text] {
                width: 100%;
                margin-bottom: 20px;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            label {
                margin-bottom: 10px;
                display: block;
            }

            .icon-container {
                margin-bottom: 20px;
                padding: 7px 0;
                font-size: 24px;
            }

            .btn {
                background-color: #4CAF50;
                color: white;
                padding: 12px;
                margin: 10px 0;
                border: none;
                width: 100%;
                border-radius: 3px;
                cursor: pointer;
                font-size: 17px;
            }

            .btn:hover {
                background-color: #45a049;
            }

            a {
                color: #2196F3;
            }

            hr {
                border: 1px solid lightgrey;
            }

            span.price {
                float: right;
                color: grey;
            }

            /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
            @media (max-width: 800px) {
                .row {
                    flex-direction: column-reverse;
                }
                .col-25 {
                    margin-bottom: 20px;
                }
            }
        </style>
    </div>

</editor-fold>
<!-- Unsuccessful order/Try again -->
<editor-fold defaultstate="collapsed" desc="Cart">
    <div id="conforderf" class="modal">
        <span onclick="document.getElementById('conforderf').style.display = 'none'" class="close" title="Close Modal">&times;</span>
        <form class="modal-content animate" action="" style="width:60%;">

            <div class="w3-container w3-center">
                <h4>Order <span class="price" style="color:black"><i class="fa fa-shopping-cart"></i> <b>4</b></span></h4>
                <p><a href="#">Product 1</a> <span class="price">$15</span></p>
                <p><a href="#">Product 2</a> <span class="price">$5</span></p>
                <p><a href="#">Product 3</a> <span class="price">$8</span></p>
                <p><a href="#">Product 4</a> <span class="price">$2</span></p>
                <hr>
                <p>Total <span class="price" style="color:black"><b>$30</b></span></p>
            </div>
            <input type="submit" onclick="document.getElementById('payment').style.display = 'block'" value="Confirm Order" class="btn">
        </form>

        <style>
            * {
                box-sizing: border-box;
            }

            .row {
                display: -ms-flexbox; /* IE10 */
                display: flex;
                -ms-flex-wrap: wrap; /* IE10 */
                flex-wrap: wrap;
                margin: 0 +16px;
            }

            .col-25 {
                -ms-flex: 25%; /* IE10 */
                flex: 25%;
            }

            .col-50 {
                -ms-flex: 50%; /* IE10 */
                flex: 50%;
            }

            .col-75 {
                -ms-flex: 75%; /* IE10 */
                flex: 75%;
            }

            .col-25,
            .col-50,
            .col-75 {
                padding: 0 16px;
            }

            .container {
                background-color: #f2f2f2;
                padding: 5px 20px 15px 20px;
                border: 1px solid lightgrey;
                border-radius: 3px;
            }

            input[type=text] {
                width: 100%;
                margin-bottom: 20px;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            label {
                margin-bottom: 10px;
                display: block;
            }

            .icon-container {
                margin-bottom: 20px;
                padding: 7px 0;
                font-size: 24px;
            }

            .btn {
                background-color: #4CAF50;
                color: white;
                padding: 12px;
                margin: 10px 0;
                border: none;
                width: 100%;
                border-radius: 3px;
                cursor: pointer;
                font-size: 17px;
            }

            .btn:hover {
                background-color: #45a049;
            }

            a {
                color: #2196F3;
            }

            hr {
                border: 1px solid lightgrey;
            }

            span.price {
                float: right;
                color: grey;
            }

            /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
            @media (max-width: 800px) {
                .row {
                    flex-direction: column-reverse;
                }
                .col-25 {
                    margin-bottom: 20px;
                }
            }
        </style>
    </div>

</editor-fold>
<!-- About Section -->
<editor-fold defaultstate="collapsed" desc="Cart">
    <div class="w3-container" style="padding:32px 16px" id="about">
        <div class="w3-container w3-center">
            <img src="Images/doughknotlogo.png" style="width:20%;height: 200px;border-radius: 10%;">
        </div>
        <h3 class="w3-center">ABOUT US</h3>
        <p class="w3-center w3-large"></p>
        <div class="w3-row-padding w3-center" style="margin-top:64px">
            <div class="w3-quarter">
                <i class="fa fa-truck w3-margin-bottom w3-jumbo w3-center"></i>
                <p class="w3-large">Baked to Order</p>
                <p>Next day delivery from our bakery to you, delivered fresh from our Bakery to you every morning</p>
            </div>
            <div class="w3-quarter">
                <i class="fa fa-diamond w3-margin-bottom w3-jumbo"></i>
                <p class="w3-large">Passion</p>
                <p>We love people and exist to always delight our customers!</p>
            </div>
            <div class="w3-quarter">
                <i class="fa fa-cutlery w3-margin-bottom w3-jumbo"></i>
                <p class="w3-large">Customer Focus</p>
                <p>Easily reachable and always ready to take your order.</p>
            </div>
            <div class="w3-quarter">
                <i class="fa fa-heart w3-margin-bottom w3-jumbo"></i>
                <p class="w3-large"> Support our Winter Clothing Drive</p>
                <p>Help fund a clothing drive by enjoying our goods </p>
            </div>
        </div>
    </div>
</editor-fold>
<!-- Team Section -->
<editor-fold defaultstate="collapsed">
    <div class="w3-container" style="padding:128px 16px" id="team">
        <h3 class="w3-center">THE TEAM</h3>
        <div class="w3-row-padding w3-grayscale" style="margin-top:64px">
            <div class="w3-col l3 m6 w3-margin-bottom">
                <div class="w3-card">
                    <img src="/w3images/team2.jpg" alt="John" style="width:100%">
                    <div class="w3-container">
                        <h3>John Doe</h3>
                        <p class="w3-opacity">CEO & Founder</p>
                        <p>Phasellus eget enim eu lectus faucibus vestibulum. Suspendisse sodales pellentesque elementum.</p>
                        <p><button class="w3-button w3-light-grey w3-block"><i class="fa fa-envelope"></i> Contact</button></p>
                    </div>
                </div>
            </div>
            <div class="w3-col l3 m6 w3-margin-bottom">
                <div class="w3-card">
                    <img src="/w3images/team1.jpg" alt="Jane" style="width:100%">
                    <div class="w3-container">
                        <h3>Anja Doe</h3>
                        <p class="w3-opacity">Art Director</p>
                        <p>Phasellus eget enim eu lectus faucibus vestibulum. Suspendisse sodales pellentesque elementum.</p>
                        <p><button class="w3-button w3-light-grey w3-block"><i class="fa fa-envelope"></i> Contact</button></p>
                    </div>
                </div>
            </div>
            <div class="w3-col l3 m6 w3-margin-bottom">
                <div class="w3-card">
                    <img src="/w3images/team3.jpg" alt="Mike" style="width:100%">
                    <div class="w3-container">
                        <h3>Mike Ross</h3>
                        <p class="w3-opacity">Web Designer</p>
                        <p>Phasellus eget enim eu lectus faucibus vestibulum. Suspendisse sodales pellentesque elementum.</p>
                        <p><button class="w3-button w3-light-grey w3-block"><i class="fa fa-envelope"></i> Contact</button></p>
                    </div>
                </div>
            </div>
            <div class="w3-col l3 m6 w3-margin-bottom">
                <div class="w3-card">
                    <img src="/w3images/team4.jpg" alt="Dan" style="width:100%">
                    <div class="w3-container">
                        <h3>Dan Star</h3>
                        <p class="w3-opacity">Designer</p>
                        <p>Phasellus eget enim eu lectus faucibus vestibulum. Suspendisse sodales pellentesque elementum.</p>
                        <p><button class="w3-button w3-light-grey w3-block"><i class="fa fa-envelope"></i> Contact</button></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</editor-fold>
<!-- Categories Section -->
<editor-fold defaultstate="collapsed" desc="Cart">
    <div class="w3-container" style="padding:64px 16px" id="catalogue">
        <h3 class="w3-center">Categories</h3>
        <div class="w3-third">
            <div class="w3-display-container">
                <div class="w3-card">
                    <a href="#shop"> <img border="0" alt="" src="Images/macaroons.jpg" style="width:100%"></a>
                    <div class="w3-display-middle w3-display-hover">
                        <a href="#shop"><button class="w3-button w3-black">MACAROONS<i class="fa fa-shopping-basket"></i></button></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="w3-third">
            <div class="w3-display-container">
                <div class="w3-card">
                    <a href="#shop"> <img border="0" alt="" src="Images/pretz.jpg" style="width:100%"></a>
                    <div class="w3-display-middle w3-display-hover">
                        <a href="#shop"><button class="w3-button w3-black">PRETZALS<i class="fa fa-shopping-basket"></i></button></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="w3-third">
            <div class="w3-display-container">
                <div class="w3-card">
                    <a href="#shop"> <img border="0" alt="" src="Images/cinna.jpg" style="width:100%"></a>
                    <div class="w3-display-middle w3-display-hover">
                        <a href="#shop"><button class="w3-button w3-black">BUNS<i class="fa fa-shopping-basket"></i></button></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
<!-- SHOP -->
<editor-fold defaultstate="collapsed" desc="Cart">
    <% ArrayList<Product> prodArr = (ArrayList<Product>) getServletContext().getAttribute("ProductArr"); %>

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

    <editor-fold defaultstate="collapsed" desc="temps">
        <!--                    <div class="w3-col l3 s6">
                                <div class="w3-container">
                                    <div class="w3-display-container">
                                        <img src="Images/cupcakes.jpg" style="width:100%">
                                        <span class="w3-tag w3-display-topleft">New</span>
                                        <div class="w3-display-middle w3-display-hover">
                                            <button class="w3-button w3-black">Add to Cart<i class="fa fa-cart-plus"></i></button>
                                        </div>
                                    </div>
                                    <p>  <br><b>$19.99</b></p>
                                </div>
                                <div class="w3-container">
                                    <div class="w3-display-container">
                                        <img src="Images/cookieshome.jpg" style="width:100%">
                                        <div class="w3-display-middle w3-display-hover">
                                            <button class="w3-button w3-black">Add to Cart<i class="fa fa-cart-plus"></i></button>
                                        </div>
                                    </div>
                                    <p>  <br><b>$20.50</b></p>
                                </div>
                            </div>
                
                            <div class="w3-col l3 s6">
                                <div class="w3-container">
                                    <div class="w3-display-container">
                                        <img src="Images/donuts.jpg" style="width:100%">
                                        <div class="w3-display-middle w3-display-hover">
                                            <button class="w3-button w3-black">Add to Cart<i class="fa fa-cart-plus"></i></button>
                                        </div>
                                    </div>
                                    <p>  <br><b>$20.50</b></p>
                                </div>
                                <div class="w3-container">
                                    <div class="w3-display-container">
                                        <img src="Images/maccies.jpg" style="width:100%">
                                                            <span class="w3-tag w3-display-topleft">Sale</span>
                                        <div class="w3-display-middle w3-display-hover">
                                            <button class="w3-button w3-black">Add to Cart<i class="fa fa-cart-plus"></i></button>
                                        </div>
                                    </div>
                                    <p><br><b class="w3-text-red">$14.99</b></p>
                                </div>
                            </div>
                
                            <div class="w3-col l3 s6">
                                <div class="w3-container">
                                    <div class="w3-display-container">
                                        <img src="Images/lamear.jpg" style="width:100%">
                                        <div class="w3-display-middle w3-display-hover">
                                            <button class="w3-button w3-black">Add to Cart<i class="fa fa-cart-plus"></i></button>
                                        </div>
                                    </div>
                                    <p> <br><b>$14.99</b></p>
                                </div>
                                <div class="w3-container">
                                    <div class="w3-display-container">
                                        <img src="Images/yesses.jpg" style="width:100%">
                                        <div class="w3-display-middle w3-display-hover">
                                            <button class="w3-button w3-black">Add to Cart<i class="fa fa-cart-plus"></i></button>
                                        </div>
                                    </div>
                                    <p><br><b>$24.99</b></p>
                                </div>
                            </div>-->

    </editor-fold>
</editor-fold>
<!-- Contact Section -->
<editor-fold defaultstate="collapsed" desc="Cart">
    <div class="w3-container w3-light-grey" style="padding:128px 16px" id="contact">
        <h3 class="w3-center">CONTACT</h3>
        <p class="w3-center w3-large">Lets get in touch. Send us a message:</p>
        <div class="w3-center">
            <p><i class="fa fa-map-marker fa-fw w3-xxlarge w3-margin-right"></i> Gauteng, ZA</p>
            <p><i class="fa fa-phone fa-fw w3-xxlarge w3-margin-right"></i> Phone: +2777 898 7654</p>
            <p><i class="fa fa-envelope fa-fw w3-xxlarge w3-margin-right"> </i> Email: thedoughknotbakery@gmail.com</p>
            <br>
            <form action="" target="_blank">
                <p><input class="w3-input w3-border" type="text" placeholder="Name" required name="Name"></p>
                <p><input class="w3-input w3-border" type="text" placeholder="Email" required name="Email"></p>
                <p><input class="w3-input w3-border" type="text" placeholder="Subject" required name="Subject"></p>
                <p><input class="w3-input w3-border" type="text" placeholder="Message" required name="Message"></p>
                <p>
                    <button class="w3-button w3-black" type="submit">
                        <i class="fa fa-paper-plane"></i> SEND MESSAGE
                    </button>
                </p>
            </form>
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
