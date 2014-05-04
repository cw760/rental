<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>login</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css"></style>
<!-- Bootstrap -->
<link rel="stylesheet" href="../resources/bootstrap-3.1.1-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/login.css">

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
          <h2 class="form-signin-heading">用户登录</h2>
          <form:input path="account" class="form-control" placeholder="账号" />
          <form:password path="pwd" class="form-control" placeholder="密码" />
          <div class="row">
            <div class="col-xs-6  col-md-3">
              <label class="checkbox"> <input type="checkbox" value="remember-me"> 记住我
              </label>
            </div>
            <div class="col-xs-3 col-xs-offset-3 col-md-2 col-md-offset-7 ">
              <a href="edit">注册</a>
            </div>
          </div>
          <button class="btn btn-lg btn-primary btn-block" type="submit">登 录</button>
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
