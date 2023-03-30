<%-- 
    Document   : logAuteur
    Created on : Feb 3, 2023, 2:51:53 PM
    Author     : caeyla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
   <form action="<%= request.getContextPath() %>/validationAuteur" method="GET" >
    <div>
      <label for="nom">nom</label>
      <input name="nom" type="text" />
    </div>
    <div>
      <label for="mdp">mdp</label>
      <input name="description" type="text" />
    </div>
     
    <div class="full-width">
      <button type="submit">Se connecter</button>
    </div>
  </form>
</html>
