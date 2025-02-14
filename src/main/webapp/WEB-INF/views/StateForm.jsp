<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>State Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <h2>State Form</h2>
        <form action="/state/save" method="post">
            <div class="mb-3">
                <label for="stateName" class="form-label">State Name</label>
                <input type="text" class="form-control" id="stateName" name="stateName" value="${stateEntity.stateName}" required />
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</body>
</html>
