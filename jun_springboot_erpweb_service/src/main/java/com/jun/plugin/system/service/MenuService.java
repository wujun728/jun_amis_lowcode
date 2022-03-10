package com.jun.plugin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.domain.Menu;
import com.jun.plugin.system.vo.MenuVo;

import java.util.List;

/**
 * ClassName: MenuService Description: layui date: 2020/4/14 18:39
 *
 * 
 * 
 * @since JDK 1.8
 */
public interface MenuService extends IService<Menu> {
	/**
	 * 全查询菜单
	 *
	 * @return
	 */
	List<Menu> queryAllMenuForList();

	/**
	 * 根据用户ID查询菜单
	 *
	 * @param id
	 * @return
	 */
	List<Menu> queryMenuForListByUserId(Integer id);

	DataGridView queryAllMenu(MenuVo menuVo);

	Integer queryMenuMaxOrderNum();

	Menu saveMenu(Menu menu);

	Menu updateMenu(Menu menu);

	Integer queryMenuChildrenCountById(Integer id);

	List<String> queryPermissionCodesByUserId(Integer id);

}
