package cn.com.ttblog.jfinal_bootstrap_table.service;

import java.util.Map;

public interface IUserService {
	public Map<String, Object> getUserList(int offset,int limit);
	public int getNewData();
}
