package com.fanlian.interviewcode.mapper;

import com.fanlian.interviewcode.model.UserTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserTagMapper {
     Integer insertTagForUser(@Param("userId") Integer userId, @Param("tagName") String tagName) ;

    int deleteByPrimaryKey(Integer userTagId);

    int insert(UserTag record);

    int insertSelective(UserTag record);

    UserTag selectByPrimaryKey(Integer userTagId);

    int updateByPrimaryKeySelective(UserTag record);

    int updateByPrimaryKey(UserTag record);
}