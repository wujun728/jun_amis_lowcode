package com.lanyu.common.model.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="configPath")
public class ConfigPath {
    private  String imgpath;
    private  String csjspath;

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getCsjspath() {
        return csjspath;
    }

    public void setCsjspath(String csjspath) {
        this.csjspath = csjspath;
    }
}
