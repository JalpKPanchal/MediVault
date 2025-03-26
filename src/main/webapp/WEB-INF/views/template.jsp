<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediVault - ${pageTitle}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f7fa;
        }
        .navbar-brand {
            font-weight: bold;
            color: #007bff !important;
        }
        .sidebar {
            min-height: 100vh;
            background-color: #343a40;
            padding-top: 20px;
        }
        .sidebar a {
            color: #adb5bd;
            padding: 10px 20px;
            display: block;
            text-decoration: none;
        }
        .sidebar a:hover {
            background-color: #495057;
            color: #ffffff;
        }
        .content {
            padding: 20px;
        }
        .card {
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
    <div class="d-flex">
        <!-- Sidebar -->
        <div class="sidebar">
            <a class="navbar-brand px-4" href="/dashboard">MediVault</a>
            <hr class="text-white">
            <c:if test="${loggedInUser != null}">
                <a href="/dashboard"><i class="bi bi-house-door me-2"></i> Dashboard</a>
                <c:if test="${loggedInUser.roleAsString eq 'ADMIN'}">
                    <a href="/city/list"><i class="bi bi-geo-alt me-2"></i> Manage Cities</a>
                    <a href="/state/list"><i class="bi bi-map me-2"></i> Manage States</a>
                    <a href="/clinic/list"><i class="bi bi-hospital me-2"></i> Manage Clinics</a>
                    <a href="/doctorClinic/list"><i class="bi bi-person-plus me-2"></i> Assign Doctors</a>
                </c:if>
                <c:if test="${loggedInUser.roleAsString eq 'PATIENT'}">
                    <a href="/appointments/new"><i class="bi bi-calendar-plus me-2"></i> Book Appointment</a>
                    <a href="/medicalHistory"><i class="bi bi-journal-medical me-2"></i> Medical History</a>
                </c:if>
                <c:if test="${loggedInUser.roleAsString eq 'DOCTOR'}">
                    <a href="/doctorProfile/form"><i class="bi bi-person-vcard me-2"></i> My Profile</a>
                    <a href="/appointments"><i class="bi bi-calendar-check me-2"></i> My Appointments</a>
                </c:if>
                <a href="/user/logout"><i class="bi bi-box-arrow-right me-2"></i> Logout</a>
            </c:if>
        </div>

        <!-- Main Content -->
        <div class="content flex-grow-1">
            <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
                <div class="container-fluid">
                    <span class="navbar-text">
                        <c:if test="${loggedInUser != null}">
                            Welcome, ${loggedInUser.firstName} ${loggedInUser.lastName} (${loggedInUser.roleAsString})
                        </c:if>
                    </span>
                </div>
            </nav>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
            <c:if test="${not empty message}">
                <div class="alert alert-success">${message}</div>
            </c:if>
            <jsp:include page="${contentPage}.jsp"/>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>