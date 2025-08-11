package org.example.controller;
import org.example.annotation.mySystemlog;
import org.example.domain.ResponseResult;
import org.example.domain.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    //UserService是我们在yx-framework工程写的接口
    private UserService userService;

    @GetMapping("/userInfo")
    @mySystemlog(xxbusinessName = "查询个人信息")//接口描述，用于'日志记录'功能
    public ResponseResult userInfo(){
        //查询个人信息
        return userService.userInfo();
    }

    @PutMapping("userInfo")
    @mySystemlog(xxbusinessName = "更新用户信息")//接口描述，用于'日志记录'功能
    public ResponseResult updateUserInfo(@RequestBody User user){
        //更新个人信息
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @mySystemlog(xxbusinessName = "用户注册")//接口描述，用于'日志记录'功能
    public ResponseResult register(@RequestBody User user){
        //注册功能
        return userService.register(user);
    }
}