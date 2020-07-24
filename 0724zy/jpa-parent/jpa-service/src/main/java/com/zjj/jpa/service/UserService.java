package com.zjj.jpa.service;

import com.zjj.jpa.entity.MyPageImpl;
import com.zjj.jpa.entity.User;
import com.zjj.jpa.entity.UserVo;
import org.springframework.data.domain.Page;

public interface UserService {
    MyPageImpl list(UserVo userVo);

    boolean del(int id);

    boolean add(User user);
}
