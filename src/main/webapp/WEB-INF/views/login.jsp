<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <style>
        .input-container {
            margin-bottom: 15px;
        }

        .password-container {
            position: relative;
            
        }

        .toggle-password {
            position: absolute;
            top: 50%;
            right: 1100px;
            transform: translateY(-50%);
            cursor: pointer;
        }
    </style>
</head>
<body>
    <form action="/Login" method="post" modelAttribute="employee" enctype="multipart/form-data">
        <div class="input-container">
            <label>Id:</label> <input type="text" name="id"><br>
        </div>
        <div class="input-container">
            <label>Name:</label> <input type="text" name="name"><br>
        </div>
        <div class="input-container">
            <label>Designation</label> <input type="text" name="designation"><br>
        </div>
        <div class="input-container">
            <label>Attachprofile:</label> <input type="file" id="fileInput" name="file" accept="image/*,.pdf"><br>
        </div>
        <div class="input-container">
            <label for="dateTime">Date and Time:</label> <input type="datetime-local" id="dateTime" name="dateTime" autocomplete="off"><br>
        </div>
        <div class="input-container">
            <label for="password-field">Password:</label>
            <div class="password-container">
                <input id="password-field" type="password" class="form-control" name="password">
                <span class="toggle-password fa fa-fw fa-eye" toggle="#password-field"></span>
            </div>
        </div>
        <button type="submit">Submit</button>
    </form>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(".toggle-password").click(function() {
            $(this).toggleClass("fa-eye fa-eye-slash");
            var input = $($(this).attr("toggle"));
            if (input.attr("type") == "password") {
                input.attr("type", "text");
            } else {
                input.attr("type", "password");
            }
        });
    </script>
</body>
</html>
