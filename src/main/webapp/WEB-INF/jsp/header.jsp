<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<a href="${pageContext.request.contextPath}/top">
		<img alt="和菓子" src="${pageContext.request.contextPath}/images/logo.jpg">
	</a>
	<nav class="navigation">
		<a href="${pageContext.request.contextPath}/productList">Products</a>
		<a href="#">About</a> 
		<a href="#">Contact</a> 
		<a href="${pageContext.request.contextPath}/cart"><ion-icon name="cart-outline"></ion-icon></a>
		<c:choose>
			<c:when test="${ not empty loginUser }">
				<a href="${pageContext.request.contextPath}/mypage"><ion-icon name="person-outline"></ion-icon></a>
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
