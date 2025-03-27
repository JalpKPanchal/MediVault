<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us - MediVault</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Include Header -->
    <%@ include file="fragments/header.jsp" %>

    <!-- Main Content -->
    <main class="py-5">
        <div class="container">
            <h1 class="text-center mb-4">Contact Us</h1>
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <p class="text-center">
                        Have questions or need support? Reach out to us!
                    </p>
                    <ul class="list-unstyled">
                        <li><strong>Email:</strong> support@medivault.com</li>
                        <li><strong>Phone:</strong> +1-800-555-1234</li>
                        <li><strong>Address:</strong> 123 Health St, Medical City, USA</li>
                    </ul>
                    <p class="text-center">
                        You can also follow us on social media for updates:
                    </p>
                    <div class="text-center">
                        <a href="#" class="btn btn-outline-primary me-2">Facebook</a>
                        <a href="#" class="btn btn-outline-primary me-2">Twitter</a>
                        <a href="#" class="btn btn-outline-primary">Instagram</a>
                    </div>
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