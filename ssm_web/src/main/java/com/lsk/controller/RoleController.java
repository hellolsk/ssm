package com.lsk.controller;

import com.lsk.domain.Permission;
import com.lsk.domain.Role;
import com.lsk.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/4/13 13:19
 * @Description:ssm com.lsk.controller
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    public IRoleService roleService;
    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll";
    }
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Role> roleList = roleService.findAll();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    /**
     * 给角色关联可以关联的权限
     * @param roleId
     * @return
     */
    @RequestMapping("/findAllPermissionByRoleId")
    public ModelAndView findAllPermissionByRoleId(@RequestParam(name = "id",required = true)String roleId){
        Role role = roleService.findById(roleId);
        List<Permission> permissionList = roleService.findAllPermissionByRoleId(roleId);
        modelAndView.addObject("role",role);
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    /**
     * 给角色关联并插入
     * @param roleId
     * @param permissionIds
     * @return
     */
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true)String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll";
    }
}
