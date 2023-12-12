package com.fanlian.interviewcode.service.impl;



import cn.hutool.core.collection.CollectionUtil;
import com.fanlian.interviewcode.common.ErrorCode;

import com.fanlian.interviewcode.exception.ThrowUtils;
import com.fanlian.interviewcode.mapper.TagMapper;
import com.fanlian.interviewcode.mapper.UserMapper;
import com.fanlian.interviewcode.mapper.UserTagMapper;
import com.fanlian.interviewcode.model.Tag;
import com.fanlian.interviewcode.model.User;
import com.fanlian.interviewcode.service.UserService;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import java.util.stream.Collectors;


/**
 * @Author FanLian
 * @Create 2023-12-11 10:23
 * @Version 1.0
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private UserTagMapper userTagMapper;

    private static final Logger logger = LoggerFactory.getLogger( UserServiceImpl.class);

    @Override
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }


//    @Override
//    public void addBatchesTagForUser(UsersAndTags usersAndTags) {
//        List<String> tags = usersAndTags.getTags();
//        List<Integer> userIds = usersAndTags.getUserIds();
//
//        for (Integer userId : userIds) {
//            User user = userMapper.getUserById(userId);
//            if (BeanUtil.isEmpty(user)) {
//                continue;
//            }
//
//        }
//    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.queryAll();
    }

    @Override
    public List<User> getAllUsersWithTags() {
        return userMapper.queryUserWithTagAll();
    }

    @Override
    @Transactional
    public void addTags(Integer userId, List<String> addTags) {
        // 查询用户
        List<User> users = userMapper.queryUserWithTagsById(userId);
        if (CollectionUtil.isEmpty(users)) {
            ThrowUtils.throwIf(true, ErrorCode.NOT_FOUND_ERROR, "该用户不存在");
        }
        // 获取用户已有标签
        Set<String> existingTags = users.stream()
                .flatMap(user -> user.getTags().stream().map(Tag::getTagName))
                .collect(Collectors.toSet());
        // 筛选需要添加的标签
        List<String> tagsToAdd = addTags.stream()
                .filter(tag -> !existingTags.contains(tag))
                .collect(Collectors.toList());
        // 添加标签
        if (!CollectionUtil.isEmpty(tagsToAdd)) {
            userMapper.addTags(userId, tagsToAdd);
        }

    }

    @Override
    @Transactional
    public void removeTags(Integer userId, List<String> removeTags) {
        // 查询用户
        List<User> users = userMapper.queryUserWithTagsById(userId);
        if (CollectionUtil.isEmpty(users)) {
            ThrowUtils.throwIf(true, ErrorCode.NOT_FOUND_ERROR, "该用户不存在");
        }
        // 获取用户已有标签
        Set<String> existingTags = users.stream()
                .flatMap(user -> user.getTags().stream().map(Tag::getTagName))
                .collect(Collectors.toSet());
        // 筛选需要删除的标签
        List<String> tagsToRemove = removeTags.stream()
                .filter(existingTags::contains)
                .collect(Collectors.toList());
        // 删除标签
        if (!CollectionUtil.isEmpty(tagsToRemove)) {
            userMapper.removeTags(userId, tagsToRemove);
        }
    }


}
