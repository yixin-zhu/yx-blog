package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.Category;
import org.example.domain.ResponseResult;
import org.example.vo.CategoryVo;
import org.example.vo.PageVo;

import java.util.List;


public interface CategoryService extends IService<Category> {
    //查询文章分类的接口
    ResponseResult getCategoryList();

    //写博客-查询文章分类的接口
    List<CategoryVo> listAllCategory();

    //分页查询分类列表
    PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize);
}