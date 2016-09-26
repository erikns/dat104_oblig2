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
    <h1>Log in som admin</h1>
    <p>Skriv inn adminpassord for å komme til kasserersiden hvor du kan registrere betaling</p>
    <p><span class="error">${msg}</span></p>
    <form method="post" action="adminlogin">
        <p>Passord: <input type="text" name="adminpassord"></p>
        <p><input type="submit" value="Logg inn"></p>
    </form>
</article>
</body>
</html>
