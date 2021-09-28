package com.free.fs.common.config;

import com.free.fs.common.properties.FsServerProperties;
import com.free.fs.common.template.OssTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 阿里云配置
 *
 * @author dinghao
 * @date 2020/5/14
 */
@Configuration
@ConditionalOnProperty(prefix = "fs.files-server", name = "type", havingValue = "oss")
@Import(OssTemplate.class)
@RequiredArgsConstructor
public class OssAutoConfigure {

    private final FsServerProperties fileProperties;

}
