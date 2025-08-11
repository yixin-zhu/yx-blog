package org.example.controller;

import org.example.annotation.mySystemlog;
import org.example.domain.Article;
import org.example.domain.Category;
import org.example.domain.ResponseResult;
import org.example.mapper.CategoryMapper;
import org.example.service.ArticleService;
import org.example.vo.ArticleListVo;
import org.example.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author yixin
 * @date 2024/09/09
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    //注入公共模块的ArticleService接口
    private ArticleService articleService;

    @GetMapping("/list")
    //Article是公共模块的实体类
    public List<Article> test(){
        //查询数据库的所有数据
        return articleService.list();
    }

    @GetMapping("/hotArticleList")
    //ResponseResult是yx-framework工程的domain目录的类
    public ResponseResult hotArticleList(){
        //查询热门文章，封装成ResponseResult返回
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    @Autowired
    CategoryMapper categoryMapper;

    @GetMapping("/articleList")
    //ResponseResult是yx-framework工程的domain目录的类
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId, @RequestParam(required = false) String search){
        if (!Objects.equals(search, "")){
            List<ArticleListVo> articleListVo = categoryMapper.serchList(search);
            if (!articleListVo.isEmpty()) {
                PageVo pageVo = new PageVo(articleListVo,articleListVo.get(0).getTotal());
                List<ArticleListVo> list = pageVo.getRows();
                for (ArticleListVo one : list) {
                    Category category = categoryMapper.selectById(one.getId());
                    one.setCategoryName(category.getName());
                    one.setCategoryId(category.getId());
                }

                return ResponseResult.okResult(pageVo);
            }

        }

        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/{id}") //路径参数形式的HTTP请求，注意下面那行只有加@PathVariable注解才能接收路径参数形式的HTTP请求
    //ResponseResult是yx-framework工程的domain目录的类
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {//注解里指定的id跟上一行保持一致

        //根据id查询文章详情
        return articleService.getArticleDetail(id);

    }

    @PutMapping("/updateViewCount/{id}")
    @mySystemlog(xxbusinessName = "根据文章id从mysql查询文章")//接口描述，用于'日志记录'功能
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }
}