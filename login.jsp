<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Event Management System</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>
    <div class="container">
        <h1>Event Management System</h1>
        <form action="UserServlet" method="post">
            <input type="hidden" name="action" value="login">
            <label for="email">Email:</label>
            <input type="email" name="email" required>
            <label for="password">Password:</label>
            <input type="password" name="password" required>
            <button type="submit">Login</button>
        </form>
        <div class="links">
            <a href="forgotPassword.jsp">Forgot password?</a>
            <a href="register.jsp">Register now</a>
        </div>
    </div>
</body>
</html>
