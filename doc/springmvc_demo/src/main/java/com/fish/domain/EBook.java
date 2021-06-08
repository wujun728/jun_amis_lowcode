package com.fish.domain;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/18
 * Time: 22:56
 */
public class EBook extends BaseDomain {
    private long id;
    private String title;
    private String subTitle;
    private String description;
    private String image;
    private String isbn;

    public EBook(JSONObject json) {
        try {
            id = json.getLong("ID");
            title = json.getString("Title");
            this.subTitle = json.getString("SubTitle");
            this.description = json.getString("Description");
            this.image = json.getString("Image");
            this.isbn = json.getString("isbn");
        } catch (JSONException e) {
            // e.printStackTrace();
        }
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
