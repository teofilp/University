<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Login</title>
    <style>
        html, body {
            width: 100%;
            height: 100%;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        form {
            margin-top: 20px;
            display: flex;
            flex-direction: column;
            gap: 12px;
        }
    </style>
</head>
<body style="">
<h1>Hey there, forumer :></h1>
<h2>Login</h2>
<form action="/login-servlet" method="POST">
    <input name="username" type="text" placeholder="username123">
    <input name="password" type="password" placeholder="password">
    <input type="submit" title="Login">
</form>
</body>
</html>
