package org.example.springbootloginregisterdemo.controller;

import org.example.springbootloginregisterdemo.entity.User;
import org.example.springbootloginregisterdemo.exception.ServiceException;
import org.example.springbootloginregisterdemo.mapper.UserMapper;
import org.example.springbootloginregisterdemo.response.Response;
import org.example.springbootloginregisterdemo.service.UserService;
import org.example.springbootloginregisterdemo.service.JwtToken;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;



    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        //check user
        if(user == null || user.getUserId() == null){
            throw new ServiceException("账号不存在");
//            return new Response("pleaase input right userId and password","404");
        }
        //数据库中的用户数据
        User res = userMapper.selectById(user.getUserId());
        //检查是否有返回数据
        if (res == null) {
            throw new ServiceException("该用户不存在");
//            return new Response("this user does not exist","404");
        }
        //注意参数顺序
        if(BCrypt.checkpw(user.getPwd(), res.getPwd())) {
            String token = JwtToken.generateToken(user.getUserId().toString());
            System.out.println(token);
            // Use Map to construct JSON object
            Map<String, String> data = new HashMap<>();
            data.put("token", token);
            return new Response("login success","200",data);
        }
        else{

            return new Response("password error","404");
        }
    }

    @PostMapping("/register")
    public Response register(@RequestBody User user) {
        //examine input
        if(user == null || user.getUserId() == null || user.getPwd() == null){
            return new Response("please input right userId and password","404");
        }
        //examine user existence
        if(userMapper.selectById(user.getUserId()) != null){
            return new Response("this user already exist","404");
        }

        System.out.println(user);

        //加密密码
        user.setPwd(BCrypt.hashpw(user.getPwd(), BCrypt.gensalt()));
        //register
        int i = userService.register(user);
        if(i == 1) {
            return new Response("register success", "200", null);
        }else {
            return new Response("register error","404");
        }
    }

    //用于测试interceptor拦截效果
    @GetMapping("/info")
    public Response info() {
        List<User> userList = userMapper.selectList(null);
        return new Response("test","200",userList);
//        userMapper.selectById()
    }


}
