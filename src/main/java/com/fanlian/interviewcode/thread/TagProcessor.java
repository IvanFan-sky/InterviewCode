package com.fanlian.interviewcode.thread;

import com.fanlian.interviewcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

    private ExecutorService executorService;


    private void createThreadPoolIfNotExists() {
        if (executorService == null || executorService.isShutdown()) {
            executorService = Executors.newFixedThreadPool(5);
        }
    }

    private void shutdownThreadPool() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

    public Integer processTag(List<Integer> userIds, List<String> addTags, List<String> removeTags) {
        // 创建线程池（如果不存在）
        createThreadPoolIfNotExists();
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (Integer userId : userIds) {
            Callable<Integer> tagTask = new TagTask(userService, userId, addTags, removeTags);
            tasks.add(tagTask);
        }
        // 执行任务并统计打标签用户数量
        int taggedUserCount = 0;
        try {
            List<Future<Integer>> results = executorService.invokeAll(tasks);
            for (Future<Integer> result : results) {
                taggedUserCount += result.get();
            }
            return taggedUserCount;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return 0;
        } finally {
            //任务执行完毕后关闭线程池
            shutdownThreadPool();
        }

    }


}
 