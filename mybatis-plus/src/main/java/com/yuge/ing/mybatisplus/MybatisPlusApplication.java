package com.yuge.ing.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://github.com/baomidou/mybatis-plus/blob/aebaca4dc0ecb2795a7b393e2ba53386f3a5a283/mybatis-plus/src/test/java/com/baomidou/mybatisplus/test/h2/H2StudentMapperTest.java
 * https://github.com/baomidou/mybatis-plus/blob/3.0/mybatis-plus-annotation/src/main/java/com/baomidou/mybatisplus/annotation/EnumValue.java
 * https://baomidou.com/pages/56bac0/#defaultenumtypehandler
 */
@SpringBootApplication
@MapperScan("com.yuge.ing.mybatisplus.mapper")
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}
