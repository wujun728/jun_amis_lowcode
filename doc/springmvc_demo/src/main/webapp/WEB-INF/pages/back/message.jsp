<%@ include file="mgr_header.jsp" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<table class="table table-striped table-hover">
    <tr>
        <td>#</td>
        <td>email</td>
        <td>content</td>
        <td>time</td>
        <td>ip</td>
        <td>edit</td>
    </tr>
    <c:forEach var="msg" items="${msgs}">
        <tr>
            <td>${msg.id}</td>
            <td>${msg.email}</td>
            <td>${msg.content}</td>
            <td>${msg.createTime}</td>
            <td>${msg.ip}</td>
            <td><a class="btn btn-danger btn-sm" href="/admin/message/delete/${msg.id}">删除</a></td>
        </tr>
    </c:forEach>
</table>
<%@ include file="mgr_footer.jsp" %>
