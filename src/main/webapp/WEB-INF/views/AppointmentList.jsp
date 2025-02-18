<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointments List</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Appointments List</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Doctor Name</th>
                    <th>Specialization</th>
                    <th>Appointment Date</th>
                    <th>Appointment Time</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="appointment" items="${appointments}">
                    <tr>
                        <td>${appointment.doctor.user.firstName} ${appointment.doctor.user.lastName}</td>
                        <td>${appointment.doctor.specialization}</td>
                        <td>${appointment.appointmentDate}</td>
                        <td>${appointment.appointmentTime}</td>
                        <td>
                            <a href="/appointments/edit/${appointment.appointmentId}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="/appointments/delete/${appointment.appointmentId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this appointment?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/appointments/new" class="btn btn-primary">Create New Appointment</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
