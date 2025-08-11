package org.example.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.example.constants.SystemConstants;
import org.example.domain.Comment;
import org.example.domain.ResponseResult;
import org.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

@Api(tags = "self comment controller", description = "self description comment controller")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    //CommentService是我们在yx-framework工程写的类
    private CommentService commentService;

    @GetMapping("commentList")
    //ResponseResult是我们在yx-framework工程写的类
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        //SystemConstants是我们写的用来解决字面值的常量类，Article_COMMENT代表0
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    //在文章的评论区发送评论。ResponseResult是我们在yx-framework工程写的类
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表",notes = "获取友链评论区的评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    //在友链的评论区发送评论。ResponseResult是我们在yx-framework工程写的类
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        //commentService是我们刚刚实现文章的评论区发送评论功能时(当时用的是addComment方法，现在用commentList方法)，写的类
        //SystemCanstants是我们写的用来解决字面值的常量类，Article_LINK代表1
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }
}