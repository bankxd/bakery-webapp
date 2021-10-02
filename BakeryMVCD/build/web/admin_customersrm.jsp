
<%@page import="com.bakerysystem.client.AccountsClient"%>
<%@page import="com.bakerysystem.Model.Customer"%>
<%@page import="com.bakerysystem.Model.Product"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <title>Admin</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
    <style>
        body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif}
        body {
            font-size:16px;
            background-color: whitesmoke;
        }
        .w3-half img{margin-bottom:-6px;margin-top:16px;opacity:0.8;cursor:pointer}
        .w3-half img:hover{opacity:1}
        table, th, td {
            border: 1px solid black;
            background-color: window;
            border: 0.5px solid grey;
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
        <% if (session.getAttribute("admin") == null) { %>
        <% request.getRequestDispatcher("login.jsp").forward(request, response);%>
        <% } %>
                <!-- Navbar (sit on top) -->
    <editor-fold defaultstate="collapsed" desc="navfull">
        <div class="w3-top" style="opacity: 0.8">
            <div class="w3-bar w3-card w3-white" id="myNavbar">
                <!-- Right-sided navbar links -->
                <div class="w3-right w3-hide-small" style="font-family: Playfair Display; font-size: 19px;"> 
                    <a href="admin_shopview.jsp" class="w3-bar-item w3-button">VIEW SHOP</a>
                    <div class="w3-dropdown-click">
                        <a href="#acc" onclick="openAccount()" class="w3-bar-item w3-button"><i class="fa fa-user-circle-o"></i>ACCOUNT</a>
                        <div id="acco" class="w3-dropdown-content w3-bar-block w3-border">
                            <a href="javascript:void(0)" onclick="openAccount()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
                            <a href="admin_account.jsp" class="w3-bar-item w3-button" onclick="">My Account</a>
                            <form action="logout" method="GET">
                                <a href="" class="w3-bar-item w3-button"><button type="submit">Log Out</button></a>
                            </form>
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
            function openAccount() {
                var x = document.getElementById("acco");
                if (x.className.indexOf("w3-show") == -1) {
                    x.className += " w3-show";
                } else {
                    x.className = x.className.replace(" w3-show", "");
                }
            }
        </script>
        <div id="t"></div>
        <!-- Sidebar/menu -->
        <nav class="w3-sidebar w3-white w3-collapse w3-top w3-large w3-padding w3-card" style="z-index:3;width:300px;font-weight:bold;" id="mySidebar"><br>
            <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px">Close Menu</a>
            <div class="w3-container">
                <img src="Images/doughknotlogo.png" width="240" height="200">
                <h3 class="w3-padding-24"><b>ADMIN</b></h3>
            </div>
            <div class="w3-bar-block">
                <a href="admin.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">DASHBOARD</a> 
                <a href="admin_customersrm.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">CUSTOMERS</a> 
                <a href="admin_productsrm.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">PRODUCTS</a> 
                <a href="admin_ingredientsrm.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">INGREDIENTS</a> 
                <a href="admin_categoriesrm.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">CATEGORIES</a> 
                <a href="admin_ordersrm.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">ORDERS</a> 
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

            <!-- CUSTOMERS -->
            <editor-fold defaultstate="collapsed" desc="Sign In">
                <div style="padding-left: 20px;padding-top: 20px;">
                    <h2>View All Customers</h2></br>
                    <div class="w3-row">
                        <% ArrayList<Customer> custArr = (ArrayList<Customer>) new AccountsClient().getAllAccounts(); %>
                        <table>
                            <tr>
                                <td>Customer ID</td>
                                <td>First Name</td>
                                <td>Last Name</td>
                                <td>Contact Number</td>
                                <td>Address</td>
                                <td>Email</td>
                                <td>Password</td>
                                <td>Update</td>
                                <td>Delete</td>
                            </tr>
                            <% for (Customer cust : custArr) {%>
                            <tr>
                                <td><%= cust.getCustomerId()%></td>
                                <td><%= cust.getFirstName()%></td>
                                <td><%= cust.getLastName()%></td>
                                <td><%= cust.getTelephonemobile()%></td>
                                <td><%= cust.getAddressId()%></td>
                                <td><%= cust.getEmail()%></td>
                                <td><%= cust.getPassword()%></td>
                            <form action="admincustomer" method="GET">
                                <input type="hidden" name="custupdate" value="<%= cust.getCustomerId()%>">
                                <td><button type="submit" name="custupdbut" value="Update">Update</button></a></td>
                            </form>
                            <form action="admincustomer" method="POST">
                                <input type="hidden" name="custdelete" value="<%= cust.getCustomerId()%>">
                                <td><button type="submit" name="custbut" value="Remove">Remove</button></td>
                            </form>

                            </tr>
                            <%}%>
                        </table><br>
                    </div>
                </div>
        </div>
    </editor-fold>

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
        <a href="#t" class="w3-button w3-light-grey"><i class="fa fa-arrow-up w3-margin-right"></i>To the top</a>
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
