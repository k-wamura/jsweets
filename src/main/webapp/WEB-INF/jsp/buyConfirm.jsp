<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入確認</title>
<link rel="stylesheet" href="css/buyConfirm.css">
<link rel="stylesheet" href="css/stepbar.css">
<link rel="stylesheet" href="css/header.css">
</head>
<body class="with-header">
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">

		<!-- ステップバー -->
		<div class="step-bar">
			<div class="step">
				<div class="circle">1</div>
				<div class="label">カートの商品</div>
			</div>
			<div class="step current">
				<div class="circle">2</div>
				<div class="label">ご注文確認</div>
			</div>
			<div class="step">
				<div class="circle">3</div>
				<div class="label">完了</div>
			</div>
		</div>



		<h2>ご注文内容のご確認</h2>
		<p>以下の商品をご確認ください：</p>

		<table>
			<tr>
				<th>商品名</th>
				<th>価格</th>
				<th>数量</th>
				<th>小計</th>
			</tr>
			<c:forEach var="item" items="${ cart.values() }">
				<tr>
					<td>${ item.product.name }</td>
					<td>¥${ item.product.price }</td>
					<td>${ item.quantity }</td>
					<td>¥${ item.product.price * item.quantity }</td>
				</tr>
			</c:forEach>
		</table>

		<p class="total">
			合計金額：¥
			<c:out value="${ totalPrice }" />
			円
		</p>

		<div class="actions">
			<a href="productList">← 商品一覧へ戻る</a>
			<form action="buy" method="post" style="display: inline;">
				<button type="submit">購入確定</button>
			</form>
		</div>
	</div>

</body>
</html>
