<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<section id="wrapper">
    <div class="container">
        <section class="panel panel-info">
            <header class="panel-heading">
                <h2 class="panel-title">
                    <i class="icon-search"></i>
                    Search IT books
                </h2>
            </header>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-offset-2 col-xs-8">
                        <form
                                class="form-horizontal" role="form">
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <input type="search" class="form-control"
                                           name="keyword"
                                           placeholder="for example: mysql">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-xs-offset-2 col-xs-8">
                        <c:if test="${bookList.total eq 0}">
                            <h2>no books available</h2>
                        </c:if>
                        <c:if test="${bookList.total ne 0}">
                            <h2>Total result: ${empty bookList.total ? 0 :bookList.total}</h2>
                            <hr/>
                            <c:forEach var="book" items="${bookList.books}">
                                <div class="row">
                                    <div class="col-xs-4" style="cursor: pointer">
                                        <div class="thumbnail" data-id="${book.id}" style="height:285px;">
                                            <img src="${book.image}" alt="${book.title}"/>
                                        </div>
                                    </div>
                                    <div class="col-xs-8">
                                        <article>
                                            <h4>${book.title}</h4>
                                            <small>${book.subTitle}</small>
                                            <p>${book.description}</p>
                                            <address>ISBN: ${book.isbn}</address>
                                        </article>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>

                    <c:if test="${bookList.total / 10 gt 0}">
                        <div class="col-xs-offset-2 col-xs-8">
                            <ul class="pagination" data-page="${bookList.total / 10 + 1}">
                                <c:forEach var="i" begin="1" end="${bookList.total / 10 + 1}">
                                    <li><a href="/search/${i}/?keyword=${keyword}">${i}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                </div>
            </div>
        </section>
    </div>
</section>
<%@ include file="footer.jsp" %>
