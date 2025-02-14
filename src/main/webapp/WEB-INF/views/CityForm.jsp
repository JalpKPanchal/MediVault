<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>City Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <h2>City Form</h2>
        <form action="/city/save" method="post">
            <div class="mb-3">
                <label for="cityName" class="form-label">City Name</label>
                <input type="text" class="form-control" id="cityName" name="cityName" value="${cityEntity.cityName}" required />
            </div>
            <div class="mb-3">
                <label for="state" class="form-label">State</label>
                <select class="form-control" id="state" name="state.stateId" required>
                    <c:forEach var="state" items="${states}">
                        <option value="${state.stateId}" ${state.stateId == cityEntity.state.stateId ? 'selected' : ''}>
                            ${state.stateName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</body>
</html>
