<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>マイページ</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/mypage.css">
</head>
<body class="with-header">
	<jsp:include page="../header.jsp"></jsp:include>
    <div class="mypage-container">
        <h1>MYPAGE</h1>
        <p>${loginUser.lName}${loginUser.fName} 様のマイページです</p>

        <div class="menu-grid">
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
        </div>
    </div>
    
    
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>
