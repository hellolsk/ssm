package com.lsk.service.impl;

import com.lsk.dao.IRoleDao;
import com.lsk.domain.Permission;
import com.lsk.domain.Role;
import com.lsk.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/4/13 13:25
 * @Description:ssm com.lsk.service.impl
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    public IRoleDao roleDao;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
         roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findAllPermissionByRoleId(String roleId) {
        return roleDao.findAllPermissionByRoleId(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for(String permissionId : permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
