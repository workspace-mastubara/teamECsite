<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/latte.css">
<link rel="stylesheet" type="text/css" href="./css/table.css">
<title>マイページ画面</title>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp"/>
	</div>
	<div id="contents">
		<div id="top">
			<h1>マイページ画面</h1>
		</div>

		<div id="text-center">

			<table class="c-table">
				<tbody>

					<tr>
						<th>姓</th>
						<s:iterator value="userInfoDTO">
							<td><s:property value="familyName"/></td>
						</s:iterator>
					</tr>

					<tr>
						<th>名</th>
						<s:iterator value="userInfoDTO">
							<td><s:property value="firstName"/></td>
						</s:iterator>
					</tr>

					<tr>
						<th>ふりがな</th>
						<s:iterator value="userInfoDTO">
							<td>
								<s:property value="familyNameKana" /><span> </span>
								<s:property value="firstNameKana" />
							</td>
						</s:iterator>
					</tr>

					<tr>
						<th>性別</th>
						<s:iterator value="userInfoDTO">
							<s:if test="sex == 0">
								<td>男性</td>
							</s:if>

							<s:if test="sex == 1">
								<td>女性</td>
							</s:if>
						</s:iterator>
					</tr>

					<tr>
						<th>メールアドレス</th>
						<s:iterator value="userInfoDTO">
							<td><s:property value="email"/></td>
						</s:iterator>
					</tr>

				</tbody>
			</table>

			<s:form action="PurchaseHistoryAction">
				<div class="submit_btn_box">
					<s:submit value="購入履歴" class="submit_btn"/>
				</div>
			</s:form>

		</div>
	</div>
</body>
</html>