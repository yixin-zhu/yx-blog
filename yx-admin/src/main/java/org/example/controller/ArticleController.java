package org.example.controller;

import org.example.domain.Article;
import org.example.domain.ResponseResult;
import org.example.dto.AddArticleDto;
import org.example.dto.ArticleDto;
import org.example.service.ArticleService;
import org.example.vo.ArticleByIdVo;
import org.example.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    //------------------------------新增博客文章-----------------------------

    @PostMapping
    @PreAuthorize("@ps.hasPermission('content:article:writer')")//权限控制
    public ResponseResult add(@RequestBody AddArticleDto articleDto){
        return articleService.add(articleDto);
    }

    //-----------------------------分页查询博客文章---------------------------

    @GetMapping("/list")
    public ResponseResult list(Article article, Integer pageNum, Integer pageSize){
        PageVo pageVo = articleService.selectArticlePage(article,pageNum,pageSize);
        return ResponseResult.okResult(pageVo);
    }

    //---------------------------根据文章id来修改文章--------------------------

    @GetMapping(value = "/{id}")
    //①先查询根据文章id查询对应的文章
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        ArticleByIdVo article = articleService.getInfo(id);
        return ResponseResult.okResult(article);
    }

    @PutMapping
    //②然后才是修改文章
    public ResponseResult edit(@RequestBody ArticleDto article){
        articleService.edit(article);
        return ResponseResult.okResult();
    }

    @DeleteMapping
    public ResponseResult remove(@RequestParam(value = "ids")String ids) {
        if (!ids.contains(",")) {
            articleService.removeById(ids);
        } else {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                articleService.removeById(id);
            }
        }
        return ResponseResult.okResult();
    }
}