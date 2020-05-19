package com.lsk.service;

import com.lsk.domain.Orders;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/3/24 08:34
 * @Description:ssm com.lsk.service
 */
public interface IOrdersService {
    public List<Orders> findAll();

    List<Orders> findAllByPage(Integer page, Integer size);

    Orders findById(String id);
}
