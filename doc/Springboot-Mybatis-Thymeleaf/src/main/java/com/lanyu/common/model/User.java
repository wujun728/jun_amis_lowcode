package com.lanyu.common.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private Integer user_id;

    private String user_name;

    private String password;

    private String phone;

    private String email;

    private Date birthday;

    private Integer sex;

    private String lgpic;

    private String ltpic;

    private String qqpid;

    private String wxpid;

    private Integer statu;

    private Set<Role> roles = new HashSet<Role>();

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLgpic() {
        return lgpic;
    }

    public void setLgpic(String lgpic) {
        this.lgpic = lgpic == null ? null : lgpic.trim();
    }

    public String getLtpic() {
        return ltpic;
    }

    public void setLtpic(String ltpic) {
        this.ltpic = ltpic == null ? null : ltpic.trim();
    }

    public String getQqpid() {
        return qqpid;
    }

    public void setQqpid(String qqpid) {
        this.qqpid = qqpid == null ? null : qqpid.trim();
    }

    public String getWxpid() {
        return wxpid;
    }

    public void setWxpid(String wxpid) {
        this.wxpid = wxpid == null ? null : wxpid.trim();
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }

    public Set<Role> getRoleList() {
        return roles;
    }

}