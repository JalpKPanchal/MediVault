<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediVault - Appointment Form</title>
</head>
<body>
    <jsp:include page="template.jsp">
        <jsp:param name="pageTitle" value="Appointment Form"/>
        <jsp:param name="contentPage" value="AppointmentFormContent"/>
    </jsp:include>
</body>
</html>

<!-- AppointmentFormContent.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <h2>${appointment.appointmentId == null ? 'Create New Appointment' : 'Edit Appointment'}</h2>
    <form action="${appointment.appointmentId == null ? '/appointments/save' : '/appointments/save'}" method="POST" novalidate>
        <div class="mb-3">
            <label for="doctor" class="form-label">Select Doctor</label>
            <select name="doctor.docProfileId" id="doctor" class="form-control" required>
                <option value="">Select a Doctor</option>
                <c:forEach var="doctor" items="${doctors}">
                    <option value="${doctor.docProfileId}" ${appointment.doctor != null && appointment.doctor.docProfileId == doctor.docProfileId ? 'selected' : ''}>
                        ${doctor.user.firstName} ${doctor.user.lastName} (${doctor.specialization})
                    </option>
                </c:forEach>
            </select>
            <div class="invalid-feedback">Please select a doctor.</div>
        </div>
        <div class="mb-3">
            <label for="appointmentDate" class="form-label">Appointment Date</label>
            <input type="date" name="appointmentDate" id="appointmentDate" class="form-control" value="${appointment.appointmentDate != null ? appointment.appointmentDate : ''}" required>
            <div class="invalid-feedback">Please select a date.</div>
        </div>
        <div class="mb-3">
            <label for="appointmentTime" class="form-label">Appointment Time</label>
            <input type="time" name="appointmentTime" id="appointmentTime" class="form-control" value="${appointment.appointmentTime != null ? appointment.appointmentTime : ''}" required>
            <div class="invalid-feedback">Please select a time.</div>
        </div>
        <div class="mb-3">
            <label for="comment" class="form-label">Comment</label>
            <textarea name="comment" id="comment" class="form-control">${appointment.comment != null ? appointment.comment : ''}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">Save Appointment</button>
        <a href="/appointments" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<script>
    (function () {
        'use strict';
        const forms = document.querySelectorAll('form');
        forms.forEach(form => {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>