<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Event - Event Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
        }

        label {
            display: block;
            margin-top: 10px;
        }

        input[type="text"],
        input[type="date"],
        input[type="time"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 5px;
            margin-top: 20px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }
    </style>
    <script>
        function setMinDate() {
            var today = new Date();
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); 
            var yyyy = today.getFullYear();

            today = yyyy + '-' + mm + '-' + dd;
            document.getElementById("date").setAttribute("min", today);
        }

        window.onload = function() {
            setMinDate();
        };

    </script>
</head>
<body>
    <div class="container">
        <h1>Add Event</h1>
        <form action="AddEvent" method="post">
            <label for="eventName">Event Name:</label>
            <input type="text" name="eventName" id="eventName" required>

            <label for="date">Date:</label>
            <input type="date" name="date" id="date" required>

            <label for="time">Time:</label>
            <input type="time" name="time" id="time" required>

            <label for="venue">Venue:</label>
            <input type="text" name="venue" id="venue" required>

            <label for="description">Description:</label>
            <textarea name="description" id="description" rows="4" required></textarea>

            <button type="submit">Add Event</button>
        </form>
    </div>
</body>
</html>
