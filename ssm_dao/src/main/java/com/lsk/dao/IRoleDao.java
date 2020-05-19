package com.lsk.dao;

import com.lsk.domain.Permission;
import com.lsk.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/3/24 17:17
 * @Description:ssm com.lsk.service
 */
public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(column="roleName",property="roleName"),
            @Result(column="roleDesc",property="roleDesc"),
            @Result(column = "id",property = "permissions",javaType = List.class,many =@Many(select = "com.lsk.dao.IpermissionDao.findByRoleId"))
    })
    List<Role> findRoleByUserId(String userId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) value(#{roleName},#{roleDesc})")
    public void save(Role role);

    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findAllPermissionByRoleId(String roleId);

    @Insert("insert into role_permission (roleId,permissionId) values (#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
