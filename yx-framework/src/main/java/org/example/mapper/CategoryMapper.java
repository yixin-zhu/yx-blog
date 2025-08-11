package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.domain.Category;
import org.example.vo.ArticleListVo;

import java.util.List;


public interface CategoryMapper extends BaseMapper<Category> {
    List<ArticleListVo> serchList(String search);
}