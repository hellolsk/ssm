package com.lsk.service.impl;

import com.github.pagehelper.PageHelper;
import com.lsk.dao.IOrdersDao;
import com.lsk.domain.Orders;
import com.lsk.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/3/23 17:40
 * @Description:ssm com.lsk.service.impl
 */
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao ordersDao;


    @Override
    public List<Orders> findAll() {
        return ordersDao.findAll();
    }

    @Override
    public List<Orders> findAllByPage(Integer page, Integer size) {
        //必须在调用查询方法之前使用
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) {
        return ordersDao.findById(id);
    }
}
