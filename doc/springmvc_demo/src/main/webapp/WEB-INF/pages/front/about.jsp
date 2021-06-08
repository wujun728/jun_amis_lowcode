<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<div class="container">
    <div class="row">
        关于: <br/>
        <div class="col-xs-6 col-xs-offset-3">
            <p>
                香蕉鱼书店是一家位于中国的线上独立书店。我们专售世界各地独立出版厂牌和艺术家个人制作的纸质艺术作品。书店的特色在于售卖他处全无的限量版书本，摄影集，杂志，zines 和小物品。 <br/>
                blog: 店长日记
            </p>
        </div>

        <div class="col-xs-6 col-xs-offset-3">
            <p>
                [a perfect book]: 香蕉鱼出版社，独立出版摄影，插画类书籍。由苏菲和 Wei 创办于2010年，我们会持续出版有香蕉鱼风格的限量版摄影书和
                zines，怀有一份出版理想，为我们的摄影师和艺术家朋友制作一本完美的个人作品集。
            </p>
        </div>

        <div class="col-xs-6 col-xs-offset-3">
            <p>
            <address>
                信箱及支付宝：bananafishbooks@gmail.com
                电话：+86 139 0791 7625 （苏菲）
                　　　+86 152 9791 4096 （Wei）
                BANANAFISH BOOKS is an online bookstore in China. We sell indie brands, artists books, zines and all the
                beautiful prints. Everything here is limited and rare, just like a bananafish.
                [a perfect book] is an indie publisher for small-run artist books and zines. We are dedicated to making
                a perfect book for our artist friends. We work with photographers and illustrators of our choice. It is
                founded by Qing and Wei since 2010.
                Follow us on Facebook and Twitter
                Mail: bananafishbooks@gmail.com
                Phone: +86 139 0791 7625
            </address>
            </p>
        </div>
    </div>

    <hr/>
    留言: <br/>

    <form class="form-horizontal" role="form" method="post" action="/msg">
        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">Email</label>

            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" name="email" placeholder="Email">
            </div>
        </div>
        <div class="form-group">
            <label for="content" class="col-sm-2 control-label">Content</label>

            <div class="col-sm-10">
                <textarea name="content"
                          id="content" class="form-control" cols="30" rows="10"></textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default btn-sm">提交</button>
            </div>
        </div>
    </form>
</div>
<%@ include file="footer.jsp" %>
