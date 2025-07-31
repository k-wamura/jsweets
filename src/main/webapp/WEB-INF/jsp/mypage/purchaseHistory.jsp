<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>購入履歴</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/history.css">
</head>
<body class="with-header">
	<jsp:include page="../header.jsp"></jsp:include>
    <div class="history-container">
        <h1>購入履歴</h1>

        <c:forEach var="order" items="${orderList}">
            <div class="order-block">
                <table>
                    <tr>
                        <th>購入日</th>
                        <td><c:out value="${order.orderDate}" /></td>
                    </tr>
                    <tr>
                        <th>購入番号</th>
                        <td><c:out value="${order.orderId}" /></td>
                    </tr>
                    <tr>
                        <th>合計金額</th>
                        <td>¥<c:out value="${order.totalPrice}" /></td>
                    </tr>
                </table>
                <div class="detail-link">
                    <a href="purchaseHistoryDetail?orderId=${order.orderId}">詳細</a>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
