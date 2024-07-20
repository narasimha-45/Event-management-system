<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // Clear all cookies
    javax.servlet.http.Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (javax.servlet.http.Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }

    // Redirect to the login page
    response.sendRedirect("login.jsp");
%>
