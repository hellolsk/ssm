package com.lsk.dao;

import com.lsk.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @Author:${六月的雨}
 * @Date:2020/3/24 11:04
 * @Description:ssm com.lsk.dao
 */
public interface IMemberDao {
    @Select("select * from member where id =#{id}")
    public Member findById(String id);
}
