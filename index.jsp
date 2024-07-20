<html>

<head>
    <title>EVENT MANAGEMENET SYSTEM</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        header {
            background-color: #333;
            color: white;
            padding: 10px;
            text-align: center;
            background-image: url('https://img.freepik.com/free-vector/realistic-bokeh-background_23-2149321463.jpg?w=996&t=st=1719334513~exp=1719335113~hmac=ba204b61cef49701546dd92952f608c901530e742dca64ab70dfda138a8440de');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }

        .container {
            display: flex;
            flex: 1;
            overflow: hidden;
        }

        nav {
            background-color: #f0f0f0;
            width: 15%;
            padding: 10px;
            box-sizing: border-box;
            overflow-y: auto;
        }

        main {
            flex: 1;
            padding: 20px;
            box-sizing: border-box;
            overflow-y: auto;
        }

        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px;
        }
        a{
            text-decoration: none;
            font-weight: bold;
            
        }
        ul{
            padding-bottom: 30px;
        }
    </style>
</head>

<body>
    <header>
        <h1 style="text-align: center;">EVENT MANAGEMENET SYSTEM</h1>
    </header>
    <div class="container">
        <nav>
            <ul><a href="home.jsp" target="content" >HOME</a></ul>
            <ul><a href="AllEvents" target="content" >ALL EVENTS</a></ul>
            <ul><a href="RegisteredEvents" target="content" >REGISTERED EVENTS</a></ul>
            <ul><a href="addEvents.jsp" target="content" >ADD EVENTS</a></ul>
            <ul><a href="MyEvents" target="content" >MY EVENTS</a></ul>
            <ul><a href="logout.jsp">LOGOUT</a></ul>
        </nav>
        <main>
            <iframe src="home.jsp" name="content" frameborder="0"
                style="width: 100%; height: 100%; border: none;"></iframe>
        </main>
    </div>

</body>

</html>