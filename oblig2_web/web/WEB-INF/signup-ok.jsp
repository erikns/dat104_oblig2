<jsp:useBean id="vm" scope="request" type="no.hib.dat104.oblig2.models.SignupDataViewModel"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h1>Påmeldingsbekreftelse</h1>
    <p>Påmeldingen er mottatt for <span class="important">${vm.firstName} ${vm.lastName}</span>
        med mobilnummer <span class="important">${vm.phone}</span></p>
    <p class="important">NB! Husk å betale til kassereren før festen!</p>
    <ul class="nav">
        <li><a href="list">Gå til deltakerlisten</a></li>
    </ul>
</article>
</body>
</html>
