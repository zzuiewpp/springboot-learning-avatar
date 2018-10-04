package com.walker.service;

import com.walker.domain.UserDO;

/**
 * @author walker
 * @version 1.0
 * @since 2018-10-04 20:14
 */
public interface UserService {
    UserDO findUserById(String id);

    Boolean saveUser(UserDO userDO);

    Boolean updateUser(UserDO userDO);

    Boolean deleteUserById(String id);
}
