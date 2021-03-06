package com.walker.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author walker
 * @version 1.0
 * @since 2018-10-06 19:15
 */
@Table(name = "user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = -4689033417693732974L;

    @Getter
    @Setter
    private String id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "gender")
    @Getter
    @Setter
    private Integer gender;

    @Getter
    @Setter
    private String tel;

    @Getter
    @Setter
    private Date ctime;

    @Getter
    @Setter
    private Date utime;

    public UserDO() {
    }

    public UserDO(String id, String name, Integer gender, String tel, Date ctime, Date utime) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.tel = tel;
        this.ctime = ctime;
        this.utime = utime;
    }

    public String formatGenderStr() {
        if (0 == this.gender) {
            return "男";
        } else if (1 == this.gender) {
            return "女";
        }
        return "保密";
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", tel='" + tel + '\'' +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
