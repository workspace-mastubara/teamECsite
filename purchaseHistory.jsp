<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/latte.css">
<link rel="stylesheet" type="text/css" href="./css/table.css">
<link rel="stylesheet" type="text/css" href="./css/message.css">
<title>商品購入履歴画面</title>
</head>
<body>

	<div id="header">
		<jsp:include page="header.jsp"/>
	</div>
	<div id="contents">
		<div id="top">
			<h1>商品購入履歴画面</h1>
		</div>

		<div id="text-center">

			<s:if test="purchaseHistoryInfoDTOList.size() == 0">
				<div class="error">
					<s:property value="message" />
				</div>
			</s:if>

			<s:else>
				<table class="r-table">
					<tbody>

						<tr>
							<th>商品名</th>
							<th>ふりがな</th>
							<th>商品画像</th>
							<th>発売会社名</th>
							<th>発売年月日</th>
							<th>値段</th>
							<th>個数</th>
							<th>合計金額</th>
							<th>宛先名前</th>
							<th>宛先住所</th>
						</tr>

						<s:iterator value="purchaseHistoryInfoDTOList">
							<tr>
								<td><s:property value="productName" /></td>
								<td><s:property value="productNameKana" /></td>
								<td><img
									src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'
									width="50px" height="50px" /></td>
								<td><s:property value="releaseCompany" /></td>
								<td><s:property value="releaseDate" /></td>
								<td><s:property value="price" />円</td>
								<td><s:property value="productCount" />個</td>
								<td><s:property value="totalPrice" />円</td>
								<td>
									<s:property value="familyName" /><span> </span>
									<s:property value="firstName" />
								</td>
								<td><s:property value="userAddress" /></td>
							</tr>
						</s:iterator>

					</tbody>
					</table>

					<s:form action="DeletePurchaseHistoryAction">
						<input type="hidden" value="<s:property value='userId'/>">
							<div class="submit_btn_box">
								<s:submit value="履歴削除" class="submit_btn"/>
							</div>
					</s:form>
			</s:else>

		</div>
	</div>
	<div id="footer"></div>
</body>
</html>