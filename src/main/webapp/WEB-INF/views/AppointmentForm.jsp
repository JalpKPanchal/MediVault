<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointment Form</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>${appointment.appointmentId == null ? 'Create New Appointment' : 'Edit Appointment'}</h2>
        
        <form action="${appointment.appointmentId == null ? '/appointments/save' : '/appointments/save'}" method="POST">
            <div class="form-group">
                <label for="doctor">Select Doctor</label>
                <select name="doctorId" id="doctor" class="form-control" required>
                    <c:forEach var="doctor" items="${doctors}">
                        <option value="${doctor.docProfileId}" ${appointment.doctor != null && appointment.doctor.docProfileId == doctor.docProfileId ? 'selected' : ''}>
                            ${doctor.user.firstName} ${doctor.user.lastName} (${doctor.specialization})
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="appointmentDate">Appointment Date</label>
                <input type="date" name="appointmentDate" id="appointmentDate" class="form-control" value="${appointment.appointmentDate != null ? appointment.appointmentDate : ''}" required>
            </div>
            <div class="form-group">
                <label for="appointmentTime">Appointment Time</label>
                <input type="time" name="appointmentTime" id="appointmentTime" class="form-control" value="${appointment.appointmentTime != null ? appointment.appointmentTime : ''}" required>
            </div>
            <div class="form-group">
                <label for="comment">Comment</label>
                <textarea name="comment" id="comment" class="form-control">${appointment.comment != null ? appointment.comment : ''}</textarea>
            </div>
            <button type="submit" class="btn btn-primary">Save Appointment</button>
            <a href="/appointments" class="btn btn-secondary">Cancel</a>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
