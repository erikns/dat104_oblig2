<jsp:useBean id="vm" scope="request" type="java.util.List<no.hib.dat104.oblig2.models.ParticipantEntity>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <tr class="top"><th>Navn</th><th>Mobil</th><th>Betalingsstatus</th></tr>
        <c:forEach items="${vm}" var="participant">
            <tr>
                <td>${participant.firstName} ${participant.lastName}</td>
                <td>
                    <c:set var="phone" value="${participant.phone} "/>
                    <c:set var="ph0" value="${fn:substring(phone, 0, 3)}" />
                    <c:set var="ph1" value="${fn:substring(phone, 3, 5)}" />
                    <c:set var="ph2" value="${fn:substring(phone, 5, 8)}" />
                    ${ph0} ${ph1} ${ph2}
                </td>

                <%--Hvem har betalt --%>
                <c:choose>
                    <c:when test="${participant.paid}">
                        <td>Betaling mottatt</td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <form action="admin" method="post">
                                <input type="hidden" name="id" value=${participant.phone}  />
                                <input type="submit" value="Registrer-betaling" />
                            </form>
                        </td>
                    </c:otherwise>
                </c:choose>

            </tr>
        </c:forEach>
    </table>

    <ul class="nav">
        <li><a href="logout">Ferdig</a></li>
    </ul>
</article>
</body>
</html>
