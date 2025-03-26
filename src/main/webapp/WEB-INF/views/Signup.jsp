<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediVault - Signup</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .signup-container {
            max-width: 500px;
            margin: 50px auto;
        }
        .card {
            border-radius: 15px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body class="bg-light">
    <div class="signup-container">
        <div class="card p-4">
            <h2 class="text-center mb-4">MediVault Signup</h2>
            <form action="signup" method="post" novalidate>
                <div class="mb-3">
                    <label class="form-label">First Name</label>
                    <input type="text" class="form-control" name="firstName" required>
                    <div class="invalid-feedback">First name is required.</div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Last Name</label>
                    <input type="text" class="form-control" name="lastName" required>
                    <div class="invalid-feedback">Last name is required.</div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" required>
                    <div class="invalid-feedback">Please enter a valid email.</div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" class="form-control" name="password" required minlength="6">
                    <div class="invalid-feedback">Password must be at least 6 characters.</div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Contact Number</label>
                    <input type="text" class="form-control" name="contactNum" required pattern="[0-9]{10}">
                    <div class="invalid-feedback">Enter a valid 10-digit contact number.</div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Born Year</label>
                    <input type="number" class="form-control" name="bornYear" required min="1900" max="2025">
                    <div class="invalid-feedback">Enter a valid year between 1900 and 2025.</div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Gender</label>
                    <select class="form-select" name="gender" required>
                        <option value="">Select Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                    <div class="invalid-feedback">Please select a gender.</div>
                </div>
                <button type="submit" class="btn btn-primary w-100">Signup</button>
            </form>
            <p class="text-center mt-3">
                Already have an account? <a href="login">Login</a>
            </p>
        </div>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>