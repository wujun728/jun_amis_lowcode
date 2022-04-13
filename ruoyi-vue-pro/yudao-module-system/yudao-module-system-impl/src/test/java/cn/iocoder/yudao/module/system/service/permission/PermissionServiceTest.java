package cn.iocoder.yudao.module.system.service.permission;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.permission.RoleDO;
import cn.iocoder.yudao.module.system.dal.dataobject.permission.RoleMenuDO;
import cn.iocoder.yudao.module.system.dal.dataobject.permission.UserRoleDO;
import cn.iocoder.yudao.module.system.dal.mysql.permission.RoleMenuBatchInsertMapper;
import cn.iocoder.yudao.module.system.dal.mysql.permission.RoleMenuMapper;
import cn.iocoder.yudao.module.system.dal.mysql.permission.UserRoleBatchInsertMapper;
import cn.iocoder.yudao.module.system.dal.mysql.permission.UserRoleMapper;
import cn.iocoder.yudao.module.system.mq.producer.permission.PermissionProducer;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.framework.datapermission.core.dept.service.dto.DeptDataPermissionRespDTO;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.system.enums.permission.DataScopeEnum;
import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertPojoEquals;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomLongId;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomPojo;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Import({PermissionServiceImpl.class,
        RoleMenuBatchInsertMapper.class, UserRoleBatchInsertMapper.class})
public class PermissionServiceTest extends BaseDbUnitTest {

    @Resource
    private PermissionServiceImpl permissionService;

    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private RoleMenuBatchInsertMapper roleMenuBatchInsertMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private UserRoleBatchInsertMapper userRoleBatchInsertMapper;

    @MockBean
    private RoleService roleService;
    @MockBean
    private MenuService menuService;
    @MockBean
    private DeptService deptService;
    @MockBean
    private PermissionProducer permissionProducer;

    @Test
    public void testProcessRoleDeleted() {
        // 准备参数
        Long roleId = randomLongId();
        // mock 数据 UserRole
        UserRoleDO userRoleDO01 = randomPojo(UserRoleDO.class, o -> o.setRoleId(roleId)); // 被删除
        userRoleMapper.insert(userRoleDO01);
        UserRoleDO userRoleDO02 = randomPojo(UserRoleDO.class); // 不被删除
        userRoleMapper.insert(userRoleDO02);
        // mock 数据 RoleMenu
        RoleMenuDO roleMenuDO01 = randomPojo(RoleMenuDO.class, o -> o.setRoleId(roleId)); // 被删除
        roleMenuMapper.insert(roleMenuDO01);
        RoleMenuDO roleMenuDO02 = randomPojo(RoleMenuDO.class); // 不被删除
        roleMenuMapper.insert(roleMenuDO02);

        // 调用
        permissionService.processRoleDeleted(roleId);
        // 断言数据 RoleMenuDO
        List<RoleMenuDO> dbRoleMenus = roleMenuMapper.selectList();
        assertEquals(1, dbRoleMenus.size());
        assertPojoEquals(dbRoleMenus.get(0), roleMenuDO02);
        // 断言数据 UserRoleDO
        List<UserRoleDO> dbUserRoles = userRoleMapper.selectList();
        assertEquals(1, dbUserRoles.size());
        assertPojoEquals(dbUserRoles.get(0), userRoleDO02);
        // 断言调用
        verify(permissionProducer).sendRoleMenuRefreshMessage();
    }

    @Test
    public void testProcessMenuDeleted() {
        // 准备参数
        Long menuId = randomLongId();
        // mock 数据
        RoleMenuDO roleMenuDO01 = randomPojo(RoleMenuDO.class, o -> o.setMenuId(menuId)); // 被删除
        roleMenuMapper.insert(roleMenuDO01);
        RoleMenuDO roleMenuDO02 = randomPojo(RoleMenuDO.class); // 不被删除
        roleMenuMapper.insert(roleMenuDO02);

        // 调用
        permissionService.processMenuDeleted(menuId);
        // 断言数据
        List<RoleMenuDO> dbRoleMenus = roleMenuMapper.selectList();
        assertEquals(1, dbRoleMenus.size());
        assertPojoEquals(dbRoleMenus.get(0), roleMenuDO02);
        // 断言调用
        verify(permissionProducer).sendRoleMenuRefreshMessage();
    }

    @Test
    public void testProcessUserDeleted() {
        // 准备参数
        Long userId = randomLongId();
        // mock 数据
        UserRoleDO userRoleDO01 = randomPojo(UserRoleDO.class, o -> o.setUserId(userId)); // 被删除
        userRoleMapper.insert(userRoleDO01);
        UserRoleDO userRoleDO02 = randomPojo(UserRoleDO.class); // 不被删除
        userRoleMapper.insert(userRoleDO02);

        // 调用
        permissionService.processUserDeleted(userId);
        // 断言数据
        List<UserRoleDO> dbUserRoles = userRoleMapper.selectList();
        assertEquals(1, dbUserRoles.size());
        assertPojoEquals(dbUserRoles.get(0), userRoleDO02);
    }

    @Test // 测试从 context 获取的场景
    public void testGetDeptDataPermission_fromContext() {
        // 准备参数
        LoginUser loginUser = randomPojo(LoginUser.class);
        // mock 方法
        DeptDataPermissionRespDTO respDTO = new DeptDataPermissionRespDTO();
        loginUser.setContext(PermissionServiceImpl.CONTEXT_KEY, respDTO);

        // 调用
        DeptDataPermissionRespDTO result = permissionService.getDeptDataPermission(loginUser);
        // 断言
        assertSame(respDTO, result);
    }

    @Test
    public void testGetDeptDataPermission_All() {
        // 准备参数
        LoginUser loginUser = randomPojo(LoginUser.class);
        // mock 方法
        RoleDO roleDO = randomPojo(RoleDO.class, o -> o.setDataScope(DataScopeEnum.ALL.getScope()));
        when(roleService.getRolesFromCache(same(loginUser.getRoleIds()))).thenReturn(singletonList(roleDO));

        // 调用
        DeptDataPermissionRespDTO result = permissionService.getDeptDataPermission(loginUser);
        // 断言
        assertTrue(result.getAll());
        assertFalse(result.getSelf());
        assertTrue(CollUtil.isEmpty(result.getDeptIds()));
        assertSame(result, loginUser.getContext(PermissionServiceImpl.CONTEXT_KEY, DeptDataPermissionRespDTO.class));
    }

    @Test
    public void testGetDeptDataPermission_DeptCustom() {
        // 准备参数
        LoginUser loginUser = randomPojo(LoginUser.class);
        // mock 方法
        RoleDO roleDO = randomPojo(RoleDO.class, o -> o.setDataScope(DataScopeEnum.DEPT_CUSTOM.getScope()));
        when(roleService.getRolesFromCache(same(loginUser.getRoleIds()))).thenReturn(singletonList(roleDO));

        // 调用
        DeptDataPermissionRespDTO result = permissionService.getDeptDataPermission(loginUser);
        // 断言
        assertFalse(result.getAll());
        assertFalse(result.getSelf());
        assertEquals(roleDO.getDataScopeDeptIds().size() + 1, result.getDeptIds().size());
        assertTrue(CollUtil.containsAll(result.getDeptIds(), roleDO.getDataScopeDeptIds()));
        assertTrue(CollUtil.contains(result.getDeptIds(), loginUser.getDeptId()));
        assertSame(result, loginUser.getContext(PermissionServiceImpl.CONTEXT_KEY, DeptDataPermissionRespDTO.class));
    }

    @Test
    public void testGetDeptDataPermission_DeptOnly() {
        // 准备参数
        LoginUser loginUser = randomPojo(LoginUser.class);
        // mock 方法
        RoleDO roleDO = randomPojo(RoleDO.class, o -> o.setDataScope(DataScopeEnum.DEPT_ONLY.getScope()));
        when(roleService.getRolesFromCache(same(loginUser.getRoleIds()))).thenReturn(singletonList(roleDO));

        // 调用
        DeptDataPermissionRespDTO result = permissionService.getDeptDataPermission(loginUser);
        // 断言
        assertFalse(result.getAll());
        assertFalse(result.getSelf());
        assertEquals(1, result.getDeptIds().size());
        assertTrue(CollUtil.contains(result.getDeptIds(), loginUser.getDeptId()));
        assertSame(result, loginUser.getContext(PermissionServiceImpl.CONTEXT_KEY, DeptDataPermissionRespDTO.class));
    }

    @Test
    public void testGetDeptDataPermission_DeptAndChild() {
        // 准备参数
        LoginUser loginUser = randomPojo(LoginUser.class);
        // mock 方法（角色）
        RoleDO roleDO = randomPojo(RoleDO.class, o -> o.setDataScope(DataScopeEnum.DEPT_AND_CHILD.getScope()));
        when(roleService.getRolesFromCache(same(loginUser.getRoleIds()))).thenReturn(singletonList(roleDO));
        // mock 方法（部门）
        DeptDO deptDO = randomPojo(DeptDO.class);
        when(deptService.getDeptsByParentIdFromCache(eq(loginUser.getDeptId()), eq(true)))
                .thenReturn(singletonList(deptDO));

        // 调用
        DeptDataPermissionRespDTO result = permissionService.getDeptDataPermission(loginUser);
        // 断言
        assertFalse(result.getAll());
        assertFalse(result.getSelf());
        assertEquals(2, result.getDeptIds().size());
        assertTrue(CollUtil.contains(result.getDeptIds(), deptDO.getId()));
        assertTrue(CollUtil.contains(result.getDeptIds(), loginUser.getDeptId()));
        assertSame(result, loginUser.getContext(PermissionServiceImpl.CONTEXT_KEY, DeptDataPermissionRespDTO.class));
    }

    @Test
    public void testGetDeptDataPermission_Self() {
        // 准备参数
        LoginUser loginUser = randomPojo(LoginUser.class);
        // mock 方法
        RoleDO roleDO = randomPojo(RoleDO.class, o -> o.setDataScope(DataScopeEnum.SELF.getScope()));
        when(roleService.getRolesFromCache(same(loginUser.getRoleIds()))).thenReturn(singletonList(roleDO));

        // 调用
        DeptDataPermissionRespDTO result = permissionService.getDeptDataPermission(loginUser);
        // 断言
        assertFalse(result.getAll());
        assertTrue(result.getSelf());
        assertTrue(CollUtil.isEmpty(result.getDeptIds()));
        assertSame(result, loginUser.getContext(PermissionServiceImpl.CONTEXT_KEY, DeptDataPermissionRespDTO.class));
    }

}
