package com.yuge.ing.jackson.date.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: yuge
 * @date: 2024/3/12
 **/
@Data
@NoArgsConstructor
public class DateWithFormat {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8", locale = "en_US")
    private Date date;

    public DateWithFormat(Date date) {
        this.date = date;
    }

}
