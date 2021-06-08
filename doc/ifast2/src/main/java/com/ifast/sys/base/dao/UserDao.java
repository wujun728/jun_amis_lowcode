package com.ifast.sys.base.dao;

import com.ifast.common.base.BaseDao;
import com.ifast.sys.base.domain.UserDO;

/**
 * <pre>
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
public interface UserDao extends BaseDao<UserDO> {
	
	Long[] listAllDept();

}
