<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Include Header -->
<%@ include file="fragments/header.jsp" %>

<main class="container my-5">
    <h1 class="text-center mb-4">Your Appointments</h1>

    <!-- Display Success/Error Messages -->
    <c:if test="${not empty param.error}">
        <div class="alert alert-danger text-center" role="alert">
            An error occurred. Please try again.
        </div>
    </c:if>
    <c:if test="${not empty param.success}">
        <div class="alert alert-success text-center" role="alert">
            Appointment booked successfully!
        </div>
    </c:if>

    <c:if test="${not empty appointments}">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Patient Name</th>
                    <th>Doctor Name</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Status</th>
                    <th>Comment</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="appointment" items="${appointments}">
                    <tr>
                        <td>${patientNames[appointment.patientId]}</td>
                        <td>
                            <c:out value="${appointment.doctor.user != null && appointment.doctor.user.firstName != null ? appointment.doctor.user.firstName : 'Dr.'}" />
                            <c:out value="${appointment.doctor.user != null && appointment.doctor.user.lastName != null ? appointment.doctor.user.lastName : 'Unknown'}" />
                        </td>
                        <td>${appointment.formattedAppointmentDate}</td>
                        <td>${appointment.formattedAppointmentTime}</td>
                        <td>${appointment.status}</td>
                        <td><c:out value="${appointment.comment != null ? appointment.comment : 'N/A'}" /></td>
                        <td>
                            <a href="/appointments/edit/${appointment.appointmentId}" class="btn btn-sm btn-warning">Edit</a>
                            <a href="/appointments/delete/${appointment.appointmentId}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this appointment?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty appointments}">
        <p class="text-center">No appointments scheduled.</p>
    </c:if>
    <div class="text-center">
        <a href="/doctorProfile/doctors" class="btn btn-primary">Book New Appointment</a>
    </div>
</main>

<!-- Include Footer -->
<%@ include file="fragments/footer.jsp" %>