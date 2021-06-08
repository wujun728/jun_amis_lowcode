<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<section class="container">
    <article id="content">
        <div class="row">
            <header class="col-xs-offset-2"><h1>${book.title}
                <c:if test="${empty sessionScope.user}">
                    [<a style="cursor: pointer;text-decoration: none"
                    data-href="/cart/add/${book.id}" id="cart">加入购物车</a>]
                </c:if>
            </h1></header>
            <div class="col-xs-1"></div>
            <div id="detail-pic" class="col-xs-11 col-md-5">
                <img src="/res/images/book/cover/${book.id}.jpg" class="thumbnail" alt="${book.title}"/>
            </div>
            <div id="detail-desc" class="col-xs-11 col-md-5">
                <h3>TITLE:${book.title}</h3><br/>
                <small>AUTHOR:${book.author}</small>
                <hr/>
                <p>
                <h5>DESCRIPTION: </h5>
                ${book.desc}
                </p>
                <hr/>
                <p>
                <h5>
                    PRESS:
                </h5>
                ${book.pressDesc}
                </p>
            </div>
            <div class="col-xs-1"></div>
        </div>
    </article>

    <article id="comment">
        <div class="row">
            <div class="col-xs-1"></div>
            <div class="col-xs-10">
                <div id="disqus_thread"></div>
                <script type="text/javascript">
                    /* * * CONFIGURATION VARIABLES: EDIT BEFORE PASTING INTO YOUR WEBPAGE * * */
                    var disqus_shortname = 'OLA'; // required: replace example with your forum shortname

                    /* * * DON'T EDIT BELOW THIS LINE * * */
                    (function () {
                        var dsq = document.createElement('script');
                        dsq.type = 'text/javascript';
                        dsq.async = true;
                        dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';
                        (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
                    })();
                </script>
                <noscript>Please enable JavaScript to view the <a href="http://disqus.com/?ref_noscript">comments
                    powered by
                    Disqus.</a></noscript>
                <a href="http://disqus.com" class="dsq-brlink">comments powered by <span
                        class="logo-disqus">Disqus</span></a>
            </div>
        </div>
    </article>
</section>

<%@ include file="footer.jsp" %>
