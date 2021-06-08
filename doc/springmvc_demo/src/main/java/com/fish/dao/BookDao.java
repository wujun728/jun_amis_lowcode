package com.fish.dao;

import com.fish.domain.Book;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/17
 * Time: 9:14
 */
@Repository
public class BookDao extends BaseDao {
    private static final String GET_BY_ID = "SELECT * FROM book WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM book";
    private static final String MAX_ID = "SELECT count(id) FROM book";

    /**
     * hard code author & press
     */
    private static final String ADD_BOOK =
            "INSERT INTO book(author,title,`desc`,press_desc,`size`,pages,print," +
                    "bookbinding,`count`,publish_time,add_time,price,press_id,author_id)" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,1,1)";
    private static final String DELETE_BOOK = "DELETE FROM book WHERE id =?";
    private static final String ONLINE = "UPDATE book SET online=1-online WHERE id=?";
    //    private static final String OFFLINE = "update book set offline=0 where id=?";
    Book book = new Book();

    private static Book processBook(Book book, ResultSet rs) throws SQLException {
        book.setId(rs.getInt("id"));
        book.setAuthor(rs.getString("author"));
        book.setTitle(rs.getString("title"));
        book.setDesc(rs.getString("desc"));
        book.setPressDesc(rs.getString("press_desc"));
        book.setSize(rs.getString("size"));
        book.setPages(rs.getInt("pages"));
        book.setBookbinding(rs.getString("bookbinding"));
        book.setCount(rs.getInt("count"));
        book.setPublishTime(rs.getDate("publish_time"));
        book.setAddTime(rs.getDate("add_time"));
        book.setPrice(rs.getDouble("price"));
        book.setPressId(rs.getInt("press_id"));
        book.setAuthorId(rs.getInt("author_id"));
        book.setOnline(rs.getInt("online"));
        return book;
    }

    public Book getById(final int id) {

        jdbcTemplate.query(GET_BY_ID, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                book = processBook(book, rs);
            }
        });
        Book rs = book;
        book = new Book();
        return rs;
    }

    public List<Book> getAll() {
        final List<Book> books = new ArrayList<>();
        jdbcTemplate.query(GET_ALL, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Book book = new Book();
                book = processBook(book, rs);
                books.add(book);
            }
        });
        return books;
    }

    public boolean addBook(Book book) {
        return jdbcTemplate.update(ADD_BOOK
                , book.getAuthor()
                , book.getTitle()
                , book.getDesc()
                , book.getPressDesc()
                , book.getSize()
                , book.getPages()
                , book.getPrint()
                , book.getBookbinding()
                , book.getCount()
                , book.getPublishTime()
                , book.getAddTime()
                , book.getPrice()
        ) != 0;
    }

    public boolean deleteBook(int id) {
        return jdbcTemplate.update(DELETE_BOOK, id) > 0;
    }

    public boolean online(int id) {
        return jdbcTemplate.update(ONLINE, id) > 0;
    }

//    public boolean offline(int id) {
//        return jdbcTemplate.update(OFFLINE, id) > 0;
//    }

    public int maxId() {
        return jdbcTemplate.queryForInt(MAX_ID);
    }

    private static final String UPDATE = "UPDATE book SET author=?,title=?,`desc`=?,press_desc=?,size=?,pages=?,print=?,bookbinding=?,`count`=?,publish_time=?,add_time=?,price=? WHERE id=?";

    public boolean update(Book book, int id) {
        return jdbcTemplate.update(UPDATE
                , book.getAuthor()
                , book.getTitle()
                , book.getDesc()
                , book.getPressDesc()
                , book.getSize()
                , book.getPages()
                , book.getPrint()
                , book.getBookbinding()
                , book.getCount()
                , book.getPublishTime()
                , book.getAddTime()
                , book.getPrice(), id
        ) > 0;
    }
}
