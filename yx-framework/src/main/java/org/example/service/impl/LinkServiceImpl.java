package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.constants.SystemConstants;
import org.example.domain.Link;
import org.example.domain.ResponseResult;
import org.example.mapper.LinkMapper;
import org.example.service.LinkService;
import org.example.utils.BeanCopyUtils;
import org.example.vo.LinkVo;
import org.example.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;


@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {

        //查询所有审核通过的友链
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        //要求查的是友链表status字段的值为0，注意SystemConstants是我们写的一个常量类，用来解决字面值的书写问题
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //把最后的查询结果封装成LinkListVo(我们写的实体类)。BeanCopyUtils是我们写的工具类
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);

        //封装响应返回。ResponseResult是我们在yx-framework工程的domain目录写的实体类
        return ResponseResult.okResult(linkVos);
    }

    //-----------------------------分页查询友链---------------------------------

    @Override
    public PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.like(StringUtils.hasText(link.getName()),Link::getName, link.getName());
        queryWrapper.eq(Objects.nonNull(link.getStatus()),Link::getStatus, link.getStatus());

        Page<Link> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);

        //转换成VO
        List<Link> categories = page.getRecords();

        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(categories);
        return pageVo;
    }
}