package com.zjj.jpa.controller;

import com.zjj.jpa.entity.MyPageImpl;
import com.zjj.jpa.entity.User;
import com.zjj.jpa.entity.UserVo;
import com.zjj.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("list")
    public MyPageImpl<User> list(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "3")int pageSize){
        System.err.println("pageSize:::"+pageSize+"page:::"+page);
        UserVo userVo = new UserVo();
        userVo.setPage(page);
        userVo.setPageSize(pageSize);
        MyPageImpl<User> userPage = userService.list(page,pageSize);

        System.out.println ("  消费者 ========== 已经获取数据了。。。。。。。。。。。。");
        userPage.getContent().iterator().forEachRemaining(x->{System.out.println("消费者  x is " + x);});

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
