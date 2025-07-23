<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<link rel="stylesheet" href="css/products.css">
</head>
<body>
	<ul class="menu_list">
		<c:forEach var="product" items="${ products }">
			<a href="productDetail?id=${ product.id }">
				<li>
					<figure><img class="product-img" src="${ product.imagePath }" alt="" width="400" height="300"></figure>
					<dl>
						<dt>${ product.name }</dt>
						<dd class="summary">${ product.description }</dd>
						<dd class="price">¥${ product.price }</dd>
					</dl>
				</li>
			</a>
		</c:forEach>
	</ul>
</body>
</html>