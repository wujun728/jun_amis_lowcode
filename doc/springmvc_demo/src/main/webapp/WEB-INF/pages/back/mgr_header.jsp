<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<div class="container">
    <header>
        <h1>${mgrTitle}</h1>
    </header>
    <nav id="mgr_nav">
        <span><a href="/admin/orders">订单管理</a></span>
        <span><a href="/admin/books">图书管理</a></span>
        <span><a href="/admin/message">留言</a></span>
        <span><a href="/admin/admins">管理员</a></span>
        <span><a href="/admin/logout">退出</a></span>
    </nav>
</div>

<section>
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-4 col-sm-offset-2 col-sm-10">
                <div class="row" id="mgrError">
                    <c:if test="${! empty mgrSuccess}">
                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${mgrSuccess}
                        </div>
                    </c:if>
                    <c:if test="${!empty mgrError}">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${mgrError}
                        </div>
                    </c:if>
                </div>

                <div class="row" id="bookError">
                    <c:if test="${! empty bookSuccess}">
                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${bookSuccess}
                        </div>
                    </c:if>
                    <c:if test="${!empty bookError}">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${bookError}
                        </div>
                    </c:if>
                </div>
                <div class="row" id="cartError">
                    <c:if test="${! empty cartSuccess}">
                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${cartSuccess}
                        </div>
                    </c:if>
                    <c:if test="${!empty cartError}">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${cartError}
                        </div>
                    </c:if>
                </div>

                <div class="row" id="adminError">
                    <c:if test="${! empty adminSuccess}">
                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${adminSuccess}
                        </div>
                    </c:if>
                    <c:if test="${!empty adminError}">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${adminError}
                        </div>
                    </c:if>
                </div>
