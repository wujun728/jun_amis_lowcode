$(function(){
    
    $(".btn").click(function(){
        let infoBy1 = $("#infoBy1").val();
        let year = $("#year").val();
        let month = $("#month").val();
        let infoBy2 = ($("#year").val()+'-'+$("#month").val()+'-'+$("#day").val());
        let infoBy3 = $("#infoBy3").val();
        let infoBy4 = $("#infoBy4").val();
        console.log(infoBy2);
        if(infoBy1 == '' || infoBy1 == null){
            messageBox("请输入您的置换车型");
            return false;
        }
        if(year == '' || year == null){
            messageBox("请输入上牌时间年份");
            return false;
        }
        if(infoBy2 == '' || infoBy2 == null){
            messageBox("请输入上牌时间");
            return false;
        }
        if(infoBy3 == '' || infoBy3 == null){
            messageBox("请输入行驶里程");
            return false;
        }
        if(infoBy4 == '' || infoBy4 == null){
            messageBox("请填写您的意向置换车型");
            return false;
        }
        $(".tel").css('display','block');
    });

    // 
    $(".cancel").click(function(){
        $(".tel").css('display','none');
    });
    // 
    $(".success").click(function(){
        let infoBy1 = $("#infoBy1").val();
        let infoBy2 = ($("#year").val()+'-'+$("#month").val()+'-'+$("#day").val());
        let infoBy3 = $("#infoBy3").val();
        let infoBy4 = $("#infoBy4").val();
        let name = $("#name").val();
        let sex = $('input:radio[name="sex"]:checked').val();
        let tel = $("#tel").val();
        // let type = $('input:radio[name="yuyue"]:checked').val();
        if(name == '' || name == null){
            messageBox("请输入姓名");
            return false;
        }
        if(sex == '' || sex == null){
            messageBox("请选择性别");
            return false;
        }
        if(tel == '' || tel == null){
            messageBox("请输入手机号");
            return false;
        }
        // if(type == '' || type == null){
        //     messageBox("请选择自用或运营");
        //     return false;
        // }
        var data = {
            zh01: localStorage.getItem("openId"),  // openId
            zh02: infoBy1, // 置换车型
            zh03: infoBy2, // 上牌时间
            zh04: infoBy3, // 行驶公里
            zh05: infoBy4, // 意向置换车型
            zh06: name, // 置换人
            zh07: tel, // 置换人电话
            zh08: sex, // 性别
            zh09: '', // 类型
            zh10: ''
        }
        $.ajax({
            type: "POST",
            url: baseUrl + "/rest/lkQczhController",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(data),
            success: function(data){
              console.log(JSON.parse(data));
              let e = JSON.parse(data);
              if(e.ok){
                messageBox("提交成功！")
                setTimeout(function(){
                    $(".tel").css('display','none');
                    window.history.go(-1);
                },1000)
              }else{
                console.log('加载失败...')
              }
            }
        });
        
    });
    
});