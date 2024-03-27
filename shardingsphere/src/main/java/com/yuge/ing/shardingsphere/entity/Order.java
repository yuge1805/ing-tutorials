package com.yuge.ing.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuge
 * @since 2024-03-26
 */
@Getter
@Setter
@TableName("t_order")
public class Order {

    @TableId(value = "order_id", type = IdType.ASSIGN_ID)
    private Long orderId;

    @TableField("user_id")
    private Long userId;

    @TableField("status")
    private String status;
}
