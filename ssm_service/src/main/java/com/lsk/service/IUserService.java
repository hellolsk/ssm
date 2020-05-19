package com.lsk.service;

import com.lsk.domain.Role;
import com.lsk.domain.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/3/24 15:40
 * @Description:ssm com.lsk.service
 */
public interface IUserService extends UserDetailsService {

    List<Users> findAll();

    void save(Users users);

    Users findById(String id);

    List<Role> findOtherRoleByUserId(String userId);

    void addRoleToUser(String[] roleIds, String userId);
}
