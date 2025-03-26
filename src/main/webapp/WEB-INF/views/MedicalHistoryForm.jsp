<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediVault - Add Medical History</title>
</head>
<body>
    <jsp:include page="template.jsp">
        <jsp:param name="pageTitle" value="Add Medical History"/>
        <jsp:param name="contentPage" value="MedicalHistoryFormContent"/>
    </jsp:include>
</body>
</html>

<!-- MedicalHistoryFormContent.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <h2>Add Medical History</h2>
    <form action="/medicalHistory/save" method="post" novalidate>
        <div class="mb-3">
            <label for="patient" class="form-label">Select Patient</label>
            <select name="patient.userId" id="patient" class="form-control" required>
                <option value="">Select a Patient</option>
                <c:forEach var="patient" items="${patients}">
                    <option value="${patient.userId}">${patient.firstName} ${patient.lastName}</option>
                </c:forEach>
            </select>
            <div class="invalid-feedback">Please select a patient.</div>
        </div>
        <div class="mb-3">
            <label for="visitDate" class="form-label">Visit Date</label>
            <input type="date" name="visitDate" id="visitDate" class="form-control" required>
            <div class="invalid-feedback">Please select a date.</div>
        </div>
        <div class="mb-3">
            <label for="diagnosis" class="form-label">Diagnosis</label>
            <input type="text" name="diagnosis" id="diagnosis" class="form-control" required>
            <div class="invalid-feedback">Diagnosis is required.</div>
        </div>
        <div class="mb-3">
            <label for="treatment" class="form-label">Treatment</label>
            <input type="text" name="treatment" id="treatment" class="form-control" required>
            <div class="invalid-feedback">Treatment is required.</div>
        </div>
        <div class="mb-3">
            <label for="notes" class="form-label">Notes</label>
            <textarea name="notes" id="notes" class="form-control"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="/medicalHistory" class="btn btn-secondary">Cancel</a>
    </form>
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