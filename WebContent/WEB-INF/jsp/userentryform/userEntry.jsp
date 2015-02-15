<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<html>
<head>
<title>유저등록 화면</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/cart_header.jsp"%>
<div align="center" class="body">
<h2>유저등록 화면</h2>
<form:form modelAttribute="user" method="post" action="../userentryform/userEntry.html">
	<spring:hasBindErrors name="user">
		<font color="red"><c:forEach items="${errors.globalErrors}"
			var="error">
			<spring:message code="${error.code}" />
		</c:forEach> </font>
	</spring:hasBindErrors>
	<table>
		<tr height="40px">
			<td>유저ID</td>
			<td><form:input path="userId" maxlength="20" cssClass="userId" /><font
				color="red"><form:errors path="userId" /></font></td>
		</tr>
		<tr height="40px">
			<td>패스워드</td>
			<td><form:password path="password" maxlength="20" cssClass="password" /><font
				color="red"><form:errors path="password" /></font></td>
		</tr>
		<tr height="40px">
			<td>이름</td>
			<td><form:input path="userName" maxlength="20" cssClass="userName" /><font
				color="red"><form:errors path="userName" /></font></td>
		</tr>
		<tr height="40px">
			<td>우편번호</td>
			<td><form:input path="postCode" maxlength="8" cssClass="postCode" /><font
				color="red"><form:errors path="postCode" /></font></td>
		</tr>
		<tr height="40px">
			<td>주소</td>
			<td><form:input path="address" maxlength="50" cssClass="address" /><font
				color="red"><form:errors path="address" /></font></td>
		</tr>
		<tr height="40px">
			<td>E-MAIL</td>
			<td><form:input path="email" maxlength="50" cssClass="email" /><font color="red"><form:errors
				path="email" /></font></td>
		</tr>
		<tr height="40px">
			<td>직업</td>
			<td><form:select path="job" cssClass="jobs">
				<form:option value="사회인" label="사회인" />
				<form:option value="주부" label="주부" />
				<form:option value="학생" label="학생" />
				<form:option value="그외" label="그외" />
			</form:select></td>
		</tr>
		<tr height="40px">
			<td>생년월일</td>
			<td><form:input path="birthDay" maxlength="10" cssClass="birthDay" /><font
				color="red"><form:errors path="birthDay" /></font></td>
		</tr>
	</table>
	<table>
		<tr>
			<td height="40px" align="center"><input type="submit"
				name="btnSubmit" value="등록"></td>
			<td height="40px" align="center"><input type="reset"
				name="btnReset" value="리셋"></td>
		</tr>
	</table>
</form:form> <a href="index.html">■목록으로 돌아가기</a></div>
</body>
</html>