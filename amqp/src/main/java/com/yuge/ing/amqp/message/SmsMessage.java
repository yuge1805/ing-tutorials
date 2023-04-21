package com.yuge.ing.amqp.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: yuge
 * @date: 2023/2/23
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SmsMessage {

    private String phone;

    private String content;

    public SmsMessage(String phone, String content) {
        this.phone = phone;
        this.content = content;
    }
}
