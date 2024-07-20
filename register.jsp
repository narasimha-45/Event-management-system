<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Event Management System</title>
    <link rel="stylesheet" href="register.css">
    <script src="register.js"></script>
</head>
<body>
    <div class="container">
        <h1>Register</h1>
        <form name="registerForm" action="UserServlet" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="action" value="register">
            
            <label for="name">Name:</label>
            <input type="text" name="name" required>
            
            <label for="gender">Gender:</label>
            <select name="gender" required>
                <option value="">Select Gender</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
            </select>
            
            <label for="email">Email:</label>
            <input type="email" name="email" required>
            
            <label for="phone">Phone Number:</label>
            <input type="text" name="phone" required>
            
            <label for="password">Password:</label>
            <input type="password" name="password" required>
            
            <label for="confirm_password">Confirm Password:</label>
            <input type="password" name="confirm_password" required>
            
            <label for="security_question">Security Question:</label>
            <select name="security_question" required>
                <option value="">Select Security Question</option>
                <option value="What is your school name?">What is your school name?</option>
                <option value="What is your favorite book?">What is your favorite book?</option>
                <option value="What is your grandfather village?">What is your grandfather village?</option>
                <option value="What is your favourite place?">What is your favourite place?</option>
            </select>
            
            <label for="answer">Answer:</label>
            <input type="text" name="answer" required>
            
            <button type="submit">Register</button>
        </form>
        
        <div class="links">
            <a href="login.jsp">Already Registered? Login here</a>
        </div>
        
        <% 
            
            String message = (String) request.getAttribute("message");
            if (message != null) {
                out.println("<script>alert('" + message + "');</script>");
            }
            
            String successMessage = (String) request.getAttribute("success");
            if (successMessage != null) {
                out.println("<script>alert('Registered Successfully. Now you can login.'); window.location.href = 'login.jsp';</script>");
            }
        %>
    </div>
</body>
</html>
