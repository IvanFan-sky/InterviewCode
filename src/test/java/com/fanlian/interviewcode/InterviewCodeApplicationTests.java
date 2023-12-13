package com.fanlian.interviewcode;

import com.fanlian.interviewcode.dto.UsersAndTags;
import com.fanlian.interviewcode.mapper.TagMapper;
import com.fanlian.interviewcode.mapper.UserMapper;
import com.fanlian.interviewcode.model.Tag;
import com.fanlian.interviewcode.model.User;
import com.fanlian.interviewcode.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class InterviewCodeApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private TagMapper tagMapper;



    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("tutu");
        user.setPassword("123456");
        int insert = userMapper.add(user);
        System.out.println(insert);
    }

    @Test
    void contextLoads2() {
        List<User> users = new ArrayList<>();

        User user = new User();
        user.setUsername("susu");
        user.setPassword("1123456");
        User user1 = new User();
        user1.setUsername("kaka");
        user1.setPassword("2123456");

        User user2 = new User();
        user2.setUsername("guaiguai");
        user2.setPassword("12123456");

        User user3 = new User();
        user3.setUsername("lala");
        user3.setPassword("11123456");
        User user4 = new User();
        user4.setUsername("gege");
        user4.setPassword("11123456");
        User user5 = new User();
        user5.setUsername("feifei");
        user5.setPassword("111123456");

        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        int insert = userMapper.insertUsers(users);
        System.out.println(insert);
    }


    @Test
    void test() {
        List<User> users = userMapper.queryAll();
        users.forEach(e -> System.out.println(e));
    }

    @Test
    void test2() {
        Tag tag = new Tag();
        tag.setTagName("沿海");
        tag.setCreatedAt(new Date());
        int insert = tagMapper.insert(tag);
        System.out.println(insert);
    }

    @Test
    void test3() {
        List<Tag> tags = new ArrayList<>();
        Tag tag2 = new Tag();
        tag2.setTagName("女性");
        tag2.setCreatedAt(new Date());
        Tag tag3 = new Tag();
        tag3.setTagName("活跃");
        tag3.setCreatedAt(new Date());
        Tag tag4 = new Tag();
        tag4.setTagName("广东");
        tag4.setCreatedAt(new Date());
        Tag tag5 = new Tag();
        tag5.setTagName("广西");
        tag5.setCreatedAt(new Date());
        Tag tag6 = new Tag();
        tag6.setTagName("新增");
        tag6.setCreatedAt(new Date());
        tags.add(tag2);
        tags.add(tag3);
        tags.add(tag4);
        tags.add(tag5);
        tags.add(tag6);

        int insert = tagMapper.insertTags(tags);
        System.out.println(insert);
    }

    @Test
    void test4() {
        List<User> users = userMapper.queryAll();
        for (User u : users) {
            System.out.println("用户id为：" + u.getId() + "用户名：" + u.getUsername() + "密码：" + u.getPassword());
        }
    }

    /**
     * 测试多线程对用户进行批量添加标签以及移除标签
     */
    @Test
    void test5() {
        Integer[] addUserIds = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<String> tagNames = new ArrayList<>();
        tagNames.add("数码");
        for (Integer userId:addUserIds) {
            userService.removeTags(userId,tagNames);
        }


    }

    /**
     * 添加标签 OK
     */
    @Test
    void test6() {
        List<String> addTags = new ArrayList<>();
//        addTags.add("新增");
        addTags.add("男性");
//        addTags.add("沿海");
        addTags.add("广西");
        userService.addTags(12, addTags);

    }

    /**
     * 测试addTags方法
     */
    @Test
    void testAddTag() {
        List<String> addTags = new ArrayList<>();
        addTags.add("新增");
        addTags.add("男性");
        Integer integer = userMapper.addTags(1, addTags);
        System.out.println(integer);

    }

    /**
     * 测试删除Tags方法
     */
    @Test
    void testRemoveTag() {
        List<String> addTags = new ArrayList<>();
        addTags.add("新增");
        addTags.add("女性");
        Integer integer = userMapper.removeTags(3, addTags);
        System.out.println(integer);

    }


    /**
     * 删除标签 OK
     */
    @Test
    void test7() {
        String[] tagNames = {"男性", "广西","新增"};
        List<String> tagNameList = Arrays.asList(tagNames);
        Integer integer = userMapper.removeTags(1, tagNameList);
        System.out.println(integer);
    }



    /**
     * 分页查询测试
     */
    @Test
    void test8() {
        Integer pageNum = 1;
        Integer pageSize = 5;
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.queryAll();
        for (User u : userList) {
            System.out.println("user:" + u);
        }

        PageInfo<User> page = new PageInfo<>(userList);
        System.out.println("总页数：" + page.getPages());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("开始行号：" + page.getStartRow());
        System.out.println("结束行号：" + page.getEndRow());
        System.out.println("当前页码：" + page.getPageNum());
        List<User> list = page.getList();
        for (User l : list) {
            System.out.println(l);
        }


    }

    /**
     * 查询所有用户以及其标签
     */
    @Test
    void test9() {
        List<User> users = userMapper.queryUserWithTagAll();
        users.forEach(System.out::println);
    }

    @Test
    void test10() {
        List<Tag> tags = tagMapper.queryAll();
        tags.forEach(System.out::println);
    }

    @Test
    void test11() {
        List<User> users = userMapper.queryUserWithTagsById(14);
        users.forEach(System.out::println);
    }
}
