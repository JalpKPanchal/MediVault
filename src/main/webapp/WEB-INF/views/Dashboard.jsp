<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard</title>

    <!-- Bootstrap 5 & Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .dashboard-container {
            max-width: 1000px;
            margin: 50px auto;
            padding: 20px;
            background: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .dashboard-header {
            text-align: center;
            margin-bottom: 30px;
        }
        .dashboard-card {
            border: none;
            border-radius: 10px;
            transition: all 0.3s ease-in-out;
        }
        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }
        .icon {
            font-size: 2rem;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="dashboard-container">
        <div class="dashboard-header">
            <h2>Welcome, <span class="text-primary">${user.firstName} ${user.lastName}</span></h2>
            <p class="text-muted">Your role: <strong>${user.roleAsString}</strong></p>
        </div>

        <div class="row">
            <c:choose>
                <%-- ADMIN Dashboard --%>
                <c:when test="${user.roleAsString eq 'ADMIN'}">
                    <div class="col-md-4">
                        <div class="card dashboard-card text-center p-3">
                            <i class="bi bi-people-fill icon text-danger"></i>
                            <h5>Manage Users</h5>
                            <a href="ManageUsers" class="btn btn-danger btn-sm">Go</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card dashboard-card text-center p-3">
                            <i class="bi bi-gear-fill icon text-primary"></i>
                            <h5>System Settings</h5>
                            <a href="Settings" class="btn btn-primary btn-sm">Go</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card dashboard-card text-center p-3">
                            <i class="bi bi-bar-chart-fill icon text-success"></i>
                            <h5>Reports</h5>
                            <a href="Reports" class="btn btn-success btn-sm">Go</a>
                        </div>
                    </div>
                </c:when>

                <%-- DOCTOR Dashboard --%>
                <c:when test="${user.roleAsString eq 'DOCTOR'}">
                    <div class="col-md-4">
                        <div class="card dashboard-card text-center p-3">
                            <i class="bi bi-file-medical-fill icon text-success"></i>
                            <h5>View Patient Records</h5>
                            <a href="ViewPatients" class="btn btn-success btn-sm">Go</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card dashboard-card text-center p-3">
                            <i class="bi bi-calendar-check-fill icon text-warning"></i>
                            <h5>Manage Appointments</h5>
                            <a href="Appointments" class="btn btn-warning btn-sm">Go</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card dashboard-card text-center p-3">
                            <i class="bi bi-person-vcard-fill icon text-info"></i>
                            <h5>My Profile</h5>
                            <a href="DoctorProfile" class="btn btn-info btn-sm">View Profile</a>
                        </div>
                    </div>
                </c:when>

                <%-- PATIENT Dashboard --%>
                <c:when test="${user.roleAsString eq 'PATIENT'}">
                    <div class="col-md-6">
                        <div class="card dashboard-card text-center p-3">
                            <i class="bi bi-calendar3 icon text-info"></i>
                            <h5>Book Appointments</h5>
                            <a href="BookAppointment" class="btn btn-info btn-sm">Go</a>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card dashboard-card text-center p-3">
                            <i class="bi bi-journal-medical icon text-primary"></i>
                            <h5>View Medical History</h5>
                            <a href="MedicalHistory" class="btn btn-primary btn-sm">Go</a>
                        </div>
                    </div>
                </c:when>

                <%-- FRONT DESK EXECUTIVE Dashboard --%>
                <c:when test="${user.roleAsString eq 'FDE'}">
                    <div class="col-md-6">
                        <div class="card dashboard-card text-center p-3">
                            <i class="bi bi-calendar2-check icon text-warning"></i>
                            <h5>Manage Appointments</h5>
                            <a href="ManageAppointments" class="btn btn-warning btn-sm">Go</a>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card dashboard-card text-center p-3">
                            <i class="bi bi-person-lines-fill icon text-secondary"></i>
                            <h5>Patient Queries</h5>
                            <a href="PatientQueries" class="btn btn-secondary btn-sm">Go</a>
                        </div>
                    </div>
                </c:when>

                <%-- Default case --%>
                <c:otherwise>
                    <div class="col-12">
                        <div class="alert alert-danger text-center">
                            <i class="bi bi-exclamation-circle-fill"></i> Access Restricted.
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<!-- Bootstrap 5 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
