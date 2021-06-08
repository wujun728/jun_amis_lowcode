package com.fish.service;

import com.fish.dao.BookDao;
import com.fish.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/17
 * Time: 9:49
 */
@Service
public class IndexService {
    @Autowired
    private BookDao bookDao;

    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    public Book getBookById(int id) {
        return bookDao.getById(id);
    }
}
