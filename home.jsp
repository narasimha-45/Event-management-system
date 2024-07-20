<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Event Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }

        .container {
            max-width: 800px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        p {
            color: #666;
            line-height: 1.6;
        }

        
    </style>
</head>

<body>
    <div class="container">
        <% Cookie[] cookies = request.getCookies();
        String username = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("name".equals(cookie.getName())) {
                    username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    break;
                }
            }
        }
        %>

        <h1>Welcome <%= (username != null) ? username + "!" : "Guest!" %></h1>
        <p>Welcome to our Event Management System, where every event is crafted with precision and passion. Whether
            you're planning a corporate conference, a grand wedding, or a community fundraiser, our platform offers
            seamless event management solutions tailored to your needs. From intuitive event planning tools to
            comprehensive guest management and real-time analytics, we empower organizers to execute unforgettable
            events effortlessly. Discover the difference with our expert team and sophisticated technology, ensuring
            every detail is meticulously planned and flawlessly executed. Let's make your next event an extraordinary
            experience together.</p>

    </div>
</body>

</html>
