<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Employee</title>
</head>
<body>
    <h1>Edit Employee</h1>
    <form action="/update" method="POST">
        <input type="hidden" name="id" value="${employee.id}" />
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${employee.name}" required /><br><br>
        <label for="designation">Designation:</label>
        <input type="text" id="designation" name="designation" value="${employee.designation}" required /><br><br>
        <input type="submit" value="Update" />
    </form>
    <br>
    <a href="/users">Back to Employee List</a>
</body>


</html>