package no.hib.dat104.oblig2.servlets;

import no.hib.dat104.oblig2.util.SessionHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //kanskje denne skal haandtere aa sende ut "bekreft betaling"
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SessionHelper sessionHelper = new SessionHelper(req.getSession());

        if (sessionHelper.isAdmin()){
            req.getRequestDispatcher("WEB-INF/adminside.jsp").forward(req, resp);
        }else{
            //hvis bruker prover aa se side uten aa vaere logget inn
            resp.sendRedirect("adminlogin?msg=" + URLEncoder.encode("Du må logge inn som admin " +
                    "for å bruke denne funksjonen", "UTF-8"));
        }
    }

}
