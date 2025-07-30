<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<h2 class="logo">Logo</h2>
	<nav class="navigation">
		<a href="top">Home</a> <a href="#">About</a> <a href="productList">Products</a>
		<a href="#">Contact</a> <a href="cart"><ion-icon
				name="cart-outline"></ion-icon></a>
		<c:choose>
			<c:when test="${ not empty loginUser }">
				<a href="#"><ion-icon name="person-outline"></ion-icon></a>
			</c:when>
			<c:otherwise>
				<button class="btnLogin-popup">Login</button>
			</c:otherwise>
		</c:choose>

	</nav>
</header>

<script type="module"
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
