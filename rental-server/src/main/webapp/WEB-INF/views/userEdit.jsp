<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Home</title>
</head>
<body>
  <h1>用户信息修改 ${user.account} ${user.id} ${user.nickName}</h1>
  <form:form commandName="user" style="padding:8px">
    ID - ${user.id}<br />
    <p>
      account<br />
      <form:input path="account" />
    </p>
    <p>
      pwd<br />
      <form:input path="pwd" />
    </p>
    <p>
      nick Name<br />
      <form:input path="nickName" />
    </p>
    <input type="submit" value="Save" />
  </form:form>
</body>
</html>
