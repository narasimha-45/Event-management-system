
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "AddEvent", urlPatterns = {"/AddEvent"})
public class AddEvent extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        String organiserName = null;
        String organiserId = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("name".equals(cookie.getName())) {
                    organiserName = cookie.getValue();
                }
                if ("userId".equals(cookie.getName())) {
                    organiserId = cookie.getValue();
                }
            }
        }

        String eventName = request.getParameter("eventName");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String description = request.getParameter("description");
        String venue = request.getParameter("venue");
        String eventId = eventName + "_" + organiserName;
        DataBaseServer db = new DataBaseServer();
        PrintWriter pw = response.getWriter();

        if (db.addEvent(eventId, organiserId, eventName, date, time, description, venue, organiserName, pw)) {
        
            String message = "Event added successfully!";
            String script = "<script type='text/javascript'>alert('" + message + "');"
                          + "window.location.href='MyEvents';</script>";
            
            pw.println(script);
        } else {
            String script = "<script type='text/javascript'>alert('There is error in Adding a event ,Try again');"
                          + "window.location.href='#';</script>";
            pw.println(script);
        }
    }
}
