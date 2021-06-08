package com.fish.service;

import com.fish.dao.OrderDao;
import com.fish.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/23
 * Time: 22:33
 */
@Service
public class CartService {
    @Autowired
    private OrderDao orderDao;

    public List<Order> getAll() {
        return orderDao.findAll();
    }

    public boolean done(int id) {
        return orderDao.done(id);
    }

    public boolean save(Order order) {
        return orderDao.save(order);
    }
}
