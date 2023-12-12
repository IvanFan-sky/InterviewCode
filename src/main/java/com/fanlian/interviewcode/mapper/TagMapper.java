package com.fanlian.interviewcode.mapper;

import com.fanlian.interviewcode.model.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    Tag getTagByName(String tag);

    /**
     * 批量添加标签
     *
     * @return count
     */
    int insertTags(List<Tag> tags);

    /**
     * 查询所有标签
     * @return
     */
    List<Tag> queryAll();
}