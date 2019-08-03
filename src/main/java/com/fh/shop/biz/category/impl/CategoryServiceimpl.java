package com.fh.shop.biz.category.impl;

import com.fh.shop.biz.category.ICategoryService;
import com.fh.shop.mapper.category.CategoryMapper;
import com.fh.shop.po.category.CategoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>包名称：com.fh.shop.biz.category.impl
 * 类名称：CategoryServiceimpl
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/2614:20
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */

@Service("categoryService")
public class CategoryServiceimpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<CategoryInfo> findChildCategoryList(Integer id) {
        return categoryMapper.findChildCategoryList(id);
    }

    @Override
    public List<CategoryInfo> queryCategory() {
        List<CategoryInfo> categoryInfo = categoryMapper.queryCategory();
        return categoryInfo;
    }

    @Override
    public void addCategory(CategoryInfo categoryInfo) {
        categoryMapper.addCategory(categoryInfo);
    }

    @Override
    public void updateCategory(CategoryInfo categoryInfo) {
        categoryMapper.updateCategory(categoryInfo);
    }

    @Override
    public void deleteCategory(List<Integer> ids) {
        categoryMapper.deleteCategory(ids);
    }
}
