package com.fanlian.interviewcode.controller;

import com.fanlian.interviewcode.common.BaseResponse;
import com.fanlian.interviewcode.common.ResultUtils;
import com.fanlian.interviewcode.dto.UsersAndTags;
import com.fanlian.interviewcode.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author FanLian
 * @Create 2023-12-11 12:41
 * @Version 1.0
 * @Description
 */
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;



}
 