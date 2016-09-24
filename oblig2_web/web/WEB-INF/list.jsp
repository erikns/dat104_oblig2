<jsp:useBean id="vm" scope="request" type="java.util.List<no.hib.dat104.oblig2.models.ParticipantPublicViewModel>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h1>Deltakerliste</h1>
    <table>
        <tr><th>Kjønn</th><th>Navn</th></tr>
        <c:forEach items="${vm}" var="participant">
            <tr>
                <td>${participant.gender}</td><!-- TODO: add gender symbol -->
                <td>${participant.firstName} ${participant.lastName}</td>
            </tr>
        </c:forEach>
    </table>
    <ul class="nav">
        <li><a href="logout">Ferdig</a></li>
    </ul>
</article>
</body>
</html>
