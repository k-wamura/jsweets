<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>新商品追加</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <div class="admin-container">
        <h1>新商品追加</h1>

		<c:if test="${ errorMsg != null }">
			<p style="color: red">${ errorMsg }</p>
		</c:if>
        <form action="${pageContext.request.contextPath}/admin/productAdd" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label>商品名:</label>
                <input type="text" name="name" required>
            </div>

            <div class="form-group">
                <label>価格 (円):</label>
                <input type="number" name="price" required min="0">
            </div>

            <div class="form-group">
                <label>在庫数:</label>
                <input type="number" name="stock" required min="0">
            </div>

            <div class="form-group">
                <label>説明:</label>
                <textarea name="description" rows="4"></textarea>
            </div>

            <div class="form-group">
                <label>商品画像:</label>
                <input type="file" name="image" accept="image/*">
            </div>

            <div class="form-buttons">
                <button type="submit">登録</button>
                <a href="${pageContext.request.contextPath}/admin/productList">キャンセル</a>
            </div>
        </form>
    </div>
</body>
</html>
