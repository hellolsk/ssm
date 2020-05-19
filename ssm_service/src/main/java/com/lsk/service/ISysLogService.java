package com.lsk.service;

import com.lsk.domain.SysLog;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/4/14 16:20
 * @Description:ssm com.lsk.service
 */
public interface ISysLogService {
    void save(SysLog sysLog);

    List<SysLog> findAll();
}
