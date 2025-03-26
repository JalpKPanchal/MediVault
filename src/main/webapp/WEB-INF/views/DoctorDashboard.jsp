<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediVault - Doctor Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .dashboard-header {
            background-color: #007bff;
            color: white;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .nav-link {
            font-size: 1.1rem;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <!-- Dashboard Header -->
        <div class="dashboard-header">
            <h1>Welcome, Dr. ${user.firstName} ${user.lastName}</h1>
            <p>Role: ${user.roleAsString}</p>
        </div>

        <!-- Navigation Links -->
        <div class="mb-4">
            <a href="/doctorProfile/form" class="btn btn-primary me-2">Manage Profile</a>
            <a href="/appointments" class="btn btn-secondary me-2">View All Appointments</a>
            <a href="/user/logout" class="btn btn-danger">Logout</a>
        </div>

        <!-- Error Message -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <!-- Appointments Section -->
        <h2>Your Appointments</h2>
        <c:choose>
            <c:when test="${empty appointments}">
                <div class="alert alert-info">No appointments found.</div>
            </c:when>
            <c:otherwise>
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th>Patient ID</th>
                            <th>Appointment Date</th>
                            <th>Appointment Time</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="appointment" items="${appointments}">
                            <tr>
                                <td>${appointment.patientId}</td>
                                <td>${appointment.formattedAppointmentDate}</td>
                                <td>${appointment.formattedAppointmentTime}</td>
                                <td>${appointment.status}</td>
                                <td>
                                    <a href="/appointments/edit/${appointment.appointmentId}" class="btn btn-warning btn-sm">Edit</a>
                                    <a href="/appointments/delete/${appointment.appointmentId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?');">Delete</a>
                                    <form action="/appointments/updateStatus/${appointment.appointmentId}" method="post" style="display:inline;">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <select name="status" onchange="this.form.submit()">
                                            <c:forEach var="status" items="${statusList}">
                                                <option value="${status}" ${appointment.status == status ? 'selected' : ''}>${status}</option>
                                            </c:forEach>
                                        </select>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Bootstrap JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>