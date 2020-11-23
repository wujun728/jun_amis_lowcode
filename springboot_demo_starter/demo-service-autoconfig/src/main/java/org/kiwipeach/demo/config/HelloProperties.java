package org.kiwipeach.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述：配置属性bean
 *
 * @author Wujun
 * @since 2.0
 */
@ConfigurationProperties(prefix = "cn.kiwipeach")
public class HelloProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
