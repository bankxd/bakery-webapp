<%-- 
    Document   : login
    Created on : 17 Mar 2021, 12:08:35 PM
    Author     : David
--%>
<%@page import="com.bakerysystem.Model.Order"%>
<%@page import="com.bakerysystem.Model.Customer"%>
<%@page import="com.bakerysystem.Model.Cart"%>
<%@page import="com.bakerysystem.Model.ProductLineItem"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! double totalprice;%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <!-- Confirm Order Form -->
    <editor-fold defaultstate="collapsed" desc="Cart">
        <div id="conforderf" >
            <span onclick="window.open('welcome.jsp').style.display = 'none'" class="close" title="Close Modal">&times;</span>
            <% Cart cart = (Cart) session.getAttribute("cart");%>
            <% Order order = new Order();%>
            <% ArrayList<ProductLineItem> cartArr = cart.getProducts();  %>
            <% order.setOrderLineArr(cartArr); %>
            <div>
                <table>
                    <br><h2>Confirm Order</h2><br>
                    <tr>
                        <td>Name</td>
                        <td>Quantity</td>
                        <td>Selling price</td>
                        <td>Delete</td>
                    </tr>
                    <% for (ProductLineItem pli : cartArr) {%>
                    <tr>
                        <td><%= pli.getProductName()%></td>
                        <td><%= pli.getQuantity()%></td> 
                        <td><%= pli.getProductPrice()%></td>
                        <% totalprice += pli.getProductPrice();%>

                    <form action="cart" method="POST">
                        <input type="hidden" name="cartdeleteID" value="<%= pli.getProductID()%>">
                        <td><button type="submit" name="cartbut" value="Remove">Remove</button></td>
                    </form>
                    </tr>
                    <% order.setTotalPrice(totalprice);%>
                    <%}%>

                    <tr> <p>Total <span class="price" style="color:black"><b><%= totalprice%></b></span></p></tr>

                    <a href="purchase_confirmdetails.jsp"><button>Confirm Order</button><a/>
                </table><br><br>
            </div>
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
                    font-size: 22px;
                    font-family: initial;
                }

                table {
                    margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
                    border: 60px solid white;
                    width: 40%; /* Could be more or less, depending on screen size */
                    overflow: auto; /* Enable scroll if needed */
                    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
                    text-align: center;
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
</body>
</html>
