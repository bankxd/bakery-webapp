<%@page import="com.bakerysystem.client.OrdersClient"%>
<%@page import="com.bakerysystem.Model.Order"%>
<%@page import="com.bakerysystem.Model.Customer"%>
<%@page import="com.bakerysystem.Model.Cart"%>
<%@page import="com.bakerysystem.Model.ProductLineItem"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <title>My Account</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
    <style>
        body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif}
        body {font-size:16px;background-color: whitesmoke;}
        .w3-half img{margin-bottom:-6px;margin-top:16px;opacity:0.8;cursor:pointer}
        .w3-half img:hover{opacity:1}
        table, th, td {
            background-color: window;
            border-collapse: collapse;
        }
        table{
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);

        }
        th, td {
            padding: 15px;
            text-align: left;
        }
        button {
            background-color: white;
            color: black;
            border: 1.5px solid grey;

        }

        button:hover {
            background-color: #555555;
            color: white;
        }
    </style>
    <body>
        <% if (session.getAttribute("logcust") == null) { %>
        <% request.getRequestDispatcher("login.jsp").forward(request, response);%>
        <% }%>
        <!-- Sidebar/menu -->
        <nav class="w3-sidebar w3-white w3-collapse w3-top w3-large w3-padding w3-card" style="z-index:3;width:300px;font-weight:bold;" id="mySidebar">
            <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px">Close Menu</a>
            <div class="w3-container">
                <img src="Images/doughknotlogo.png" width="240" height="200">
                <h3 class="w3-padding-24"><b>ACCOUNT</b></h3>
            </div>
            <div class="w3-bar-block">
                <a href="account_details.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">ACCOUNT DETAILS</a> 
                <a href="account_cart.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">VIEW CART</a> 
                <a href="account_orders.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">ORDER HISTORY</a> 
                <a href="shop4.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">SHOP</a> 
            </div>
        </nav>

        <!-- Top menu on small screens -->
        <header class="w3-container w3-top w3-hide-large w3-white w3-xlarge w3-padding">
            <a href="javascript:void(0)" class="w3-button w3-white w3-margin-right" onclick="w3_open()">X</a>
            <span>The Dough Knot</span>
        </header>

        <!-- Overlay effect when opening sidebar on small screens -->
        <div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

        <!-- !PAGE CONTENT! -->
        <div class="w3-main" style="margin-left:340px;margin-right:40px">

            <!-- Header -->
            <div class="w3-container" style="margin-top:80px" id="showcase">

            </div>

            <!-- ORDERS -->
            <editor-fold defaultstate="collapsed" >
                <div class="w3-row" style="padding-left: 100px;padding-top: 1px;">
                    <h2>View All Orders</h2></br>
                    <% Customer logcust = (Customer) session.getAttribute("logcust"); %>
                    <% int lcid = (int) logcust.getCustomerId(); %>
                    <% ArrayList<Order> ordArr = (ArrayList<Order>) new OrdersClient().getCustomersOrders(lcid); %>
                    <table>
                        <tr>
                            <td>Order ID</td>
                            <td>Address</td>
                            <td>Price</td>
                            <td>Order Status</td>
                            <td>Payment Status</td>
                        </tr>
                        <% if (ordArr.isEmpty()) { %>
                        <tr><td rowspan="5">No Orders Yet!</td></tr>
                        <%} else { %>
                        <% for (Order ord : ordArr) {%>
                        <tr>
                            <td><%= ord.getOrderID()%></td>
                            <td><%= ord.getDeliveryAddressId()%></td>
                            <td><%= ord.getTotalPrice()%></td>
                            <td><%= ord.getOrderStatus()%></td>
                            <td><%= ord.getPaymentStatus()%></td>
                        </tr>
                        <%}%>
                        <%}%>
                    </table><br>
                </div>


                <script>
                    // Script to open and close sidebar
                    function w3_open() {
                        document.getElementById("mySidebar").style.display = "block";
                        document.getElementById("myOverlay").style.display = "block";
                    }

                    function w3_close() {
                        document.getElementById("mySidebar").style.display = "none";
                        document.getElementById("myOverlay").style.display = "none";
                    }
                </script>
                <!-- Footer -->
                <editor-fold defaultstate="collapsed" desc="Cart">
                    <footer class="w3-center w3-grayscale-min w3-padding-64">
                        <div class="w3-xlarge w3-section">
                            <i class="fa fa-facebook-official w3-hover-opacity"></i>
                            <i class="fa fa-instagram w3-hover-opacity"></i>
                            <i class="fa fa-snapchat w3-hover-opacity"></i>
                            <i class="fa fa-pinterest-p w3-hover-opacity"></i>
                            <i class="fa fa-twitter w3-hover-opacity"></i>
                            <i class="fa fa-linkedin w3-hover-opacity"></i>
                        </div>
                    </footer>

                    </body>
                    </html>
