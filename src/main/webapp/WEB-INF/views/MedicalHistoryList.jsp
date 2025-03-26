<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediVault - Medical History</title>
</head>
<body>
    <jsp:include page="template.jsp">
        <jsp:param name="pageTitle" value="Medical History"/>
        <jsp:param name="contentPage" value="MedicalHistoryListContent"/>
    </jsp:include>
</body>
</html>

<!-- MedicalHistoryListContent.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <h2>Medical History</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Visit Date</th>
                <th>Doctor</th>
                <th>Diagnosis</th>
                <th>Treatment</th>
                <th>Notes</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${history}">
                <tr>
                    <td>${record.visitDate}</td>
                    <td>${record.doctor != null ? record.doctor.user.firstName : 'N/A'} ${record.doctor != null ? record.doctor.user.lastName : ''}</td>
                    <td>${record.diagnosis}</td>
                    <td>${record.treatment}</td>
                    <td>${record.notes}</td>
                    <td>
                        <a href="/medicalHistory/delete/${record.historyId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>