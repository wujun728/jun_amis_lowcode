<%@ include file="mgr_header.jsp" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<a href="/admin/orders/output"
   class="btn btn-primary btn-sm"
   style="cursor: pointer">订单导出</a>
<table class="table table-striped table-hover">
    <tr>
        <td>#</td>
        <td>地址</td>
        <td>姓名</td>
        <td>电话</td>
        <td>备注</td>
        <td>订单号</td>
        <td>交易额</td>
        <td>交易时间</td>
        <td>操作</td>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.address}</td>
            <td>${order.name}</td>
            <td>${order.phone}</td>
            <td>${order.note}</td>
            <td>${order.orderId}</td>
            <td>${order.price}</td>
            <td>${order.time}</td>
            <td>
                <c:if test="${order.done eq 0}">
                    <a class="btn btn-danger btn-sm" href="/admin/cart/${order.id}" id="online">
                        未处理</a>
                </c:if>
                <c:if test="${order.done ne 0}">
                    <a class="btn btn-primary btn-sm" href="/admin/cart/${order.id}" disabled id="online">
                        已处理</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="mgr_footer.jsp" %>
