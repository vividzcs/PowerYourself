package com.nwuer.core.dao;

import com.nwuer.core.entity.Policy;

public interface PolicyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Policy record);

    int insertSelective(Policy record);

    Policy selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Policy record);

    int updateByPrimaryKey(Policy record);
}