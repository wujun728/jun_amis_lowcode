package com.fish.web.admin;

import com.fish.domain.BaseDomain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/23
 * Time: 16:01
 */
public class BookCommand extends BaseDomain {
    private String author;
    private String title;
    private String pressDesc;
    private String size;
    private int pages;
    private String print;
    private String bookbinding;
    private int count;
    private Date publishTime;
    private double price;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
