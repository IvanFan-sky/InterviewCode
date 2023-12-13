package com.fanlian.interviewcode;


import com.fanlian.interviewcode.model.User;
import com.fanlian.interviewcode.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author FanLian
 * @Create 2023-12-13 12:05
 * @Version 1.0
 * @Description
 */
@SpringBootTest
public class ThreadTest {
    @Autowired
    private UserService userService;

    @Test
    public void testThreadUserByTags() {
        // 创建用户列表
        List<Integer> userIds = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
        List<String> addTags = new ArrayList<>(Arrays.asList("新增", "女性"));
        List<String> removeTags = new ArrayList<>(Arrays.asList("新增", "手机", "数码"));

        //线程开始时间
        long start = System.currentTimeMillis();
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 提交任务到线程池
        List<Callable<Integer>> tasks = new ArrayList<>();

        for (Integer id : userIds) {
            tasks.add(() -> {
                userService.addTags(id, addTags);
                userService.removeTags(id, removeTags);
                return 1;
            });

        }
        // 执行任务并统计打标签用户数量
        int taggedUserCount = 0;
        try {
            List<Future<Integer>> results = executorService.invokeAll(tasks);
            for (Future<Integer> result : results) {
                taggedUserCount += result.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            executorService.shutdown();
        }

        // 打印最终用户状态和打标签数量
        System.out.println("打标签用户数量: " + taggedUserCount);
        System.out.println("执行耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

}
 