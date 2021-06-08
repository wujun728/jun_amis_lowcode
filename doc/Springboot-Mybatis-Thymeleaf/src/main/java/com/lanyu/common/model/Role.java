package com.lanyu.common.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 */
public class Role{

    private String rcode;

    private String rname;

    private Integer flag;

    private Set<String> modules = new HashSet<String>();

    public String getRcode() {
        return rcode;
    }

    public void setRcode(String rcode) {
        this.rcode = rcode;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Set<String> getModules() {
        return this.modules;
    }

    public void setModules(Set<String> modules) {
        this.modules = modules;
    }
}