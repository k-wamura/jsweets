<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>管理者ダッシュボード</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script>
		document.addEventListener("DOMContentLoaded", function () {
	    const logoutLink = document.getElementById("logout-link");
	    logoutLink.addEventListener("click", function (e) {
	        e.preventDefault();
	
	        if (!confirm("ログアウトしてもよろしいですか？")) {
	            return;
	        }
	
	        // フォームを作成してPOST送信
	        const form = document.createElement("form");
	        form.method = "POST";
	        form.action = logoutLink.getAttribute("href");
	
	        document.body.appendChild(form);
	        form.submit();
	    	});
		});
	</script>
</head>
<body>
    <div class="admin-container">
        <h1>管理者ダッシュボード</h1>
        <p>ようこそ、<c:out value="${loginUser.lName}${loginUser.fName}" /> さん（管理者）</p>

        <ul class="admin-menu">
            <li><a href="userList">ユーザー管理</a></li>
            <li><a href="productList">商品管理</a></li>
            <li><a href="orderList">注文管理</a></li>
            <li>
            	<a href="${pageContext.request.contextPath}/logout" id="logout-link">ログアウト</a>
            </li>
        </ul>
    </div>
</body>
</html>
