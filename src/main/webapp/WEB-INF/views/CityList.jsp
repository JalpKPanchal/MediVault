<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>City List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <h2>City List</h2>
        <a href="/city/new" class="btn btn-primary mb-3">Add New City</a>
        <table class="table">
            <thead>
                <tr>
                    <th>City ID</th>
                    <th>City Name</th>
                    <th>State</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="city" items="${cities}">
                    <tr>
                        <td>${city.cityId}</td>
                        <td>${city.cityName}</td>
                        <td>${city.state.stateName}</td>
                        <td>
                            <a href="/city/edit/${city.cityId}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="/city/delete/${city.cityId}" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
