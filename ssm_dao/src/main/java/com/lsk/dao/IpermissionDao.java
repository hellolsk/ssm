package com.lsk.dao;

import com.lsk.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/4/13 11:35
 * @Description:ssm com.lsk.dao
 */
public interface IpermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission (permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission);
}
