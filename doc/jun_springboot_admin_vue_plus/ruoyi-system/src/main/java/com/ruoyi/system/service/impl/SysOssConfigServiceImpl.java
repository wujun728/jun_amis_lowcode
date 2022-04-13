package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.mybatisplus.core.ServicePlusImpl;
import com.ruoyi.common.core.page.PagePlus;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.JsonUtils;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.common.utils.RedisUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.oss.constant.CloudConstant;
import com.ruoyi.system.domain.SysOssConfig;
import com.ruoyi.system.domain.bo.SysOssConfigBo;
import com.ruoyi.system.domain.vo.SysOssConfigVo;
import com.ruoyi.system.mapper.SysOssConfigMapper;
import com.ruoyi.system.service.ISysOssConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

/**
 * 对象存储配置Service业务层处理
 *
 * @author Lion Li
 * @author 孤舟烟雨
 * @date 2021-08-13
 */
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class SysOssConfigServiceImpl extends ServicePlusImpl<SysOssConfigMapper, SysOssConfig, SysOssConfigVo> implements ISysOssConfigService {

	/**
	 * 项目启动时，初始化参数到缓存，加载配置类
	 */
	@PostConstruct
	public void init() {
		List<SysOssConfig> list = list();
		for (SysOssConfig config : list) {
			String configKey = config.getConfigKey();
			if ("0".equals(config.getStatus())) {
				RedisUtils.setCacheObject(CloudConstant.CACHE_CONFIG_KEY, configKey);
			}
			setConfigCache(true, config);
		}
	}

    @Override
    public SysOssConfigVo queryById(Integer ossConfigId){
        return getVoById(ossConfigId);
    }

    @Override
    public TableDataInfo<SysOssConfigVo> queryPageList(SysOssConfigBo bo) {
        PagePlus<SysOssConfig, SysOssConfigVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo));
        return PageUtils.buildDataInfo(result);
    }


    private LambdaQueryWrapper<SysOssConfig> buildQueryWrapper(SysOssConfigBo bo) {
        LambdaQueryWrapper<SysOssConfig> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getConfigKey()), SysOssConfig::getConfigKey, bo.getConfigKey());
        lqw.like(StringUtils.isNotBlank(bo.getBucketName()), SysOssConfig::getBucketName, bo.getBucketName());
		lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SysOssConfig::getStatus, bo.getStatus());
		return lqw;
    }

    @Override
    public Boolean insertByBo(SysOssConfigBo bo) {
        SysOssConfig config = BeanUtil.toBean(bo, SysOssConfig.class);
        validEntityBeforeSave(config);
		return setConfigCache(save(config), config);
    }

    @Override
    public Boolean updateByBo(SysOssConfigBo bo) {
        SysOssConfig config = BeanUtil.toBean(bo, SysOssConfig.class);
        validEntityBeforeSave(config);
		LambdaUpdateWrapper<SysOssConfig> luw = new LambdaUpdateWrapper<>();
		luw.set(StringUtils.isBlank(config.getPrefix()), SysOssConfig::getPrefix, "");
		luw.set(StringUtils.isBlank(config.getRegion()), SysOssConfig::getRegion, "");
		luw.set(StringUtils.isBlank(config.getExt1()), SysOssConfig::getExt1, "");
		luw.eq(SysOssConfig::getOssConfigId, config.getOssConfigId());
		return setConfigCache(update(config, luw), config);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysOssConfig entity){
		if (StringUtils.isNotEmpty(entity.getConfigKey())
			&& UserConstants.NOT_UNIQUE.equals(checkConfigKeyUnique(entity))) {
			throw new ServiceException("操作配置'" + entity.getConfigKey() + "'失败, 配置key已存在!");
		}
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
    	if(isValid) {
			if (CollUtil.containsAny(ids, CloudConstant.SYSTEM_DATA_IDS)) {
				throw new ServiceException("系统内置, 不可删除!");
			}
		}
        boolean flag = removeByIds(ids);
    	if (flag) {
			for (Long configId : ids) {
				SysOssConfig config = getById(configId);
				RedisUtils.deleteObject(getCacheKey(config.getConfigKey()));
			}
		}
    	return flag;
    }

	/**
	 * 判断configKey是否唯一
	 */
	private String checkConfigKeyUnique(SysOssConfig sysOssConfig) {
		long ossConfigId = StringUtils.isNull(sysOssConfig.getOssConfigId()) ? -1L : sysOssConfig.getOssConfigId();
		SysOssConfig info = getOne(new LambdaQueryWrapper<SysOssConfig>()
			.select(SysOssConfig::getOssConfigId, SysOssConfig::getConfigKey)
			.eq(SysOssConfig::getConfigKey, sysOssConfig.getConfigKey()));
		if (StringUtils.isNotNull(info) && info.getOssConfigId() != ossConfigId) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}

	/**
	 * 启用禁用状态
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateOssConfigStatus(SysOssConfigBo bo) {
		SysOssConfig sysOssConfig = BeanUtil.toBean(bo, SysOssConfig.class);
		int row = baseMapper.update(null, new LambdaUpdateWrapper<SysOssConfig>()
			.set(SysOssConfig::getStatus, "1"));
		row += baseMapper.updateById(sysOssConfig);
		if (row > 0) {
			RedisUtils.setCacheObject(CloudConstant.CACHE_CONFIG_KEY, sysOssConfig.getConfigKey());
		}
		return row;
	}

	/**
	 * 设置cache key
	 *
	 * @param configKey 参数键
	 * @return 缓存键key
	 */
	private String getCacheKey(String configKey) {
		return CloudConstant.SYS_OSS_KEY + configKey;
	}

	/**
	 * 如果操作成功 则更新缓存
	 * @param flag 操作状态
	 * @param config 配置
	 * @return 返回操作状态
	 */
	private boolean setConfigCache(boolean flag, SysOssConfig config) {
		if (flag) {
			RedisUtils.setCacheObject(
				getCacheKey(config.getConfigKey()),
				JsonUtils.toJsonString(config));
			RedisUtils.publish(CloudConstant.CACHE_CONFIG_KEY, config.getConfigKey(), msg -> {
				log.info("发布刷新OSS配置 => " + msg);
			});
		}
		return flag;
	}
}
