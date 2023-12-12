package com.fanlian.interviewcode.service;

import com.fanlian.interviewcode.dto.UsersAndTags;
import com.fanlian.interviewcode.model.Tag;
import com.fanlian.interviewcode.model.User;

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

    void addTags(Integer userId, List<String> addTags);

    void removeTags(Integer userId, List<String> removeTags);
}