<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<html>
<head>
<title>로그인 완료 화면</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/cart_header.jsp"%>
<div align="center" class="body">
<h2>로그인 완료 화면</h2>
환영해요,${loginUser.userName}씨！ <br>
<br>
<a href="../index/index.html">■목록으로 돌아가기 </a><br>
<a href="../checkout/checkout.html">■계산하러 가기 </a></div>
</body>
</html>