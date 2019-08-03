package com.fh.shop.mapper.category;

import com.fh.shop.po.category.CategoryInfo;

import java.util.List;

public interface CategoryMapper {

    List<CategoryInfo> findChildCategoryList(Integer id);

    List<CategoryInfo> queryCategory();

    void addCategory(CategoryInfo categoryInfo);

    void updateCategory(CategoryInfo categoryInfo);

    void deleteCategory(List<Integer> ids);
}
