<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Verify OTP - MediVault</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function validateOtp() {
            let otp = document.getElementById("otp").value;
            let otpError = document.getElementById("otpError");
            if (otp.length !== 6 || isNaN(otp)) {
                otpError.innerText = "Enter a valid 6-digit OTP!";
                return false;
            }
            otpError.innerText = "";
            return true;
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Verify OTP</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <form action="verify-otp" method="post" onsubmit="return validateOtp()">
            <input type="hidden" name="email" value="${email}">
            <div class="mb-3">
                <label class="form-label">Enter OTP</label>
                <input type="text" id="otp" name="otp" class="form-control" required>
                <span id="otpError" class="text-danger"></span>
            </div>
            <button type="submit" class="btn btn-primary w-100">Verify OTP</button>
        </form>
        <a href="forgotPassword.jsp" class="d-block text-center mt-3">Resend OTP</a>
    </div>
</body>
</html>
