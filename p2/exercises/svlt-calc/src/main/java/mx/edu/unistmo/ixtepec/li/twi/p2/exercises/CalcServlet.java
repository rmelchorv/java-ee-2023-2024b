package mx.edu.unistmo.ixtepec.li.twi.p2.exercises;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CalcServlet", urlPatterns = { "/CalcServlet" })
public class CalcServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String op = req.getParameter("operation");
    double a;
    double b;
    double result;

    try {
      a = Double.parseDouble(req.getParameter("a"));
      b = Double.parseDouble(req.getParameter("b"));
    } catch (NumberFormatException e) {
      a = 0;
      b = 1;
    }
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html");

    switch (op) {
      case "sub":
        result = a - b;
        op = "-";
        break;
      case "mul":
        result = a * b;
        op = "*";
        break;
      case "div":
        result = a / b;
        op = "/";
        break;
      default:
        result = a + b;
        op = "+";
        break;
    }

    try (PrintWriter out = resp.getWriter()) {
      String html = String.join("\n", "<!DOCTYPE html>",
          "<html lang='es-mx'>",
          " <head>",
          "  <meta charset='UTF-8'>",
          "  <link rel='icon' href='assets/img/favicon.png'>",
          "  <title>" + this.getServletName() + "</title>",
          " </head>",
          " <body>",
          "  <h1>Operation:</h1>",
          "  <p>" + String.format(Locale.ROOT, "%.0f %s %.0f = %.1f", a, op, b, result) + "</p>",
          " </body>",
          "</html>");

      out.println(html);
    } catch (IOException e) {
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      getServletContext().log("Error writing response", e);
    }
  }
}
