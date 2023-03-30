<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>loginveritablebsdesign</title>
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.min.css"/>">
</head>

<body>
    <form style="width: 80vw;height: 80vh;margin-top: 10vh;margin-left: 10vw;background: #353b41;border-width: 10px;border-radius: 6px;" action="<%= request.getContextPath() %>/validationAdmin" method="GET">
        <header></header>
        <h1 style="width: 80%;margin-left: 10%;margin-top: 0%;color: rgb(225,225,225);">Connectez-vous</h1><label class="form-label" style="width: 20%;margin-top: 0%;margin-left: 20%;color: rgb(247,247,247);">Pseudo</label><input class="form-control" type="text" style="width: 80%;margin-top: 1%;height: 4vh;margin-left: 10%;" name="nom"><label class="form-label" style="width: 39%;margin-top: 54px;margin-left: 20%;color: rgb(232,239,245);">Mot de passe</label><input class="form-control" type="password" name="motdepasse" style="width: 80%;margin-left: 10%;"><button class="btn btn-primary btn-success" type="submit" style="width: 80%;margin-left: 10%;margin-top: 10%;">Button</button>
    </form>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>