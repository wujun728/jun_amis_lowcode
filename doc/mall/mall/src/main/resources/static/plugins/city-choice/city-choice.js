function getRegionPlug() {
	$(".city-div").html("");
	$(".city-div").append(
		$("<div/>", {
			"class": "place-div-model"
		}).append(
			$("<div/>", {
				"class": "placegroup"
			}).append(
				deliver_getPlace(deliver_global_val.commonAreaData)
			)
		)
	)
}

function deliver_getPlace(datas) {
	return datas.map(function(item) {
		return $("<div/>", {
			"class": "place-model clearfloat"
		}).append(
			$("<div/>", {
				"class": "bigplace"
			}).append(
				$("<div/>", {}).append(
					$("<label/>", {
						"text": item.name,
						"class": "m-checkbox"
					}).prepend(
						$("<input/>", {
							"id": "pdDeliver_" + item.no,
							"value": item.no,
							"type": "checkbox",
							"class": "bigarea",
							"data-name": item.name,
							"click": function() {
								var bool = $(this).prop("checked");
								var single = $(this).parents(".bigplace").next().find("input");
								single.each(function(index, ele){
									if(!$(ele).attr("disabled")){
										$(ele).prop("checked", bool);
									}
								});
							}
						})
					).append($("<span/>", {})).append($("<i/>", {
						"class": "fa fa-caret-down arrow_list"
					}))
				)
			)
		).append(
			function() {
				if (item.children) {
					return deliver_getSmallPlace(item.children)
				}
			}()
		)
	})
}

function deliver_getSmallPlace(datas) {
	return $("<div/>", {
		"class": "smallplace-model clearfloat"
	}).append($("<span>/", {
		"class": "arrow_top"
	})).append(
		datas.map(function(item) {
			return $("<div/>", {
				"class": "place-tooltips"
			}).append(
				$("<label/>", {
					"html": "<span>"+ item.name +"</span>",
					"class": "m-checkbox"
				}).prepend(
					$("<input/>", {
						"id": "pdDeliver_" + item.no,
						"value": item.no,
						"type": "checkbox",
						"class": "bigcity",
						"data-name": item.name,
						"click": function() {
							if ($(this).prop("checked")) {
								$(this).parents(".smallplace-model").prev().find(".bigarea").prop("checked", true);
							} else {
								deliver_clearArea($(this).parents(".smallplace-model"), $(this).parents(".smallplace-model").prev().find(".bigarea"));
							};
						}
					})
				).append(
					$("<span/>", {})
				)
			)
		})
	)
}

//省市区全部取消选择时华北东北等取消选择
function deliver_clearArea(place, area) { //参数area为包含省级input的父级div
	var checked = place.find("input:checked").length;
	if (checked == 0) {
		area.prop("checked", false);
	}
}

//获取已选中的数据
function deliver_getChecked() {
	var Checked = {};
	Checked.name = [];
	Checked.no = [];

	var n = $(".place-div-model").find(".place-model");
	n.each(function(index, a) {
		var len = $(this).find(".smallplace-model").length;
		if (len == 0 && $(this).find(".bigarea:checked").length) {
			Checked.name.push($(this).find(".bigarea").attr("data-name"));
			Checked.no.push($(this).find(".bigarea").attr("value"));
		} else {
			var m = $(this).find(".smallplace-model");
			m.each(function(index, a) {
				var len1 = $(this).find(".bigcity").length;
				var len2 = $(this).find(".bigcity:checked").length;
				if (len1 == len2) {
					Checked.name.push($(this).closest(".place-model").find(".bigarea").attr("data-name"));
				}

				var p = $(this).find(".bigcity");
				p.each(function(index, a) {
					if ($(this).prop("checked")) {
						if(len1 != len2){
							Checked.name.push($(this).attr("data-name"));
						}
						Checked.no.push($(this).attr("value"));
					}
				})
			})
		}
	})
	return Checked;
}