<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Doctor-Clinic Assignments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Doctor-Clinic Assignments</h2>
    <a href="/doctorClinic/new" class="btn btn-success mb-3">Assign New Doctor to Clinic</a>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Doctor Name</th>
            <th>Clinic Name</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="doctorClinic" items="${doctorClinics}">
            <tr>
                <td>${doctorClinic.id}</td>
                <td>${doctorClinic.user.firstName} ${doctorClinic.user.lastName}</td>
                <td>${doctorClinic.clinic.clinicName}</td>
                <td>
                    <a href="/doctorClinic/delete/${doctorClinic.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Are you sure?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
