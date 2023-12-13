package com.fanlian.interviewcode.thread;

import com.fanlian.interviewcode.common.ErrorCode;
import com.fanlian.interviewcode.exception.BusinessException;
import com.fanlian.interviewcode.service.UserService;
import com.fanlian.interviewcode.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author FanLian
 * @Create 2023-12-12 14:08
 * @Version 1.0
 * @Description 标签任务类
 */
public class TagTask implements Callable<Integer> {
    @Autowired
    private UserService userService;

    private Integer userId;
    private List<String> addTags;
    private List<String> removeTags;
    private static final Logger logger = LoggerFactory.getLogger(TagTask.class);

    public TagTask(UserService userService, Integer userId, List<String> addTags, List<String> removeTags) {
        this.userService = userService;
        this.userId = userId;
        this.addTags = addTags;
        this.removeTags = removeTags;
    }


    @Override
    public Integer call() throws BusinessException{
        try {
            Integer addTags1 = userService.addTags(userId, addTags);
            Integer removeTags1 = userService.removeTags(userId, removeTags);
            if ((addTags1 > 0) && (removeTags1 > 0)) {
                return 1;
            }
            return 0;
        }catch (BusinessException businessException) {
            // 记录异常
            logger.error("添加和移除标签操作失败", businessException);
            //抛出异常
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"添加和移除标签操作失败");
        }
    }
}
 