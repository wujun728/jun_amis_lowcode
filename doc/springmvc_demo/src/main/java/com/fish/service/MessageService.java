package com.fish.service;

import com.fish.dao.MsgDao;
import com.fish.domain.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/22
 * Time: 15:44
 */
@Service
public class MessageService {
    @Autowired
    private MsgDao msgDao;

    public List<Msg> findAllMsg() {
        return msgDao.findAll();
    }

    public List<Msg> findMsgByPage(int page) {
        return msgDao.findByPage(page);
    }

    public boolean deleteById(int id) {
        return msgDao.deleteById(id);
    }

    public boolean addMsg(String email, String content, String ip) {
        return msgDao.addMsg(email, content, ip);
    }
}
