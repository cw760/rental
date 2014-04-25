<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>User Home</title>
</head>
<body>
  <h1>Listing User</h1>
  <c:forEach items="${user}" var="v_user">
    <a href="edit?id=${v_user.id}">${v_user.id} - ${v_user.account} ${v_user.nickName}</a>
    <br />
  </c:forEach>
  <a href="edit"> Add User</a>
</body>
</html>
