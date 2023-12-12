package com.fanlian.interviewcode.dto;

import com.fanlian.interviewcode.model.Tag;
import com.fanlian.interviewcode.model.User;
import lombok.Data;

import java.util.List;

/**
 * @Author FanLian
 * @Create 2023-12-11 14:26
 * @Version 1.0
 * @Description
 */
@Data
public class UsersAndTags {
    List<Integer> userIds;
    List<String> addTags;
    List<String> removeTags;
}
 