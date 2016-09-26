<jsp:useBean id="vm" scope="request" type="java.util.List<no.hib.dat104.oblig2.models.ParticipantPublicViewModel>"/>
<jsp:useBean id="loggedInUser" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Festpåmelding</title>
    <script src="https://use.fontawesome.com/a273eb4620.js"></script>
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
        <tr class="top"><th>Kjønn</th><th>Navn</th></tr>
        <c:forEach items="${vm}" var="participant">
            <c:choose>
                <c:when test="${participant.isUser(loggedInUser)}">
                    <c:choose>
                        <c:when test="${participant.paid}">
                            <tr class="green">
                        </c:when>
                        <c:otherwise>
                            <tr class="red">
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <tr>
                </c:otherwise>
            </c:choose>
                <td class="gender">
                    <c:choose>
                        <c:when test="${participant.gender == 'F'}">
                            <i class="fa fa-venus fa-fw"></i>
                        </c:when>
                        <c:when test="${participant.gender == 'M'}">
                            <i class="fa fa-mars fa-fw"></i>
                        </c:when>
                        <c:otherwise>
                            <i class="fa fa-question fa-fw"></i>
                        </c:otherwise>
                    </c:choose>
                </td>
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
