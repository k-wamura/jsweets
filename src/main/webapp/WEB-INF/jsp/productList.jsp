<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>商品一覧</h1>
<div class="product-group">
	<c:forEach var="product" items="${ products }">
		<div class="product-card">
			<a href="productDetail?id=${ product.id }">
				<img class="product-image" src="${ product.imagePath }" alt="${ product.name }">
			</a>
			<h2 class="product-name">${ product.name }</h2>
			<p class="product-price">価格：${ product.price }円</p>
			<p class="product-stock">在庫：${ product.stock }個</p>
		</div>
	</c:forEach>
</div>
</body>
</html>