package no.hib.dat104.oblig2.models;

import junit.framework.AssertionFailedError;
import org.junit.Test;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class SignupDataViewModelTest {
    @Test
    public void singnupDataCompletelyEmptyDoesNotValidate() {
        SignupDataViewModel vm = SignupDataViewModel.buildEmpty();

        assertFalse(vm.validate());
        assertNotEquals("", vm.getFirstNameError());
        assertNotEquals("", vm.getLastNameError());
        assertNotEquals("", vm.getPhoneError());
        assertNotEquals("", vm.getGenderError());
    }

    @Test
    public void singupDataAllNullsDoesNotValidate() {
        SignupDataViewModel vm = SignupDataViewModel.build(null, null, null, null);

        assertFalse(vm.validate());
    }

    @Test
    public void singupDataCorrectlyFilledOutValidatesWithNoErrors() {
        SignupDataViewModel vm = SignupDataViewModel.build("Test", "Testesen", "90000000", "M");

        assertTrue(vm.validate());
        assertEquals("", vm.getFirstNameError());
        assertEquals("", vm.getLastNameError());
        assertEquals("", vm.getPhoneError());
        assertEquals("", vm.getGenderError());
    }

    @Test
    public void signupDataCompleteExceptFirstNameDoesNotValidateWithCorrectError() {
        SignupDataViewModel vm = SignupDataViewModel.build("", "Testesen", "90000000", "F");

        assertFalse(vm.validate());
        assertStringContains(vm.getFirstNameError(), "Fornavn");
        assertStringContains(vm.getFirstNameError(), "kan ikke være tomt");
    }

    @Test
    public void signupDataCompleteExceptLastNameDoesNotValidateWithCorrectError() {
        SignupDataViewModel vm = SignupDataViewModel.build("Test", "", "90000000", "F");

        assertFalse(vm.validate());
        assertStringContains(vm.getLastNameError(), "Etternavn");
        assertStringContains(vm.getLastNameError(), "kan ikke være tomt");
    }

    @Test
    public void signupDataCompleteExceptPhoneEmptyDoesNotValidateWithCorrectError() {
        SignupDataViewModel vm = SignupDataViewModel.build("Test", "Testesen", "", "F");

        assertFalse(vm.validate());
        assertStringContains(vm.getPhoneError(), "Mobil");
        assertStringContains(vm.getPhoneError(), "akkurat 8 tegn");
    }

    @Test
    public void signupDataCompleteExceptPhoneIsNotMobileDoesNotValidateWithCorrectError() {
        SignupDataViewModel vm = SignupDataViewModel.build("Test", "Testesen", "55000000", "F");

        assertFalse(vm.validate());
        assertStringContains(vm.getPhoneError(), "Mobil");
        assertStringContains(vm.getPhoneError(), "begynne på 4 eller 9");
    }

    @Test
    public void signupDataCompleteExceptPhoneIsNotCompletelyNumericDoesNotValidateWithCorrectError() {
        SignupDataViewModel vm = SignupDataViewModel.build("Test", "Testesen", "440aa00c", "F");

        assertFalse(vm.validate());
        assertStringContains(vm.getPhoneError(), "Mobil");
        assertStringContains(vm.getPhoneError(), "bestå av tall");
    }

    @Test
    public void signupDataEscapesHtmlEntities() {
        Map<String, String[]> parameters = new HashMap<>();
        parameters.put("firstName", new String[] { "<h1>Escape</h1>" });
        parameters.put("lastName", new String[] { "<b>Escape</b>" });

        HttpServletRequest req = new MockHttpServletRequest(parameters);
        SignupDataViewModel vm = SignupDataViewModel.buildFromRequestParameters(req);

        assertStringContains(vm.getFirstName(), "&lt;");
        assertStringContains(vm.getLastName(), "&gt;");
    }

    private static void assertStringContains(String s, String contains) {
        if (!s.contains(contains)) {
            throw new AssertionFailedError("Expected '" + s + "' to contain '" + contains + "', but didn't");
        }
    }

    private class MockHttpServletRequest implements HttpServletRequest {
        private Map<String, String[]> parameters;

        public MockHttpServletRequest(Map<String, String[]> parameters) {
            this.parameters = parameters;
        }

        @Override
        public String getAuthType() {
            return null;
        }

        @Override
        public Cookie[] getCookies() {
            return new Cookie[0];
        }

        @Override
        public long getDateHeader(String s) {
            return 0;
        }

        @Override
        public String getHeader(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaders(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return null;
        }

        @Override
        public int getIntHeader(String s) {
            return 0;
        }

        @Override
        public String getMethod() {
            return null;
        }

        @Override
        public String getPathInfo() {
            return null;
        }

        @Override
        public String getPathTranslated() {
            return null;
        }

        @Override
        public String getContextPath() {
            return null;
        }

        @Override
        public String getQueryString() {
            return null;
        }

        @Override
        public String getRemoteUser() {
            return null;
        }

        @Override
        public boolean isUserInRole(String s) {
            return false;
        }

        @Override
        public Principal getUserPrincipal() {
            return null;
        }

        @Override
        public String getRequestedSessionId() {
            return null;
        }

        @Override
        public String getRequestURI() {
            return null;
        }

        @Override
        public StringBuffer getRequestURL() {
            return null;
        }

        @Override
        public String getServletPath() {
            return null;
        }

        @Override
        public HttpSession getSession(boolean b) {
            return null;
        }

        @Override
        public HttpSession getSession() {
            return null;
        }

        @Override
        public String changeSessionId() {
            return null;
        }

        @Override
        public boolean isRequestedSessionIdValid() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromCookie() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromURL() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromUrl() {
            return false;
        }

        @Override
        public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
            return false;
        }

        @Override
        public void login(String s, String s1) throws ServletException {

        }

        @Override
        public void logout() throws ServletException {

        }

        @Override
        public Collection<Part> getParts() throws IOException, ServletException {
            return null;
        }

        @Override
        public Part getPart(String s) throws IOException, ServletException {
            return null;
        }

        @Override
        public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
            return null;
        }

        @Override
        public Object getAttribute(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String getCharacterEncoding() {
            return null;
        }

        @Override
        public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

        }

        @Override
        public int getContentLength() {
            return 0;
        }

        @Override
        public long getContentLengthLong() {
            return 0;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public String getParameter(String s) {
            String[] array = parameters.get(s);

            if (array != null) {
                return array[0];
            } else {
                return null;
            }
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return null;
        }

        @Override
        public String[] getParameterValues(String s) {
            return new String[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return null;
        }

        @Override
        public String getProtocol() {
            return null;
        }

        @Override
        public String getScheme() {
            return null;
        }

        @Override
        public String getServerName() {
            return null;
        }

        @Override
        public int getServerPort() {
            return 0;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return null;
        }

        @Override
        public String getRemoteAddr() {
            return null;
        }

        @Override
        public String getRemoteHost() {
            return null;
        }

        @Override
        public void setAttribute(String s, Object o) {

        }

        @Override
        public void removeAttribute(String s) {

        }

        @Override
        public Locale getLocale() {
            return null;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return null;
        }

        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String s) {
            return null;
        }

        @Override
        public String getRealPath(String s) {
            return null;
        }

        @Override
        public int getRemotePort() {
            return 0;
        }

        @Override
        public String getLocalName() {
            return null;
        }

        @Override
        public String getLocalAddr() {
            return null;
        }

        @Override
        public int getLocalPort() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public AsyncContext startAsync() throws IllegalStateException {
            return null;
        }

        @Override
        public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
            return null;
        }

        @Override
        public boolean isAsyncStarted() {
            return false;
        }

        @Override
        public boolean isAsyncSupported() {
            return false;
        }

        @Override
        public AsyncContext getAsyncContext() {
            return null;
        }

        @Override
        public DispatcherType getDispatcherType() {
            return null;
        }
    }
}
