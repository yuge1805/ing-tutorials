package com.yuge.ing.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yuge
 * @date: 2023/10/18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngCustomMessage<T> {

    private String msgType;

    private T content;

}
