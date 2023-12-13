package com.fanlian.interviewcode.mapper;

import com.fanlian.interviewcode.model.Tag;
import com.fanlian.interviewcode.model.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User user);

    int add(User user);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> queryAll();

    User getUserById(Integer userId);


    int insertUsers(List<User> users);

    Integer addTags(@Param("userId") Integer userId, @Param("addTags") List<String> addTags);

    Integer removeTags(@Param("userId") Integer userId, @Param("removeTags") List<String> removeTags);

    List<User> queryUserWithTagsById(Integer userId);

    List<User> queryUserWithTagAll();


    List<User> queryUserByTagFilter(@Param("allMatch") List<String> allMatch,
                              @Param("anyMatch") List<String> anyMatch,
                              @Param("notMatch") List<String> notMatch);
}