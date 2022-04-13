package com.element.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.element.common.utils.Constant;
import com.element.common.utils.MapUtils;
import com.element.common.utils.StringUtils;
import com.element.modules.sys.entity.SysMenuEntity;
import com.element.modules.sys.entity.SysUserEntity;
import com.element.modules.sys.entity.vo.MetaVo;
import com.element.modules.sys.entity.vo.RouterVo;
import com.element.modules.sys.mapper.SysMenuMapper;
import com.element.modules.sys.service.SysMenuService;
import com.element.modules.sys.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenuEntity> listMenuByParentId(Long parentId) {
        return baseMapper.listMenuByParentId(parentId);
    }

    @Override
    public List<SysMenuEntity> listMenuNotButton() {
        return baseMapper.listMenuNotButton();
    }

    @Override
    public void delete(Long menuId) {
        // 删除菜单
        this.removeById(menuId);
        // 删除菜单与角色关联
        sysRoleMenuService.removeByMap(new MapUtils().put("menu_id", menuId));
    }

    @Override
    public Set<String> listMenuPermission(SysUserEntity user) {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
            perms.add("*:*:*");
        } else {
            List<String> permList = baseMapper.listMenuPermsByUserId(user.getUserId());
            Set<String> permsSet = new HashSet<>();
            for (String perm : permList) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
            perms.addAll(permsSet);
        }
        return perms;
    }

    @Override
    public List<SysMenuEntity> listMenuTreeByUserId(Long userId) {
        List<SysMenuEntity> menus = null;
        if (userId == Constant.SUPER_ADMIN) {
            menus = baseMapper.listMenuTreeAll();
        } else {
            menus = baseMapper.listMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, 0);
    }

    /**
     * 根据菜单信息构建前端路由
     *
     * @param menus
     * @return
     */
    @Override
    public List<RouterVo> buildMenus(List<SysMenuEntity> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenuEntity menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
            List<SysMenuEntity> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && 0 == menu.getType()) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMeunFrame(menu)) {
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenuEntity menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMeunFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenuEntity menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && menu.getType() == 0 && "1".equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMeunFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenuEntity menu) {
        String component = "Layout";
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMeunFrame(menu)) {
            component = menu.getComponent();
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMeunFrame(SysMenuEntity menu) {
        return menu.getParentId().intValue() == 0 && menu.getType() == 1
                && "1".equals(menu.getIsFrame());
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenuEntity> getChildPerms(List<SysMenuEntity> list, int parentId) {
        List<SysMenuEntity> returnList = new ArrayList<SysMenuEntity>();
        for (Iterator<SysMenuEntity> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenuEntity t = iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param sysMenu
     */
    private void recursionFn(List<SysMenuEntity> list, SysMenuEntity sysMenu) {
        // 得到子节点列表
        List<SysMenuEntity> childList = getChildList(list, sysMenu);
        sysMenu.setChildren(childList);
        for (SysMenuEntity tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysMenuEntity> it = childList.iterator();
                while (it.hasNext()) {
                    SysMenuEntity n = it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuEntity> getChildList(List<SysMenuEntity> list, SysMenuEntity sysMenu) {
        List<SysMenuEntity> tlist = new ArrayList<SysMenuEntity>();
        Iterator<SysMenuEntity> it = list.iterator();
        while (it.hasNext()) {
            SysMenuEntity n = it.next();
            if (n.getParentId().longValue() == sysMenu.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuEntity> list, SysMenuEntity sysMenu) {
        return getChildList(list, sysMenu).size() > 0 ? true : false;
    }

}
