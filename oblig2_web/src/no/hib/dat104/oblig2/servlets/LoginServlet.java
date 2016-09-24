package no.hib.dat104.oblig2.servlets;


import no.hib.dat104.oblig2.services.ParticipantService;
import no.hib.dat104.oblig2.util.SessionHelper;
import no.hib.dat104.oblig2.models.ParticipantEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    @EJB
    ParticipantService participantService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("msg", req.getParameter("msg"));
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionHelper sessionHelper = new SessionHelper(req.getSession());

        String phone = req.getParameter("phone");

        ParticipantEntity participantEntity = participantService.getParticipant(phone);
        if (participantEntity != null && participantEntity.getPhone().equals(phone)) {
            // log in
            sessionHelper.logIn(phone);
            resp.sendRedirect("/list");
        } else {
            // probably not signed up.
            resp.sendRedirect("/login?msg=" + URLEncoder.encode("Feil mobilnummer. Er du påmeldt?", "UTF-8"));
        }
    }
}
