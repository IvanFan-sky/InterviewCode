package com.fanlian.interviewcode.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @Author FanLian
 * @Create 2023-12-11 10:39
 * @Version 1.0
 * @Description 用户标签表
 */
@Data
public class UserTag implements Serializable {
    /**
     * 用户标签表id
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 标签ID
     */
    private Integer tagId;

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