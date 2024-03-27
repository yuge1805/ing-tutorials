package com.yuge.ing.shardingsphere.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 *
 * @author yuge
 * @since 2024-03-26
 */
@Getter
@Setter
public class OrderVO {


    private Long orderId;

    private Long userId;

    private String username;

    private String status;

    private List<OrderItemVO> items;

}
