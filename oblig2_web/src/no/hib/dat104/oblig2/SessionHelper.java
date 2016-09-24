package no.hib.dat104.oblig2;

import javax.servlet.http.HttpSession;

public class SessionHelper {
    private static final String LOGGED_IN = "loggedIn";
    private final HttpSession session;

    public SessionHelper(HttpSession session) {
        this.session = session;
    }

    public boolean isLoggedIn() {
        Object loggedIn = session.getAttribute(LOGGED_IN);
        return loggedIn != null && (boolean) loggedIn;
    }

    public void logIn() {
        session.setAttribute(LOGGED_IN, true);
    }

    public void logOut() {
        session.invalidate();
    }
}
