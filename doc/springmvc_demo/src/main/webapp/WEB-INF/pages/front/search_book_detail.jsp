<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<div class="container">
    <div class="row">
        <div class="col-xs-4 col-xs-offset-2">
            <img class="thumbnail" src="${book.image}" alt="${book.title}"/>
        </div>
        <div class="col-xs-6">
            <p><strong>ID: </strong>${book.id}</p>

            <p><strong>Title: </strong>${book.title}</p>

            <p><strong>Description: </strong>${book.description}</p>

            <p><strong>Author: </strong>${book.author}</p>

            <p><strong>ISBN: </strong>${book.isbn}</p>

            <p><strong>Year: </strong>${book.year}</p>

            <p><strong>Page: </strong>${book.page}</p>

            <p><strong>Publisher: </strong>${book.publisher}</p>

            <p><strong>Download: </strong>
                <span class="label label-info">
                    <a href="${book.download}
                    ">${book.download}</a>
                </span></p>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
