package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.ArticleVoMapper;
import org.example.service.ArticleVoService;
import org.example.vo.ArticleVo;
import org.springframework.stereotype.Service;


@Service
public class ArticleVoServiceImpl extends ServiceImpl<ArticleVoMapper, ArticleVo> implements ArticleVoService {

}