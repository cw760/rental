<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello SpringMVC! - 
</h1>
${controllerMessage}<br/>
${controllerMessage1}<br/>
<br/>
<br/>
<a href="person/list">Go to the person list</a>
<a href="user/list">Go to the user list</a>
<a href="user/login">Go to the user login</a>
</body>
</html>
