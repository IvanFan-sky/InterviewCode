package com.fanlian.interviewcode.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.Data;
/**
 * @Author FanLian
 * @Create 2023-12-11 10:38
 * @Version 1.0
 * @Description 标签表
 */
@Data
public class Tag implements Serializable {
    /**
     * 标签ID，自增唯一
     */
    private Integer id;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 标签关联的用户集合
     */
    private Set<User> users;

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