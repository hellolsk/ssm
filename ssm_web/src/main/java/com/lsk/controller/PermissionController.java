package com.lsk.controller;

import com.lsk.domain.Permission;
import com.lsk.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/4/13 14:22
 * @Description:ssm com.lsk.controller
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    public IPermissionService permissionService;
    ModelAndView modelAndView = new ModelAndView();
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Permission> permissionList = permissionService.findAll();
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll";
    }
}
