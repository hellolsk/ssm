package com.lsk.dao;

import com.lsk.domain.Orders;
import com.lsk.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/3/23 17:41
 * @Description:ssm com.lsk.dao
 */
public interface IOrdersDao {

     @Select("select * from orders")
     @Results({
             @Result(id=true,property = "id",column = "id"),
             @Result(column = "orderNum",property = "orderNum"),
             @Result(column = "orderTime",property = "orderTime"),
             @Result(column = "orderStatus",property = "orderStatus"),
             @Result(column = "peopleCount",property = "peopleCount"),
             @Result(column = "payType",property = "payType"),
             @Result(column = "orderDesc",property = "orderDesc"),
             //select the product property by productId in the column orders
             @Result(column = "productId",property = "product",javaType = Product.class,one = @One(select = "com.lsk.dao.IProductDao.findById"))
     })
     List<Orders> findAll();

     //通过id查询orders详情
     @Select("select * from orders where id = #{id}")
     @Results({
             @Result(id=true,column = "id",property = "id"),
             @Result(column = "orderNum",property = "orderNum"),
             @Result(column = "orderTime",property = "orderTime"),
             @Result(column = "orderStatus",property = "orderStatus"),
             @Result(column = "peopleCount",property = "peopleCount"),
             @Result(column = "payType",property = "payType"),
             @Result(column = "orderDesc",property = "orderDesc"),
             @Result(column = "productId",property = "product",one=@One(select = "com.lsk.dao.IProductDao.findById")),
             @Result(column = "memberId",property = "member",one=@One(select = "com.lsk.dao.IMemberDao.findById")),
             @Result(column = "id",property = "travellers",javaType = java.util.List.class,many = @Many(select = "com.lsk.dao.ITravellerDao.findByOrdersId"))
     })
     Orders findById(String id);
}
