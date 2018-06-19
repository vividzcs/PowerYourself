package com.nwuer.core.dao;

import com.nwuer.core.entity.Condition;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConditionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Condition record);

    int insertSelective(Condition record);

    Condition selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Condition record);

    int updateByPrimaryKey(Condition record);
}