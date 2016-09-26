<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="msg" scope="request" class="java.lang.String"/>
<html>
<head>
    <title>KassererLogin</title>
    <link href="https://fonts.googleapis.com/css?family=Lobster|Noto+Serif" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
</head>
<body>
<header>
    <div>Kassererlogin</div>
</header>
<article>
    <h1>Login for kasserer</h1>
    <p>Skriv inn admin passord for å komme til kasserer-siden</p>
    <p><span class="error">${msg}</span></p>
    <form method="post" action="adminlogin">
        <p>Passord: <input type="text" name="adminpassord"></p>
        <p><input type="submit" value="Logg inn"></p>
    </form>
</article>
</body>
</html>
