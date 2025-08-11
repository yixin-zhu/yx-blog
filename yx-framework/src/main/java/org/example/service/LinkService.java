package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.Link;
import org.example.domain.ResponseResult;
import org.example.vo.PageVo;


public interface LinkService extends IService<Link> {

    //查询友链
    ResponseResult getAllLink();

    //分页查询友链
    PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);
}