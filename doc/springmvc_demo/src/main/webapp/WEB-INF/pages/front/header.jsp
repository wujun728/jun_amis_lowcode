<%--
  Created by IntelliJ IDEA.
  User: Worshiper
  Date: 2014/6/16 
  Time: 16:52 
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <title>${title}</title>
    <link rel="shortcut icon" href="/res/images/favicon.ico"/>
    <link rel="shortcut icon" href="/res/images/apple-touch-icon-precomposed.png"/>
    <link rel="stylesheet" href="/res/vendor/bower_components/bootstrap/dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="/res/css/fontawesome/css/font-awesome.css"/>
    <link rel="stylesheet" href="/res/css/style.css"/>
</head>
<body>
<header>
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand"
                   href="#"><img src="/res/images/name.png" alt=""/>
                    <c:if test="${!empty sessionScope.user}">
                        &nbsp; <i class="icon-shopping-cart"></i>
                    </c:if>
                </a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right i-nav">
                    <li>

                        <c:choose>
                            <c:when test="${empty sessionScope.user}">
                                <a href="/admin" id="sign">
                                    <i class="icon-signin" data-toggle="modal" data-target="#myModal">login</i>
                                </a>
                            </c:when>
                            <c:when test="${! empty sessionScope.user}">
                                <a href="/admin/logout" id="sign">
                                    <i class="icon-signout">logout</i>
                                </a>
                            </c:when>
                        </c:choose>

                    </li>
                    <li><a href="/books">books</a></li>
                    <li><a href="/search">search</a></li>
                    <li>
                        <c:if test="${! empty sessionScope.user}">
                            <a href="/admin/mgr">mgr</a>
                        </c:if>
                        <c:if test="${ empty sessionScope.user}">
                            <a href="/about">about</a>
                        </c:if>
                    </li>

                    <c:if test="${ empty sessionScope.user}">
                        <li><a href="/cart"><i class="icon-shopping-cart"></i>
                            <span class="badge pull-right alert-danger" id="badge">${sessionScope.ids.size()}</span>
                        </a></li>
                    </c:if>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
    </nav>

</header>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">登陆</h4>
            </div>
            <div class="modal-body">

                <form role="form" action="/admin/login" method="post">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="icon-user"></i></span>
                        <input type="email" class="form-control" name="email" placeholder="Email" required>
                    </div>
                    <div style="height: 20px"></div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="icon-key"></i></span>
                        <input type="password" class="form-control" name="password" placeholder="Password" required>
                    </div>
                    <div style="height: 15px;"></div>
                    <button type="submit" class="btn btn-xs btn-success pull-right">login</button>
                    <button type="button" style="margin-right: 15px" class="btn btn-xs btn-default pull-right"
                            data-dismiss="modal">cancel
                    </button>
                    <div style="margin-bottom: 20px;"></div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div class="container">
    <div class="row" id="error">
        <c:if test="${! empty error}">
            <div class="alert alert-danger alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${error}
            </div>
        </c:if>
    </div>
</div>
<div class="container">
    <div class="row" id="msgError">
        <c:if test="${! empty msgError}">
            <div class="alert alert-danger alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${msgError}
            </div>
        </c:if>
    </div>
</div>


<div class="container">
    <div class="row" id="listError">
        <c:if test="${! empty listError}">
            <div class="alert alert-danger alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${listError}
            </div>
        </c:if>
    </div>
</div>
