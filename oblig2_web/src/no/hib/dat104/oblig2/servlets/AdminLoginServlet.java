package no.hib.dat104.oblig2.servlets;

import no.hib.dat104.oblig2.util.Config;
import no.hib.dat104.oblig2.util.SessionHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


@WebServlet("/adminlogin")
public class AdminLoginServlet extends HttpServlet {
    //tar i mot submit fra adminlogin og sjekker passord
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        SessionHelper sessionHelper = new SessionHelper(req.getSession());

        //hva er forskjell paa getParameter og getAttribute?
        String password = req.getParameter("adminpassord");

        if (password.equals(getAdminPassword())){
            // valid password
            sessionHelper.logInAdmin();
            resp.sendRedirect("admin");
        }else{
            // invalid password
            resp.sendRedirect("adminlogin?msg=" + URLEncoder.encode("Feil passord. Prøv igjen.", Config.URL_ENCODING));
        }
    }//doPost


    //tar i mot redirect og legger paa feilmelding for siden vises
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("msg", req.getParameter("msg"));
        req.getRequestDispatcher("WEB-INF/adminlogin.jsp").forward(req, resp);
    }

    private String getAdminPassword() {
        return getServletContext().getInitParameter("adminPassword");
    }
}
