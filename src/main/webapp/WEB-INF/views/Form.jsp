<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Doctor Profile Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <h2>Doctor Profile Form</h2>
        <form action="/doctorProfile/save" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="qualification" class="form-label">Qualification</label>
                <input type="text" class="form-control" id="qualification" name="qualification" value="${doctorProfileEntity.qualification}" required />
            </div>
            <div class="mb-3">
                <label for="specialization" class="form-label">Specialization</label>
                <input type="text" class="form-control" id="specialization" name="specialization" value="${doctorProfileEntity.specialization}" required />
            </div>
            <div class="mb-3">
                <label for="experience" class="form-label">Experience</label>
                <input type="number" class="form-control" id="experience" name="experience" value="${doctorProfileEntity.experience}" required />
            </div>
            <div class="mb-3">
                <label for="profilePic" class="form-label">Profile Picture</label>
                <input type="file" class="form-control" id="profilePic" name="profilePic" />
            </div>
            <div class="mb-3">
                <label for="about" class="form-label">About</label>
                <textarea class="form-control" id="about" name="about">${doctorProfileEntity.about}</textarea>
            </div>
            <div class="mb-3">
                <label for="registrationNo" class="form-label">Registration No</label>
                <input type="text" class="form-control" id="registrationNo" name="registrationNo" value="${doctorProfileEntity.registrationNo}" />
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</body>
</html>
