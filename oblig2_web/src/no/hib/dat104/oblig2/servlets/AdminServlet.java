package no.hib.dat104.oblig2.servlets;

import no.hib.dat104.oblig2.models.ParticipantPublicViewModel;
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

import static java.lang.System.out;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @EJB
    private ParticipantService participantService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //her tar jeg i mot data fra adminside som skal behandles

        String id = req.getParameter("id");

        //TODO legge til behandling i stedet for utskrift av id her
        out.println(id);


        //resp.sendRedirect("admin");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SessionHelper sessionHelper = new SessionHelper(req.getSession());

        if (sessionHelper.isAdmin()){
            //happy path
            // retrieve list of participants from database
            List<ParticipantPublicViewModel> participants = participantService.getParticipantsPublic();
            req.setAttribute("vm", participants);
            req.setAttribute("loggedInUser", sessionHelper.getLoggedInUser());

            req.getRequestDispatcher("WEB-INF/adminside.jsp").forward(req, resp);
        }else{
            //innbrudd paa side
            resp.sendRedirect("adminlogin?msg=" + URLEncoder.encode("Du må logge inn som admin " +
                    "for å bruke denne funksjonen", "UTF-8"));
        }
    }

}
