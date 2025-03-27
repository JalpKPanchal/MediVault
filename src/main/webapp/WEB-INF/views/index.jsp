<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediVault - Smart Hospital Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS (optional, if you have a custom stylesheet) -->
    <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet">
</head>
<body>
    <!-- Header Section -->
    <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container">
                <a class="navbar-brand" href="/">MediVault</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="/">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/doctors">Doctors</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/appointments">Appointments</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/about">About Us</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/contact">Contact</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-light ms-2" href="/user/login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-outline-light ms-2" href="/user/signup">Register</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <!-- Main Content Section -->
    <main>
        <!-- Hero Section -->
        <section class="bg-light py-5">
            <div class="container text-center">
                <h1 class="display-4">Welcome to MediVault</h1>
                <p class="lead">Your Smart Hospital Management Solution</p>
                <p>Manage appointments, access patient records, and connect with doctors seamlessly.</p>
                <a href="/register" class="btn btn-primary btn-lg">Get Started</a>
            </div>
        </section>

        <!-- Features Section -->
        <section class="py-5">
            <div class="container">
                <h2 class="text-center mb-4">Our Features</h2>
                <div class="row">
                    <div class="col-md-4 text-center">
                        <img src="https://via.placeholder.com/150" alt="Appointment Booking" class="img-fluid mb-3">
                        <h3>Appointment Booking</h3>
                        <p>Schedule appointments with doctors easily and manage your visits.</p>
                    </div>
                    <div class="col-md-4 text-center">
                        <img src="https://via.placeholder.com/150" alt="Patient Records" class="img-fluid mb-3">
                        <h3>Patient Records</h3>
                        <p>Securely store and access patient medical records anytime, anywhere.</p>
                    </div>
                    <div class="col-md-4 text-center">
                        <img src="https://via.placeholder.com/150" alt="Doctor Directory" class="img-fluid mb-3">
                        <h3>Doctor Directory</h3>
                        <p>Find and connect with the best doctors for your needs.</p>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <!-- Footer Section -->
    <footer class="bg-dark text-white py-4">
        <div class="container text-center">
            <p>&copy; 2025 MediVault. All rights reserved.</p>
            <p>
                <a href="/about" class="text-white">About Us</a> |
                <a href="/contact" class="text-white">Contact</a> |
                <a href="/privacy" class="text-white">Privacy Policy</a>
            </p>
        </div>
    </footer>

    <!-- Bootstrap JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>