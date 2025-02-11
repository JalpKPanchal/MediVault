<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Forgot Password - MediVault</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function validateEmail() {
            let email = document.getElementById("email").value;
            let emailError = document.getElementById("emailError");
            let regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!regex.test(email)) {
                emailError.innerText = "Invalid email format!";
                return false;
            }
            emailError.innerText = "";
            return true;
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Forgot Password</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <form action="send-otp" method="post" onsubmit="return validateEmail()">
            <div class="mb-3">
                <label class="form-label">Enter your Email</label>
                <input type="email" id="email" name="email" class="form-control" required>
                <span id="emailError" class="text-danger"></span>
            </div>
            <button type="submit" class="btn btn-primary w-100">Send OTP</button>
        </form>
        <a href="login.jsp" class="d-block text-center mt-3">Back to Login</a>
    </div>
</body>
</html>
