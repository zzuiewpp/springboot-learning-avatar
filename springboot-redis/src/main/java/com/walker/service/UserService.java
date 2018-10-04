package com.walker.service;

import com.walker.dao.UserDAO;
import com.walker.domain.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author walker
 * @version 1.0
 * @since 2018-10-04 20:20
 */
@RestController
@RequestMapping("/user")
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private UserDAO userDAO;

    @GetMapping("/findUserById")
    public UserDO findUserById(String id) {
        if (null == id) {
            logger.error("id is null, id: {}" + id);
            return null;
        }
        return userDAO.findUserById(id);
    }

    @PutMapping("/saveUser")
    public Integer saveUser(UserDO userDO) {
        if (null == userDO) {
            logger.error("UserDO is null, UserDO: {}" + userDO);
            return -1;
        }
        return userDAO.saveUser(userDO);
    }

    @PostMapping("/updateUser")
    public Integer updateUser(UserDO userDO) {
        if (null == userDO) {
            logger.error("UserDO is null, UserDO: {}" + userDO);
            return -1;
        }
        return userDAO.updateUser(userDO);
    }

    @DeleteMapping("/deleteUserById")
    public Integer deleteUserById(String id) {
        if (null == id) {
            logger.error("id is null, id: {}" + id);
            return null;
        }
        return userDAO.deleteUserById(id);
    }
}
