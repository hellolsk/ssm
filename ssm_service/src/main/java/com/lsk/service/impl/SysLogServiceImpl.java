package com.lsk.service.impl;

import com.lsk.dao.SysLogDao;
import com.lsk.domain.SysLog;
import com.lsk.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/4/14 16:21
 * @Description:ssm com.lsk.service.impl
 */
@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @RolesAllowed("ROLE_ADMIN")
    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}
