package com.yuge.ing.jackson.date;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
class DateControllerTest {

    @Resource
    protected MockMvc mockMvc;

    /**
     * 验证Jackson2ObjectMapperBuilder#simpleDateFormat
     *
     * simpleDateFormat
     *
     */
    @Test
    @SneakyThrows
    void now_QueryNow_SuccessToReturn() {
        validateNow("/date/now");
    }

    @Test
    @SneakyThrows
    void localDateTimeNow_QueryNow_SuccessToReturn() {
        validateNow("/date/local-date-time/now");
    }

    @Test
    @SneakyThrows
    void vo_Query_SuccessToReturn() {
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/date/vo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.log())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(contentAsString);
    }

    @Test
    @SneakyThrows
    void submit_With_SuccessToReturn() {
        String body = "{\"localDateTime\":\"2022-12-01 12:00:02\"}";
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/date/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(body))
                .andDo(MockMvcResultHandlers.log())
//                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(contentAsString);
    }

    private void validateNow(String urlTemplate) throws Exception {
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.log())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(contentAsString);
        // 用魔法验证魔法
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Config.DATE_FORMAT);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(contentAsString);
        Assertions.assertEquals(simpleDateFormat.format(new Date()), jsonNode.get("data").textValue());
    }

}