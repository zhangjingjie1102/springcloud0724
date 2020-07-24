package com.zjj.jpa.service;

import com.zjj.jpa.entity.MyPageImpl;
import com.zjj.jpa.entity.User;
import com.zjj.jpa.entity.UserVo;
import com.zjj.jpa.service.impl.UserServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "jpa-service",fallback = UserServiceFallBack.class)
public interface UserService {

    @RequestMapping("/user/list")
    MyPageImpl<User> list(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "3")int pageSize);

    @RequestMapping("/user/del")
    boolean del(@RequestParam(value = "id")int id);

    @RequestMapping("/user/add")
    boolean add(@RequestBody User user);
}
