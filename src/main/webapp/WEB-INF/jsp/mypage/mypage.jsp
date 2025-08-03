<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>マイページ</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/mypage.css">
    <link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho+B1&display=swap" rel="stylesheet">
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
<body class="with-header">
	<jsp:include page="../header.jsp"></jsp:include>
    <div class="mypage-container">
        <h1>MYPAGE</h1>
        <p>${loginUser.lName}${loginUser.fName} 様のマイページです</p>

        <div class="menu-grid">
        <c:if test="${loginUser.role == 1}">
            <a href="mypage/purchaseHistory" class="menu-item">
            	<ion-icon name="cart-outline"></ion-icon>
                <span>購入履歴</span>
            </a>
            <a href="#" class="menu-item">
                <ion-icon name="reload-outline"></ion-icon>
                <span>定期管理</span>
            </a>
            <a href="#" class="menu-item">
                <ion-icon name="create-outline"></ion-icon>
                <span>会員情報変更</span>
            </a>
            <a href="#" class="menu-item">
                <ion-icon name="home-outline"></ion-icon>
                <span>お届け先変更</span>
            </a>
            <a href="#" class="menu-item">
                <ion-icon name="card-outline"></ion-icon>
                <span>クレジットカード情報変更</span>
            </a>
            <a href="#" class="menu-item">
                <ion-icon name="lock-closed-outline"></ion-icon>
                <span>パスワード変更</span>
            </a>
        </c:if>
        
        <c:if test="${loginUser.role == 2}">
        	<a href="#" class="menu-item">
                <ion-icon name="body-outline"></ion-icon>
                <span>ユーザ管理</span>
            </a>
            <a href="admin/productList" class="menu-item">
                <ion-icon name="nutrition-outline"></ion-icon>
                <span>商品管理</span>
            </a>
            <a href="#" class="menu-item">
                <ion-icon name="notifications-outline"></ion-icon>
                <span>注文管理</span>
            </a>
        </c:if>
        
            <a href="logout" class="menu-item" id="logout-link">
                <ion-icon name="log-out-outline"></ion-icon>
                <span>ログアウト</span>
            </a>
            
        </div>
    </div>
    
    
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>
