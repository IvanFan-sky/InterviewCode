package com.fanlian.interviewcode.controller;

import cn.hutool.core.bean.BeanUtil;
import com.fanlian.interviewcode.common.BaseResponse;
import com.fanlian.interviewcode.common.ErrorCode;
import com.fanlian.interviewcode.common.ResultUtils;
import com.fanlian.interviewcode.dto.UserFilter;
import com.fanlian.interviewcode.dto.UsersAndTags;
import com.fanlian.interviewcode.exception.BusinessException;
import com.fanlian.interviewcode.exception.ThrowUtils;
import com.fanlian.interviewcode.model.User;
import com.fanlian.interviewcode.service.TagService;
import com.fanlian.interviewcode.service.UserService;
import com.fanlian.interviewcode.service.impl.UserServiceImpl;
import com.fanlian.interviewcode.thread.TagProcessor;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author FanLian
 * @Create 2023-12-11 10:24
 * @Version 1.0
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TagProcessor tagProcessor;

    @Autowired
    private TagService tagService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/test")
    public String test() {
        log.info("application 执行了........");
        ArrayList<String> tags = new ArrayList<>();
        tags.add("沿海");
        userService.removeTags(3, tags);
        return "hello world!";
    }


    @PostMapping("/addUser")
    public BaseResponse<Integer> addUser(@RequestBody User user) {
        Integer count = userService.addUser(user);
        if (!(count > 0)) {
            ThrowUtils.throwIf(true, ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(count);
    }

    @PostMapping("/getUsers")
    public BaseResponse<List<User>> getUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResultUtils.success(allUsers);
    }


    /**
     * 多现程批量添加用户标签和移除标签
     *
     * @param  usersAndTags
     * @return taggedUserCount
     */
    @PostMapping("/addBatchesTagForUser")
    public BaseResponse<Integer> addBatchesTagForUser(@RequestBody UsersAndTags usersAndTags) {
        if (BeanUtil.isEmpty(usersAndTags)) {
            logger.error("请求参数为空");
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "请求参数为空");
        }
        List<String> addTags = usersAndTags.getAddTags();
        List<String> removeTags = usersAndTags.getRemoveTags();
        List<Integer> userIds = usersAndTags.getUserIds();

        Integer taggedUserCount = tagProcessor.processTag(userIds, addTags, removeTags);
        return ResultUtils.success(taggedUserCount);
    }


    @RequestMapping("/queryPage")
    public BaseResponse<List<User>> queryPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userService.getAllUsersWithTags();
        for (User user : userList) {
            System.out.println("user:" + user);
        }
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        System.out.println("总页数: " + pageInfo.getPages());
        System.out.println("总记录数: " + pageInfo.getTotal());
        System.out.println("当前页数: " + pageInfo.getPageNum());
        System.out.println("当前页面记录数量: " + pageInfo.getSize());
        List<User> list = pageInfo.getList();
        return ResultUtils.success(list);
    }

    /**
     * 通过标签条件查询用户列表，并对结果进行分页返回
     *
     * @param userFilter
     * @return userPageIno
     */
    @RequestMapping("/queryUserByTageFilterPage")
    public BaseResponse<PageInfo<User>> queryUserByTageFilterPage(@RequestBody UserFilter userFilter) {
        PageInfo<User> userPageIno = userService.queryUserByTageFilter(userFilter);
        return ResultUtils.success(userPageIno);
    }


}
 