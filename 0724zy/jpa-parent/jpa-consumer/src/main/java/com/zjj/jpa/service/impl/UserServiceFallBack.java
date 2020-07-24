package com.zjj.jpa.service.impl;

import com.zjj.jpa.entity.MyPageImpl;
import com.zjj.jpa.entity.User;
import com.zjj.jpa.entity.UserVo;
import com.zjj.jpa.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserServiceFallBack  implements UserService {
//    @Override
//    public MyPageImpl<User> list(UserVo userVo) {
//        System.out.println(" 对不起，熔断了。。。。");
//        return  null;
//    }

    @Override
    public MyPageImpl<User> list(int page,int pageSize) {
        return null;
    }

    @Override
    public boolean del(int id) {
        return false;
    }

    @Override
    public boolean add(User user) {
        return false;
    }
}
