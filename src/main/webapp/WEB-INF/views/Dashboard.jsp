<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediVault - Dashboard</title>
</head>
<body>
    <jsp:include page="template.jsp">
        <jsp:param name="pageTitle" value="Dashboard"/>
        <jsp:param name="contentPage" value="DashboardContent"/>
    </jsp:include>
</body>
</html>

<!-- DashboardContent.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <div class="row">
        <c:choose>
            <c:when test="${user.roleAsString eq 'ADMIN'}">
                <div class="col-md-4">
                    <div class="card text-center p-3">
                        <i class="bi bi-people-fill text-danger" style="font-size: 2rem;"></i>
                        <h5>Manage Users</h5>
                        <a href="/users" class="btn btn-danger btn-sm">Go</a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-center p-3">
                        <i class="bi bi-gear-fill text-primary" style="font-size: 2rem;"></i>
                        <h5>System Settings</h5>
                        <a href="/settings" class="btn btn-primary btn-sm">Go</a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-center p-3">
                        <i class="bi bi-bar-chart-fill text-success" style="font-size: 2rem;"></i>
                        <h5>Reports</h5>
                        <a href="/reports" class="btn btn-success btn-sm">Go</a>
                    </div>
                </div>
            </c:when>
            <c:when test="${user.roleAsString eq 'DOCTOR'}">
                <div class="col-md-4">
                    <div class="card text-center p-3">
                        <i class="bi bi-file-medical-fill text-success" style="font-size: 2rem;"></i>
                        <h5>View Patient Records</h5>
                        <a href="/patients" class="btn btn-success btn-sm">Go</a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-center p-3">
                        <i class="bi bi-calendar-check-fill text-warning" style="font-size: 2rem;"></i>
                        <h5>Manage Appointments</h5>
                        <a href="/appointments" class="btn btn-warning btn-sm">Go</a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-center p-3">
                        <i class="bi bi-person-vcard-fill text-info" style="font-size: 2rem;"></i>
                        <h5>My Profile</h5>
                        <a href="/doctorProfile/form" class="btn btn-info btn-sm">View Profile</a>
                    </div>
                </div>
            </c:when>
            <c:when test="${user.roleAsString eq 'PATIENT'}">
                <div class="col-md-6">
                    <div class="card text-center p-3">
                        <i class="bi bi-calendar3 text-info" style="font-size: 2rem;"></i>
                        <h5>Book Appointments</h5>
                        <a href="/appointments/new" class="btn btn-info btn-sm">Go</a>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card text-center p-3">
                        <i class="bi bi-journal-medical text-primary" style="font-size: 2rem;"></i>
                        <h5>View Medical History</h5>
                        <a href="/medicalHistory" class="btn btn-primary btn-sm">Go</a>
                    </div>
                </div>
            </c:when>
            <c:when test="${user.roleAsString eq 'FDE'}">
                <div class="col-md-6">
                    <div class="card text-center p-3">
                        <i class="bi bi-calendar2-check text-warning" style="font-size: 2rem;"></i>
                        <h5>Manage Appointments</h5>
                        <a href="/appointments" class="btn btn-warning btn-sm">Go</a>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card text-center p-3">
                        <i class="bi bi-person-lines-fill text-secondary" style="font-size: 2rem;"></i>
                        <h5>Patient Queries</h5>
                        <a href="/patientQueries" class="btn btn-secondary btn-sm">Go</a>
                    </div>
                </div>
            </c:when>
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