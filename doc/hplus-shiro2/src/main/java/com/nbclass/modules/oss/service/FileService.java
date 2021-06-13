package com.nbclass.modules.oss.service;

import java.util.List;

import com.nbclass.modules.oss.model.SysFile;

/**
 * FileService
 */
public interface FileService {

	List<SysFile> listSysFile(SysFile vo);

	void save(SysFile file);

	void deleteBatch(Long[] ids);

	List<SysFile> listSysFileByIds(Long[] ids);

}
