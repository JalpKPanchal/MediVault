<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doctor Profile Form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>${doctorProfileEntity.docProfileId == null ? "Add Doctor Profile" : "Edit Doctor Profile"}</h2>
    <form action="${pageContext.request.contextPath}/doctorProfile/save" method="post" enctype="multipart/form-data">
        <!-- Ensure userId is included -->
        <input type="hidden" name="userId" value="${doctorProfileEntity.user.userId}">

        <div class="mb-3">
            <label class="form-label">Doctor Name</label>
            <input type="text" class="form-control" name="doctorName" value="${doctorProfileEntity.user.firstName} ${doctorProfileEntity.user.lastName}" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label">Qualification</label>
            <input type="text" class="form-control" name="qualification" value="${doctorProfileEntity.qualification}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Specialization</label>
            <input type="text" class="form-control" name="specialization" value="${doctorProfileEntity.specialization}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Experience (Years)</label>
            <input type="number" class="form-control" name="experience" value="${doctorProfileEntity.experience}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Profile Picture</label>
            <input type="file" class="form-control" name="profileImage">
        </div>

        <!-- Adding About Section -->
        <div class="mb-3">
            <label class="form-label">About</label>
            <textarea class="form-control" name="about">${doctorProfileEntity.about}</textarea>
        </div>

        <!-- Adding Registration Number Section -->
        <div class="mb-3">
            <label class="form-label">Registration Number</label>
            <input type="text" class="form-control" name="registrationNo" value="${doctorProfileEntity.registrationNo}">
        </div>

        <button type="submit" class="btn btn-primary">Save Profile</button>
    </form>
</div>
</body>
</html>
