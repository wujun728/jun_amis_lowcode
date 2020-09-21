$(function(){
    $(".success").click(function(){
        var lsnum = $("#lsnum").val();
        var frameno = $("#frameno").val();
        var engineno = $("#engineno").val();
        var fkinput = {
            // createBy:this.setting.user.username,//openid
            lsnum:lsnum,// 车牌号
            frameno:frameno,//车架号
            engineno:engineno,//发动机号
            // shiietype:this.product.cartype//默认02
        };
        $.ajax({
            type: "POST",
            url: baseUrl + "/rest/fk/do360wz",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(fkinput),
            success: function(res){
                if(res.data.ok){
                    var queryResults = res.data.message;//查询结果
                    var violationTimes = res.data.data.result.wzts;//违章次数
                    var totalDeduction = res.data.data.result.wzjfhj;//总扣分
                    var totalFine = res.data.data.result.wzfkhj;//总罚款
                    var list = res.data.data.result.data;
                    if(list.length>0){
                        var a = '';
                        for(let i=0;i<list.length;i++){
                            a += '<div class="item">';
                            a += '<div class="l">';
                            a += '<div class="t">'+list[i].wzdd+'</div>';
                            a += '<div class="b">'+list[i].wzsj+'</div>';
                            a += '</div>';
                            a += '<div class="r">扣'+list[i].wzjf+'分</div>';
                            a += '</div>';
                        }
                        $(".wrap").append(a);
                    }else{
                        $(".wrap").append('<div class="no">目前差无数据</div>');
                    }
                    
                }else{
                    console.log('加载失败...')
                }
            }
        });
    })
});