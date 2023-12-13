package com.fanlian.interviewcode.dto;


import lombok.Data;


import java.util.List;

/**
 * @Author FanLian
 * @Create 2023-12-12 21:01
 * @Version 1.0
 * @Description
 */
@Data
public class UserFilter {
    private Integer pageNum;
    private Integer pageSize;
    private List<String> allMatch;
    private List<String> anyMatch;
    private List<String> notMatch;
}
 