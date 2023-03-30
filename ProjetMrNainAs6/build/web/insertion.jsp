<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="entity.*,java.util.*"%>
<%
    String ray=(String) request.getAttribute("tay");
    String roa=(String) request.getAttribute("class");
        ArrayList<Type>ra=(ArrayList<Type>)request.getAttribute("listeType");
    int idutil=(Integer)request.getAttribute("idutil");

%>
<html lang="en" style="width: 80%;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>login</title>
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.min.css"/>">
</head>

<body>
    <form action="<%= request.getContextPath() %>/insertion" method="POST" enctype="multipart/form-data" style="width: 80vw;height: 90vh;margin-bottom: 5vh;margin-left: 10vw;background-color: #353b41;border-width: 10px;border-radius: 19px;color: rgb(0, 25, 0);">
        <header></header>
        <h1 style="width: 80%;margin-left: 10%;margin-top: 10%;">Nouvel Element</h1><label class="form-label" style="width: 20%;margin-top: 0px;margin-left: 20%;">Titre</label>
        <input class="form-control" name="titre" type="text" style="width: 80%;margin-top: 1%;height: 4vh;margin-left: 10%;"><label class="form-label" style="width: 39%;margin-top: 0px;margin-left: 20%;">Corps de texte</label>
        <input class="form-control" name="description" style="width: 80%;height: 17%;margin-left: 10%;"><label class="form-label" style="width: 51%;margin-top: 0%;margin-left: 20%;">Date 1</label>
        <input class="form-control" name="un" type="datetime-local" style="width: 80%;margin-left: 10%;margin-top: 0%;"><label class="form-label" style="width: 51%;margin-top: 0%;margin-left: 20%;">Date 2</label>
        <input class="form-control" name="deux" type="datetime-local" style="width: 80%;margin-left: 10%;margin-top: 0%;">
        <input class="form-control"  name="file" type="file" style="width: 80%;margin-left: 10%;margin-top: 1vh;">
        <select class="form-select" style="width: 80%;margin-left: 10%;margin-top: 3vh;" name="idtype">
            <%for (Type elem : ra) {%>
                <option value="<%=elem.getIdtype()%>"><%=elem.getIntitule()%></option>
            <%}%>
        </select>
        <p><input type="hidden" name="class" value="<%=roa%>"></p>
        <p><input type="hidden" name="idauteur" value="<%=idutil%>"></p>
        <button class="btn btn-primary btn-success" type="submit" style="width: 80%;margin-left: 10%;margin-top: 3%;">Valider</button>
    
    </form>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>