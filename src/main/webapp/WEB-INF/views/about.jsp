<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - MediVault</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Include Header -->
    <%@ include file="fragments/header.jsp" %>

    <!-- Main Content -->
    <main class="py-5">
        <div class="container">
            <h1 class="text-center mb-4">About Us</h1>
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <p class="lead">
                        MediVault is a Smart Hospital Management system designed to streamline healthcare services for patients, doctors, and administrators.
                    </p>
                    <p>
                        Our mission is to provide a seamless platform for managing appointments, accessing patient records, and connecting with healthcare professionals. With MediVault, you can book appointments, view doctor profiles, and manage your medical history with ease.
                    </p>
                    <p>
                        Founded in 2025, MediVault is committed to improving healthcare accessibility and efficiency through technology.
                    </p>
                </div>
            </div>
        </div>
    </main>

    <!-- Include Footer -->
    <%@ include file="fragments/footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>