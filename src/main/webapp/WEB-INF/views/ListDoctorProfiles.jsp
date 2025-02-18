<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doctor Profiles</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Doctor Profiles</h2>
    <a href="form" class="btn btn-success mb-3">Add New Doctor</a>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Name</th>
                <th>Qualification</th>
                <th>Specialization</th>
                <th>Experience</th>
                <th>Profile Picture</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="doctor" items="${doctorProfiles}">
                <tr>
                    <td>${doctor.user.firstName} ${doctor.user.lastName}</td> <!-- Display Doctor's Name -->
                    <td>${doctor.qualification}</td>
                    <td>${doctor.specialization}</td>
                    <td>${doctor.experience} years</td>
                    <td>
                        <c:if test="${not empty doctor.profilePic}">
                            <img src="${doctor.profilePic}" alt="Profile Pic" width="50"/>
                        </c:if>
                    </td>
                    <td>
                        <a href="view/${doctor.docProfileId}" class="btn btn-info btn-sm">View</a> <!-- View Button -->
                        <a href="edit/${doctor.docProfileId}" class="btn btn-warning btn-sm">Edit</a>
                        <a href="delete/${doctor.docProfileId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
