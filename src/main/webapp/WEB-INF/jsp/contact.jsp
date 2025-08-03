<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お問い合わせ</title>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/contact.css">
<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho+B1&display=swap" rel="stylesheet">
</head>
<body class="with-header">
<jsp:include page="header.jsp"></jsp:include>
	
	<div class="contact-container">
		<h2>【 CONTACT 】</h2>
		<h1>お問い合わせ</h1>
		
		<form action="contact" method="post">
			<label>お問い合わせ項目</label>
			<select name="contactItem">
				<option value="">お問い合わせ項目を選択</option>
				<c:forEach var="item" items="${ contactItem }">
				<option value="">${ item }</option>
				</c:forEach>
			</select><br>
			
			<label>お名前</label>
			<input type="text" name="name" placeholder="お名前をご入力ください" required><br>
			
			<label>都道府県</label>
			<select name="prefecture">
				<option value="">都道府県を選択</option>
				<c:forEach var="p" items="${ prefectures }">
				<option value="">${ p }</option>
				</c:forEach>
			</select><br>
			
			<label>メールアドレス</label>
			<input type="email" name="mail" placeholder="xxxxxxx@ooo.com"><br>
			
			<label>お問い合わせ内容</label>
	        <textarea name="message" rows="6" required placeholder="お問い合わせをご入力ください"></textarea><br>
	        
	        <button type="submit">送信</button>
		</form>
	</div>
</body>
</html>