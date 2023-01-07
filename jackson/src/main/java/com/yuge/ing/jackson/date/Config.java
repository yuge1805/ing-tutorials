package com.yuge.ing.jackson.date;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: yuge
 * @date: 2022/12/14
 **/
@Configuration
public class Config {

    public final static String DATE_FORMAT = "yyyy-MM";
    public final static String DATE_FORMAT2 = "yyyy-MM-dd HH:mm:ss";

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        // simpleDateFormat设置后，ObjectMapper会成为非线程安全
        // simpleDateFormat仅影响java.util.Date类
        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.simpleDateFormat(DATE_FORMAT)
                        .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                        .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT2)));

    }

}
