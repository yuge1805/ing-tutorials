package com.yuge.ing.aviator.demo;

import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class Demo {

    private AviatorEvaluatorInstance aviatorEvaluatorInstance;

    public void ddd() {
        Expression expression = aviatorEvaluatorInstance.compile("", true);
        Map<String, Object> env = Maps.newHashMap();
        Object result = expression.execute(env);
    }

}
