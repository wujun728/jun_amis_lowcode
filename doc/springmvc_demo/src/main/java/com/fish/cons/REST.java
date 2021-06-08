package com.fish.cons;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/18
 * Time: 22:40
 */
public class REST {
    public static final String INDEX = "/";
    public static final String GET_BOOKS = "/books";
    public static final String GET_BOOKS_DETAIL = "/books/{bookId}";
    public static final String SEARCH_BOOK_DETAIL = "/book/{bookId}";
    public static final String SEARCH = "/search";
    public static final String POST_SEARCH = "/search";
    public static final String ABOUT = "/about";
    public static final String ADMIN = "/admin/mgr";
    public static final String PAGE = "{number}";
    public static final String LOGIN = "/admin/login";
    public static final String LOGOUT = "/admin/logout";
    public static final String ADMIN_ADMINS = "/admin/admins";
    public static final String PASSCHECK = "/admin/passcheck";
    public static final String ADMIN_MSG = "/admin/message";
    public static final String MSG = "/msg";
    public static final String ADMIN_BOOKS = "/admin/books";
}
