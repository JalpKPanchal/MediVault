<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Include Header -->
<%@ include file="fragments/header.jsp" %>

<main class="container my-5">
    <h1 class="text-center mb-5">Doctor Profile</h1>
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="card shadow-sm">
                <c:choose>
                    <c:when test="${not empty doctorProfile.profilePic}">
                        <img src="${doctorProfile.profilePic}" class="card-img-top" alt="Doctor Profile" style="height: 300px; object-fit: cover;">
                    </c:when>
                    <c:otherwise>
                        <img src="https://via.placeholder.com/300x300?text=No+Image" class="card-img-top" alt="No Image" style="height: 300px; object-fit: cover;">
                    </c:otherwise>
                </c:choose>
                <div class="card-body">
                    <h3 class="card-title">
                        <c:out value="${doctorProfile.user != null && doctorProfile.user.firstName != null ? doctorProfile.user.firstName : 'Dr.'}" />
                        <c:out value="${doctorProfile.user != null && doctorProfile.user.lastName != null ? doctorProfile.user.lastName : 'Unknown'}" />
                    </h3>
                    <p class="card-text">
                        <strong>Specialization:</strong> ${doctorProfile.specialization}<br>
                        <strong>Qualification:</strong> ${doctorProfile.qualification}<br>
                        <strong>Experience:</strong> ${doctorProfile.experience} years<br>
                        <strong>Email:</strong> <c:out value="${doctorProfile.user != null && doctorProfile.user.email != null ? doctorProfile.user.email : 'Not available'}" /><br>
                        <strong>About:</strong> <c:out value="${doctorProfile.about != null ? doctorProfile.about : 'Not provided'}" /><br>
                        <strong>Registration No:</strong> <c:out value="${doctorProfile.registrationNo != null ? doctorProfile.registrationNo : 'Not provided'}" />
                    </p>
                    <a href="/doctorProfile/doctors" class="btn btn-secondary">Back to Doctors</a>
                    <a href="/appointments/book?doctorId=${doctorProfile.docProfileId}" class="btn btn-primary">Book Appointment</a>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- Include Footer -->
<%@ include file="fragments/footer.jsp" %>