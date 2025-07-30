<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/products.css">
</head>
<body class="with-header">
	<jsp:include page="header.jsp"></jsp:include>
	<ul class="menu_list">
		<c:forEach var="product" items="${ products }">
			<li><a href="productDetail?id=${ product.id }">
					<figure>
						<img class="product-img" src="${ product.imagePath }" alt="" />
					</figure>
					<dl>
						<dt>${ product.name }</dt>
						<dd class="summary">${ product.description }</dd>
						<dd class="price">¥${ product.price }</dd>
					</dl>
			</a></li>
		</c:forEach>
	</ul>
</body>
</html>