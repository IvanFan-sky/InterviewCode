package com.fanlian.interviewcode.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.fanlian.interviewcode.common.ErrorCode;
import com.fanlian.interviewcode.dto.UsersAndTags;
import com.fanlian.interviewcode.exception.ThrowUtils;
import com.fanlian.interviewcode.mapper.TagMapper;
import com.fanlian.interviewcode.mapper.UserMapper;
import com.fanlian.interviewcode.mapper.UserTagMapper;
import com.fanlian.interviewcode.model.Tag;
import com.fanlian.interviewcode.model.User;
import com.fanlian.interviewcode.service.TagService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author FanLian
 * @Create 2023-12-11 12:36
 * @Version 1.0
 * @Description
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private UserTagMapper userTagMapper;


}
 