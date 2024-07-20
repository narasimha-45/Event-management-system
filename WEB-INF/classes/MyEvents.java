import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "MyEvents", urlPatterns = { "/MyEvents" })
public class MyEvents extends HttpServlet {
    private DataBaseServer dbObj = new DataBaseServer();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Event> events = dbObj.getMyEvents(request);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>My Events</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f0f0f0; margin: 0; padding: 20px; }");
        out.println("h1 { text-align: center; color: #333; }");
        out.println(".event-container { display: flex; flex-wrap: wrap; justify-content: center; }");
        out.println(
                ".event-card { background-color: white; border: 1px solid #ddd; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); margin: 10px; padding: 20px; width: 300px; }");
        out.println(".event-details { margin-bottom: 10px; }");
        out.println(".event-name { font-size: 18px; font-weight: bold; margin-bottom: 5px; }");
        out.println(".event-info { font-size: 14px; color: #666; margin-bottom: 3px; }");
        out.println(".event-description { font-size: 14px; color: #444; margin-bottom: 10px; }");
        out.println(
                ".register-button { display: inline-block; padding: 10px 15px; font-size: 14px; font-weight: bold; color: white; background-color: #007BFF; border: none; border-radius: 4px; cursor: pointer; text-align: center; text-decoration: none; }");
        out.println(".register-button:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>My Events</h1>");
        out.println("<div class='event-container'>");
        if (events.size() == 0) {
            out.println("<p>You are not organising any event</p>");
        } else {
            for (Event event : events) {
                out.println("<div class='event-card'>");
                out.println("<div class='event-details'>");
                out.println("<div class='event-name'>" + event.getEventName() + "</div>");
                out.println(
                        "<div class='event-info'>Date: " + event.getDate() + ", Time: " + event.getTime() + "</div>");
                out.println("<div class='event-info'>Venue: " + event.getVenue() + "</div>");
                out.println("<div class='event-info'>Registrations: " + event.getNoOfRegistrations() + "</div>");
                out.println("</div>");
                out.println("<div class='event-description'>" + event.getDescription() + "</div>");
            }
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
