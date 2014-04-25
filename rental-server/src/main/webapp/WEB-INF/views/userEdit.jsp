<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>Home</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css"></style>
<!-- Bootstrap -->
<link rel="stylesheet" href="../resources/bootstrap-3.1.1-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/userEdit.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

</head>
<body>
  <div class="container">
    <div class="row">
      <div class="col-xs-10 col-xs-offset-1 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
        <form:form commandName="user" class="form-signin " role="form">
          <h2 class="form-signin-heading">${h1}</h2>
          <label>账号：</label>
          <form:input path="account" class="form-control" placeholder="账号" />
          <label>原密码：</label>
          <form:password path="pwd" class="form-control" placeholder="请输入密码" />
          <label>新密码：</label>
          <form:password path="newPwd" class="form-control" placeholder="请输入密码" />
          <form:password path="" class="form-control" placeholder="再次输入密码" />
          <label>呢称：</label>
          <form:input path="nickName" class="form-control" placeholder="呢称" />
          <br>
          <button class="btn btn-lg btn-primary btn-block" type="submit">${button}</button>
        </form:form>
      </div>
    </div>
  </div>

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="../resources/jquery/jquery-2.1.0.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="../resources/bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>

</body>
</html>
