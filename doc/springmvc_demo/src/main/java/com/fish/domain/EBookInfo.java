package com.fish.domain;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/19
 * Time: 16:28
 */
public class EBookInfo extends BaseDomain {
    private int error;
    private double time;
    private long id;
    private String title;
    private String subTitle;
    private String description;
    private String author;
    private String isbn;
    private String year;
    private int page;
    private String publisher;
    private String image;
    private String download;

    public EBookInfo(JSONObject json) {
        try {
            error = json.getInt("Error");
            time = json.getDouble("Time");
            id = json.getLong("ID");
            title = json.getString("Title");
            subTitle = json.getString("SubTitle");
            description = json.getString("Description");
            author = json.getString("Author");
            isbn = json.getString("ISBN");
            year = json.getString("Year");
            page = json.getInt("Page");
            publisher = json.getString("Publisher");
            image = json.getString("Image");
            download = json.getString("Download");
        } catch (JSONException e) {
//            e.printStackTrace();
        }
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }
}
