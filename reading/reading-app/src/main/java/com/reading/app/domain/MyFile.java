package com.reading.app.domain;

public class MyFile {

    private Integral id;
    private String name;
    private String url;
    private String orginName;

    public Integral getId() {
        return id;
    }

    public void setId(Integral id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrginName() {
        return orginName;
    }

    public void setOrginName(String orginName) {
        this.orginName = orginName;
    }
}
