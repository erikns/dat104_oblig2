package no.hib.dat104.oblig2.util;

import junit.framework.AssertionFailedError;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class SessionHelperTest {
    @Test
    public void initialSessionNoOneIsLoggedIn() {
        SessionHelper session = new SessionHelper(new TestHttpSession());
        assertFalse(session.isLoggedIn());
    }

    @Test
    public void testUserLoggedInReturnsLoggedIn() {
        SessionHelper session = new SessionHelper(new TestHttpSession());

        session.logIn("testUser");

        assertTrue(session.isLoggedIn());
        assertStringEquals("testUser", session.getLoggedInUser());
    }

    @Test
    public void loggedInUserLoggedOutReturnsNotLoggedIn() {
        SessionHelper session = new SessionHelper(new TestHttpSession());

        session.logIn("testUser");
        session.logOut();

        assertFalse(session.isLoggedIn());
    }

    @Test
    public void normalLoggedInUserIsNotAdmin() {
        SessionHelper session = new SessionHelper(new TestHttpSession());

        session.logIn("testUser");

        assertFalse(session.isAdmin());
    }

    @Test
    public void adminUserLoggedInReturnsIsAdminTrueAndLoggedInTrue() {
        SessionHelper session = new SessionHelper(new TestHttpSession());

        session.logInAdmin();

        assertTrue(session.isAdmin());
        assertTrue(session.isLoggedIn());
    }

    @Test
    public void noUserLoggedInIsNotAdmin() {
        SessionHelper session = new SessionHelper(new TestHttpSession());
        assertFalse(session.isAdmin());
    }

    private static void assertStringEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionFailedError("Expected string to be '" + expected + ", but was '" + actual + "'");
        }
    }

    private class TestHttpSession implements HttpSession {
        private Map<String, Object> attributes = new HashMap<>();

        @Override
        public void setAttribute(String s, Object o) {
            attributes.put(s, o);
        }

        @Override
        public Object getAttribute(String s) {
            return attributes.get(s);
        }

        @Override
        public void invalidate() {
            attributes.clear();
        }

        @Override
        public long getCreationTime() {
            return 0;
        }

        @Override
        public String getId() {
            return null;
        }

        @Override
        public long getLastAccessedTime() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public void setMaxInactiveInterval(int i) {
        }

        @Override
        public int getMaxInactiveInterval() {
            return 0;
        }

        @Override
        public HttpSessionContext getSessionContext() {
            return null;
        }

        @Override
        public Object getValue(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String[] getValueNames() {
            return new String[0];
        }

        @Override
        public void putValue(String s, Object o) {

        }

        @Override
        public void removeAttribute(String s) {

        }

        @Override
        public void removeValue(String s) {

        }

        @Override
        public boolean isNew() {
            return false;
        }
    }
}
