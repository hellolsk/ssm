package com.lsk.dao;

import com.lsk.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/4/14 16:22
 * @Description:ssm com.lsk.dao
 */
public interface SysLogDao {
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    @Select("select * from syslog")
    List<SysLog> findAll();
}
