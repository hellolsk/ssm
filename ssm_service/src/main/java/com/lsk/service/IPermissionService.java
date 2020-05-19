package com.lsk.service;

import com.lsk.domain.Permission;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/4/13 14:25
 * @Description:ssm com.lsk.service
 */
public interface IPermissionService {

    public List<Permission> findAll();

    void save(Permission permission);
}
