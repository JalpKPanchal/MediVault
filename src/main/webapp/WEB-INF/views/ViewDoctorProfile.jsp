<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doctor Profile Details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Doctor Profile Details</h2>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">${doctorProfile.user.firstName} ${doctorProfile.user.lastName}</h5>
            <p><strong>Qualification:</strong> ${doctorProfile.qualification}</p>
            <p><strong>Specialization:</strong> ${doctorProfile.specialization}</p>
            <p><strong>Experience:</strong> ${doctorProfile.experience} years</p>
            <p><strong>About:</strong> ${doctorProfile.about}</p>
            <p><strong>Registration No:</strong> ${doctorProfile.registrationNo}</p>

            <c:if test="${not empty doctorProfile.profilePic}">
                <img src="${doctorProfile.profilePic}" alt="Profile Pic" width="150"/>
            </c:if>
        </div>
    </div>

    <a href="/doctorProfile/list" class="btn btn-secondary mt-3">Back to List</a>
</div>
</body>
</html>
