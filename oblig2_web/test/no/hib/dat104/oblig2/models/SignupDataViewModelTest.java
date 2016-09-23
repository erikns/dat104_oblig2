package no.hib.dat104.oblig2.models;

import junit.framework.AssertionFailedError;
import org.junit.Test;

import javax.swing.*;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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

    private static void assertStringContains(String s, String contains) {
        if (!s.contains(contains)) {
            throw new AssertionFailedError("Expected '" + s + "' to contain '" + contains + "', but didn't");
        }
    }
}
