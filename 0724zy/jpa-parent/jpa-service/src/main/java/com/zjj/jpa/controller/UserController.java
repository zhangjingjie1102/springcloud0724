package com.zjj.jpa.controller;

import com.netflix.discovery.converters.Auto;
import com.zjj.jpa.entity.MyPageImpl;
import com.zjj.jpa.entity.User;
import com.zjj.jpa.entity.UserVo;
import com.zjj.jpa.repository.UserRepository;
import com.zjj.jpa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String test(){
        return "this is helthy";
    }


    @RequestMapping("testaa")
    public String testAdd(){
        User user = new User();
        user.setUsername("mytest");
        userRepository.save(user);
        return "success";
    }


    @RequestMapping("list")
    public MyPageImpl<User> list(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "3")int pageSize){
        UserVo userVo = new UserVo();
        userVo.setPage(page);
        userVo.setPageSize(pageSize);
        System.out.print(" 服务提供者  参数是  " + userVo);

        MyPageImpl<User> userPage = userService.list(userVo);
        log.info(" 已经获取数据了。。。。。。。。。。。。");
        userPage.getContent().iterator().forEachRemaining(x->{System.out.println("x is " + x);});
        log.info("page.class is " + userPage.getClass());
        return userPage;
    }

    @RequestMapping("del")
    public boolean del(@RequestParam(value = "id")int id){
        boolean flag = userService.del(id);
        return flag;
    }

    @RequestMapping("add")
    public boolean del(@RequestBody User user){
        return userService.add(user);
    }
}
