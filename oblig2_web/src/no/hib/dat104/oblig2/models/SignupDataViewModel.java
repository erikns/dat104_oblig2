package no.hib.dat104.oblig2.models;

import no.hib.dat104.oblig2.util.Config;

import org.apache.commons.lang3.StringEscapeUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SignupDataViewModel {
    private String firstName = "";
    private String firstNameError = "";
    private String lastName = "";
    private String lastNameError = "";
    private String phone = "";
    private String phoneError = "";
    private String gender = "";
    private String genderError = "";
    private String msg = "";

    private SignupDataViewModel() { }

    private SignupDataViewModel(String firstName, String lastName, String phone, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.gender = gender;

        if (this.firstName == null) this.firstName = "";
        if (this.lastName == null) this.lastName = "";
        if (this.phone == null) this.phone = "";
        if (this.gender == null) this. gender = "";
    }

    public static SignupDataViewModel buildEmpty() {
        return new SignupDataViewModel();
    }

    public static SignupDataViewModel build(String firstName, String lastName, String phone, String gender) {
        return new SignupDataViewModel(firstName, lastName, phone, gender);
    }

    public static SignupDataViewModel buildFromRequestParameters(HttpServletRequest req) {
        SignupDataViewModel vm = new SignupDataViewModel();

        vm.setFirstName(getStringParameter(req, "firstName"));
        vm.setFirstNameError(getStringParameter(req, "firstNameError"));
        vm.setLastName(getStringParameter(req, "lastName"));
        vm.setLastNameError(getStringParameter(req, "lastNameError"));
        vm.setPhone(getStringParameter(req, "phone"));
        vm.setPhoneError(getStringParameter(req, "phoneError"));
        vm.setGender(getStringParameter(req, "gender"));
        vm.setGenderError(getStringParameter(req, "genderError"));
        vm.setMessage(getStringParameter(req, "message"));

        return vm;
    }

    private static String getStringParameter(HttpServletRequest req, String parameter) {
        if (req.getParameter(parameter) != null) {
            return req.getParameter(StringEscapeUtils.escapeHtml4(parameter)); //TODO la inn escape her
        } else {
            return "";
        }
    }

    /**
     * Perform validation of the view model. The view model will change state after this method is called.
     * The error fields are populated with any errors that occur. The fields containing errors are also emptied
     * out during this process.
     * @return True if there are no errors, false otherwise
     */
    public boolean validate() {
        boolean resultFirstName = true;
        if (firstName.length() < 1) {
            resultFirstName = false;
            firstNameError = "Fornavn kan ikke være tomt";
        }

        boolean resultLastName = true;
        if (lastName.length() < 1) {
            resultLastName = false;
            lastNameError = "Etternavn kan ikke være tomt";
        }

        boolean resultPhone = true;
        if (phone.length() != 8) {
            resultPhone = false;
            phoneError = "Mobil må være akkurat 8 tegn";
        } else if (!(phone.startsWith("4") || phone.startsWith("9"))) {
            resultPhone = false;
            phoneError = "Mobil må begynne på 4 eller 9";
        } else if (!isNumeric(phone)) {
            resultPhone = false;
            phoneError = "Mobil kan bare bestå av tall";
        }

        boolean resultGender = true;
        if (!(gender.equals("M") || gender.equals("F"))) {
            resultGender = false;
            genderError = "Kjønn må være mann eller kvinne";
        }

        return resultFirstName && resultLastName && resultPhone && resultGender;
    }

    private static boolean isNumeric(String phone) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Integer.parseUnsignedInt(phone);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String urlEncode() {
        return urlEncodeParameter("firstName", firstName) + "&" +
                urlEncodeParameter("firstNameError", firstNameError) + "&" +
                urlEncodeParameter("lastName", lastName) + "&" +
                urlEncodeParameter("lastNameError", lastNameError) + "&" +
                urlEncodeParameter("phone", phone) + "&" +
                urlEncodeParameter("phoneError", phoneError) + "&" +
                urlEncodeParameter("gender", gender) + "&" +
                urlEncodeParameter("genderError", genderError) + "&" +
                urlEncodeParameter("message", msg);
    }

    private static String urlEncodeParameter(String param, String value) {
        try {
            String encodedValue = URLEncoder.encode(value, Config.URL_ENCODING);
            return param + "=" + encodedValue;
        } catch (UnsupportedEncodingException e) {
            return param + "=" + "unsuppEncoding";
        }
    }

    public String getFirstNameError() {
        return firstNameError;
    }

    private void setFirstNameError(String firstNameError) {
        this.firstNameError = firstNameError;
    }

    public String getLastNameError() {
        return lastNameError;
    }

    private void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    private void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getGenderError() {
        return genderError;
    }

    private void setGenderError(String genderError) {
        this.genderError = genderError;
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    private void setGender(String gender) {
        this.gender = gender;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public String getMessage() {
        return msg;
    }
}
