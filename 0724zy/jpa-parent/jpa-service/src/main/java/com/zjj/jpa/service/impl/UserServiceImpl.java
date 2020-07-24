package com.zjj.jpa.service.impl;

import com.netflix.discovery.converters.Auto;
import com.zjj.jpa.entity.MyPageImpl;
import com.zjj.jpa.entity.User;
import com.zjj.jpa.entity.UserVo;
import com.zjj.jpa.repository.UserRepository;
import com.zjj.jpa.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public MyPageImpl<User> list(UserVo userVo) {

//        Specification specification = new Specification<UserVo>() {
//           //动态的生成查询条件
//            @Override
//            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                //保存查询条件
//                List<Predicate> list = new ArrayList<>();
//                //根据username查询
//                if(!StringUtils.isEmpty(userVo.getUsername())){
//                    Predicate username = criteriaBuilder.like(root.get("username"), "%" + userVo.getUsername() + "%");
//                    //查询条件保存到集合中
//                    list.add(username);
//                }
//                if(!StringUtils.isEmpty(userVo.getName())){
//                    Predicate name = criteriaBuilder.like(root.get("name"), "%" + userVo.getName() + "%");
//                    //查询条件保存到集合中
//                    list.add(name);
//                }
////                if(userVo.getSex()!=0){
////                    Predicate sex = criteriaBuilder.equal(root.get("sex"), userVo.getSex());
////                    //查询条件保存到集合中
////                    list.add(sex);
////                }
//                Predicate[] predicates = list.toArray(new Predicate[list.size()]);
//
//                //所有的查询条件在一起，都组合到一起
//                Predicate predicatesAll = criteriaBuilder.and(predicates);
//                return predicatesAll;
//            }
//        };


        // 生成分页的对象
        Pageable pageable = PageRequest.of(userVo.getPage()-1,userVo.getPageSize(), Sort.Direction.DESC,"uid");
        PageImpl<User> userPage = (PageImpl<User>)userRepository.findAll(pageable);
        return new MyPageImpl(userPage);

        //使用动态sql 查询的结果
//        Page page = userRepository.findAll(specification, pageable);
//        return new MyPageImpl((PageImpl) page);
    }

    @Override
    public boolean del(int id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public boolean add(User user) {

       try{
           User user1 = userRepository.saveAndFlush(user);
           if(user1.getUid()>0){
               return true;
           }else{
               return false;
           }
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }
}