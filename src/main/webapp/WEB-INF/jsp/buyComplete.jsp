<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入完了</title>
</head>
<body>
	<h1>ご購入ありがとうございました。</h1>
	<p>購入商品</p>
	<table border="1">
			<tr>
				<th>商品名</th>
				<th>価格</th>
				<th>数量</th>
				<th>小計</th>
			</tr>
			<c:forEach var="item" items="${ cart.values() }">
			<tr>
				<td>${ item.product.name }</td>
				<td>${ item.product.price }</td>
				<td>${ item.quantity }</td>
				<td>${ subtotal }</td>
			</tr>
			</c:forEach>
		</table>
		<p>合計金額：<c:out value="${ total }"></c:out>円</p>
	<a href="productList">商品一覧へ戻る</a>
</body>
</html>