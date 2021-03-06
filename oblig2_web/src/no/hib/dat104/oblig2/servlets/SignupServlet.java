package no.hib.dat104.oblig2.servlets;

import no.hib.dat104.oblig2.models.ParticipantEntity;
import no.hib.dat104.oblig2.models.SignupDataViewModel;
import no.hib.dat104.oblig2.services.ParticipantService;
import no.hib.dat104.oblig2.util.SessionHelper;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    @EJB
    private ParticipantService participantService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Grab passed in parameters to pre populate the fields if necessary
        req.setAttribute("vm", SignupDataViewModel.buildFromRequestParameters(req));
        // Render the jsp template
        req.getRequestDispatcher("WEB-INF/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignupDataViewModel vm = SignupDataViewModel.buildFromRequestParameters(req);
        boolean validationResult = vm.validate();
        req.setAttribute("vm", vm);

        if (validationResult) {
            try {
                // happy path
                ParticipantEntity participantEntity = new ParticipantEntity();
                participantEntity.setFirstName(vm.getFirstName());
                participantEntity.setLastName(vm.getLastName());
                participantEntity.setPhone(vm.getPhone());
                participantEntity.setGender(vm.getGender());
                participantEntity.setPaid(false); // initially not paid

                participantService.signup(participantEntity);

                SessionHelper sessionHelper = new SessionHelper(req.getSession());
                sessionHelper.logIn(participantEntity.getPhone());

                req.getRequestDispatcher("WEB-INF/signup-ok.jsp").forward(req, resp);
            } catch (Exception e) {
                vm.setMessage("Er du allerede påmeldt?");
                resp.sendRedirect("signup?" + vm.urlEncode());
            }
        } else {
            // error validating. send redirect with url encoded vm
            resp.sendRedirect("signup?" + vm.urlEncode());
        }
    }
}
