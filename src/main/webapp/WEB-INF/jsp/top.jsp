<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>和菓子Top</title>
    <link rel="stylesheet" href="css/top.css">
    <link rel="stylesheet" href="css/header.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<c:if test="${ empty loginUser }">
	    <div class="wrapper active-popup">
	        <span class="icon-close">
	            <ion-icon name="close"></ion-icon>
	        </span>
	
	        <div class="form-box login">
	            <h2>Login</h2>
	            <c:if test="${ errorMsg != null }">
	            	<p style="color: red">${ errorMsg }</p>
	            </c:if>
	            <form action="login" method="post">
	                <div class="input-box">
	                    <span class="icon"><ion-icon name="mail"></ion-icon></span>
	                    <input type="email" name="email" required>
	                    <label>Email</label>
	                </div>
	                <div class="input-box">
	                    <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
	                    <input type="password" name="password" required>
	                    <label>Password</label>
	                </div>
	                <div class="remember-forgot">
	                    <label><input type="checkbox" name="">Remember me</label>
	                    <a href="#">Forgot Password?</a>
	                </div>
	                <button type="submit" class="btn">Login</button>
	                
	                <div class="login-register">
	                    <p>Don't have an account?<a href="#" class="register-link">Register</a></p>
	                </div>
	            </form>
	        </div>
	
	        <div class="form-box register">
	            <h2>Registration</h2>
	            <c:if test="${ not empty errors }">
	            	<c:forEach var="msg" items="${ errors }">
	            		<p style="color: red">${ msg }</p>
	            	</c:forEach>
	            </c:if>
	            <form action="register" method="post">
	                <div class="input-pair">
					    <div class="input-box">
					        <input type="text" name="lName" required>
					        <label>姓</label>
					    </div>
					    <div class="input-box">
					        <input type="text" name="fName" required>
					        <label>名</label>
					    </div>
					</div>
					
					<div class="input-pair">
					    <div class="input-box">
					        <input type="text" name="lNameKana" required>
					        <label>セイ</label>
					    </div>
					    <div class="input-box">
					        <input type="text" name="fNameKana" required>
					        <label>メイ</label>
					    </div>
					</div>

	                <div class="input-box">
	                    <input type="password" name="password" required>
	                    <label>パスワード</label>
	                </div>
	                <div class="input-box">
	                    <input type="text" name="prefecture" required>
	                    <label>都道府県</label>
	                </div>
	                <div class="input-box">
	                    <input type="text" name="city" required>
	                    <label>市区町村</label>
	                </div>
	                <div class="input-box">
	                    <input type="text" name="oAddress" required>
	                    <label>その他住所</label>
	                </div>
	                <div class="input-box">
	                    <input type="text" name="tel" required>
	                    <label>電話番号</label>
	                </div>
	                <div class="input-box">
	                    <input type="email" name="email" required>
	                    <label>メールアドレス</label>
	                </div>
	                <div class="remember-forgot">
	                    <label><input type="checkbox" name="">I agree to the terms & condisions</label>
	                </div>
	                <button type="submit" class="btn" onclick="alert('新規登録しますか？')">Register</button>
	                
	                <div class="login-register">
	                    <p>Already have an account?<a href="#" class="login-link">Login</a></p>
	                </div>
	            </form>
	        </div>
	    </div>
	</c:if>


    <script src="js/script.js"></script>
</body>
</html>