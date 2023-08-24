package com.yuge.ing.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: yuge
 * @date: 2023/7/5
 **/
@Component
public class SpringUtil implements BeanFactoryPostProcessor, ApplicationContextAware {

    private static ConfigurableListableBeanFactory beanFactory;
    private static ApplicationContext applicationContext;
    private static final String PATTERN = "\\{(.*?)\\}";

    public static ListableBeanFactory getBeanFactory() {
        return null == beanFactory ? applicationContext : beanFactory;
    }

    public static <A extends Annotation> String[] findUrlAntPathsByAnnotation(Class<A> annotationType) {
        Map<String, RequestMappingHandlerMapping> mappings = getBeanFactory().getBeansOfType(RequestMappingHandlerMapping.class);

        List<String> paths = new ArrayList<>();
        for (RequestMappingHandlerMapping mapping : mappings.values()) {
            Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
            map.entrySet().stream().forEach(requestMappingInfoHandlerMethodEntry -> {
                HandlerMethod handlerMethod = requestMappingInfoHandlerMethodEntry.getValue();
                RequestMappingInfo info = requestMappingInfoHandlerMethodEntry.getKey();
                A loginIgnore = getAnnotation(handlerMethod, annotationType);
                if (loginIgnore != null) {
                    paths.addAll(info.getPathPatternsCondition().getPatternValues().stream().map(url -> url.replaceAll(PATTERN, "*")).collect(Collectors.toList()));
                    return;
                }
            });
        }
        return paths.stream().distinct().toArray(String[]::new);
    }

    public static <A extends Annotation> A getAnnotation(HandlerMethod handlerMethod, Class<A> annotationClass) {
        A annotation = handlerMethod.getMethodAnnotation(annotationClass);
        if (annotation != null) {
            return annotation;
        }
        return AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getBeanType(), annotationClass);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringUtil.beanFactory = configurableListableBeanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

}
