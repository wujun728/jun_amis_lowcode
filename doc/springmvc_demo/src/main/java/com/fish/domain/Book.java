package com.fish.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/16
 * Time: 16:05
 */
public class Book extends BaseDomain {
    private int id;
    private String author;
    private String title;
    private String desc;
    private String pressDesc;
    private String size;
    private int pages;
    private String print;
    private String bookbinding;
    private int count;
    private Date publishTime;
    private Date addTime;
    private double price;
    private int pressId;
    private int authorId;
    private int online;

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getPressId() {
        return pressId;
    }

    public void setPressId(int pressId) {
        this.pressId = pressId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPressDesc() {
        return pressDesc;
    }

    public void setPressDesc(String pressDesc) {
        this.pressDesc = pressDesc;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public String getBookbinding() {
        return bookbinding;
    }

    public void setBookbinding(String bookbinding) {
        this.bookbinding = bookbinding;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
