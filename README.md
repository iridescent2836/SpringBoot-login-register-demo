# 系统说明
登录与注册系统，实现登录，注册与token认证功能。

## 注册
见UserController，为用户名密码注册，根据前端发送的数据，创建User实例，加密密码，插入数据库中。
## 登录
UserController中实现，为用户名密码登录，根据前端发送的数据，验证数据库中是否存在相应用户及密码正确与否，由此登录
## token
InterceptorConfig为拦截器配置文件，配置要拦截的url和不用拦截（豁免）的url。
<br>
被拦截的url要先经过JwtInterceptor中检查后才能正常执行UserController中的逻辑。
<br>
Interceptor教程：【【带小白做毕设】12. SpringBoot集成JWT token实现权限验证】 https://www.bilibili.com/video/BV1p14y1174k/?share_source=copy_web&vd_source=f1c6a02629caa255a3e2f4fade81e42e P12
<br>
token生成，验证逻辑在JwtToken类中，
<br>
token生成，验证逻辑参考教程：【1天搞定SpringBoot+Vue全栈开发】 https://www.bilibili.com/video/BV1nV4y1s7ZN/?p=16&share_source=copy_web&vd_source=f1c6a02629caa255a3e2f4fade81e42e P17

## 其他信息
Response为默认返回格式类，用于规范返回格式，已在所有返回中使用。
<br>
exception包内的两个类为自定义Exception，用于规范报错信息格式，尚未在所有报错中使用。