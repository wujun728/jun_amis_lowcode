$(function(){
	var openId = localStorage.getItem("openId");
	let driverDataA = {
		username:openId,
		pageNumber:'1',
		pageSize:'10',
		costType:'',
		// driverName:'',
		// driverTel:'',
		// lkDate:'',
		// handleMan:'',
	}
	getFinance(openId,driverDataA);

	$(".tab .item").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		let idx = $(this).index();
		if(idx == '0'){
			var costType = '';
		}
		if(idx == '1'){
			var costType = '收入';
		}
		if(idx == '2'){
			var costType = '支出';
		}
		let driverData = {
			username:openId,
			pageNumber:'1',
			pageSize:'100',
			costType:costType,
			// driverName:'',
			// driverTel:'',
			// lkDate:'',
			// handleMan:'',
		}
		getFinance(openId,driverData);
	});
});

function getFinance(openId,driverData){
	$(".box").html(' ').append('<div class="txt">暂无数据</div>');
	// $.ajax({
	// 	type: "GET",
	// 	url: baseUrl + "/rest/lkDriverFinanceController/list/"+openId,
	// 	contentType:"application/json;charset=UTF-8",
	// 	data:driverData,
	// 	success: function(data) {
	// 		let e = JSON.parse(data);
	// 		if(e.ok){
	// 			if(e.data.length){
	// 				$(".box").html(" ");
	// 				let a = '';
	// 				for(let i=0;i<e.data.length;i++){
	// 					a += '<div class="item">';
	// 					a += '<div class="left">';
	// 					a += '<p>'+e.data[i].driverName+'</p>';
	// 					a += '<p>'+e.data[i].createDate+'</p>';
	// 					a += '</div>';
	// 					a += '<div class="righ">';
	// 					// a += '<div class="icon icon-a"></div>';
	// 					a += '<div class="info">￥'+e.data[i].applicationAmount+'</div>';
	// 					a += '</div></div>';
	// 				}
	// 				$(".box").append(a);
	// 			}
	// 		}else{
	// 			alert("数据加载失败...");
	// 		}
	// 	}
	// });
}