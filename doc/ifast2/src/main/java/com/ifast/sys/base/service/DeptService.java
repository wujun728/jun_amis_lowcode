package com.ifast.sys.base.service;

import com.ifast.common.base.CoreService;
import com.ifast.common.base.domain.Tree;
import com.ifast.sys.base.domain.DeptDO;

/**
 * <pre>
 * 部门管理
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
public interface DeptService extends CoreService<DeptDO> {
    
	Tree<DeptDO> getTree();
	
	boolean checkDeptHasUser(Long deptId);
}
