package com.fish.web.home;

import com.fish.domain.BaseDomain;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/24
 * Time: 9:28
 */
public class CartCommand extends BaseDomain {
    private String price;
    private String email;
    private String username;
    private String phone;
    private String note;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
