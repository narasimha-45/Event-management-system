<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Event Management System</title>
    <link rel="stylesheet" href="register.css">
</head>
<body>
    <div class="container">
        <h1>Forgot Password</h1>
        <form name="ForgotPassword" action="UserServlet" method="post">
            <input type="hidden" name="action" value="ForgotPassword">
            <label for="email">Email:</label>
            <input type="email" name="email" placeholder="Enter your email-id" required>
            <label for="security question">Security Question</label>
            <select name="security_question" required>
                <option value="">Select Security Question</option>
                <option value="What is your school name?">What is your favorite color?</option>
                <option value="What is your favorite book?">What is your favorite book??</option>
                <option value="What is your grandfather village?">What is your grandfather village?</option>
                <option value="What is your favourite place?">What is your favourite place?</option>
            </select>
            <label for="answer">Answer:</label>
            <input type="text" name="answer" placeholder="enter your answer" required>
            <button type="submit">Get Password</button>
        </form>
        <div class="links">
            <a href="login.jsp">Login here</a>
        </div>
    </div>
    <% 
    String message = (String) request.getAttribute("message");
    if (message != null) {
        out.println("<script>alert('" + message + "');</script>");
    }
    
    String password = (String) request.getAttribute("password");
    if (password != null) {
        out.println("<script>alert('Your password is: " + password + ".login now!'); window.location.href = 'login.jsp';</script>");
    }
    %>
</body>
</html>
