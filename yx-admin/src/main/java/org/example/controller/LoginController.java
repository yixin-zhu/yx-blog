package org.example.controller;

import org.example.domain.LoginUser;
import org.example.domain.Menu;
import org.example.domain.ResponseResult;
import org.example.domain.User;
import org.example.enums.AppHttpCodeEnum;
import org.example.exception.SystemException;
import org.example.service.MenuService;
import org.example.service.RoleService;
import org.example.service.SystemLoginService;
import org.example.utils.BeanCopyUtils;
import org.example.utils.RedisCache;
import org.example.utils.SecurityUtils;
import org.example.vo.AdminUserInfoVo;
import org.example.vo.RoutersVo;
import org.example.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class LoginController {

    @Autowired
    private SystemLoginService systemLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return systemLoginService.login(user);
    }

    //-----------------------------查询(超级管理员|非超级管理员)的权限和角色信息------------------------------------------

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户。SecurityUtils是我们在yx-framework写的类
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        //BeanCopyUtils是我们在yx-framework写的类
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        //封装响应返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    //-------------------------------------查询路由信息(权限菜单)--------------------------------------

    @GetMapping("/getRouters")
    //RoutersVo是我们在yx-framework工程写的类
    public ResponseResult<RoutersVo> getRouters(){
        //获取用户id
        Long userId = SecurityUtils.getUserId();

        //根据用户id来查询menu(权限菜单)。要求查询结果是tree的形式，也就是子父菜单树
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装响应返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }

    @Autowired
    private RedisCache redisCache;

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        //退出登录
        return systemLoginService.logout();
    }
}