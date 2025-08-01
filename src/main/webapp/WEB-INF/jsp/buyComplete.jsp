<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>購入完了</title>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/stepbar.css">
<link rel="stylesheet" href="css/buyComplete.css">
</head>
<body class="with-header">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">

		<!-- ステップバー -->
		<div class="step-bar">
			<div class="step">
				<div class="circle">1</div>
				<div class="label">カートの商品</div>
			</div>
			<div class="step">
				<div class="circle">2</div>
				<div class="label">ご注文確認</div>
			</div>
			<div class="step current">
				<div class="circle">3</div>
				<div class="label">完了</div>
			</div>
		</div>

		<div class="thanks">
			<h2>ご注文ありがとうございました</h2>
			<p>
				ただいま、ご注文の確認メールをお送りさせていただきました。<br>
				万一、ご確認メールが届かない場合は、トラブルの可能性もありますので<br>
				お手数ですがもう一度お問い合わせいただくか、お電話にてご連絡ください。<br>
				今後ともご愛顧賜りますようよろしくお願い申し上げます。
			</p>
		</div>

		<div class="order-number">
			ご注文番号：
			<c:out value="${orderId}" />
		</div>

		<a href="top" class="btn">トップページへ</a>
	</div>
</body>
</html>
