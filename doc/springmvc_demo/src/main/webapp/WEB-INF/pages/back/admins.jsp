<%@ include file="mgr_header.jsp" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<nav>
    <ul class="nav nav-tabs nav-justified" id="mgr_tab_admins">
        <li class="active"><a href="#mgr_admin" data-toggle="tab">管理员</a></li>
        <li><a href="#mgr_update" data-toggle="tab">修改管理员信息</a></li>
        <li><a href="#mgr_add" data-toggle="tab">添加管理员</a></li>
        <li><a href="#mgr_pass" data-toggle="tab">修改密码</a></li>
    </ul>
</nav>
<div class="tab-content">
    <div id="mgr_admin" class="tab-pane fade active in">
        <div style="margin-top: 40px"></div>
        <div class="row">
            <div class="col-xs-2">用户名：</div>
            <div class="col-xs-10">${info.name}</div>

            <div class="col-xs-2">WebSite:</div>
            <div class="col-xs-10"><a href="${info.website}">${info.website}</a></div>

            <div class="col-xs-2">简介：</div>
            <div class="col-xs-10">${info.desc}</div>

            <div class="col-xs-2">添加时间：</div>
            <div class="col-xs-10">${info.registTime}</div>

            <div class="col-xs-2">Email:</div>
            <div class="col-xs-10">${info.email}</div>
        </div>
    </div>

    <div id="mgr_update" class="tab-pane fade">
        <div style="margin-top: 40px"></div>
        <form
                class="form-horizontal"
                role="form"
                method="post"
                action="/admin/admins/update" id="updateSubmit">
            <div class="form-group">
                <label class="col-sm-2 control-label">用户名：</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" name="username" value="${info.name}" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">WebSite:</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" name="website" value="${info.website}" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">简介：</label>

                <div class="col-sm-10">
                    <textarea name="desc" class="form-control" cols="70" rows="10" required>${info.desc}</textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">Email:</label>

                <div class="col-sm-10">
                    <input type="email" class="form-control" name="email" id="checkExist" data-email="${info.email}" value="${info.email}" required>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default btn-sm" >Update</button>
                </div>
            </div>
        </form>
    </div>

    <div id="mgr_add" class="tab-pane fade">
        <div style="margin-top: 40px"></div>
        <form
                class="form-horizontal"
                role="form"
                method="post"
                action="/admin/admins/add">
            <div class="form-group">
                <label class="col-sm-2 control-label">用户名：</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" name="username" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">WebSite:</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" name="website" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">简介：</label>

                <div class="col-sm-10">
                    <textarea name="desc" class="form-control" cols="70" rows="10" required></textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">Email:</label>

                <div class="col-sm-10">
                    <input type="email" class="form-control checkEmail" name="email"  required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">密码:</label>

                <div class="col-sm-10">
                    <input type="password" class="form-control" name="password"  required>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default btn-sm">Add</button>
                </div>
            </div>
        </form>
    </div>


    <div id="mgr_pass" class="tab-pane fade">
        <div style="margin-top: 40px"></div>
        <form class="form-horizontal"
              action="/admin/admins/passupdate"
              role="form" id="update_pass" method="post">
            <div class="form-group">
                <label for="passold"
                       class="col-sm-2 control-label">原密码</label>

                <div class="col-sm-10">
                    <input type="password" data-email="${info.email}" data-id="${info.id}"
                           class="form-control"
                           id="passold" name="passold" required>
                </div>
            </div>
            <div class="form-group">
                <label for="passnew" class="col-sm-2 control-label">新密码</label>

                <div class="col-sm-10">
                    <input type="password"
                           class="form-control"
                           id="passnew" name="passnew" required min="4">
                </div>
            </div>
            <div class="form-group">
                <label for="passrepeat" class="col-sm-2 control-label">确认密码</label>

                <div class="col-sm-10">
                    <input type="password"
                           class="form-control"
                           id="passrepeat" name="passrepeat" required min="4">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-3">
                    <button type="submit" class="btn btn-default">更新密码</button>
                </div>
                <div class="col-sm-7">
                    <span id="passcheck_res"></span>
                </div>
            </div>
        </form>

    </div>
</div>
<%@ include file="mgr_footer.jsp" %>
