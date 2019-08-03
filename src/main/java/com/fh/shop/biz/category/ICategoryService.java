package com.fh.shop.biz.category;

import com.fh.shop.po.category.CategoryInfo;

import java.util.List;

public interface ICategoryService {


    List<CategoryInfo> findChildCategoryList(Integer id);

    List<CategoryInfo> queryCategory();

    void addCategory(CategoryInfo categoryInfo);

    void updateCategory(CategoryInfo categoryInfo);

    void deleteCategory(List<Integer> ids);
}
