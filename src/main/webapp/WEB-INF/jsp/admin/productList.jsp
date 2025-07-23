<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>商品管理</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
  <div class="admin-container">
    <h1>商品管理</h1>
    <a href="productAdd" class="btn">＋ 新規商品追加</a>

    <table class="product-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>商品名</th>
          <th>価格</th>
          <th>在庫</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="product" items="${productList}">
          <tr>
            <td><c:out value="${product.id}" /></td>
            <td><c:out value="${product.name}" /></td>
            <td>¥<c:out value="${product.price}" /></td>
            <td><c:out value="${product.stock}" /></td>
            <td>
              <a href="productEdit?id=${product.id}" class="btn-small">編集</a>
              <a href="productDelete?id=${product.id}" class="btn-small btn-danger" onclick="return confirm('本当に削除しますか？')">削除</a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <p><a href="dashboard">← 管理メニューに戻る</a></p>
  </div>
</body>
</html>
