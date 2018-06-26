package com.nwuer.core.dao;

import com.nwuer.core.entity.Category;
import com.nwuer.core.vo.CategoryFormVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectAll(String uid);

    int countChildren(String id);
    int countChildrenJob(String id);

    CategoryFormVo selectVoById(String id);
    int updateByIdAndVo(CategoryFormVo categoryFormVo);

    //查分类,不等于自己
    List<Category> selectAllForUpdate(@Param("uid") String uid, @Param("id") String id);
}