<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>login</title>
</head>
<body>
  <h1>User Login</h1>
  <br />
  <br />
  <form:form commandName="user">
    <p>
      <label>账号:</label>  
      <form:input path="account" />
    </p>
    <p>
      <label>密码:</label> 
      <form:password path="pwd" />
    </p>
    <input type="submit" value="登录" />
    <input type="button" value="注册" />
  </form:form>
</body>
</html>
