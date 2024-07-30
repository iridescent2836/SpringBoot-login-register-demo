package org.example.springbootloginregisterdemo.common;

import com.auth0.jwt.JWT;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springbootloginregisterdemo.entity.User;
import org.example.springbootloginregisterdemo.exception.ServiceException;
import org.example.springbootloginregisterdemo.mapper.UserMapper;
import org.example.springbootloginregisterdemo.service.JwtToken;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //get token
        String token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }


        // Verify token and extract userId
        String userId = JwtToken.getUserIdFromToken(token);
        if (userId == null) {
            throw new ServiceException("no user, login please", "401");
        }


        // Check if token is expired
        if (JwtToken.isTokenExpired(token)) {
            throw new ServiceException("Token is expired", "403");
        }

        //Check if user exists
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new ServiceException("user wrong", "401");
        }


        System.out.println("Token verified: " + token);
        return true;
    }
}
