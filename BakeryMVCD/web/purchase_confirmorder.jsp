<%-- 
    Document   : confirmorder
    Created on : 17 Mar 2021, 12:12:12 PM
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
        <title>Order Confirmation</title>
    </head>
    <body>
        <% if (session.getAttribute("logcust") == null) { %>
        <% request.getRequestDispatcher("login.jsp").forward(request, response);%>
        <% } %>
        <!-- Confirm Order Form -->
    <editor-fold defaultstate="collapsed">
        <div id="conforderf" >
            <span onclick="window.open('welcome.jsp').style.display = 'none'" class="close" title="Close Modal">&times;</span>
            <% Cart cart = (Cart) session.getAttribute("cart");%>
            <% Order order = new Order();%>
            <% ArrayList<ProductLineItem> cartArr = cart.getProducts();  %>
            <% if (cartArr.isEmpty()){request.getRequestDispatcher("account_cart.jsp").forward(request, response);}%>
            <div>
                <table>
                    <br><h2>Confirm Order</h2><br>
                    <tr>
                        <td>Name</td>
                        <td>Quantity</td>
                        <td>Selling price</td>
                    </tr>
                    <% for (ProductLineItem pli : cartArr) {%>
                    <tr>
                        <td><%= pli.getProductName()%></td>
                        <td><%= pli.getQuantity()%></td> 
                        <td><%= "R " + pli.getProductPrice()%></td>
                    </tr>
                    <%}%>
                </table>
                <% ArrayList<ProductLineItem> CartArr = (ArrayList<ProductLineItem>) session.getAttribute("CartArr"); %>
                <% totalprice = 0;%>
                <% for (ProductLineItem plip : CartArr) {%>
                <% totalprice += plip.getProductPrice();%>
                <%}%>
                <% order.setTotalPrice(totalprice);%>
                <% session.setAttribute("order", order);%>
                <% session.setAttribute("CartArr", CartArr);%>
                <div id="totalbuts"
                     <h4>Total <span id="ttprice" style="color:black"><b><%= "R " + totalprice%></b></span></h4><br><br>
                    <form action="checkout" method="POST">
                        <input type="hidden" name>
                        <button type="submit" id="confbut" name="shopordbut" value="Confirm Order">Confirm Order</button>
                    </form>
                </div>
            </div>
        </div>
        <style>
            label {
                margin-bottom: 10px;
                display: block;
            }

            #confbut {
                background-color: white;
                color: black;
                border: 2px solid #555555;
                width: 40%;
                height: 60px;

            }

            button:hover {
                background-color: #555555;
                color: white;
            }

            a {
                color: #2196F3;
            }

            hr {
                border: 1px solid lightgrey;
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

            #ttprice{
                margin: 8% auto 10% auto;
                width: 60%;
            }

            span.price {
                float: right;
                color: grey;
            }
            #conforderf{
                margin: 3% auto 20% auto; /* 5% from the top, 15% from the bottom and centered */
                border: 3px solid burlywood;
                width: 60%; /* Could be more or less, depending on screen size */
                overflow: auto; /* Enable scroll if needed */
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
                text-align: center;
                font-size: 22px;
                font-family: initial;
                background-color: burlywood;
                border-collapse: collapse;
            }
            table{
                font-size: 22px;
                margin: 8% auto 10% auto;
                width: 80%;
            }
            #totalbuts{
                font-size: 22px;
                margin: 8% auto 10% auto;
                width: 60%;
            }
            th, td {
                padding: 25px;
                text-align: left;
                background-color: window;
                border-collapse: collapse;
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

    </editor-fold>
</body>
</html>
