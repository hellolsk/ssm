package com.lsk.service.impl;

import com.lsk.dao.IpermissionDao;
import com.lsk.domain.Permission;
import com.lsk.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/4/13 14:27
 * @Description:ssm com.lsk.service.impl
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    public IpermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
