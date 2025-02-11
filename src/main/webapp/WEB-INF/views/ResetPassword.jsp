<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Reset Password - MediVault</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function validatePassword() {
            let password = document.getElementById("newPassword").value;
            let confirmPassword = document.getElementById("confirmPassword").value;
            let passwordError = document.getElementById("passwordError");

            if (password.length < 6) {
                passwordError.innerText = "Password must be at least 6 characters!";
                return false;
            }
            if (password !== confirmPassword) {
                passwordError.innerText = "Passwords do not match!";
                return false;
            }
            passwordError.innerText = "";
            return true;
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Reset Password</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <form action="reset" method="post" onsubmit="return validatePassword()">
            <input type="hidden" name="email" value="${email}">
            <div class="mb-3">
                <label class="form-label">New Password</label>
                <input type="password" id="newPassword" name="newPassword" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Confirm Password</label>
                <input type="password" id="confirmPassword" class="form-control" required>
                <span id="passwordError" class="text-danger"></span>
            </div>
            <button type="submit" class="btn btn-success w-100">Change Password</button>
        </form>
        <a href="login.jsp" class="d-block text-center mt-3">Back to Login</a>
    </div>
</body>
</html>
