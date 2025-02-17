<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Appointments - MediVault</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="d-flex justify-content-between mb-3">
        <h3>Appointments</h3>
        <a href="${pageContext.request.contextPath}/appointments/new" class="btn btn-primary">New Appointment</a>
    </div>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>#</th>
                <th>Patient ID</th>
                <th>Doctor ID</th>
                <th>Status</th>
                <th>Date</th>
                <th>Time</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${appointments}" var="a" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${a.patientId}</td>
                    <td>${a.doctorId}</td>
                    <td>${a.status}</td>
                    <td>${a.appointmentDate}</td>
                    <td>${a.appointmentTime}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/appointments/edit/${a.appointmentId}" class="btn btn-sm btn-warning">Edit</a>
                        <a href="${pageContext.request.contextPath}/appointments/delete/${a.appointmentId}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty appointments}">
                <tr>
                    <td colspan="7" class="text-center text-muted">No Appointments Found</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
