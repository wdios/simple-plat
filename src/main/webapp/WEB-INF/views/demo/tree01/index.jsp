<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
    <link rel="icon" href="${path}/resources/img/favicon.ico" mce_href="${path}/resources/img/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="${path}/resources/img/favicon.ico" mce_href="${path}/resources/img/favicon.ico" type="image/x-icon">
		<title>DEMO 页 - 树</title>
    
    <script type="text/javascript" src="${path}/resources/js/tree/jsonData.js"></script>
    <script type="text/javascript" src="${path}/resources/js/tree/recurrenceTree.js"></script>
    <script type="text/javascript" src="${path}/resources/js/tree/noRecurrenceTree.js"></script>
  </head>
  <body>
    递归遍历：<span id="app"></span>
    <script type="text/javascript" src="recurrenceTree.js"></script>
    <hr>
    非递归遍历：<span id="app2"></span>
    <script type="text/javascript" src="noRecurrenceTree.js"></script>
  </body>
</html>