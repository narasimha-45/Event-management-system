import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.URLEncoder;
import java.net.URLDecoder;

public class DataBaseServer {

    final String url = "jdbc:mysql://localhost:3306/eventmanagement";
    final String user = "root";
    final String password = "Narasimha@45";
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;

    public DataBaseServer() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (Exception e) {

        }
    }

    String generateUserID(String userName) {
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        String userID = userName.toUpperCase() + "_" + randomNumber;
        return userID;
    }

    boolean userLogin(String email, String password, HttpServletRequest request, HttpServletResponse response,
            PrintWriter pw) throws ServletException {
        try {
            String query = "Select password,name,userId from user_details where email='" + email + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String dbPassword = resultSet.getString("password");

                if (dbPassword.equals(dbPassword)) {
                    String name = resultSet.getString("name");
                    String userId = resultSet.getString("userId");
                    Cookie ck = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
                    Cookie ck1 = new Cookie("userId", URLEncoder.encode(userId, "UTF-8"));
                    ck.setMaxAge(60 * 60 * 24 * 365 * 10);
                    response.addCookie(ck);
                    ck.setMaxAge(60 * 60 * 24 * 365 * 10);
                    response.addCookie(ck1);
                    request.setAttribute("name", name);
                    request.setAttribute("userId", userId);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            pw.println(e.getMessage());
            return false;
        }
    }

    String userRegister(String email, String name, String password, String phone_number, String securityQuestiion,
            String securityAnswer, String gender) {
        try {
            String query = "SELECT * from user_details where email='" + email + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return "user already existed with this email";
            }
            String userID = generateUserID(name);
            query = "INSERT INTO user_details values(?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone_number);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, securityQuestiion);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, securityAnswer);
            preparedStatement.setString(8, userID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0 ? "Registration successful" : "-1";
        } catch (Exception e) {
            return "-1";
        }
    }

    String getForgotPassword(String email, String securityQuestion, String answer) {
        try {
            String query = "Select security_question,answer,password from user_details where email = '" + email + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String dbSecurityQuestion = resultSet.getString("security_question");
                String dbAnswer = resultSet.getString("answer");
                if (dbSecurityQuestion.equals(securityQuestion) && dbAnswer.equals(answer)) {
                    return resultSet.getString("password");
                } else {
                    return "Invalid Security Question or Answer";
                }
            } else {
                return "Invalid Email-id";
            }
        } catch (Exception e) {
            return "-1";
        }
    }

    boolean addEvent(String event_id, String organiserId, String eventName, String date, String time,
            String description, String venue, String organiser, PrintWriter pw) throws IOException {
        String query = "INSERT INTO events values(?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, event_id);
            preparedStatement.setString(2, organiserId);
            preparedStatement.setString(3, eventName);
            preparedStatement.setString(4, time);
            preparedStatement.setString(5, date);
            preparedStatement.setString(6, description);
            preparedStatement.setString(7, venue);
            preparedStatement.setString(8, organiser);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            pw.write(e.getMessage());
            return false;
        }
    }

    public List<Event> getAllEvents(HttpServletRequest request) throws IOException,ServletException {
        List<Event> events = new ArrayList<>();
        try {
            String userId = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("userId".equals(cookie.getName())) {
                        userId = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        break;
                    }
                }
            }
            String query = "SELECT * FROM events where organiserId != '" + userId + "' ORDER BY date, time";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String eventId = resultSet.getString("eventId");
                String organiserId = resultSet.getString("organiserId");
                String eventName = resultSet.getString("eventName");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                String description = resultSet.getString("description");
                String venue = resultSet.getString("venue");
                String organiser = resultSet.getString("organiser");
                query = "SELECT COUNT(userId) from registered_events where eventId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, eventId);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                int count = 0;
                if (resultSet1.next()) {
                    count = resultSet1.getInt("COUNT(userId)");
                }
                Event event = new Event(eventId, organiserId, eventName, date, time, description, venue, organiser,
                        count);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public List<Event> getMyEvents(HttpServletRequest request) throws IOException,ServletException {
        List<Event> events = new ArrayList<>();
        try {
            String query = "SELECT * FROM events WHERE organiserId = ? ORDER BY date, time";
            preparedStatement = connection.prepareStatement(query);
            String userId = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("userId".equals(cookie.getName())) {
                        userId = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        break;
                    }
                }
            }
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String eventId = resultSet.getString("eventId");
                String organiserId = resultSet.getString("organiserId");
                String eventName = resultSet.getString("eventName");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                String description = resultSet.getString("description");
                String venue = resultSet.getString("venue");
                String organiser = resultSet.getString("organiser");
                query = "SELECT COUNT(userId) from registered_events where eventId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, eventId);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                int count = 0;
                if (resultSet1.next()) {
                    count = resultSet1.getInt("COUNT(userId)");
                }
                Event event = new Event(eventId, organiserId, eventName, date, time, description, venue, organiser,
                        count);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public List<Event> getRegisteredEvents(HttpServletRequest request) throws IOException,ServletException {
        List<Event> events = new ArrayList<>();
        try {
            String query = "SELECT * FROM events e,registered_events r WHERE r.userId = ? and e.eventId = r.eventId  ORDER BY e.date, e.time";
            preparedStatement = connection.prepareStatement(query);
            String userId = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("userId".equals(cookie.getName())) {
                        userId = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        break;
                    }
                }
            }
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String eventId = resultSet.getString("eventId");
                String organiserId = resultSet.getString("organiserId");
                String eventName = resultSet.getString("eventName");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                String description = resultSet.getString("description");
                String venue = resultSet.getString("venue");
                String organiser = resultSet.getString("organiser");
                query = "SELECT COUNT(userId) from registered_events where eventId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, eventId);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                int count = 0;
                if (resultSet1.next()) {
                    count = resultSet1.getInt("COUNT(userId)");
                }
                Event event = new Event(eventId, organiserId, eventName, date, time, description, venue, organiser,
                        count);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public String registerToEvent(String userId, String eventId) throws IOException {
        try {
            String query = "Select * from registered_events where userId = ? and eventId = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, eventId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return "You are already registered to this event";
            }
            query = "INSERT INTO registered_events (userId,eventId) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, eventId);
            preparedStatement.executeUpdate();
            return "Registered SuccessFully";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
