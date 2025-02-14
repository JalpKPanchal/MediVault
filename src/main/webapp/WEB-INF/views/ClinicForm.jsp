<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Clinic Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Clinic Form</h2>
    <form action="/clinic/save" method="post">

        <input type="hidden" name="clinicId" value="${clinicEntity.clinicId}"/>

        <div class="mb-3">
            <label class="form-label">Clinic Name</label>
            <input type="text" class="form-control" name="clinicName" value="${clinicEntity.clinicName}" required/>
        </div>

        <div class="mb-3">
            <label class="form-label">Timing</label>
            <input type="text" class="form-control" name="timing" value="${clinicEntity.timing}" required/>
        </div>

        <div class="mb-3">
            <label class="form-label">Phone Number</label>
            <input type="number" class="form-control" name="phoneNo" value="${clinicEntity.phoneNo}" required/>
        </div>

        <div class="mb-3">
            <label class="form-label">Rating</label>
            <input type="number" step="0.1" class="form-control" name="rating" value="${clinicEntity.rating}" required/>
        </div>

        <div class="mb-3">
            <label class="form-label">Address</label>
            <textarea class="form-control" name="address" required>${clinicEntity.address}</textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">About</label>
            <textarea class="form-control" name="about">${clinicEntity.about}</textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Latitude</label>
            <input type="text" class="form-control" name="lat" value="${clinicEntity.lat}"/>
        </div>

        <div class="mb-3">
            <label class="form-label">Longitude</label>
            <input type="text" class="form-control" name="log" value="${clinicEntity.log}"/>
        </div>

        <div class="mb-3">
            <label class="form-label">Pincode</label>
            <input type="number" class="form-control" name="pincode" value="${clinicEntity.pincode}"/>
        </div>

        <div class="mb-3">
            <label class="form-label">City</label>
            <select name="city.cityId" class="form-select" required>
                <option value="">Select City</option>
                <c:forEach var="city" items="${cities}">
                    <option value="${city.cityId}" ${clinicEntity.city != null && city.cityId == clinicEntity.city.cityId ? 'selected' : ''}>
                        ${city.cityName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">State</label>
            <select name="state.stateId" class="form-select" required>
                <option value="">Select State</option>
                <c:forEach var="state" items="${states}">
                    <option value="${state.stateId}" ${clinicEntity.state != null && state.stateId == clinicEntity.state.stateId ? 'selected' : ''}>
                        ${state.stateName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Save Clinic</button>
    </form>
</div>
</body>
</html>
