package com.fish.domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/18
 * Time: 22:54
 */
public class EBookRes extends BaseDomain {
    private int error;
    private double time;
    private int total;
    private int page;
    private ArrayList<EBook> books;

    public EBookRes(JSONObject jsonObject) {
        try {
            this.error = jsonObject.getInt("Error");
            this.time = jsonObject.getDouble("Time");
            this.total = jsonObject.getInt("Total");
            this.page = jsonObject.getInt("Page");
            JSONArray jsonArray = jsonObject.getJSONArray("Books");
            this.books = new ArrayList<>();
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                EBook eBook = new EBook(jsonArray.getJSONObject(i));
                books.add(eBook);
            }
        } catch (JSONException e) {
            //e.printStackTrace();
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<EBook> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<EBook> books) {
        this.books = books;
    }
}
