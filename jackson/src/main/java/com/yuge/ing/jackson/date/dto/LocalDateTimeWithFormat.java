package com.yuge.ing.jackson.date.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: yuge
 * @date: 2024/3/12
 **/
@Data
@NoArgsConstructor
public class LocalDateTimeWithFormat {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+2")
    private LocalDateTime date;

    public LocalDateTimeWithFormat(LocalDateTime date) {
        this.date = date;
    }

}
