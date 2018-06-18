package com.nwuer.core.dao;

import com.nwuer.core.entity.Condition;

public interface ConditionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Condition record);

    int insertSelective(Condition record);

    Condition selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Condition record);

    int updateByPrimaryKey(Condition record);
}