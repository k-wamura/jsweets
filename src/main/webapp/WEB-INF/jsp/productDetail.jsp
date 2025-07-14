<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ product.name }-商品詳細</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="product-detail">
		<img class="product-image" src="${ product.imagePath }"
			alt="${ product.name }">
		<h1>${ product.name }</h1>
		<p>${ product.description }</p>
		<p>価格：${ product.price }円</p>
		<p>在庫：${ product.stock }個</p>

		<form action="addcart" method="post">
			<input type="hidden" name="productId" value="${product.id}">
			<label>
				数量: <input type="number" name="quantity" value="1"min="1" max="${product.stock}">
			</label>
			<button type="submit">カートに追加</button>
		</form>
	</div>
</body>
</html>