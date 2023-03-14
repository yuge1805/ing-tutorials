package com.yuge.ing.amqp.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: yuge
 * @date: 2023/2/23
 **/
@Data
@Accessors(chain = true)
public class SmsMessage {

    private String phone;

    private String content;
    
}
