package com.nwuer.core.dao;

import com.nwuer.core.entity.task;

public interface taskMapper {
    int deleteByPrimaryKey(String id);

    int insert(task record);

    int insertSelective(task record);

    task selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(task record);

    int updateByPrimaryKey(task record);
}