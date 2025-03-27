<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Include Header -->
<%@ include file="fragments/header.jsp" %>

<main class="container my-5">
    <h1 class="text-center mb-5">Our Doctors</h1>

    <!-- Display Success/Error Messages (if any) -->
    <c:if test="${not empty param.error}">
        <div class="alert alert-danger text-center" role="alert">
            <c:choose>
                <c:when test="${param.error == 'ProfileNotFound'}">
                    Doctor profile not found.
                </c:when>
                <c:otherwise>
                    An error occurred. Please try again.
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
    <c:if test="${not empty param.success}">
        <div class="alert alert-success text-center" role="alert">
            Action completed successfully!
        </div>
    </c:if>

    <!-- Doctors List -->
    <c:choose>
        <c:when test="${not empty doctorProfiles}">
            <div class="row">
                <c:forEach var="doctor" items="${doctorProfiles}">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 shadow-sm">
                            <!-- Doctor Profile Picture -->
                            <c:choose>
                                <c:when test="${not empty doctor.profilePic}">
                                    <img src="${doctor.profilePic}" class="card-img-top" alt="Doctor Profile" style="height: 200px; object-fit: cover;">
                                </c:when>
                                <c:otherwise>
                                    <img src="https://via.placeholder.com/200x200?text=No+Image" class="card-img-top" alt="No Image" style="height: 200px; object-fit: cover;">
                                </c:otherwise>
                            </c:choose>
                            <div class="card-body">
                                <!-- Doctor Name (from UserEntity) -->
                                <h5 class="card-title">
                                    <c:out value="${doctor.user != null && doctor.user.firstName != null ? doctor.user.firstName : 'Dr.'}" />
                                    <c:out value="${doctor.user != null && doctor.user.lastName != null ? doctor.user.lastName : 'Unknown'}" />
                                </h5>
                                <p class="card-text">
                                    <strong>Specialization:</strong> ${doctor.specialization}<br>
                                    <strong>Qualification:</strong> ${doctor.qualification}<br>
                                    <strong>Experience:</strong> ${doctor.experience} years
                                </p>
                                <div class="d-flex justify-content-between">
                                    <a href="/doctorProfile/view/${doctor.docProfileId}" class="btn btn-info btn-sm">View Profile</a>
                                    <a href="/appointments/book?doctorId=${doctor.docProfileId}" class="btn btn-primary btn-sm">Book Appointment</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info text-center" role="alert">
                No doctors are available at the moment. Please check back later.
            </div>
        </c:otherwise>
    </c:choose>
</main>

<!-- Include Footer -->
<%@ include file="fragments/footer.jsp" %>