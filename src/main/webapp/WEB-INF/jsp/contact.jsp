<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お問い合わせ</title>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/contact.css">
<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho+B1&display=swap" rel="stylesheet">
</head>
<body class="with-header">
<jsp:include page="header.jsp"></jsp:include>
	
	<div class="contact-container">
		<h2>【 CONTACT 】</h2>
		<h1>お問い合わせ</h1>
		
		<c:if test="${ not empty errors }">
         	<c:forEach var="msg" items="${ errors }">
         		<p style="color: red">${ msg }</p>
         	</c:forEach>
         </c:if>
		
		<form action="contact" method="post">
			<label>お問い合わせ項目</label>
			<select name="contactItem" required>
				<option value="" disabled ${empty contact.item ? 'selected' : ''} class="placeholder">お問い合わせ項目を選択</option>
				<c:forEach var="item" items="${ contactItem }">
				<option value="${ item.id }" ${item == contact.item ? 'selected' : ''}>${ item.name }</option>
				</c:forEach>
			</select><br>
			
			<label>お名前</label>
			<input type="text" name="name" value="${contact.name}" placeholder="お名前をご入力ください" required><br>
			
			<label>都道府県</label>
			<select name="prefecture" required>
				<option value="" disabled ${empty contact.prefecture ? 'selected' : ''} class="placeholder">都道府県を選択</option>
				<option value="北海道" ${contact.prefecture == '北海道' ? 'selected' : ''}>北海道</option>
				<option value="青森県" ${contact.prefecture == '青森県' ? 'selected' : ''}>青森県</option>
				<option value="岩手県" ${contact.prefecture == '岩手県' ? 'selected' : ''}>岩手県</option>
				<option value="宮城県" ${contact.prefecture == '宮城県' ? 'selected' : ''}>宮城県</option>
				<option value="秋田県" ${contact.prefecture == '秋田県' ? 'selected' : ''}>秋田県</option>
				<option value="山形県" ${contact.prefecture == '山形県' ? 'selected' : ''}>山形県</option>
				<option value="福島県" ${contact.prefecture == '福島県' ? 'selected' : ''}>福島県</option>
				<option value="茨城県" ${contact.prefecture == '茨城県' ? 'selected' : ''}>茨城県</option>
				<option value="栃木県" ${contact.prefecture == '栃木県' ? 'selected' : ''}>栃木県</option>
				<option value="群馬県" ${contact.prefecture == '群馬県' ? 'selected' : ''}>群馬県</option>
				<option value="埼玉県" ${contact.prefecture == '埼玉県' ? 'selected' : ''}>埼玉県</option>
				<option value="千葉県" ${contact.prefecture == '千葉県' ? 'selected' : ''}>千葉県</option>
				<option value="東京都" ${contact.prefecture == '東京都' ? 'selected' : ''}>東京都</option>
				<option value="神奈川県" ${contact.prefecture == '神奈川県' ? 'selected' : ''}>神奈川県</option>
				<option value="新潟県" ${contact.prefecture == '新潟県' ? 'selected' : ''}>新潟県</option>
				<option value="富山県" ${contact.prefecture == '富山県' ? 'selected' : ''}>富山県</option>
				<option value="石川県" ${contact.prefecture == '石川県' ? 'selected' : ''}>石川県</option>
				<option value="福井県" ${contact.prefecture == '福井県' ? 'selected' : ''}>福井県</option>
				<option value="山梨県" ${contact.prefecture == '山梨県' ? 'selected' : ''}>山梨県</option>
				<option value="長野県" ${contact.prefecture == '長野県' ? 'selected' : ''}>長野県</option>
				<option value="岐阜県" ${contact.prefecture == '岐阜県' ? 'selected' : ''}>岐阜県</option>
				<option value="静岡県" ${contact.prefecture == '静岡県' ? 'selected' : ''}>静岡県</option>
				<option value="愛知県" ${contact.prefecture == '愛知県' ? 'selected' : ''}>愛知県</option>
				<option value="三重県" ${contact.prefecture == '三重県' ? 'selected' : ''}>三重県</option>
				<option value="滋賀県" ${contact.prefecture == '滋賀県' ? 'selected' : ''}>滋賀県</option>
				<option value="京都府" ${contact.prefecture == '京都府' ? 'selected' : ''}>京都府</option>
				<option value="大阪府" ${contact.prefecture == '大阪府' ? 'selected' : ''}>大阪府</option>
				<option value="兵庫県" ${contact.prefecture == '兵庫県' ? 'selected' : ''}>兵庫県</option>
				<option value="奈良県" ${contact.prefecture == '奈良県' ? 'selected' : ''}>奈良県</option>
				<option value="和歌山県" ${contact.prefecture == '和歌山県' ? 'selected' : ''}>和歌山県</option>
				<option value="鳥取県" ${contact.prefecture == '鳥取県' ? 'selected' : ''}>鳥取県</option>
				<option value="島根県" ${contact.prefecture == '島根県' ? 'selected' : ''}>島根県</option>
				<option value="岡山県" ${contact.prefecture == '岡山県' ? 'selected' : ''}>岡山県</option>
				<option value="広島県" ${contact.prefecture == '広島県' ? 'selected' : ''}>広島県</option>
				<option value="山口県" ${contact.prefecture == '山口県' ? 'selected' : ''}>山口県</option>
				<option value="徳島県" ${contact.prefecture == '徳島県' ? 'selected' : ''}>徳島県</option>
				<option value="香川県" ${contact.prefecture == '香川県' ? 'selected' : ''}>香川県</option>
				<option value="愛媛県" ${contact.prefecture == '愛媛県' ? 'selected' : ''}>愛媛県</option>
				<option value="高知県" ${contact.prefecture == '高知県' ? 'selected' : ''}>高知県</option>
				<option value="福岡県" ${contact.prefecture == '福岡県' ? 'selected' : ''}>福岡県</option>
				<option value="佐賀県" ${contact.prefecture == '佐賀県' ? 'selected' : ''}>佐賀県</option>
				<option value="長崎県" ${contact.prefecture == '長崎県' ? 'selected' : ''}>長崎県</option>
				<option value="熊本県" ${contact.prefecture == '熊本県' ? 'selected' : ''}>熊本県</option>
				<option value="大分県" ${contact.prefecture == '大分県' ? 'selected' : ''}>大分県</option>
				<option value="宮崎県" ${contact.prefecture == '宮崎県' ? 'selected' : ''}>宮崎県</option>
				<option value="鹿児島県" ${contact.prefecture == '鹿児島県' ? 'selected' : ''}>鹿児島県</option>
				<option value="沖縄県" ${contact.prefecture == '沖縄県' ? 'selected' : ''}>沖縄県</option>
			</select><br>
			
			<label>メールアドレス</label>
			<input type="email" name="mail" value="${contact.mail}" placeholder="xxxxxxx@ooo.com" required><br>
			
			<label>お問い合わせ内容</label>
	        <textarea name="message" rows="6" required value="${contact.message}" placeholder="お問い合わせをご入力ください" required></textarea><br>
	        
	        <button type="submit">送信</button>
		</form>
	</div>
</body>
</html>