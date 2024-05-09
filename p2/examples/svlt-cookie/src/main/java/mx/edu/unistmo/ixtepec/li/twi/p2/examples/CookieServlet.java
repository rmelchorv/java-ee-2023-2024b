package mx.edu.unistmo.ixtepec.li.twi.p2.examples;

//#region Imports
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//#endregion

@WebServlet(name = "CookieServlet", urlPatterns = "/CookieServlet")
public class CookieServlet extends HttpServlet {
  private static final String COOKIE_NAME = "my-cookie";

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try (PrintWriter out = response.getWriter()) {
      response.setCharacterEncoding(StandardCharsets.UTF_8.name());
      response.setContentType("text/html");

      String value = URLEncoder.encode(
          request.getParameter("cookie"),
          StandardCharsets.UTF_8.name());

      // #region Cookie creation
      Cookie cookie = new Cookie(COOKIE_NAME, value);

      cookie.setMaxAge(60 * 60 * 24 * 365); // <-- 1 year
      cookie.setHttpOnly(true);
      cookie.setSecure(true);

      response.addCookie(cookie);
      // #endregion
      // #region Cookie retrieval
      Cookie[] cookies = request.getCookies();

      for (Cookie c : cookies) {
        out.println(
            c.getName() + ": " +
                URLDecoder.decode(c.getValue(),
                    StandardCharsets.UTF_8.name())
                + "<br>");
      }
      // #endregion
      // #region Cookie deletion
      cookie = new Cookie(COOKIE_NAME, null);

      cookie.setMaxAge(0);
      cookie.setHttpOnly(true);
      cookie.setSecure(true);

      response.addCookie(cookie);
      // #endregion

      String html = String.join(
          // #region HTML response
          "\n", "<!DOCTYPE html>",
          "<html lang='es-mx'>",
          " <head>",
          "   <meta charset='UTF-8'>",
          "   <meta name='viewport' content='width=device-width, initial-scale=1.0'>",
          "   <title>" + getServletName() + "</title>",
          "   <link rel='icon' href='assets/img/favicon.png'>",
          " </head>",
          " <body>",
          "   <h1>" + getServletName() + "</h1>",
          " </body>",
          "</html>"
      // #endregion
      );

      out.println(html);
    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      getServletContext().log(e.getMessage(), e);
    }
  }
}
