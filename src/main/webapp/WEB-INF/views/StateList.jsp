<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>State List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <h2>State List</h2>
        <a href="/state/new" class="btn btn-primary mb-3">Add New State</a>
        <table class="table">
            <thead>
                <tr>
                    <th>State ID</th>
                    <th>State Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="state" items="${states}">
                    <tr>
                        <td>${state.stateId}</td>
                        <td>${state.stateName}</td>
                        <td>
                            <a href="/state/edit/${state.stateId}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="/state/delete/${state.stateId}" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
