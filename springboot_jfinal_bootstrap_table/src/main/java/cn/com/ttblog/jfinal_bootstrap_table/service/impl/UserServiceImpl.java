package cn.com.ttblog.jfinal_bootstrap_table.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import cn.com.ttblog.jfinal_bootstrap_table.Constant.ConfigConstant;
import cn.com.ttblog.jfinal_bootstrap_table.service.IUserService;

public class UserServiceImpl implements IUserService {
//	private SimpleDateFormat format = new SimpleDateFormat(
//			"yyyy-MM-dd HH:mm:ssSSS");

	public Map<String, Object> getUserList(int offset, int limit) {
		int total = Db.queryLong("select count(id) from " + ConfigConstant.USERTABLE).intValue();
		if (total == 0) {
			return null;
		} else {
			List<Record> records = Db.find("select id,name,sex,age,phone,deliveryaddress,FROM_UNIXTIME(adddate,'%Y-%m-%d %H:%m:%s') adddate from "
					+ ConfigConstant.USERTABLE
					+ " order by adddate desc limit ?,?", offset, limit);
			Map<String, Object> datas = new HashMap<String, Object>(2);
			datas.put("rows", records);
			datas.put("total", total);
			return datas;
		}
	}

	@Override
	public int getNewData() {
		return Db.queryLong("select count(id) from " + ConfigConstant.USERTABLE+" where DATE_FORMAT(NOW(),'%Y-%m-%d')=FROM_UNIXTIME(adddate,'%Y-%m-%d')").intValue();
	}
}
