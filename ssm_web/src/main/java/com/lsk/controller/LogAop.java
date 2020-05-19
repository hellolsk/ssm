package com.lsk.controller;

import com.lsk.domain.SysLog;
import com.lsk.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 用于aop处理日志的组件，注入spring容器中
 * @Author:${六月的雨}
 * @Date:2020/4/14 14:29
 * @Description:ssm com.lsk.controller
 */

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    //记录访问开始时间的data
    private Date startTime;
    //记录执行的类名
    private Class executionClass;
    //反射记录访问的方法名
    private Method method;

    //连接点，作用在方法上
    private JoinPoint joinPoint;

    //配置前置通知及表达式
    @Before("execution(public * com.lsk.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        startTime = new Date();

        //获取访问的类
        executionClass = joinPoint.getTarget().getClass();

        //获取到方法名
        String methodName = joinPoint.getSignature().getName();
        //获取方法参数
        Object[] args = joinPoint.getArgs();
        if(args==null || args.length==0){
             method = executionClass.getMethod(methodName);
        }else{
            //有参数方法的获取，还需传入一个class数组类型的参数
            Class[] classes = new Class[args.length];
            for(int i = 0;i<args.length;i++){
                classes[i] = args[i].getClass();
            }
             method = executionClass.getMethod(methodName, classes);
        }
    }

    /**
     * 后置通知，获取离开的时间点
     * @param joinPoint
     */
    @After("execution(public * com.lsk.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint){

        String url = "";

        //1,统计时长
        Long visitTime = new Date().getTime()-startTime.getTime();

        //2，通过request获取ip地址
        String ip = request.getRemoteAddr();
        System.out.println("URL:"+request.getRequestURL());//测试
        System.out.println("URI:"+request.getRequestURI());//测试

        //3,获取访问的url，这里主要拿到注解上的方法名并拼接路径
        //3.1拿到类上的@RequestMapping对象
        if(executionClass!=null && method!=null && executionClass!=LogAop.class){//不需要统计日志类的访问记录
            RequestMapping classAnnotation= (RequestMapping)executionClass.getAnnotation(RequestMapping.class);
            //3.2获取方法上的对象
            if(classAnnotation!=null){
                RequestMapping methodAnnotation = (RequestMapping)method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    //注意注解Annotation为数组类型
                    url = classAnnotation.value()[0]+methodAnnotation.value()[0];
                }
            }
        }

        //4,获取操作者名字
        SecurityContext context = SecurityContextHolder.getContext();
        //SecurityContext context = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");

        //4.1从context上下文中获取当前身份对象object，强转为security中的user类型
        String username = ((User) (context.getAuthentication().getPrincipal())).getUsername();

        //封装所有属性
        SysLog sysLog = new SysLog();
        sysLog.setIp(ip);
        sysLog.setExecutionTime(visitTime);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(startTime);
        sysLog.setMethod(executionClass.getName()+method.getName());

        sysLogService.save(sysLog);
    }
}
