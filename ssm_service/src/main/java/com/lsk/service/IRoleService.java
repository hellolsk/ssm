package com.lsk.service;

import com.lsk.domain.Permission;
import com.lsk.domain.Role;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/4/13 13:22
 * @Description:ssm com.lsk.service
 */
public interface IRoleService {
    public List<Role> findAll();

    void save(Role role);

    Role findById(String roleId);

    List<Permission> findAllPermissionByRoleId(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
