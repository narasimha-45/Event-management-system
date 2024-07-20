import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import java.net.URLDecoder;

@WebServlet(name = "RegisterToEvent", urlPatterns = { "/RegisterToEvent" })

public class RegisterToEvent extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        String userId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    userId = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    break;
                }
            }
        }
        String eventId = request.getParameter("eventId");
        DataBaseServer db = new DataBaseServer();
        String isRegistered = db.registerToEvent(userId, eventId);
        if(isRegistered.equals("Registered SuccessFully")){
            out.println("<script type='text/javascript'>");
            out.println("alert('Registered SuccessFully');");
            out.println("window.location.href='RegisteredEvents';");
            out.println("</script>");
        }
        else if(isRegistered.equals("You are already registered to this event")){
            out.println("<script type='text/javascript'>");
            out.println("alert('You are already registered to this event');");
            out.println("window.location.href='RegisteredEvents';");
            out.println("</script>");
        }
        else{
            out.println("<script type='text/javascript'>");
            out.println("alert('Something went wrong');");
            out.println("window.location.href='AllEvents';");
            out.println("</script>");
        }
    }
}
