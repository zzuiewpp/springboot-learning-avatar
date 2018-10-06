package com.walker.service;

import com.walker.dao.UserDAOMapper;
import com.walker.domain.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author walker
 * @version 1.0
 * @since 2018-10-06 19:12
 */
@RestController
@RequestMapping("/user")
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserDAOMapper userDAOMapper;

    @GetMapping("/findUserById")
    public UserDO findUserById(String id) {
        if (null == id) {
            logger.error("id is null, id: {}" + id);
            return null;
        }
        return userDAOMapper.findUserById(id);
    }
}
