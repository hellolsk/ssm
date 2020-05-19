package com.lsk.controller;

import com.lsk.domain.Role;
import com.lsk.domain.Users;
import com.lsk.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/3/24 19:52
 * @Description:ssm com.lsk.controller
 */
@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    public IUserService userService;

    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Users> usersList = userService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usersList",usersList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Users users){
        userService.save(users);
        return "redirect:findAll";
    }

    /**
     * 根据id查询用户，并关联查询用户对应的角色即权限
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        Users user =  userService.findById(id);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    /**
     * 根据用户id查询用户可以添加的角色
     * @param userId
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId){
        //获取当前用户并存放到页面，是因为为了给用户分配角色时传userId执行插入操作
        Users user = userService.findById(userId);
        List<Role> roleList =  userService.findOtherRoleByUserId(userId);
        modelAndView.addObject("user",user);
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    /**
     *
     * @param roleIds 关联的角色可以是多个，所以id为数组类型
     * @param userId
     * @return
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "ids",required = true) String[] roleIds,@RequestParam(name = "userId",required = true) String userId){
        userService.addRoleToUser(roleIds,userId);
        return "redirect:findAll";
    }
}
