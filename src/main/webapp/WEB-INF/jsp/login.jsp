<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>ログイン</title></head>
<body>
<h2>ログイン</h2>
<form action="login" method="post">
    Email：<input type="email" name="email" required><br>
    Password：<input type="password" name="password" required><br>
    <input type="submit" value="ログイン">
</form>

<c:if test="${not empty errorMsg}">
    <p style="color:red">${errorMsg}</p>
</c:if>
</body>
</html>
