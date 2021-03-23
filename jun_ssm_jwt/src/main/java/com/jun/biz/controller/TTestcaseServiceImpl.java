package com.jun.biz.controller;
import org.springframework.stereotype.Service;

import com.jun.common.ReturnT;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 用例信息
*
* Created by wujun on '2021-03-24 01:01:30'.
*/
@Service
public class TTestcaseServiceImpl implements TTestcaseService {

	@Resource
	private TTestcaseDao tTestcaseDao;

	/**
    * 新增
    */
	@Override
	public ReturnT<String> insert(TTestcase tTestcase) {

		// valid
		if (tTestcase == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "必要参数缺失");
        }

		tTestcaseDao.insert(tTestcase);
        return ReturnT.SUCCESS;
	}

	/**
	* 删除
	*/
	@Override
	public ReturnT<String> delete(int id) {
		int ret = tTestcaseDao.delete(id);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* 更新
	*/
	@Override
	public ReturnT<String> update(TTestcase tTestcase) {
		int ret = tTestcaseDao.update(tTestcase);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* Load查询
	*/
	@Override
	public TTestcase load(int id) {
		return tTestcaseDao.load(id);
	}

	/**
	* 分页查询
	*/
	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<TTestcase> pageList = tTestcaseDao.pageList(offset, pagesize);
		int totalCount = tTestcaseDao.pageListCount(offset, pagesize);

		// result
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("pageList", pageList);
		maps.put("totalCount", totalCount);

		return maps;
	}

}
