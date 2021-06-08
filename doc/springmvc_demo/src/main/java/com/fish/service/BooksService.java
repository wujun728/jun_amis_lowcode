package com.fish.service;

import com.fish.dao.BookDao;
import com.fish.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/23
 * Time: 15:12
 */
@Service
public class BooksService {
    @Autowired
    private BookDao bookDao;

    public boolean addBook(Book book) {
        return bookDao.addBook(book);
    }

    public boolean deleteBook(int id) {
        return bookDao.deleteBook(id);
    }

    public boolean online(int id) {
        return bookDao.online(id);
    }

//    public boolean offline(int id) {
//        return bookDao.offline(id);
//    }

    public int maxId() {
        return bookDao.maxId();
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    public boolean updateBook(Book book, int id) {
        return bookDao.update(book,id);
    }
}
