package no.hib.dat104.oblig2.servlets;

import no.hib.dat104.oblig2.util.SessionHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new SessionHelper(req.getSession()).logOut();
        req.getRequestDispatcher("WEB-INF/logout.jsp").forward(req, resp);
    }
}
