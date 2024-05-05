package mx.edu.unistmo.ixtepec.li.twi.p2.examples.dbconn.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.unistmo.ixtepec.li.twi.p2.examples.dbconn.models.DBConnManager;

@WebServlet(name = "DBConnServlet", urlPatterns = { "/DBConnServlet" })
public class DBConnServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String connStr = getServletContext().getInitParameter("connStr");
    String connPwd = getServletContext().getInitParameter("connPwd");
    String connDb = getServletContext().getInitParameter("connDb");

    DBConnManager conn = new DBConnManager(
        String.format(Locale.ROOT, connStr, connPwd), connDb);
 
    try (PrintWriter out = response.getWriter()) {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html");

      MongoDatabase mDB = conn.getConn();

      mDB.runCommand(new Document("ping", 1));

      String html = String.join("\n", "<!DOCTYPE html>",
          "<html lang='es-mx'>",
          " <head>",
          "   <meta charset='UTF-8'>",
          "   <meta name='viewport' content='width=device-width, initial-scale=1.0'>",
          "   <title>" + getServletName() + "</title>",
          "   <link rel='icon' href='assets/img/favicon.png'>",
          " </head>",
          " <body>",
          "   <h1>" + getServletName() + "</h1>",
          "   <h2>Successfully connected to MongoDB!</h2>",
          " </body>",
          "</html>");

      out.println(html);
    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      getServletContext().log(e.getMessage(), e);
    }
  }
}
