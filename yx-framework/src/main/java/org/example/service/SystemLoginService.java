package org.example.service;

import org.example.domain.ResponseResult;
import org.example.domain.User;


public interface SystemLoginService {

    //登录
    ResponseResult login(User user);

    //退出登录
    ResponseResult logout();
}