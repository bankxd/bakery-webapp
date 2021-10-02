<%-- 
    Document   : cart
    Created on : 19 Mar 2021, 1:50:38 PM
    Author     : David
--%>

<%@page import="com.bakerysystem.Model.Customer"%>
<%@page import="com.bakerysystem.Model.ProductLineItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bakerysystem.Model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <div class="w3-container" id="cart">
            <span onclick="window.open('shop4.jsp').style.display = 'none'" class="close" title="Close Modal">&times;</span>
            <form action="cart" method="POST">
                <% Customer customer = (Customer) session.getAttribute("logcust"); %>
                <% ArrayList<ProductLineItem> cartArr = customer.getCart().getProducts(); %>
                <% for(ProductLineItem pli : cartArr) {%>
                <p><a href="shop4.jsp"><%= pli.getProductName()%></a> <span class="price"><%= pli.getQuantity()%></span></p>
                    <%}%>
                <button><a href="confirmorder.jsp" class="w3-button" style="background-color:beige;">Proceed to Checkout</a></button>
            </form>
        </div>
        <style>
            body {font-family: Arial, Helvetica, sans-serif;}
            * {box-sizing: border-box;}

            /* Full-width input fields */
            input[type=text], input[type=password] {
                width: 100%;
                padding: 15px;
                margin: 5px 0 15px 0;
                display: inline-block;
                border: none;
                background: #f1f1f1;
                font-size: 22px;
                font-family: initial;
            }

            form {
                margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
                border: 40px solid white;
                width: 40%; /* Could be more or less, depending on screen size */
                overflow: auto; /* Enable scroll if needed */
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
                text-align: center;
                border-top-width: 20px;
                font-size: 22px;
                font-family: initial;
            }

            /* Add a background color when the inputs get focus */
            input[type=text]:focus, input[type=password]:focus {
                background-color: #ddd;
                outline: none;
            }

            /* Set a style for all buttons */
            button {
                background-color: burlywood;
                color: black;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
                opacity: 0.9;
                font-size: 22px;
                font-family: initial;
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
                margin:auto;
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
    </body>
</html>
