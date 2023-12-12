package com.fanlian.interviewcode.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.Data;

/**
 * @Author FanLian
 * @Create 2023-12-11 10:37
 * @Version 1.0
 * @Description 用户表
 */
@Data
public class User implements Serializable {
    /**
     * 用户ID，自增唯一
     */
    private Integer id;

    /**
     * 用户名，非空
     */
    private String username;

    /**
     * 用户关联的标签集合
     */
    private Set<Tag> tags;

    /**
     * 密码，非空
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 记录创建时间
     */
    private Date createdAt;

    /**
     * 记录最后更新时间
     */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}