package org.example.springbootloginregisterdemo.config;

import org.example.springbootloginregisterdemo.common.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//表示这是配置类
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    //自定义拦截器规则
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())   //配置jwt的拦截器规则
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/register");    //豁免的路径，登录与注册路径不用验证token

        super.addInterceptors(registry);
    }

    //注册到spring里，用@Bean
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
