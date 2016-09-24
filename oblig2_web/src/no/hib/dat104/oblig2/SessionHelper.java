package no.hib.dat104.oblig2;

import javax.servlet.http.HttpSession;

public class SessionHelper {
    private static final String LOGGED_IN = "loggedIn";
    private static final String USER_NAME = "username";
    private static final String IS_ADMIN = "isAdmin";
    private final HttpSession session;

    public SessionHelper(HttpSession session) {
        this.session = session;
    }

    public boolean isLoggedIn() {
        Object loggedIn = session.getAttribute(LOGGED_IN);
        return loggedIn != null && (boolean) loggedIn;
    }

    public boolean isAdmin() {
        Object isAdmin = session.getAttribute(IS_ADMIN);
        return isAdmin != null && (boolean) isAdmin;
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

    public void logInAdmin() {
        session.setAttribute(IS_ADMIN, true);
        session.setAttribute(LOGGED_IN, true);
    }
}
