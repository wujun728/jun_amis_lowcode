<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<div class="container">
    <div class="row">

        <c:if test="${! empty books}">
            <table class="table table-striped table-hover" id="list">
                <tr>
                    <td>#</td>
                    <td>author</td>
                    <td>title</td>
                    <td>pages</td>
                    <td>add time</td>
                    <td>price</td>
                    <td>remove</td>
                    <%--<td>n</td>--%>
                </tr>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.author}</td>
                        <td>${book.title}</td>
                        <td>${book.pages}</td>
                        <td>${book.addTime}</td>
                        <td>${book.price}</td>
                        <%--<td class="num_p">--%>
                            <%--<i class="icon-minus minus"></i>--%>
                            <%--<span class="num">1</span>--%>
                            <%--<i  class="icon-plus plus"></i>--%>
                        <%--</td>--%>
                        <td><a class="btn btn-primary btn-sm" href="/cart/remove/${book.id}">
                            remove</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <form class="form-horizontal" id="listForm" role="form" method="post" action="/cart/add">
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">联系邮箱</label>

                <div class="col-sm-6">
                    <input type="email"
                           class="form-control" id="email" required name="email">
                </div>
            </div>
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">收件人</label>

                <div class="col-sm-6">
                    <input type="text"
                           class="form-control" id="username" name="username" required>
                </div>
            </div>

            <div class="form-group">
                <label for="phone" class="col-sm-2 control-label">tel</label>

                <div class="col-sm-6">
                    <input type="tel"
                           class="form-control" id="phone" name="phone" required>
                </div>
            </div>

            <div class="form-group">
                <label for="address" class="col-sm-2 control-label">Address</label>

                <div class="col-sm-6">
                    <input type="tel"
                           class="form-control" id="address" name="address" required>
                </div>
            </div>

            <div class="form-group">
                <label for="note" class="col-sm-2 control-label">备注</label>

                <div class="col-sm-6">
                    <textarea name="note" id="note" cols="70" rows="10"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="submit" class="btn btn-default">支付宝</button>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="footer.jsp" %>
