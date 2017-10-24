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
    
    <link href="${path}/resources/js/bootstrap/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="${path}/resources/js/bootstrap/js/jquery.js"></script>
    <script src="${path}/resources/js/bootstrap/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    
    <script type="text/javascript" src="${path}/resources/js/tree/bootTree.js"></script>
    <script type="text/javascript" src="${path}/resources/js/tree/bootTreeData.js"></script>
    
  </head>
  <body>
    <div class="tree well">
      <ul id="ul_tree">
      </ul>
    </div>
  </body>
</html>