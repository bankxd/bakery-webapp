<%-- 
    Document   : checkoutpaymentdetails
    Created on : 17 Mar 2021, 12:13:18 PM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Checkout</title>
    </head>
    <body>
        <% if (session.getAttribute("logcust") == null) { %>
        <% request.getRequestDispatcher("login.jsp").forward(request, response);%>
        <% }%>
        <!-- Checkout Form -->
    <editor-fold defaultstate="collapsed" desc="Cart">
        <div id="detailsconf" class="modal">
            <br><h2>Confirm Your Details</h2>
            <form action="checkout" method="POST" style="width:100%;">
                <div class="row">
                    <div class="col-50">
                        <h3>Billing Address</h3>
                        <label for="fname"><i class="fa fa-user"></i> Full Name</label>
                        <input type="text" id="fname" name="name" placeholder="Name">
                        <label for="email"><i class="fa fa-envelope"></i> Email</label>
                        <input type="text" id="email" name="email" placeholder="email@example.com">
                        <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
                        <input type="text" id="adr" name="custupdaddr" placeholder="Address">
                        <label for="city"><i class="fa fa-institution"></i> City</label>
                        <input type="text" id="city" name="city" placeholder="South Africa">

                        <div class="row">
                            <div class="col-50">
                                <label for="state">State</label>
                                <input type="text" id="state" name="Province" placeholder="province">
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
                        <input type="text" id="cname" name="cardname" placeholder="card name">
                        <label for="ccnum">Credit card number</label>
                        <input type="text" id="ccnum" name="cardnumber" placeholder="xxxx-xxxx-xxxx-xxxx">
                        <label for="expmonth">Exp Month</label>
                        <input type="text" id="expmonth" name="expmonth" placeholder="month">
                        <div class="row">
                            <div class="col-50">
                                <label for="expyear">Exp Year</label>
                                <input type="text" id="expyear" name="expyear" placeholder="xxxx">
                            </div>
                            <div class="col-50">
                                <label for="cvv">CVV</label>
                                <input type="text" id="cvv" name="cvv" placeholder="xxx">
                            </div>
                        </div>
                    </div>

                </div>
                <label>
                    <input type="checkbox" checked="checked" name="sameadr"> Shipping address same as billing
                </label>
                <button id="confbut" type="submit" name="shopordbut" value="Make Payment" >Place Order</button>
            </form>
            <style>
                * {
                    box-sizing: border-box;
                }
                #detailsconf{
                    margin: 3% auto 20% auto; /* 5% from the top, 15% from the bottom and centered */
                    border: 20px solid burlywood;
                    width: 60%; /* Could be more or less, depending on screen size */
                    overflow: auto; /* Enable scroll if needed */
                    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
                    text-align: center;
                    font-size: 22px;
                    font-family: initial;
                    background-color: burlywood;
                    border-collapse: collapse;
                }
                .row {
                    display: -ms-flexbox; /* IE10 */
                    display: flex;
                    -ms-flex-wrap: wrap; /* IE10 */
                    flex-wrap: wrap;
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

                #confbut {
                    background-color: white;
                    color: black;
                    border: 2px solid #555555;
                    width: 60%;
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
</body>
</html>
