<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Doctor Clinic Assignment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Assign Doctor to Clinic</h2>
    <form action="/doctorClinic/save" method="post">
        <input type="hidden" name="id" value="${doctorClinicEntity.id}"/>

        <div class="mb-3">
            <label class="form-label">Doctor</label>
            <select name="user.userId" class="form-select" required>
                <option value="">Select Doctor</option>
                <c:forEach var="doctor" items="${users}">
                    <option value="${doctor.userId}" ${doctor.userId == doctorClinicEntity.user?.userId ? 'selected' : ''}>
                        ${doctor.firstName} ${doctor.lastName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Clinic</label>
            <select name="clinic.clinicId" class="form-select" required>
                <option value="">Select Clinic</option>
                <c:forEach var="clinic" items="${clinics}">
                    <option value="${clinic.clinicId}" ${clinic.clinicId == doctorClinicEntity.clinic?.clinicId ? 'selected' : ''}>
                        ${clinic.clinicName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Assign</button>
    </form>
</div>
</body>
</html>
