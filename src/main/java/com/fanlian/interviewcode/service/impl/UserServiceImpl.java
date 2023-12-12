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
        List<String> userTagList = new ArrayList<>();
        for (User u : users) {
            Set<Tag> uTags = u.getTags();
            if (CollectionUtil.isEmpty(uTags)) {
                userMapper.addTags(userId, addTags);
            } else {
                userTagList.addAll(uTags.stream().map(Tag::getTagName).collect(Collectors.toList()));
            }
        }
        Collection<String> inTag = CollectionUtil.intersection(userTagList, addTags);
        addTags.removeAll(inTag);
        // 检查是否还有需要添加的标签
        if (!CollectionUtil.isEmpty(addTags)) {
            // 添加剩余的标签
            userMapper.addTags(userId, addTags);
        }

    }

    @Override
    @Transactional
    public void removeTags(Integer userId, List<String> removeTags) {
        List<String> userTagList = new ArrayList<>();
        List<User> users = userMapper.queryUserWithTagsById(userId);
        if (CollectionUtil.isEmpty(users)) {
            ThrowUtils.throwIf(true, ErrorCode.NOT_FOUND_ERROR, "该用户不存在");
        }
        if (CollectionUtil.isEmpty(removeTags)) {
            ThrowUtils.throwIf(true, ErrorCode.PARAMS_ERROR, "删除标签为空");
        }
        for (User u : users) {
            Set<Tag> uTags = u.getTags();
            if (CollectionUtil.isEmpty(uTags)) {
                //ThrowUtils.throwIf(true, ErrorCode.PARAMS_ERROR, "用户没有标签");
                logger.warn("用户没有标签，用户ID：" + userId);
            } else {
                userTagList.addAll(uTags.stream().map(Tag::getTagName).collect(Collectors.toList()));
            }

        }
        Collection<String> inTag = CollectionUtil.intersection(userTagList, removeTags);
        if (!CollectionUtil.isEmpty(inTag)) {
            // 删除存在的标签
            userMapper.removeTags(userId, (List<String>) inTag);
        }
    }


}
