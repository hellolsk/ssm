package com.lsk.controller;

import com.lsk.domain.SysLog;
import com.lsk.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/4/14 17:07
 * @Description:ssm com.lsk.controller
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll")
    private ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll();
        modelAndView.addObject("sysLogs",sysLogs);
        modelAndView.setViewName("syslog-list");
        return modelAndView;
    }
}
