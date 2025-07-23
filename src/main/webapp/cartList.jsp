<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Shopping Cart</title>
<link rel="stylesheet" type="text/css" href="css/cart.css">
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>

	<div class="container">
		<h1>Shopping Cart</h1>
		<div class="cart">
			<div class="products">
				<c:forEach var="item" items="${ cart.values() }">
				<div class="product">
					<img src="${item.product.imagePath}">
					<div class="product-info">
						<h3 class="product-name">${ item.product.name }</h3>
						<h4 class="product-price">${ item.product.price }</h4>
						<h4 class="product-offer">40%</h4>
						<p class="product-quantity">
							Qnt: <input value="${ item.quantity }" name="">
						</p>
						<p class="product-remove">
							<i class="fa fa-trash" aria-hidden="true"></i>
							<span class="remove">Remove</span>
						</p>
					</div>
				</div>
				</c:forEach>
			</div>
			
			<div class="cart-total">
				<p><span>Total Price</span> <span>${ totalPrice }</span></p>
				<p><span>Number of Items</span> <span>${ cart.size() }</span></p>
				<a href="#">Buy</a>
			</div>
		</div>
	</div>

</body>
</html>