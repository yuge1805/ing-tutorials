package com.yuge.ing.spring.config;

import com.yuge.ing.spring.annotation.LoginIgnore;
import com.yuge.ing.spring.util.SpringUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @author: yuge
 * @date: 2023/7/5
 **/
@Configuration
public class SpringConfig implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        String[] urlArray = SpringUtil.findUrlAntPathsByAnnotation(LoginIgnore.class);
        System.out.println(Arrays.asList(urlArray));
    }

}
