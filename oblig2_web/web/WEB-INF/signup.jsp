<%@ page import="no.hib.dat104.oblig2.models.SignupDataViewModel" %>
<jsp:useBean id="vm" scope="request" type="no.hib.dat104.oblig2.models.SignupDataViewModel"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private SignupDataViewModel getViewModel(HttpServletRequest request) {
        return (SignupDataViewModel) request.getAttribute("vm");
    }

    private String maleCheckedString(String gender) {
        return gender.equals("M") ? "checked" : "";
    }

    private String femaleCheckedString(String gender) {
        return gender.equals("F") ? "checked" : "";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Festpåmelding</title>
    <link href="https://fonts.googleapis.com/css?family=Lobster|Noto+Serif" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
</head>
<body>
<header>
    <div>Festpåmelding</div>
</header>
<article>
    <h1>Påmeldingsskjema</h1>
    <p>Fyll ut skjemaet for å melde deg på festen</p>

    <form method="post" action="signup">
        <p>Fornavn: <input type="text" name="firstName" value="${vm.firstName}">
            <span class="error">${vm.firstNameError}</span></p>
        <p>Etternavn: <input type="text" name="lastName" value="${vm.lastName}">
            <span class="error">${vm.lastNameError}</span></p>
        <p>Mobil: <input type="text" name="phone" value="${vm.phone}">
            <span class="error">${vm.phoneError}</span></p>
        <p>Kjønn: <input type="radio" name="gender" value="M"
            <%= maleCheckedString(getViewModel(request).getGender()) %>> Mann
            <input type="radio" name="gender" value="F"
            <%= femaleCheckedString(getViewModel(request).getGender()) %>> Kvinne
            <span class="error">${vm.genderError}</span></p>
        <p><input type="submit" value="Meld deg på!"></p>
    </form>
</article>
</body>
</html>
