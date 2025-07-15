<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
</head>
<body>
	<h1>カート情報</h1>
	<c:if test="${ empty cart }">
		<p>カートは空です</p>
	</c:if>
	<c:if test="${ not empty cart }">
	<form action="buy" method="post">
	  	<!-- totalの初期化 -->
		<c:set var="total" value="0" scope="page" />
		<table border="1">
			<tr>
				<th>商品名</th>
				<th>価格</th>
				<th>数量</th>
				<th>小計</th>
			</tr>
			<c:forEach var="item" items="${ cart.values() }">
			<!-- 合計金額の算出 -->
			<c:set var="subtotal" value="${ item.product.price * item.quantity }" />
			<c:set var="total" value="${ total + subtotal }" />
			
			<tr>
				<td>${ item.product.name }</td>
				<td>${ item.product.price }</td>
				<td>${ item.quantity }</td>
				<td>${ subtotal }</td>
			</tr>
			</c:forEach>
		</table>
		<p>合計金額：<c:out value="${ total }"></c:out>円</p>
		<button type="submit">購入する</button>
	</form>
	<form action="cartClear" method="post">
		<button type="submit">カートクリア</button>
	</form>
	</c:if>
</body>
</html>