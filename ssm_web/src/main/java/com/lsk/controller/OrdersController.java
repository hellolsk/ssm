package com.lsk.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsk.dao.IProductDao;
import com.lsk.domain.Orders;
import com.lsk.service.IOrdersService;
import com.lsk.service.impl.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/3/23 17:38
 * @Description:ssm com.lsk.controller
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        System.out.println(ordersList.toArray());
        modelAndView.addObject("ordersList", ordersList);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }

    /**
     * select orders by pageHelper
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAllByPage")
    public ModelAndView findAllByPage(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "4") Integer size){
        List<Orders> ordersList = ordersService.findAllByPage(page, size);
        //将结果集封装到page bean中
        PageInfo pageInfo = new PageInfo(ordersList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-page-list");
        return  modelAndView;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id){
        Orders orders = ordersService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }
}
