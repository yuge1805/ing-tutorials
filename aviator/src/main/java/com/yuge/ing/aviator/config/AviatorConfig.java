package com.yuge.ing.aviator.config;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Options;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangbw
 * @since 2025/7/29
 */
@Configuration
public class AviatorConfig {

    /**
     * AviatorEvaluatorInstance
     *
     * @return
     */
    @Bean
    public AviatorEvaluatorInstance aviatorEvaluatorInstance() {
        AviatorEvaluatorInstance instance = AviatorEvaluator.newInstance();
        instance.setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.EVAL);
        instance.setOption(Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL, true);
        instance.setOption(Options.TRACE_EVAL, false);
        return instance;
    }

}
