package no.hib.dat104.oblig2.servlets;

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

    //TODO endre implementasjon av denne variabelen... init?
    String admin = "ADMIN";

    //tar i mot submit fra adminlogin og sjekker passord
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        SessionHelper sessionHelper = new SessionHelper(req.getSession());

        //hva er forskjell paa getParameter og getAttribute?
        String adminpassord = req.getParameter("adminpassord");

        if (adminpassord.equals(admin)){
            //happy path
            sessionHelper.logInAdmin();
            resp.sendRedirect("admin");
        }else{
            //ikke admin
            resp.sendRedirect("adminlogin?msg=" + URLEncoder.encode("Feil passord. Prøv igjen.", "UTF-8"));
        }
    }//doPost


    //tar i mot redirect og legger paa feilmelding for siden vises
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("msg", req.getParameter("msg"));
        req.getRequestDispatcher("WEB-INF/adminlogin.jsp").forward(req, resp);
    }
}
