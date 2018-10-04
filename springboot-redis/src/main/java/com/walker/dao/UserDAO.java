package com.walker.dao;

import com.walker.domain.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author walker
 * @version 1.0
 * @since 2018-10-04 21:10
 */
@Repository
public class UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    public UserDO findUserById(String id) {
        String sql = "select * from users where id = ?";
        Object[] sqlParams = new Object[]{id};
        RowMapper<UserDO> rowMapper = new BeanPropertyRowMapper<>(UserDO.class);
        return jdbcTemplate.queryForObject(sql, sqlParams, rowMapper);
    }

    public Integer saveUser(UserDO userDO) {
        if (null == userDO) {
            logger.error("the object of UserDO is null, UserDO: {}" + userDO);
            return -1;
        }
        String sql = "insert into users(name, gender, tel) values(?, ?, ?)";
        Object[] sqlParams = new Object[]{userDO.getId(), userDO.getClass().getName(), userDO.getGender(), userDO.getTel()};
        return jdbcTemplate.update(sql, sqlParams);
    }

    public Integer updateUser(UserDO userDO) {
        if (null == userDO) {
            logger.error("the object of UserDO is null, UserDO: {}" + userDO);
            return -1;
        }
        String sql = "update users set name = ?, gernder = ?, tel = ?";
        Object[] sqpParams = new Object[]{userDO.getClass().getName(), userDO.getGender(), userDO.getTel()};
        return jdbcTemplate.update(sql, sqpParams);
    }

    public Integer deleteUserById(String id) {
        if (null == id) {
            logger.error("id is null, id: {}" + id);
            return -1;
        }
        String sql = "delete from users where id = ?";
        Object[] sqlParams = new Object[]{id};
        return jdbcTemplate.update(sql, sqlParams);
    }
}
