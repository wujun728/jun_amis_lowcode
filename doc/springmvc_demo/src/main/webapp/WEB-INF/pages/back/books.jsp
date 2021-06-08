<%@ include file="mgr_header.jsp" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<nav>
    <ul class="nav nav-tabs nav-justified" id="mgr_books">
        <li class="active"><a href="#books_add" data-toggle="tab">添加图书</a></li>
        <li><a href="#books_update" data-toggle="tab">修改图书信息</a></li>
        <li><a href="#books_delete" data-toggle="tab">删除图书</a></li>
        <li><a href="#books_online" data-toggle="tab">上/下线</a></li>
    </ul>
</nav>
<div class="tab-content">
<div id="books_add" class="tab-pane fade active in">
    <div style="margin-top: 40px"></div>
    <form
            class="form-horizontal"
            role="form"
            method="post"
            action="/admin/books/add"
            enctype="multipart/form-data">
        <div class="form-group">
            <label  class="col-sm-2 control-label">Author</label>

            <div class="col-sm-10">
                <input type="text" class="form-control"  name="author" required>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">Title</label>

            <div class="col-sm-10">
                <input type="text" class="form-control"  name="title" required>
            </div>
        </div>


        <div class="form-group">
            <label  class="col-sm-2 control-label">Desc</label>

            <div class="col-sm-10">
                <textarea name="desc" class="form-control" cols="30" rows="10" required></textarea>
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">Press_Desc</label>

            <div class="col-sm-10">
                <textarea name="pressDesc"  class="form-control" required cols="30" rows="10"></textarea>
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">Size</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" required name="size">
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">Pages</label>

            <div class="col-sm-10">
                <input type="number" class="form-control" required  name="pages">
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">Print</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" required  name="print">
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">Bookbinding</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" required  name="bookbinding">
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">count</label>

            <div class="col-sm-10">
                <input type="number" class="form-control" required  name="count">
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">Publish_Time</label>

            <div class="col-sm-10">
                <input type="date" class="form-control" required  name="publishTime">
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">price</label>

            <div class="col-sm-10">
                <input type="number" class="form-control" required  name="price">
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">BookCover</label>

            <div class="col-sm-10">
                <input type="file" class="form-control"  name="cover">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default btn-sm">提交</button>
            </div>
        </div>
    </form>
</div>

<div id="books_update" class="tab-pane fade">
    <div style="margin-top: 40px"></div>
    <!-- Split button -->

    <div class="btn-group" style="margin-left: 70px;margin-bottom: 30px;">
        <button type="button" class="btn btn-default">选择要修改的图书</button>
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
            <span class="caret" id="mySelect"></span>
            <span class="sr-only">Toggle Dropdown</span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <c:forEach var="book" items="${books}">
                <li><a style="cursor: pointer" data-id="${book.id}" data-author="${book.author}"
                       data-title="${book.title}"
                       data-desc="${book.desc}"
                       data-press-desc="${book.pressDesc}"
                       data-size="${book.size}"
                       data-pages="${book.pages}"
                       data-print="${book.print}"
                       data-bookbinding="${book.bookbinding}"
                       data-count="${book.count}"
                       data-publish-time="<fmt:formatDate value="${book.publishTime}" pattern="yyyy/MM/dd"/>"
                       data-price="${book.price}"
                       class="mySelect">${book.title}</a></li>
            </c:forEach>
        </ul>
    </div>

    <form
            class="form-horizontal"
            role="form"
            method="post"
            action="/admin/books/update"
            enctype="multipart/form-data">

        <div class="form-group">
            <label class="col-sm-2 control-label sr-only">id</label>

            <div class="col-sm-10">
                <input type="text" class="form-control sr-only" id="id" required name="id">
            </div>
        </div>

        <div class="form-group">
            <label for="author" class="col-sm-2 control-label">Author</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" required id="author" name="author">
            </div>
        </div>
        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">Title</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" required id="title" name="title">
            </div>
        </div>


        <div class="form-group">
            <label for="desc" class="col-sm-2 control-label">Desc</label>

            <div class="col-sm-10">
                <textarea name="desc" class="form-control" required id="desc" cols="30" rows="10"></textarea>
            </div>
        </div>

        <div class="form-group">
            <label for="pressDesc" class="col-sm-2 control-label">Press_Desc</label>

            <div class="col-sm-10">
                <textarea name="pressDesc" id="pressDesc" required class="form-control" cols="30" rows="10"></textarea>
            </div>
        </div>

        <div class="form-group">
            <label for="size" class="col-sm-2 control-label">Size</label>

            <div class="col-sm-10">
                <input type="text" id="size" class="form-control" required name="size">
            </div>
        </div>

        <div class="form-group">
            <label for="pages" class="col-sm-2 control-label">Pages</label>

            <div class="col-sm-10">
                <input type="number" id="pages" required class="form-control" name="pages">
            </div>
        </div>

        <div class="form-group">
            <label for="print" class="col-sm-2 control-label">Print</label>

            <div class="col-sm-10">
                <input type="text" id="print" required class="form-control" name="print">
            </div>
        </div>

        <div class="form-group">
            <label for="bookbinding" class="col-sm-2 control-label">Bookbinding</label>

            <div class="col-sm-10">
                <input type="text" id="bookbinding" required class="form-control" name="bookbinding">
            </div>
        </div>

        <div class="form-group">
            <label for="count" class="col-sm-2 control-label">count</label>

            <div class="col-sm-10">
                <input type="number" id="count" required class="form-control" name="count">
            </div>
        </div>

        <div class="form-group">
            <label for="publishTime" class="col-sm-2 control-label">Publish_Time</label>

            <div class="col-sm-10">
                <input type="text" id="publishTime" required class="form-control" name="publishTime">
            </div>
        </div>

        <div class="form-group">
            <label for="price" class="col-sm-2 control-label">price</label>

            <div class="col-sm-10">
                <input type="number" id="price" required class="form-control" name="price">
            </div>
        </div>

        <div class="form-group">
            <label for="cover" class="col-sm-2 control-label">BookCover</label>

            <div class="col-sm-10">
                <input type="file" id="cover" class="form-control" name="cover">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default btn-sm">提交</button>
            </div>
        </div>
    </form>
</div>

<div id="books_delete" class="tab-pane fade">
    <table class="table table-striped table-hover">
        <tr>
            <td>#</td>
            <td>author</td>
            <td>title</td>
            <td>pages</td>
            <td>add time</td>
            <td>price</td>
            <td>edit</td>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.id}</td>
                <td>${book.author}</td>
                <td>${book.title}</td>
                <td>${book.pages}</td>
                <td><fmt:formatDate value="${book.addTime}" pattern="yyyy/MM/dd"/></td>
                <td>${book.price}</td>
                <td><a class="btn btn-danger btn-sm" href="/admin/books/delete/${book.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div id="books_online" class="tab-pane fade">
    <table class="table table-striped table-hover">
        <tr>
            <td>#</td>
            <td>author</td>
            <td>title</td>
            <td>pages</td>
            <td>add time</td>
            <td>price</td>
            <td>on/off/line</td>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.id}</td>
                <td>${book.author}</td>
                <td>${book.title}</td>
                <td>${book.pages}</td>
                <td><fmt:formatDate value="${book.addTime}" pattern="yyyy/MM/dd"/></td>
                <td>${book.price}</td>
                <td>
                    <c:if test="${book.online eq 1}">
                        <a class="btn btn-danger btn-sm" href="/admin/books/online/${book.id}" id="online">
                            下线</a>
                    </c:if>
                    <c:if test="${book.online ne 1}">
                        <a class="btn btn-primary btn-sm" href="/admin/books/online/${book.id}" id="online">
                            上线</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</div>

<%@ include file="mgr_footer.jsp" %>
