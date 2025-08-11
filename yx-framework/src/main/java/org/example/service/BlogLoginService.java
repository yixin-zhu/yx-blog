package org.example.service;

import org.example.domain.ResponseResult;
import org.example.domain.User;


public interface BlogLoginService {
    ResponseResult login(User user);

    //退出登录
    ResponseResult logout();
}