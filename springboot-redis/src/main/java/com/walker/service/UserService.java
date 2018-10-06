package com.walker.service;

import com.walker.dao.UserDAO;
import com.walker.domain.UserDO;
import org.n3r.idworker.Sid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        String key = "tel_" + id;
        ValueOperations<String, UserDO> operations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            UserDO userDO = operations.get(key);
            logger.info(("从缓存中根据ID获取UserDO, key: {}") + key + "\n缓存中的数据是：" + userDO.toString());
            return userDO;
        }
        UserDO userDO = userDAO.findUserById(id);
        if (null == userDO) {
            logger.warn("数据库中不存在该用户, UserDO: {}" + userDO);
            return userDO;
        }
        //写入Redis，设置有效时间
        operations.set(key, userDO, 60 * 60 * 12, TimeUnit.SECONDS);
        logger.info("缓存中写入数据成功, key: {}" + key);
        return userDO;
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
        String key = "tel_" + userDO.getId();
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            logger.info("缓存中数据删除成功, key: {}" + key);
        }
        return userDAO.updateUser(userDO);
    }

    @DeleteMapping("/deleteUserById")
    public Integer deleteUserById(String id) {
        if (null == id) {
            logger.error("id is null, id: {}" + id);
            return null;
        }
        String key = "tel_" + id;
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            logger.info("缓存中数据删除成功, key: {}" + key);
        }
        return userDAO.deleteUserById(id);
    }
}
