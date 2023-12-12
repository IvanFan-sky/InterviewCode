package com.fanlian.interviewcode.thread;

import com.fanlian.interviewcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author FanLian
 * @Create 2023-12-12 14:08
 * @Version 1.0
 * @Description 标签任务类
 */
public class TagTask implements Runnable {
    @Autowired
    private UserService userService;

    private Integer userId;
    private List<String> addTags;
    private List<String> removeTags;

    public TagTask(UserService userService, Integer userId, List<String> addTags, List<String> removeTags) {
        this.userService = userService;
        this.userId = userId;
        this.addTags = addTags;
        this.removeTags = removeTags;
    }


    @Override
    public void run() {
        userService.addTags(userId, addTags);
        userService.removeTags(userId, removeTags);
    }

}
 