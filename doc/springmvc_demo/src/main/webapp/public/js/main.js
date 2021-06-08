(function ($) {
    var thumbnail = $('.thumbnail', '#wrapper');

    $('.navbar-brand').attr({'href': window.location.origin});

    thumbnail.hover(function () {
            $(this).addClass('thumbnail-hover');
        }, function () {
            $(this).removeClass('thumbnail-hover');
        }
    );

    thumbnail.click(function () {
        console.log($(this).data('id'));
        if (window.location.href.indexOf("/search") != -1) {
            window.location.href = '/search/' + 'book/' + $(this).data('id');
        } else {
            window.location.href = '/books/' + $(this).data('id');
        }
    });

    $('#sign').click(function (event) {
        if ($('.icon-signin', $(this)).length) {
            event.preventDefault();
        }
    });

    if ($('button', $('#error')).length) {
        setTimeout(function () {
            window.location.href = '/';
        }, 1000);
    }

    if ($('button', $('#mgrError')).length) {
        setTimeout(function () {
            window.location.href = '/admin/message';
        }, 1000);
    }
    if ($('button', $('#msgError')).length) {
        setTimeout(function () {
            window.location.href = '/about';
        }, 1000);
    }

    if ($('button', $('#bookError')).length) {
        setTimeout(function () {
            window.location.href = '/admin/books';
        }, 1000);
    }

    if ($('button', $('#cartError')).length) {
        setTimeout(function () {
            window.location.href = '/admin/orders';
        }, 1000);
    }

    if ($('button', $('#listError')).length) {
        setTimeout(function () {
            window.location.href = '/cart';
        }, 1000);
    }

    if ($('button', $('#adminError')).length) {
        setTimeout(function () {
            window.location.href = '/admin/admins';
        }, 1000);
    }

    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        e.target;
        e.relatedTarget;
    });

    $('#passold').blur(function () {
        var obj = $(this);
        obj.focus(function () {
            obj.removeClass('alert-danger');
        });
        $.ajax({
            type: "POST",
            url: "/admin/passcheck",
            data: {
                id: $(this).data('id'),
                email: $(this).data('email'),
                pass: $(this).val()
            }
        }).done(function (msg) {
            // success => "Ok",  fail => "Fail"
            if (msg === "Ok") {
                obj.removeClass('alert-danger').attr({placeHolder: ''});
            } else {
                obj.addClass('alert-danger').val('').attr({placeHolder: '原密码错误'});
            }
        });
    });

    $('.checkEmail').blur(function () {
        var obj = $(this);
        obj.focus(function () {
            obj.removeClass('alert-danger');
        });
        $.ajax({
            type: "POST",
            url: "/admin/admins/checkEmail",
            data: {
                email: $(this).val()
            }
        }).done(function (msg) {
            // success => "Ok",  fail => "Fail"
            if (msg === "Ok") {
                obj.removeClass('alert-danger').attr({placeHolder: ''});
            } else {
                obj.addClass('alert-danger').val('').attr({placeHolder: '邮箱已被注册'});
            }
        });
    });

    $('#updateSubmit').submit(function (event) {
        var obj = $('#checkExist');
        var form = $(this);
        obj.focus(function () {
            obj.removeClass('alert-danger');
        });
        event.preventDefault();
        var flag = false;
        console.log(obj.data('email'), obj.val());
        if (obj.data('email') != obj.val()) {
            $.ajax({
                type: "POST",
                url: "/admin/admins/checkEmail",
                data: {
                    email: obj.val()
                }
            }).done(function (msg) {
                // success => "Ok",  fail => "Fail"
                console.log(msg);
                if (msg === "Ok") {
                    obj.removeClass('alert-danger').attr({placeHolder: ''});
                    form.unbind('submit').submit()
                } else {
                    flag = true;
                    obj.addClass('alert-danger').val('').attr({placeHolder: '邮箱已被注册'});
                }
            });
        }else{
            form.unbind('submit').submit()
        }
    });

    $('#update_pass').submit(function (event) {
        event.preventDefault();
        var passnew = $('#passnew');
        var passrepeat = $('#passrepeat');
        var passold = $('#passold');

        passnew.focus(function () {
            $(this).removeClass('alert-danger');
        });

        passrepeat.focus(function () {
            $(this).removeClass('alert-danger');
        });

        if (passnew.val() != passrepeat.val()) {
            passnew.addClass('alert-danger').val('').attr({placeHolder: '密码确认失败'});
            passrepeat.addClass('alert-danger').val('').attr({placeHolder: '密码确认失败'});
            return;
        }

        $.ajax({
            type: "POST",
            url: "/admin/admins/passupdate",
            data: {
                passnew: passnew.val()
            }
        }).done(function (msg) {
            var rs = $('#passcheck_res');
            if (msg === "Ok") {
                alert('密码更新成功');
            } else {
                alert('密码更新失败');
            }
            passnew.val('');
            passrepeat.val('');
            passold.val('');
        });
    });

    $('#cart').click(function (event) {
        event.preventDefault();
        var obj = $(this);
        $.ajax({
            type: "POST",
            url: $(this).data('href')
        }).done(function (msg) {
            console.log(msg);
//            alert("添加成功");
            var len = msg.split(',').length;
            $('#badge').text(len);
        });
    });

    var total = 0;
    $('tr:gt(0) td:nth-child(6)', '#list').each(function (index) {
//        total += $(this).val();
        total += Number($(this).text());
    });

    $('#listForm').prepend('<div class="form-group">' +
        '<label for="price" class="col-sm-2 control-label">Total</label><div class="col-sm-6"><input type="text"' +
        'class="form-control" id="price" name="price" readonly required value="' + total + '$">   </div> </div>');
    console.log(total);

//    var num = $('.num');
//    $('.minus').click(function () {
//        var obj = $(this);
//        var org = num.text();
//        if (org == 1) {
//            num.text(org);
//        } else {
//            num.text(Number(org) - 1);
//        }
//
//        $('tr:gt(0) td:nth-child(6)', '#list').each(function (index) {
////        total += $(this).val();
//            total += Number($(this).text()) * $(this).siblings( ".num_p").find(".num",obj).text();
//        });
//    });
//    $('.plus').click(function () {
//        var obj = $(this);
//        var org = num.text();
//        if (org == 10) {
//            num.text(org);
//        } else {
//            num.text(Number(org) + 1);
//        }
//
//        $('tr:gt(0) td:nth-child(6)', '#list').each(function (index) {
////        total += $(this).val();
//            total += Number($(this).text()) * $(this).siblings( ".num_p").find(".num",obj).text();
//        });
//    });

    $('.mySelect').click(function () {
        var obj = $(this);
        console.log(obj.data('id'));
        console.log(obj.data('author'));
        console.log(obj.data('title'));
        console.log(obj.data('desc'));
        console.log(obj.data('pressDesc'));
        console.log(obj.data('size'));
        console.log(obj.data('pages'));
        console.log(obj.data('print'));
        console.log(obj.data('bookbinding'));
        console.log(obj.data('count'));
        console.log(obj.data('publishTime'));
        console.log(obj.data('price'));

        $('#author').val(obj.data('author'));
        $('#title').val(obj.data('title'));
        $('#desc').val(obj.data('desc') || '无信息');
        $('#pressDesc').val(obj.data('pressDesc') || '无信息');
        $('#size').val(obj.data('size') || '无信息');
        $('#pages').val(obj.data('pages'));
        $('#print').val(obj.data('print') || '无信息');
        $('#bookbinding').val(obj.data('bookbinding') || '无信息');
        $('#count').val(obj.data('count'));
        $('#publishTime').val(obj.data('publishTime'));
        $('#price').val(obj.data('price'));
        $('#id').val(obj.data('id'));
    });
})(jQuery);
