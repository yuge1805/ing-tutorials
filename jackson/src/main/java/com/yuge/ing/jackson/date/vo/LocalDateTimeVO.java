package com.yuge.ing.jackson.date.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: yuge
 * @date: 2022/12/14
 **/
@Data
public class LocalDateTimeVO {

    private LocalDateTime localDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime localDateTimeWithFormat;

}
