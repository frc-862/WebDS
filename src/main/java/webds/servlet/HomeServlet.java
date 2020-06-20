package webds.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jnr.ffi.LibraryLoader;
import webds.DriverStation;

@WebServlet(name = "HomeServlet", urlPatterns = {"run"}) 
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Start DS things here. IDK what tho.
        DriverStation ds = LibraryLoader.create(DriverStation.class).load("c");
        ds.start(); // Initialize Driver Station and Establish Connection with Robot
        // TODO - figure out how to run loop from here
        request.getRequestDispatcher("views/HomeView.jsp").forward(request, response);
    }

}