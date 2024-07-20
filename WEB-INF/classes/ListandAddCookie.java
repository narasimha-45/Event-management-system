import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "Cookie", urlPatterns = {"/ListandAddCookies"})

public class ListandAddCookie extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie cookie = null;
        out.println("<html><body>" +
                "<form method='get' action='/ListandAddCookie'>" + "Name:<input type='text' name='user' /><br/>" +

                "Password:<input type='text' name='pass' ><br/>" + "<input type='submit' value='submit'>" + "</form>");

        String name = request.getParameter("user");
        String pass = request.getParameter("pass");

        if (!pass.equals("") || !name.equals("")) {
            Cookie ck = new Cookie(name, pass);
            response.addCookie(ck);
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            out.println("<h2> Found Cookies Name and Value</h2>");
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                out.print("Cookie Name : " + cookie.getName() + ", ");
                out.print("Cookie Value: " + cookie.getValue() + " <br/>");
            }
        }
        out.println("</body></html>");
    }
}
