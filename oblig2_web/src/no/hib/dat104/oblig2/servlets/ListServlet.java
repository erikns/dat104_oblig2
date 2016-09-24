package no.hib.dat104.oblig2.servlets;


import no.hib.dat104.oblig2.services.ParticipantService;
import no.hib.dat104.oblig2.util.SessionHelper;
import no.hib.dat104.oblig2.models.ParticipantPublicViewModel;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    @EJB
    private ParticipantService participantService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check for authorization
        SessionHelper sessionHelper = new SessionHelper(req.getSession());

        if (sessionHelper.isLoggedIn()) {
            // retrieve list of participants from database
            List<ParticipantPublicViewModel> participants = participantService.getParticipantsPublic();
            req.setAttribute("vm", participants);
            req.setAttribute("loggedInUser", sessionHelper.getLoggedInUser());

            // forward to list jsp template
            req.getRequestDispatcher("WEB-INF/list.jsp").forward(req, resp);
        } else {
            // not logged in. Redirect to login screen
            resp.sendRedirect("/login");
        }
    }
}
