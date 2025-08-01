<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ product.name }-商品詳細</title>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/style.css">
<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho+B1&display=swap" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body class="with-header">
	<jsp:include page="header.jsp"></jsp:include>
	
	<h1>商品詳細 / Product Detail</h1>
	
	<div class="product-detail">
		<div class="product-left">
			<img class="product-image" src="${ product.imagePath }" alt="${ product.name }">
		</div>
		<div class="product-right">
			<h1>${ product.name }</h1>
			<p>${ product.description }</p>
			<p>価格：<span class="price">${ product.price }</span>円</p>
			<c:if test="${ product.stock == 0 }">在庫なし</c:if>

			<c:if test="${ product.stock != 0 }">
				<form action="cart" method="post">
					<input type="hidden" name="productId" value="${product.id}">
					<label>数量: </label>
					<select name="quantity" id="quantity">
					  <c:forEach var="i" begin="1" end="${product.stock}">
					    <option value="${i}">${i}</option>
					  </c:forEach>
					</select>
					<br>
					<button type="submit" class="add-to-cart">カートに追加</button>
				</form>
			</c:if>
		</div>
	</div>

</body>
</html>