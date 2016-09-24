package no.hib.dat104.oblig2;

import javax.servlet.http.HttpSession;

public class SessionHelper {
    private static final String LOGGED_IN = "loggedIn";
    private static final String USER_NAME = "username";
    private final HttpSession session;

    public SessionHelper(HttpSession session) {
        this.session = session;
    }

    public boolean isLoggedIn() {
        Object loggedIn = session.getAttribute(LOGGED_IN);
        return loggedIn != null && (boolean) loggedIn;
    }

    public String getLoggedInUser() {
        return (String) session.getAttribute(USER_NAME);
    }

    public void logIn(String username) {
        session.setAttribute(LOGGED_IN, true);
        session.setAttribute(USER_NAME, username);
    }

    public void logOut() {
        session.invalidate();
    }
}
