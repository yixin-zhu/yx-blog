package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.domain.ArticleTag;
import org.example.mapper.ArticleTagMapper;
import org.example.service.ArticleTagService;
import org.springframework.stereotype.Service;


@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}