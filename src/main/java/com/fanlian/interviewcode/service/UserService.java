package com.fanlian.interviewcode.service;

import com.fanlian.interviewcode.dto.UserFilter;
import com.fanlian.interviewcode.model.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author FanLian
 * @Create 2023-12-11 10:22
 * @Version
 * @Description
 */
public interface UserService {

    Integer addUser(User user);

//    void addBatchesTagForUser(UsersAndTags usersAndTags);

    List<User> getAllUsers();

    List<User> getAllUsersWithTags();

    Integer addTags(Integer userId, List<String> addTags);

    Integer removeTags(Integer userId, List<String> removeTags);


    PageInfo<User> queryUserByTageFilter(UserFilter userFilter);
}