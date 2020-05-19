package com.lsk.dao;

import com.lsk.domain.Travellers;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/3/24 11:23
 * @Description:ssm com.lsk.dao
 */
public interface ITravellerDao {
    //先从中间表中依据ordersId查询travellerId,然后依据id查询traveller
    @Select("select * from travellers where id in(select travellerId from order_traveller where orderId=#{id})")
    public List<Travellers> findByOrdersId(String id);
}
