package com.fish.dao;

import com.fish.domain.Admin;
import com.fish.web.admin.AdminCommand;
import com.fish.web.admin.UpdateCommand;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/20
 * Time: 10:16
 */
@Repository
public class AdminDao extends BaseDao {
    private static final String GET_ALL = "select * from admin";
    private static final String COUNT = "SELECT count(*) cnt FROM admin WHERE email=?";
    private static final String VALID = "SELECT count(*) cnt FROM admin WHERE email=? AND password=?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM admin WHERE email =?";
    private static final String UPDATE_PASS = "UPDATE admin SET password=?WHERE email=?";
    boolean flag = false;

    public boolean exists(String email) {
        flag = false;
        jdbcTemplate.query(COUNT, new Object[]{email}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                if (rs.getInt("cnt") != 0) {
                    flag = true;
                }
            }
        });
        return flag;
    }

    public boolean valid(String email, String password) {
        flag = false;
        jdbcTemplate.query(VALID, new Object[]{email, password}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                flag = rs.getInt("cnt") != 0;
            }
        });
        return flag;
    }

    public Admin findByEmail(String email) {
        final Admin admin = new Admin();
        jdbcTemplate.query(FIND_BY_EMAIL, new Object[]{email}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                admin.setAddTime(rs.getDate("add_time"));
                admin.setDesc(rs.getString("desc"));
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setRegistTime(rs.getDate("regist_time"));
                admin.setWebsite(rs.getString("website"));
                admin.setEmail(rs.getString("email"));
            }
        });
        return admin;
    }

    public boolean updatePass(String pass, String email) {
        return jdbcTemplate.update(UPDATE_PASS, pass, email) > 0;
    }

    private static final String UPDATE = "UPDATE admin SET `name`=?,website=?, `desc`=?,email=? WHERE id=?";

    public boolean update(UpdateCommand updateCommand, int id) {
        return jdbcTemplate.update(UPDATE, updateCommand.getUsername(), updateCommand.getWebsite()
                , updateCommand.getDesc(), updateCommand.getEmail(), id) > 0;
    }

    private static final String INSERT = "INSERT INTO admin(`name`,website,`desc`,regist_time,email,password)VALUES(?,?,?,?,?,?)";

    public boolean add(AdminCommand adminCommand) {
        return jdbcTemplate.update(INSERT,
                adminCommand.getUsername(),
                adminCommand.getWebsite(),
                adminCommand.getDesc(),
                new Date(),
                adminCommand.getEmail(),
                adminCommand.getPassword()) > 0;
    }
}
