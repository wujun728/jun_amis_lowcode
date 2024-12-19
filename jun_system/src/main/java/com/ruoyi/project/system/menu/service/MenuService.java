package com.ruoyi.project.system.menu.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.TreeUtils;
import com.ruoyi.common.utils.db.BatchSql;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import cn.hutool.core.convert.Convert;
import com.ruoyi.framework.web.domain.Ztree;
import com.ruoyi.project.common.CommonService;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.user.domain.User;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 菜单 业务层处理
 * @author ruoyi
 */
@Service
public class MenuService extends CommonService {

    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    /**
     * 根据用户查询菜单
     * @param user 用户信息
     * @return 菜单列表
     */
    public List<Menu> selectMenusByUser(User user) {
        List<Menu> menus = new LinkedList<Menu>();
        // 管理员显示所有菜单信息
        if (user.isAdmin()) {
        	String sql = "select distinct m.menu_id, m.parent_id, m.menu_name, m.url, m.visible, m.is_refresh, ifnull(m.perms,'') as perms, " +
   				 		 "       m.target, m.menu_type, m.icon, m.order_num, m.create_time, " +
   				 		 "       (select t.menu_name from sys_menu t where m.parent_id = t.menu_id) parent_name " +
   				 		 "  from sys_menu m " +
   				 		 " where m.menu_type in ('M', 'C') and m.visible = 0 " +
   				 		 " order by m.parent_id, m.order_num";
            menus = db.queryForList(sql, null, Menu.class);
        }
        else {
        	String sql = "select distinct m.menu_id, m.parent_id, m.menu_name, m.url, m.visible, m.is_refresh, ifnull(m.perms,'') as perms, " +
   				 		 "       m.target, m.menu_type, m.icon, m.order_num, m.create_time, " +
   				 		 "       (select t.menu_name from sys_menu t where m.parent_id = t.menu_id) parent_name " +
   				 		 "  from sys_menu m " +
   				 		 "  left join sys_role_menu rm on m.menu_id = rm.menu_id " +
   				 		 "  left join sys_user_role ur on rm.role_id = ur.role_id " +
   				 		 "  left join sys_role ro on ur.role_id = ro.role_id " +
   				 		 " where ur.user_id = ? and m.menu_type in ('M', 'C') and m.visible = 0  and ro.status = 0 " +
   				 		 " order by m.parent_id, m.order_num";
            menus = db.queryForList(sql, new Object[]{user.getUserId()}, Menu.class);
        }
        return TreeUtils.getChildPerms(menus, 0);
    }

    /**
     * 查询菜单集合
     * @return 所有菜单信息
     */
    public List<Map<String, Object>> selectMenuList(HttpServletRequest request) {
		String menu_name = RequestUtil.getValue(request, "menu_name");
    	String visible = RequestUtil.getValue(request, "visible");

        User user = ShiroUtils.getSysUser();
        StringBuffer sql = new StringBuffer();
        List<String> paramList = new ArrayList<String>();
        if (user.isAdmin()) {
        	sql.append("select menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, " +
        			   "       is_refresh, ifnull(perms,'') as perms, icon, create_by, create_time, " +
        			   "       (select t.menu_name from sys_menu t where m.parent_id = t.menu_id) parent_name " +
        			   "  from sys_menu m " +
        			   " where 1 = 1 ");
        }
        else {
            sql.append("select distinct m.menu_id, m.parent_id, m.menu_name, m.url, m.visible, m.is_refresh, ifnull(m.perms,'') as perms, " +
            		   "       m.target, m.menu_type, m.icon, m.order_num, m.create_time, " +
            		   "       (select t.menu_name from sys_menu t where m.parent_id = t.menu_id) parent_name " +
            		   "  from sys_menu m " +
            		   "  left join sys_role_menu rm on m.menu_id = rm.menu_id " +
            		   "  left join sys_user_role ur on rm.role_id = ur.role_id " +
            		   "  left join sys_role ro on ur.role_id = ro.role_id " +
            		   " where ur.user_id = ? ");
    		paramList.add(String.valueOf(user.getUserId()));
        }

    	if(StrUtil.isNotBlank(menu_name)) {
    		sql.append(" and m.menu_name like concat('%', ?, '%') ");
    		paramList.add(menu_name);
    	}
    	if(StrUtil.isNotBlank(visible)) {
    		sql.append(" and m.visible = ? ");
    		paramList.add(visible);
    	}
    	sql.append(" order by m.parent_id, m.order_num");

        return db.queryForList(sql.toString(), paramList.toArray());
    }

    /**
     * 查询系统所有菜单（含按钮）
     * @return 菜单列表
     */
    public List<Menu> selectMenuAllByAdmin() {
    	String sql = "select menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, " +
    				 "       is_refresh, ifnull(perms,'') as perms, icon, create_by, create_time, " +
				 	 "       (select t.menu_name from sys_menu t where m.parent_id = t.menu_id) parent_name " +
    				 "  from sys_menu m order by parent_id, order_num ";
    	return db.queryForList(sql, null, Menu.class);
    }

    /**
     * 根据用户查询菜单
     * @param user 用户信息
     * @return 菜单列表
     */
    public List<Menu> selectMenuAllByUserId(User user) {
    	String sql = "select distinct m.menu_id, m.parent_id, m.menu_name, m.url, m.visible, m.is_refresh, ifnull(m.perms,'') as perms, " +
    				 "       m.target, m.menu_type, m.icon, m.order_num, m.create_time, " +
				 	 "       (select t.menu_name from sys_menu t where m.parent_id = t.menu_id) parent_name " +
    				 "  from sys_menu m " +
    				 "  left join sys_role_menu rm on m.menu_id = rm.menu_id " +
    				 "  left join sys_user_role ur on rm.role_id = ur.role_id " +
    				 "  left join sys_role ro on ur.role_id = ro.role_id " +
    				 " where ur.user_id = ? " +
    				 " order by m.parent_id, m.order_num";
    	return db.queryForList(sql, new Object[]{user.getUserId()}, Menu.class);
    }

    /**
     * 查询菜单集合
     * @return 所有菜单信息
     */
    public List<Menu> selectMenuAll() {
        List<Menu> menuList = null;
        User user = ShiroUtils.getSysUser();
        if (user.isAdmin()) {
            menuList = selectMenuAllByAdmin();
        }
        else {
            menuList = selectMenuAllByUserId(user);
        }
        return menuList;
    }

    /**
     * 根据用户ID查询权限
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectPermsByUserId(Long userId) {
    	//根据用户ID查询权限
    	String sql = "select distinct m.perms " +
                     "  from sys_menu m " +
                     "    left join sys_role_menu rm on m.menu_id = rm.menu_id " +
                     "    left join sys_user_role ur on rm.role_id = ur.role_id " +
                     "    left join sys_role r on r.role_id = ur.role_id " +
                     " where m.visible = '0' and r.status = '0' and ur.user_id = ?";
        List<String> perms = db.queryForList(sql, new Object[]{userId}, "perms");

        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StrUtil.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据角色ID查询菜单
     * @param role_id 角色编号
     * @return 菜单列表
     */
    public List<Ztree> roleMenuTreeData(String role_id) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<Menu> menuList = selectMenuAll();
        if (StrUtil.isNotBlank(role_id)) {
        	//根据角色ID查询菜单
        	String sql = "select concat(m.menu_id, ifnull(m.perms,'')) as perms " +
        				 "  from sys_menu m " +
        				 "  left join sys_role_menu rm on m.menu_id = rm.menu_id " +
        				 " where rm.role_id = ? " +
        				 " order by m.parent_id, m.order_num";
        	List<String> roleMenuList = db.queryForList(sql, new Object[]{role_id}, "perms");
            ztrees = initZtree(menuList, roleMenuList, true);
        }
        else {
            ztrees = initZtree(menuList, null, true);
        }
        return ztrees;
    }

    /**
     * 查询所有菜单
     * @return 菜单列表
     */
    public List<Ztree> menuTreeData() {
        List<Menu> menuList = selectMenuAll();
        List<Ztree> ztrees = initZtree(menuList);
        return ztrees;
    }

    /**
     * 查询系统所有权限
     * @return 权限列表
     */
    public LinkedHashMap<String, String> selectPermsAll() {
        LinkedHashMap<String, String> section = new LinkedHashMap<>();
        List<Menu> permissions = selectMenuAll();
        if (CollUtil.isNotEmpty(permissions)) {
            for (Menu menu : permissions) {
                section.put(menu.getUrl(), MessageFormat.format(PREMISSION_STRING, menu.getPerms()));
            }
        }
        return section;
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Menu> menuList) {
        return initZtree(menuList, null, false);
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Menu> menuList, List<String> roleMenuList, boolean permsFlag) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = CollUtil.isNotEmpty(roleMenuList);
        for (Menu menu : menuList) {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getMenuId());
            ztree.setpId(menu.getParentId());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getMenuName());
            if (isCheck) {
                ztree.setChecked(roleMenuList.contains(menu.getMenuId() + menu.getPerms()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(Menu menu, boolean permsFlag) {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }

    /**
     * 删除菜单管理信息
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Long menuId) {
        //删除菜单管理信息
        String sql = "delete from sys_menu where menu_id = ? or (parent_id = ? and menu_type = 'F')";
        return db.execute(sql, new Object[]{menuId, menuId});
    }

    /**
     * 根据菜单ID查询信息
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public Menu selectMenuById(Long menuId) {
    	String sql = "select t.menu_id, t.parent_id, t.menu_name, t.order_num, t.url, t.target, t.menu_type, t.visible, t.is_refresh, t.perms, t.icon, t.remark, " +
    				 "		 (select m.menu_name from sys_menu m where m.menu_id = t.parent_id) parent_name " +
    				 "  from sys_menu t " +
    				 " where t.menu_id = ?";
        return db.queryForObject(sql, new Object[]{menuId}, Menu.class);
    }

    /**
     * 根据菜单ID查询菜单下的权限配置信息
     * @param menuId 菜单ID
     * @return
     */
    public List<Menu> selectPermitsByMenuId(Long menuId) {
    	String sql = "select t.menu_id, t.parent_id, t.menu_name, t.order_num, t.url, t.target, t.menu_type, t.visible, t.is_refresh, t.perms, t.icon, t.remark, " +
    				 "		 (select m.menu_name from sys_menu m where m.menu_id = t.parent_id) parent_name " +
    				 "  from sys_menu t " +
    				 " where t.menu_type = 'F' and t.parent_id = ?";
        return db.queryForList(sql, new Object[]{menuId}, Menu.class);
    }

    /**
     * 查询子菜单数量
     * @param parentId 菜单ID
     * @return 结果
     */
    public int selectCountMenuByParentId(Long parentId) {
    	String sql = "select count(1) from sys_menu where parent_id = ? and menu_type <> 'F'";
        return db.queryForInt(sql, new Object[]{parentId});
    }

    /**
     * 查询菜单使用数量
     * @param menuId 菜单ID
     * @return 结果
     */
    public int selectCountRoleMenuByMenuId(Long menuId) {
    	String sql = "select count(1) from sys_role_menu where menu_id = ?";
    	return db.queryForInt(sql, new Object[]{menuId});
    }

    /**
     * 新增菜单配置
     * @param request
     * @return 结果
     */
    public int insertMenu(HttpServletRequest request) {
		String menu_name = RequestUtil.getValue(request, "menu_name");
    	String parent_id = RequestUtil.getValue(request, "parent_id");
    	String order_num = RequestUtil.getValue(request, "order_num");
    	String url = RequestUtil.getValue(request, "url");
    	String target = RequestUtil.getValue(request, "target");
    	String menu_type = RequestUtil.getValue(request, "menu_type");
    	String visible = RequestUtil.getValue(request, "visible");
    	String is_refresh = RequestUtil.getValue(request, "is_refresh");
    	String perms = RequestUtil.getValue(request, "perms");
    	String icon = RequestUtil.getValue(request, "icon");
    	String remark = RequestUtil.getValue(request, "remark");

    	String[] permit_names = RequestUtil.getValues(request, "permit_name");
    	String[] permit_perms = RequestUtil.getValues(request, "permit_perms");
    	String[] permit_orders = RequestUtil.getValues(request, "permit_order");

    	String operator_id = ShiroUtils.getLoginName();
    	BatchSql batchSql = new BatchSql();

    	String sql = "insert into sys_menu(menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, " +
    				 "create_by, create_time, remark) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate(), ?)";
    	long id = db.insert(sql, new String[]{menu_name, parent_id, order_num, url, target, menu_type, visible,
                is_refresh, perms, icon, operator_id, remark});
    	String menu_id = String.valueOf(id);

    	//保存菜单权限
    	sql = "insert into sys_menu(menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, " +
    		  "create_by, create_time) values(?, ?, ?, '#', 'F', '0', ?, '#', ?, sysdate())";
    	for(int i=0;i<permit_names.length;i++) {
    		if(StrUtil.isNotBlank(permit_names[i])) {
    			batchSql.addBatch(sql, new Object[]{permit_names[i], menu_id, permit_orders[i], permit_perms[i], operator_id});
    		}
    	}

        return db.doInTransaction(batchSql);
    }

    /**
     * 修改菜单配置
     * @param request
     * @return 结果
     */
    public int updateMenu(HttpServletRequest request) {
    	String menu_id = RequestUtil.getValue(request, "menu_id");
		String menu_name = RequestUtil.getValue(request, "menu_name");
    	String parent_id = RequestUtil.getValue(request, "parent_id");
    	String order_num = RequestUtil.getValue(request, "order_num");
    	String url = RequestUtil.getValue(request, "url");
    	String target = RequestUtil.getValue(request, "target");
    	String menu_type = RequestUtil.getValue(request, "menu_type");
    	String visible = RequestUtil.getValue(request, "visible");
        String is_refresh = RequestUtil.getValue(request, "is_refresh");
    	String perms = RequestUtil.getValue(request, "perms");
    	String icon = RequestUtil.getValue(request, "icon");
    	String remark = RequestUtil.getValue(request, "remark");

    	String[] permit_ids = RequestUtil.getValues(request, "permit_id");
    	String[] permit_names = RequestUtil.getValues(request, "permit_name");
    	String[] permit_perms = RequestUtil.getValues(request, "permit_perms");
    	String[] permit_orders = RequestUtil.getValues(request, "permit_order");

    	String operator_id = ShiroUtils.getLoginName();
    	BatchSql batchSql = new BatchSql();

    	//保存菜单信息
		String sql = "update sys_menu a "+
				 	 "   set menu_name = ?, parent_id = ?, order_num = ?, url = ?, target = ?, menu_type = ?, " +
				 	 "		 visible = ?, is_refresh = ?, perms = ?, icon = ?, remark = ?, update_by = ?, update_time = sysdate() " +
				 	 " where menu_id = ?";
		batchSql.addBatch(sql, new Object[]{menu_name, parent_id, order_num, url, target, menu_type,
        		visible, is_refresh, perms, icon, remark, operator_id, menu_id});

		//删除无效权限
        if(permit_ids.length > 0 && !"".equals(permit_ids[0])) {
            List<String> paramList = new ArrayList<String>();
            sql = "delete from sys_menu a where a.menu_type = 'F' and a.menu_id not in ("+SqlUtil.rebuildInSql(permit_ids, paramList)+") and a.parent_id = ?";
            paramList.add(menu_id);
            batchSql.addBatch(sql, paramList);
        }

        //保存菜单权限
        String updateSql = "update sys_menu a set menu_name = ?, perms = ?, order_num = ?, update_by = ?, update_time = sysdate() where menu_id = ?";
    	String insertSql = "insert into sys_menu(menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, " +
    				 "create_by, create_time) values(?, ?, ?, '#', 'F', '0', ?, '#', ?, sysdate())";
        for(int i=0;i<permit_ids.length;i++) {
        	if(StrUtil.isNotBlank(permit_ids[i])) {
        		batchSql.addBatch(updateSql, new Object[]{permit_names[i], permit_perms[i], permit_orders[i], operator_id, permit_ids[i]});
        	}
        	else if(StrUtil.isNotBlank(permit_names[i])) {
        		batchSql.addBatch(insertSql, new Object[]{permit_names[i], menu_id, permit_orders[i], permit_perms[i], operator_id});
        	}
        }

        return db.doInTransaction(batchSql);
    }


    /**
     * 校验菜单名称是否唯一
     * @param request
     * @return
     */
    public int checkMenuNameUnique(HttpServletRequest request) {
    	String menu_name = RequestUtil.getValue(request, "menu_name");
		String parent_id = RequestUtil.getValue(request, "parent_id");
		String menu_id = RequestUtil.getValue(request, "menu_id");

		List<String> paramList = new ArrayList<String>();
		String sql = "select count(1) from sys_menu a where a.menu_name = ? and a.parent_id = ? ";
		paramList.add(menu_name);
		paramList.add(parent_id);

		if(StrUtil.isNotBlank(menu_id)) {
    		sql += " and a.menu_id <> ? ";
    		paramList.add(menu_id);
    	}
		return db.queryForInt(sql, paramList.toArray());
    }

	/**
	 * 根据菜单ID查询已授权角色
	 * @param menuIds 角色编号
	 * @return
	 */
	public List<Role> selectRolesByMenuIds(String menuIds) {
    	List<String> paramList = new ArrayList<String>();
		String sql = "select a.role_id, a.role_name, " +
					 "       (case when exists(select 1 from sys_role_menu b where a.role_id = b.role_id and b.menu_id in ("+SqlUtil.rebuildInSql(menuIds, paramList)+")) then 'true' else 'false' end) flag" +
					 "  from sys_role a" +
					 " where a.status = '0' and a.del_flag = '0' order by a.role_sort";
		return db.queryForList(sql, paramList.toArray(), Role.class);
	}

    /**
     * 插入菜单授权角色信息
     * @param request
     * @return 结果
     */
    public int insertRoleMenu(HttpServletRequest request) {
    	String menuIds = RequestUtil.getValue(request, "menuIds");
    	String roleIds = RequestUtil.getValue(request, "roleIds");

    	String[] menuArray = Convert.toStrArray(menuIds);
    	String[] roleArray = Convert.toStrArray(roleIds);

    	BatchSql batchSql = new BatchSql();
    	String sql = "delete from sys_role_menu a where a.menu_id in ("+SqlUtil.rebuildInSql(menuArray.length)+")";
    	batchSql.addBatch(sql, menuArray);

    	sql = "insert into sys_role_menu(role_id, menu_id) values(?, ?) ";
    	for(String roleId : roleArray) {
    		for(String menuId : menuArray) {
    			if(StrUtil.isNotBlank(roleId) && StrUtil.isNotBlank(menuId)) {
    				batchSql.addBatch(sql, new Object[]{roleId, menuId});
    			}
    		}
    	}

    	return db.doInTransaction(batchSql);
    }
}