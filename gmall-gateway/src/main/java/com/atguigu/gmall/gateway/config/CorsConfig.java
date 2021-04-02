package com.atguigu.gmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author Hasee  2021/3/9 16:39 周二
 * @version JDK 8.017
 * @description:
 */

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter(){

        // 初始化 cors 配置类
        CorsConfiguration config = new CorsConfiguration();
        // 允许跨域访问的域名   *: 代表所有域名可以跨域访问,但是不能携带 cookie. 要携带 cookie ,必须配置具体的域名
        config.addAllowedOrigin("http://manager.gmall.com");
        config.addAllowedOrigin("http://localhost:1000");
        config.addAllowedOrigin("http://127.0.0.1:1000");
        config.addAllowedOrigin("http://192.168.255.1:1000");
        config.addAllowedOrigin("http://www.gmall.com");
        config.addAllowedOrigin("http://gmall.com");
        config.addAllowedOrigin("http://item.gmall.com");
        config.addAllowedOrigin("http://order.gmall.com");
        // 允许所有请求方式跨域访问
        config.addAllowedMethod("*");
        // 允许携带 cookie
        config.setAllowCredentials(true);
        // 允许携带任何请求头信息
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",config);
        return new CorsWebFilter(configurationSource);
    }
}
