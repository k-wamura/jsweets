<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お問い合わせ完了</title>
<link rel="stylesheet" href="css/buyComplete.css">
<link rel="stylesheet" href="css/header.css">
</head>
<body class="with-header">
<jsp:include page="header.jsp"></jsp:include>
	<div class="contact-container">
		<div class="thanks">
			<h2>お問い合わせ</h2>
			<p>
				回答の送信が完了しました。<br>
				ご回答ありがとうございました。<br>
			</p>
		</div>	
		<a href="top" class="btn">トップページへ</a>
	</div>
</body>
</html>