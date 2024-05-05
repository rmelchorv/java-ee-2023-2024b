package mx.edu.unistmo.ixtepec.li.twi.p2.examples;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = { "/HelloServlet" })
public class HelloServlet extends HttpServlet {
  private static final String FIRSTNAME = "firstname";
  private static final String LASTNAME = "lastname";

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String fname = request.getParameter(FIRSTNAME);
    String lname = request.getParameter(LASTNAME);

    if (fname == null || fname.isBlank()) {
      fname = getServletConfig().getInitParameter(FIRSTNAME);

      if (lname == null || lname.isBlank())
        lname = getServletConfig().getInitParameter(LASTNAME);
    } else if (lname == null || lname.isBlank())
      lname = "";

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html");

    try (PrintWriter out = response.getWriter()) {
      String html = String.format(
          String.join("\n", "<!DOCTYPE html>",
              "<html lang=\"es-mx\">",
              " <head>",
              "  <meta charset='UTF-8'>",
              "  <link rel='icon' href='assets/img/favicon.png'>",
              "  <title>%s</title>",
              " </head>",
              " <body>",
              "  <h1>Hello %s %s!</h1>",
              "  <h3>Request URI: %s</h3>",
              "  <h3>Context path: %s</h3>",
              "  <h3>Servlet path: %s</h3>",
              " </body>",
              "</html>"),
          this.getServletName(), fname, lname,
          request.getRequestURI(),
          request.getContextPath(),
          request.getServletPath());

      out.println(html);
    } catch (IOException e) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      getServletContext().log("Error writing response", e);
    }
  }
}
