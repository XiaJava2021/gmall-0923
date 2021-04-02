package com.atguigu.gmall.payment.interceptors;

import com.atguigu.gmall.payment.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Hasee  2021/3/29 10:30 周一
 * @version JDK 8.017
 * @description:
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Autowired

    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        UserInfo userInfo = new UserInfo();
        String userId = request.getHeader("userId");
        userInfo.setUserId(Long.valueOf(userId));
        THREAD_LOCAL.set(userInfo);

        return true;
    }

    /**
     * 可以从本地线程的局部变量中获取载荷
     * @return
     */
    public static UserInfo getUserInfo(){
        return THREAD_LOCAL.get();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 由于使用的是 tomcat 线程池(线程不会结束),所以必须显式调用该方法,否则会导致内存泄漏
        THREAD_LOCAL.remove();
    }
}
