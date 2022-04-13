package com.element.modules.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import com.element.common.exception.CustomException;
import com.element.common.utils.DateUtils;
import com.element.modules.sys.mapper.SysCaptchaMapper;
import com.element.modules.sys.entity.SysCaptchaEntity;
import com.element.modules.sys.service.SysCaptchaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * 验证码
 */
@Service("sysCaptchaService")
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaMapper, SysCaptchaEntity> implements SysCaptchaService {

    @Autowired
    private Producer producer;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)) {
            throw new CustomException("uuid不能为空");
        }
        // 生成验证码文本
        String capText = producer.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String code = capText.substring(capText.lastIndexOf("@") + 1);
        SysCaptchaEntity captchaEntity = new SysCaptchaEntity();
        captchaEntity.setUuid(uuid);
        captchaEntity.setCode(code);
        //5分钟后过期
        captchaEntity.setExpireTime(DateUtils.addDateMinutes(new Date(), 5));
        this.save(captchaEntity);
        return producer.createImage(capStr);
    }

    @Override
    public boolean validate(String uuid, String code) {
        SysCaptchaEntity captchaEntity = this.getOne(new QueryWrapper<SysCaptchaEntity>().eq("uuid", uuid));
        if (captchaEntity == null) {
            return false;
        }
        //删除验证码
        this.removeById(uuid);
        if (captchaEntity.getCode().equalsIgnoreCase(code) && captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

}
