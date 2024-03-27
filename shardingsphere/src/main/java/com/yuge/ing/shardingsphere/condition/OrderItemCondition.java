package com.yuge.ing.shardingsphere.condition;

import com.yuge.ing.shardingsphere.dto.PageQuery;
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
public class OrderItemCondition extends PageQuery {

    private Long itemId;

    private Long orderId;

    private Long userId;

    private String status;

}
