package com.fish.dao;

import com.fish.domain.Order;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/23
 * Time: 22:26
 */
@Repository
public class OrderDao extends BaseDao {
    private static final String FIND_ALL = "SELECT * FROM `order`";
    private static final String DONE = "UPDATE `order` SET done = 1 - done WHERE id=?";
    private static final String SAVE = "INSERT INTO `order`(address,`name`," +
            "phone,email,note,order_id,price,time)VALUES(?,?,?,?,?,?,?,?)";

    public List<Order> findAll() {
        final List<Order> list = new ArrayList<>();
        jdbcTemplate.query(FIND_ALL, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Order order = new Order();
                order.setAddress(rs.getString("address"));
                order.setEmail(rs.getString("email"));
                order.setId(rs.getInt("id"));
                order.setName(rs.getString("name"));
                order.setNote(rs.getString("note"));
                order.setOrderId(rs.getString("order_id"));
                order.setPhone(rs.getString("phone"));
                order.setPrice(rs.getDouble("price"));
                order.setTime(rs.getDate("time"));
                order.setDone(rs.getInt("done"));
                list.add(order);
            }
        });

        return list;
    }

    public boolean done(int id) {
        return jdbcTemplate.update(DONE, id) > 0;
    }

    public boolean save(Order order) {
        return jdbcTemplate.update(SAVE,
                order.getAddress(), order.getName(), order.getPhone()
                , order.getEmail(), order.getNote(), order.getOrderId(),
                order.getPrice(), order.getTime()) > 0;
    }
}
