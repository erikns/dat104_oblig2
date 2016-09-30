package no.hib.dat104.oblig2.servlets;

import no.hib.dat104.oblig2.models.ParticipantEntity;
import no.hib.dat104.oblig2.services.ParticipantService;
import no.hib.dat104.oblig2.util.SessionHelper;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @EJB
    private ParticipantService participantService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //her tar jeg i mot data fra adminside som skal behandles

        SessionHelper sessionHelper = new SessionHelper(req.getSession());
        if (sessionHelper.isAdmin()) {
            String id = req.getParameter("id");
            participantService.registerPayment(id);
        } else {
            redirectNotLoggedIn(resp);
        }

        resp.sendRedirect("admin");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SessionHelper sessionHelper = new SessionHelper(req.getSession());

        if (sessionHelper.isAdmin()){
            //happy path
            // retrieve list of participants from database
            List<ParticipantEntity> participants = participantService.getAllParticipants();
            req.setAttribute("vm", participants);
            req.setAttribute("loggedInUser", sessionHelper.getLoggedInUser());

            req.getRequestDispatcher("WEB-INF/adminside.jsp").forward(req, resp);
        }else{
            //innbrudd paa side
            redirectNotLoggedIn(resp);
        }
    }

    private void redirectNotLoggedIn(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("adminlogin?msg=" + URLEncoder.encode("Du må logge inn som admin " +
                "for å bruke denne funksjonen", "UTF-8"));
    }
}
