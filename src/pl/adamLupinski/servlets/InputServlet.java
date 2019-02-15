package pl.adamLupinski.servlets;

import pl.adamLupinski.POJOs.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    // in this example we are passing parameters via request but to method doPost, and we are taking parameters not from urls like in method doGet but from form in html

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = createUserFromRequest(request);
        sendResponse(user, response);
    }

    private User createUserFromRequest(HttpServletRequest request){
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setGender(request.getParameter("gender"));
        user.setHobby(request.getParameterValues("hobby"));
        return user;
    }

    private void sendResponse(User user, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.print("<h2>Data confirmed</h2>");
        writer.print("<div>");
        writer.println("Username: " + user.getUsername() + "<br>");
        writer.println("Password: " + user.getPassword() + "<br>");
        writer.println("Gender: " + user.getGender() + "<br>");
        if(user.getHobby() != null) {
            writer.print("Hobby: <br>");
            for(String hobby: user.getHobby())
                writer.println("    -" + hobby + "<br>");
        }
        writer.print("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
