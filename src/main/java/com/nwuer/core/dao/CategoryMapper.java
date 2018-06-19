package com.nwuer.core.dao;

import com.nwuer.core.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}