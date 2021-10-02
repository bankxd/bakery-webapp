<%-- 
    Document   : errorpage
    Created on : 28 Mar 2021, 11:15:45 PM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR PAGE</title>
    </head>
    <body>
    <center style="">
        <h1>Error</h1>
        <p>Application Interrupted</p>
        <a href="welcome.jsp"><button>Go back</button></a>   
    </center>
</body>
<style>
    button {
        background-color: burlywood;
        color: black;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 50%;
        opacity: 0.9;
        font-size: 22px;
        font-family: initial;
    }

    button:hover {
        opacity:1;
    }
</style>
</html>