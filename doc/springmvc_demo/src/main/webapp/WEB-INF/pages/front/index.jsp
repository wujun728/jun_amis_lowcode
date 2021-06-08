<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<section id="wrapper">
    <div class="container">
        <section class="panel panel-info">
            <header class="panel-heading">
                <h2 class="panel-title">
                    <i class="icon-bookmark"></i>
                    Books
                </h2>
            </header>
            <div class="panel-body">
                <div class="row">
                    <c:forEach var="book" items="${books}">
                        <c:if test="${book.online eq 1}">
                            <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                                <div data-id="${book.id}" class="thumbnail" style="cursor: pointer">
                                    <img src="/res/images/book/cover/${book.id}.jpg"
                                         alt="${book.title}">

                                    <div class="caption">
                                        <h6>${book.title}</h6>

                                        <p>${book.price}$ &nbsp; &nbsp;<c:if test="${book.count ge 800}"><span
                                                class="label label-danger">hot</span></c:if></p>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </section>
    </div>
</section>
<%@ include file="footer.jsp" %>
