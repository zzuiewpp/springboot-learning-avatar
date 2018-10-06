package com.walker.dao;

import com.walker.domain.UserDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author walker
 * @version 1.0
 * @since 2018-10-06 19:02
 */
@Repository
@Mapper
public interface UserDAOMapper {
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "ctime", column = "utime"),
            @Result(property = "utime", column = "utime")
    })
    public UserDO findUserById(@Param("id") String id);

    //TODO
    public Boolean saveUser(UserDO userDO);

    //TODO
    public Boolean updateUser(UserDO userDO);

    //TODO
    public Boolean deleteUserById(String id);
}
