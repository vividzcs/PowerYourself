package com.nwuer.core.dao;

import com.nwuer.core.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}