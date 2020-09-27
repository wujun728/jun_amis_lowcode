package com.itstyle.es.area.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itstyle.es.area.entity.Area;
import com.itstyle.es.area.service.AreaService;
import com.itstyle.es.log.entity.Pages;
@Controller
@RequestMapping(value = "area")
public class AreaController {
   
   @Autowired
   private  ElasticsearchTemplate elasticSearchTemplate;
   @Autowired
   private AreaService areaService;
   
   @GetMapping(value="index")
   public String  index() {
		 return "area/index";
   }
   @PostMapping(value="list")
   public @ResponseBody Pages<Area>  list(Integer pageNumber,Integer pageSize,String searchContent) {
	  return areaService.searchAreaPage(pageNumber, pageSize, searchContent);
   }
}
