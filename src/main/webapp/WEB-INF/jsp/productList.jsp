<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>商品一覧</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/products.css">
</head>
<body class="with-header">
    <jsp:include page="header.jsp"></jsp:include>

    <div class="product-grid">
        <c:forEach var="product" items="${products}">
            <div class="product-card">
                <a href="productDetail?id=${product.id}">
                    <img class="product-img" src="${product.imagePath}" alt="${product.name}" />
                    <div class="product-info">
                        <div class="name">${product.name}</div>
                        <div class="price">¥${product.price}</div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</body>
</html>
