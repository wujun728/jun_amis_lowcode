package com.ruoyi.project.system.menu.domain;

import com.ruoyi.framework.aspectj.lang.annotation.MapRow;
import com.ruoyi.framework.aspectj.lang.enums.RowType;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 * @author ruoyi
 */
public class Menu extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 菜单ID */
	@MapRow(column = "menu_id", type = RowType.LONG)
	private Long menuId;

	/** 菜单名称 */
	@MapRow(column = "menu_name", type = RowType.STRING)
	private String menuName;

	/** 父菜单名称 */
	@MapRow(column = "parent_name", type = RowType.STRING)
	private String parentName;

	/** 父菜单ID */
	@MapRow(column = "parent_id", type = RowType.LONG)
	private Long parentId;

	/** 显示顺序 */
	@MapRow(column = "order_num", type = RowType.STRING)
	private String orderNum;

	/** 菜单URL */
	@MapRow(column = "url", type = RowType.STRING)
	private String url;

    /** 打开方式（menuItem页签 menuBlank新窗口） */
	@MapRow(column = "target", type = RowType.STRING)
	private String target;

    /** 类型（M目录 C菜单 F按钮） */
	@MapRow(column = "menu_type", type = RowType.STRING)
	private String menuType;

    /** 菜单状态（0显示 1隐藏） */
	@MapRow(column = "visible", type = RowType.STRING)
	private String visible;

    /** 是否刷新（0刷新 1不刷新） */
    @MapRow(column = "is_refresh", type = RowType.STRING)
    private String isRefresh;

	/** 权限字符串 */
	@MapRow(column = "perms", type = RowType.STRING)
	private String perms;

	/** 菜单图标 */
	@MapRow(column = "icon", type = RowType.STRING)
	private String icon;

	/** 子菜单 */
	private List<Menu> children = new ArrayList<Menu>();

	public Menu() {

	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

    public String getIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(String isRefresh) {
        this.isRefresh = isRefresh;
    }

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("menuId", getMenuId())
				.append("menuName", getMenuName()).append("parentId", getParentId()).append("orderNum", getOrderNum())
				.append("url", getUrl()).append("target", getTarget()).append("menuType", getMenuType())
				.append("visible", getVisible()).append("perms", getPerms()).append("icon", getIcon())
				.append("createBy", getCreateBy()).append("createTime", getCreateTime())
				.append("updateBy", getUpdateBy()).append("updateTime", getUpdateTime()).append("remark", getRemark())
				.toString();
	}
}