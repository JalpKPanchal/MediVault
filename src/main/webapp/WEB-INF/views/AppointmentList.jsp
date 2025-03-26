<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container">
    <h2>Appointments List</h2>
    <div class="mb-3">
        <form action="/appointments" method="get" class="d-flex">
            <select name="status" class="form-select me-2" style="width: 200px;">
                <option value="">All Statuses</option>
                <c:forEach var="status" items="${statusList}">
                    <option value="${status}" ${param.status == status ? 'selected' : ''}>${status}</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary">Filter</button>
        </form>
    </div>
    <c:choose>
        <c:when test="${empty appointments}">
            <div class="alert alert-info">No appointments found.</div>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Doctor Name</th>
                        <th>Specialization</th>
                        <th>Appointment Date</th>
                        <th>Appointment Time</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="appointment" items="${appointments}">
                        <tr>
                            <td>${appointment.doctor.user.firstName} ${appointment.doctor.user.lastName}</td>
                            <td>${appointment.doctor.specialization}</td>
                            <td>${appointment.formattedAppointmentDate}</td>
                            <td>${appointment.formattedAppointmentTime}</td>
                            <td>${appointment.status}</td>
                            <td>
                                <a href="/appointments/edit/${appointment.appointmentId}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="/appointments/delete/${appointment.appointmentId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?');">Delete</a>
                                <form action="/appointments/updateStatus/${appointment.appointmentId}" method="post" style="display:inline;">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <select name="status" onchange="this.form.submit()">
                                        <c:forEach var="status" items="${statusList}">
                                            <option value="${status}" ${appointment.status == status ? 'selected' : ''}>${status}</option>
                                        </c:forEach>
                                    </select>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
    <a href="/appointments/new" class="btn btn-primary">Create New Appointment</a>
</div>