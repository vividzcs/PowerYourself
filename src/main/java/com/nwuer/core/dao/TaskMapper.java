package com.nwuer.core.dao;

import com.nwuer.core.entity.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper {
    int deleteByPrimaryKey(String id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}