<!-- 修正済みカート画面 JSP -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/stepbar.css">
<link rel="stylesheet" href="css/cart.css">
<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho+B1&display=swap" rel="stylesheet">
</head>
<body class="with-header">
	<jsp:include page="header.jsp"></jsp:include>

	<div class="cart-container">
	
		<c:if test="${ empty cart }">
			<p class="empty-msg">カートは空です</p>
		</c:if>


		<c:if test="${ not empty cart }">
			<!-- ステップバー -->
			<div class="step-bar">
				<div class="step current">
					<div class="circle">1</div>
					<div class="label">カートの商品</div>
				</div>
				<div class="step">
					<div class="circle">2</div>
					<div class="label">ご注文確認</div>
				</div>
				<div class="step">
					<div class="circle">3</div>
					<div class="label">完了</div>
				</div>
			</div>



			<!-- カート情報 -->
			<div class="cart-header">
				カートに入っている商品（
				<c:out value="${ cart.size() }" />
				点）
			</div>
			<c:set var="total" value="0" scope="page" />

			<c:forEach var="item" items="${ cart.values() }">
				<c:set var="subtotal"
					value="${ item.product.price * item.quantity }" />
				<c:set var="total" value="${ total + subtotal }" />

				<div class="cart-item">
					<img src="${ item.product.imagePath }" alt="商品画像">
					<form action="updateCart" method="post" style="display: inline;">
						<input type="hidden" name="id" value="${ item.product.id }" />
						<button class="remove-btn" name="action" value="remove">
							<ion-icon name="close-outline"></ion-icon>
						</button>
					</form>
					<div class="item-info">
						<div class="item-name">${ item.product.name }</div>
						<div class="item-price">¥${ item.product.price }</div>
						<div class="item-qty">
							<form action="updateCart" method="post">
								<input type="hidden" name="id" value="${ item.product.id }" />
								<button class="qty-btn" name="action" value="decrease">
									<ion-icon name="remove-circle-outline"></ion-icon>
								</button>
							</form>
							<span>${ item.quantity }</span>
							<form action="updateCart" method="post">
								<input type="hidden" name="id" value="${ item.product.id }" />
								<button class="qty-btn" name="action" value="increase">
									<ion-icon name="add-circle-outline"></ion-icon>
								</button>
							</form>
						</div>
					</div>
				</div>
			</c:forEach>

			<div class="summary">
				<div class="summary-row total">
					<span>注文合計</span><span>¥${ total + shipping }</span>
				</div>
			</div>

			<form action="buy" method="get">
				<button type="submit" class="checkout-btn">ご購入の手続きへ</button>
			</form>
		</c:if>
	</div>

	<!-- アイコンJS -->
	<script type="module"
		src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>
