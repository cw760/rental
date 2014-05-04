<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="zh-cn">
<title>User Home</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css"></style>
<!-- Bootstrap -->
<link rel="stylesheet" href="../resources/bootstrap-3.1.1-dist/css/bootstrap.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

</head>
<body>
  <div class="container">
    <table class="table">
      <thead>
        <tr>
          <th>用户id</th>
          <th>账号</th>
          <th>呢称</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${user}" var="v_user">
          <tr>
            <td>${v_user.id}</td>
            <td><a href="edit?id=${v_user.id}">${v_user.account}</a></td>
            <td>${v_user.nickName}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <a href="edit"> Add User</a>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../resources/jquery/jquery-2.1.0.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../resources/bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>

  </div>




</body>
</html>
