<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="msg" scope="request" class="java.lang.String"/>
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
    <h1>Innlogging</h1>
    <p>Det er kun registrerte deltagere som får se deltakerlisten. Logg inn ved å angi
    mobilnummeret ditt</p>
    <p><span class="error">${msg}</span></p>
    <form method="post" action="login">
        <p>Mobil: <input type="text" name="phone"></p>
        <p><input type="submit" value="Logg inn"></p>
    </form>
</article>
</body>
</html>
