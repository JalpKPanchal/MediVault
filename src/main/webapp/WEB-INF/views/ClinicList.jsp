<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Clinic List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Clinic List</h2>
    <a href="/clinic/new" class="btn btn-primary mb-3">Add New Clinic</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Timing</th>
            <th>Phone</th>
            <th>Rating</th>
            <th>City</th>
            <th>State</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="clinic" items="${clinics}">
            <tr>
                <td>${clinic.clinicId}</td>
                <td>${clinic.clinicName}</td>
                <td>${clinic.timing}</td>
                <td>${clinic.phoneNo}</td>
                <td>${clinic.rating}</td>
                <td>${clinic.city.cityName}</td>
                <td>${clinic.state.stateName}</td>
                <td>
                    <a href="/clinic/edit/${clinic.clinicId}" class="btn btn-warning btn-sm">Edit</a>
                    <a href="/clinic/delete/${clinic.clinicId}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
