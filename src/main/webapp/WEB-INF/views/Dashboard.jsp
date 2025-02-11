<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard</title>
    
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            background-color: #f8f9fa;
        }
        .dashboard-container {
            max-width: 900px;
            margin: 50px auto;
            padding: 20px;
            background: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .dashboard-header {
            text-align: center;
            margin-bottom: 20px;
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
    </style>
</head>
<body>

<div class="container">
    <div class="dashboard-container">
        <div class="dashboard-header">
            <h2>Welcome, <span class="text-primary">${user.firstName} ${user.lastName}</span></h2>
        </div>

        <div class="card dashboard-card">
            <div class="card-body">
                <c:choose>
                    <c:when test="${user.roleAsString eq 'ADMIN'}">
                        <h4 class="card-title text-danger">Admin Dashboard</h4>
                        <p class="card-text">Manage users, settings, and reports.</p>
                    </c:when>

                    <c:when test="${user.roleAsString eq 'DOCTOR'}">
                        <h4 class="card-title text-success">Doctor Dashboard</h4>
                        <p class="card-text">View patient records and appointments.</p>
                    </c:when>

                    <c:when test="${user.roleAsString eq 'PATIENT'}">
                        <h4 class="card-title text-info">Patient Dashboard</h4>
                        <p class="card-text">Book appointments and view medical history.</p>
                    </c:when>

                    <c:when test="${user.roleAsString eq 'FDE'}">
                        <h4 class="card-title text-warning">Front Desk Executive Dashboard</h4>
                        <p class="card-text">Schedule and manage patient appointments.</p>
                    </c:when>

                    <c:otherwise>
                        <h4 class="card-title text-muted">Unknown Role</h4>
                        <p class="card-text">Access restricted.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap 5 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
