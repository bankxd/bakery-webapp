<%-- 
    Document   : login
    Created on : 17 Mar 2021, 12:08:35 PM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <!-- Login Form -->
    <editor-fold defaultstate="collapsed" desc="login">
        <div id="signinf">
            <form action="login" method="POST">
                <div class="">
                    <h2>Login!</h2>
                </div>

                <div class="w3-container">
                    <span onclick="window.open('welcome.jsp').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                    <label for="uname"><b>Email</b></label>
                    <input type="text" placeholder="Enter Username" name="em" required>
                    <input type="hidden"name="from" value="">
                    <label for="psw"><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" name="pw" required>

                    <button type="submit">Login</button>

                    <button type="button" onclick="window.open('registration.jsp').style.display = 'block'">Sign Up</button>

                    <button type="button" onclick="window.open('welcome.jsp').style.display = 'none'">Cancel</button>
                    <br/><br/><br/>
                    <!--<span class="psw">Forgot <a href="passwordrecovery.jsp" style="color: ff0000;">password?</a></span>-->
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
                    font-size: 22px;
                    font-family: initial;
                }

                form {
                    margin: 3% auto 17% auto; /* 5% from the top, 15% from the bottom and centered */
                    border: 40px solid white;
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
        </editor-fold>
    </editor-fold>
</body>
</html>
