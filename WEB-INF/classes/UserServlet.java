
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class UserServlet extends HttpServlet {
    DataBaseServer dbObj;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("User servlet");
        String action = request.getParameter("action");

        if (action.equals("login")) {
            dbObj = new DataBaseServer();
            pw.println("login");
            userLogin(request, response, pw);
        } else if (action.equals("register")) {
            dbObj = new DataBaseServer();
            pw.println("Register");
            userRegister(request, response, pw);
        } else if (action.equals("ForgotPassword")) {
            dbObj = new DataBaseServer();
            pw.println("ForgotPassword");
            getForgotPassword(request, response, pw);
        }
    }

    void userLogin(HttpServletRequest request, HttpServletResponse response, PrintWriter pw) throws IOException,ServletException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (dbObj.userLogin(email, password,request,response,pw)) {
            response.sendRedirect("index.jsp");
        } else {
            pw.println("Login Failed");
        }
    }

    void userRegister(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)throws IOException,ServletException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone_number = request.getParameter("phone");
        String securityQuestiion = request.getParameter("security_question");
        String securityAnswer = request.getParameter("answer");
        String gender = request.getParameter("gender");
        String res = dbObj.userRegister(email, name, password, phone_number, securityQuestiion, securityAnswer, gender);
        if (res.equals("Registration successful")) {
            request.setAttribute("success", "Registration Successful. Now you can login.");
        } else if (res.equals("user already existed with this email")) {
            request.setAttribute("message", "User already exists with this email.");
        } else {
            request.setAttribute("message", "Registration failed. Please try again.");
        }
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    void getForgotPassword(HttpServletRequest request, HttpServletResponse response, PrintWriter pw) throws IOException,ServletException {
        String email = request.getParameter("email");
        String securityQuestion = request.getParameter("security_question");
        String securityAnswer = request.getParameter("answer");
        String res = dbObj.getForgotPassword(email, securityQuestion, securityAnswer);
        String inValid1 = "Invalid Security Question or Answer";
        String inValid2 = "Invalid Email";
        if (res.equals(inValid1)) {
            request.setAttribute("message", inValid1);
        } else if (res.equals(inValid2)) {
            request.setAttribute("message", inValid2);
        } else if (res.equals("-1")) {
            request.setAttribute("message", "Unexpected error");
        } else {
            request.setAttribute("password", res);
        }
        
        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }
}
