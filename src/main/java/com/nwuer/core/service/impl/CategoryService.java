package com.nwuer.core.service.impl;

import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.common.util.UuidUtil;
import com.nwuer.core.dao.CategoryMapper;
import com.nwuer.core.entity.Category;
import com.nwuer.core.service.ICategoryService;
import com.nwuer.core.vo.CategoryFormVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vividzc
 * @date 2018/6/23 15:53
 */
@Slf4j
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    public ServerResponse addCategory(CategoryFormVo categoryFormVo){

        Category category = new Category();
        category.setId(UuidUtil.get32UUID());
        category.setCategoryName(categoryFormVo.getCategoryName());
        category.setParentId(categoryFormVo.getParentId());
        category.setUserId(categoryFormVo.getUserId());

        int rowCount = categoryMapper.insert(category);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    @Transactional
    public ServerResponse delete(String id) {
        if(categoryMapper.countChildren(id) > 0 || categoryMapper.countChildrenJob(id) >0){
            return ServerResponse.createByErrorMessage("分类下还有分类或文章,请删除后再重试");
        }
        //开始删除
        int resultCount = categoryMapper.deleteByPrimaryKey(id);
        if(resultCount>0){
            return ServerResponse.createBySuccess("删除成功");
        }else{
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }



    /**
     * 得到一个list,list中是一个个数组,一个是category对象,一个是层次
     * @return
     */
    public List<Object[]> getCategoryOrdered(String uid,String id) {
        List<Category> list = this.categoryMapper.selectAll(uid);
        List<Object[]> listRs = new ArrayList<Object[]>();
        this.categoryOrder(listRs, list, id, 1);
        return listRs;

    }
    public void categoryOrder(List<Object[]> listRs, List<Category> list, String id, int level) {
        for(Category c : list) {
            if(c.getParentId().equals(id)) {
                listRs.add(new Object[]{c,level});
                //开始找pid==c.id的栏目
                categoryOrder(listRs,list,c.getId(),level+1);
            }
        }
    }

    public List<Category> listCategory(String uid) {
        return categoryMapper.selectAll(uid);
    }

    public ServerResponse selectById(String id) {
        CategoryFormVo categoryFormVo = categoryMapper.selectVoById(id);
        if(categoryFormVo == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }else {
            return ServerResponse.createBySuccess(categoryFormVo);
        }
    }

    @Transactional
    public ServerResponse update(CategoryFormVo categoryFormVo) {
        int affected = categoryMapper.updateByIdAndVo(categoryFormVo);
        if(affected > 0){
            return ServerResponse.createBySuccess("修改成功");
        }else {
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }

    public List<Object[]> getCategoryOrderedForUpdate(String uid,String thisCategory, String id) {
        List<Category> list = this.categoryMapper.selectAllForUpdate(uid,thisCategory);
        List<Object[]> listRs = new ArrayList<Object[]>();
        this.categoryOrder(listRs, list, id, 1);
        return listRs;
    }
    @Transactional
    public void deleteByUserId(String id) {
        categoryMapper.deleteByUserId(id);
    }
}
