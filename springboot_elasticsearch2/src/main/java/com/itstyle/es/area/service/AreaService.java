package com.itstyle.es.area.service;
import com.itstyle.es.area.entity.Area;
import com.itstyle.es.log.entity.Pages;


public interface AreaService {
    /**
	 * 新增区域
	 * @param area
	 * @return
	 */
	void saveArea(Area area);
    
    /**
     * 搜索词搜索，分页返回区域信息
     * @param pageNumber 当前页码
     * @param pageSize 每页大小
     * @param searchContent 搜索内容
     * @return JSON
     */
    Pages<Area> searchAreaPage(Integer pageNumber, Integer pageSize, String searchContent);
    
    void getNearbyAreas(double lat, double lon);
}
