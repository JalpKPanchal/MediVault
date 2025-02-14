<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Doctor Profiles</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <h2>Doctor Profiles</h2>
        <a href="/doctorProfile/new" class="btn btn-primary">Add New Profile</a>
        <table class="table">
            <thead>
                <tr>
                    <th>Profile ID</th>
                    <th>Qualification</th>
                    <th>Specialization</th>
                    <th>Experience</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="doctor" items="${doctorProfiles}">
                    <tr>
                        <td>${doctor.docProfileId}</td>
                        <td>${doctor.qualification}</td>
                        <td>${doctor.specialization}</td>
                        <td>${doctor.experience}</td>
                        <td>
                            <a href="/doctorProfile/edit/${doctor.docProfileId}" class="btn btn-warning">Edit</a>
                            <a href="/doctorProfile/delete/${doctor.docProfileId}" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
