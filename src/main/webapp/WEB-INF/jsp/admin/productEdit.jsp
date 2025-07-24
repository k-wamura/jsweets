<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>商品編集</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <div class="admin-container">
        <h1>商品編集</h1>

        <form action="productEdit" method="post">
            <input type="hidden" name="id" value="${product.id}" />

			<img alt="" src="${pageContext.request.contextPath}/${ product.imagePath }">
            <div class="form-group">
                <label>商品名:</label>
                <input type="text" name="name" value="${product.name}" required />
            </div>

            <div class="form-group">
                <label>説明:</label>
                <textarea name="description" rows="4">${product.description}</textarea>
            </div>

            <div class="form-group">
                <label>価格 (円):</label>
                <input type="number" name="price" value="${product.price}" required min="0" />
            </div>

            <div class="form-group">
                <label>在庫数:</label>
                <input type="number" name="stock" value="${product.stock}" required min="0" />
            </div>

            <div class="form-group">
                <label>画像パス:</label>
                <input type="text" name="imagePath" value="${product.imagePath}" />
            </div>

            <div class="form-buttons">
                <button type="submit">更新</button>
                <a href="${pageContext.request.contextPath}/admin/productList">キャンセル</a>
            </div>
        </form>
    </div>
</body>
</html>
