<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>購入履歴詳細</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/purchaseHistoryDetail.css">
</head>
<body class="with-header">
	<jsp:include page="../header.jsp"></jsp:include>
    <div class="detail-container">
        <h1>購入履歴詳細</h1>

        <div class="order-info">
            <p><strong>購入番号：</strong><c:out value="${order.orderId}" /></p>
            <p><strong>購入日：</strong><c:out value="${order.orderDate}" /></p>
            <p><strong>合計金額：</strong>¥<c:out value="${order.totalPrice}" /></p>
        </div>

        <table class="item-table">
            <thead>
                <tr>
                    <th>商品名</th>
                    <th>価格</th>
                    <th>数量</th>
                    <th>小計</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${order.orderList}">
                    <tr>
                        <td><c:out value="${item.name}" /></td>
                        <td>¥<c:out value="${item.price}" /></td>
                        <td><c:out value="${item.quantity}" /></td>
                        <td>¥<c:out value="${item.price * item.quantity}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="back-link">
            <a href="purchaseHistory">← 購入履歴に戻る</a>
        </div>
    </div>
</body>
</html>
