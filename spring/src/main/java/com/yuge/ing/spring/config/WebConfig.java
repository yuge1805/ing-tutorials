package com.yuge.ing.spring.config;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.yuge.ing.spring.annotation.LoginIgnore;
import com.yuge.ing.spring.formatter.DecryptStringFormatterFactory;
import com.yuge.ing.spring.interceptor.MyInterceptor;
import com.yuge.ing.spring.util.SpringUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author: yuge
 * @date: 2023/7/5
 **/
@Configuration
@EnableSpringUtil
public class WebConfig implements InitializingBean, WebMvcConfigurer {

    @Override
    public void afterPropertiesSet() throws Exception {
        String[] urlArray = SpringUtil.findUrlAntPathsByAnnotation(LoginIgnore.class);
        System.out.println(Arrays.asList(urlArray));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                // 拦截所有路径
                .addPathPatterns("/**")
                // 排除某些路径
                .excludePathPatterns("/login", "/logout");
    }

    /**
     * decrypt formatter
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer decryptStringFormatConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addFormatterForFieldAnnotation(new DecryptStringFormatterFactory());
            }
        };
    }

}
