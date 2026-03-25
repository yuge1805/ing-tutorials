package com.yuge.ing;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author zhangbw
 * @since 2025/7/29
 */
public class AviatorTest {

    public void test() {
        // execute
        AviatorEvaluatorInstance instance = AviatorEvaluator.newInstance();
        instance.setOption(Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL, true);
        instance.setOption(Options.ALWAYS_PARSE_INTEGRAL_NUMBER_INTO_DECIMAL, true);
        Expression expression = instance.compile("a + b", true);
        Map<String, Object> env = new HashMap<>();
        env.put("a", new BigDecimal("10.2"));
        env.put("b", new BigDecimal("20.2"));
        Object r = expression.execute(env);
        System.out.println(r);
    }

    @Test
    public void test2() {
        // execute
        AviatorEvaluatorInstance instance = AviatorEvaluator.newInstance();
        instance.setOption(Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL, true);
        instance.setOption(Options.ALWAYS_PARSE_INTEGRAL_NUMBER_INTO_DECIMAL, true);
        Expression expression = instance.compile("mpcv__1 + mpcv__2", true);
        Map<String, Object> env = new HashMap<>();
        env.put("mpcv__1", new BigDecimal("10.2"));
        env.put("mpcv__2", new BigDecimal("20.2"));
        Object r = expression.execute(env);
        System.out.println(r);
    }

    // 等于运算符测试
    @Test
    public void testEqualsOperator() {
        // 数字相等
        assertEquals(true, AviatorEvaluator.execute("33.3 == 33.3"));
        assertEquals(false, AviatorEvaluator.execute("33.3 == 33.4"));

        // 字符串相等
        assertEquals(true, AviatorEvaluator.execute("hello == hello"));
//        assertEquals(true, AviatorEvaluator.execute("\"hello\" == \"hello1\""));
        assertEquals(true, AviatorEvaluator.execute("\"hello\" == \"hello\""));
        assertEquals(false, AviatorEvaluator.execute("\"hello\" == \"world\""));

        // 变量比较
        Map<String, Object> env = new HashMap<>();
        env.put("aaa", "33.3");
        assertEquals(true, AviatorEvaluator.execute("aaa == 33.3", env));
        assertEquals(false, AviatorEvaluator.execute("aaa == 33.4", env));
    }

    // 不等于运算符测试
    @Test
    public void testNotEqualsOperator() {
        // 数字不相等
        assertEquals(true, AviatorEvaluator.execute("33.3 != 33.4"));
        assertEquals(false, AviatorEvaluator.execute("33.3 != 33.3"));

        // 字符串不相等
        assertEquals(true, AviatorEvaluator.execute("\"hello\" != \"world\""));
        assertEquals(false, AviatorEvaluator.execute("\"hello\" != \"hello\""));

        // 变量比较
        Map<String, Object> env = new HashMap<>();
        env.put("aaa", 33.3);
        assertEquals(true, AviatorEvaluator.execute("aaa != 33.4", env));
        assertEquals(false, AviatorEvaluator.execute("aaa != 33.3", env));
    }

    // 大于运算符测试
    @Test
    public void testGreaterThanOperator() {
        // 数字比较
        assertEquals(true, AviatorEvaluator.execute("33.4 > 33.3"));
        assertEquals(false, AviatorEvaluator.execute("33.3 > 33.3"));
        assertEquals(false, AviatorEvaluator.execute("33.2 > 33.3"));

        // 变量比较
        Map<String, Object> env = new HashMap<>();
        env.put("aaa", 33.3);
        assertEquals(true, AviatorEvaluator.execute("34 > aaa", env));
        assertEquals(false, AviatorEvaluator.execute("33.3 > aaa", env));
        assertEquals(false, AviatorEvaluator.execute("32 > aaa", env));

        Object result = AviatorEvaluator.execute("32 > aaa", env);
        System.out.println(result);
    }

    // 小于运算符测试
    @Test
    public void testLessThanOperator() {
        // 数字比较
        assertEquals(true, AviatorEvaluator.execute("33.2 < 33.3"));
        assertEquals(false, AviatorEvaluator.execute("33.3 < 33.3"));
        assertEquals(false, AviatorEvaluator.execute("33.4 < 33.3"));

        // 变量比较
        Map<String, Object> env = new HashMap<>();
        env.put("aaa", 33.3);
        assertEquals(true, AviatorEvaluator.execute("32 < aaa", env));
        assertEquals(false, AviatorEvaluator.execute("33.3 < aaa", env));
        assertEquals(false, AviatorEvaluator.execute("34 < aaa", env));
    }

    // 大于等于运算符测试
    @Test
    public void testGreaterThanOrEqualOperator() {
        // 数字比较
        assertEquals(true, AviatorEvaluator.execute("33.4 >= 33.3"));
        assertEquals(true, AviatorEvaluator.execute("33.3 >= 33.3"));
        assertEquals(false, AviatorEvaluator.execute("33.2 >= 33.3"));

        // 变量比较
        Map<String, Object> env = new HashMap<>();
        env.put("aaa", 33.3);
        assertEquals(true, AviatorEvaluator.execute("34 >= aaa", env));
        assertEquals(true, AviatorEvaluator.execute("33.3 >= aaa", env));
        assertEquals(false, AviatorEvaluator.execute("32 >= aaa", env));
    }

    // 小于等于运算符测试
    @Test
    public void testLessThanOrEqualOperator() {
        // 数字比较
        assertEquals(true, AviatorEvaluator.execute("33.2 <= 33.3"));
        assertEquals(true, AviatorEvaluator.execute("33.3 <= 33.3"));
        assertEquals(false, AviatorEvaluator.execute("33.4 <= 33.3"));

        // 变量比较
        Map<String, Object> env = new HashMap<>();
        env.put("aaa", 33.3);
        assertEquals(true, AviatorEvaluator.execute("32 <= aaa", env));
        assertEquals(true, AviatorEvaluator.execute("33.3 <= aaa", env));
        assertEquals(false, AviatorEvaluator.execute("34 <= aaa", env));
    }

    // 混合表达式测试
    @Test
    public void testMixedExpressions() {
        Map<String, Object> env = new HashMap<>();
        env.put("aaa", 33.3);
        env.put("bbb", 40);
        env.put("name", "Aviator");

        // 组合比较
        assertEquals(true, AviatorEvaluator.execute("aaa <= 33.3 && bbb > 35", env));
        assertEquals(false, AviatorEvaluator.execute("aaa == 33.4 || bbb < 30", env));
        assertEquals(true, AviatorEvaluator.execute("name == 'Aviator' && aaa < bbb", env));
    }

}
