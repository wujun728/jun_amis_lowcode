package com.jun.plugin.system.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.org.apache.regexp.internal.RE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: MenuTreeNode Description: layui date: 2020/4/14 23:49
 *
 * 
 * 
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeNode implements Serializable {
	/**
	 * 菜单id
	 */
	private Integer id;
	/**
	 * 父id
	 */
	private Integer pid;
	/**
	 * 标题
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String title;
	/**
	 * 跳转
	 */
	private String href;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 是否展开
	 */
	private Boolean spread;
	/**
	 * 页面跳转方式
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String target;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * 区分权限 菜单 类型
	 */
	private String typecode;
	/**
	 * 子菜单
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<MenuTreeNode> child = new ArrayList<>();

	/**
	 * 构造主页左边树
	 *
	 * @param id
	 * @param pid
	 * @param title
	 * @param href
	 * @param icon
	 * @param spread
	 * @param target
	 */
	public MenuTreeNode(Integer id, Integer pid, String title, String href, String icon, Boolean spread, String target,
			String typecode) {
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.href = href;
		this.icon = icon;
		this.spread = spread;
		this.target = target;
		this.typecode = typecode;
	}

	/**
	 * 构造树
	 */
	public static class MenuTreeNodeBuild {
		/**
		 * 逻辑思路: * 传入菜单 和 菜单顶级 pid * 进行循环判断 传入进来的菜单 pid 是否等于 topPid * 等于则为顶级菜单进行添加到集合当中
		 * * 二次循环 遍历子节点 * 判断 父pid 是否等于 主键id * 等于则为当前菜单的子节点
		 *
		 * @param nodes 所有菜单
		 * @param topid 菜单顶部id
		 * @return 处理完毕的菜单树
		 */
		public static List<MenuTreeNode> build(List<MenuTreeNode> nodes, Integer topid) {
			List<MenuTreeNode> menuTreeNodes = new ArrayList<>();
			// 循环菜单列
			for (MenuTreeNode n1 : nodes) {
				// 判断是否为顶部菜单
				if (n1.getPid().equals(topid)) {
					menuTreeNodes.add(n1);
				}
				// 循环判断子菜单
				for (MenuTreeNode n2 : nodes) {
					// 如果 n2 菜单的pid 等于 n1 菜单的id
					if (n2.getPid().equals(n1.getId())) {
						// 为子菜单
						n1.getChild().add(n2);
					}
				}
			}
			return menuTreeNodes;
		}
	}

}