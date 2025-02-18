<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Welcome, Dr. ${user.firstName} ${user.lastName}</h2>
        <h4>Your Appointments</h4>

        <table class="table">
            <thead>
                <tr>
                    <th>Patient Name</th>
                    <th>Appointment Date</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="appointment" items="${appointments}">
                    <tr>
                        <td>${appointment.patient.firstName} ${appointment.patient.lastName}</td>
                        <td>${appointment.appointmentDate}</td>
                        <td>${appointment.status}</td>
                        <td>
                            <a href="/appointments/edit/${appointment.appointmentId}" class="btn btn-primary">Edit</a>
                            <a href="/appointments/delete/${appointment.appointmentId}" class="btn btn-danger">Cancel</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>