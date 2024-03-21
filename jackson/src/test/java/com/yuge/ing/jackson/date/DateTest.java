package com.yuge.ing.jackson.date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuge.ing.jackson.date.dto.DateWithFormat;
import com.yuge.ing.jackson.date.dto.LocalDateTimeWithFormat;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author: yuge
 * @date: 2024/3/12
 **/
public class DateTest {

    @Test
    public void testDate() throws ParseException, JsonProcessingException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2024-03-12 12:00:00");
        DateWithFormat dto = new DateWithFormat(date);
        ObjectMapper mapper = new ObjectMapper();
        TimeZone timeZone = mapper.getSerializationConfig().getTimeZone();
        System.out.println(timeZone.getID());
        String json = mapper.writeValueAsString(dto);
        System.out.println(json);
    }

    @Test
    public void testLocalDateTime() throws JsonProcessingException {
        LocalDateTimeWithFormat dto = new LocalDateTimeWithFormat(LocalDateTime.of(2024, 3, 12, 12, 0,0));
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        TimeZone timeZone = mapper.getSerializationConfig().getTimeZone();
        System.out.println(timeZone.getID());
        String json = mapper.writeValueAsString(dto);
        System.out.println(json);
    }

}
