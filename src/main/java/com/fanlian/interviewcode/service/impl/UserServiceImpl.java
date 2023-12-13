package com.fanlian.interviewcode.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import com.fanlian.interviewcode.common.ErrorCode;

import com.fanlian.interviewcode.dto.UserFilter;
import com.fanlian.interviewcode.exception.BusinessException;
import com.fanlian.interviewcode.exception.ThrowUtils;
import com.fanlian.interviewcode.mapper.TagMapper;
import com.fanlian.interviewcode.mapper.UserMapper;
import com.fanlian.interviewcode.mapper.UserTagMapper;
import com.fanlian.interviewcode.model.Tag;
import com.fanlian.interviewcode.model.User;
import com.fanlian.interviewcode.service.UserService;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }


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
    public Integer addTags(Integer userId, List<String> addTags) {
        // 查询用户
        List<User> users = userMapper.queryUserWithTagsById(userId);
        if (CollectionUtil.isEmpty(users)) {
            logger.error("用户不存在");
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"用户不存在");
        }
        // 获取用户已有标签
        Set<String> existingTags = users.stream()
                .flatMap(user -> user.getTags().stream().map(Tag::getTagName))
                .collect(Collectors.toSet());
        Integer count = 0;
        // 筛选需要添加的标签
        List<String> tagsToAdd = addTags.stream()
                .filter(tag -> !existingTags.contains(tag))
                .collect(Collectors.toList());

        //加同步锁保证线程安全
        synchronized (this) {
            // 添加标签
            if (!CollectionUtil.isEmpty(tagsToAdd)) {
                count = userMapper.addTags(userId, tagsToAdd);
            }
        }
        return count;
    }

    @Override
    @Transactional
    public Integer removeTags(Integer userId, List<String> removeTags) {
        // 查询用户
        List<User> users = userMapper.queryUserWithTagsById(userId);
        if (CollectionUtil.isEmpty(users)) {
            logger.error("用户不存在");
           throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"用户不存在");
        }
        // 获取用户已有标签
        Set<String> existingTags = users.stream()
                .flatMap(user -> user.getTags().stream().map(Tag::getTagName))
                .collect(Collectors.toSet());
        // 筛选需要删除的标签
        List<String> tagsToRemove = removeTags.stream()
                .filter(existingTags::contains)
                .collect(Collectors.toList());
        Integer count = 0;
        //加同步锁保证线程安全
        synchronized (this) {
            // 删除标签
            if (!CollectionUtil.isEmpty(tagsToRemove)) {
                count = userMapper.removeTags(userId, tagsToRemove);
            }
        }
        return count;
    }

//    /**
//     * 使用Mybatis动态SQL对用户标签数据实现查询过滤逻辑 1.2s
//     * @param userFilter
//     * @return
//     */
//    @Override
//    public PageInfo<User> queryUserByTageFilter(UserFilter userFilter) {
//        // 校验 pageNum 和 pageSize 是否为空，为空则设置默认值
//        Integer pageNum = (userFilter.getPageNum() != null && userFilter.getPageNum() > 0) ? userFilter.getPageNum() : 1;
//         Integer pageSize = (userFilter.getPageSize() != null && userFilter.getPageSize() > 0) ? userFilter.getPageSize() : 10;
//        List<String> allMatch = userFilter.getAllMatch();
//        List<String> anyMatch = userFilter.getAnyMatch();
//        List<String> notMatch = userFilter.getNotMatch();
//        //开启分页
//        PageHelper.startPage(pageNum, pageSize);
//        List<User> users = userMapper.queryUserByTagFilter(allMatch, anyMatch, notMatch);
//        // 返回整个 PageInfo 对象
//        return new PageInfo<>(users);
//    }

    /**
     * java实现查询过滤 1.4s
     *
     * @param userFilter
     * @return
     */
    @Override
    public PageInfo<User> queryUserByTageFilter(UserFilter userFilter) {
        // 校验 pageNum 和 pageSize 是否为空，为空则设置默认值
        int pageNum = (userFilter.getPageNum() != null && userFilter.getPageNum() > 0) ? userFilter.getPageNum() : 1;
        int pageSize = (userFilter.getPageSize() != null && userFilter.getPageSize() > 0) ? userFilter.getPageSize() : 10;
        //查询所有用户及关联标签数据
        List<User> userList = userMapper.queryUserWithTagAll();
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<User> userByTag = filterUserByTag(userList, userFilter);
        //返回整个 PageInfo 对象
        return new PageInfo<>(userByTag);
    }

    /**
     * 实现根据标签条件过滤用户的逻辑
     *
     * @param users
     * @param userFilter
     * @return filteredUsers
     */
    private static List<User> filterUserByTag(List<User> users, UserFilter userFilter) {
        List<User> filteredUsers = new ArrayList<>();
        List<String> allMatch = userFilter.getAllMatch();
        List<String> anyMatch = userFilter.getAnyMatch();
        List<String> notMatch = userFilter.getNotMatch();

        for (User user : users) {
            List<String> tags = user.getTags().stream().map(Tag::getTagName).collect(Collectors.toList());
            //1.查询结果的用户必须同时包含allMatch所有标签
            boolean allMatchCondition = allMatch == null || allMatch.isEmpty() || new HashSet<>(tags).containsAll(allMatch);
            //2.查询结果的用户必须含有anyMatch中任意一个标签
            boolean anyMatchCondition = anyMatch == null || anyMatch.isEmpty() || tags.stream().anyMatch(anyMatch::contains);
            //3.查询结果的用户必须不能含有nonMatch中任意一个标签
            boolean notMatchCondition = notMatch == null || notMatch.isEmpty() || tags.stream().noneMatch(notMatch::contains);
            //以上条件符合，将过滤后的用户添加进集合
            if (allMatchCondition && anyMatchCondition && notMatchCondition) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
    }

}
