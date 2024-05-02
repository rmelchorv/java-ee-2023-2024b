<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es-mx">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport' content='width=device-width, initial-scale=1.0">
    <title>Hello Servlet</title>
    <link rel="icon" href="assets/img/favicon.png">
  </head>
  <body>
    <h2>Hello Servlet</h2>
    <h3>Informática, (608) – UNISTMO</h3>

    <form method="get" action="HelloServlet">
      <label for="firstname">First name:</label><br>
      <input type="text" name="firstname" placeholder="Enter your fisrt name" autofocus><br>
      <label for="lastname">Last name:</label><br>
      <input type="text" name="lastname" placeholder="Enter yout last name"><br>
      <input type="submit" value="Submit">
    </form>
  </body>
</html>
