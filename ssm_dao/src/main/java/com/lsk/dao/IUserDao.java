package com.lsk.dao;

import com.lsk.domain.Role;
import com.lsk.domain.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/3/24 17:10
 * @Description:ssm com.lsk.dao
 */
public interface IUserDao {

    //login by username and find the role by the userId from the user_roles
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class, many = @Many(select = "com.lsk.dao.IRoleDao.findRoleByUserId"))
    })
     Users findByUsername(String username);

    @Select("select * from users")
    public List<Users> findAll();

    @Insert("insert into users(email,username,password,phoneNum,status) value(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(Users users);

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class, many = @Many(select = "com.lsk.dao.IRoleDao.findRoleByUserId"))
    })
    Users findById(String id);

    /**
     * 根据用户id查询可以关联的角色，该角色不能是当前用户所拥有的，不在users_role里
     * @param userId
     * @return
     */
    @Select("select * from role where id not in(select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoleByUserId(String userId);

    /**
     * 关联操作字中间表里执行
     * @param roleId，未来防止参数名无法区分，这里应显示指出参数名
     * @param userId
     */
    @Insert("insert into users_role (roleId,userId) values (#{roleId},#{userId})")
    void addRoleToUser(@Param("roleId") String roleId, @Param("userId") String userId);
}
