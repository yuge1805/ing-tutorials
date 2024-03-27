package com.yuge.ing.shardingsphere.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 *
 * @author yuge
 * @since 2024-03-26
 */
@Getter
@Setter
public class OrderItemPageQuery extends PageQuery {

    private Long itemId;

    private Long orderId;

    private Long userId;

    private String status;

}
