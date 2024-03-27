package com.yuge.ing.shardingsphere.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 
 *
 * @author yuge
 * @since 2024-03-26
 */
@Getter
@Setter
public class OrderItemVO {


    private Long itemId;

    private Long orderId;

    private Long userId;

    private String status;

}
