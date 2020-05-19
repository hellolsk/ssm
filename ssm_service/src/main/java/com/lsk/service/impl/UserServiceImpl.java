package com.lsk.service.impl;

import com.lsk.dao.IUserDao;
import com.lsk.domain.Role;
import com.lsk.domain.Users;
import com.lsk.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/3/24 15:40
 * @Description:ssm com.lsk.service.impl
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    public IUserDao userDao;
    //注入加密类
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<Users> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(Users users) {
        //加密
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userDao.save(users);
    }

    @Override
    public Users findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoleByUserId(String userId) {
        return userDao.findOtherRoleByUserId(userId);
    }

    @Override
    public void addRoleToUser(String[] roleIds, String userId) {
        //遍历string数组，拿到每一个roleId并插入
        for(String roleId : roleIds){
            userDao.addRoleToUser(roleId,userId);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("访问了userService");
        Users users = userDao.findByUsername(username);
        System.out.println(users.toString());
        List<SimpleGrantedAuthority> authoritys = getAuthority(users.getRoles());
        //User user = new User(users.getUsername(),users.getPassword(),getAuthority(users.getRoles()));
        User user = new User(users.getUsername(), users.getPassword(),users.getStatus()==0 ? false:true,true,true,true,authoritys);
        return user;
    }

    /**
     * 获取关联的角色list集合
     * @param roles
     * @return
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(Role role:roles){
            list.add(new SimpleGrantedAuthority(role.getRoleName()));
            System.out.println(role.toString());
        }
        return list;
    }


}
