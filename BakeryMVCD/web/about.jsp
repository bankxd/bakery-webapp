<%-- 
    Document   : about
    Created on : 30 Mar 2021, 10:59:17 PM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Playfair+Display" />
    </head>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            margin: 0;
        }

        html {
            box-sizing: border-box;
        }

        *, *:before, *:after {
            box-sizing: inherit;
        }

        .column {
            float: left;
            width: 33.3%;
            margin-bottom: 16px;
            padding: 0 8px;
        }

        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            margin: 8px;
        }

        .about-section {
            padding: 50px;
            text-align: center;
            background-color: burlywood;
            color: white;
        }

        .container {
            padding: 0 16px;
        }

        .container::after, .row::after {
            content: "";
            clear: both;
            display: table;
        }

        .title {
            color: grey;
        }

        .button {
            border: none;
            outline: 0;
            display: inline-block;
            padding: 8px;
            color: white;
            background-color: #000;
            text-align: center;
            cursor: pointer;
            width: 100%;
        }

        .button:hover {
            background-color: #555;
        }

        @media screen and (max-width: 650px) {
            .column {
                width: 100%;
                display: block;
            }
        }
    </style>
</head>
<body>
    <!-- Navbar (sit on top) -->
<editor-fold defaultstate="collapsed" desc="navfull">
    <div class="w3-top" style="opacity: 0.8">
        <div class="w3-bar w3-black w3-card" id="myNavbar">
            <div class="w3-bar-item w3-button w3-wide">
                <a href="welcome.jsp"><img src="Images/pretzellogo.png" id="navpic"/></a>
            </div>
            <!-- Right-sided navbar links -->
            <div class="w3-right w3-hide-small" style="font-family: Playfair Display; font-size: 19px;"> 
                <a href="welcome.jsp" class="w3-bar-item w3-button"><i class="fa fa-shopping-bag"></i> HOME</a>
                <a href="welcome.jsp#contact" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i> CONTACT</a>
                <a href="login.jsp" onclick="openAccount()" class="w3-bar-item w3-button"><i class="fa fa-user-circle-o"></i>SIGN IN</a>
            </div>
        </div>
        <!-- Hide right-floated links on small screens and replace them with a menu icon -->
        <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="w3_open()">
            <i class="fa fa-bars"></i>
        </a>
    </div>

    <!-- Sidebar on small screens when clicking the menu icon -->
    <nav class="w3-sidebar w3-bar-block w3-black w3-card w3-animate-left w3-hide-medium w3-hide-large" style="display:none" id="mySidebar">
        <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
        <a href="about.jsp" onclick="w3_close()" class="w3-bar-item w3-button">ABOUT</a>
        <a href="shop4.jsp" onclick="w3_close()" class="w3-bar-item w3-button">SHOP</a>
        <a href="welcome.jsp#contact" onclick="w3_close()" class="w3-bar-item w3-button">CONTACT</a>
        <a href="account_details.jsp" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i>ACCOUNT</a>
        <a href="" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i>CART</a>
    </nav>
    <div style="margin-top: 100px;"
         <div class="about-section">
            <h1>We are THE DOUGH KNOT Bakery</h1>
            <p>We love baking and customers!</p>
        </div>

        <h2 style="text-align:center">Our Team</h2>
        <div class="row">
            <div class="column">
                <div class="card">
                    <img src="Images/empl2.jpg" alt="Jane" style="width:100%">
                    <div class="container">
                        <h2>Jane</h2>
                        <p class="title">Team</p>
                        <p>Some text that describes me lorem ipsum ipsum lorem.</p>
                        <p>jane@example.com</p>
                        <p><button class="button">Contact</button></p>
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="card">
                    <img src="Images/eml3.jpg" alt="Mike" style="width:100%">
                    <div class="container">
                        <h2>Angel Cakes</h2>
                        <p class="title">Founder</p>
                        <p>Some text that describes me lorem ipsum ipsum lorem.</p>
                        <p>mike@example.com</p>
                        <p><button class="button">Contact</button></p>
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="card">
                    <img src="Images/empl1.jpg" alt="John" style="width:100%">
                    <div class="container">
                        <h2>The team, John and Happy</h2>
                        <p class="title">Team</p>
                        <p>Some text that describes me lorem ipsum ipsum lorem.</p>
                        <p>john@example.com</p>
                        <p><button class="button">Contact</button></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <style>
        body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}

        body, html {
            height: 100%;
            line-height: 1.8;
        }

        /* Full height image header */
        .bgimg-1 {
            background-position: top;
            background-size: cover;
            background-image: url("Images/usethishero.jpg");
            min-height: 100%;
        }

        .w3-bar .w3-button {
            padding: 16px;
        }

        #navpic{
            height: 45px;
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
            background-color: slategray;
            color: white;
            transform: rotateY(180deg);
        }
        button {
            background-color: white;
            color: black;
            border: 1px solid #555555;
            width: 200px;
            height: 40px;
        }

        button:hover {
            background-color: #555555;
            color: white;
        }
    </style>

</editor-fold>
</body>
</html>
