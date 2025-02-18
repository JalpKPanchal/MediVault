<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Welcome, ${user.firstName} ${user.lastName}</h2>
        <h4>Manage Users and Appointments</h4>

        <a href="/appointments" class="btn btn-primary">View Appointments</a>
        <a href="/users" class="btn btn-secondary">Manage Users</a>
        <a href="/doctors" class="btn btn-success">Manage Doctors</a>
        <a href="/patients" class="btn btn-info">Manage Patients</a>
    </div>
</body>
</html>
