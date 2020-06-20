package webds.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TeamNumServlet", urlPatterns = {"config-team-num"}) 
public class TeamNumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numIn = request.getParameter("num");
        if (numIn == null || numIn.equals("")) numIn = "0000";
        request.setAttribute("teamNum", numIn);
        request.getRequestDispatcher("views/TeamNumView.jsp").forward(request, response); 
    }

}