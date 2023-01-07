package com.yuge.ing.jackson.date.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: yuge
 * @date: 2022/12/15
 **/
@Data
public class DateDTO {

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime localDateTime;

}
