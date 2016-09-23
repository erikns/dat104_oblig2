package no.hib.dat104.oblig2;

import no.hib.dat104.oblig2.models.SignupDataViewModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
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
            // happy path
            req.getRequestDispatcher("WEB-INF/signup-ok.jsp").forward(req, resp);
        } else {
            // error validating. send redirect with url encoded vm
            resp.sendRedirect("/signup?" + vm.urlEncode());
        }
    }
}
