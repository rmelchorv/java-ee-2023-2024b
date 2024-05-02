package mx.edu.unistmo.ixtepec.li.twi.p2.examples.person.control;

import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.unistmo.ixtepec.li.twi.p2.examples.person.util.AESCipherUtil;

@WebServlet(name = "PersonServlet", urlPatterns = { "/PersonServlet" })
public class PersonServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {

    String pwd = getServletContext().getInitParameter("connPwd");
    String key = getServletContext().getInitParameter("connPwdKey");

    try {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String html = String.join("\n", "<!DOCTYPE html>",
        "<html lang='es-mx'>",
        " <head>",
        "   <meta charset='UTF-8'>",
        "   <meta name='viewport' content='width=device-width, initial-scale=1.0'>",
        "   <title>Person Servlet</title>",
        "   <link rel='icon' href='assets/img/favicon.png'>",
        " </head>",
        " <body>",
        "   <h1>Person Servlet</h1>",
        "   <h2>Pwd: " + AESCipherUtil.decrypt(pwd, key) + "</h2>",
        " </body>",
        "</html>");

      out.println(html);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
