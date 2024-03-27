package com.yuge.ing.shardingsphere.service;

import com.yuge.ing.shardingsphere.condition.OrderItemCondition;
import com.yuge.ing.shardingsphere.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuge
 * @since 2024-03-26
 */
public interface IOrderItemService extends IService<OrderItem> {

    /**
    * query by page
    *
    * @param orderItemCondition
    * @return
    */
    IPage<OrderItem> queryByPage(OrderItemCondition orderItemCondition);

}
