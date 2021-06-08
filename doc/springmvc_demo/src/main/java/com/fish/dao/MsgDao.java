package com.fish.dao;

import com.fish.domain.Msg;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/22
 * Time: 15:45
 */
@Repository
public class MsgDao extends BaseDao {
    private static final String FIND_ALL = "select * from msg";
    private static final String DELETE_BY_ID = "delete from msg where id = ?";
    private static final String INSERT = "insert into msg(email,content,create_time,ip)values(?,?,?,?)";

    public List<Msg> findAll() {
        final List<Msg> msgs = new ArrayList<>();
        jdbcTemplate.query(FIND_ALL, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Msg msg = new Msg();
                msg.setContent(rs.getString("content"));
                msg.setCreateTime(rs.getDate("create_time"));
                msg.setIp(rs.getString("ip"));
                msg.setEmail(rs.getString("email"));
                msg.setId(rs.getInt("id"));
                msgs.add(msg);
            }
        });
        return msgs;
    }

    public List<Msg> findByPage(int page) {
        List<Msg> msgs = findAll();
        // 10 per page
        List<Msg> rs = new ArrayList<>();
        int pageMax = msgs.size() / 10 + 1;
        if (page > pageMax) {
            page = 1;
        }

        if (page <= 0) {
            page = 1;
        }

        int flag = pageMax == page ? msgs.size() - (page - 1) * 10 : 10;
        for (int start = (page - 1) * 10, j = 0;
             j < flag; j++, start++) {
            rs.add(msgs.get(start));
        }
        return rs;
    }

    public boolean deleteById(int id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
        return jdbcTemplate.update(DELETE_BY_ID, id) == 0;
    }

    public boolean addMsg(String email, String content, String ip) {
        return jdbcTemplate.update(INSERT, email, content, new Date(), ip) != 0;
    }
}
