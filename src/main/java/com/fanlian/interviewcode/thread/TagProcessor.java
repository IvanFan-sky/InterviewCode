package com.fanlian.interviewcode.thread;

import com.fanlian.interviewcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author FanLian
 * @Create 2023-12-12 14:13
 * @Version 1.0
 * @Description
 */
@Component
public class TagProcessor {
    @Autowired
    private UserService userService;

    private final ExecutorService executorService;

    public TagProcessor(UserService userService) {
        this.userService = userService;
        this.executorService = Executors.newFixedThreadPool(5);//创建10个固定线程池
    }

    public void processTag(List<Integer> userIds, List<String> addTags, List<String> removeTags) {
        for (Integer userId : userIds) {
            Runnable task = new TagTask(userService, userId, addTags, removeTags);
            executorService.submit(task);
        }
       // executorService.shutdown();
    }

}
 