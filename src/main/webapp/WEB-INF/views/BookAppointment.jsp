<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Include Header -->
<%@ include file="fragments/header.jsp" %>

<main class="container my-5">
    <h1 class="text-center mb-5">Book an Appointment</h1>

    <!-- Display Success/Error Messages (if any) -->
    <c:if test="${not empty param.error}">
        <div class="alert alert-danger text-center" role="alert">
            <c:choose>
                <c:when test="${param.error == 'DoctorNotFound'}">
                    Doctor not found. Please select a different doctor.
                </c:when>
                <c:otherwise>
                    An error occurred. Please try again.
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
    <c:if test="${not empty param.success}">
        <div class="alert alert-success text-center" role="alert">
            Appointment booked successfully!
        </div>
    </c:if>

    <!-- Doctor Information -->
    <div class="row mb-4">
        <div class="col-md-8 offset-md-2">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="card-title">
                        Booking with 
                        <c:out value="${doctor.user != null && doctor.user.firstName != null ? doctor.user.firstName : 'Dr.'}" />
                        <c:out value="${doctor.user != null && doctor.user.lastName != null ? doctor.user.lastName : 'Unknown'}" />
                    </h3>
                    <p class="card-text">
                        <strong>Specialization:</strong> ${doctor.specialization}<br>
                        <strong>Qualification:</strong> ${doctor.qualification}<br>
                        <strong>Experience:</strong> ${doctor.experience} years
                    </p>
                </div>
            </div>
        </div>
    </div>

    <!-- Appointment Booking Form -->
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <form action="/appointments/save" method="post" class="needs-validation" novalidate>
                <!-- Hidden Field for Doctor ID -->
                <input type="hidden" name="doctorId" value="${doctor.docProfileId}">

                <!-- Appointment Date -->
                <div class="mb-3">
                    <label for="appointmentDate" class="form-label">Appointment Date</label>
                    <input type="date" class="form-control" id="appointmentDate" name="appointmentDate" required>
                    <div class="invalid-feedback">
                        Please select a date.
                    </div>
                </div>

                <!-- Appointment Time -->
                <div class="mb-3">
                    <label for="appointmentTime" class="form-label">Appointment Time</label>
                    <input type="time" class="form-control" id="appointmentTime" name="appointmentTime" required>
                    <div class="invalid-feedback">
                        Please select a time.
                    </div>
                </div>

                <!-- Comment/Description -->
                <div class="mb-3">
                    <label for="description" class="form-label">Comment (Optional)</label>
                    <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                </div>

                <!-- Submit Button -->
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Book Appointment</button>
                    <a href="/doctorProfile/doctors" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</main>

<!-- Include Footer -->
<%@ include file="fragments/footer.jsp" %>

<!-- Form Validation Script -->
<script>
    // Bootstrap form validation
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>